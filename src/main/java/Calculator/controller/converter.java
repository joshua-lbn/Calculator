package Calculator.controller;
import java.util.Scanner;

public class converter {
    static void convertDecToBin(){
        Scanner input = new Scanner(System.in);//Scanner zur Speicherung der Eingabe
        System.out.println("Bitte gib eine Dezimalzahl ein!");//Aufforderung zur Eingabe

        int decNumber = input.nextInt();//Eingabe wird gespeichert
        int binaryPlace=0;//Anzahl der Stellen der Dualzahl
        int decNumberTwo=decNumber;//Kopie der Zahl, da am Ende der while-Schleife die Zahl Null ist

        /*
         * While Schleife soll die Anzahl der Stellen bestimmen
         */
        while (decNumberTwo != 0){
            decNumberTwo=decNumberTwo / 2;//Zahl wird solange durch 2 dividiert bis 0 herauskommt
            binaryPlace++;//Erhöhung der Zählvariablen
        }

        int []numbers = new int [binaryPlace];//Array mit Länge der Zählvariablen

        /*
         * For Schleife füllt das Array mit den Restwerten
         */
        for (int i = 0; i < binaryPlace; i++){
            numbers[i]=decNumber % 2; //Speichern der Restwerte im Array
            decNumber = decNumber / 2; //Die Zahl wird immer wieder durch 2 dividiert
        }

        /*
         * Die zweite for-Schleife liest das Array von hinten nach vorne
         */
        for (int i = binaryPlace - 1; i >= 0; i--){
            System.out.print(numbers[i]);
        }
    }

    public static void main(String[] args) {
        convertDecToBin();
    }
}

