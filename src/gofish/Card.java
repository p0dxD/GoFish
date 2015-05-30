/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gofish;

import javafx.scene.image.ImageView;

/**
 *
 * @author Joseph
 */
public class Card {

    private String name;
    private ImageView image;
    private boolean state;

    Card(String name, ImageView image) {
        this.name = name;
        this.image = image;
        this.state = false;
    }

    Card() {
        this.name = null;
        this.image = new ImageView();
        this.state = false;
    }
    Card(String name) {
        this.name = name;
        this.image = new ImageView();
        this.state = false;
    }

    public String getName() {
        return name;
    }

    public ImageView getImage() {
        return image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public void flip(boolean state) {
        this.state = state;
    }

    public boolean isFlipped() {
        return state;
    }
    @Override
    public String toString(){
        return "name: " + this.name + "\nIs if flipped?  " + this.state;
    }
    //Usage with two
    public static void main(String[] args) {
        Card[] cards = new Card[2];

        for (int i = 0; i < cards.length; i++) {
            cards[i] = new Card();
            cards[i].setName("Carl");
        }
        
            cards[1].setName("Ho");
//        System.out.println("Name: " + cards[0].getName());
        System.out.println("Is it flipped? " + cards[0].toString());
        cards[0].flip(true);
        System.out.println("Is it flipped? " + cards[1].toString());
        System.out.println("Is it flipped? " + cards[0].toString());
    }
}
