import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.web.WebView;

public class Main extends Application {

    private static final double UPPER_LIMIT = 20;
    private static final double BOTTOM_LIMIT = 490;
    private static boolean beginnerCheck = true;

    private ArrayList<ArrayList<Cards>> myDeck = new ArrayList<>();
    private ArrayList<ArrayList<Cards>> oppDeck = new ArrayList<>();

    private Stage mainWindow = new Stage();
    private Pane previewPane = new Pane();
    private Pane mainPane = new Pane();
    private Scene previewScene = new Scene(previewPane, 1280, 720);
    private Scene mainScene = new Scene(mainPane, 1280, 720);

    private Font CMS16 = new Font("Comic Sans MS", 16);
    private Button attackButton;
    private Button discardButton;
    private Button endTurnButton = endTurnButton();
    private Button beginTurnButton = beginTurnButton();
    private Text displayText = makeDisplayText();

    //Preview Windows
    @Override
    public void start(Stage mainWindow) throws Exception {
        this.mainWindow.setTitle("Scrimish");
        WebView howToVideo = YouTubeVideo.howToVideo();
        howToVideo.setTranslateX(640);
        howToVideo.setTranslateY(0);
        WebView walkthroughVideo = YouTubeVideo.walkthroughVideo();
        walkthroughVideo.setTranslateX(640);
        walkthroughVideo.setTranslateY(360);
        previewPane.getChildren().addAll(howToVideo, walkthroughVideo);
        displayRules();
        this.mainWindow.setScene(previewScene);
        Button startDeckCreationButton = startDeckCreationButton(myDeck);
        previewPane.getChildren().add(startDeckCreationButton);
        this.mainWindow.show();
    }

    private void displayRules() {
        Rectangle border = new Rectangle(620, 340);
        border.setStroke(Color.BLACK);
        border.setX(10);
        border.setY(180);
        border.setFill(Color.TRANSPARENT);
        Text title = new Text("Scrimish: An Addictive Strategy Card Game");
        title.setFont(new Font("Comic Sans MS", 28));
        title.setX(0);
        title.setY(40);
        title.setWrappingWidth(640);
        title.setTextAlignment(TextAlignment.CENTER);
        Text overview = new Text("Scrimish is a fast moving card game that pits player against player in an " +
                "epic 10 minute battle of strategy, memory, and misdirection. Every game is different and you'll " +
                "want to play over and over again to try new ways of outsmarting your opponent.");
        overview.setFont(CMS16);
        overview.setTranslateX(15);
        overview.setTranslateY(80);
        overview.setWrappingWidth(620);
        overview.setTextAlignment(TextAlignment.CENTER);
        Text objective = new Text("Objective: The object of the game is to uncover and attack your opponent's \n" +
                "                  Crown Card with one of your own cards.");
        objective.setFont(CMS16);
        objective.setTranslateX(15);
        objective.setTranslateY(200);
        Text setup = new Text("Setup: Each player places 5 piles of 5 cards each face down in front of them. The \n" +
                "           Crown Card should be hidden on the bottom of one of the 5 piles. The rest \n" +
                "           of the Cards may be arranged however you like, but choose carefully. A \n" +
                "           bad setup can quickly give your opponent the upper hand.");
        setup.setFont(CMS16);
        setup.setTranslateX(15);
        setup.setTranslateY(250);
        Text gamePlay = new Text("Gameplay: Players take turns attacking by selecting the top Card from one of \n" +
                "                their Piles and laying that Card in front of one their opponent's Piles. \n" +
                "                The defending player must then reveal the top Card of the Pile that  \n" +
                "                was attacked. The Card with the lowest number value loses and is \n" +
                "                discarded. The winning Card must be returned face down to the top of  \n" +
                "                Pile it was drawn from. If the two Cards have the same number value, \n" +
                "                both the original Cards are discarded. Play continues until one of the \n" +
                "                players attacks their opponent's Crown Card, winning the game.");
        gamePlay.setFont(CMS16);
        gamePlay.setTranslateX(15);
        gamePlay.setTranslateY(350);
        previewPane.getChildren().addAll(border, title, overview , objective, setup, gamePlay);
    }

    //Deck Creation Windows
    private Button startDeckCreationButton (ArrayList<ArrayList<Cards>> deck) {
        Button startDeckCreationButton = new Button("Start Deck Creation");
        startDeckCreationButton.setPrefWidth(620);
        startDeckCreationButton.setPrefHeight(160);
        startDeckCreationButton.setTranslateX(10);
        startDeckCreationButton.setTranslateY(540);
        startDeckCreationButton.setFont(new Font("Comic Sans MS",56));
        startDeckCreationButton.setOnMouseClicked(event -> {
            Stage viewPile = new Stage();
            viewPile.setTitle("Scrimish Deck Creation");
            viewPile.initModality(Modality.APPLICATION_MODAL);
            Pane selectPane = new Pane();
            double WIDTH = ((double)790/9) - 10;
            double HEIGHT = ((double)350/3);
            double xPos = 20;
            ArrayList<Cards> optionDeck = optionDeck();
            for (Cards card : optionDeck) {
                card.setWidth(WIDTH);
                card.setHeight(HEIGHT);
                card.moveCard(xPos, UPPER_LIMIT);
                card.showCard();
                card.setOnMouseEntered(e -> card.setStroke(Color.RED));
                card.setOnMouseExited(e -> card.setStroke(Color.BLACK));
                selectPane.getChildren().add(card);
                xPos += WIDTH + 10;
            }
            Rectangle specialCardsBox = new Rectangle(800, 125);
            specialCardsBox.setX(10);
            specialCardsBox.setY(175);
            specialCardsBox.setStroke(Color.BLACK);
            specialCardsBox.setFill(Color.TRANSPARENT);
            Text specialCards = new Text("Special Cards:\n" +
                    "Archer Card: If you attack with an Archer Card, it always wins. If your Archer Card is attacked, it always loses.\n" +
                    "Shield Card: Shield Cards cannot be used to attack. If your Shield Card is attacked, both your Shield Card and your \n" +
                    "                     opponent's attacking Card are discarded (except for Archer Cards, and both are instead returned).\n" +
                    "Crown Card: You can attack with your Crown Card. If you attack your opponent's Crown Card, you win. Otherwise, you \n" +
                    "                    lose the game.");
            specialCards.setFont(new Font("Comic Sans MS",14));
            specialCards.setTranslateX(15);
            specialCards.setTranslateY(192);
            selectPane.getChildren().addAll(specialCardsBox, specialCards);
            Scene scene = new Scene(selectPane, 820, 720);
            viewPile.setScene(scene);
            viewPile.show();
            ArrayList<Cards> pile = new ArrayList<>();
            Player.createPlayerPile(viewPile, selectPane, optionDeck, pile);
            viewPile.setOnCloseRequest(event1 -> {
                orderPile(deck, pile);
                previewPane.getChildren().remove(startDeckCreationButton);
                Button startGameButton = startGameButton();
                previewPane.getChildren().add(startGameButton);
            });
        });
        return startDeckCreationButton;
    }

    private ArrayList<Cards> optionDeck () {
        ArrayList<Cards> optionDeck = new ArrayList<>();
        optionDeck.add(new Dagger());
        optionDeck.add(new Sword());
        optionDeck.add(new MorningStar());
        optionDeck.add(new WarAxe());
        optionDeck.add(new Halberd());
        optionDeck.add(new LongSword());
        optionDeck.add(new Archer());
        optionDeck.add(new Shield());
        optionDeck.add(new Crown());
        return optionDeck;
    }

    private void orderPile(ArrayList<ArrayList<Cards>> deck, ArrayList<Cards> pile) {
        ArrayList<Cards> newPile = new ArrayList<>();
        int i = 0;
        for (Cards card : pile) {
            if (i % 5 == 0 && i != 0) {
                deck.add(newPile);
                newPile = new ArrayList<>();
            }
            newPile.add(card);
            i++;
            if (i == 25){
                deck.add(newPile);
            }
        }
    }

    private Button startGameButton() {
        Button startGameButton = new Button("Start Game");
        startGameButton.setPrefWidth(620);
        startGameButton.setPrefHeight(160);
        startGameButton.setTranslateX(10);
        startGameButton.setTranslateY(540);
        startGameButton.setFont(new Font("Comic Sans MS",64));
        startGameButton.setOnMouseClicked(event -> {
            selectGameMode();
            mainPane.getChildren().remove(startGameButton);
        });
        return startGameButton;
    }

    //Select Game Mode Windows
    private void selectGameMode() {
        Stage selectGameModeWindow = new Stage();
        selectGameModeWindow.initModality(Modality.APPLICATION_MODAL);
        selectGameModeWindow.setTitle("Select Game Mode");
        Pane selectGameModePane = new Pane();
        Text select = new Text("Select a Game Mode");
        select.setFont(new Font("Comic Sans MS",32));
        select.setTranslateX(150);
        select.setTranslateY(45);
        Text beginner = new Text("You may look at all the cards \n" +
                "in each of your 5 piles.");
        beginner.setFont(CMS16);
        beginner.setTranslateX(20);
        beginner.setTranslateY(115);
        Text advanced = new Text("You may look at only the top \n" +
                "card in each of your 5 piles");
        advanced.setFont(CMS16);
        advanced.setTranslateX(20);
        advanced.setTranslateY(235);
        Font buttonModeFont = new Font("Comic Sans MS",48);
        Button selectBeginner = new Button("Beginner");
        selectBeginner.setPrefWidth(330);
        selectBeginner.setPrefHeight(100);
        selectBeginner.setTranslateX(250);
        selectBeginner.setTranslateY(70);
        selectBeginner.setFont(buttonModeFont);
        selectBeginner.setOnMouseClicked(e -> {
            makeDeck("Player", myDeck);
            AI.createRandomDeck(oppDeck);
            makeDeck("AI", oppDeck);
            this.mainWindow.setScene(mainScene);
            selectGameModeWindow.close();
            startGame();
        });
        Button selectAdvanced = new Button("Advanced");
        selectAdvanced.setPrefWidth(330);
        selectAdvanced.setPrefHeight(100);
        selectAdvanced.setTranslateX(250);
        selectAdvanced.setTranslateY(190);
        selectAdvanced.setFont(buttonModeFont);
        selectAdvanced.setOnMouseClicked(e -> {
            beginnerCheck = false;
            makeDeck("Player", myDeck);
            AI.createRandomDeck(oppDeck);
            makeDeck("AI", oppDeck);
            this.mainWindow.setScene(mainScene);
            selectGameModeWindow.close();
            displayText.setTranslateY(355);
            startGame();
        });
        selectGameModePane.getChildren().addAll(select, beginner, advanced, selectBeginner, selectAdvanced);
        Scene scene = new Scene(selectGameModePane, 600, 310);
        selectGameModeWindow.setScene(scene);
        selectGameModeWindow.show();
    }

    //Main Game Windows
    private void makeDeck(String player, ArrayList<ArrayList<Cards>> deck) {
        double xPos = 250;
        for (ArrayList<Cards> pile : deck) {
            makePile(player, pile, xPos);
            xPos += 160;
        }
    }

    private void makePile(String player, ArrayList<Cards> pile, double xPos) {
        Cards topCard = pile.get(pile.size() - 1);
        double increment = 0;
        if (player.equals("AI")) {
            for (Cards card : pile) {
                card.hideCard();
                card.moveCard(xPos, UPPER_LIMIT + increment);
                increment += 15;
            }
        }
        else {
            for (Cards card : pile) {
                card.moveCard(xPos, BOTTOM_LIMIT - increment);
                increment += 15;
            }
            topCard.showCard();
            if (beginnerCheck) {
                Button showPileButton = showPileButton(pile, xPos);
                mainPane.getChildren().add(showPileButton);
            }
        }

        mainPane.getChildren().addAll(pile);
    }

    private Button showPileButton(ArrayList<Cards> pile, double xPos) {
        Button showPileButton = new Button("Show Pile");
        showPileButton.setFont(new Font("Comic Sans MS", 12));
        showPileButton.setPrefWidth(140);
        showPileButton.setPrefHeight(20);
        showPileButton.setTranslateX(xPos);
        showPileButton.setTranslateY(390);
        showPileButton.setOnMouseClicked(event -> {
            Stage viewPile = new Stage();
            viewPile.initModality(Modality.APPLICATION_MODAL);
            Pane viewPane = new Pane();
            double yPos = 520;
            for (Cards card : pile) {
                Cards cardCopy = Player.clone(card);
                cardCopy.showCard();
                cardCopy.moveCard(20, yPos);
                yPos -= 125;
                viewPane.getChildren().add(cardCopy);
                viewPile.setOnCloseRequest(e -> viewPane.getChildren().remove(cardCopy));
            }
            Scene scene = new Scene(viewPane, 180, 750);
            viewPile.setScene(scene);
            viewPile.show();
        });
        return showPileButton;
    }

    private Text makeDisplayText() {
        Text displayText = new Text();
        displayText.setFont(CMS16);
        displayText.setTranslateX(470);
        displayText.setTranslateY(335);
        displayText.setWrappingWidth(340);
        displayText.setTextAlignment(TextAlignment.CENTER);
        return displayText;
    }

    private Button endTurnButton() {
        Button endTurnButton = new Button("End Turn");
        endTurnButton.setFont(new Font("Comic Sans MS", 24));
        endTurnButton.setTranslateX(1050);
        endTurnButton.setTranslateY(310);
        endTurnButton.setPrefWidth(210);
        endTurnButton.setPrefHeight(100);
        endTurnButton.setOnMouseClicked(event -> {
            AI.opponentTurn(oppDeck, myDeck, beginTurnButton, mainPane, displayText);
            endTurnButton.setDisable(true);
        });
        endTurnButton.setDisable(true);
        return endTurnButton;
    }

    private Button beginTurnButton() {
        Button beginTurnButton = new Button("Begin Turn");
        beginTurnButton.setFont(new Font("Comic Sans MS", 24));
        beginTurnButton.setTranslateX(20);
        beginTurnButton.setTranslateY(310);
        beginTurnButton.setPrefWidth(210);
        beginTurnButton.setPrefHeight(100);
        beginTurnButton.setOnMouseClicked(event -> {
            Player.playerCommand(myDeck,oppDeck, attackButton, discardButton, endTurnButton, mainPane, displayText);
            beginTurnButton.setDisable(true);
        });
        beginTurnButton.setDisable(true);
        return beginTurnButton;
    }

    private void startGame() {
        attackButton = Moves.attackButton(beginnerCheck);
        attackButton.setDisable(true);
        discardButton = Moves.discardButton(beginnerCheck);
        discardButton.setDisable(true);
        mainPane.getChildren().addAll(attackButton, discardButton,endTurnButton, beginTurnButton, displayText);
        Player.playerCommand(myDeck,oppDeck, attackButton, discardButton, endTurnButton, mainPane, displayText);
    }

    public static void main(String[] args) {
        launch(args);
    }

}