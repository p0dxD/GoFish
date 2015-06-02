/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gofish;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Joseph
 */
public class Practice {
    private static boolean isOver = false;
    private static boolean isComputerTurn = false;
    private static boolean isPlayerTurn = true;
    private static boolean containsIt = false;
    public static void main(String[] args){
        String[] test = {"1","2","3","4","5","6","7","8","9","10","11","12"};
        ArrayList<String> cards = new ArrayList<>();
        
        for(String card: test){
            cards.add(card);
        }
        System.out.println(Arrays.toString(cards.toArray()));
        ArrayList<String> player = new ArrayList<>();
        ArrayList<String> computer = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        //Adds the numbers to the two lists
        int count = 3;
        int ran;
        while(count !=0){
            ran = new Random().nextInt(cards.size());
            player.add(cards.get(ran));
            cards.remove(ran);
            count--;
        }
        count = 3;
        while(count !=0){
            ran = new Random().nextInt(cards.size());
            computer.add(cards.get(ran));
            cards.remove(ran);
            count--;
        }        
        System.out.println("Computer: "+Arrays.toString(computer.toArray()));
        System.out.println("Player: "+Arrays.toString(player.toArray()));
        System.out.println("Cards left: "+Arrays.toString(cards.toArray()));
        while(!isOver){
//            isOver(cards,player,computer);
            isPlayerTurn = true;
            isComputerTurn = false;
            //For the player
            while(isPlayerTurn){
                System.out.print("Take a guess player: ");
                String number = input.nextLine();
                //checks if computer has it
                if(containsIt(computer,number)){
                    player.add(number);
                    computer.remove(number);
                    System.out.println("Player: "+Arrays.toString(player.toArray()));
                    System.out.println("Computer: "+Arrays.toString(computer.toArray()));
                    containsIt = false;
                }else{
                    System.out.println("Sorry didn't have any,will get you a card from deck");
                    ran = new Random().nextInt(cards.size());
                    player.add(cards.get(ran));
                    cards.remove(ran);
                    isPlayerTurn = false;
                    isComputerTurn = true;
                }
            }
            containsIt =false;
            System.out.println("Cards left: "+Arrays.toString(cards.toArray()));
            //For the computer 
            String com = makeComputerMove();
            while(isComputerTurn){
                   System.out.println("Now the computer will try to get a number: "+ com +" picked");
                    String number = input.nextLine();
                System.out.print("Do you have it?(y/n)");
                if(containsIt(player,com)&&"y".equals(number)){
                    computer.add(com);
                    player.remove(com);
                    com = makeComputerMove();
                }else if(player.contains(com)&&"n".equals(number)){
                System.out.print("you are lying, try again");
                }else{
                    
                }
                   
                
            }
            //Checks if its over
            isOver(cards,player,computer);
        }
    }
    public static String makeComputerMove(){
        int ran = new Random().nextInt(10);
        return Integer.toString(ran);
    }
    public static void isOver(ArrayList<String> cards,ArrayList<String> player,ArrayList<String> computer){
        if(cards.isEmpty()&&player.isEmpty()&&computer.isEmpty()){
            isOver = true;
        }
    }
    public static boolean containsIt(ArrayList<String> cards, String number){
        for(String card: cards){
            if(card.equals(number)){
                containsIt = true;
            }
        }
        return containsIt;
    }
}
