/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gofish;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Joseph
 */
public class Player {

//    private DeckTest ui;
    private static Deck instance = null;
    private Deck deck;
    private ArrayList<Card> hand;
    private int sizeOfHand;
    private int score = 0;
    private boolean containsIt;
    private boolean isOver = false;
    
    public Player() {
//        ui = new DeckTest();
        hand = new ArrayList<>();
        sizeOfHand = 7;
        deck = getInstance();
        fillHand();
    }

    /**
     * In this one we can start with a different size of hand
     *
     * @param sizeOfHand size
     */
    public Player(int sizeOfHand) {
//        ui = new DeckTest();
        hand = new ArrayList<>();
        this.sizeOfHand = sizeOfHand;
        deck = getInstance();
        fillHand();
    }

    /**
     * Fills up the initial hand with the size wanted
     */
    private void fillHand() {
        Collections.shuffle(deck.getCards());
//        deck.flipDeck(false);//flips to back 
        System.out.println("Inside fillhand: " + deck.sizeOfDeck());
        for (int i = 0; i < sizeOfHand; i++) {
            deck.removeCardAndAddToHand(i, hand);
        }
        System.out.println("Going out fillhand: " + deck.sizeOfDeck());
    }

    /**
     * Mainly for the computer flips whole initial hand
     *
     * @param cards the hand to flip
     */
    public void flipHand() {
        for (int i = 0; i < getHand().size(); i++) {
            if (this.getHand().get(i).isFlipped() == true) {
                if (this.getHand().get(i).isFlipped()) {
                    Image img = new Image(new File("back.jpg").toURI().toString());
                     ImageView view = new ImageView(img);
                view.setFitHeight(100);
                view.setFitWidth(80);
                    this.getHand().get(i).setImage(view);
                }
            }
        }
    }

    public void flipCard(Card card) {
        if (card.isFlipped() == true) {
            Image img = new Image(new File("back.jpg").toURI().toString());
                            ImageView view = new ImageView(img);
                view.setFitHeight(100);
                view.setFitWidth(80);
            card.setImage(view);
        }
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public int getHandSize() {
        return hand.size();
    }

    /**
     * This method will add from one hand to the other if it has it
     *
     * @param from where we taking the cards
     * @param to where we are bringing the cards
     * @param cardToAdd name of the card (number) ex. "5"
     */
    public void handAdditionFromOtherHand(Player to, String cardToAdd) {
        boolean itHadIt = false;
        for (Card card : this.getHand()) {
            if (card.getName().contains(cardToAdd)) {
                to.getHand().add(card);
                itHadIt = true;
            } else {
                System.out.println("Taken from Deck not");
            }
        }
        if (itHadIt == true) {
            this.removeFromHand(cardToAdd);
        }
    }

    public void removeFromHand(String cardToRemove) {
        ArrayList<Card> temp = new ArrayList<>();
//        System.out.println("Inside removeFromHand: " + getHandSize() + " " + cardToRemove);
//        int loops = getHandSize();
        for (Card card : getHand()) {
            if (!card.getName().contains(cardToRemove)) {
                temp.add(card);
            }
        }
        hand = temp;
//        System.out.println("Inside removeFromHand new: " + getHandSize());
    }

    /**
     * Adds a card from deck
     */
    public void getCardToHandFromDeck() {
        if (deck.sizeOfDeck() != 0) {
            deck.removeCardAndAddToHand(deck.sizeOfDeck() - 1, hand);
        }
    }

    /**
     * Gets the current size of the deck
     *
     * @return deck size
     */
    public int getDeckSize() {
        return deck.sizeOfDeck();
    }
    /**
     * Returns the deck 
     * @return 
     */
    public Deck getDeck() {
        return deck;
    }

    /**
     * Singleton that will take care of the deck once per game No other decks
     * can be created
     *
     * @return instance of the deck if not already created
     */
    public Deck getInstance() {
        if (instance == null) {
            instance = new Deck();
        }
        return instance;
    }

    /**
     * This will check if the hand has 4 cards of the same will add one to score
     * if it does
     *
     * @param cards
     * @param player
     */
    public void checkMatchingFour() {
        System.out.println("Inside check");
        String[] cardOptions = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        int max = 4;//matches needed
        final Map<String, Integer> countMap = new HashMap<>();
//        for (int i = 2; i <= 10; i++) {//O(mn)
        for (String cardNames : cardOptions) {
            int count = 0;
            for (int j = 0; j < getHandSize(); j++) {
                if (countMap.containsKey(cardNames) && this.getHand().get(j).getName().contains(cardNames)) {
                    count = countMap.get(cardNames) + 1;
                } else {
                    count = 1;
                }
                if (this.getHand().get(j).getName().contains(cardNames)) {
                    countMap.put(cardNames, count);
                }
            }
        }
        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            if (value == max) {
                System.out.print("Key: " + key);
                removeFromHand(key);
                System.out.println(" value " + value);
                score++;
            }
        }

    }
    public boolean isItOver(){
        if(this.getHand().isEmpty()&&this.getDeckSize()==0){
            this.isOver = true;
        }
        return isOver;
    }
    public void increaseScore() {
        score++;
    }

    public int getScore() {
        return score;
    }
    public boolean isInHand(String cardName){
        containsIt = false;
        for(Card card: this.getHand()){
            if(card.getName().contains(cardName)){
                containsIt = true;
            }
        }
        return containsIt;
    }
}
