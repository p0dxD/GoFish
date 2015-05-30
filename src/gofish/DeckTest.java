/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gofish;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
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
//    public static Deck deck = new Deck();
    @Override
    public void start(Stage primaryStage) {
        //deck = new Deck();
//        initDeck();//initialized the deck
//        System.out.println("Size: "+deck.sizeOfDeck());
        Player player = new Player(2);
        System.out.println("Size: "+player.getHandSize());
        Player computer = new Player(2);
        System.out.println("Size: "+computer.getHandSize());
        Player player2 = new Player(2);
        System.out.println("Size: "+player2.getHandSize());
        Player player3 = new Player(2);
        System.out.println("Size: "+player3.getHandSize());
        
//        System.out.println("Size: "+deck.sizeOfDeck());
//        int count = 1;
//        deck = new Deck();
//        ArrayList<Card> cards = deck.getCards();
//
//        count = 1;
////        deck.removeCardAndAddToHand(1);
//        System.out.println();
//        for(Card card: cards){
//        System.out.println(card.getName() + count);
//        count++;
//        }
//        System.out.println();
//        count = 1;
//        deck.addCard("Playing_card_club_2.jpg");
//        for(Card card: cards){
//        System.out.println(card.getName() + count);
//        count++;
//        }

        
        Pane root = new Pane();
        display(player3,player3.getHand(),root);
        root.setOnMouseClicked(e->{
        player3.addCardToHandFromDeck();
        System.out.println("Size: "+player3.getHandSize());
        System.out.println("Size deck: "+player3.getDeckSize());
       display(player3,player3.getHand(),root);
       player3.removeFromHand("3");
        });
            //TESSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSST
        /*
//        ArrayList<Card> cardsPlayer = new ArrayList<>();
        Player player = new Player();
        ArrayList<Card> cardsPlayer = player.getHand();
        System.out.println("Size: "+player.getHandSize());
//        player.removeFromHand(cardsPlayer.get(0).getName());
        System.out.println("Player has: " + cardsPlayer.size()+" Cards");
        for(int i = 0;i < player.getHandSize();i++){
//            System.out.println(cardsPlayer.get(i).isFlipped());
        cardsPlayer.get(i).getImage().setTranslateX(60+((i+1)*width/10));
        root.getChildren().addAll(cardsPlayer.get(i).getImage());
//        cards.get(i).getImage().setOnMouseClicked(e->{change(i);});
        }
        root.setOnMouseClicked(e->{player.removeFromHand("5");
        display(player,cardsPlayer,root);
        });
//        player.removeFromHand("2");
//        System.out.println("Size: "+player.getHandSize());
        */
        Scene scene = new Scene(root, width, height);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public void change(int i){
        int j = i;
    }
public void display(Player player,ArrayList<Card> cardsPlayer, Pane root){
    root.getChildren().clear();
            for(int i = 0;i < player.getHandSize();i++){
//            System.out.println(cardsPlayer.get(i).isFlipped());
        cardsPlayer.get(i).getImage().setTranslateX(60+((i+1)*width/10));
        root.getChildren().addAll(cardsPlayer.get(i).getImage());
//        cards.get(i).getImage().setOnMouseClicked(e->{change(i);});
        }
}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
