package es.uji.ei1048.weatherApp.view;

import es.uji.ei1048.weatherApp.main.HelperControllerMain;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.text.Text;


public class OpenAMapViewController {

    private HelperControllerMain helperControllerMain;

    @FXML
    Text error;

    @FXML
    ChoiceBox<String > options;




    public void setHelperControllerMain(HelperControllerMain helperControllerMain){
        this.helperControllerMain = helperControllerMain;
        String[] list = helperControllerMain.getListOfOptions();
        for(String s: list){
            options.getItems().add(s);
        }
    }

    @FXML
    private void handleOpen(){
        error.setText("");

        String option = options.getSelectionModel().getSelectedItem();

        if(option == null){
            error.setText("Please, choose an option");
        } else {
            helperControllerMain.showTheMap(option.replace(" ", ""));
        }

    }


}
