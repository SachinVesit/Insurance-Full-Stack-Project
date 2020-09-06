import { Component, OnInit } from '@angular/core';
import { TaskDetail } from 'src/app/models/taskdetail';
import { InsuranceService } from 'src/app/services/insurance.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-process-portal',
  templateUrl: './process-portal.component.html',
  styleUrls: ['./process-portal.component.scss']
})
export class ProcessPortalComponent implements OnInit {

  constructor(private insuranceService: InsuranceService,
              private routeService: Router) { }

  tableData: boolean = false;
  tasks: Array<any> = [];

  ngOnInit(): void {
    this.refreshTask();
  }

  refreshTask(): void{
    this.insuranceService.getAllTasks().subscribe((data) => {
      this.tasks = data;
      if(this.tasks.length>0){
        this.tableData = true;
      }else{
        this.tableData = false;
      }
    }, (error) =>{
      alert(JSON.stringify(error));
    })
  }

  approveRequest(task){
    this.insuranceService.approveTask(task,"comment").subscribe((data) => {
      this.refreshTask();
    }, (error) =>{
      alert(JSON.stringify(error));
    })
  }

  rejectRequest(task){
    this.insuranceService.rejectTask(task).subscribe((data) => {
      this.refreshTask();
    }, (error) =>{
      alert(JSON.stringify(error));
    })
  }

  goToHomePage(){
    this.routeService.navigate(['/']);
  }

}
