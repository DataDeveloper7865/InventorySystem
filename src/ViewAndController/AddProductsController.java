
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
import static Model.Inventory.getAllParts;
import javafx.scene.control.Alert;

public class AddProductsController implements Initializable {

    private final ObservableList<Part> currentParts = FXCollections.observableArrayList();
    
    private int productID;
    private String messageToUser = new String();

    @FXML private TextField IDFieldForAddProducts;
    @FXML private TextField NameFieldForAddProduct;
    @FXML private TextField PriceTextFieldForAddProducts;
    @FXML private TextField InvTextFieldForAddProducts;
    @FXML private TextField MinimumTextFieldForAddProducts;
    @FXML private TextField MaximumTextFieldForAddProducts;
    @FXML private TextField SearchFieldForAddProductAddPartSection;
    @FXML private TableView<Part> TableViewForAddProductsAddParts;
    @FXML private TableColumn<Part, Integer> PartIDColumnAddProduct;
    @FXML private TableColumn<Part, String> PartNameTableViewColumnAddProduct;
    @FXML private TableColumn<Part, Integer> InventoryLevelColumnForAddProduct;
    @FXML private TableColumn<Part, Double> PriceColumnForAddProduct;
    @FXML private TableView<Part> TableViewForDeletingPartsFromProduct;
    @FXML private TableColumn<Part, Integer> PartIDColumnForDeleting;
    @FXML private TableColumn<Part, String> PartNameColumnForDeleting;
    @FXML private TableColumn<Part, Integer> InventoryColumnForDeleting;
    @FXML private TableColumn<Part, Double> PriceColumnForDeleting;

    public AddProductsController() {
    }

    //the search button is clicked
    @FXML void SearchButtonforAddPartOnAddProductsWasPushed(ActionEvent event) {

        String searchPart = SearchFieldForAddProductAddPartSection.getText();
        int partIdx = Inventory.findPart(searchPart);
        Part temporaryPart = Inventory.getAllParts().get(partIdx);

        ObservableList<Part> temporaryProductList = FXCollections.observableArrayList();
        //add the part to the temporary list
        temporaryProductList.add(temporaryPart);
        //set the table view to the items in the product list
        TableViewForAddProductsAddParts.setItems(temporaryProductList);
        
    }


    //the add part button is pressed to add part to the list of parts assoicated with the product
    @FXML void AddPartToProductButtonWasPushed(ActionEvent event) {
        Part part = TableViewForAddProductsAddParts.getSelectionModel().getSelectedItem();
        currentParts.add(part);
        setCurrentPartsIntoTableView();
    }

    //the selected part is deleted from the list of associated parts
    @FXML void DeleteButtonForProductsWasPushed(ActionEvent event) {

        Part part = TableViewForDeletingPartsFromProduct.getSelectionModel().getSelectedItem();
        currentParts.remove(part);

    }

    //tje save button is pressed adding all of the associated parts to the product
    @FXML void SaveButtonForProductWasPushed(ActionEvent event) throws IOException {

        String productName = NameFieldForAddProduct.getText();
        String productInventory = InvTextFieldForAddProducts.getText();
        String productPrice = PriceTextFieldForAddProducts.getText();
        String productMinimum = MinimumTextFieldForAddProducts.getText();
        String productMaximum = MaximumTextFieldForAddProducts.getText();
        
        try {
            messageToUser = Product.exceptionControlSet2(currentParts, Double.parseDouble(productPrice), productMinimum, productMaximum);
            
            if (messageToUser.length() > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText(messageToUser);
                alert.showAndWait();
                messageToUser = "";
            } else {

                Product productToAddToInventory = new Product();
                productToAddToInventory.setProductID(productID);
                productToAddToInventory.setNameProduct(productName);
                productToAddToInventory.setPriceProduct(Double.parseDouble(productPrice));
                productToAddToInventory.setInStockProduct(Integer.parseInt(productInventory));
                productToAddToInventory.setMinProduct(Integer.parseInt(productMinimum));
                productToAddToInventory.setMaxProduct(Integer.parseInt(productMaximum));
                productToAddToInventory.addAssociatedParts(currentParts);
                Inventory.addProduct(productToAddToInventory);

                //Go back to main Screen
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
       

    
    //the cancel button is clicekd returing to the main menu
    @FXML void AddProductsCancelWasClicked(ActionEvent event) throws IOException {
        Parent partsCancel = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene scene = new Scene(partsCancel);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    } 


    //Initialize the screen
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PartIDColumnAddProduct.setCellValueFactory(cellData -> cellData.getValue().getPartIDIntegerProperty().asObject());
        PartNameTableViewColumnAddProduct.setCellValueFactory(cellData -> cellData.getValue().getPartNameStringProperty());
        InventoryLevelColumnForAddProduct.setCellValueFactory(cellData -> cellData.getValue().getPartInvIntegerProperty().asObject());
        PriceColumnForAddProduct.setCellValueFactory(cellData -> cellData.getValue().getPartPriceDoubleProperty().asObject());

        PartIDColumnForDeleting.setCellValueFactory(cellData -> cellData.getValue().getPartIDIntegerProperty().asObject());
        PartNameColumnForDeleting.setCellValueFactory(cellData -> cellData.getValue().getPartNameStringProperty());
        InventoryColumnForDeleting.setCellValueFactory(cellData -> cellData.getValue().getPartInvIntegerProperty().asObject());
        PriceColumnForDeleting.setCellValueFactory(cellData -> cellData.getValue().getPartPriceDoubleProperty().asObject());

        TableViewOfPartUpdate();
        setCurrentPartsIntoTableView();

        productID = Inventory.getProductCount();
        IDFieldForAddProducts.setText("Auto Gen:-Disabled");
    }

    //update the table view of the part
    public void TableViewOfPartUpdate() {
        TableViewForAddProductsAddParts.setItems(getAllParts());
    }

    //set the items of the current parts to the delete table view
    public void setCurrentPartsIntoTableView() {
        TableViewForDeletingPartsFromProduct.setItems(currentParts);
    }
}