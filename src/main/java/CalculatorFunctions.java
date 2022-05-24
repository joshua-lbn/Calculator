import java.util.LinkedList;
import java.util.List;

public class CalculatorFunctions {
    private List<String> inputs;
    private List<String> inputsLatext;
    private CalculatorState state;
    public CalculatorFunctions(){
        inputs = new LinkedList<>();
        state = CalculatorState.CALCULATION;
    }

    public void update(String input){
        switch(input){
            case "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" -> {
                if (state == CalculatorState.SOLUTION){
                    state = CalculatorState.CALCULATION;
                }
                // TODO do sth
                inputs.add(input);
                inputsLatext.add(input);
            }

            case "." -> {
                // TODO do sth
                inputs.add(".");
                inputsLatext.add("\\cdot");
            }

            case "+", "-", "*", "/" -> {
                if (state == CalculatorState.SOLUTION){
                    state = CalculatorState.CALCULATION;
                }
                inputs.add(input);
                inputsLatext.add(input);
                // TODO do sth
            }

            case "cos", "sin", "tan" -> {
                if (state == CalculatorState.SOLUTION){
                    state = CalculatorState.CALCULATION;
                }
                inputs.add(input);
                inputsLatext.add("\\" + input);
                // TODO do sth
            }

            case "log" -> {
                inputs.add(input);
                inputsLatext.add("\\log{x}"); //TODO do sth
            }

            case "=" -> {
                state = CalculatorState.SOLUTION;
                calculateOperation();
                //inputs.add(input);
            }

            case "delOneSign" -> {
                inputs.remove(inputs.size() - 1);
                // TODO Jannis
            }

            case "delAll" -> {
                inputs.clear();
            }
        }
    }

    private void calculateOperation() {
    }
}
