import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

abstract class Cards extends Rectangle{

    static final double CARD_WIDTH = 140;
    static final double CARD_HEIGHT = 210;
    static final int ALIVE = 1;

    int value;
    String type;
    String ID;
    int status;
    String name;
    boolean show = false;

    abstract int getValue();
    abstract String getType();
    abstract String getID();
    abstract int getStatus();
    abstract void setStatus(int status);
    abstract String getName();
    abstract boolean equals(String ID);

    abstract void moveCard(double x, double y);
    abstract void showCard();
    abstract void hideCard();

}

//#1 Dagger Card
class Dagger extends Cards {

    private static final String DAGGER_PATH = "file:/C:/Users/Daniel/IdeaProjects/Scrimish (with GUI)/src/Images/Dagger.jpg";
    private static final Image IMAGE = new Image(DAGGER_PATH);

    Dagger() {
        this.value = 1;
        this.type = "NORMAL";
        this.ID = "DA";
        this.status = ALIVE;
        this.name = "Dagger";
        setWidth(CARD_WIDTH);
        setHeight(CARD_HEIGHT);
        setFill(Color.BLUE);
        setStroke(Color.BLACK);
        setStrokeWidth(2);
    }

    int getValue() {
        return value;
    }
    String getType() {
        return type;
    }
    String getID() {
        return ID;
    }
    int getStatus() {
        return status;
    }
    void setStatus(int status) {
        this.status = status;
    }
    String getName() {
        return name;
    }
    boolean equals(String ID) {
        return (ID.equals(getID()));
    }

    void moveCard(double x, double y)  {
        setTranslateX(x);
        setTranslateY(y);
    }
    void showCard() {
        show = true;
        setFill(new ImagePattern(IMAGE));
    }
    void hideCard() {
        show = false;
        setFill(Color.RED);
    }

}

//#2 Sword Card
class Sword extends Cards {

    private static final String SWORD_PATH = "file:/C:/Users/Daniel/IdeaProjects/Scrimish (with GUI)/src/Images/Sword.jpg";
    private static final Image IMAGE = new Image(SWORD_PATH);

    Sword() {
        this.value = 2;
        this.type = "NORMAL";
        this.ID = "SW";
        this.status = ALIVE;
        this.name = "Sword";
        setWidth(CARD_WIDTH);
        setHeight(CARD_HEIGHT);
        setFill(Color.BLUE);
        setStroke(Color.BLACK);
        setStrokeWidth(2);
    }

    int getValue() {
        return value;
    }
    String getType() {
        return type;
    }
    String getID() {
        return ID;
    }
    int getStatus() {
        return status;
    }
    void setStatus(int status) {
        this.status = status;
    }
    String getName() {
        return name;
    }
    boolean equals(String ID) {
        return (ID.equals(getID()));
    }

    void moveCard(double x, double y)  {
        setTranslateX(x);
        setTranslateY(y);
    }
    void showCard() {
        show = true;
        setFill(new ImagePattern(IMAGE));
    }
    void hideCard() {
        show = false;
        setFill(Color.RED);
    }

}

//#3 Morning Star Card
class MorningStar extends Cards {

    private static final String MORNINGSTAR_PATH = "file:/C:/Users/Daniel/IdeaProjects/Scrimish (with GUI)/src/Images/Morning Star.jpg";
    private static final Image IMAGE = new Image(MORNINGSTAR_PATH);

    MorningStar() {
        this.value = 3;
        this.type = "NORMAL";
        this.ID = "MS";
        this.status = ALIVE;
        this.name = "Morning Star";
        setWidth(CARD_WIDTH);
        setHeight(CARD_HEIGHT);
        setFill(Color.BLUE);
        setStroke(Color.BLACK);
        setStrokeWidth(2);
    }

    int getValue() {
        return value;
    }
    String getType() {
        return type;
    }
    String getID() {
        return ID;
    }
    int getStatus() {
        return status;
    }
    void setStatus(int status) {
        this.status = status;
    }
    String getName() {
        return name;
    }
    boolean equals(String ID) {
        return (ID.equals(getID()));
    }

    void moveCard(double x, double y)  {
        setTranslateX(x);
        setTranslateY(y);
    }
    void showCard() {
        show = true;
        setFill(new ImagePattern(IMAGE));
    }
    void hideCard() {
        show = false;
        setFill(Color.RED);
    }

}

//#4 War Axe Card
class WarAxe extends Cards {

    private static final String WARAXE_PATH = "file:/C:/Users/Daniel/IdeaProjects/Scrimish (with GUI)/src/Images/War Axe.jpg";
    private static final Image IMAGE = new Image(WARAXE_PATH);

    WarAxe() {
        this.value = 4;
        this.type = "NORMAL";
        this.ID = "WA";
        this.status = ALIVE;
        this.name = "War Axe";
        setWidth(CARD_WIDTH);
        setHeight(CARD_HEIGHT);
        setFill(Color.BLUE);
        setStroke(Color.BLACK);
        setStrokeWidth(2);
    }

    int getValue() {
        return value;
    }
    String getType() {
        return type;
    }
    String getID() {
        return ID;
    }
    int getStatus() {
        return status;
    }
    void setStatus(int status) {
        this.status = status;
    }
    String getName() {
        return name;
    }
    boolean equals(String ID) {
        return (ID.equals(getID()));
    }

    void moveCard(double x, double y)  {
        setTranslateX(x);
        setTranslateY(y);
    }
    void showCard() {
        show = true;
        setFill(new ImagePattern(IMAGE));
    }
    void hideCard() {
        show = false;
        setFill(Color.RED);
    }
}

//#5 Halberd Card
class Halberd extends Cards {

    private static final String HALBERD_PATH = "file:/C:/Users/Daniel/IdeaProjects/Scrimish (with GUI)/src/Images/Halberd.jpg";
    private static final Image IMAGE = new Image(HALBERD_PATH);

    Halberd() {
        this.value = 5;
        this.type = "NORMAL";
        this.ID = "HA";
        this.status = ALIVE;
        this.name = "Halberd";
        setWidth(CARD_WIDTH);
        setHeight(CARD_HEIGHT);
        setFill(Color.BLUE);
        setStroke(Color.BLACK);
        setStrokeWidth(2);
    }

    int getValue() {
        return value;
    }
    String getType() {
        return type;
    }
    String getID() {
        return ID;
    }
    int getStatus() {
        return status;
    }
    void setStatus(int status) {
        this.status = status;
    }
    String getName() {
        return name;
    }
    boolean equals(String ID) {
        return (ID.equals(getID()));
    }

    void moveCard(double x, double y)  {
        setTranslateX(x);
        setTranslateY(y);
    }
    void showCard() {
        show = true;
        setFill(new ImagePattern(IMAGE));
    }
    void hideCard() {
        show = false;
        setFill(Color.RED);
    }
}

//#6 Longsword Card
class LongSword extends Cards {

    private static final String LONGSWORD_PATH = "file:/C:/Users/Daniel/IdeaProjects/Scrimish (with GUI)/src/Images/Longsword.jpg";
    private static final Image IMAGE = new Image(LONGSWORD_PATH);

    LongSword() {
        this.value = 6;
        this.type = "NORMAL";
        this.ID = "LS";
        this.status = ALIVE;
        this.name = "Longsword";
        setWidth(CARD_WIDTH);
        setHeight(CARD_HEIGHT);
        setFill(Color.BLUE);
        setStroke(Color.BLACK);
        setStrokeWidth(2);
    }

    int getValue() {
        return value;
    }
    String getType() {
        return type;
    }
    String getID() {
        return ID;
    }
    int getStatus() {
        return status;
    }
    void setStatus(int status) {
        this.status = status;
    }
    String getName() {
        return name;
    }
    boolean equals(String ID) {
        return (ID.equals(getID()));
    }

    void moveCard(double x, double y)  {
        setTranslateX(x);
        setTranslateY(y);
    }
    void showCard() {
        show = true;
        setFill(new ImagePattern(IMAGE));
    }
    void hideCard() {
        show = false;
        setFill(Color.RED);
    }
}

//Archer Card
class Archer extends Cards {

    private static final String ARCHER_PATH = "file:/C:/Users/Daniel/IdeaProjects/Scrimish (with GUI)/src/Images/Arrow.jpg";
    private static final Image IMAGE = new Image(ARCHER_PATH);

    Archer() {
        this.value = 0;
        this.type = "SPECIAL";
        this.ID = "AR";
        this.status = ALIVE;
        this.name = "Archer";
        setWidth(CARD_WIDTH);
        setHeight(CARD_HEIGHT);
        setFill(Color.BLUE);
        setStroke(Color.BLACK);
        setStrokeWidth(2);
    }

    int getValue() {
        return value;
    }
    String getType() {
        return type;
    }
    String getID() {
        return ID;
    }
    int getStatus() {
        return status;
    }
    void setStatus(int status) {
        this.status = status;
    }
    String getName() {
        return name;
    }
    boolean equals(String ID) {
        return (ID.equals(getID()));
    }

    void moveCard(double x, double y)  {
        setTranslateX(x);
        setTranslateY(y);
    }
    void showCard() {
        show = true;
        setFill(new ImagePattern(IMAGE));
    }
    void hideCard() {
        show = false;
        setFill(Color.RED);
    }
}

//Shield Card
class Shield extends Cards {

    private static final String SHIELD_PATH = "file:/C:/Users/Daniel/IdeaProjects/Scrimish (with GUI)/src/Images/Shield.jpg";
    private static final Image IMAGE = new Image(SHIELD_PATH);

    Shield() {
        this.value = 0;
        this.type = "SPECIAL";
        this.ID = "SH";
        this.status = ALIVE;
        this.name = "Shield";
        setWidth(CARD_WIDTH);
        setHeight(CARD_HEIGHT);
        setFill(Color.BLUE);
        setStroke(Color.BLACK);
        setStrokeWidth(2);
    }

    int getValue() {
        return value;
    }
    String getType() {
        return type;
    }
    String getID() {
        return ID;
    }
    int getStatus() {
        return status;
    }
    void setStatus(int status) {
        this.status = status;
    }
    String getName() {
        return name;
    }
    boolean equals(String ID) {
        return (ID.equals(getID()));
    }

    void moveCard(double x, double y)  {
        setTranslateX(x);
        setTranslateY(y);
    }
    void showCard() {
        show = true;
        setFill(new ImagePattern(IMAGE));
    }
    void hideCard() {
        show = false;
        setFill(Color.RED);
    }
}

//Crown Card
class Crown extends Cards {

    private static final String CROWN_PATH = "file:/C:/Users/Daniel/IdeaProjects/Scrimish (with GUI)/src/Images/Crown.jpg";
    private static final Image IMAGE = new Image(CROWN_PATH);

    Crown() {
        this.value = 0;
        this.type = "SPECIAL";
        this.ID = "CR";
        this.status = ALIVE;
        this.name = "Crown";
        setWidth(CARD_WIDTH);
        setHeight(CARD_HEIGHT);
        setFill(Color.BLUE);
        setStroke(Color.BLACK);
        setStrokeWidth(2);
    }

    int getValue() {
        return value;
    }
    String getType() {
        return type;
    }
    String getID() {
        return ID;
    }
    int getStatus() {
        return status;
    }
    void setStatus(int status) {
        this.status = status;
    }
    String getName() {
        return name;
    }
    boolean equals(String ID) {
        return (ID.equals(getID()));
    }

    void moveCard(double x, double y)  {
        setTranslateX(x);
        setTranslateY(y);
    }
    void showCard() {
        show = true;
        setFill(new ImagePattern(IMAGE));
    }
    void hideCard() {
        show = false;
        setFill(Color.RED);
    }
}