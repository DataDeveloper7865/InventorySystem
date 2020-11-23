
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Model.Inhouse;
import Model.Inventory;
import Model.Outsourced;
import Model.Part;
import static ViewAndController.MainScreenController.getIndexOfPart;
import static Model.Inventory.getAllParts;
import javafx.scene.control.Alert;


public class ModifyPartsController implements Initializable {

    private boolean isPartOutsourced;
    int partIdx = getIndexOfPart();
    private int partID;
    private String messageToUserSet1 = new String();

    @FXML private TextField partIDTextFieldForModifyParts;
    @FXML private TextField partNameTextFieldForModifyParts;
    @FXML private TextField partInventoryTextFieldForModifyParts;
    @FXML private TextField partPriceTextFieldForModifyParts;
    @FXML private TextField minimumTextFieldForModifyParts;
    @FXML private TextField DynamicTextFieldForModifyParts;
    @FXML private TextField MaximumTextFieldForModifyParts;
    @FXML private Label DynamicLabelForModifyParts;
    @FXML private RadioButton inHouseRadioButtonForModifyParts;
    @FXML private RadioButton outsourcedRadioButtonForModifyParts;

    @FXML void oustsourcedRadioButtonForModifyParts(ActionEvent event) {
        isPartOutsourced = true;
        DynamicLabelForModifyParts.setText("Company Name");
    }

    @FXML void inHouseRadioButtonForModifyParts(ActionEvent event) {
        isPartOutsourced = false;
        DynamicLabelForModifyParts.setText("Machine ID");
    }

    @FXML void CancelWasClickedOnModifyPartsScreen(ActionEvent event) throws IOException {
            Parent partsCancel = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
            Scene scene = new Scene(partsCancel);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
    }

    @FXML void SaveWasClickedOnModifyPartsScreen(ActionEvent event) throws IOException {
        String partName = partNameTextFieldForModifyParts.getText();
        String partInventory = partInventoryTextFieldForModifyParts.getText();
        String partPrice = partPriceTextFieldForModifyParts.getText();
        String partMin = minimumTextFieldForModifyParts.getText();
        String partMax = MaximumTextFieldForModifyParts.getText();
        String partDynamicLabel = DynamicTextFieldForModifyParts.getText();
        
        try {
            messageToUserSet1 = Part.exceptionControlSet1(partMin, partMax);
            if (messageToUserSet1.length() > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText(messageToUserSet1);
                alert.showAndWait();
                messageToUserSet1 = "";
            } else {
                if (isPartOutsourced == false) {
                    Inhouse inhousePart = new Inhouse();
                    inhousePart.setPartID(partID);
                    inhousePart.setNamePart(partName);
                    inhousePart.setPricePart(Double.parseDouble(partPrice));
                    inhousePart.setInStockPart(Integer.parseInt(partInventory));
                    inhousePart.setMinPart(Integer.parseInt(partMin));
                    inhousePart.setMaxPart(Integer.parseInt(partMax));
                    inhousePart.setMachineID(Integer.parseInt(partDynamicLabel));
                    Inventory.updatePart(partIdx, inhousePart);
                } else {
                    Outsourced outsourcedPart = new Outsourced();
                    outsourcedPart.setPartID(partID);
                    outsourcedPart.setNamePart(partName);
                    outsourcedPart.setPricePart(Double.parseDouble(partPrice));
                    outsourcedPart.setInStockPart(Integer.parseInt(partInventory));
                    outsourcedPart.setMinPart(Integer.parseInt(partMin));
                    outsourcedPart.setMaxPart(Integer.parseInt(partMax));
                    outsourcedPart.setCompanyName(partDynamicLabel);
                    Inventory.updatePart(partIdx, outsourcedPart);
                    }

                Parent modifyProductCancel = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
                Scene scene = new Scene(modifyProductCancel);
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



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Part part = getAllParts().get(partIdx);
        partID = getAllParts().get(partIdx).getPartID();
        partIDTextFieldForModifyParts.setText(Integer.toOctalString(partID));
        partNameTextFieldForModifyParts.setText(part.getNamePart());
        partInventoryTextFieldForModifyParts.setText(Integer.toString(part.getInStockPart()));
        partPriceTextFieldForModifyParts.setText(Double.toString(part.getPricePart()));
        minimumTextFieldForModifyParts.setText(Integer.toString(part.getMinPart()));
        MaximumTextFieldForModifyParts.setText(Integer.toString(part.getMaxPart()));
        if (part instanceof Inhouse) {
            DynamicTextFieldForModifyParts.setText(Integer.toString(((Inhouse) getAllParts().get(partIdx)).getMachineID()));
            DynamicLabelForModifyParts.setText("Machine ID");
            inHouseRadioButtonForModifyParts.setSelected(true);

        } else {
            DynamicTextFieldForModifyParts.setText(((Outsourced) getAllParts().get(partIdx)).getCompanyName());
            DynamicLabelForModifyParts.setText("Company Name");
            outsourcedRadioButtonForModifyParts.setSelected(true);
        }
    }
}
