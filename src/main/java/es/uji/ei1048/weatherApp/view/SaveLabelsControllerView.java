package es.uji.ei1048.weatherApp.view;

import es.uji.ei1048.weatherApp.main.HelperControllerMain;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

import java.awt.*;

public class SaveLabelsControllerView {

    HelperControllerMain helperControllerMain;

    @FXML
    TextField label;

    @FXML
    ChoiceBox<String> savedLabelsOptions;

    @FXML
    Button weatherOfLabel;

    @FXML
    Button createLabel;

    public void setHelperControllerMain(HelperControllerMain helperControllerMain){
        this.helperControllerMain = helperControllerMain;
    }
}
