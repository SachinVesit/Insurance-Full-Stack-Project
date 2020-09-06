import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiResponse } from '../models/apiResponse';
import { TaskDetail } from '../models/taskdetail';

@Injectable({
  providedIn: 'root'
})
export class InsuranceService {

  constructor(private httpService: HttpClient) { }

  /**
   * starts new process for insurance approve
   */
  startNewProcess(applicant:any): Observable<ApiResponse> {
    return this.httpService.post<ApiResponse>("/insurance_request/start_new_process", applicant);
  }

  /**
	 * 
	 * @return this methods returns all the tasks for all the running process
	 */
  getAllTasks(): Observable<any> {
    return this.httpService.get("/insurance_request/get_all_tasks");
  }

  /**
	 * 
	 * @return this methods returns all the tasks for given username
	 */
  getTasksByUser(userRole: String): Observable<any> {
    return this.httpService.get("/insurance_request/get_tasks_by_user?userRole="+userRole);
  }

  /**
	 * 
	 * @return this methods returns all the tasks for all the running process
	 */
  approveTask(task:any, comment:String): Observable<ApiResponse> {
    return this.httpService.post<ApiResponse>("/insurance_request/approve_task?comment="+comment, task);
  }

  /**
	 * 
	 * @return this methods returns all the tasks for all the running process
	 */
  rejectTask(task:any): Observable<ApiResponse> {
    return this.httpService.post<ApiResponse>("/insurance_request/reject_task", task);
  }
}