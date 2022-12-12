package com.example.supplychain;

import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Order {

    void placeOrder(String productID) throws SQLException {
        ResultSet res = Main.connection.executeQuery("Select max(orderID) from orders");
        int orderID = 0;
        if(res.next()) orderID = res.getInt("max(orderID)") + 1;

        String query = String.format("Insert Into Orders values(%s,%s,'%s')",orderID,productID,Main.emailId);
        int response = Main.connection.executeUpdate(query);
        if(response > 0) {
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Order");
            ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
            dialog.setContentText("Your order is placed!");
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.showAndWait();
            System.out.println("Order is placed!");
        }
    }
}
