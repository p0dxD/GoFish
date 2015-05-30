/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gofish;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author Joseph
 */
public class Player {

    private DeckTest ui;
    private static Deck instance = null;
    private Deck deck;
    private ArrayList<Card> hand;
    private int sizeOfHand;

    Player() {
        ui = new DeckTest();
        hand = new ArrayList<>();
        sizeOfHand = 7;
        deck = getInstance();
//        System.out.println(deck.sizeOfDeck());
        fillHand();
    }

    /**
     * In this one we can start with a different size of hand
     *
     * @param sizeOfHand size
     */
    Player(int sizeOfHand) {
        ui = new DeckTest();
        hand = new ArrayList<>();
        this.sizeOfHand = sizeOfHand;
        deck = getInstance();
//        System.out.println(deck.sizeOfDeck());
        fillHand();
    }
//            fillhand
//            
//            have remove from hand, add to hand from this or the
//                    computer deck

    /**
     * Fills up the initial hand with the size wanted
     */
    private void fillHand() {
//        Random cardChooser = new Random();
        //Collections.shuffle(deck.getCards());
        System.out.println("Inside fillhand: " + deck.sizeOfDeck());
        for (int i = 0; i < sizeOfHand; i++) {
            deck.removeCardAndAddToHand(i, hand);
        }
        System.out.println("Going out fillhand: " + deck.sizeOfDeck());
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
    public void handAdditionFromOtherHand(ArrayList<Card> from, ArrayList<Card> to, String cardToAdd) {
        for (int i = 0; i < from.size(); i++) {

//        System.out.println("Names: "+ hand.get(i).getName());
            if (from.get(i).getName().contains(cardToAdd)) {
            }
            to.add(from.get(i));
            from.remove(i);
        }
    }

    public void removeFromHand(String cardToRemove) {

        System.out.println("Inside removeFromHand: " + getHandSize());
        //hand.get(1).getName().contains(cardToRemove);
        for (int i = 0; i < getHandSize(); i++) {

//        System.out.println("Names: "+ hand.get(i).getName());
            if (hand.get(i).getName().contains(cardToRemove)) {
                hand.remove(hand.get(i));

                System.out.println("It contains it");
            } else {

//        System.out.println("Removed nothing");
            }
        }
    }

    public void addCardToHandFromDeck() {
        if (deck.sizeOfDeck() != 0) {
            deck.removeCardAndAddToHand(deck.sizeOfDeck() - 1, hand);
        }
    }

    public int getDeckSize() {
        return deck.sizeOfDeck();
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
}
