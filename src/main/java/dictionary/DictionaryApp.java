package dictionary;

import java.util.Map;
import java.util.Scanner;

public class DictionaryApp {
    public static final String four_letter_dict_path = "src/main/resources/four_letter_dict.txt";
    public static final String five_digit_dict_path = "src/main/resources/five_digit_dict.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DictionaryService fourLetterService = new DictionaryService(new RegexDictionary("[A-Za-z]{4}", four_letter_dict_path));
        DictionaryService fiveDigitService = new DictionaryService(new RegexDictionary("\\d{5}", five_digit_dict_path));

        fourLetterService.load();
        fiveDigitService.load();

        while (true) {
            System.out.println("Choose a dictionary (1 for 4-letter, 2 for 5-digit) or 0 to save and exit:");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) {
                fourLetterService.save();
                fiveDigitService.save();
                break;
            }

            DictionaryService service = (choice == 1) ? fourLetterService : fiveDigitService;

            while (true) {
                System.out.println("Choose operation (1 to add, 2 to remove, 3 to find, 4 to view all, 5 to exit):");
                int operation = scanner.nextInt();
                scanner.nextLine();

                if (operation == 5) {
                    break;
                }

                switch (operation) {
                    case 1:
                        System.out.println("Enter key:");
                        String key = scanner.nextLine();
                        System.out.println("Enter value:");
                        String value = scanner.nextLine();
                        service.addEntry(key, value);
                        break;

                    case 2:
                        System.out.println("Enter key to remove:");
                        key = scanner.nextLine();
                        service.removeEntry(key);
                        break;

                    case 3:
                        System.out.println("Enter key to find:");
                        key = scanner.nextLine();
                        String result = service.findEntry(key);
                        System.out.println("Result: " + result);
                        break;

                    case 4:
                        Map<String, String> entries = service.getAllEntries();
                        for (Map.Entry<String, String> entry : entries.entrySet()) {
                            System.out.println(entry.getKey() + " -> " + entry.getValue());
                        }
                        break;

                    default:
                        System.out.println("Invalid operation.");
                }
            }
        }

        scanner.close();
    }
}
