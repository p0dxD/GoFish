/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gofish;

import java.util.Random;

/**
 *
 * @author Joseph
 */
public class Computer extends Player{
    private String pick = null, message = null;
    public Computer(){
        super();
    }
    public Computer(int i){
        super(i);
        super.flipHand();
    }
    
    public String makeMove(){
        pick = null;
        String[] cardOptions = {"A", "2", "3", "4", "5",
            "6", "7", "8", "9", "10", "J", "Q", "K"};
        int ran;
        while(pick==null){
            ran = new Random().nextInt(cardOptions.length-1);
            System.out.println("Inside while "+ran);
        if(super.isInHand(cardOptions[ran]))
            pick = cardOptions[ran];
        }
        return pick;
    }

    public String message(){
        return message;
    }
}
