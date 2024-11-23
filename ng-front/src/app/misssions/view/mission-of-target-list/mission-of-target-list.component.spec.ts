import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MissionOfTargetListComponent } from './mission-of-target-list.component';

describe('MissionOfTargetListComponent', () => {
  let component: MissionOfTargetListComponent;
  let fixture: ComponentFixture<MissionOfTargetListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MissionOfTargetListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MissionOfTargetListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
