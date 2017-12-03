import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

class Moves {

    private static final int DEAD = -1;
    private static final int ALIVE = 1;

    static Button attackButton(boolean beginnerCheck) {
        Button attack = new Button("Attack");
        attack.setFont(new Font("Comic Sans MS", 24));
        attack.setTranslateX(250);
        attack.setTranslateY(310);
        attack.setPrefWidth(200);
        if (beginnerCheck) attack.setPrefHeight(60);
        else attack.setPrefHeight(100);
        return attack;
    }

    static Button discardButton(boolean beginnerCheck) {
        Button discard = new Button("Discard");
        discard.setFont(new Font("Comic Sans MS", 24));
        discard.setTranslateX(830);
        discard.setTranslateY(310);
        discard.setPrefWidth(200);
        if (beginnerCheck) discard.setPrefHeight(60);
        else discard.setPrefHeight(100);
        return discard;
    }

    static void attack(Cards selectedCard, Cards targetCard, Pane mainPane) {
        if (selectedCard.getType().equals("NORMAL")) {
            if (targetCard.getType().equals("NORMAL")) {
                if (selectedCard.getValue() > targetCard.getValue()) {
                    targetCard.setStatus(DEAD);
                    mainPane.getChildren().remove(targetCard);
                }
                else if (selectedCard.getValue() < targetCard.getValue()) {
                    selectedCard.setStatus(DEAD);
                    mainPane.getChildren().remove(selectedCard);
                }
                else {
                    selectedCard.setStatus(DEAD);
                    targetCard.setStatus(DEAD);
                    mainPane.getChildren().remove(selectedCard);
                    mainPane.getChildren().remove(targetCard);
                }
            }
            else {
                if (!(targetCard.getID().equals("SH"))) {
                    targetCard.setStatus(DEAD);
                    mainPane.getChildren().remove(targetCard);
                }
                else {
                    targetCard.setStatus(DEAD);
                    selectedCard.setStatus(DEAD);
                    mainPane.getChildren().remove(targetCard);
                    mainPane.getChildren().remove(selectedCard);
                }
            }
        }
        else {
            if (selectedCard.getID().equals("AR")) {
                if (!(targetCard.getID().equals("SH"))) {
                    targetCard.setStatus(DEAD);
                    mainPane.getChildren().remove(targetCard);
                }
                else {
                    selectedCard.setStatus(ALIVE);
                    targetCard.setStatus(ALIVE);
                }
            }
            else {
                if (!(targetCard.getID().equals("CR"))) {
                    selectedCard.setStatus(DEAD);
                    mainPane.getChildren().remove(selectedCard);
                }
                else {
                    targetCard.setStatus(DEAD);
                    mainPane.getChildren().remove(targetCard);
                }
            }
        }
    }

    //Discard command
    static void discard(Cards card, Pane mainPane) {
        mainPane.getChildren().remove(card);
    }

}
