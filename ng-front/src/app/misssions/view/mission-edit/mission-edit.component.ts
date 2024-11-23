import {Component, OnInit} from '@angular/core';
import {MissionService} from '../../service/mission.service';
import {ActivatedRoute, Router} from '@angular/router';
import {MissionEditForm} from '../../models/mission-edit-form';
import {FormsModule, NgForm} from '@angular/forms';
import {NgIf} from '@angular/common';

@Component({
  standalone: true,
  selector: 'app-mission-edit',
  imports: [
    FormsModule,
    NgIf
  ],
  templateUrl: './mission-edit.component.html',
  styleUrl: './mission-edit.component.css'
})
export class MissionEditComponent implements OnInit {
  id: string = "";
  targetId: string = "";
  newMission: MissionEditForm = {name: "", arrivalDate: "", lunchDate: ""};
  originalMission: MissionEditForm = {name: "", lunchDate: "", arrivalDate: ""};

  constructor(private service: MissionService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.service.getMission(params['mission_id']).subscribe(data => {
        this.newMission = {
          name: data.name,
          lunchDate: data.lunchDate,
          arrivalDate: data.arrivalDate
        };
        this.originalMission = {...this.newMission};
        this.id = data.id;
        this.targetId = data.targetId;
      })
    });
  }

  onSubmit(): void {
    this.service.putMission(this.id!, this.newMission!)
      .subscribe(() => this.router.navigate(['targets', this.targetId]))
    // go back to target (category) details view
  }

  onReset(form: NgForm): void {
    form.resetForm();
    this.newMission = {...this.originalMission};
  }
}
