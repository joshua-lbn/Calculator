package Calculator.model;

// Java-Imports
import java.io.*;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Scanner;
// Import weiterer Programmklassen
import Calculator.controller.Controller;
import Calculator.view.main.View;
// Imports des JSON-Packages
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Model-Klasse mit allen gespeicherten Daten.
 */
public class Model {
    // Referenzen auf andere Klassen
    private View view;
    private Controller controller;
    // String mit Rechenausdruck
    private String expression;
    // String mit HTML-Ausdruck
    private String html;
    // Liste mit durch Eingaben eingefügten Elementen (des Ausdrucks)
    private LinkedList<String> expressionsElementsList;
    // Liste mit durch Eingaben eingefügten Elementen (des HTML-Ausdruckes)
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
    // Instanzen, um Einstellungen permanent im Dateisystem zu speichern
    private File file;
    private Scanner fileScanner;
    private File folder;
    private BufferedWriter bufferedWriter;

    /**
     * Konstruktor: Ausdruecke und Listen als leer initialisieren, Antwort initialisieren, Cursorsymbol setzen und
     * Cursor zurueckkehren lassen, Instanzen fuer Schreiben ins Dateisystem erstellen
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
     * Getter für die Expression-Variable, die den normalen Ausdruck in Textform enthält.
     * @return String mit Ausdruck
     */
    public String GetExpression() {
        return expression;
    }

    /**
     * Methode zur Erweiterung des Ausdrucks-Strings: Element wird in Liste eingefuegt an der Position des Cursors
     * eingefuegt und anschliessend wird der String neu aus der Liste generiert.
     * Voraussetzung hierfuer ist eine Laenge von unter 100 Elementen.
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
     * Methode zur Generierung des Expression-Strings aus der Liste.
     * @return Generierte Expression
     */
    public String GenerateNewExpression() {
        return expressionsElementsList.stream().reduce("", (a, b) -> a + b);
    }

    /**
     * Methode zur Verkürzung des Ausdrucks-Strings: Element vor dem Cursor wird aus der Liste entfernt und
     * mithilfe der Liste der String neu generiert.
     * Voraussetzung hierfuer ist eine nicht-leere Liste.
     */
    public void ShortenExpression() {
        if(expressionsElementsList.size() > 0) {
            expressionsElementsList.remove(cursorPosition-1);
            expression = GenerateNewExpression();
        }
    }

    /**
     * Methode zur Leerung des Expression-Strings und seiner Liste.
     */
    public void ClearExpression() {
        expression = "";
        expressionsElementsList.clear();
    }

    /**
     * Getter für die HTML-String-Variable, die den HTML-Ausdruck in Textform enthält.
     * Dabei werden die umschliessenden <HTML>-Tags mit eingefuegt, die Schriftgroesse per Methode (anhand der Fenster-
     * groesse bestimmt) ermittelt und die Farbe ueber die colorExtesion (in Abhaengigkeit vom Farbmodus) gesetzt.
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
     * Methode zur Erweiterung des HTML-Strings: Element wird in Liste an Cursor-Position eingefuegt, die Position um
     * eins erhoeht und anschliessend der String mithilfe der Liste neu generiert.
     * Voraussetzung hierfuer ist eine Laenge von unter 101 Elementen (da Cursor hier inklusive).
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
     * Methode zur Generierung des HTML-Strings aus der Liste.
     * @return Generierte Expression
     */
    public String GenerateNewHTML() {
        return htmlElementsList.stream().reduce("", (a, b) -> a + b);
    }

    /**
     * Methode zur Verkürzung des Ausdrucks-Strings: Element vor dem Cursor wird aus der Liste entfernt, die Cursor-
     * Position um eins verringert und der String mithilfe der Liste neu generiert.
     * Voraussetzung hierfuer ist eine Laenge von ueber einem Element, da Cursor als ein Element eine de facto leere
     * Liste darstellt.
     */
    public void ShortenHTML() {
        if(htmlElementsList.size() > 1) {
            htmlElementsList.remove(cursorPosition-1);
            cursorPosition -= 1;
            html = GenerateNewHTML();
        }
    }

    /**
     * Methode zur Leerung des HTML-Strings und seiner Liste, wobei der Cursor an den Zeilenanfang zurueckkehrt.
     */
    public void ClearHTML() {
        html = "";
        htmlElementsList.clear();
        CursorBack();
    }

    /**
     * Methode zur Leerung des HTML-Strings und seiner Liste ohne Cursor.
     * Verwendet zur Ergebnisanzeige.
     */
    public void ClearHTMLSolution() {
        html = "";
        htmlElementsList.clear();
        // Um das Ergebnis an erster Stelle einzufuegen, muss die Einfuegeposition zurueckgesetzt werden
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
            // In Anzeige einfuegen, wobei ein Punkt durch ein Komma ersetzt wird
            ExtendHTML(Double.toString(ans).replace(".",","));
        }
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
     * Methode, um den Cursor um eine Position nach links zu verschieben.
     */
    public void CursorLeft()
    {
        // Falls Cursor noch nicht ganz links
        if(cursorPosition > 0)
        {
            // Cursor-Element aus HTML-Liste entfernen
            htmlElementsList.remove(cursorPosition);
            // Position aktualisieren
            cursorPosition -= 1;
            // Cursor-Symbol an neuer Position in HTML-Liste einfuegen
            htmlElementsList.add(cursorPosition, cursorSymbolHTML);
            // HTML-String neu generieren
            html = GenerateNewHTML();
        }
    }

    /**
     * Methode, um den Cursor um eine Position nach rechts zu verschieben.
     */
    public void CursorRight() {
        // Falls Cursor noch nicht ganz rechts
        if (cursorPosition < htmlElementsList.size() - 1) {
            // Cursor-Element aus HTML-Liste entfernen
            htmlElementsList.remove(cursorPosition);
            // Position aktualisieren
            cursorPosition += 1;
            // Cursor-Symbol an neuer Position in HTML-Liste einfuegen
            htmlElementsList.add(cursorPosition, cursorSymbolHTML);
            // HTML-String neu generieren
            html = GenerateNewHTML();
        }
    }

    /**
     * Methode, um den Cursor wieder in den Ursprungszustand zu versetzen.
     */
    public void CursorBack() {
        // Position auf 0
        cursorPosition = 0;
        // Am HTML-Listen-Beginn Symbol einfuegen
        htmlElementsList.add(cursorSymbolHTML);
        // HTML-String neu generieren
        html = GenerateNewHTML();
    }

    /**
     * Methode, um das korrekte Exponierungszeichen hinzuzufuegen.
     * Diese ist noetig, damit man bspw. beim Loeschen nicht zwei "Hoch" und kein "Runter" hat.
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
        // String erstellen
        String data = "";
        // Datei finden
        file = new File(System.getenv("APPDATA") + "\\Calculator\\settings.txt");
        try {
            // Datei auslesen
            fileScanner = new Scanner(file);
            data = fileScanner.nextLine();
        } catch (FileNotFoundException e) {
            // Keine Fehlerbehandlung noetig: wird in der View gehandelt
        }
        // Je nach Dateiinhalt: Rueckgabe
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

    /**
     * Methode, um Waehrungsumrechnungskurse der EZB zu erhalten.
     * @param code Dreistelliger Waehrungscode, welche gegenueber dem Euro verglichen wird
     * @return Umrechnungskurs als Double
     */
    public Double GetExchangeRate(String code) {
        try {
            // URL anfragen
            URL url = new URL("https://sdw-wsrest.ecb.europa.eu/service/data/EXR/D." + code + ".EUR.SP00.A?" +
                    "lastNObservations=1&format=jsondata");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            // Pruefen, ob Anfrage durchgegangen
            int responsecode = conn.getResponseCode();
            if (responsecode != 200) {
                return null;
            } else {
                // JSON-Datei einlesen
                String data = "";
                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    data = data + scanner.nextLine();
                }
                scanner.close();
                // Umrechnungskurs extrahieren
                JSONObject dataObject = new JSONObject(data);
                JSONArray dataSets = (JSONArray) dataObject.get("dataSets");
                JSONObject object0_1 = (JSONObject) dataSets.get(0);
                JSONObject series = (JSONObject) object0_1.get("series");
                JSONObject object00000 = (JSONObject) series.get("0:0:0:0:0");
                JSONObject observations = (JSONObject) object00000.get("observations");
                JSONArray object0_2 = (JSONArray) observations.get("0");
                BigDecimal exchangeRate = (BigDecimal) object0_2.get(0);
                return exchangeRate.doubleValue();
            }
        } catch (MalformedURLException e) {
            return null;
        } catch (ProtocolException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
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