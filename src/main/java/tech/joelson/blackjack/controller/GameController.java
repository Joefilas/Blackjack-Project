package tech.joelson.blackjack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.joelson.blackjack.model.Dealer;
import tech.joelson.blackjack.model.DeckOfCards;
import tech.joelson.blackjack.model.Player;
import tech.joelson.blackjack.service.GameService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")  // Allow communication from Angular frontend
public class GameController {

    @Autowired
    private GameService gameService;

    private DeckOfCards deck = new DeckOfCards();
    private Player player = new Player("Player", 100);  // Assuming starting balance is 100
    private Dealer dealer = new Dealer();

    @GetMapping("/start")
    public String startGame() {
        gameService.startGame(deck, player, dealer);
        return "Game started";
    }

    @PostMapping("/hit")
    public String hit() {
        gameService.hit(deck, player, dealer);
        return "Hit performed";
    }

    @PostMapping("/stand")
    public String stand() {
        gameService.stand(deck, player, dealer);
        return "Stand performed";
    }

    // Additional endpoints can be added as needed (e.g., for doubling stake, split, etc.)
}
