import java.util.Date;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.Scanner;

// Clase básica para ejecutar el programa
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskStorage storage = new TaskStorage("tasks.txt");
        TaskManager manager = new TaskManager(storage);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Añadir una tarea");
            System.out.println("2. Añadir una tarea con un deadline");
            System.out.println("3. Eliminar la tarea");
            System.out.println("4. Editar la tarea");
            System.out.println("5. Mostrar a la lista de tareas");
            System.out.println("6. Marcar la tarea como completada");
            System.out.println("7. Salir y guardar");
            System.out.println("Seleccione la opción: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Limpieza de búfer

                switch (choice) {
                    case 1:
                        System.out.println("Indíquese el título de la tarea: ");
                        String title = scanner.nextLine();
                        System.out.println("Introduzca la descripción de la tarea: ");
                        String description = scanner.nextLine();
                        manager.addTask(title, description);
                        break;
                    case 2:
                        System.out.println("Indíquese el título de la tarea: ");
                        String titleWithDeadline = scanner.nextLine();
                        System.out.println("Introduzca la descripción de la tarea: ");
                        String descriptionWithDeadline = scanner.nextLine();
                        System.out.println("Introduzca el plazo de la tarea (en formato AAAA-MM-DD):");
                        String deadlineInput = scanner.nextLine();
                        Date deadline = new GregorianCalendar(
                                Integer.parseInt(deadlineInput.split("-")[0]),
                                Integer.parseInt(deadlineInput.split("-")[1]) - 1,
                                Integer.parseInt(deadlineInput.split("-")[2])
                        ).getTime();
                        manager.addTaskWithDeadline(titleWithDeadline, descriptionWithDeadline, deadline);
                        break;
                    case 3:
                        manager.listTasks();
                        System.out.println("Introduzca el número de tarea que desea eliminar: ");
                        int indexToRemove = scanner.nextInt();
                        manager.removeTask(indexToRemove);
                        break;
                    case 4:
                        manager.listTasks();
                        System.out.println("Introduzca el número de tarea a editar: ");
                        int indexToEdit = scanner.nextInt();
                        scanner.nextLine(); // Limpieza de búfer
                        System.out.println("Indíquese el título de la tarea: ");
                        String newTitle = scanner.nextLine();
                        System.out.println("Introduzca la descripción de la tarea: ");
                        String newDescription = scanner.nextLine();
                        manager.editTask(indexToEdit, newTitle, newDescription);
                        break;
                    case 5:
                        manager.listTasks();
                        break;
                    case 6:
                        manager.listTasks();
                        System.out.println("Introduzca el número de la tarea que se desea marcar como completada: ");
                        int indexToMark = scanner.nextInt();
                        manager.markTaskAsCompleted(indexToMark);

                        // Mostrar lista actualizada después de completar la tarea
                        System.out.println("Actualizado a la lista de tareas:");
                        manager.listTasks();
                        break;
                    case 7:
                        System.out.println("Salida...");
                        return;
                    default:
                        System.out.println("Opción no válida. Por favor, inténtelo de nuevo.");
                }
            } catch (InvalidTaskOperationException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Error al introducir datos. Inténtalo de nuevo");
                scanner.next(); // Borrar la entrada incorrecta
            }
        }

    }
}
