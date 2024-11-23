import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router, RouterLink} from '@angular/router';
import {TargetDetails} from '../../models/target-details';
import {
  MissionOfTargetListComponent
} from '../../../misssions/view/mission-of-target-list/mission-of-target-list.component';
import {TargetService} from '../../service/target.service';
import {NgIf} from '@angular/common';

@Component({
  standalone: true,
  selector: 'app-target-details',
  imports: [
    MissionOfTargetListComponent,
    NgIf,
    RouterLink
  ],
  templateUrl: './target-details.component.html',
  styleUrl: './target-details.component.css'
})
export class TargetDetailsComponent {
  target: TargetDetails | undefined;
  id: string | undefined;

  constructor(private service: TargetService,
              private route: ActivatedRoute,
              private router: Router) {
    this.route.params.subscribe(params => {
      this.id = params['id'];
      this.service.getTarget(params['id']).subscribe(target => {
        this.target = target;
      });
    });
  }

}
