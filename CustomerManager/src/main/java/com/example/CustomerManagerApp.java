package com.example;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CustomerManagerApp extends Application {

    ObservableList<Customer> customers = FXCollections.observableArrayList();

    TextField nameField = new TextField();
    ComboBox<String> provinceBox = new ComboBox<>();
    TableView<Customer> table = new TableView<>();

    @Override
    public void start(Stage stage) {

        nameField.setPromptText("Customer name");

        provinceBox.getItems().addAll(
                "Central", "Copperbelt", "Eastern", "Luapula",
                "Lusaka", "Muchinga", "Northern", "North-Western",
                "Southern", "Western"
        );

        TableColumn<Customer, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Customer, String> provinceCol = new TableColumn<>("Province");
        provinceCol.setCellValueFactory(new PropertyValueFactory<>("province"));

        table.getColumns().addAll(nameCol, provinceCol);
        table.setItems(customers);

        Button addButton = new Button("Add Customer");
        Button deleteButton = new Button("Delete");

        addButton.setOnAction(e -> addCustomer());
        deleteButton.setOnAction(e -> deleteCustomer());

        nameField.setOnAction(e -> addCustomer());

        VBox root = new VBox(10,
                new Label("Customer Manager"),
                nameField,
                provinceBox,
                addButton,
                deleteButton,
                table
        );

        Scene scene = new Scene(root, 500, 500);

        scene.setOnKeyPressed(e -> {
            if (e.getCode() == javafx.scene.input.KeyCode.DELETE)
                deleteCustomer();
        });

        stage.setTitle("Customer Manager");
        stage.setScene(scene);
        stage.show();
    }

    void addCustomer() {
        if (nameField.getText().isBlank()) {
            new Alert(Alert.AlertType.ERROR, "Enter customer name.").showAndWait();
            return;
        }

        if (provinceBox.getValue() == null) {
            new Alert(Alert.AlertType.ERROR, "Select a province.").showAndWait();
            return;
        }

        customers.add(new Customer(
                nameField.getText(),
                provinceBox.getValue()
        ));

        nameField.clear();
        provinceBox.setValue(null);
    }

    void deleteCustomer() {
        Customer selected = table.getSelectionModel().getSelectedItem();

        if (selected == null)
            return;

        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Delete " + selected.getName() + "?"
        );

        if (alert.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK)
            customers.remove(selected);
    }

    public static void main(String[] args) {
        launch();
    }
}
