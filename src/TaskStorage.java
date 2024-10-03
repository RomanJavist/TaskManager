import java.io.*;
import java.util.ArrayList;

// Clase para guardar y descargar datos de tareas
public class TaskStorage implements Storable {
    private final String filePath;

    public TaskStorage(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void save(ArrayList<Task> tasks) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        for (Task task : tasks) {
            writer.write(task.toFileString());
            writer.newLine();
        }
        writer.close();
    }

    @Override
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.split(";").length > 4) {
                tasks.add(TaskWithDeadline.fromFileString(line));
            } else {
                tasks.add(Task.fromFileString(line));
            }
        }
        reader.close();
        return tasks;
    }
}
