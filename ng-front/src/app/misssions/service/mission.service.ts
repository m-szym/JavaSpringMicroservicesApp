import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {MissionEditForm} from '../models/mission-edit-form';
import {Observable} from 'rxjs';
import {Missions} from '../models/missions';
import {MissionDetails} from '../models/mission-details';
import {MissionCreateForm} from '../models/mission-create-form';

@Injectable({
  providedIn: 'root'
})
export class MissionService {

  constructor(private http: HttpClient) { }

  postMission(request: MissionCreateForm): Observable<any> {
    console.log('POST:', request);
    return this.http.post('/api/missions', request);
  }

  getMissions(): Observable<Missions> {
    return this.http.get<Missions>('/api/missions')
      .pipe(data => {
        console.log('GET:', data);
        return data;
      });
  }

  getMission(id: string): Observable<MissionDetails> {
    return this.http.get<MissionDetails>('/api/missions/' + id)
      .pipe(data => {
        console.log('GET:', data);
        return data;
      });
  }

  getMissionsByTargetId(targetId: string): Observable<Missions> {
    return this.http.get<Missions>('/api/missions/target/' + targetId)
      .pipe(data => {
        console.log('GET by target:', data);
        return data;
      });
  }

  putMission(id: string, request: MissionEditForm): Observable<any> {
    console.log('PUT:', request);
    return this.http.put('/api/missions/' + id, request);
  }

  deleteMission(id: string): Observable<any> {
    return this.http.delete('/api/missions/' + id);
  }
}
