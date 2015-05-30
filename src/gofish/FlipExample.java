/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gofish;

import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Joseph
 */
public class FlipExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        //Rectangle front = new Rectangle(50, 50);
        Node node = createCard(false);
        Node nodeTrue = createCard(true);
        ScaleTransition stHideFront = new ScaleTransition(Duration.millis(1500), node);
        stHideFront.setFromX(1);
        stHideFront.setToX(0);

        //Rectangle back = new Rectangle(50, 50, Color.RED);
//        back.setScaleX(0);
        nodeTrue.setScaleX(0);
        ScaleTransition stShowBack = new ScaleTransition(Duration.millis(1500), nodeTrue);
        stShowBack.setFromX(0);
        stShowBack.setToX(1);

        stHideFront.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                stShowBack.play();
            }
        });

        StackPane root = new StackPane();
        root.getChildren().addAll(node, nodeTrue);
        Scene scene = new Scene(root, 600, 700);
        primaryStage.setScene(scene);

        primaryStage.show();
        stHideFront.play();
    }

    private Node createCard(boolean back) {
        ImageView imgView = null;
        if (back == false) {
            imgView = new ImageView(new Image("http://www.ohmz.net/wp-content/uploads/2012/05/Game-of-Throne-Magic-trading-cards-2.jpg"));
        } else {
            imgView = new ImageView(new Image("http://upload.wikimedia.org/wikipedia/commons/thumb/e/e9/Cards-A-Diamond.svg/428px-Cards-A-Diamond.svg.png"));
        }
        return imgView;

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
