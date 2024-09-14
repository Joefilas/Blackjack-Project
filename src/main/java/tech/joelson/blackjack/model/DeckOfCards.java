package tech.joelson.blackjack.model;


import java.util.ArrayList;
import java.util.Collections;

public class DeckOfCards {

    private final ArrayList<Card> deck;

    public DeckOfCards(){
        this.deck = new ArrayList<>();

        for(Suits suit :Suits.values()){
            for(Ranks rank: Ranks.values()){
                deck.add(new Card(suit,rank));
            }
        }
    }

    public void shuffleDeck(){
        Collections.shuffle(this.deck);
    }

    public Card removeCard(){
        return this.deck.remove(this.deck.size() - 1);
    }


    @Override
    public String toString() {
        StringBuilder listOfCards = new StringBuilder();
        for(Card card: deck){
            listOfCards.append(card).append("\n");
        }
        return listOfCards.toString();
    }



}

