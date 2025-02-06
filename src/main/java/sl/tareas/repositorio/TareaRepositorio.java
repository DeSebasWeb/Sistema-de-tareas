package sl.tareas.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import sl.tareas.modelo.Tarea;

public interface TareaRepositorio extends JpaRepository<Tarea, Integer> {
}
