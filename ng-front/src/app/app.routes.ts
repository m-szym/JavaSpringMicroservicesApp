import { Routes } from '@angular/router';
import {MissionDetailsComponent} from "./misssions/view/mission-details/mission-details.component";

export const routes: Routes = [
    { path: 'missions/:id', component: MissionDetailsComponent },
];
