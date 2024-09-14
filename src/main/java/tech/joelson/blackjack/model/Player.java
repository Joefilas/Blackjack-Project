package tech.joelson.blackjack.model;

import java.util.ArrayList;

public class Player {

    private String name;
    private double money;

    private ArrayList<Card> playerHand;


    public Player(String name, int money){
        this.name = name;
        this.money = money;
        this.playerHand = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getMoney() {
        return money;
    }

    public ArrayList<Card> getHand() {
        return playerHand;
    }



    public String toString(){
        return this.name + " has " + "$"+this.money +" left.";
    }
}
