package sl.tareas.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Objects;

@Entity

public class Tarea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombreTarea;
    private String responsable;
    private String estatus;

    public Tarea() {
    }

    public Tarea(Integer id, String nombreTarea, String responsable, String estatus) {
        this.id = id;
        this.nombreTarea = nombreTarea;
        this.responsable = responsable;
        this.estatus = estatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getNombreTarea() {
        return nombreTarea;
    }

    public void setNombreTarea(String nombreTarea) {
        this.nombreTarea = nombreTarea;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tarea tarea = (Tarea) o;
        return Objects.equals(id, tarea.id) && Objects.equals(nombreTarea, tarea.nombreTarea) && Objects.equals(responsable, tarea.responsable) && Objects.equals(estatus, tarea.estatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombreTarea, responsable, estatus);
    }

    @Override
    public String toString() {
        return "Tarea{" +
                "id=" + id +
                ", nombreTarea='" + nombreTarea + '\'' +
                ", responsable='" + responsable + '\'' +
                ", estatus='" + estatus + '\'' +
                '}';
    }
}
