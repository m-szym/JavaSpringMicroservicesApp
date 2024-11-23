import { Component } from '@angular/core';
import {TargetCreateForm} from '../../models/target-create-form';
import {TargetService} from '../../service/target.service';
import {Router} from '@angular/router';
import {FormsModule} from '@angular/forms';

@Component({
  standalone: true,
  selector: 'app-target-create',
  imports: [
    FormsModule
  ],
  templateUrl: './target-create.component.html',
  styleUrl: './target-create.component.css'
})
export class TargetCreateComponent {
  target: TargetCreateForm = {
    name: "",
    distance: 0,
  };

  constructor(private service: TargetService,
              private router: Router) {
  }

  onSubmit(): void {
    this.service.postTarget(this.target)
      .subscribe(() => this.router.navigate(['targets']))
  }

}
