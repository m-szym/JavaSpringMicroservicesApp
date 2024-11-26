import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {TargetCreateForm} from '../models/target-create-form';
import {Observable} from 'rxjs';
import {Targets} from '../models/targets';
import {TargetDetails} from '../models/target-details';
import {TargetEditForm} from '../models/target-edit-form';

@Injectable({
  providedIn: 'root'
})
export class TargetService {

  constructor(private http: HttpClient) { }

  postTarget(request: TargetCreateForm): Observable<any> {
    console.log('POST:', request);
    return this.http.post('api/targets', request);
  }

  getTargets(): Observable<Targets> {
    return this.http.get<Targets>('api/targets')
      .pipe(data => {
        console.log('GET:', data);
        return data;
      });
  }

  getTarget(id: string): Observable<TargetDetails> {
    return this.http.get<TargetDetails>('api/targets/' + id)
      .pipe(data => {
        console.log('GET:', data);
        return data;
      });
  }

  putTarget(id: string, request: TargetEditForm): Observable<any> {
    console.log('PUT:', request);
    return this.http.put('api/targets/' + id, request);
  }

  deleteTarget(id: string): Observable<any> {
    return this.http.delete('api/targets/' + id)
      .pipe(data => {
        console.log('DELETE:', data);
        return data;
      });
  }
}
