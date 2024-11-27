import {Component, Input, OnInit} from '@angular/core';
import {Missions} from '../../models/missions';
import {MissionService} from '../../service/mission.service';
import {ActivatedRoute, RouterLink} from '@angular/router';
import {MissionBrief} from '../../models/mission-brief';
import {NgFor, NgIf} from '@angular/common';

@Component({
  standalone: true,
  selector: 'app-mission-of-target-list',
  imports: [
    RouterLink,
    NgIf,
    NgFor
  ],
  templateUrl: './mission-of-target-list.component.html',
  styleUrl: './mission-of-target-list.component.css'
})
export class MissionOfTargetListComponent implements OnInit {
  @Input()
  id: string | undefined;

  missionsOfTarget: Missions | undefined;

  constructor(private service: MissionService) {
  }

  ngOnInit(): void {
    this.service.getMissionsByTargetId(this.id!)
      .subscribe(missions => {
      this.missionsOfTarget = missions;
    });
  }

  onDelete(mission: MissionBrief): void {
    this.service.deleteMission(mission.id)
      .subscribe(() => this.ngOnInit());
  }

}
