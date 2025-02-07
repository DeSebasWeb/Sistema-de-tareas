package sl.tareas.controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sl.tareas.modelo.Tarea;
import sl.tareas.servicio.TareaServicio;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class IndexControlador implements Initializable {

    private static final Logger logger = LoggerFactory.getLogger(IndexControlador.class);

    @Autowired
    private TareaServicio tareaServicio;

    @FXML
    private TableView<Tarea> tareaTabla;
    @FXML
    private TableColumn<Tarea, Integer> idTareaColumna;
    @FXML
    private TableColumn<Tarea, String> nombreTareaColumna;
    @FXML
    private TableColumn<Tarea, String> nombreResponsable;
    @FXML
    private TableColumn<Tarea, String> estatusColumna;

    private Integer idTareaInterno;

    private final ObservableList<Tarea> tareaList = FXCollections.observableArrayList();

    @FXML
    private TextField nombreTareaTexto;
    @FXML
    private TextField  responsableTexto;
    @FXML
    private TextField estatusTexto;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tareaTabla.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        configurarColumnas();
        mostraTareas();
    }

    private void configurarColumnas(){
        idTareaColumna.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombreTareaColumna.setCellValueFactory(new PropertyValueFactory<>("nombreTarea"));
        nombreResponsable.setCellValueFactory(new PropertyValueFactory<>("responsable"));
        estatusColumna.setCellValueFactory(new PropertyValueFactory<>("estatus"));
    }
    private void mostraTareas(){
        logger.info("Ejecutando el metodo mostrar tareas");
        tareaList.clear();
        tareaList.addAll(tareaServicio.mostrarTareas());
        logger.info("datos= "+ tareaList.size());
        tareaTabla.setItems(tareaList);
    }
//Boton para agregar la tarea
    public void agregarTarea(){
        if (nombreTareaTexto.getText().isEmpty()){
            mostrarMensaje("Error de validacion", "Debe proporcionar una tarea");
            nombreTareaTexto.requestFocus();
            return;
        }
        else {
            Tarea tarea = new Tarea();
            recolectarDatosFormulario(tarea);
            tarea.setId(null);
            tareaServicio.guardarTarea(tarea);
            mostrarMensaje("Novedad","Tarea guardada");
            limpiarFormulario();
            mostraTareas();
        }
    }
    public void modificarTarea(){

        if (idTareaInterno == null){
            mostrarMensaje("Advertencia", "Debe seleccionar una tarea para modificar");
            return;
        }
        if(nombreTareaTexto.getText().isEmpty()) {
            mostrarMensaje("Error de validacion", "Debe proporcionar una tarea");
            nombreTareaTexto.requestFocus();
            return;
        }
        var tarea = new Tarea();
        recolectarDatosFormulario(tarea);
        tareaServicio.guardarTarea(tarea);
        mostrarMensaje("Informacion", "Se ha actualizado el cliente correctamente");
        limpiarFormulario();
        mostraTareas();
    }

    public void cargarTareaFormulario(){
        var tarea = tareaTabla.getSelectionModel().getSelectedItem();
        if (tarea!= null){
            idTareaInterno = tarea.getId();
            nombreTareaTexto.setText(tarea.getNombreTarea());
            responsableTexto.setText(tarea.getResponsable());
            estatusTexto.setText(tarea.getEstatus());
        }
    }

    private void limpiarFormulario(){
        idTareaInterno = null;
        nombreTareaTexto.clear();
        responsableTexto.clear();
        estatusTexto.clear();
    }

    private void recolectarDatosFormulario(Tarea tarea){
        if (idTareaInterno != null){
            tarea.setId(idTareaInterno);
        }
        tarea.setNombreTarea(nombreTareaTexto.getText());
        tarea.setResponsable(responsableTexto.getText());
        tarea.setEstatus(estatusTexto.getText());

    }

//Forma de mostrar alertas o mensajes en javaFX
    private void mostrarMensaje(String titulo, String mensaje){
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }



}
