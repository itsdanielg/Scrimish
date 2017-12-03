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
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.util.ArrayList;

class Player {

    private static final double BOTTOM_LIMIT = 490;
    private static final double WIDTH = ((double)790/9) - 10;

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

    private static Text DACounter = displayRemaining(DACount, 20);
    private static Text SWCounter = displayRemaining(SWCount, 20 + (WIDTH + 10));
    private static Text MSCounter = displayRemaining(MSCount, 20 + (WIDTH + 10) * 2);
    private static Text WACounter = displayRemaining(WACount, 20 + (WIDTH + 10) * 3);
    private static Text HACounter = displayRemaining(HACount, 20 + (WIDTH + 10) * 4);
    private static Text LSCounter = displayRemaining(LSCount, 20 + (WIDTH + 10) * 5);
    private static Text ARCounter = displayRemaining(ARCount, 20 + (WIDTH + 10) * 6);
    private static Text SHCounter = displayRemaining(SHCount, 20 + (WIDTH + 10) * 7);
    private static Text creationDisplayText = makeDisplayText();

    private static double xPos;
    private static double increment;
    private static int pileCounter;
    private static int cardCounter;

    private static Text displayRemaining(int counter, double xPos) {
        Text displayRemaining = new Text(counter + "x");
        displayRemaining.setFont(new Font("Comic Sans MS",14));
        displayRemaining.setTranslateX(xPos);
        displayRemaining.setTranslateY(155);
        displayRemaining.setWrappingWidth(WIDTH);
        displayRemaining.setTextAlignment(TextAlignment.CENTER);
        return displayRemaining;
    }

    private static Text makeDisplayText() {
        Text creationDisplayText = new Text();
        creationDisplayText.setFont(new Font("Comic Sans MS",20));
        creationDisplayText.setTranslateX(0);
        creationDisplayText.setTranslateY(343);
        creationDisplayText.setWrappingWidth(820);
        creationDisplayText.setTextAlignment(TextAlignment.CENTER);
        return creationDisplayText;
    }

    private static String displayCounter(int pileCounter, int cardCounter) {
        String numberOne;
        String numberTwo;
        if (cardCounter == 1) numberOne = "1st";
        else if (cardCounter == 2) numberOne = "2nd";
        else if (cardCounter == 3) numberOne = "3rd";
        else if (cardCounter == 4) numberOne = "4th";
        else numberOne = "5th";
        if (pileCounter == 1) numberTwo = "1st";
        else if (pileCounter == 2) numberTwo = "2nd";
        else if (pileCounter == 3) numberTwo = "3rd";
        else if (pileCounter == 4) numberTwo = "4th";
        else numberTwo = "5th";
        return ("Enter the " + numberOne + " card on the " + numberTwo + " pile.");
    }

    static int getCRCount() {
        return CRCount;
    }
    static void setCRCount(int count) {
        CRCount = count;
    }
    private static void resetCounts() {
        DACount = 5;
        SWCount = 5;
        MSCount = 3;
        WACount = 3;
        HACount = 2;
        LSCount = 2;
        ARCount = 2;
        SHCount = 2;
        CRCount = 0;
    }

    static void createPlayerPile(Stage viewPile, Pane selectPane, ArrayList<Cards> optionDeck, ArrayList<Cards> pile) {
        pile.clear();
        resetCounts();
        pileCounter = 1;
        cardCounter = 1;
        increment = 0;
        xPos = 20;
        selectPane.getChildren().addAll(DACounter, SWCounter, MSCounter, WACounter, HACounter, LSCounter, ARCounter,
                SHCounter, creationDisplayText);
        creationDisplayText.setText(displayCounter(pileCounter, cardCounter));
        for (Cards card : optionDeck) {
            card.setOnMouseClicked(event -> {
                if (card.equals("CR") && cardCounter != 1) creationDisplayText.setText("A Crown can only be placed " +
                        "at the bottom of a pile. " + displayCounter(pileCounter, cardCounter));
                else {
                    total--;
                    pickCard(card, pile, selectPane);
                    Cards cardCopy = clone(card);
                    cardCopy.setWidth(140);
                    cardCopy.setHeight(210);
                    cardCopy.showCard();
                    cardCopy.setTranslateX(xPos);
                    cardCopy.setTranslateY(BOTTOM_LIMIT - increment);
                    selectPane.getChildren().add(cardCopy);
                    increment += 30;
                    cardCounter++;
                    if (cardCounter == 6) {
                        xPos += 160;
                        increment = 0;
                        cardCounter = 1;
                        pileCounter++;
                    }
                    if (total == 5 && CRCount == 0) {
                        total--;
                        CRCount++;
                        pile.add(new Crown());
                        selectPane.getChildren().remove(optionDeck.get(8));
                        Cards crownCopy = new Crown();
                        crownCopy.setWidth(140);
                        crownCopy.setHeight(210);
                        crownCopy.showCard();
                        crownCopy.setTranslateX(xPos);
                        crownCopy.setTranslateY(BOTTOM_LIMIT - increment);
                        selectPane.getChildren().add(crownCopy);
                        creationDisplayText.setText("Crown has been automatically added. Enter the 2nd card on " +
                                "the 5th pile.");
                        increment = 30;
                        cardCounter = 2;
                    } else creationDisplayText.setText(displayCounter(pileCounter, cardCounter));
                }
                if (total == 0) {
                    selectPane.getChildren().removeAll(DACounter, SWCounter, MSCounter, WACounter, HACounter,
                            LSCounter, ARCounter, SHCounter);
                    creationDisplayText.setText("Deck Creation complete.");
                    Button closeWindow = new Button("Close Window \n" +
                            "to Finish Deck Creation");
                    closeWindow.setTextAlignment(TextAlignment.CENTER);
                    closeWindow.setPrefWidth(380);
                    closeWindow.setPrefHeight(130);
                    closeWindow.setTranslateX(20);
                    closeWindow.setTranslateY(20);
                    closeWindow.setFont(new Font("Comic Sans MS",30));
                    closeWindow.setOnMouseClicked(e -> viewPile.fireEvent(new WindowEvent(viewPile,
                            WindowEvent.WINDOW_CLOSE_REQUEST)));
                    Button reset = new Button("Reset Deck");
                    reset.setPrefWidth(380);
                    reset.setPrefHeight(130);
                    reset.setTranslateX(420);
                    reset.setTranslateY(20);
                    reset.setFont(new Font("Comic Sans MS",48));
                    reset.setOnMouseClicked(e -> {
                        viewPile.close();
                    });
                    //fix later
                    reset.setDisable(true);
                    selectPane.getChildren().addAll(closeWindow, reset);
                }
            });
        }

    }

    private static void pickCard(Cards card, ArrayList<Cards> pile, Pane selectPane) {
        if (card.equals("DA")) {
            DACount--;
            pile.add(new Dagger());
            DACounter.setText(DACount + "x");
            if (DACount == 0) selectPane.getChildren().remove(card);
        }
        else if (card.equals("SW")) {
            SWCount--;
            pile.add(new Sword());
            SWCounter.setText(SWCount + "x");
            if (SWCount == 0) selectPane.getChildren().remove(card);
        }
        else if (card.equals("MS")) {
            MSCount--;
            pile.add(new MorningStar());
            MSCounter.setText(MSCount + "x");
            if (MSCount == 0) selectPane.getChildren().remove(card);
        }
        else if (card.equals("WA")) {
            WACount--;
            pile.add(new WarAxe());
            WACounter.setText(WACount + "x");
            if (WACount == 0) selectPane.getChildren().remove(card);
        }
        else if (card.equals("HA")) {
            HACount--;
            pile.add(new Halberd());
            HACounter.setText(HACount + "x");
            if (HACount == 0) selectPane.getChildren().remove(card);
        }
        else if (card.equals("LS")) {
            LSCount--;
            pile.add(new LongSword());
            LSCounter.setText(LSCount + "x");
            if (LSCount == 0) selectPane.getChildren().remove(card);
        }
        else if (card.equals("AR")) {
            ARCount--;
            pile.add(new Archer());
            ARCounter.setText(ARCount + "x");
            if (ARCount == 0) selectPane.getChildren().remove(card);
        }
        else if (card.equals("SH")) {
            SHCount--;
            pile.add(new Shield());
            SHCounter.setText(SHCount + "x");
            if (SHCount == 0) selectPane.getChildren().remove(card);
        }
        else {
            CRCount++;
            pile.add(new Crown());
            selectPane.getChildren().remove(card);
        }
    }

    static void playerCommand(ArrayList<ArrayList<Cards>> myDeck, ArrayList<ArrayList<Cards>> oppDeck,
                              Button attackButton, Button discardButton, Button endTurnButton, Pane mainPane,
                              Text displayText) {
        displayText.setText("Select a pile from your deck.");
        for (ArrayList<Cards> pile : myDeck) {
            if (!(pile.isEmpty())) {
                Cards topCard = pile.get(pile.size() - 1);
                topCard.setOnMouseEntered(event -> topCard.setStroke(Color.RED));
                topCard.setOnMouseExited(event -> topCard.setStroke(Color.BLACK));
                topCard.setOnMouseClicked(event -> {
                    attackButton.setDisable(false);
                    attackButton.setOnMouseClicked(event1 -> opponentPile(myDeck, oppDeck, pile, topCard, attackButton,
                            discardButton, endTurnButton, mainPane, displayText));
                    discardButton.setDisable(false);
                    discardButton.setOnMouseClicked(e -> {
                        Moves.discard(topCard, mainPane);
                        pile.remove(topCard);
                        if (!(pile.isEmpty())) {
                            Cards newCard = pile.get(pile.size() - 1);
                            newCard.showCard();
                        }
                        displayText.setText("You have discarded your " + topCard.getName() + " card.");
                        endTurn(myDeck, oppDeck, attackButton, discardButton);
                        endTurnButton.setDisable(false);
                    });
                    if (topCard.equals("CR")) {
                        displayText.setText("Crown selected. You may only attack.");
                        discardButton.setDisable(true);
                    } else if (topCard.equals("SH")) {
                        displayText.setText("Shield selected. You may only discard.");
                        attackButton.setDisable(true);
                    } else displayText.setText(topCard.getName() + " selected. You may attack or discard.");
                });
            }
        }
    }


    private static void opponentPile(ArrayList<ArrayList<Cards>> myDeck, ArrayList<ArrayList<Cards>> oppDeck,
                                    ArrayList<Cards> myPile, Cards topCard, Button attackButton, Button discardButton,
                                    Button endTurnButton, Pane mainPane, Text displayText) {
        displayText.setText("Select a pile from the opponent's deck");
        for (ArrayList<Cards> pile : oppDeck) {
            if (!(pile.isEmpty())) {
                Cards oppTopCard = pile.get(pile.size() - 1);
                oppTopCard.setOnMouseEntered(event -> oppTopCard.setStroke(Color.BLUE));
                oppTopCard.setOnMouseExited(event -> oppTopCard.setStroke(Color.BLACK));
                oppTopCard.setOnMouseClicked(event -> {
                    endTurn(myDeck, oppDeck, attackButton, discardButton);
                    topCard.setStroke(Color.RED);
                    oppTopCard.setStroke(Color.BLUE);
                    oppTopCard.showCard();
                    PauseTransition showPause = new PauseTransition(Duration.seconds(2));
                    showPause.setOnFinished(e -> {
                        Moves.attack(topCard, oppTopCard, mainPane);
                        if (topCard.getStatus() == -1 && oppTopCard.getStatus() == -1) {
                            myPile.remove(topCard);
                            if (!(myPile.isEmpty())) {
                                Cards newCard = myPile.get(myPile.size() - 1);
                                newCard.showCard();
                            }
                            pile.remove(oppTopCard);
                            displayText.setText("Your " + topCard.getName() + " card and the opponent's " +
                                    oppTopCard.getName() + " card have both been discarded.");
                        } else if (topCard.getStatus() == -1) {
                            oppTopCard.hideCard();
                            oppTopCard.setStroke(Color.BLACK);
                            myPile.remove(topCard);
                            if (!(myPile.isEmpty())) {
                                Cards newCard = myPile.get(myPile.size() - 1);
                                newCard.showCard();
                            }
                            displayText.setText("Your " + topCard.getName() + " card has been defeated by the " +
                                    "opponent's " + oppTopCard.getName() + " card.");
                            if (topCard.equals("CR")) CRCount--;
                        } else if (oppTopCard.getStatus() == -1) {
                            pile.remove(oppTopCard);
                            topCard.setStroke(Color.BLACK);
                            displayText.setText("Your " + topCard.getName() + " card has successfully killed the " +
                                    "opponent's " + oppTopCard.getName() + " card.");
                            if (oppTopCard.equals("CR")) AI.setCRCount(0);
                        } else {
                            oppTopCard.hideCard();
                            oppTopCard.setStroke(Color.BLACK);
                            displayText.setText("Neither your " + topCard.getName() + " card nor the opponent's " +
                                    oppTopCard.getName() + " card were discarded.");
                        }
                        endTurnButton.setDisable(false);
                        endGameCheck(endTurnButton);
                    });
                    showPause.play();
                });
            }
        }

    }

    private static void endTurn(ArrayList<ArrayList<Cards>> myDeck, ArrayList<ArrayList<Cards>> oppDeck,
                                Button attackButton, Button discardButton) {
        attackButton.setDisable(true);
        discardButton.setDisable(true);
        for (ArrayList<Cards> playerPile : myDeck) {
            if (!(playerPile.isEmpty())) {
                Cards myCard = playerPile.get(playerPile.size() - 1);
                myCard.setOnMouseEntered(null);
                myCard.setOnMouseClicked(null);
            }
        }
        for (ArrayList<Cards> oppPile : oppDeck) {
            if (!(oppPile.isEmpty())) {
                Cards oppCard = oppPile.get(oppPile.size() - 1);
                oppCard.setOnMouseEntered(null);
                oppCard.setOnMouseClicked(null);
            }
        }
    }

    static Cards clone(Cards card) {
        if (card.equals("DA")) return new Dagger();
        else if (card.equals("SW")) return new Sword();
        else if (card.equals("MS")) return new MorningStar();
        else if (card.equals("WA")) return new WarAxe();
        else if (card.equals("HA")) return new Halberd();
        else if (card.equals("LS")) return new LongSword();
        else if (card.equals("AR")) return new Archer();
        else if (card.equals("SH")) return new Shield();
        else return new Crown();
    }

    private static void endGameCheck(Button endTurnButton) {
        if (CRCount == 0 || AI.getCRCount() == 0) {
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
                endGameText.setText("Your crown has died. You have lost the game.");
            } else if (AI.getCRCount() == 0) {
                endGameText.setText("The opponent's crown has died. You have won the game!");
            }
            endGamePane.getChildren().add(endGameText);
            Scene endScene = new Scene(endGamePane, 640, 360);
            endGame.setScene(endScene);
            endGame.show();
            endTurnButton.setDisable(true);
        }
    }

}