package com.example.asmjava;

import javafx.scene.control.Alert;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Function {

    public static void addDriedFruitToFile(List<DriedFruit> driedFruits, File file) {
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            for (DriedFruit o : driedFruits) {
                bw.write(o.toString());
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<DriedFruit> readDriedFruitListFromFile(File file) {
        List<DriedFruit> driedFruits = new ArrayList<>();
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while (true) {
                line = br.readLine();
                if (line == null)
                    break;
                String eachDriedFruit[] = line.split(", ");
                int id = Integer.parseInt(eachDriedFruit[0]);
                String name = eachDriedFruit[1];
                float weight = Float.parseFloat(eachDriedFruit[2]);
                float pricePerKg = Float.parseFloat(eachDriedFruit[3]);
                float driedFruitTemp = Float.parseFloat(eachDriedFruit[4]);
                driedFruits.add(new DriedFruit(id, name, weight, pricePerKg, driedFruitTemp));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return driedFruits;
    }

    public static void SaveDriedFruitToFile(List<DriedFruit> driedFruits, File file) {
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            for (DriedFruit o : driedFruits) {
                bw.write(o.toString());
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addNutToFile(List<Nut> nuts, File file) {
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Nut o : nuts) {
                bw.write(o.toString());
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Nut> readNutListFromFile(File file) {
        List<Nut> nuts = new ArrayList<>();
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while (true) {
                line = br.readLine();
                if (line == null)
                    break;
                String eachNut[] = line.split(", ");
                int id = Integer.parseInt(eachNut[0]);
                String name = eachNut[1];
                float weight = Float.parseFloat(eachNut[2]);
                float pricePerKG = Float.parseFloat(eachNut[3]);
                float nutTemp = Float.parseFloat(eachNut[4]);
                nuts.add(new Nut(id, name, weight, pricePerKG, nutTemp));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nuts;
    }

    public static void SaveNutToFile(List<Nut> nuts, File file) {
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Nut o : nuts) {
                bw.write(o.toString());
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean IsDriedFruitExisted(List<DriedFruit> driedFruits, Integer IdToCheck) {
        for (DriedFruit o : driedFruits) {
            if (IdToCheck == o.getId())
                return true;
        }
        return false;
    }

    public static boolean IsNutExisted(List<Nut> nuts, Integer IdToCheck) {
        for (Nut o : nuts) {
            if (IdToCheck == o.getId())
                return true;
        }
        return false;
    }

    public static void addComplete()
    {
        Alert addComplete = new Alert(Alert.AlertType.INFORMATION);
        addComplete.setTitle("Alert!");
        addComplete.setContentText("Add successfully");
        addComplete.show();
    }
    public static void addField()
    {
        Alert addFail = new Alert(Alert.AlertType.ERROR);
        addFail.setTitle("Alert!");
        addFail.setContentText("Please complete all fields!!!");
        addFail.show();
    }

    public static void productAlreadyExist()
    {
        Alert addFail = new Alert(Alert.AlertType.ERROR);
        addFail.setTitle("Alert!");
        addFail.setContentText("ID Already exist, please enter another ID!!!");
        addFail.show();
    }

    public static void enterAgainId()
    {
        Alert addFail = new Alert(Alert.AlertType.ERROR);
        addFail.setTitle("Alert!");
        addFail.setContentText("IDs are not allowed to be negative numbers, please enter another ID!!!");
        addFail.show();
    }

    public static void enterAgainWeight()
    {
        Alert addFail = new Alert(Alert.AlertType.ERROR);
        addFail.setTitle("Alert!");
        addFail.setContentText("Weight not allowed to be negative number and older than 100, please enter another Weight!!!");
        addFail.show();
    }

    public static void enterAgainPricePerKg()
    {
        Alert addFail = new Alert(Alert.AlertType.ERROR);
        addFail.setTitle("Alert!");
        addFail.setContentText("Price per Kg not less than 5$ and older than 100$, please enter another Price!!!");
        addFail.show();
    }

    public static void enterAgainTemperature()
    {
        Alert addFail = new Alert(Alert.AlertType.ERROR);
        addFail.setTitle("Alert!");
        addFail.setContentText("Temperature are not less than -20*C and older than 20*C, please enter another Temperature!!!");
        addFail.show();
    }

    public static void updateComplete()
    {
        Alert updateComplete = new Alert(Alert.AlertType.INFORMATION);
        updateComplete.setTitle("Alert!");
        updateComplete.setContentText("Update successfully");
        updateComplete.show();
    }
    public static void updateFail()
    {
        Alert updateFail = new Alert(Alert.AlertType.ERROR);
        updateFail.setTitle("Alert!");
        updateFail.setContentText("Update Fail!!!");
        updateFail.show();
    }
    public static void deleteComplete()
    {
        Alert deleteComplete = new Alert(Alert.AlertType.INFORMATION);
        deleteComplete.setTitle("Alert!");
        deleteComplete.setContentText("Delete successfully");
        deleteComplete.show();
    }
    public static void deleteFail()
    {
        Alert deleteFail = new Alert(Alert.AlertType.ERROR);
        deleteFail.setTitle("Alert!");
        deleteFail.setContentText("Delete Fail!!!");
        deleteFail.show();
    }
}
