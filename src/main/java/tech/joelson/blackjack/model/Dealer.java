package tech.joelson.blackjack.model;



import java.util.ArrayList;

public class Dealer {

    private final ArrayList<Card> dealerHand;


    public Dealer(){
        this.dealerHand = new ArrayList<>();
    }

    public ArrayList<Card> getHand() {
        return dealerHand;
    }

    public void drawCardDealer(DeckOfCards deck){
        Card cardDrawn = deck.removeCard();
        dealerHand.add(cardDrawn);
    }

    public void drawCardPlayer(DeckOfCards deck, Player player){
        Card cardDrawn = deck.removeCard();
        player.getHand().add(cardDrawn);
    }

    public void drawCardDealerFaceDown(DeckOfCards deck){
        Card drawnCard = deck.removeCard();
        drawnCard.setFaceUp(false);
        dealerHand.add(drawnCard);
    }

    public void flipFaceDownCard(){
        this.dealerHand.get(0).setFaceUp(true);
    }


}
