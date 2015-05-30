/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gofish;

import java.io.File;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Joseph
 */
public class Deck {
    private DeckTest ui;
    private ArrayList<Card> cards;
    private String imagesPath = "./images";
    private String ImgPath = "file:images/";
    private File folder;
    private File[] listOfImages;

    /**
     * Constructor just creates a deck with 52 (or how ever many cards)
     */
    Deck() {
        cards = new ArrayList<>();
        fillDeck();
    }
    /**
     * Fill the deck initially 
     */
    public void fillDeck() {
        folder = new File(imagesPath);
        listOfImages = folder.listFiles();
        System.out.println("Path: "+new File(listOfImages[0].getName()).toURI().toString());
        for (int i = 0; i < listOfImages.length; i++) {
            if (listOfImages[i].isFile() && listOfImages[i].getName().startsWith("Playing")) {
                cards.add(new Card());
                cards.get(i).setName(listOfImages[i].getName());
                Image img = new Image(ImgPath + listOfImages[i].getName());
                cards.get(i).setImage(new ImageView(img));
            }
        }

    }
    /**
     * Remove a card from the deck
     * @param location 
     */
    public void removeCard(int location) {
        cards.remove(location);
        //add to player
    }
    /**
     * Adds a card not currently in deck to the deck
     * @param cardName 
     */
    public void addCard(String cardName) {
        if(isInDeck(cardName)==false){
        cards.add(new Card(cardName));
        System.out.println("Added");
        }else{
        System.out.println("Its already in deck");
        }
    }
    /**
     * Will get the cards in the current deck
     * @return ArrayList<> of cards
     */
    public ArrayList<Card> getCards(){
        return this.cards;
    }
    /**
     * This will check whether the card is in the deck or not 
     * @param cardName the name to check for
     * @return true or false depending if it was found or not
     */
    public boolean isInDeck(String cardName){
        boolean status = false;
        ArrayList<Card> cardStatus = getCards();
        for(Card card: cardStatus){
            if(cardName.equals(card.getName())){
                status = true;
            }
        }
        return status;
    }

}
