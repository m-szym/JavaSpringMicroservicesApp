import { Component, OnInit } from '@angular/core';
import {MissionService} from '../../service/mission.service';
import {ActivatedRoute, Router} from '@angular/router';
import {NgIf} from '@angular/common';
import {MissionCreateForm} from '../../models/mission-create-form';
import {FormsModule} from '@angular/forms';

@Component({
  standalone: true,
  selector: 'app-mission-create',
  imports: [
    NgIf,
    FormsModule,
  ],
  templateUrl: './mission-create.component.html',
  styleUrl: './mission-create.component.css'
})
export class MissionCreateComponent implements OnInit {
  targetId: string = "";
  mission: MissionCreateForm = {name: "", lunchDate: "", arrivalDate: "", targetId: ""};

  constructor(private service: MissionService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => this.targetId = params['id']);
    this.mission = {
      name: "",
      lunchDate: "",
      arrivalDate: "",
      targetId: this.targetId || ""
    }
  }

  onSubmit(): void {
    this.service.postMission(this.mission!)
      .subscribe(() => this.router.navigate(['targets', this.targetId]))
      // go back to target (category) view
  }
}
