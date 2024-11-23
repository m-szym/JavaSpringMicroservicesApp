import {Component, OnInit} from '@angular/core';
import {Missions} from '../../models/missions';
import {MissionService} from '../../service/mission.service';
import {NgFor, NgIf} from '@angular/common';
import {RouterLink} from '@angular/router';
import {MissionBrief} from '../../models/mission-brief';

@Component({
  standalone: true,
  selector: 'app-mission-list',
  imports: [
    NgIf,
    RouterLink,
    NgFor
  ],
  templateUrl: './mission-list.component.html',
  styleUrl: './mission-list.component.css'
})
export class MissionListComponent implements OnInit {
  missions: Missions | undefined;

  constructor(private service: MissionService) {
  }

  ngOnInit(): void {
    this.service.getMissions().subscribe(data => {
      this.missions = data;
    });
  }

  onDelete(mission: MissionBrief): void {
    this.service.deleteMission(mission.id).subscribe(() => this.ngOnInit());
  }
}
