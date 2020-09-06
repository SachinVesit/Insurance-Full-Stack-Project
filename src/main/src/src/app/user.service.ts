import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ApiResponse } from './models/apiResponse';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpService: HttpClient) { }


  /**
   * this method calls api service to get all the user details
   */
  getUsers(): Observable<any> {
    return this.httpService.get("/user/get_all_users");
  }


  /**
   * @param user id
   *  calls api service to get  user details for given id
   */
  getUserById(id: String): Observable<any> {
    return this.httpService.get("/user/user_id?id="+id);
  }


  /**
   * @param username
   *  calls api service to get  user details for given username
   */
  getUserByUsername(username: String): Observable<ApiResponse> {
    return this.httpService.get<ApiResponse>("/user/username?username="+username);
  }


  /**
   * @param email
   *  calls api service to get  user details for given email
   */
  getUserByEmail(email: String): Observable<ApiResponse> {
    return this.httpService.get<ApiResponse>("/user/email?email="+email);
  }


  /**
   * @param employee code
   *  calls api service to get  user details for given employee code
   */
  getUserByEmpCode(employeeCode: String): Observable<ApiResponse> {
    return this.httpService.get<ApiResponse>("/user/emp_code?employeeCode="+employeeCode);
  }


  /**
   * @param a new user details
   *  calls api service to save this new user to database
   */
  addUser(user:any):Observable<ApiResponse>{
    return this.httpService.post<ApiResponse>("/user/add_new_user", user);
  }


  /**
   * @param user details
   * calls api service to update this user details in database
   */
  updateUser(user:any): Observable<ApiResponse>{
    return this.httpService.post<ApiResponse>("/user/update_user_details", user);
  }


  /**
   * 
   * @param id 
   * calls api service to delete the user details from database corresponding to the id
   */
  deleteUser(id: String): Observable<ApiResponse>{
    return this.httpService.delete<ApiResponse>("/user/delete_user?id="+id);
  }

  /**
   * this methods gives currently logged in user's role
   */
  getUserole(): Observable<ApiResponse>{
    return this.httpService.get<ApiResponse>("/user/get_userrole");
  }
}
