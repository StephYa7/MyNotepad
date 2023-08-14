import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final File file = new File("src/Notes/notes.json");
    private static List<Note> notes = new ArrayList<Note>();

    public static void main(String[] args) throws IOException {
        if (!file.exists()) {
            file.createNewFile();
        }

        Note note = new Note(1, "heeeead", "booooooooergoooooob", LocalDate.now(), LocalDate.now());
        Note note2 = new Note(1, "heeeead", "boooo2df3foooooob", LocalDate.now(), LocalDate.now());
        loadAllNotes();
        System.out.println(notes.size());
//        notes.add(note);
//        notes.add(note);
//        notes.add(note);
//        notes.add(note2);
//        saveNotesToFile();


//        Scanner scanner = new Scanner(System.in);

    }

    private static void loadAllNotes() {
        List<String> notesList = null;
        try {
            notesList = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (String notesFromList : notesList) {
            String[] notesFromArray = notesFromList.split(";");

            int id = Integer.parseInt(notesFromArray[0]);
            String head = notesFromArray[1];
            String body = notesFromArray[2];
            LocalDate createdDate = LocalDate.parse(notesFromArray[3]);
            LocalDate updateDate = LocalDate.parse(notesFromArray[4]);
            Note note = new Note(id, head, body, createdDate, updateDate);
            notes.add(note);
        }
    }

    // Запись в файл из -List<Note> notes- в формате id;head;body;createdDate;updatedDate\n
    private static void saveNotesToFile() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Note note : notes) {
                int id = note.getId();
                String head = note.getHead();
                String body = note.getBody();
                LocalDate createdDate = note.getCreateDate();
                LocalDate updateDate = note.getUpdateDate();
                writer.write(id + ";" + head + ";" + body + ";" + createdDate + ";" + updateDate + "\n");
            }
        }
    }

    private static void deleteNote(int id) {
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

    private static void editNote(Scanner scanner, List<Note> notes) {

    }

    private static void addNote(Scanner scanner, List<Note> notes) {

    }

    private static void viewNotes(Scanner scanner, List<Note> notes) {

    }
}