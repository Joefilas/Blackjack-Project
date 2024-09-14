package tech.joelson.blackjack.model;

public enum Suits {

    CLUBS("Clubs"),
    DIAMONDS("Diamonds"),
    HEARTS("Hearts"),
    SPADES("Spades");

    private final String suitsAsString;

    Suits(String suitsAsString){
        this.suitsAsString = suitsAsString;
    }

    public String toString(){
        return suitsAsString;
    }
}
