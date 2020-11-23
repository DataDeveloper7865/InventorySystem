
package Model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public abstract class Part {


    //Instance variables
    private final StringProperty name;
    private final IntegerProperty partID;
    private final DoubleProperty price;
    private final IntegerProperty inStock;
    private final IntegerProperty min;
    private final IntegerProperty max;

    //Contructor
    public Part() {
        partID = new SimpleIntegerProperty();
        name = new SimpleStringProperty();
        price = new SimpleDoubleProperty();
        inStock = new SimpleIntegerProperty();
        min = new SimpleIntegerProperty();
        max = new SimpleIntegerProperty();
    }

    //return the partID property
    public IntegerProperty getPartIDIntegerProperty() {
        return partID;
    }

    //return the partname property of the object
    public StringProperty getPartNameStringProperty() {
        return name;
    }

    //return the price of the object
    public DoubleProperty getPartPriceDoubleProperty() {
        return price;
    }

    //return the inventory level of the object
    public IntegerProperty getPartInvIntegerProperty() {
        return inStock;
    }

//GETTERS AND SETTERS
    //Get the partid of the object
    public int getPartID() {
        return this.partID.get();
    }
    
    //set the partID Of the object
    public void setPartID(int partID) {
        this.partID.set(partID);
    }

    //get the partname of the object
    public String getNamePart() {
        return this.name.get();
    }
    
    //set the part name of the part
    public void setNamePart(String name) {
        this.name.set(name);
    }

    //get the price of the part
    public double getPricePart() {
        return this.price.get();
    }
    
    //set the price of the part
    public void setPricePart(double price) {
        this.price.set(price);
    }

    //get the number of in stock of the parts
    public int getInStockPart() {
        return this.inStock.get();
    }
    
    //set the number of in stock of the parts
    public void setInStockPart(int inStock) {
        this.inStock.set(inStock);
    }

    //get the minimum of the part
    public int getMinPart() {
        return this.min.get();
    }
    
    
    //sset the minimum of the part
    public void setMinPart(int min) {
        this.min.set(min);
    }

    
    ///get the maximum of the opart
    public int getMaxPart() {
        return this.max.get();
    }

    
    //St the maximum of the part
    public void setMaxPart(int max) {
        this.max.set(max);
    }

    
    //Set 1 item 3 requirement in SoftwareIProjectRequirments.pdf
    public static String exceptionControlSet1(String min, String max) {
        String error = "";
        if (Integer.parseInt(min) > Integer.parseInt(max)) {
            error = error + ("Max must be greater than the min value.");
        }
        return error;
    }
}
