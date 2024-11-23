import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {MainComponent} from "./components/main/main.component";
import {NavComponent} from "./components/nav/nav.component";

@Component({
  standalone: true,
  selector: 'app-root',
  imports: [MainComponent, NavComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'ng-front';
}
