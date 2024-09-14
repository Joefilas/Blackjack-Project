import { Component } from '@angular/core';
import { BlackjackService } from '../blackjack.service'; // Assume the service is in the same level

@Component({
  selector: 'app-control-panel',
  templateUrl: './control-panel.component.html'
})
export class ControlPanelComponent {
  constructor(private blackjackService: BlackjackService) {}

  onHit() {
    this.blackjackService.hit().subscribe(response => {
      // Handle the response, update the game state
    });
  }

  onStand() {
    this.blackjackService.stand().subscribe(response => {
      // Handle the response, update the game state
    });
  }

  onNewGame() {
    this.blackjackService.newGame().subscribe(response => {
      // Handle new game, reset the state
    });
  }
}
