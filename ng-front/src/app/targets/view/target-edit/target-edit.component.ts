import {Component, OnInit} from '@angular/core';
import {TargetEditForm} from '../../models/target-edit-form';
import {TargetService} from '../../service/target.service';
import {ActivatedRoute, Router} from '@angular/router';
import {FormsModule, NgForm} from '@angular/forms';
import {NgIf} from '@angular/common';

@Component({
  standalone: true,
  selector: 'app-target-edit',
  imports: [
    FormsModule,
    NgIf
  ],
  templateUrl: './target-edit.component.html',
  styleUrl: './target-edit.component.css'
})
export class TargetEditComponent implements OnInit{
  id: string = "";
  originalTarget: TargetEditForm = {name: "", distance: 0};
  newTarget: TargetEditForm = {name: "", distance: 0};

  constructor(private service: TargetService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.service.getTarget(params['id']).subscribe(data => {
        this.newTarget = {
          name: data.name,
          distance: data.distance
        };
        this.originalTarget = {...this.newTarget};
        this.id = data.id;
      })
    })
  }

  onSubmit(): void {
    this.service.putTarget(this.id!, this.newTarget!)
      .subscribe(() => this.router.navigate(['targets']));
  }

  onReset(form: NgForm): void {
    //form.reset();
    this.newTarget = {...this.originalTarget};
  }
}
