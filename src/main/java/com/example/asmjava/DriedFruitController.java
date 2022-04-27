package com.example.asmjava;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DriedFruitController implements Initializable {

    public static File file1 = new File("DriedFruit.txt");
    public static File file2 = new File("Nut.txt");


    @FXML
    private TextField tfID;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfWeight;
    @FXML
    private TextField tfPricePerKg;
    @FXML
    private TextField tfTemperature;
    @FXML
    private TextField tfSearch;
    @FXML
    private TableView<DriedFruit> tvDriedFruit;
    @FXML
    private TableColumn<DriedFruit, Integer> colID;
    @FXML
    private TableColumn<DriedFruit, String> colName;
    @FXML
    private TableColumn<DriedFruit, Float> colWeight;
    @FXML
    private TableColumn<DriedFruit, Float> colPricePerKg;
    @FXML
    private TableColumn<DriedFruit, Float> colTemperature;
    @FXML
    private ObservableList<DriedFruit> driedFruitsList;

    public void changeMainScene(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Main.fxml"));
        Scene mainScene = new Scene(fxmlLoader.load(), 900, 450);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(mainScene);
        window.show();
    }

    @FXML
    public void addDriedFruit(ActionEvent event) {
        List<DriedFruit> driedFruits = Function.readDriedFruitListFromFile(file1);
        List<Nut> nuts = Function.readNutListFromFile(file2);
        try {
            if (tfID.getText().trim().isEmpty() || tfName.getText().trim().isEmpty() || tfWeight.getText().trim().isEmpty() || tfPricePerKg.getText().trim().isEmpty() || tfTemperature.getText().trim().isEmpty()) {
                Function.addField();
            } else if (Function.IsDriedFruitExisted(driedFruits, Integer.parseInt(tfID.getText().trim())) ) {
                Function.productAlreadyExist();
            } else if (Integer.parseInt(tfID.getText().trim()) < 0) {
                Function.enterAgainId();
            } else if (Float.parseFloat(tfWeight.getText().trim()) < 0 || Float.parseFloat(tfWeight.getText().trim()) > 100) {
                Function.enterAgainWeight();
            } else if (Float.parseFloat(tfPricePerKg.getText().trim()) < 5 || Float.parseFloat(tfPricePerKg.getText().trim()) > 100) {
                Function.enterAgainPricePerKg();
            } else if (Float.parseFloat(tfTemperature.getText().trim()) < (-20) || Float.parseFloat(tfTemperature.getText().trim()) > 20) {
                Function.enterAgainTemperature();
            } else {
                DriedFruit driedFruit = new DriedFruit(Integer.parseInt(tfID.getText()),
                        tfName.getText(),
                        Float.parseFloat(tfWeight.getText()),
                        Float.parseFloat(tfPricePerKg.getText()),
                        Float.parseFloat(tfTemperature.getText()));
                driedFruits.add(driedFruit);
                Function.addDriedFruitToFile(driedFruits, file1);
                Function.addComplete();
                tvDriedFruit.getItems().add(driedFruit);
                tfID.setText("");
                tfName.setText("");
                tfWeight.setText("");
                tfPricePerKg.setText("");
                tfTemperature.setText("");
            }
        } catch (NumberFormatException e) {
            Alert addFail = new Alert(Alert.AlertType.ERROR);
            addFail.setTitle("Alert!");
            addFail.setContentText("Please fill with the number!!!");
            addFail.show();
        }
    }

    @FXML
    public void chooseItemFromTable(MouseEvent event) {
        DriedFruit driedFruit = tvDriedFruit.getSelectionModel().getSelectedItem();
        if (driedFruit != null) {
            tfID.setDisable(true);
            tfID.setText(colID.getCellData(driedFruit).toString());
            tfName.setText(colName.getCellData(driedFruit));
            tfWeight.setText(colWeight.getCellData(driedFruit).toString());
            tfPricePerKg.setText(colPricePerKg.getCellData(driedFruit).toString());
            tfTemperature.setText(colTemperature.getCellData(driedFruit).toString());
        }
    }

    @FXML
    public void updateDriedFruit(ActionEvent event) {

        try {
            driedFruitsList = tvDriedFruit.getItems();
            int selectedItem = Integer.parseInt(tfID.getText());
            for (DriedFruit driedFruit : driedFruitsList) {
                if (driedFruit.getId() == selectedItem) {
                    try{
                        if (Float.parseFloat(tfWeight.getText().trim()) < 0 || Float.parseFloat(tfWeight.getText().trim()) > 100) {
                            Function.enterAgainWeight();
                        } else if (Float.parseFloat(tfPricePerKg.getText().trim()) < 5 || Float.parseFloat(tfPricePerKg.getText().trim()) > 100) {
                            Function.enterAgainPricePerKg();
                        } else if (Float.parseFloat(tfTemperature.getText().trim()) < (-20) || Float.parseFloat(tfTemperature.getText().trim()) > 20) {
                            Function.enterAgainTemperature();
                        } else {
                            driedFruit.setName(tfName.getText());
                            driedFruit.setWeight(Float.parseFloat(tfWeight.getText()));
                            driedFruit.setPricePerKg(Float.parseFloat(tfPricePerKg.getText()));
                            driedFruit.setDriedFruitTemp(Float.parseFloat(tfTemperature.getText()));
                            tvDriedFruit.setItems(driedFruitsList);
                            tvDriedFruit.refresh();
                            colID.getCellValueFactory();
                            colName.getCellValueFactory();
                            colWeight.getCellValueFactory();
                            colPricePerKg.getCellValueFactory();
                            colTemperature.getCellValueFactory();
                            List<DriedFruit> driedFruits = tvDriedFruit.getItems();
                            Function.SaveDriedFruitToFile(driedFruitsList, file1);
                            Function.updateComplete();
                        }
                    } catch (NumberFormatException e) {
                        Alert addFail = new Alert(Alert.AlertType.ERROR);
                        addFail.setTitle("Alert!");
                        addFail.setContentText("Please fill with the number!!!");
                        addFail.show();
                    }
                }

            }
        } catch (NumberFormatException e) {
            Alert addFail = new Alert(Alert.AlertType.ERROR);
            addFail.setTitle("Alert!");
            addFail.setContentText("Please choose the person first!!!");
            addFail.show();
        }
    }

    @FXML
    public void deleteDriedFruit(ActionEvent event) {
        try
        {
            int selectID = tvDriedFruit.getSelectionModel().getSelectedIndex();
            tvDriedFruit.getItems().remove(selectID);
            colID.getCellValueFactory();
            colName.getCellValueFactory();
            colWeight.getCellValueFactory();
            colPricePerKg.getCellValueFactory();
            colTemperature.getCellValueFactory();
            List<DriedFruit> driedFruits = tvDriedFruit.getItems();
            Function.SaveDriedFruitToFile(driedFruits, file1);
            Function.deleteComplete();
            tfID.setText("");
            tfName.setText("");
            tfWeight.setText("");
            tfPricePerKg.setText("");
            tfTemperature.setText("");
            tfID.setDisable(false);
        } catch (Exception e) {
            Alert addFail = new Alert(Alert.AlertType.ERROR);
            addFail.setTitle("Alert!");
            addFail.setContentText("Please choose the person first!!!");
            addFail.show();
        }
    }

    @FXML
    public void searchDriedFruit(ActionEvent event) {
        driedFruitsList = FXCollections.observableArrayList(Function.readDriedFruitListFromFile(file1));
        List<DriedFruit> driedFruits = new ArrayList<>();
        for (DriedFruit e : driedFruitsList) {
            if (e.getName().matches("(?i).*\\b" + tfSearch.getText().trim() + "\\b.*"))
                driedFruits.add(e);
        }
        driedFruitsList = FXCollections.observableArrayList(driedFruits);
        tvDriedFruit.setItems(driedFruitsList);
    }

    @FXML
    public void refreshTable(ActionEvent event) {
        driedFruitsList = FXCollections.observableArrayList(Function.readDriedFruitListFromFile(file1));
        tvDriedFruit.setItems(driedFruitsList);
    }

    @FXML
    public void refreshForm() {
        tfID.setDisable(false);
        tfID.setText("");
        tfName.setText("");
        tfWeight.setText("");
        tfPricePerKg.setText("");
        tfTemperature.setText("");
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        driedFruitsList = FXCollections.observableArrayList(Function.readDriedFruitListFromFile(file1));
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colWeight.setCellValueFactory(new PropertyValueFactory<>("weight"));
        colPricePerKg.setCellValueFactory(new PropertyValueFactory<>("pricePerKg"));
        colTemperature.setCellValueFactory(new PropertyValueFactory<>("driedFruitTemp"));
        tvDriedFruit.setItems(driedFruitsList);
    }
}
