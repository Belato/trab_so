package GUI.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.ResourceBundle;

public class Start implements Initializable{

    /*
    Esta classe tem o controle sobre o arquivo mainMenu.fxml
    A interface Initializable força o método initialize() a ser executado assim
    que o arquivo fxml for carregado.
     */

    // FXML items

    @FXML
    private TextArea commands_ta;

    @FXML
    private TextField memorySize_tf, frameSize_tf, logicalAddressSize_tf, secondaryMemorySize_tf;

    @FXML
    private ChoiceBox<String> replacementPolicy_cb;

    // Others

    private FileChooser fileChooser;
    private static TextArea textArea_st;

    // Methods

    public void initialize(URL url, ResourceBundle resourceBundle){

        fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extensionFilter);

        replacementPolicy_cb.getItems().addAll("Clock", "LRU");
        replacementPolicy_cb.setValue("LRU");

        textArea_st = commands_ta;
        textArea_st.setText("P1 C 1 MB\n" +
                "P2 C 2 MB\n" +
                "P3 C 1 MB\n" +
                "P1 T\n" +
                "P3 T\n" +
                "P4 C 2 MB\n" +
                "P4 T\n" +
                "P2 T");
    }

    public static String getCommands(){ return textArea_st.getText();}

    @FXML
    void openFile_bt(){

        // File selection

        File file = fileChooser.showOpenDialog(new Stage());

        // Text extracting

        try{
            StringBuilder stringBuilder = new StringBuilder();
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine();
            while(true){
                if(line.charAt(0) == '#')
                    line = bufferedReader.readLine();
                if(line != null)
                    stringBuilder.append(line + "\n");
                else break;
                line = bufferedReader.readLine();
            }

            bufferedReader.close();
            fileReader.close();

            commands_ta.setText(stringBuilder.toString());
        }
        catch (Exception exc){
            exc.printStackTrace();
        }

    }

    @FXML
    void start_bt(){
        int policy;
        if(replacementPolicy_cb.getValue().equals("Clock"))
            policy = 0;
        else policy = 1;
        implementation.Main.initializeOS(
                Integer.parseInt(memorySize_tf.getText()),
                Integer.parseInt(secondaryMemorySize_tf.getText()),
                Integer.parseInt(frameSize_tf.getText()),
                Integer.parseInt(logicalAddressSize_tf.getText()),
                policy
        );
        implementation.Main.showSimulator();
    }

}




