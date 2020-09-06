import { Component, OnInit } from '@angular/core';
import { InsuranceService } from 'src/app/services/insurance.service';
import { UserService } from 'src/app/user.service';
import { PasswordDialogComponent } from 'src/app/password-dialog/password-dialog.component';
import { MatDialog } from '@angular/material/dialog';
import { CommentBoxComponent } from '../comment-box/comment-box.component';

@Component({
  selector: 'app-user-inbox',
  templateUrl: './user-inbox.component.html',
  styleUrls: ['./user-inbox.component.scss']
})
export class UserInboxComponent implements OnInit {

  tableData: boolean = false;
  loggedInUsername: String;
  tasks: Array<any> = [];
  taskDetails: any;
  enableTaskDetail: boolean = false;
  comment: String;

  constructor(private insuranceService: InsuranceService,
              private userService: UserService,
              public dialog: MatDialog) { }

  ngOnInit(): void {
    this.userService.getUserole().subscribe((data) => {
      this.loggedInUsername = data.result;
      this.refreshTasks();
    }, (error) =>{
      alert(JSON.stringify(error));
    });
  }

  refreshTasks(){
    this.insuranceService.getTasksByUser(this.loggedInUsername).subscribe((data) => {
      this.tasks = data.result;
      if(this.tasks.length>0){
        this.tableData = true;
      }else{
        this.tableData = false;
      }
    }, (error) =>{
      alert(JSON.stringify(error));
    })
  }

  approveRequest(task): void{
    this.insuranceService.approveTask(task,this.comment).subscribe((data) => {
      this.refreshTasks();
      alert(data.result);
    }, (error) =>{
      alert(JSON.stringify(error));
    })
  }

  rejectRequest(task): void{
    this.insuranceService.rejectTask(task).subscribe((data) => {
      this.refreshTasks();
      alert(data.result);
    }, (error) =>{
      alert(JSON.stringify(error));
    })
  }

  getTaskDetails(task): void{
    this.taskDetails = task;
    this.enableTaskDetail = true;
  }

  getComment(task){
    const dialogRef = this.dialog.open(CommentBoxComponent, {
      width: '500px',
      data: {comment: this.comment}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.comment = result;
      this.approveRequest(task);
    });
  }

}
