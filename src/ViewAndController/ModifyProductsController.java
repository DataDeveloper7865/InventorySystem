
package ViewAndController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Model.Inventory;
import Model.Part;
import Model.Product;
import static ViewAndController.MainScreenController.getIndexOfProduct;
import static Model.Inventory.getAllParts;
import static Model.Inventory.getAllProducts;
import javafx.scene.control.Alert;


public class ModifyProductsController implements Initializable {

    private ObservableList<Part> observableListOfCurrentParts = FXCollections.observableArrayList();
    private final int productIdx = getIndexOfProduct();
    private int productID;
    private String messageToUser1 = new String();
    private String messageToUser2 = new String();

    @FXML private TextField IDOfProductToModifyTextField;
    @FXML private TextField MinOfProductToModifyTextField;
    @FXML private TextField MaxOfProductToModifyTextField;
    @FXML private TextField InvOfProductToModifyTextField;
    @FXML private TextField NameOfProductToModifyTextField;
    @FXML private TextField priceOfProductToModifyTextField;
    @FXML private TextField searchFieldForModifyProduct;
    
    @FXML private TableView<Part> TableViewForModifyProductAddParts;
    @FXML private TableColumn<Part, Integer> partIDColumnForModifyProductAddPart;
    @FXML private TableColumn<Part, String> partNameForModifyProductAddPart;
    @FXML private TableColumn<Part, Integer> invColumnForModifyProductAddPart;
    @FXML private TableColumn<Part, Double> priceColumnForModifyProduct;
    @FXML private TableView<Part> TableViewForDeleteItemsFromProduct;
    @FXML private TableColumn<Part, Integer> partIDColumnForDeleteItemsFromProduct;
    @FXML private TableColumn<Part, String> partNameForDeleteItemsFromProduct;
    @FXML private TableColumn<Part, Integer> partInvColumnForDeleteItemsFromProduct;
    @FXML private TableColumn<Part, Double> priceColumnForDeleteItemsFromProduct;


    @FXML void SearchPartsButtonWasPushed(ActionEvent event) {
        String partToBeSearchedFor = searchFieldForModifyProduct.getText();
        int partIdx = Inventory.findPart(partToBeSearchedFor);
        Part temporaryPart = Inventory.getAllParts().get(partIdx);
        ObservableList<Part> temporaryPoductObservableList = FXCollections.observableArrayList();
        temporaryPoductObservableList.add(temporaryPart);
        TableViewForModifyProductAddParts.setItems(temporaryPoductObservableList);
        }

    @FXML void AddButtonForModifyProductsWasPushed(ActionEvent event) {
        Part partToBeAddded = TableViewForModifyProductAddParts.getSelectionModel().getSelectedItem();
        observableListOfCurrentParts.add(partToBeAddded);
        updatePartsTableView();
    }
    
    
    @FXML void DeleteButtonForModifyProductsWasPushed(ActionEvent event) {
        Part partToBeDeleted = TableViewForDeleteItemsFromProduct.getSelectionModel().getSelectedItem();  
        observableListOfCurrentParts.remove(partToBeDeleted);
        }


    @FXML void saveButtonForModifyProductsWasPushed(ActionEvent event) throws IOException {
        String productName = NameOfProductToModifyTextField.getText();
        String productInventory = InvOfProductToModifyTextField.getText();
        String productPrice = priceOfProductToModifyTextField.getText();
        String productMinimum = MinOfProductToModifyTextField.getText();
        String productMaximum = MaxOfProductToModifyTextField.getText();
        
        try {
            messageToUser1 = Product.exceptionControlSet2(observableListOfCurrentParts, Double.parseDouble(productPrice), productMinimum, productMaximum);
            
            if (messageToUser1.length() > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText(messageToUser1);
                alert.showAndWait();
                messageToUser1 = "";
            } else {
                Product modifiedProduct = new Product();
                modifiedProduct.setProductID(productID);
                modifiedProduct.setNameProduct(productName);
                modifiedProduct.setPriceProduct(Double.parseDouble(productPrice));
                modifiedProduct.setInStockProduct(Integer.parseInt(productInventory));
                modifiedProduct.setMinProduct(Integer.parseInt(productMinimum));
                modifiedProduct.setMaxProduct(Integer.parseInt(productMaximum));
                modifiedProduct.addAssociatedParts(observableListOfCurrentParts);
                Inventory.updateProduct(productIdx, modifiedProduct);

                Parent productsSave = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
                Scene scene = new Scene(productsSave);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
                }
            } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Error");
            alert.setContentText("Do not include any blank fields or invalid content. Try Again");
            alert.showAndWait();
            }
    }

        


    @FXML void cancelButtonForModifyProductsWasClicked(ActionEvent event) throws IOException {
            Parent partsCancelled = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
            Scene scene = new Scene(partsCancelled);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Product product = getAllProducts().get(productIdx);
        productID = getAllProducts().get(productIdx).getProductID();
        IDOfProductToModifyTextField.setText("Auto Gen: " + productID);
        NameOfProductToModifyTextField.setText(product.getNameProduct());
        InvOfProductToModifyTextField.setText(Integer.toString(product.getInStockProduct()));
        priceOfProductToModifyTextField.setText(Double.toString(product.getPriceProduct()));
        MinOfProductToModifyTextField.setText(Integer.toString(product.getMinProduct()));
        MaxOfProductToModifyTextField.setText(Integer.toString(product.getMaxProduct()));
        observableListOfCurrentParts = product.getProductParts();
        partIDColumnForModifyProductAddPart.setCellValueFactory(cellData -> cellData.getValue().getPartIDIntegerProperty().asObject());
        partNameForModifyProductAddPart.setCellValueFactory(cellData -> cellData.getValue().getPartNameStringProperty());
        invColumnForModifyProductAddPart.setCellValueFactory(cellData -> cellData.getValue().getPartInvIntegerProperty().asObject());
        priceColumnForModifyProduct.setCellValueFactory(cellData -> cellData.getValue().getPartPriceDoubleProperty().asObject());
        partIDColumnForDeleteItemsFromProduct.setCellValueFactory(cellData -> cellData.getValue().getPartIDIntegerProperty().asObject());
        partNameForDeleteItemsFromProduct.setCellValueFactory(cellData -> cellData.getValue().getPartNameStringProperty());
        partInvColumnForDeleteItemsFromProduct.setCellValueFactory(cellData -> cellData.getValue().getPartInvIntegerProperty().asObject());
        priceColumnForDeleteItemsFromProduct.setCellValueFactory(cellData -> cellData.getValue().getPartPriceDoubleProperty().asObject());
        tableViewUpdated();
        updatePartsTableView();
    }

    public void tableViewUpdated() {
        TableViewForModifyProductAddParts.setItems(getAllParts());
    }

    public void updatePartsTableView() {
        TableViewForDeleteItemsFromProduct.setItems(observableListOfCurrentParts);
    }

}
