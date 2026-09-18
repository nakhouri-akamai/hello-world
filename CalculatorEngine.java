public final class CalculatorEngine {

    private CalculatorEngine() {
    }

    public static double calculate(double first, String operator, double second) {
        switch (operator) {
            case "+":
                return first + second;
            case "-":
                return first - second;
            case "*":
                return first * second;
            case "/":
                if (second == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return first / second;
            case "^":
                return Math.pow(first, second);
            default:
                throw new IllegalArgumentException("Unsupported operator: " + operator);
        }
    }
}