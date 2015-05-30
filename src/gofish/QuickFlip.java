/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gofish;

import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.layout.StackPane;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class QuickFlip extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Node card = createCard(false);

        stage.setScene(createScene(card));
        stage.show();
        
        RotateTransition rotator = createRotator(false);
        rotator.play();
    }

    private Scene createScene(Node card) {
        StackPane root = new StackPane();
        root.getChildren().addAll(card);

        Scene scene = new Scene(root, 600, 700, true, SceneAntialiasing.BALANCED);
        scene.setCamera(new PerspectiveCamera());

        return scene;
    }

    private Node createCard(boolean back) {
        ImageView imgView = null;
        if(back==false){
            imgView = new ImageView(new 
        Image("http://www.ohmz.net/wp-content/uploads/2012/05/Game-of-Throne-Magic-trading-cards-2.jpg"));
        }else{
           imgView = new ImageView(new 
        Image("http://upload.wikimedia.org/wikipedia/commons/thumb/e/e9/Cards-A-Diamond.svg/428px-Cards-A-Diamond.svg.png")); 
        }
        return imgView;
        
    }

    private RotateTransition createRotator(boolean back) {
        Node card = createCard(back);
        RotateTransition rotator = new RotateTransition(Duration.millis(10000), card);
        rotator.setAxis(Rotate.Y_AXIS);
        rotator.setFromAngle(0);
        rotator.setToAngle(360);
        rotator.setInterpolator(Interpolator.LINEAR);
        rotator.setCycleCount(10);

        return rotator;
    }

    public static void main(String[] args) {
        launch();
    }
}
