import {TargetBrief} from '../../targets/models/target-brief';


export interface MissionDetails {
  name: string;
  lunchDate: string;
  arrivalDate: string;
  target: TargetBrief;
}
