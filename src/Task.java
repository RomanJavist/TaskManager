// Clase de la base de tareas
public class Task {
    private String title;
    private String description;
    private boolean isCompletad;
    private int priority;

    public Task(String title, String description, int priority) {
        this.title = title;
        this.description = description;
        this.isCompletad = false;
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompletad() {
        return isCompletad;
    }

    public void setCompletad(boolean completad) {
        isCompletad = completad;
    }

    public void markAsCompleted() {
        this.isCompletad = true;
    }

    @Override
    public String toString() {
        return "Tarea: " + title + " - " + (isCompletad ? "Cumplida" : "No cumplida") + "\nDescription: " + description;
    }

    // Método para guardar la tarea en una cadena de archivo
    public String toFileString() {
        return title + ";" + description + ";" + isCompletad + ";" + priority;
    }

    // Método para crear una tarea a partir de una cadena de archivo
    public static Task fromFileString(String line) {
        String[] parts = line.split(";");
        String title = parts[0];
        String description = parts[1];
        boolean isCompleted = Boolean.parseBoolean(parts[2]);
        int priority = Integer.parseInt(parts[3]);
        Task task = new Task(title, description, priority);
        if (isCompleted) {
            task.markAsCompleted();
        }
        return task;
    }
}

