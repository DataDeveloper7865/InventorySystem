//Main Program called
//Submitted to WGU 10MAR2019
//Stephen W
//Class C482
//Software I
//Program written in Java using NetBeans IDE 8.2

package Main;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ViewAndController.MainScreenController;


public class Main extends Application {
    Stage window;
    private AnchorPane MainScreenView;

    
    //Initializing the main screen
    public void initMainScreen() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/ViewAndController/MainScreen.fxml"));
        AnchorPane MainScreenView = (AnchorPane) loader.load();
        Scene scene = new Scene(MainScreenView);       
        window.setScene(scene);
        window.show();
    }
    
    //Calling the main screen
    public void showMainScreen() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/ViewAndController/MainScreen.fxml"));
        AnchorPane MainScreenView = (AnchorPane) loader.load();      
        MainScreenController controller = loader.getController();
        controller.setMainApp(this);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Stephen Whelan C482 - Epic Inventory Mangement System"); 
        initMainScreen();
        showMainScreen();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
