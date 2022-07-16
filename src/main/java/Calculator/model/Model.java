package Calculator.model;

import Calculator.controller.Controller;
import Calculator.view.main.View;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Model-Klasse mit allen gespeicherten Daten.
 */
public class Model {
    private View view;
    private Controller controller;
    // String mit Rechenausdruck
    private String expression;
    // String mit HTML-Ausdruck
    private String html;
    // Liste mit durch Eingaben eingefügten Elementen (des Ausdrucks)
    private LinkedList<String> expressionsElementsList;
    // Liste mit durch Eingaben eingefügten Elementen in HTML-Form
    private LinkedList<String> htmlElementsList;
    // Antwort der letzten Rechnung (s. Ans-Taste)
    private double ans;
    // Zustand des Rechners
    private CalculatorState state;
    // Position des Cursors als Zahl
    private int cursorPosition;
    // Symbol fuer den Cursor in der HTML-Darstellung
    private String cursorSymbolHTML;
    // Farbmodus des Rechners
    private ColorMode colorMode;
    // Speicherung der Farbe der HTML-Darstellung in Abhaengigkeit vom Farbmodus
    private String colorExtension;
    // Variablen, um Einstellungen permanent im Dateisystem zu speichern
    private File file;
    private Scanner fileScanner;
    private File folder;
    private BufferedWriter bufferedWriter;
    // Objekte,
    private StringBuilder sb;
    private InputStream is;
    private BufferedReader rd;
    // JSON, um aktuelle Daten aus dem Internet zu speichern
    private JSONObject json;

    /**
     * Konstruktor: Ausdruecke als leer initialisieren.
     */
    public Model() {
        // Beide Strings als leer initialisieren
        expression = "";
        html = "";
        // Listen initialisieren
        expressionsElementsList = new LinkedList<>();
        htmlElementsList = new LinkedList<>();
        // Letzte Antwort als 0 initialisieren
        ans = 0;
        // Cursor-Symbol setzen
        cursorSymbolHTML = "&#10073;";
        // Cursor an den Ursprungszustand zurueckkehren lassen
        CursorBack();
        // Instanzen von File und BufferedWriter ueber Datei im %APPDATA% des Nutzers
        folder = new File(System.getenv("APPDATA") + "\\Calculator");
        file = new File (System.getenv("APPDATA") + "\\Calculator\\settings.txt");
    }

    /**
     * Methode zur Erweiterung des Ausdrucks-Strings: Element wird in Liste eingefuegt und anschliessend der String erweitert.
     * @param extension Zu erweiternder Ausdruck
     */
    public void ExtendExpression(String extension) {
        if(expressionsElementsList.size() < 100)
        {
            expressionsElementsList.add(cursorPosition, extension);
            expression = GenerateNewExpression();
        }
    }

    /**
     * Methode zur Verkürzung des Ausdrucks-Strings: letztes Element wird aus der Liste entfernt und mithilfe der Liste der String neu generiert.
     */
    public void ShortenExpression() {
        if(expressionsElementsList.size() > 0) {
            expressionsElementsList.remove(cursorPosition-1);
            expression = GenerateNewExpression();
        }
    }

    /**
     * Methode zur Räumung des Expression-Strings und seiner Liste.
     */
    public void ClearExpression() {
        expression = "";
        expressionsElementsList.clear();
    }

    /**
     * Methode zur Erweiterung des HTML-Strings: Element wird in Liste eingefuegt und anschliessend der String erweitert.
     * @param extension Zu erweiternder Ausdruck
     */
    public void ExtendHTML(String extension) {
        if(htmlElementsList.size() < 101) {
            htmlElementsList.add(cursorPosition, extension);
            cursorPosition += 1;
            html = GenerateNewHTML();
        }
    }

    /**
     * Methode zur Verkürzung des Ausdrucks-Strings: letztes Element wird aus der Liste entfernt und mithilfe der Liste der String neu generiert.
     */
    public void ShortenHTML() {
        if(htmlElementsList.size() > 1) {
            htmlElementsList.remove(cursorPosition-1);
            cursorPosition -= 1;
            html = GenerateNewHTML();
        }
    }

    /**
     * Methode zur Leerung des HTML-Strings und seiner Liste, wobei der Cursor an dne Zeilenanfang zurueckkehrt.
     */
    public void ClearHTML() {
        html = "";
        htmlElementsList.clear();
        CursorBack();
    }

    /**
     * Methode zur Leerung des HTML-Strings und seiner Liste ohne Cursor zur Ergebnisanzeige.
     */
    public void ClearHTMLSolution() {
        html = "";
        htmlElementsList.clear();
        // Um Ergebnis an erster Stelle einzufuegen, muss die Einfuegeposition zurueckgesetzt werden
        cursorPosition = 0;
    }

    /**
     * Getter für die Answer-Variable, die als Antwortspeicher dient.
     * @return Gespeicherte Antwort als String
     */
    public String GetAnswer() {
        return Double.toString(ans);
    }

    /**
     * Setter für die Answer-Variable, die als Antwortspeicher dient.
     * @param gottenAnswer Ausgerechneter Wert
     */
    public void SetAnswer(double gottenAnswer) {
        // Strings leeren
        ClearExpression();
        ClearHTMLSolution();
        // Pruefen, ob Antwort ein Sonderfall ist
        if(Double.toString(gottenAnswer).equals("NaN"))
        {
            // Ans nicht setzen, da sonst keine korrekte Eingabe gespeichert
            ExtendHTML("Kein g\u00FCltiger Ausdruck");
        }
        else if(Double.toString(gottenAnswer).equals("Infinity"))
        {
            ExtendHTML("Positiv unendlich");
        }
        else if(Double.toString(gottenAnswer).equals("-Infinity"))
        {
            ExtendHTML("Negativ unendlich");
        }
        // Bei korrekter Rueckgabe
        else
        {
            // Antwort setzen
            ans = gottenAnswer;
            // In Anzeige einfuegen
            ExtendHTML(Double.toString(ans).replace(".",","));
        }
    }

    /**
     * Getter für die HTML-String-Variable, die den HTML-Ausdruck in Textform enthält.
     * Dabei werden die umschliessenden <HTML>-Tags mit eingefuegt.
     * @return String mit HTML-Ausdruck
     */
    public String GetHTMLExpression() {
        return "<html><pre style=\"font-family: Consolas; font-size: " + CalculateSize() + "px; color: "
                + colorExtension + ";\">" + html + "</pre></html>";
    }

    /**
     * Methode, um die aktuelle Schriftgroesse in Abhaengigkeit von der Fenstergroeße zu berechnen.
     * @return Neue Schriftgroesse
     */
    public int CalculateSize()
    {
        return Math.round(35 * view.GetWindowHeight() / 300);
    }

    /**
     * Methode, um die Laenge der HTML-Liste zu erhalten.
     * @return Laenge der Liste
     */
    public int GetHTMLElementsListSize()
    {
        return htmlElementsList.size();
    }

    /**
     * Getter für die Expression-Variable, die den normalen Ausdruck in Textform enthält.
     * @return String mit Ausdruck
     */
    public String GetExpression() {
        return expression;
    }

    /**
     * Methode zur Generierung des Expression-Strings aus der Liste.
     * @return Generierte Expression
     */
    public String GenerateNewExpression() {
        return expressionsElementsList.stream().reduce("", (a, b) -> a + b);
    }

    /**
     * Methode zur Generierung des HTML-Strings aus der Liste.
     * @return Generierte Expression
     */
    public String GenerateNewHTML() {
        return htmlElementsList.stream().reduce("", (a, b) -> a + b);
    }

    /**
     * Methode, um den Cursor um eine Position nach links zu verschieben.
     */
    public void CursorLeft()
    {
        if(cursorPosition > 0)
        {
            htmlElementsList.remove(cursorPosition);
            cursorPosition -= 1;
            htmlElementsList.add(cursorPosition, cursorSymbolHTML);
            expression = GenerateNewExpression();
            html = GenerateNewHTML();
        }
    }

    /**
     * Methode, um den Cursor um eine Position nach rechts zu verschieben.
     */
    public void CursorRight() {
        if (cursorPosition < htmlElementsList.size() - 1) {
            htmlElementsList.remove(cursorPosition);
            cursorPosition += 1;
            htmlElementsList.add(cursorPosition, cursorSymbolHTML);
            html = GenerateNewHTML();
        }
    }

    /**
     * Methode, um den Cursor wieder in den Ursprungszustand zu versetzen.
     */
    public void CursorBack() {
        cursorPosition = 0;
        htmlElementsList.add(cursorSymbolHTML);
        html = GenerateNewHTML();
    }

    /**
     * Methode, um die Position des Cursors in der Liste zu erhalten.
     * @return Position als int
     */
    public int GetCursorPosition()
    {
        return cursorPosition;
    }

    /**
     * Methode, um den Zustand des Taschenrechners zu erhalten.
     */
    public CalculatorState GetState() {
        return state;
    }

    /**
     * Methode, um den Zustand des Taschenrechners zu setzen.
     */
    public void SetState(CalculatorState newState) {
        state = newState;
    }

    /**
     * Methode, um das korrekte Exponierungszeichen hinzuzufuegen.
     * Diese ist noetig, damit man nicht beim Loeschen bspw. zwei "Hoch" und kein "Runter" hat.
     */
    public void AddExponent() {
        // Falls letztes "Hoch" vor letztem "Runter": runter
        if (htmlElementsList.lastIndexOf("<sup>") > htmlElementsList.lastIndexOf("</sup>")) {
            ExtendExpression(")");
            ExtendHTML("</sup>");
        }
        // Falls letztes "Runter vor letztem "Hoch": hoch
        else if (htmlElementsList.lastIndexOf("</sup>") > htmlElementsList.lastIndexOf("<sup>")) {
            ExtendExpression("^(");
            ExtendHTML("<sup>");
        }
        // Falls beides gleich (sprich: noch nichts von beidem hinzugefuegt): hoch
        else {
            ExtendExpression("(");
            ExtendHTML("<sup>");
        }
    }

    /**
     * Methode, um das korrekte Wurzelzeichen einzufuegen.
     * Diese ist noetig, da die Wurzel "geoeffnet" und "geschlossen" werden muss.
     */
    public void AddSquareRoot() {
        // Falls letzte "Oeffnung" vor "Schliessung": schliessen
        if (htmlElementsList.lastIndexOf("&radic;") > htmlElementsList.lastIndexOf("&#39;")) {
            ExtendExpression(")");
            ExtendHTML("&#39;");
        }
        // Falls letzte "Schliessung" vor "Oeffnung": oeffnen
        else if (htmlElementsList.lastIndexOf("&#39;") > htmlElementsList.lastIndexOf("&radic;")) {
            ExtendExpression("sqrt(");
            ExtendHTML("&radic;");
        }
        // Falls beides gleich (sprich: noch nichts von beidem hinzugefuegt): oeffnen
        else {
            ExtendExpression("sqrt(");
            ExtendHTML("&radic;");
        }
    }

    /**
     * Methode, um den Farbmodus zu erhalten.
     * @return Farbmodus
     */
    public ColorMode GetColorMode()
    {
        return colorMode;
    }

    /**
     * Methode, um den hellen Modus zu setzen.
     * Tatsaechliche Umsetzung in der View.
     */
    public void SetLightmode()
    {
        // Modus setzen
        colorMode = ColorMode.LIGHTMODE;
        // HTML-Farbe setzen
        colorExtension = "black";
    }

    /**
     * Methode, um den hellen Modus zu setzen.
     * Tatsaechliche Umsetzung in der View.
     */
    public void SetDarkmode()
    {
        // Modus setzen
        colorMode = ColorMode.DARKMODE;
        // HTML-Farbe setzen
        colorExtension = "white";
    }

    /**
     * Methode, um den Zustand des dunklen (bzw. hellen) Modus im Dateisystem zu speichern.
     * @param active Wahrheitswert, ob dunkler Modus aktiv
     */
    public void SaveDarkmode(boolean active) {
        try {
            // Ordner in %APPDATA% erstellen
            folder.mkdir();
            // BufferedWriter erstellen
            bufferedWriter = new BufferedWriter(new FileWriter(file));
            // Wert schreiben
            if (active) {
                bufferedWriter.write("Darkmode: on");
            }
            else {
                bufferedWriter.write("Darkmode: off");
            }
            // BufferedWriter beenden
            bufferedWriter.close();
        } catch (IOException e) {
            // Da nicht kritisch: kein Fallback noetig
        }
    }

    /**
     * Methode, um den Zustand des dunklen Modus im Dateizustand auszulesen.
     * @return Wahrheitswert, ob aktiv
     */
    public boolean ExtractDarkmode() {
        String data = "";
        file = new File(System.getenv("APPDATA") + "\\Calculator\\settings.txt");
        try {
            fileScanner = new Scanner(file);
            data = fileScanner.nextLine();
        } catch (FileNotFoundException e) {
            // Keine Fehlerbehandlung noetig: wird in der View gehandelt
        }
        if (data.equals("Darkmode: on")) {
            return true;
        }
        else if (data.equals("Darkmode: off")) {
            return false;
        }
        else {
            return false;
        }
    }


    private JSONObject ReadJSONFromUrl(String url) {
        InputStream is;
        try {
            is = new URL(url).openStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = ReadAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private String ReadAll(Reader rd) {
        StringBuilder sb = new StringBuilder();
        int cp;
        while (true) {
            try {
                if (!((cp = rd.read()) != -1)) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public String GetCurrenciesAsString() {
        JSONObject json = ReadJSONFromUrl("https://www.currency-api.com/rates?base=USD");
        return json.toString();
    }


    /**
     * Methode zur Setzung der Referenzen auf View und Controller in Main.
     * @param v View-Instanz
     * @param c Controller-Instanz
     */
    public void UpdateLinks(View v, Controller c) {
        view = v;
        controller = c;
    }
}
