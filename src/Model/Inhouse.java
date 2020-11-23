/**
 * checked 09MAR2019
 * FINAL
 */

//Inhouse Class for UML Class Diagram
//Inherits from Part


package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;


public class Inhouse extends Part {

    //class variables
    //machineID from UML Diagram
    private final IntegerProperty machineID;
    
    //Constructro for the inHouse Class
    public Inhouse() {
        super(); //call parent constructor to allow part addition
        machineID = new SimpleIntegerProperty(); //must be set as simple integer 
    }

    
    //set the value of machineID
    public void setMachineID(int machineID) {
        this.machineID.set(machineID);
    }

    
    //Get the ID of the machine
    public int getMachineID() {
        return this.machineID.get();
    }
}