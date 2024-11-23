import {Component, OnInit} from '@angular/core';
import {Targets} from '../../models/targets';
import {TargetService} from '../../service/target.service';
import {TargetBrief} from '../../models/target-brief';
import {NgFor, NgIf} from '@angular/common';
import {RouterLink} from '@angular/router';

@Component({
  standalone: true,
  selector: 'app-target-list',
  imports: [
    NgFor,
    NgIf,
    RouterLink
  ],
  templateUrl: './target-list.component.html',
  styleUrl: './target-list.component.css'
})
export class TargetListComponent implements OnInit {

  targets: Targets | undefined;

  constructor(private targetService: TargetService) {
  }

  ngOnInit(): void {
    this.targetService.getTargets().subscribe(data => {
      this.targets = data;
    });
  }

  onDelete(target: TargetBrief): void {
    this.targetService.deleteTarget(target.id).subscribe(() => this.ngOnInit());
  }
}
