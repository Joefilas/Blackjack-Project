package tech.joelson.blackjack.model;

public class Card {

    private final Suits suit;
    private final Ranks rank;
    private boolean isFaceUp;

    public Card(Suits suit, Ranks rank){
        this.suit = suit;
        this.rank = rank;
        this.isFaceUp = true;
    }

    public boolean isFaceUp() {
        return isFaceUp;
    }

    public void setFaceUp(boolean faceUp) {
        isFaceUp = faceUp;
    }

    public int cardValue(){
        return rank.getRanksAsValue();
    }


    public Boolean isAce(){
        return rank.getRanksAsString().equals("Ace");
    }

    public String toString(){
        if(isFaceUp){
            return rank + " of " + suit;
        }
        return "Face down Card";
    }

}
