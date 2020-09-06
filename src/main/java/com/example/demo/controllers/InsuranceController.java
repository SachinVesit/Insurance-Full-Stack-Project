package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.ApiResponse;
import com.example.demo.entities.InsuranceApplicant;
import com.example.demo.entities.TaskDetail;
import com.example.demo.services.InsuranceService;

@RestController
@RequestMapping("/insurance_request")
public class InsuranceController {

	@Autowired
	InsuranceService insuranceService;

	/**
	 * 
	 * @param applicant starts a new process instance of insurance_approval process
	 */
	@RequestMapping(value = "/start_new_process", method = { RequestMethod.POST, RequestMethod.PUT })
	public ApiResponse<String> startNewProcess(@RequestBody InsuranceApplicant applicant) {
		return new ApiResponse<>(HttpStatus.OK.value(), "User request successfull!!!",
				insuranceService.startNewProcess(applicant));
	}

	/**
	 * 
	 * @param assigneeName
	 * @return all the tasks for given assignee
	 */
	@GetMapping("/get_tasks_by_user")
	public ApiResponse<List<TaskDetail>> getTasksByUser(@RequestParam("userRole") String assigneeName) {
		return new ApiResponse<>(HttpStatus.OK.value(), " ", insuranceService.getTasksByUser(assigneeName));
	}

	/**
	 * 
	 * @return this methods returns all the tasks for all the running process
	 */
	@GetMapping("/get_all_tasks")
	public List<TaskDetail> getAllTasks() {
		return this.insuranceService.getAllTasks();
	}

	@RequestMapping(value = "/approve_task", method = { RequestMethod.POST })
	public ApiResponse<String> approveTask(@RequestBody TaskDetail task, @RequestParam("comment") String comment) {
		return new ApiResponse<>(HttpStatus.OK.value(), "!!!", insuranceService.approveTask(task,comment));
	}

	@RequestMapping(value = "/reject_task", method = { RequestMethod.POST })
	public ApiResponse<String> rejectTask(@RequestBody TaskDetail task) {
		return new ApiResponse<>(HttpStatus.OK.value(), "!!!", insuranceService.rejectTask(task));
	}
}
