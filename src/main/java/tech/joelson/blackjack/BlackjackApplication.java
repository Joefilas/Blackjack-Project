package tech.joelson.blackjack;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tech.joelson.blackjack.model.Dealer;
import tech.joelson.blackjack.model.DeckOfCards;
import tech.joelson.blackjack.model.Player;
import tech.joelson.blackjack.service.GameService;

import java.util.Scanner;

@SpringBootApplication
public class BlackjackApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlackjackApplication.class, args);
	}

	@Bean
	public CommandLineRunner runGame(GameService gameService) {
		return args -> {
			Scanner scannerForInt = new Scanner(System.in);
			Scanner scannerForString = new Scanner(System.in);

			// Get user input for player name and money
			System.out.println("Enter your name");
			String name = scannerForString.nextLine();
			System.out.println("Choose your money");
			int money = scannerForInt.nextInt();

			// Create Player and Dealer objects
			Player player1 = new Player(name, money);
			Dealer dealer = new Dealer();
			DeckOfCards deck = new DeckOfCards();

			// Start the game using the injected gameService
			gameService.startGame(deck, player1, dealer);
		};
	}
}
