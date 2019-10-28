package GUI.controllers;

import implementation.*;

import implementation.Process;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import structures.Stack;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Simulator implements Initializable {

    // FXML

    @FXML
    private Label commandLine_lb;

    @FXML
    private TextArea history_ta;


    // Memory Tab

    @FXML
    private TableView<Frame> memory_tv;

    @FXML
    private TableColumn<Frame, Integer> u_tc, frame_tc;

    @FXML
    private TableColumn<Frame, String> value_tc;

    @FXML
    private ListView<Integer> lru_lv;

    // Processes Tab

        // Page Table

    @FXML
    private TableView<TableLine> pageTable_tv;

    @FXML
    private TableColumn<TableLine, Integer> page_tc, presenceBit_tc, modBit_tc, frameLocation_tc;

        // Processes Table

    @FXML
    private TableView<Process> processes_tv;

    @FXML
    private TableColumn<Process, Integer> id_tc, qtd_pag_tc;

    @FXML
    private TableColumn<Process, String> status_tc;

    // Others

    private static String[] commands;
    private static int commandIndex;

    // Statics

    /*
    Atributos FXML não podem ser estáticos, por isso, caso haja a necessidade de referenciá-los como tal,
    é necessário criar redundâncias como as abaixo.
     */

    private static Label label_st;
    private static TextArea textArea_st;
    private static TableView<Frame> memory_tv_st;
    private static TableView<TableLine> pageTable_tv_st;
    private static TableView<Process> processes_tv_st;
    private static ListView<Integer> lru_lv_st;

    public static StringBuilder stringBuilder;

    // Methods

    public void initialize(URL url, ResourceBundle resourceBundle){

        label_st = commandLine_lb;
        textArea_st = history_ta;

        memory_tv_st = memory_tv;
        u_tc.setCellValueFactory(new PropertyValueFactory<>("utilizationBit"));
        frame_tc.setCellValueFactory(new PropertyValueFactory<>("location"));
        value_tc.setCellValueFactory(new PropertyValueFactory<>("content"));

        processes_tv_st = processes_tv;
        processes_tv_st.getSelectionModel().selectedItemProperty().addListener((e, oldValue, newValue) -> showPageTable());
        id_tc.setCellValueFactory(new PropertyValueFactory<>("id"));
        qtd_pag_tc.setCellValueFactory(new PropertyValueFactory<>("pagesQt"));
        status_tc.setCellValueFactory(new PropertyValueFactory<>("status"));

        pageTable_tv_st = pageTable_tv;
        page_tc.setCellValueFactory(new PropertyValueFactory<>("page"));
        presenceBit_tc.setCellValueFactory(new PropertyValueFactory<>("presenceBit"));
        modBit_tc.setCellValueFactory(new PropertyValueFactory<>("modificationBit"));
        frameLocation_tc.setCellValueFactory(new PropertyValueFactory<>("memoryLocation"));

        lru_lv_st = lru_lv;

        stringBuilder = new StringBuilder();
    }

    private void showPageTable(){
        Process process = processes_tv.getSelectionModel().getSelectedItem();
        if(process != null){
            pageTable_tv.getItems().clear();
            pageTable_tv.getItems().addAll(process.getPageTable().getTableLines());
        }
    }

    // Memory

    public static void addFrame(Frame frame){
        memory_tv_st.getItems().add(frame);
    }

    public static void refreshStack(Stack stack){
        for(Integer integer : stack.getStack())
            lru_lv_st.getItems().add(integer);
    }

    // Processes

    public static void setProcessesTable(List<Process> processes){
        processes_tv_st.setItems(FXCollections.observableList(processes));
    }

    public static void setPageTable(PageTable pageTable){
    }

    // Buttons

    public void continue_bt(){
    	try {
            label_st.setText(commands[commandIndex]);
            Main.executeCommandLine(commands[commandIndex]);
            
            textArea_st.setText(stringBuilder.toString());
            commandIndex++;
		} catch (Exception e) {
			// TODO: handle exception
		}

    }

    public void terminate_bt(){
        textArea_st.clear();
        memory_tv.getItems().clear();
        processes_tv.getItems().clear();
        pageTable_tv.getItems().clear();
        lru_lv.getItems().clear();
        Main.showMenu();
    }

    public static void start(String coms){
        commands = coms.split("\n");
        commandIndex = 0;
        label_st.setText(commands[commandIndex]);
        Main.executeCommandLine(commands[commandIndex]);
        textArea_st.setText(stringBuilder.toString());
        commandIndex++;
    }

}