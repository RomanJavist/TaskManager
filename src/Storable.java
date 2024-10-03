import java.io.IOException;
import java.util.ArrayList;

// Interfaz de almacenamiento
public interface Storable {
    void save(ArrayList<Task> tasks) throws IOException;
    ArrayList<Task> load() throws IOException;
}
