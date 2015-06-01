/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gofish;

import gofish.ui.GoFishUI;
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
    private int width =600;
    private int height = 300;
    private boolean won = false;
//    public static Deck deck = new Deck();
    @Override
    public void start(Stage primaryStage) {
        GoFishUI go = new GoFishUI();
        System.out.println(go.whoStarts());
//        Player player = new Player(2);
//        System.out.println("Size: "+player.getHandSize());
//        System.out.println("Score Computer: "+player.getScore());
        Computer computer = new Computer(7);
        System.out.println("Size: "+computer.getHandSize());
        System.out.println("Score Computer: "+computer.getScore());
        System.out.println(computer.makeMove());
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

//        Playing_card_club_10
        
        Pane root = new Pane();
        display(computer,computer.getHand(),root);
        //PANE CLICK
        root.setOnMouseClicked(e->{
//        player3.flipHand(player3.getHand());
        System.out.println("Size deck: "+computer.getDeckSize());
//        System.out.println("Size player: "+player.getHandSize());
//        player.handAdditionFromOtherHand(player.getHand(),computer.getHand(),"Playing_card_club_10");
        System.out.println("Size: "+computer.getHandSize());
        System.out.println("Score com: "+computer.getScore());
//        System.out.println("Score player: "+player.getScore());
        computer.getCardToHandFromDeck();
        computer.checkMatchingFour();
        System.out.println("won: "+won(computer));
       display(computer,computer.getHand(),root);
//        computer.increaseScore();
//       computer.removeFromHand("3");
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
/**
 * temporary to display stuff on pane
 * @param player
 * @param cardsPlayer
 * @param root 
 */
public void display(Player player,ArrayList<Card> cardsPlayer, Pane root){
    root.getChildren().clear();
            for(int i = 0;i < player.getHandSize();i++){
//            System.out.println(cardsPlayer.get(i).isFlipped());
        cardsPlayer.get(i).getImage().setTranslateX(60+((i+1)*width/10));
        root.getChildren().addAll(cardsPlayer.get(i).getImage());
//        cards.get(i).getImage().setOnMouseClicked(e->{change(i);});
        }
}public boolean won(Player player){
    if(player.getHand().isEmpty()&&player.getDeckSize()==0){
        won = true;
    }
    return won;
}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
