package interfaces.controllers;


import interfaces.model.Product;
import interfaces.persistence.SqliteProductDAO;
import interfaces.view.FindProductDisplay;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ControllerTableExample {

    @FXML
    private TableView<Product> tablePrices;

    @FXML
    private TableColumn<Product, String> cName;

    @FXML
    private TableColumn<Product, Double> cPrice;

    @FXML
    private TableColumn<Product, Integer> cQuanty;

    @FXML
    private  TextField txtCode;

    @FXML
    private TextField txtProductName;

    @FXML
    private TextField txtProductPrice;

    @FXML
    private TextField txtProductQuantity;

    private ObservableList<Product> products;

    @FXML
    private void initialize() {
        bindPropertityesTableColumns();
        bindTableList();
        loadData();
    }

    public void loadData() {
        products.clear();
        SqliteProductDAO sqliteProductDAO = new SqliteProductDAO();
        products.addAll(sqliteProductDAO.findAll());
    }

    private void bindPropertityesTableColumns() {
        cName.setCellValueFactory(new PropertyValueFactory<>("name"));
        cPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        cQuanty.setCellValueFactory(new PropertyValueFactory<>("quanty"));
    }

    private void bindTableList() {
        products = FXCollections.observableArrayList();
        tablePrices.setItems(products);
    }

    public void clear(ActionEvent actionEvent) {
        SqliteProductDAO sqliteProductDAO = new SqliteProductDAO();
        sqliteProductDAO.deleteAll();
        products.clear();
    }

    public void delete(ActionEvent actionEvent) {
        SqliteProductDAO sqliteProductDAO = new SqliteProductDAO();
        sqliteProductDAO.delete(tablePrices.getSelectionModel().getSelectedItem().getCode());
        products.remove(tablePrices.getSelectionModel().getSelectedItem());
        loadData();
        txtCode.setText("");
        txtProductName.setText("");
        txtProductPrice.setText("");
        txtProductQuantity.setText("");
    }

    public void save(ActionEvent actionEvent) {
        SqliteProductDAO sqliteProductDAO = new SqliteProductDAO();
        sqliteProductDAO.salvar(new Product(txtCode.getText(), txtProductName.getText(),
                Double.parseDouble(txtProductPrice.getText()), Integer.parseInt(txtProductQuantity.getText())));
        List<Product> data = new ArrayList<>();
        final String code = txtCode.getText();
        final String name = txtProductName.getText();
        final double price = Double.parseDouble(txtProductPrice.getText());
        final Integer quantity = Integer.parseInt(txtProductQuantity.getText());
        data.add(new Product(code, name, price, quantity));
        products.addAll(data);
        txtCode.setText("");
        txtProductName.setText("");
        txtProductPrice.setText("");
        txtProductQuantity.setText("");
        loadData();
    }

    public void update(MouseEvent mouseEvent) {
        txtCode.setEditable(false);
        txtCode.setText(tablePrices.getSelectionModel().getSelectedItem().getCode());
        txtProductName.setText(tablePrices.getSelectionModel().getSelectedItem().getName());
        txtProductPrice.setText(String.valueOf(tablePrices.getSelectionModel().getSelectedItem().getPrice()));
        txtProductQuantity.setText(String.valueOf(tablePrices.getSelectionModel().getSelectedItem().getQuanty()));
    }

    public void find(ActionEvent actionEvent) {
        FindProductDisplay findProductDisplay = new FindProductDisplay();
        findProductDisplay.showAndWait();
    }
}
