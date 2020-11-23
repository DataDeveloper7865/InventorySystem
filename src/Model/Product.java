
package Model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Product {

    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private final IntegerProperty productID;
    private final StringProperty name;
    private final DoubleProperty price;
    private final IntegerProperty inStock;
    private final IntegerProperty min;
    private final IntegerProperty max;


    //have the constructor set the class variables with new simple integer properties.
    public Product() {
        productID = new SimpleIntegerProperty();
        name = new SimpleStringProperty();
        price = new SimpleDoubleProperty();
        inStock = new SimpleIntegerProperty();
        min = new SimpleIntegerProperty();
        max = new SimpleIntegerProperty();
    }

    ///return the product ID
    //helper function to return an integer property when needed instead of simple integer property
    public IntegerProperty getProductIDIntegerProperty() {
        return productID;
    }

    //return the name fo the object
    //helper function to return an integer property when needed instead of simple integer property
    public StringProperty getProductNameStringProperty() {
        return name;
    }

    //return the price of the object
    //helper function to return an integer property when needed instead of simple integer property
    public DoubleProperty getProductPriceDoubleProperty() {
        return price;
    }

    //return the number of inventory of the object
    //helper function to return an integer property when needed instead of simple integer property
    public IntegerProperty getProductInvIntegerProperty() {
        return inStock;
    }

    //get the prodcut ID of the product object
    public int getProductID() {
        return this.productID.get();
    }
    
    
    //set the prodcut ID of the product object
    public void setProductID(int productID) {
        this.productID.set(productID);
    }

    
    //get the name of the oproduct object
    public String getNameProduct() {
        return this.name.get();
    }
    
    //set the name of the product object
    public void setNameProduct(String name) {
        this.name.set(name);
    }

    //get the price of the product object
    public double getPriceProduct() {
        return this.price.get();
    }
    
    //set the price of the product 
    public void setPriceProduct(double price) {
        this.price.set(price);
    }

    
    //get the stock of the prodccut
    public int getInStockProduct() {
        return this.inStock.get();
    }
    
    //set the number of stock of produt
    public void setInStockProduct(int inStock) {
        this.inStock.set(inStock);
    }

    
    //get the minimum amount of the product
    public int getMinProduct() {
        return this.min.get();
    }
    
    //set the minimum of the product
    public void setMinProduct(int min) {
        this.min.set(min);
    }

    
    //get the maximum of the product
    public int getMaxProduct() {
        return this.max.get();
    }
    
    //set the maximum of the product
    public void setMaxProduct(int max) {
        this.max.set(max);
    }

    //return an observable list of all the parts associated with a product
    public ObservableList getProductParts() {
        return associatedParts;
    }

    //add parts that are to be associated with the product
    public void addAssociatedParts(ObservableList<Part> parts) {
        this.associatedParts = parts;
    }

    //check if the sum of the parts is greater than the cost of the product
    //exception control set 1 applies to product as well because it has a minimum and a maximum

    public static String exceptionControlSet2(ObservableList<Part> parts, double price, String min, String max) {
        String messageToUser = "";
        double sumParts = 0.00;
        for (int i = 0; i < parts.size(); i++) {
            sumParts = sumParts + parts.get(i).getPricePart();
        }
        if (sumParts > price) {
                messageToUser = "The price of the product has to be greater than the sum of each part";
                return messageToUser;
            }
        if (Integer.parseInt(min) > Integer.parseInt(max)) {
            messageToUser = messageToUser + ("Max must be greater than the min value.");
        }
        return messageToUser;
    }
}