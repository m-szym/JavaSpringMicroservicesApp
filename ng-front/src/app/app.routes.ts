import { Routes } from '@angular/router';
import {TargetListComponent} from "./targets/view/target-list/target-list.component";
import {TargetDetailsComponent} from "./targets/view/target-details/target-details.component";
import {MissionDetailsComponent} from "./misssions/view/mission-details/mission-details.component";
import {MissionListComponent} from "./misssions/view/mission-list/mission-list.component";
import {MissionCreateComponent} from "./misssions/view/mission-create/mission-create.component";
import {TargetCreateComponent} from "./targets/view/target-create/target-create.component";
import {TargetEditComponent} from "./targets/view/target-edit/target-edit.component";
import {MissionEditComponent} from "./misssions/view/mission-edit/mission-edit.component";

export const routes: Routes = [
    { path: 'targets', component: TargetListComponent },
    { path: 'targets/:id', component: TargetDetailsComponent },
    { path: 'targets/:id/edit', component: TargetEditComponent },
    { path: 'targets/:id/new_mission', component: MissionCreateComponent },
    { path: 'targets/:id/missions/:mission_id/edit', component: MissionEditComponent },
    { path: 'targets/:id/missions/new', component: MissionCreateComponent },
    { path: 'targets/new', component: TargetCreateComponent },

    { path: 'missions', component: MissionListComponent },
    { path: 'missions/:id', component: MissionDetailsComponent },
    // { path: 'missions/:id/edit', component: MissionEditComponent },
    { path: 'missions/new/:id', component: MissionCreateComponent},
];
