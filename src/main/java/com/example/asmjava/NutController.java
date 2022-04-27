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

public class NutController implements Initializable {

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
    private TableView<Nut> tvNut;
    @FXML
    private TableColumn<Nut, Integer> colID;
    @FXML
    private TableColumn<Nut, String> colName;
    @FXML
    private TableColumn<Nut, Float> colWeight;
    @FXML
    private TableColumn<Nut, Float> colPricePerKg;
    @FXML
    private TableColumn<Nut, Float> colTemperature;
    @FXML
    private ObservableList<Nut> NutsList;

    public void changeMainScene(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Main.fxml"));
        Scene mainScene = new Scene(fxmlLoader.load(), 900, 450);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(mainScene);
        window.show();
    }

    @FXML
    public void addNut(ActionEvent event) {
        List<DriedFruit> driedFruits = Function.readDriedFruitListFromFile(file1);
        List<Nut> nuts = Function.readNutListFromFile(file2);
        try {
            if (tfID.getText().trim().isEmpty() || tfName.getText().trim().isEmpty() || tfWeight.getText().trim().isEmpty() || tfPricePerKg.getText().trim().isEmpty() || tfTemperature.getText().trim().isEmpty()) {
                Function.addField();
            } else if (Function.IsNutExisted(nuts, Integer.parseInt(tfID.getText().trim()))) {
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
                Nut nut = new Nut(Integer.parseInt(tfID.getText()),
                        tfName.getText(),
                        Float.parseFloat(tfWeight.getText()),
                        Float.parseFloat(tfPricePerKg.getText()),
                        Float.parseFloat(tfTemperature.getText()));
                nuts.add(nut);
                Function.addNutToFile(nuts, file2);
                Function.addComplete();
                tvNut.getItems().add(nut);
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
        Nut nut = tvNut.getSelectionModel().getSelectedItem();
        if (nut != null) {
            tfID.setDisable(true);
            tfID.setText(colID.getCellData(nut).toString());
            tfName.setText(colName.getCellData(nut));
            tfWeight.setText(colWeight.getCellData(nut).toString());
            tfPricePerKg.setText(colPricePerKg.getCellData(nut).toString());
            tfTemperature.setText(colTemperature.getCellData(nut).toString());
        }
    }

    @FXML
    public void updateNut(ActionEvent event) {

        try {
            NutsList = tvNut.getItems();
            int selectedItem = Integer.parseInt(tfID.getText());
            for (Nut nut : NutsList) {
                if (nut.getId() == selectedItem) {
                    try{
                        if (Float.parseFloat(tfWeight.getText().trim()) < 0 || Float.parseFloat(tfWeight.getText().trim()) > 100) {
                            Function.enterAgainWeight();
                        } else if (Float.parseFloat(tfPricePerKg.getText().trim()) < 5 || Float.parseFloat(tfPricePerKg.getText().trim()) > 100) {
                            Function.enterAgainPricePerKg();
                        } else if (Float.parseFloat(tfTemperature.getText().trim()) < (-20) || Float.parseFloat(tfTemperature.getText().trim()) > 20) {
                            Function.enterAgainTemperature();
                        } else {
                            nut.setName(tfName.getText());
                            nut.setWeight(Float.parseFloat(tfWeight.getText()));
                            nut.setPricePerKg(Float.parseFloat(tfPricePerKg.getText()));
                            nut.setNutTemp(Float.parseFloat(tfTemperature.getText()));
                            tvNut.setItems(NutsList);
                            tvNut.refresh();
                            colID.getCellValueFactory();
                            colName.getCellValueFactory();
                            colWeight.getCellValueFactory();
                            colPricePerKg.getCellValueFactory();
                            colTemperature.getCellValueFactory();
                            List<Nut> nuts = tvNut.getItems();
                            Function.SaveNutToFile(NutsList, file2);
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
    public void deleteNut(ActionEvent event) {
        try
        {
            int selectID = tvNut.getSelectionModel().getSelectedIndex();
            tvNut.getItems().remove(selectID);
            colID.getCellValueFactory();
            colName.getCellValueFactory();
            colWeight.getCellValueFactory();
            colPricePerKg.getCellValueFactory();
            colTemperature.getCellValueFactory();
            List<Nut> nuts = tvNut.getItems();
            Function.SaveNutToFile(nuts, file2);
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
    public void searchNut(ActionEvent event) {
        NutsList = FXCollections.observableArrayList(Function.readNutListFromFile(file2));
        List<Nut> nuts = new ArrayList<>();
        for (Nut e : NutsList) {
            if (e.getName().matches("(?i).*\\b" + tfSearch.getText().trim() + "\\b.*"))
                nuts.add(e);
        }
        NutsList = FXCollections.observableArrayList(nuts);
        tvNut.setItems(NutsList);
    }

    @FXML
    public void refreshTable(ActionEvent event) {
        NutsList = FXCollections.observableArrayList(Function.readNutListFromFile(file2));
        tvNut.setItems(NutsList);
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
        NutsList = FXCollections.observableArrayList(Function.readNutListFromFile(file2));
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colWeight.setCellValueFactory(new PropertyValueFactory<>("weight"));
        colPricePerKg.setCellValueFactory(new PropertyValueFactory<>("pricePerKg"));
        colTemperature.setCellValueFactory(new PropertyValueFactory<>("nutTemp"));
        tvNut.setItems(NutsList);
    }
}
