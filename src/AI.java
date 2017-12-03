import javafx.animation.PauseTransition;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

class AI {

    private static int DACount = 5;
    private static int SWCount = 5;
    private static int MSCount = 3;
    private static int WACount = 3;
    private static int HACount = 2;
    private static int LSCount = 2;
    private static int ARCount = 2;
    private static int SHCount = 2;
    private static int CRCount = 0;
    private static int total = 25;

    private static PauseTransition showPause;

    static int getCRCount() {
        return CRCount;
    }
    static void setCRCount(int count) {
        CRCount = count;
    }

    private static String selectRandomCardID() {
        int choice = (int) (Math.random() * 9);
        if (choice == 0) return "DA";
        else if (choice == 1) return "SW";
        else if (choice == 2) return "MS";
        else if (choice == 3) return "WA";
        else if (choice == 4) return "HA";
        else if (choice == 5) return "LS";
        else if (choice == 6) return "AR";
        else if (choice == 7) return "SH";
        else return "CR";
    }

    private static String selectRandomCommand() {
        int choice = (int) (Math.random() * 5);
        if (choice > 0) return "AT";
        else return "DC";
    }

    static void createRandomDeck(ArrayList<ArrayList<Cards>> deck) {
        for (int i = 0; i < 5; i++) {
            ArrayList<Cards> pile = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                if (total == 5 && CRCount == 0) {
                    pile.add(new Crown());
                    CRCount++;
                    total--;
                }
                else {
                    pickCard(pile, j);
                    total--;
                }
            }
            deck.add(i, pile);
        }
    }

    private static void pickCard(ArrayList<Cards> pile, int j) {
        String card = selectRandomCardID();
        if (card.equals("DA")) {
            if (DACount == 0) pickCard(pile, j);
            else {
                pile.add(new Dagger());
                DACount--;
            }
        }
        else if (card.equals("SW")) {
            if (SWCount == 0) pickCard(pile, j);
            else {
                pile.add(new Sword());
                SWCount--;
            }
        }
        else if (card.equals("MS")) {
            if (MSCount == 0) pickCard(pile, j);
            else {
                pile.add(new MorningStar());
                MSCount--;
            }
        }
        else if (card.equals("WA")) {
            if (WACount == 0) pickCard(pile, j);
            else {
                pile.add(new WarAxe());
                WACount--;
            }
        }
        else if (card.equals("HA")) {
            if (HACount == 0) pickCard(pile, j);
            else {
                pile.add(new Halberd());
                HACount--;
            }
        }
        else if (card.equals("LS")) {
            if (LSCount == 0) pickCard(pile, j);
            else {
                pile.add(new LongSword());
                LSCount--;
            }
        }
        else if (card.equals("AR")) {
            if (ARCount == 0) pickCard(pile, j);
            else {
                pile.add(new Archer());
                ARCount--;
            }
        }
        else if (card.equals("SH")) {
            if (SHCount == 0) pickCard(pile, j);
            else {
                pile.add(new Shield());
                SHCount--;
            }
        }
        else if (card.equals("CR")) {
            if (CRCount == 1 || j != 0) pickCard(pile, j);
            else {
                pile.add(new Crown());
                CRCount++;
            }
        }
    }

    static void opponentTurn(ArrayList<ArrayList<Cards>> OpponentDeck, ArrayList<ArrayList<Cards>> PlayerDeck,
                             Button beginTurnButton, Pane mainPane, Text displayText) {
        int selectedPileNumber = (int) (Math.random() * 5);
        ArrayList<Cards> selectedPile = OpponentDeck.get(selectedPileNumber);
        if (selectedPile.isEmpty()) {
            opponentTurn(OpponentDeck, PlayerDeck, beginTurnButton, mainPane, displayText);
        }
        else {
            displayText.setText("The opponent is making its move...");
            showPause = new PauseTransition(Duration.seconds(2));
            showPause.setOnFinished(event -> opponentCommand(selectedPile, OpponentDeck, PlayerDeck, beginTurnButton,
                    mainPane, displayText));
            showPause.play();
        }
    }

    private static void opponentCommand(ArrayList<Cards> selectedPile, ArrayList<ArrayList<Cards>> OpponentDeck,
                                        ArrayList<ArrayList<Cards>> PlayerDeck,  Button beginTurnButton, Pane mainPane, Text displayText) {
        String command = selectRandomCommand();
        Cards selectedCard = selectedPile.get(selectedPile.size() - 1);
        if (command.equals("AT")) {
            if (selectedCard.equals("SH")) {
                opponentTurn(OpponentDeck, PlayerDeck, beginTurnButton, mainPane, displayText);
            }
            else playerPile(selectedPile, selectedCard, PlayerDeck, beginTurnButton, mainPane, displayText);
        }
        else {
            if (selectedCard.equals("CR")) {
                opponentTurn(OpponentDeck, PlayerDeck, beginTurnButton, mainPane, displayText);
            }
            else {
                showPause = new PauseTransition(Duration.seconds(1));
                showPause.setOnFinished(event -> {
                    Moves.discard(selectedCard, mainPane);
                    selectedPile.remove(selectedCard);
                    displayText.setText("The opponent has discarded a card from his pile.");
                    beginTurnButton.setDisable(false);
                });
                showPause.play();
            }
        }
    }

    private static void playerPile(ArrayList<Cards> selectedPile, Cards selectedCard, ArrayList<ArrayList<Cards>> PlayerDeck, Button beginTurnButton, Pane mainPane, Text displayText) {
        ArrayList<Cards> targetPile = PlayerDeck.get((int) (Math.random() * 5));
        if (targetPile.isEmpty()) {
            playerPile(selectedPile, selectedCard, PlayerDeck, beginTurnButton, mainPane, displayText);
        }
        else {
            Cards targetCard = targetPile.get(targetPile.size() - 1);
            selectedCard.showCard();
            selectedCard.setStroke(Color.BLUE);
            targetCard.setStroke(Color.RED);
            displayText.setText("The opponent is making its attack...");
            showPause = new PauseTransition(Duration.seconds(2));
            showPause.setOnFinished(event -> {
                Moves.attack(selectedCard, targetCard, mainPane);
                if (selectedCard.getStatus() == -1 && targetCard.getStatus() == -1) {
                    selectedPile.remove(selectedCard);
                    targetPile.remove(targetCard);
                    if (!(targetPile.isEmpty())) {
                        Cards newCard = targetPile.get(targetPile.size() - 1);
                        newCard.showCard();
                    }
                    displayText.setText("The opponent's " + selectedCard.getName() + " card and your " + targetCard.getName() + " card have both been discarded.");
                }
                else if (selectedCard.getStatus() == -1) {
                    selectedPile.remove(selectedCard);
                    targetCard.setStroke(Color.BLACK);
                    displayText.setText("The opponent's " + selectedCard.getName() + " card has been defeated by your " + targetCard.getName() + " card.");
                    if (selectedCard.equals("CR")) CRCount--;
                }
                else if (targetCard.getStatus() == -1) {
                    targetPile.remove(targetCard);
                    selectedCard.hideCard();
                    selectedCard.setStroke(Color.BLACK);
                    if (!(targetPile.isEmpty())) {
                        Cards newCard = targetPile.get(targetPile.size() - 1);
                        newCard.showCard();
                    }
                    displayText.setText("The opponent's " + selectedCard.getName() + " card has successfully killed your " + targetCard.getName() + " card.");
                    if (targetCard.equals("CR")) Player.setCRCount(0);
                }
                else {
                    selectedCard.hideCard();
                    selectedCard.setStroke(Color.BLACK);
                    targetCard.setStroke(Color.BLACK);
                    displayText.setText("Neither the opponent's " + selectedCard.getName() + " card nor your " + targetCard.getName() + " card were discarded.");
                }
                beginTurnButton.setDisable(false);
                endGameCheck(beginTurnButton);
            });
            showPause.play();
        }
    }

    private static void endGameCheck(Button beginTurnButton) {
        if (CRCount == 0 || Player.getCRCount() == 0) {
            Stage endGame = new Stage();
            endGame.setTitle("Game Over");
            endGame.initModality(Modality.APPLICATION_MODAL);
            Pane endGamePane = new Pane();
            Text endGameText = new Text();
            endGameText.setFont(new Font("Comic Sans MS", 64));
            endGameText.setWrappingWidth(600);
            endGameText.setX(20);
            endGameText.setY(100);
            endGameText.setTextAlignment(TextAlignment.CENTER);
            if (CRCount == 0) {
                endGameText.setText("The opponent's crown has died. You have won the game!");
            } else if (Player.getCRCount() == 0) {
                endGameText.setText("Your crown has died. You have lost the game.");
            }
            endGamePane.getChildren().add(endGameText);
            Scene endScene = new Scene(endGamePane, 640, 360);
            endGame.setScene(endScene);
            endGame.show();
            beginTurnButton.setDisable(true);
        }
    }

}
