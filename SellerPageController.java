package com.example.supplychain;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SellerPageController {

    @FXML
    TextField name;

    @FXML
    TextField price;

    @FXML
    TextField email;

    @FXML
    public void add(MouseEvent event) throws IOException, SQLException {
        ResultSet res = Main.connection.executeQuery("Select max(productID) from product");

        int productID = 0;

        if(res.next()) {
            productID = res.getInt("max(productID)") + 1;
        }

        String query = String.format("Insert Into product values(%s,'%s',%s,'%s')",productID,name.getText(), price.getText(),email.getText());

        int response = Main.connection.executeUpdate(query);

        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Product status!");
        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);

        if(response > 0) {

            dialog.setContentText("A new Product is added");

        } else {
            dialog.setContentText("Product is not added");
        }
        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.showAndWait();

    }

    @FXML
    public void back(MouseEvent event) throws IOException {
        AnchorPane loginpage = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));

        Main.root.getChildren().add(loginpage);
    }

}
