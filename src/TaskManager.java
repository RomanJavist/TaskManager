import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;

// Clase básica para la gestión de tareas
public class TaskManager {
    private final ArrayList<Task> tasks = new ArrayList<>();
    private int[] priorities = new int[10];
    private final Storable storage;

    public TaskManager(Storable storage) {
        this.storage = storage;

// Cargar tareas desde el archivo en la inicialización
        try {
            ArrayList<Task> loadedTasks = storage.load();
            tasks.addAll(loadedTasks);
            for (int i = 0; i < tasks.size(); i++) {
                priorities[i] = tasks.get(i).getPriority();
            }
            System.out.println("Las tareas se han descargado correctamente del archivo.");
        } catch (IOException e) {
            System.out.println("Falló cargar tareas. Nueva lista iniciada.");
        }
    }

    // Método de adición de tareas
    public void addTask(String title, String description) {
        Random random = new Random();
        int priority = random.nextInt(3) + 1;
        tasks.add(new Task(title, description, priority));
        System.out.println("Se ha añadido la tarea prioritaria: " + priority);
        saveTask(); // Guardar cambios en el archivo
    }

    // Método para eliminar tareas
    public void removeTask(int index) throws InvalidTaskOperationException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskOperationException("El índice de problemas no es válido.");
        }
        tasks.remove(index);
        System.out.println("La tarea se elimina.");
        saveTask(); // Guardar cambios en el archivo
    }

    // Método para la edición de tareas
    public void editTask(int index, String newTitle, String newDescription) throws InvalidTaskOperationException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskOperationException("El índice de problemas no es válido.");
        }
        Task task = tasks.get(index);
        task.setTitle(newTitle);
        task.setDescription(newDescription);
        System.out.println("Tarea actualizada.");
        saveTask(); // Guardar cambios en el archivo
    }

    // Método para realizar la tarea
    public void markTaskAsCompleted(int index) throws InvalidTaskOperationException {
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskOperationException("El índice de problemas no es válido.");
        }
        tasks.get(index).markAsCompleted(); // Marcar la tarea como completada
        System.out.println("Tarea " + tasks.get(index).getTitle() + " marcado como completado.");
        saveTask(); // Guardar cambios en el archivo
    }


    // Método para mostrar la lista de tareas
    public void listTasks() {
        Iterator<Task> taskIterator = tasks.iterator();
        for (int i = 0; i < tasks.size(); i++) {
            if (taskIterator.hasNext()) {
                Task task = taskIterator.next();
                System.out.println(i + ": " + task + "\nPrioridad: " + task.getPriority());
            }
        }
    }

    // Método para añadir una tarea con un plazo
    public void addTaskWithDeadline(String title, String description, Date deadline) {
        Random random = new Random();
        int priority = random.nextInt(3) + 1;
        tasks.add(new TaskWithDeadline(title, description, priority, deadline));
        System.out.println("Se ha añadido la tarea de plazo con prioridad: " + priority);
        saveTask();
    }

    // Método para guardar tareas
    public void saveTask() {
        try {
            storage.save(tasks);
            System.out.println("Tareas retenidas.");
        } catch (IOException e) {
            System.out.println("No se pudo guardar las tareas: " + e.getMessage());
        }
    }
}

