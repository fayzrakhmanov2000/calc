import java.util.Scanner;

public class StringCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение:");
        String input = scanner.nextLine();

        try {
            String result = calculate(input);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public static String calculate(String input) throws Exception {
        // Удаление пробелов вокруг символов
        input = input.trim();

        // Проверяем тип операции
        if (input.contains("+")) {
            String[] parts = input.split("\\+");
            if (parts.length != 2) throw new Exception("Неправильный формат выражения");
            String a = extractString(parts[0].trim());
            String b = extractString(parts[1].trim());
            return "\"" + addStrings(a, b) + "\"";
        } else if (input.contains("-")) {
            String[] parts = input.split("-");
            if (parts.length != 2) throw new Exception("Неправильный формат выражения");
            String a = extractString(parts[0].trim());
            String b = extractString(parts[1].trim());
            return "\"" + subtractStrings(a, b) + "\"";
        } else if (input.contains("*")) {
            String[] parts = input.split("\\*");
            if (parts.length != 2) throw new Exception("Неправильный формат выражения");
            String a = extractString(parts[0].trim());
            int multiplier = extractInteger(parts[1].trim());
            return "\"" + multiplyString(a, multiplier) + "\"";
        } else if (input.contains("/")) {
            String[] parts = input.split("/");
            if (parts.length != 2) throw new Exception("Неправильный формат выражения");
            String a = extractString(parts[0].trim());
            int divisor = extractInteger(parts[1].trim());
            return "\"" + divideString(a, divisor) + "\"";
        } else {
            throw new Exception("Неподдерживаемая операция");
        }
    }

    // Метод для сложения строк
    public static String addStrings(String a, String b) {
        return a + b;
    }

    // Метод для вычитания строки из строки
    public static String subtractStrings(String a, String b) {
        return a.replace(b, "");
    }

    // Метод для умножения строки на число
    public static String multiplyString(String a, int times) throws Exception {
        if (times < 1 || times > 10) throw new Exception("Число должно быть от 1 до 10");
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < times; i++) {
            result.append(a);
        }
        return limitLength(result.toString());
    }

    // Метод для деления строки на число
    public static String divideString(String a, int divisor) throws Exception {
        if (divisor < 1 || divisor > 10) throw new Exception("Число должно быть от 1 до 10");
        int newLength = a.length() / divisor;
        return limitLength(a.substring(0, newLength));
    }

    // Метод для извлечения строки из кавычек
    public static String extractString(String part) throws Exception {
        if (part.startsWith("\"") && part.endsWith("\"")) {
            String str = part.substring(1, part.length() - 1);
            if (str.length() > 10) throw new Exception("Строка должна быть не длиннее 10 символов");
            return str;
        } else {
            throw new Exception("Неправильный формат строки");
        }
    }

    // Метод для извлечения числа
    public static int extractInteger(String part) throws Exception {
        try {
            int value = Integer.parseInt(part);
            if (value < 1 || value > 10) throw new Exception("Число должно быть от 1 до 10");
            return value;
        } catch (NumberFormatException e) {
            throw new Exception("Неправильный формат числа");
        }
    }

    // Ограничение строки до 40 символов
    public static String limitLength(String str) {
        if (str.length() > 40) {
            return str.substring(0, 40) + "...";
        }
        return str;
    }
}
