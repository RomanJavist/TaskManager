import java.util.Date;

// TaskWithDeadline es heredado de la tarea, mostrando polimorfismo
public class TaskWithDeadline extends Task {
    private Date deadline;

    public TaskWithDeadline(String title, String description, int priority, Date deadline) {
        super(title, description, priority);
        this.deadline = deadline;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return super.toString() + "\nDeadline: " + deadline;
    }

    public String toFileString() {
        return super.toFileString() + ";" + deadline.getTime();
    }

    public static TaskWithDeadline fromFileString(String line) {
        Task baseTask = Task.fromFileString(line);
        String[] parts = line.split(";");
        Date deadline = new Date(Long.parseLong(parts[4]));
        return new TaskWithDeadline(baseTask.getTitle(), baseTask.getDescription(), baseTask.getPriority(), deadline);
    }
}


