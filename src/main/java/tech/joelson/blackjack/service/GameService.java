package tech.joelson.blackjack.service;

import org.springframework.stereotype.Service;
import tech.joelson.blackjack.model.Card;
import tech.joelson.blackjack.model.Dealer;
import tech.joelson.blackjack.model.DeckOfCards;
import tech.joelson.blackjack.model.Player;

import java.util.ArrayList;
import java.util.Scanner;

@Service
public class GameService {

    private int playerStake;
    private final Scanner scannerForInt = new Scanner(System.in);
    private final Scanner scannerForString = new Scanner(System.in);

    public void startGame(DeckOfCards deck, Player player, Dealer dealer) {
        this.roundStart(deck, player, dealer);
    }

    public void roundStart(DeckOfCards deck, Player player, Dealer dealer) {
        player.getHand().clear();
        dealer.getHand().clear();
        deck.shuffleDeck();

        while (true) {
            System.out.println("Choose your stake");
            this.playerStake = scannerForInt.nextInt();

            if (playerStake > player.getMoney()) {
                System.out.println("Stake exceeds balance");
                continue;
            }
            break;
        }

        dealer.drawCardPlayer(deck, player);
        dealer.drawCardPlayer(deck, player);
        dealer.drawCardDealerFaceDown(deck);
        dealer.drawCardDealer(deck);

        blackjackCheck(player, dealer);
        printCurrentHands(player, dealer);

        System.out.println("Make a decision");
        commands(deck, player, dealer);
    }

    public Card lastCardDrawn(ArrayList<Card> hand) {
        return hand.get(hand.size() - 1);
    }

    public void printCurrentHands(Player player, Dealer dealer) {
        printDealerHand(dealer);
        printPlayerHand(player);
    }

    public void printDealerHand(Dealer dealer) {
        System.out.println("Dealer has " + dealer.getHand() + " with value: " + valueOfHand(dealer.getHand()));
    }

    public void printPlayerHand(Player player) {
        System.out.println(player.getName() + " has " + player.getHand() + " with value: " + valueOfHand(player.getHand()));
    }

    public void playAgainOption(DeckOfCards deck, Player player, Dealer dealer) {
        String choice = scannerForString.nextLine();
        if (choice.equals("yes")) {
            this.startGame(deck, player, dealer);
        } else if (choice.equals("no")) {
            System.exit(0);
        } else {
            System.out.println("unrecognized command");
        }
    }

    public void roundPlayerWin(Player player) {
        player.setMoney(player.getMoney() + this.playerStake);
        System.out.println("You win $" + this.playerStake + " " + player + " Play Again? Type 'yes' if so.");
        playAgainOption(new DeckOfCards(), player, new Dealer());
    }

    public void roundPlayerLoss(Player player) {
        player.setMoney(player.getMoney() - this.playerStake);
        if (player.getMoney() == 0) {
            System.out.println("You lose $" + this.playerStake + "\nAnd you have run out of money. Game is OVER!");
            System.exit(0);
        }
        System.out.println("You lose $" + this.playerStake + " " + player + " Play Again? Type 'yes' if so.");
        playAgainOption(new DeckOfCards(), player, new Dealer());
    }

    public void roundPlayerPush(Player player) {
        System.out.println("Player and Dealer have equal cards, push!");
        System.out.println("You have $" + this.playerStake + " " + player + " Play Again? Type 'yes' if so.");
        playAgainOption(new DeckOfCards(), player, new Dealer());
    }

    public void commands(DeckOfCards deck, Player player, Dealer dealer) {
        String playerDecision = scannerForString.nextLine();
        switch (playerDecision) {
            case "stand" -> stand(deck, player, dealer);
            case "hit" -> hit(deck, player, dealer);
            case "double" -> doubleStake(deck, player, dealer);
            case "split" -> split();
            default -> {
                System.out.println("unrecognized command");
                commands(deck, player, dealer);
            }
        }
    }

    public void stand(DeckOfCards deck, Player player, Dealer dealer) {
        dealer.flipFaceDownCard();
        System.out.println("Hidden card is " + dealer.getHand().get(0));
        int valueOfPlayerHand = valueOfHand(player.getHand());
        int valueOfDealerHand = valueOfHand(dealer.getHand());

        while (valueOfDealerHand < 17) {
            dealer.drawCardDealer(deck);
            System.out.println("Dealer draws " + lastCardDrawn(dealer.getHand()));
            System.out.println("Hand of dealer: " + dealer.getHand());
            valueOfDealerHand = valueOfHand(dealer.getHand());
            System.out.println("Value of dealer hand: " + valueOfDealerHand);
        }
        if (valueOfDealerHand > 21) {
            System.out.println("Dealer bust");
            roundPlayerWin(player);
        } else if (valueOfDealerHand > valueOfPlayerHand) {
            printDealerHand(dealer);
            roundPlayerLoss(player);
        } else if (valueOfDealerHand == valueOfPlayerHand) {
            roundPlayerPush(player);
        } else {
            printDealerHand(dealer);
            roundPlayerWin(player);
        }
    }

    public void hit(DeckOfCards deck, Player player, Dealer dealer) {
        dealer.drawCardPlayer(deck, player);
        System.out.println("You have drawn " + lastCardDrawn(player.getHand()));
        printPlayerHand(player);
        int valueOfPlayerHand = valueOfHand(player.getHand());

        if (valueOfPlayerHand > 21) {
            System.out.println("You bust");
            dealer.flipFaceDownCard();
            printDealerHand(dealer);
            roundPlayerLoss(player);
        }

        while (true) {
            System.out.println("Hit or stand");
            String decision = scannerForString.nextLine();
            if (decision.equals("hit")) {
                hit(deck, player, dealer);
            } else if (decision.equals("stand")) {
                stand(deck, player, dealer);
                break;
            }
        }
    }

    public void doubleStake(DeckOfCards deck, Player player, Dealer dealer) {
        dealer.flipFaceDownCard();
        this.playerStake *= 2;
        System.out.println("Stake is now " + this.playerStake);
        dealer.drawCardPlayer(deck, player);
        System.out.println("You have drawn " + lastCardDrawn(player.getHand()));
        int valueOfPlayerHand = valueOfHand(player.getHand());
        printPlayerHand(player);
        if (valueOfPlayerHand > 21) {
            System.out.println("You bust");
            roundPlayerLoss(player);
        }
        stand(deck, player, dealer);
    }

    public void split() {
        // Implement the split logic here
    }

    public boolean isBlackjack(ArrayList<Card> hand) {
        int firstCardValue = hand.get(0).cardValue();
        int secondCardValue = hand.get(1).cardValue();

        return (firstCardValue == 1 && secondCardValue == 10) ||
                (firstCardValue == 10 && secondCardValue == 1);
    }

    public void blackjackCheck(Player player, Dealer dealer) {
        if (isBlackjack(dealer.getHand()) && isBlackjack(player.getHand())) {
            roundPlayerPush(player);
        }

        if (isBlackjack(dealer.getHand())) {
            System.out.println("BLACKJACK FOR DEALER!!!");
            dealer.flipFaceDownCard();
            printPlayerHand(player);
            printDealerHand(dealer);
            roundPlayerLoss(player);
        }

        if (isBlackjack(player.getHand())) {
            System.out.println("BLACKJACK FOR PLAYER !!!");
            dealer.flipFaceDownCard();
            printPlayerHand(player);
            printDealerHand(dealer);
            player.setMoney(player.getMoney() + this.playerStake * 1.5);
            roundPlayerWin(player);
        }
    }

    public int valueOfHand(ArrayList<Card> hand) {
        int handValue = 0;
        boolean hasAce = false;

        for (Card card : hand) {
            if (card.isFaceUp()) {
                handValue += card.cardValue();
            }
            if (card.isAce()) {
                hasAce = true;
            }
        }

        if (hasAce && handValue <= 11) { // treat Ace as 11 if hand won't bust (soft hand)
            handValue += 10;
        }

        return handValue;
    }
}
