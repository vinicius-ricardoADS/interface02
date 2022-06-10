package interfaces.controllers;

import interfaces.model.Product;
import interfaces.persistence.SqliteProductDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class FindProductController {


    @FXML
    private TableView<Product> tableProduct;

    @FXML
    private TableColumn<Product, String> cCode;

    @FXML
    private TableColumn<Product, String> cName;

    @FXML
    private TableColumn<Product, Double> cPrice;

    @FXML
    private TableColumn<Product, Integer> cQuantity;

    @FXML
    private TextField txtCode;

    private ObservableList<Product> product;


    @FXML
    public void initialize() {
        bindPropertityesTableColumns();
        bindTableList();
    }

    private void bindPropertityesTableColumns() {
        cCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        cName.setCellValueFactory(new PropertyValueFactory<>("name"));
        cPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        cQuantity.setCellValueFactory(new PropertyValueFactory<>("quanty"));
    }

    private void bindTableList() {
        product = FXCollections.observableArrayList();
        tableProduct.setItems(product);
    }


    public void findProduct(ActionEvent actionEvent) {
        product.clear();
        SqliteProductDAO sqliteProductDAO = new SqliteProductDAO();
        product.addAll(sqliteProductDAO.findOnde(txtCode.getText()));
        txtCode.setText("");
    }
}
