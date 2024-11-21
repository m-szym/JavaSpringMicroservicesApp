import {Component, OnInit} from '@angular/core';
import {MissionDetails} from '../../models/mission-details';
import {MissionService} from '../../service/mission.service';
import {ActivatedRoute, Router} from '@angular/router';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-mission-details',
  imports: [
    NgIf
  ],
  templateUrl: './mission-details.component.html',
  standalone: true,
  styleUrl: './mission-details.component.css'
})
export class MissionDetailsComponent implements OnInit {

  mission: MissionDetails | undefined;

  constructor(private service: MissionService, private route: ActivatedRoute, private router: Router) {
  }


  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.service.getMission(params['id']).subscribe(mission => {
        this.mission = mission;
      });
    });
  }
}
