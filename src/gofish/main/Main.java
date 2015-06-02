/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gofish.main;

import gofish.ui.GoFishUI;
import java.io.File;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Joseph
 */
public class Main extends Application {

    static String title = "Go Fish";
    static int width = 700, heigth = 500;

    @Override
    public void start(Stage primaryStage) {
            //We create a new instance of the GUI
            GoFishUI root = new GoFishUI(width, heigth);
            BorderPane mainPane = root.getMainPane();
            root.setStage(primaryStage);//the main stage
            
            Scene scene = new Scene(mainPane, mainPane.getWidth(), mainPane.getHeight());
            scene.getStylesheets().add(new File("styleButtons.css").toURI().toString());
//            primaryStage.setResizable(false);
            primaryStage.setTitle(title);//set the title
            primaryStage.setScene(scene);//set the scene
            primaryStage.show();//we show

    }

    public static void main(String[] args) {
        launch(args);
    }
}
