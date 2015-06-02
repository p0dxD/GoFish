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
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
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
    private StackPane playerPane;
    private StackPane computerPane;
    private Pane askPane;
    private StackPane deckPane;
    private BorderPane aboutPane;
    // mainStage
    private Stage primaryStage;
    private int paneWidth, paneHeight;
    //Buttons
    private Button play, exit, about;//for splash
    private Button askComputerButton, yesBtn, gofishBtn;//buttons for in game
    //TextBoxes 
    private TextField decisionBoxTextField;
    //Players 
    private Player human;
    private Computer computer;
    //labels
    private Label computerLabel;
    private Label playerLabel;
    //Variables in game
    private int numberOfCardsPerPlayer = 7;
    //Event handler for buttons 
    private EventHandler eventHandler;
    //To view the about 
    private WebView browser;
    private WebEngine webEngine;
    //turn
    private String whoseTurn;
    //state of game
    private boolean isOver = false;

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

    public void initHandler() {
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
            eventHandler.startGame(this.whoStarts());
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
        whoseTurn = this.whoStarts();
        System.out.println("Inside initGameScreen");
        deckPane = new StackPane();
//        deckPane.setAlignment(Pos.);
        gamePane = new BorderPane();
        askPane = new Pane();
        askComputerButton = new Button("Ask");
        playerPane = new StackPane();//to store the player cards
//        playerPane.setMaxSize(paneWidth, paneHeight);
        computerPane = new StackPane();//to store the computer cards
        decisionBoxTextField = new TextField("ex.\"Q\"");
        computerLabel = new Label("Here will appear what the computer says");
        playerLabel = new Label("Here will appear what the player says");
        yesBtn = new Button("YES");
        gofishBtn = new Button("GoFish");
        human = new Player(numberOfCardsPerPlayer);//initialize(player)
        computer = new Computer(numberOfCardsPerPlayer);//initialize(computer)
        //to initialy display cards
        displayCards(playerPane, human, "human");
        displayCards(computerPane, computer, "Computer");
        updateDeck(deckPane, computer);
        //The button ask and the textField
        playerLabel.setTranslateX((this.getWidth() / 2 - 200));
        playerLabel.setTranslateY(this.getHeigth() - 300);
        computerLabel.setTranslateX((this.getWidth() / 2 - 100));
        computerLabel.setTranslateY(this.getHeigth() - 440);
        yesBtn.setTranslateX((this.getWidth() / 2) + 200);
        yesBtn.setTranslateY(this.getHeigth() - 440);
        gofishBtn.setTranslateX((this.getWidth() / 2) + 150);
        gofishBtn.setTranslateY(this.getHeigth() - 440);
        askComputerButton.setTranslateX((this.getWidth() / 2) + 200);
        askComputerButton.setTranslateY(this.getHeigth() - 300);
        decisionBoxTextField.setTranslateX((this.getWidth() / 150));
        decisionBoxTextField.setTranslateY(this.getHeigth() - 300);
        decisionBoxTextField.setPrefSize(50, 15);
            
        decisionBoxTextField.requestFocus();
        askPane.getChildren().addAll(askComputerButton, decisionBoxTextField,
                yesBtn, gofishBtn, computerLabel, playerLabel);
        //This is for the ask button
        askComputerButton.setOnMouseClicked(e -> {

        });//ASK BUTTON
        //set gamePane click action
        deckPane.setOnMouseClicked(e -> {
//        human.getCardToHandFromDeck();
//        displayCards(playerPane, human, "human");
        });//DECK PANE LEFT SIDE
        yesBtn.setOnMouseClicked(e -> {

        });//YES BUTTON
        gofishBtn.setOnMouseClicked(e -> {

        });
        gamePane.setLeft(deckPane);//DECK ON LEFT
        gamePane.setCenter(askPane);//THE INFORMATION AND ASK BUTTONS IN CENTER
        gamePane.setBottom(playerPane);//THE PLAYER 
        gamePane.setTop(computerPane);

        this.getMainPane().setCenter(gamePane);
    }

    /**
     * Initializes the players with on screen cards, for later
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
    public void displayCards(StackPane pane, Player player, String type) {
//        if(type.equals("computer")){
//            player.flipHand(player.getHand());
//        }
        System.out.println("InsideDsplay cards");
        pane.getChildren().clear();
        int count = (-this.getWidth() / 2) + 150;
        for (Card card : player.getHand()) {
            if (player.getHandSize() > 10) {
                card.getImage().setTranslateX(count / 3);
            } else {
                card.getImage().setTranslateX(count);
            }
            pane.setAlignment(Pos.CENTER);
//            pane.setPadding(new Insets(0,0,0,100));
            pane.getChildren().add(card.getImage());
//            pane.setAlignment(Pos.CENTER);
            count += 50;

//        System.out.println("Count"+ count);
        }

    }

    public void updateDeck(Pane pane, Player player) {
        System.out.println("Inside updateDeck");
        pane.getChildren().clear();
//        player.getDeck().flipDeck(false);
        int count = 1;
        for (Card card : player.getDeck().getCards()) {
//card.getImage().setTranslateX(count/3);
            pane.getChildren().add(card.getImage());
            count += 10;
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
     *
     * @return deck
     */
    public Pane getDeckPane() {
        return this.deckPane;
    }

    /**
     * This returns the main game pane
     *
     * @return pane with game
     */
    public BorderPane getGamePane() {
        return this.gamePane;
    }

    public void setAboutPane(BorderPane about) {
        this.aboutPane = about;
    }

    /**
     * This returns the about pane with htlm
     *
     * @return about
     */
    public BorderPane getAboutPane() {
        return this.aboutPane;
    }

    public void setSplashScreen(Pane splashScreenPane) {
        this.splashScreenPane = splashScreenPane;
    }

    public Pane getSplashScreenPane() {
        return this.splashScreenPane;
    }

    /**
     * Randomly chooses who starts
     *
     * @return player or computer
     */
    public String whoStarts() {
        String[] player = {"player", "computer"};
        int who = new Random().nextInt(1);
        return player[who];
    }

    public void setMainPane(BorderPane mainPane) {
        this.mainPane = mainPane;
    }

    public BorderPane getInstance() {
        if (mainPane == null) {
            mainPane = new BorderPane();

        }
        return mainPane;
    }
/**
 * Game play
 * @param playerType who starts first 
 */
    public void gamePlay(String playerType) {
        while (!isOver) {
            if ("computer".equals(playerType)) {
                System.out.println("If computer: " + playerType);
                computer.setIsTurn(true);
                human.setIsTurn(false);
            } else {
                System.out.println("If Player: " + playerType);
                computer.setIsTurn(false);
                human.setIsTurn(true);
            }
            while (human.isTurn()) {
                System.out.println("Inside player");
                playerLabel.setText("Please input guess from hand and press \"Ask\".");
                askComputerButton.setOnMouseClicked(e -> {
                    eventHandler.requestToAskButton();
                });
                human.setIsTurn(false);
            }
            while (computer.isTurn()) {
                System.out.println("Inside computer");

            }
            isOver = true;
        }
    }

    public StackPane getPlayerPane() {
        return playerPane;
    }

    public StackPane getComputerPane() {
        return computerPane;
    }

    public Pane getAskPane() {
        return askPane;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public int getPaneWidth() {
        return paneWidth;
    }

    public int getPaneHeight() {
        return paneHeight;
    }

    public Button getPlay() {
        return play;
    }

    public Button getExit() {
        return exit;
    }

    public Button getAbout() {
        return about;
    }

    public Button getAskComputerButton() {
        return askComputerButton;
    }

    public Button getYesBtn() {
        return yesBtn;
    }

    public Button getGofishBtn() {
        return gofishBtn;
    }

    public TextField getDecisionBoxTextField() {
        return decisionBoxTextField;
    }

    public Player getHuman() {
        return human;
    }

    public Computer getComputer() {
        return computer;
    }

    public Label getComputerLabel() {
        return computerLabel;
    }

    public Label getPlayerLabel() {
        return playerLabel;
    }

    public int getNumberOfCardsPerPlayer() {
        return numberOfCardsPerPlayer;
    }

    public EventHandler getEventHandler() {
        return eventHandler;
    }

    public WebView getBrowser() {
        return browser;
    }

    public WebEngine getWebEngine() {
        return webEngine;
    }

    public String getWhoseTurn() {
        return whoseTurn;
    }

    public boolean isIsOver() {
        return isOver;
    }
}
