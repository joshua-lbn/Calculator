package Calculator.controller;
import java.util.Scanner;

public class NumeralSystemConverter {
    public static void main(String[] args) {
        convertDecToBin();
        convertDecToHex();
    }

    static double convertDecToBin() {
        Scanner input = new Scanner(System.in);//Scanner zur Speicherung der Eingabe
        System.out.println("Please enter a decimal number!");//Aufforderung zur Eingabe

        int decNumber = input.nextInt();//Eingabe wird gespeichert
        int binaryPlace = 0;//Anzahl der Stellen der Dualzahl
        int decNumberTwo = decNumber;//Kopie der Zahl, da am Ende der while-Schleife die Zahl Null ist
        double result = 0;

        while (decNumberTwo != 0) { //While Schleife soll die Anzahl der Stellen bestimmen
            decNumberTwo = decNumberTwo / 2;//Zahl wird solange durch 2 dividiert bis 0 herauskommt
            binaryPlace++;//Erhöhung der Zählvariablen
        }

        int[] numbers = new int[binaryPlace];//Array mit Länge der Zählvariablen

        for (int i = 0; i < binaryPlace; i++) { //For Schleife füllt das Array mit den Restwerten
            numbers[i] = decNumber % 2; //Speichern der Restwerte im Array
            decNumber = decNumber / 2; //Die Zahl wird immer wieder durch 2 dividiert
        }

        for (int i = binaryPlace - 1; i >= 0; i--) { //Die zweite for-Schleife liest das Array von hinten nach vorne
            System.out.print(numbers[i]);
            result = numbers[i];
        }
        return result;
    }

    static String convertDecToHex() {

        Scanner input = new Scanner(System.in);
        System.out.println("Please enter a decimal number!");

        int decNumber = input.nextInt();
        int rem;
        String str = "";

        char[] hex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        while (decNumber > 0) {
            rem = decNumber % 16;
            str = hex[rem] + str;
            decNumber = decNumber / 16;
        }
        System.out.println("Decimal to Hexadecimal: " + str);
        return str;
    }
}