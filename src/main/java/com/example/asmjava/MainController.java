package com.example.asmjava;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    public void changeDriedFruitScene(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("DriedFruit.fxml"));
        Scene driedFruitScene = new Scene(fxmlLoader.load(), 900, 450);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(driedFruitScene);
        window.show();
    }

    public void changeNutScene(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Nut.fxml"));
        Scene nutScene = new Scene(fxmlLoader.load(), 900, 450);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(nutScene);
        window.show();
    }

    public void changeProductEvaluaScene(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ProductEvaluation.fxml"));
        Scene productEvaluaScene = new Scene(fxmlLoader.load(), 900, 450);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(productEvaluaScene);
        window.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}
}