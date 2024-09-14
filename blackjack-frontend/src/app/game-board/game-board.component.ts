import { Component } from '@angular/core';
import {DealerComponent} from "../dealer/dealer.component";
import {PlayerComponent} from "../player/player.component";

@Component({
  selector: 'app-game-board',
  standalone: true,
  imports: [
    DealerComponent,
    PlayerComponent
  ],
  templateUrl: './game-board.component.html',
  styleUrl: './game-board.component.css'
})
export class GameBoardComponent {

}
