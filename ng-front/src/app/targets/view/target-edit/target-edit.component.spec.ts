import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TargetEditComponent } from './target-edit.component';

describe('TargetEditComponent', () => {
  let component: TargetEditComponent;
  let fixture: ComponentFixture<TargetEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TargetEditComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TargetEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
