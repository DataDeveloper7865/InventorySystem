

/** outsourced class if part is outsourced needs to behave differently than inhouse
 * 
 * 
 * FINAL
 */

package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Outsourced extends Part {

    private final StringProperty companyName;

    //constructor for outsourced 
    public Outsourced() {
        super();
        companyName = new SimpleStringProperty();
    }

    //set the company name 
    public void setCompanyName(String companyName) {
        this.companyName.set(companyName);
    }

    //get the company name
    public String getCompanyName() {
        return this.companyName.get();
    }
}
