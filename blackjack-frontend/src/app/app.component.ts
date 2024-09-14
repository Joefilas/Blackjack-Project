import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {GameBoardComponent} from "./game-board/game-board.component";
import {ControlPanelComponent} from "./control-panel/control-panel.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, GameBoardComponent, ControlPanelComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'blackjack-frontend';
}
