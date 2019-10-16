package controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import com.jfoenix.controls.JFXTextField;

import dao.ProductDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.Product;

public class ProductController implements Initializable{
	
	private ObservableList<Product> products;
	private int index;

    @FXML
    private AnchorPane frame_product;

    @FXML
    private TableView<Product> tb_product;

    @FXML
    private TableColumn<Product, String> col_id;

    @FXML
    private TableColumn<Product, String> col_name;

    @FXML
    private TableColumn<Product, Integer> col_price;

    @FXML
    private TableColumn<Product, Integer> col_amount;

    @FXML
    private JFXTextField txt_id;

    @FXML
    private JFXTextField txt_name;

    @FXML
    private JFXTextField txt_price;

    @FXML
    private JFXTextField txt_amount;

    @FXML
    private JFXButton btn_add;

    @FXML
    private JFXButton btn_update;

    @FXML
    private JFXButton btn_delete;

    @FXML
    private JFXButton btn_clear;

    @FXML
    void clearData(ActionEvent event) {
    	clearText();
    	enableSaveButton();
    	disableUpdateDeleteButton();
    }

    private void disableUpdateDeleteButton() {
		
		btn_update.setDisable(true);
		btn_delete.setDisable(true);
	}

	private void enableSaveButton() {
		
		btn_add.setDisable(false);
	}

	@FXML
    void deleteProduct(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText("Look, a confirmation dialog");
		alert.setContentText("Are you sure to delete product?");
		
		Optional<ButtonType> answer = alert.showAndWait();
		if(answer.get() == ButtonType.OK) {
			ProductDAO dao = new ProductDAO();
			dao.delete(txt_id.getText().toString().trim());
			
			clearText();
			tb_product.getItems().remove(index);
		}else {
			clearText();
			
		}
    }

    @FXML
    void insertProduct(ActionEvent event) {
    	if(txt_name.getText().equals("") || txt_price.getText().equals("") || txt_amount.getText().equals("")) {
    		Alert a = new Alert(AlertType.WARNING);
    		a.setTitle("Warning Dialog");
    		a.setHeaderText("Look, an Warning Dialog");
    		a.setContentText("Fullfill data Before Press Insert Button!!!");
    		
    		a.show();
    	}else {
    		String id = txt_id.getText().toString().trim();
        	String name = txt_name.getText().toString().trim();
        	int price = Integer.parseInt(txt_price.getText().toString().trim());
        	int amount = Integer.parseInt(txt_amount.getText().toString().trim());
        	
        	Product product = new Product(id, name, price, amount);
        	ProductDAO dao = new ProductDAO();
        	
        	boolean result = dao.insert(product);
        	if(result) {
        		Alert a = new Alert(AlertType.INFORMATION);
        		a.setTitle("Information Dialog");
        		a.setHeaderText("Look, an Information Dialog");
        		
        		a.setContentText("Insert product to database successfully");
        		a.show();
        		
        		tb_product.getItems().add(product);
        	}
        	clearText();
    	}
    	
    	
    	
    }

    private void clearText() {
		
		txt_amount.setText("");
		txt_name.setText("");
		txt_price.setText("");
		
		txt_id.requestFocus();
		
		tb_product.getSelectionModel().clearSelection();
		
		generateProductId();
		
		disableUpdateDeleteButton();
		enableSaveButton();
	}

	@FXML
    void updateProduct(ActionEvent event) {
		String id = txt_id.getText().toString().trim();
    	String name = txt_name.getText().toString().trim();
    	int price = Integer.parseInt(txt_price.getText().toString().trim());
    	int amount = Integer.parseInt(txt_amount.getText().toString().trim());
    	
    	Product product = new Product(id, name, price, amount);
    	ProductDAO dao = new ProductDAO();
    	
    	boolean result = dao.update(product);
    	if(result) {
    		Alert a = new Alert(AlertType.INFORMATION);
    		a.setTitle("Information Dialog");
    		a.setHeaderText("Look, an Information Dialog");
    		
    		a.setContentText("Update product successfully");
    		a.show();
    		
    		tb_product.getItems().set(index, product);
    	}
    	clearText();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		products = FXCollections.observableArrayList();
		initProductTable();
		loadProductToTable();
		
		generateProductId();
		
		
	}

	private void generateProductId() {
		
		ProductDAO dao = new ProductDAO();
		txt_id.setText(dao.getProductId());
	}

	private void loadProductToTable() {
		
		ProductDAO dao = new ProductDAO();
		products = dao.select();
		
		tb_product.setItems(products);
	}

	private void initProductTable() {
		
		col_id.setCellValueFactory(new PropertyValueFactory<Product, String>("id"));
		col_name.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
		col_price.setCellValueFactory(new PropertyValueFactory<Product, Integer>("price"));
		col_amount.setCellValueFactory(new PropertyValueFactory<Product, Integer>("amount"));
		
		tb_product.setOnMouseReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				
				Product product = tb_product.getSelectionModel().getSelectedItem();
				if(product!= null) {
					txt_id.setText(product.getId());
					txt_name.setText(product.getName());
					txt_price.setText(product.getPrice() + "");
					txt_amount.setText(product.getAmount() + "");
					
					index = tb_product.getSelectionModel().getSelectedIndex();
					
					enableUpdateDeleteButton();
					disableSaveButton();
				}
			}
		});
		
	}

	protected void disableSaveButton() {
		
		btn_add.setDisable(true);
	}

	protected void enableUpdateDeleteButton() {
		
		btn_update.setDisable(false);
		btn_delete.setDisable(false);
	}

}
