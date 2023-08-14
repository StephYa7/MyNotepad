import java.time.LocalDate;

public class Note {
    private static int id;
    private String head;
    private String body;
    private LocalDate createDate;
    private LocalDate updateDate;

    public Note(int id, String head, String body, LocalDate createDate, LocalDate updateDate) {
        this.head = head;
        this.body = body;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setUpdateDate(LocalDate updateDate) {
        this.updateDate = updateDate;
    }

    public static int getId() {
        return id;
    }

    public String getHead() {
        return head;
    }

    public String getBody() {
        return body;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public LocalDate getUpdateDate() {
        return updateDate;
    }

    @Override
    public String toString() {
        return "Note{id= " + id +
                ", head='" + head + '\'' +
                ", body='" + body + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }
}