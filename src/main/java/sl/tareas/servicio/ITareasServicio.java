package sl.tareas.servicio;

import sl.tareas.modelo.Tarea;

import java.util.List;

public interface ITareasServicio {
    public List<Tarea> mostrarTareas();

    public Tarea buscarTareaPorid(Integer idTarea);

    public void guardarTarea(Tarea tarea);

    public void eliminarTarea(Tarea tarea);

}
