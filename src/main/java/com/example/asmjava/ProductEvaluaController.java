package com.example.asmjava;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ProductEvaluaController implements Initializable {

    FileChooser fileChooser = new FileChooser();
    @FXML
    private TextArea textArea;
    @FXML
    private Label time;
    @FXML

    void getFile(MouseEvent event){
        File file = fileChooser.showOpenDialog(new Stage());
        try {
            Date date = new Date(new File("C:\\Users\\ngoct\\IdeaProjects\\AsmJava").lastModified());
            time.setText(date.toString());
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine())
            {
                textArea.appendText(scanner.nextLine() + "\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void saveFile(MouseEvent event)
    {
        File file = fileChooser.showSaveDialog(new Stage());
        if (file != null)
        {
            save(file, textArea.getText());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fileChooser.setInitialDirectory(new File("C:\\Users\\ngoct\\IdeaProjects\\AsmJava"));
    }

    public void save(File file, String context)
    {
        try {
            PrintWriter printWriter = new PrintWriter(file);
            printWriter.write(context);
            printWriter.close();
            textArea.setText("");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void changeMainScene(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Main.fxml"));
        Scene mainScene = new Scene(fxmlLoader.load(), 900, 450);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainScene);
        window.show();
    }

}
