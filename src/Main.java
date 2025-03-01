import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> history = new ArrayList<>();

        while (true) {
            exibirMenu();
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    realizarCalculo(scanner, history);
                    break;
                case 2:
                    exibirHistorico(history);
                    break;
                case 3:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }


    private static void exibirMenu() {
        System.out.println("\n===== CALCULADORA =====");
        System.out.println("1 - Fazer um cálculo");
        System.out.println("2 - Ver histórico");
        System.out.println("3 - Sair");
        System.out.print("Escolha uma opção: ");
    }


    private static void realizarCalculo(Scanner scanner, ArrayList<String> history) {
        try {
            System.out.print("Digite o primeiro número: ");
            BigDecimal value = scanner.nextBigDecimal();

            System.out.print("Digite o operador (+, -, *, /): ");
            char operator = scanner.next().charAt(0);

            System.out.print("Digite o segundo número: ");
            BigDecimal secondValue = scanner.nextBigDecimal();

            String calculation = value + " " + operator + " " + secondValue + " = ";
            BigDecimal result = calcular(value, secondValue, operator);

            if (result != null) {
                history.add(calculation + result);
                System.out.println("Resultado: " + result);
            }
        } catch (Exception e) {
            System.out.println("Entrada inválida! Certifique-se de digitar números corretamente.");
            scanner.nextLine();
        }
    }

    // Método para exibir o histórico de cálculos
    private static void exibirHistorico(ArrayList<String> history) {
        if (history.isEmpty()) {
            System.out.println("Histórico vazio!");
        } else {
            System.out.println("Histórico de cálculos:");
            history.forEach(System.out::println);
        }
    }

    // Método para calcular com base no operador
    private static BigDecimal calcular(BigDecimal value, BigDecimal secondValue, char operator) {
        switch (operator) {
            case '+':
                return value.add(secondValue);
            case '-':
                return value.subtract(secondValue);
            case '*':
                return value.multiply(secondValue);
            case '/':
                if (secondValue.compareTo(BigDecimal.ZERO) == 0) {
                    System.out.println("Erro: divisão por zero!");
                    return null;
                }
                return value.divide(secondValue, 5, RoundingMode.HALF_UP);
            default:
                System.out.println("Operador inválido!");
                return null;
        }
    }
}
