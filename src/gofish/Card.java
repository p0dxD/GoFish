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
        this.state = true;
    }

    Card() {
        this.name = null;
        this.image = new ImageView();
        this.state = true;
    }
    Card(String name) {
        this.name = name;
        this.image = new ImageView();
        this.state = true;
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
}
