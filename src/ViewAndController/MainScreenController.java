
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
import Main.Main;
import Model.Inventory;
import Model.Part;
import Model.Product;
import static Model.Inventory.removeProduct;
import static Model.Inventory.deletePart;
import static Model.Inventory.getAllParts;
import static Model.Inventory.getAllProducts;


public class MainScreenController implements Initializable {

    @FXML private TableView<Part> TableViewForPartsOnMainScreen;
    @FXML private TableColumn<Part, Integer> PartIDColumnForPartsOnMainScreen;
    @FXML private TableColumn<Part, String> NameColumnForPartsOnMainScreen;
    @FXML private TableColumn<Part, Integer> InventoryColumnForPartsOnMainScreen;
    @FXML private TableColumn<Part, Double> PriceColumnForPartsOnMainScreen;
    @FXML private TableView<Product> TableViewForProductsOnMainScreen;
    @FXML private TableColumn<Product, Integer> IDColumnForProductsOnMainScreen;
    @FXML private TableColumn<Product, String> NameColumnForProductsOnMainScreen;
    @FXML private TableColumn<Product, Integer> InventoryColumnForProductsOnMainScreen;
    @FXML private TableColumn<Product, Double> PriceColumnForProductsOnMainScreen;
    @FXML private TextField SearchFieldForPartsOnMainScreen;
    @FXML private TextField SearchFieldForProductsOnMainScreen;

    private static Part partToModify;
    private static int partModificationIdx;
    private static Product productToModify;
    private static int productModificationIdx;

    public static int getIndexOfPart() {
        return partModificationIdx;
    }

    public static int getIndexOfProduct() {
        return productModificationIdx;
    }
    
    public void setMainApp(Main mainApp) {
        PartTableViewUpdate();
        TableViewUpdate();
    }

    public MainScreenController() {
    }

    @FXML void ExitOnMainScreenWasClicked(ActionEvent event) {
            System.exit(0);
    }

    @FXML void AddPartsButtonWasClicked(ActionEvent event) throws IOException {
        Parent addPartsToInventory = FXMLLoader.load(getClass().getResource("AddParts.fxml"));
        Scene scene = new Scene(addPartsToInventory);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML void AddProductsButtonWasClicked(ActionEvent event) throws IOException {
        Parent addProductsToInventory = FXMLLoader.load(getClass().getResource("AddProducts.fxml"));
        Scene scene = new Scene(addProductsToInventory);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML void ModifyPartsButtonWasClicked(ActionEvent event) throws IOException {
        partToModify = TableViewForPartsOnMainScreen.getSelectionModel().getSelectedItem();
        partModificationIdx = getAllParts().indexOf(partToModify);
        Parent modifyPartsInInventory = FXMLLoader.load(getClass().getResource("ModifyParts.fxml"));
        Scene scene = new Scene(modifyPartsInInventory);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML void ModifyProductsButtonWasClicked(ActionEvent event) throws IOException {
        productToModify = TableViewForProductsOnMainScreen.getSelectionModel().getSelectedItem();
        productModificationIdx = getAllProducts().indexOf(productToModify);
        Parent modifyProductsInInventory = FXMLLoader.load(getClass().getResource("ModifyProducts.fxml"));
        Scene scene = new Scene(modifyProductsInInventory);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML void SearchProductsButton(ActionEvent event)  {
        String SearchProducts = SearchFieldForProductsOnMainScreen.getText();
        int prodIndex = Inventory.lookupProduct(SearchProducts);
        Product tempProd = Inventory.getAllProducts().get(prodIndex);
        ObservableList<Product> tempProdList = FXCollections.observableArrayList();
        tempProdList.add(tempProd);
        TableViewForProductsOnMainScreen.setItems(tempProdList);
    }


    @FXML void DeletedProductsButtonWasClicked(ActionEvent event) throws IOException {
        Product productToBeDeleted = TableViewForProductsOnMainScreen.getSelectionModel().getSelectedItem();
        removeProduct(productToBeDeleted);
        TableViewUpdate();
    }

    @FXML void SearchPartsButtonWasClicked(ActionEvent event) throws IOException {
        String partToBeSearchedFor = SearchFieldForPartsOnMainScreen.getText();
        int partIndex = Inventory.findPart(partToBeSearchedFor);
        Part tempPart = Inventory.getAllParts().get(partIndex);
        ObservableList<Part> tempProdList = FXCollections.observableArrayList();
        tempProdList.add(tempPart);
        TableViewForPartsOnMainScreen.setItems(tempProdList);
    }

    @FXML void DeletePartsButtonWasClicked(ActionEvent event) throws IOException {
        Part partToBeDeleted = TableViewForPartsOnMainScreen.getSelectionModel().getSelectedItem();
        deletePart(partToBeDeleted);
        PartTableViewUpdate();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PartIDColumnForPartsOnMainScreen.setCellValueFactory(cellData -> cellData.getValue().getPartIDIntegerProperty().asObject());
        NameColumnForPartsOnMainScreen.setCellValueFactory(cellData -> cellData.getValue().getPartNameStringProperty());
        InventoryColumnForPartsOnMainScreen.setCellValueFactory(cellData -> cellData.getValue().getPartInvIntegerProperty().asObject());
        PriceColumnForPartsOnMainScreen.setCellValueFactory(cellData -> cellData.getValue().getPartPriceDoubleProperty().asObject());
        IDColumnForProductsOnMainScreen.setCellValueFactory(cellData -> cellData.getValue().getProductIDIntegerProperty().asObject());
        NameColumnForProductsOnMainScreen.setCellValueFactory(cellData -> cellData.getValue().getProductNameStringProperty());
        InventoryColumnForProductsOnMainScreen.setCellValueFactory(cellData -> cellData.getValue().getProductInvIntegerProperty().asObject());
        PriceColumnForProductsOnMainScreen.setCellValueFactory(cellData -> cellData.getValue().getProductPriceDoubleProperty().asObject());
        PartTableViewUpdate();
        TableViewUpdate();
    }

    public void PartTableViewUpdate() {
        TableViewForPartsOnMainScreen.setItems(getAllParts());
    }

    public void TableViewUpdate() {
        TableViewForProductsOnMainScreen.setItems(getAllProducts());
    }
}
