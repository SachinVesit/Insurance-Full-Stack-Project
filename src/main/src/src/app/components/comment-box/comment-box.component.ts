import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

import { Data } from '@angular/router';
import { HomeComponent } from 'src/app/home/home.component';

@Component({
  selector: 'app-comment-box',
  templateUrl: './comment-box.component.html',
  styleUrls: ['./comment-box.component.scss']
})
export class CommentBoxComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<HomeComponent>, @Inject(MAT_DIALOG_DATA) public data: Data) { }

  ngOnInit(): void {
  }

}
