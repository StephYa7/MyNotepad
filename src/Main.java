import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Path file = Path.of("notes.json");
    private static List<Note> notes = new ArrayList<Note>();

    public static void main(String[] args) {
        loadAllNotes();

        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (choice != 5) {
            System.out.println("Выберите действие:");
            System.out.println("1. Просмотреть заметки");
            System.out.println("2. Добавить новую заметку");
            System.out.println("3. Редактировать заметку");
            System.out.println("4. Удалить заметку");
            System.out.println("5. Выход");
            System.out.print("Введите номер действия: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewNotes(scanner,notes);
                    break;
                case 2:
                    addNote(scanner, notes);
                    break;
                case 3:
                    editNote(scanner, notes);
                    break;
                case 4:
                    deleteNote(scanner, notes);
                    break;
                case 5:
                    saveNotesToFile(notes);
                    System.out.println("Выход...");
                    break;
                default:
                    System.out.println("Неверный номер действия. Повторите ввод.");
            }
        }

    }

    private static void loadAllNotes() {
    }

    private static void saveNotesToFile(List<Note> notes) {
    }

    private static void deleteNote(Scanner scanner, List<Note> notes) {

    }

    private static void editNote(Scanner scanner, List<Note> notes) {

    }

    private static void addNote(Scanner scanner, List<Note> notes) {

    }

    private static void viewNotes(Scanner scanner, List<Note> notes) {

    }
}