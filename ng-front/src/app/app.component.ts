import { Component } from '@angular/core';
import {RouterOutlet} from '@angular/router';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  standalone: true,
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'ng-front';
}
