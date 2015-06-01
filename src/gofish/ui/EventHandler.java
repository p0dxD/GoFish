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
    public void requestToPlay(){
        ui.getSplashScreenPane().setVisible(false);
        ui.getMainPane().setCenter(ui.getGamePane());
    }
    public void requestToAbout(){

        ui.getSplashScreenPane().setVisible(false);
        ui.getMainPane().setCenter(ui.getAboutPane());
    }
    public void requestToExit(){
        System.exit(0);//exits the game
    }
    public void requestToAboutBack(){
        ui.getSplashScreenPane().setVisible(true);
        ui.getMainPane().setCenter(ui.getSplashScreenPane());
    }
    public void requestToDeckPane(){
        ui.gamePlay("human");
    }
}
