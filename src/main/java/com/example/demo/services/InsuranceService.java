package com.example.demo.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ActivitiObjectNotFoundException;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.demo.entities.InsuranceApplicant;
import com.example.demo.entities.TaskDetail;
import com.example.demo.entities.User;
import com.example.demo.repositories.InsuranceApplicantRepository;

@Service
public class InsuranceService {

	private List<String> allProcessIds = new ArrayList<>();
	
	Map<String,InsuranceApplicant> processVariables = new HashMap<>();

	@Autowired
	ActivitiInterface activitiInterface;

	@Autowired
	InsuranceApplicantRepository insuranceRepository;

	/**
	 * 
	 * @param applicant starts a new process instance of insurance_approval process
	 */
	public String startNewProcess(InsuranceApplicant applicant) {
		
		User requestor = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		Map<String, Object> processValues = new HashMap<>();
		processValues.put("requestor", requestor.getUsername());

		processValues.put("applicantDetails", applicant);

		double premiumAmount = applicant.getPremiumAmount();
		double sumAssuredAmount = applicant.getSumAssuredAmount();
		processValues.put("premiumAmount", premiumAmount);
		if (premiumAmount < 500000) {
			processValues.put("scrutinizer", "SCRUTINIZERLEVEL1");
		} else if (premiumAmount > 500000 && premiumAmount < 1500000) {
			processValues.put("scrutinizer", "SCRUTINIZERLEVEL2");
		} else {
			processValues.put("scrutinizer", "SCRUTINIZERLEVEL3");
		}

		if (sumAssuredAmount < 500000) {
			processValues.put("underwriter", "UNDERWRITERLEVEL1");
		} else if (sumAssuredAmount > 500000 && sumAssuredAmount < 1500000) {
			processValues.put("underwriter", "UNDERWRITERLEVEL2");
		} else {
			processValues.put("underwriter", "UNDERWRITERLEVEL3");
		}

		String processId = activitiInterface.startNewProcess("insurance_approval", processValues);
		allProcessIds.add(processId);
		processVariables.put(processId, applicant);

		insuranceRepository.save(applicant);

		return processId;
	}

	/**
	 * 
	 * @return this methods returns all the tasks for all the running process
	 */
	public List<TaskDetail> getAllTasks() {

		User requestor = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		List<TaskDetail> userTasks = new ArrayList<>();
		
		allProcessIds.forEach(pid -> activitiInterface.getTasksByProcessId(pid)
				.forEach(t -> userTasks.add(new TaskDetail(t.getId(), t.getExecutionId(), t.getAssignee(),
						t.getParentTaskId(), requestor.getUsername(), t.getCreateTime()))));
		return userTasks;
	}

	/**
	 * 
	 * @param assigneeName
	 * @return returns all the tasks for given assignee
	 */
	public List<TaskDetail> getTasksByUser(String assigneeName) {
		User requestor = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		List<TaskDetail> userTasks = new ArrayList<>();

		List<Task> tasks = activitiInterface.getTasksByUser(assigneeName);
		
		tasks.forEach(t -> userTasks.add(new TaskDetail(t.getId(), t.getProcessInstanceId(), t.getAssignee(),
				t.getParentTaskId(), requestor.getUsername(), t.getCreateTime(), processVariables.get(t.getProcessInstanceId()),(String)t.getTaskLocalVariables().get("COMMENT")==null?"No message":(String)t.getTaskLocalVariables().get("COMMENT"))));

		return userTasks;
	}

	/**
	 * 
	 * @param task
	 * @return returns true if Scrutinizer successfully approves task
	 */
	public String approveTask(TaskDetail task, String comment) {
		try {
			Map<String, Object> params = new HashMap<>();
			params.put("ACTION", "APPROVE");
			params.put("COMMENT", comment);
			activitiInterface.completeTask(task.getTaskId(), params);
			return "Request Approved";
		}catch(ActivitiObjectNotFoundException e) {
			return "There is no task with provided task id";
		}catch (Exception e) {
			return "Something wrong";
		}
	}

	/**
	 * 
	 * @return returns true if Scrutinizer rejects task
	 */
	public String rejectTask(TaskDetail task) {
		try {
			Map<String, Object> params = new HashMap<>();
			params.put("ACTION", "REJECT");
			activitiInterface.completeTask(task.getTaskId(), params);
			return "Request Rejected";
		}catch(ActivitiObjectNotFoundException e) {
			return "There is no task with provided task id";
		}catch (Exception e) {
			return "Something wrong";
		}
	}
}
