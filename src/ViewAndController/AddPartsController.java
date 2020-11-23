//ADD parts controller


package ViewAndController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Model.Inhouse;
import Model.Outsourced;
import Model.Inventory;
import Model.Part;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class AddPartsController implements Initializable {

    @FXML private Label AddPartLabelDynamic;
    @FXML private TextField IDFieldForAddParts;
    @FXML private TextField NameTextFieldForAddParts;
    @FXML private TextField InvTextFieldForAddParts;
    @FXML private TextField PriceTextFieldForAddParts;
    @FXML private TextField MinFieldForAddParts;
    @FXML private TextField DynamicTextFieldForAddParts;
    @FXML private TextField MaxFieldForAddParts;

    private boolean isPartOutsourced;
    private int partID;
    private String messageToUser = new String();
    
    //if the in-house button is clicked, set the dynamic label to machine ID
    @FXML void InHouseRadioButtonAddPartsWasClicked(ActionEvent event) {
        isPartOutsourced = false;
        AddPartLabelDynamic.setText("Machine ID");
    }

   //if the in-house button is clicked, set the dynamic label to the comapny name
    @FXML void OutSourcedRadioButtonAddPartsWasClicked(ActionEvent event) {
        isPartOutsourced = true;
        AddPartLabelDynamic.setText("Company Name");
    }

    //when the add parts save button is clicked get all the input and store it
    //if outsourced, create outsourced object
    // if incoursed, create insourced object
    @FXML void SaveWasClickedForAddParts(ActionEvent event) throws IOException {
        String partName = NameTextFieldForAddParts.getText();
        String partInv = InvTextFieldForAddParts.getText();
        String partPrice = PriceTextFieldForAddParts.getText();
        String partMin = MinFieldForAddParts.getText();
        String partMax = MaxFieldForAddParts.getText();
        String dynamicPartField = DynamicTextFieldForAddParts.getText();

        try {
            messageToUser = Part.exceptionControlSet1(partMin, partMax);
            if (messageToUser.length() > 0) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setContentText(messageToUser);
                alert.showAndWait();
                messageToUser = "";
            } else {
                if (isPartOutsourced == false) {
                    Inhouse inhousePart = new Inhouse();
                    inhousePart.setPartID(partID);
                    inhousePart.setNamePart(partName);
                    inhousePart.setPricePart(Double.parseDouble(partPrice));
                    inhousePart.setInStockPart(Integer.parseInt(partInv));
                    inhousePart.setMinPart(Integer.parseInt(partMin));
                    inhousePart.setMaxPart(Integer.parseInt(partMax));
                    inhousePart.setMachineID(Integer.parseInt(dynamicPartField));
                    Inventory.addPart(inhousePart);
                } else {
                    Outsourced outsourcedPart = new Outsourced();
                    outsourcedPart.setPartID(partID);
                    outsourcedPart.setNamePart(partName);
                    outsourcedPart.setPricePart(Double.parseDouble(partPrice));
                    outsourcedPart.setInStockPart(Integer.parseInt(partInv));
                    outsourcedPart.setMinPart(Integer.parseInt(partMin));
                    outsourcedPart.setMaxPart(Integer.parseInt(partMax));
                    outsourcedPart.setCompanyName(dynamicPartField);
                    Inventory.addPart(outsourcedPart);
                }

                Parent partsSave = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
                Scene scene = new Scene(partsSave);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
            }       
    } catch (NumberFormatException e) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setContentText("Error");
        alert.setContentText("Do not include any blank fields or invalid content. Try Again");
        alert.showAndWait();
    }
}


    
    //return to the main menu if the add parts cancel button is clicked
    @FXML void CancelWasClickedForAddParts(ActionEvent event) throws IOException {
            Parent partsCancel = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
            Scene scene = new Scene(partsCancel);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        isPartOutsourced = false;
        partID = Inventory.getPartCounter();
        IDFieldForAddParts.setText("Auto Gen: Disabled");
    }
}
