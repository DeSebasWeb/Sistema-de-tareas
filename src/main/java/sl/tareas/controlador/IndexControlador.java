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

    private final ObservableList<Tarea> tareaList = FXCollections.observableArrayList();

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


}
