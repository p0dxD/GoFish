/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gofish.ui;

/**
 *
 * @author Joseph
 */
public class EventHandler {

    private GoFishUI ui;

    public EventHandler(GoFishUI initUI) {
        ui = initUI;
    }

    public void requestToPlay() {
        ui.getSplashScreenPane().setVisible(false);
        ui.getMainPane().setCenter(ui.getGamePane());
    }

    public void requestToAbout() {

        ui.getSplashScreenPane().setVisible(false);
        ui.getMainPane().setCenter(ui.getAboutPane());
    }

    public void requestToExit() {
        System.exit(0);//exits the game
    }

    public void requestToAboutBack() {
        ui.getSplashScreenPane().setVisible(true);
        ui.getMainPane().setCenter(ui.getSplashScreenPane());
    }

    public void requestToDeckPane() {
        ui.gamePlay("human");
    }

//    public void requestToAskButton() {
//        System.out.println("Inside event handler");
//        if(ui.getHuman().isTurn()){
//            processPlayerTurn();
//        }else{
//            processComputerTurn();
//        }
//    }
    public void processPlayerTurn() {
        ui.getAskComputerButton().setDisable(false);
        ui.getDecisionBoxTextField().setDisable(false);
        ui.getPlayerLabel().setText("Please input guess from hand and press \"Ask\".");
        ui.getAskComputerButton().setOnMouseClicked(ei -> {
            if (isAllowedOption(ui.getDecisionBoxTextField().getText())) {//check if its an allowed char
                ui.getPlayerLabel().setText("Lets see if the computer has it.\n" + ui.getDecisionBoxTextField().getText());
                if (ui.getComputer().isInHand(ui.getDecisionBoxTextField().getText())) {
                    ui.getComputerLabel().setText("Oh you got some of my cards");
                    ui.getComputer().addCardsFromOneHandToTheOther(ui.getHuman(), ui.getDecisionBoxTextField().getText());
                    if (ui.getHuman().hasMatchingFour()) {
                        ui.getPlayerLabel().setText("You just scored a point.");
                    }
//                ui.getHuman().getHandSize();
                    System.out.println("Size of player after this " + ui.getHuman().getHandSize());
                    ui.displayCards(ui.getComputerPane(), ui.getComputer(), "computer");
                    ui.displayCards(ui.getPlayerPane(), ui.getHuman(), "human");
                } else {//in case player doesnt guess correctly
                    ui.getComputerLabel().setText("Better Luck next time! GoFish!");
                    ui.getDeckPane().setOnMouseClicked(e -> {
                        System.out.println("Card picked");
                        ui.getHuman().getCardToHandFromDeck();
                        if (ui.getHuman().hasMatchingFour()) {
                            ui.getPlayerLabel().setText("You just scored a point.");
                        }
                        ui.displayCards(ui.getComputerPane(), ui.getComputer(), "computer");
                        ui.displayCards(ui.getPlayerPane(), ui.getHuman(), "human");
                        ui.getHuman().setIsTurn(false);
                        ui.getComputer().setIsTurn(true);
                        ui.getAskComputerButton().setDisable(true);
                        //now call the computer stuff
                        processComputerTurn();
                    });
                }
            } else {
                ui.getPlayerLabel().setText("This is not Allowed please try again.");
            }
        });
        System.out.println("Outside event handler");
    }

    public void processComputerTurn() {
        //Disable buttons for player, enable for computer
        ui.getAskComputerButton().setDisable(true);
        ui.getDecisionBoxTextField().setDisable(true);
        ui.getYesBtn().setDisable(false);
        ui.getGofishBtn().setDisable(false);
        System.out.println("Now is the computers turn");
        String move = ui.getComputer().makeMove();
        ui.getComputerLabel().setText("Let me pick a card:\n" + move + " Do you have it?");
        ui.getYesBtn().setOnMouseClicked(e -> {
            if (ui.getHuman().isInHand(move)) {
                //give it to him
                ui.getHuman().addCardsFromOneHandToTheOther(ui.getComputer(),
                        move);
                if (ui.getComputer().hasMatchingFour()) {
                    ui.getPlayerLabel().setText("Computer just scored a point.");
                }
                ui.displayCards(ui.getComputerPane(), ui.getComputer(), "computer");
                ui.displayCards(ui.getPlayerPane(), ui.getHuman(), "human");
                ui.getYesBtn().setDisable(true);
                ui.getGofishBtn().setDisable(true);
                ui.getAskComputerButton().setDisable(false);
                processPlayerTurn();
            } else {
                ui.getComputerLabel().setText("Don't lie to me!\n"
                        + "Try again.");
            }
        });
        ui.getGofishBtn().setOnMouseClicked(e -> {
            if (ui.getHuman().isInHand(move)) {
                ui.getComputerLabel().setText("Don't lie to me!\n"
                        + "Try again.");
            } else {
                //take from deck
                ui.getComputer().getCardToHandFromDeck();
                ui.getComputerLabel().setText("Okay I got a card from deck.\n"
                        + "Your turn!");
                if (ui.getComputer().hasMatchingFour()) {
                    ui.getPlayerLabel().setText("Computer just scored a point.");
                }
                ui.displayCards(ui.getComputerPane(), ui.getComputer(), "computer");
                ui.displayCards(ui.getPlayerPane(), ui.getHuman(), "human");
                ui.getYesBtn().setDisable(true);
                ui.getGofishBtn().setDisable(true);
                ui.getAskComputerButton().setDisable(false);
                ui.getHuman().setIsTurn(true);
                ui.getComputer().setIsTurn(false);
                processPlayerTurn();
            }
        });
    }

    public void startGame(String playerTypeStarter) {
        System.out.println("Starts: " + playerTypeStarter);
        ui.gamePlay(playerTypeStarter);
    }

    private boolean isAllowedOption(String option) {
        boolean allowed = false;
        String[] cardOptions = {"A", "2", "3", "4", "5",
            "6", "7", "8", "9", "10", "J", "Q", "K"};
        for (String options : cardOptions) {
            if (options.equals(option)) {
                allowed = true;
            }
        }
        return allowed;
    }
}
