package Calculator.controller;
import Calculator.view.general.ViewNumeralSystem;

public class NumeralSystemConverter {
    public NumeralSystemConverter(){}
    protected String[] DectoHex_Bin (double decimalNumber) {
        double decimalNumberRounded = ((double) Math.round(decimalNumber * 1));
        int decInt = (int) decimalNumberRounded;
        String[] ArrayHex_Bin = new String[2];
        if (decimalNumber < 0) {
            decInt = Math.abs(decInt);
            ArrayHex_Bin[0] = "-" + Integer.toBinaryString(decInt);
            ArrayHex_Bin[1] = "-" + Integer.toHexString(decInt);
            return ArrayHex_Bin;

            //BinTextField.setText("-" + Integer.toBinaryString(decInt));
            //HexaTextField.setText("-" + Integer.toHexString(decInt));
        } else {
            //Umrechnung vom Dezimalsystem in die anderen beiden Zahlensysteme
            ArrayHex_Bin[0] = Integer.toBinaryString(decInt);
            ArrayHex_Bin[1] = Integer.toHexString(decInt);
            return ArrayHex_Bin;
        }
    }
}
        /*
        String[] ArrayHex_Bin = new String[2];
        //ArrayHex_Bin[0] = Hex;
        return ArrayHex_Bin;
        //double decimalNumber = Double.parseDouble(DecTextField.getText().replace(',', '.'));
        //Runden der eingegeben Zahl
        //double decimalNumberRounded = ((double) Math.round(decimalNumber * 1));
        //int decInt = (int) decimalNumberRounded;
        // @Maths.abs() prüft, ob Zahl negativ, wenn ja: Betrag der Zahl wird genommen
        // (bei Hexa und Binär nicht klar, ob negative Zahlen, Martin?)
        // sonst einfach löschen
        if (decimalNumber < 0) {
            decInt = Math.abs(decInt);
            BinTextField.setText("-" + Integer.toBinaryString(decInt));
            HexaTextField.setText("-" + Integer.toHexString(decInt));
        } else {
            //Umrechnung vom Dezimalsystem in die anderen beiden Zahlensysteme
            BinTextField.setText(Integer.toBinaryString(decInt));
            HexaTextField.setText(Integer.toHexString(decInt));
        }
    }
}

         */