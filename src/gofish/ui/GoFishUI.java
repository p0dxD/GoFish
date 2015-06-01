/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gofish.ui;

import gofish.Card;
import gofish.Computer;
import gofish.Player;
import java.io.File;
import java.util.Random;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 *
 * @author Joseph
 */
public class GoFishUI {
    //For ui
//    private static BorderPane instance = null;
    //Panes used 
    private BorderPane mainPane = null;
    private Pane splashScreenPane;
    private BorderPane gamePane;
    private HBox playerBox;
    private HBox computerBox;
    private Pane askPane;
    private Pane deckPane;
    private BorderPane aboutPane;
    // mainStage
    private Stage primaryStage;
    private int paneWidth, paneHeight;
    //Buttons
    private Button play, exit, about, askComputerButton;
    //TextBoxes 
    private TextField decisionBoxTextField;
    //Players 
    private Player human;
    private Computer computer;
    //Variables in game
    private int numberOfCardsPerPlayer = 7;
    //Event handler for buttons 
    private EventHandler eventHandler;
    //To view the about 
    private WebView browser;
    private WebEngine webEngine;
    

    /**
     * Default constructor
     */
    public GoFishUI() {
        this.initMainPane();
        this.initGameScreen();
        this.initSplashScreen();
        this.initAbout();
    }

    /**
     * Constructor that can take a width and height
     *
     * @param width to start
     * @param height to start
     */
    public GoFishUI(int width, int height) {
        this.setHeigth(height);
        this.setWidth(width);
        this.initHandler();
        this.initMainPane();
        this.initGameScreen();
        this.initAbout();
        this.initSplashScreen();
//        initPlayers();
    }
    public void initHandler(){
        System.out.println("Inside initHandler");
        eventHandler = new EventHandler(this);
    }
    /**
     * Inits the initial stage
     */
    public void initMainPane() {
        mainPane = getInstance();
        this.setMainPane(mainPane);
        mainPane.resize(paneWidth, paneHeight);
    }

    /**
     * Inits the splash screen on main pane
     */
    public void initSplashScreen() {
        System.out.println("Inside initSplashScreen");
        splashScreenPane = new Pane();//this is where we are putting the splash screen stuff(buttons)
        this.setSplashScreen(splashScreenPane);
        ImageView img = new ImageView(new Image(//Sets the image of splashScreen
                new File("backgroundSplashOptional.gif").toURI().toString()));
        img.setFitHeight(this.getHeigth());
        img.setFitWidth(this.getWidth());
//            splashScreenPane.getStyleClass().add("right-page-turn");
        play = new Button("PLAY");
        about = new Button("ABOUT");
        exit = new Button("EXIT");
        //Modifies play button
        play.getStyleClass().add("green");
        play.setTranslateX(this.getWidth() / 2);
        play.setTranslateY(this.getHeigth() / 2);
        play.setTooltip(new Tooltip("Click here to play"));
        //Modifies about button
        about.getStyleClass().add("orange");
        about.setTranslateX(this.getWidth() / 2);
        about.setTranslateY((this.getHeigth() / 6));
        about.setTooltip(new Tooltip("Click here to read the about"));

        //Modifies exit button
        exit.getStyleClass().add("red");
        exit.setTranslateX(this.getWidth() / 2);
        exit.setTranslateY(this.getHeigth() - (this.getHeigth() / 6));
        exit.setTooltip(new Tooltip("Click here to exit"));
        //Sets the action for play button
        play.setOnMouseClicked(e -> {
            eventHandler.requestToPlay();
        });
        //sets the action for about button
        about.setOnMouseClicked(e -> {
            eventHandler.requestToAbout();
        });
        //sets the action for exit button
        exit.setOnMouseClicked(e -> {
            eventHandler.requestToExit();
        });

        splashScreenPane.getChildren().addAll(img, play, about, exit);
        this.getMainPane().setCenter(splashScreenPane);
    }

    /**
     * initializes the game screen
     */
    public void initGameScreen() {
        System.out.println("Inside initGameScreen");
        deckPane = new Pane();
        gamePane = new BorderPane();
        askPane = new Pane();
        askComputerButton = new Button("Ask");
        playerBox = new HBox();//to store the player cards
        computerBox = new HBox();//to store the computer cards
        decisionBoxTextField = new TextField("input choice here");
        human = new Player(numberOfCardsPerPlayer);//initialize(player)
        computer = new Computer(numberOfCardsPerPlayer);//initialize(computer)
        //to initialy display cards
        displayCards(playerBox, human, "human");
        displayCards(computerBox, computer, "Computer");
        updateDeck(deckPane, computer);
        //The button ask and the textField
        decisionBoxTextField.setTranslateX((this.getWidth() / 2));
        askComputerButton.setTranslateX((this.getWidth() / 2) + 200);
        askComputerButton.setTranslateY(this.getHeigth() - 300);
        decisionBoxTextField.setTranslateY(this.getHeigth() - 300);
        askPane.getChildren().addAll(askComputerButton, decisionBoxTextField);
        //This is for the ask button
        askComputerButton.setOnMouseClicked(e -> {
            if (computer.isInHand(decisionBoxTextField.getText())) {
                computer.handAdditionFromOtherHand(human, decisionBoxTextField.getText());
                displayCards(playerBox, human, "human");
                displayCards(computerBox, computer, "computer");
            } else {
                System.out.println("It was not, pick a card from deck");
                askComputerButton.setDisable(true);
            }
        });
        //set gamePane click action
        deckPane.setOnMouseClicked(e -> {
            human.getCardToHandFromDeck();
            human.checkMatchingFour();
//        computer.getCardToHandFromDeck();
            displayCards(playerBox, human, "human");
            displayCards(computerBox, computer, "computer");
            updateDeck(deckPane, computer);

        });
        gamePane.setLeft(deckPane);
        gamePane.setCenter(askPane);
        gamePane.setBottom(playerBox);
        gamePane.setTop(computerBox);
        this.getMainPane().setCenter(gamePane);
    }

    /**
     * Initializes the players with on screen cards
     */
    public void initPlayers() {
        System.out.println("Inside initPlayers");
        //human = new Player(numberOfCardsPerPlayer);
//        computer = new Computer(numberOfCardsPerPlayer);

    }

    public void initAbout() {
        browser = new WebView();
        webEngine = browser.getEngine();
        HBox box = new HBox();
        Button back = new Button("Back");
        box.setAlignment(Pos.CENTER);
        aboutPane = new BorderPane();
        this.setAboutPane(aboutPane);
        aboutPane.setTop(box);
        aboutPane.setCenter(browser);
        box.getChildren().add(back);
        webEngine.load(new File("about.html").toURI().toString());
        this.getMainPane().setCenter(aboutPane);
        back.setOnMouseClicked(e -> {
            eventHandler.requestToAboutBack();
        });
        back.getStyleClass().add("blue");
    }

    /**
     * Displays the cards as they update
     *
     * @param pane where we put them
     * @param player whose cards
     */
    public void displayCards(HBox pane, Player player, String type) {
//        if(type.equals("computer")){
//            player.flipHand(player.getHand());
//        }
        System.out.println("InsideDsplay cards");
        pane.getChildren().clear();
        int count = 1;
        for (Card card : player.getHand()) {

            card.getImage().setTranslateX(60 + (count));
            pane.getChildren().add(card.getImage());
            count += 20;
        }

    }

    public void updateDeck(Pane pane, Player player) {
        System.out.println("Inside updateDeck");
        pane.getChildren().clear();
//        player.getDeck().flipDeck(false);
        int count = 1;
        for (Card card : player.getDeck().getCards()) {

            pane.getChildren().add(card.getImage());
//                count+=10;
        }
    }

    /**
     * Returns an instance of the main pane
     *
     * @return borderPane main
     */
    public BorderPane getMainPane() {
        return this.mainPane;
    }

    /**
     * Sets the stage to the given one
     *
     * @param stage input stage
     */
    public void setStage(Stage stage) {
        this.primaryStage = stage;
    }

    /**
     * Sets the width of the main pane
     *
     * @param paneWidth
     */
    public void setWidth(int paneWidth) {
        this.paneWidth = paneWidth;
    }

    /**
     * The width of the main pane
     *
     * @return width
     */
    public int getWidth() {
        return this.paneWidth;
    }

    /**
     * sets the height of the main pane
     *
     * @param paneHeight
     */
    public void setHeigth(int paneHeight) {
        this.paneHeight = paneHeight;
    }

    /**
     * Returns the main pane height
     *
     * @return height
     */
    public int getHeigth() {
        return this.paneHeight;
    }
    /**
     * The deck pane with cards
     * @return deck 
     */
    public Pane getDeckPane() {
        return this.deckPane;
    }
    /**
     * This returns the main game pane
     * @return pane with game
     */
    public BorderPane getGamePane(){
        return this.gamePane;
    }
    public void setAboutPane(BorderPane about){
        this.aboutPane = about;
    }
    /**
     * This returns the about pane with htlm
     * @return about 
     */
    public BorderPane getAboutPane(){
        return this.aboutPane;
    }
    public void setSplashScreen(Pane splashScreenPane){
        this.splashScreenPane = splashScreenPane;
    }
    public Pane getSplashScreenPane(){
        return this.splashScreenPane;
    }
    /**
     * Randomly chooses who starts
     *
     * @return player or computer
     */
    public String whoStarts() {
        String[] player = {"human", "computer"};
        int who = new Random().nextInt(2);
        return player[who];
    }
    public void setMainPane(BorderPane mainPane){
        this.mainPane = mainPane;
    }
        public BorderPane getInstance() {
        if (mainPane == null) {
            mainPane = new BorderPane();
            
        }
        return mainPane;
    }
        public void gamePlay(String playerType){
            while(!human.isItOver()&&!computer.isItOver()){
            human.getCardToHandFromDeck();
            human.checkMatchingFour();
//        computer.getCardToHandFromDeck();
            displayCards(playerBox, human, "human");
            displayCards(computerBox, computer, "computer");
            updateDeck(deckPane, computer);
                System.out.println("Not over yet");

            }
        }
}
