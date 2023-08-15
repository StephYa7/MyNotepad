import java.io.*;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final File file = new File("src/Notes/notes.json");
    private static List<Note> notes = new ArrayList<Note>();

    public static void main(String[] args) {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        loadAllNotes();
        Scanner scanner = new Scanner(System.in);


        int option;
        System.out.println("Hello, this is your notepad.");

        do {
            System.out.println("Select the options: 1. Read the note. 2. Write a note. 3.Edit note. 4.Delete note. 5.Exit");
            option = scanner.nextInt();

            switch (option) {
                case 1 -> readNotes(scanner);
                case 2 -> addNote(scanner);
                case 3 -> editNote(scanner);
                case 4 -> deleteNote(scanner);
                case 5 -> System.out.println("Exit");
            }

        } while (option != 5);

        saveNotesToFile();
        scanner.close();
    }

    private static void loadAllNotes() {
        List<String> notesList = null;
        try {
            notesList = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int maxId = 0;
        for (String notesFromList : notesList) {
            String[] notesFromArray = notesFromList.split(";");

            int id = Integer.parseInt(notesFromArray[0]);
            if (id > maxId) {
                maxId = id;
            }
            String head = notesFromArray[1];
            String body = notesFromArray[2];
            LocalDate createdDate = LocalDate.parse(notesFromArray[3]);
            LocalDate updateDate = LocalDate.parse(notesFromArray[4]);
            Note note = new Note(id, head, body, createdDate, updateDate);
            notes.add(note);
        }
        Note.setMaxId(maxId);
    }

    // Запись в файл из -List<Note> notes- в формате id;head;body;createdDate;updatedDate\n
    private static void saveNotesToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
            for (Note note : notes) {
                int id = note.getId();
                String head = note.getHead();
                String body = note.getBody();
                LocalDate createdDate = note.getCreateDate();
                LocalDate updateDate = note.getUpdateDate();
                writer.write(id + ";" + head + ";" + body + ";" + createdDate + ";" + updateDate + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void deleteNote(Scanner scanner) {
        System.out.println("Enter the id of the note to delete: ");
        int id = scanner.nextInt();
        Note del = null;
        for (Note note : notes) {
            if (note.getId() == id) {
                del = note;
            }
        }
        if (del != null) {
            notes.remove(del);
            System.out.println("Note deleted");
        } else {
            System.out.println("Note not found");
        }
    }

    private static void editNote(Scanner scanner) {
        System.out.println("Enter the id of the note to edite: ");
        int id = scanner.nextInt();
        for (Note note : notes) {
            if (note.getId() == id) {
                System.out.println("Enter new head for note: ");
                note.setHead(scanner.next());
                System.out.println("Enter new body for note: ");
                note.setBody(scanner.next());
                note.setUpdateDate(LocalDate.now());
                return;
            }
        }
        System.out.println("Note not found.");
    }

    private static void addNote(Scanner scanner) {
        System.out.println("Enter new head for note: ");
        String head = scanner.next();
        System.out.println("Enter new body for note: ");
        String body = scanner.next();
        Note note = new Note(head, body, LocalDate.now(), LocalDate.now());
        notes.add(note);
    }

    private static void readNotes(Scanner scanner) {
        System.out.println("Choose an option:\n" +
                "1.Show all. 2.Show by id. 3.Show by date. 4.Exit");
        int option;
        while ((option = scanner.nextInt()) != 1 && option != 2 && option != 3 && option != 4) {
            System.out.println("You have only this option: 1.Show all. 2.Show by id. 3.Show by date. 4.Exit");
        }
        int id = 0;
        boolean found = false;
        LocalDate date = null;
        if (option == 4) return;
        else if (option == 2) {
            System.out.println("Enter id: ");
            id = scanner.nextInt();
        } else if (option == 3) {
            System.out.println("Enter date: ");
            try {
                date = LocalDate.parse(scanner.next());
            } catch (Exception e) {
                System.out.println("Bad date format: " + e.getMessage());
                throw new RuntimeException();
            }
        }
        for (Note note : notes) {
            if (id != 0) {
                if (note.getId() == id) {
                    found = true;
                    System.out.println(note);
                }
            } else if (date != null) {
                if (note.getCreateDate().equals(date)) {
                    found = true;
                    System.out.println(note);
                }
            } else {
                found = true;
                System.out.println(note);
            }
        }
        if (!found) {
            System.out.println("Note not found.");
        }

    }
}