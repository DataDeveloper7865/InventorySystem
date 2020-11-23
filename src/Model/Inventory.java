/**
 * checked 09MAR2019
 */

//Inventory class from UML Diagram
package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Inventory {

    private static int partIDCounter = 0;
    private static int productIDCounter = 0;
    private static final ObservableList<Product> Products = FXCollections.observableArrayList();
    private static final ObservableList<Part> allParts = FXCollections.observableArrayList();

    
    //class constructor
    public Inventory() {
    }

    
    //get all the parts from the inv
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }
    

    //add a part to the inv
    public static void addPart(Part part) {
        allParts.add(part);
    }

    //delete the part from the inv
    public static void deletePart(Part part) {
        allParts.remove(part);
    }

    //update the part in the inv
    public static void updatePart(int index, Part part) {
        allParts.set(index, part);
    }
    

    //Increment the PartID count and then return/get the part ID count
    public static int getPartCounter() {
        partIDCounter++;
        return partIDCounter;
    }

    //verify that the part is actually able to be deleted
    public static boolean isPartDeletable(Part part) {
        boolean inList = false;
        for (int i = 0; i < Products.size(); i++) {
            if (Products.get(i).getProductParts().contains(part)) {
                inList = true;
            }
        }
        return inList;
    }

    
    //lookup the part using a search term, check if it is integer input first
    public static int findPart(String partToBeSearchedFor) {
        boolean inPartList = false;
        int idx = 0;
        if (isInt(partToBeSearchedFor)) {
            for (int i = 0; i < allParts.size(); i++) {
                if (Integer.parseInt(partToBeSearchedFor) == allParts.get(i).getPartID()) {
                    idx = i;
                    inPartList = true;
                }
            }
        } else {
            for (int i = 0; i < allParts.size(); i++) {
                if (partToBeSearchedFor.equals(allParts.get(i).getNamePart())) {
                    idx = i;
                    inPartList = true;
                }
            }
        }
        if (inPartList = true) {
            return idx;
        } else {
            return -1;
        }
    }


    //create function that returns the product list in an observable list data type
    
    public static ObservableList<Product> getAllProducts() {
        return Products;
    }

    //add a product to the product list inventory
    
    
    public static void addProduct(Product productToBeAdded) {
        Products.add(productToBeAdded);
    }

    //remove a product from the product list inventory
    public static void removeProduct(Product productToBeRemoved) {
        Products.remove(productToBeRemoved);
    }

    //get the id of the next product, increments product ID and returns it
    public static int getProductCount() {
        productIDCounter++;
        return productIDCounter;
    }
    
        //helper function to check if the input is an integer
    
    
    public static boolean isInt(String integerToBeChekced) {
        try {
            Integer.parseInt(integerToBeChekced);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //find the product in the list
    
    
    
    public static int lookupProduct(String productToBeLookedUp) {
        boolean inProductList = false;
        int idx = 0;

        if (isInt(productToBeLookedUp)) {
            for (int i = 0; i < Products.size(); i++) {
                if (Integer.parseInt(productToBeLookedUp) == Products.get(i).getProductID()) {
                    idx = i;
                    inProductList = true;
                }
            }
        } else {
            for (int i = 0; i < Products.size(); i++) {
                if (productToBeLookedUp.equals(Products.get(i).getNameProduct())) {
                    idx = i;
                    inProductList = true;
                }
            }
        }
        if (inProductList = true) {
            return idx;
        } else {
            return -1;
        }
    }

    
    //update the product using the index and the product
    
    public static void updateProduct(int index, Product product) {
        Products.set(index, product);
    }

    

}
