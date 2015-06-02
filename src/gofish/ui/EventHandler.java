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

    public void requestToAskButton() {
        System.out.println("Size of player before this " + ui.getHuman().getHandSize());
        if (allowedOption(ui.getDecisionBoxTextField().getText().toUpperCase())) {
            ui.getPlayerLabel().setText("Lets see if the computer has it." + ui.getDecisionBoxTextField().getText());
            if (ui.getComputer().isInHand(ui.getDecisionBoxTextField().getText())) {
                ui.getComputerLabel().setText("Oh you got some of my cards");
                ui.getComputer().addCardsFromOneHandToTheOther(ui.getHuman(), ui.getDecisionBoxTextField().getText());
//                ui.getHuman().getHandSize();
        System.out.println("Size of player after this " + ui.getHuman().getHandSize());
        ui.displayCards(ui.getComputerPane(), ui.getComputer(), "computer");
        ui.displayCards(ui.getPlayerPane(), ui.getHuman(), "human");
            }
        } else {
            ui.getPlayerLabel().setText("This is not Allowed please try again.");
        }

    }

    public void startGame(String playerTypeStarter) {
        System.out.println("Starts: " + playerTypeStarter);
        ui.gamePlay(playerTypeStarter);
    }

    private boolean allowedOption(String option) {
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
