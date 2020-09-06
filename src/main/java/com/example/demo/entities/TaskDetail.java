package com.example.demo.entities;

import java.util.Date;

public class TaskDetail {
	private String taskId;
	private String processId;
	private String assignee;
	private String parentTaskId;
	private String requestor;
	private Date taskAssignDate;
	private InsuranceApplicant applicant;
	private String comment;

	public TaskDetail() {
		super();
	}

	public TaskDetail(String taskId, String processId, String assignee, String parentTaskId, String requestor,
			Date taskAssignDate) {
		super();
		this.taskId = taskId;
		this.processId = processId;
		this.assignee = assignee;
		this.parentTaskId = parentTaskId;
		this.requestor = requestor;
		this.taskAssignDate = taskAssignDate;
	}

	public TaskDetail(String taskId, String processId, String assignee, String parentTaskId, String requestor,
			Date taskAssignDate, InsuranceApplicant applicant) {
		super();
		this.taskId = taskId;
		this.processId = processId;
		this.assignee = assignee;
		this.parentTaskId = parentTaskId;
		this.requestor = requestor;
		this.taskAssignDate = taskAssignDate;
		this.applicant = applicant;
	}

	public TaskDetail(String taskId, String processId, String assignee, String parentTaskId, String requestor,
			Date taskAssignDate, InsuranceApplicant applicant, String comment) {
		super();
		this.taskId = taskId;
		this.processId = processId;
		this.assignee = assignee;
		this.parentTaskId = parentTaskId;
		this.requestor = requestor;
		this.taskAssignDate = taskAssignDate;
		this.applicant = applicant;
		this.comment = comment;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public String getParentTaskId() {
		return parentTaskId;
	}

	public void setParentTaskId(String parentTaskId) {
		this.parentTaskId = parentTaskId;
	}

	public String getRequestor() {
		return requestor;
	}

	public void setRequestor(String requestor) {
		this.requestor = requestor;
	}

	public Date getTaskAssignDate() {
		return taskAssignDate;
	}

	public void setTaskAssignDate(Date taskAssignDate) {
		this.taskAssignDate = taskAssignDate;
	}

	public InsuranceApplicant getApplicant() {
		return applicant;
	}

	public void setApplicant(InsuranceApplicant applicant) {
		this.applicant = applicant;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
