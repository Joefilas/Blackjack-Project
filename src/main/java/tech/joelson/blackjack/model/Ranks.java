package tech.joelson.blackjack.model;

public enum Ranks {

    ACE(1,"Ace"),
    DEUCE(2,"Deuce"), TREY(3,"Trey"), FOUR(4,"Four"), FIVE(5,"Five"),
    SIX(6,"Six"), SEVEN(7,"Seven"), EIGHT(8,"Eight"), NINE(9,"Nine"),
    TEN(10,"Ten"), JACK(10,"Jack"), QUEEN(10,"Queen"), KING(10,"King");

    private final int ranksAsValue;
    private final String ranksAsString;

    Ranks(int ranksAsValue, String ranksAsString){
        this.ranksAsValue = ranksAsValue;
        this.ranksAsString = ranksAsString;
    }

    public int getRanksAsValue() {
        return ranksAsValue;
    }

    public String getRanksAsString() {
        return ranksAsString;
    }

    public String toString(){
        return String.valueOf(ranksAsString);
    }
}