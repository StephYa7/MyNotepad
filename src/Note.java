import java.time.LocalDate;

public class Note {
    private static int id;
    private String head;
    private String body;
    private LocalDate date;


    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDate getDate() {
        return date;
    }

    public static int getId() {
        return id;
    }

    public Note() {
        this.date = LocalDate.now();
        this.id = id;
        this.id++;
    }

    @Override
    public String toString() {
        return "Note{" +
                "head='" + head + '\'' +
                ", body='" + body + '\'' +
                ", date=" + date +
                '}';
    }
}