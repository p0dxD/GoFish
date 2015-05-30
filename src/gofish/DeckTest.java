/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gofish;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Joseph
 */
public class DeckTest extends Application {
    private String ImgPath = "file:images/";
    private int width =800;
    private int height = 600;
    @Override
    public void start(Stage primaryStage) {
        int count = 1;
        Deck deck = new Deck();
        ArrayList<Card> cards = deck.getCards();

        count = 1;
        deck.removeCard(1);
        System.out.println();
        for(Card card: cards){
        System.out.println(card.getName() + count);
        count++;
        }
        System.out.println();
        count = 1;
        deck.addCard("Playing_card_club_2.jpg");
        for(Card card: cards){
        System.out.println(card.getName() + count);
        count++;
        }

        
        Pane root = new Pane();
        for(int i = 0;i < 5;i++){
        cards.get(i).getImage().setTranslateX(60+((i+1)*width/10));
        root.getChildren().addAll(cards.get(i).getImage());
//        cards.get(i).getImage().setOnMouseClicked(e->{change(i);});
        }
        
        Scene scene = new Scene(root, width, height);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void change(int i){
        int j = i;
    }
    public Image loadImage(String imageName) {
        System.out.println("Inside loadImage");
        
        Image img = new Image(ImgPath + imageName);
        System.out.println(ImgPath+imageName);
        return img;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
