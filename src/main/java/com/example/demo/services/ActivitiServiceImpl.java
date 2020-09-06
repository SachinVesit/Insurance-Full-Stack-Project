package com.example.demo.services;

import java.util.List;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivitiServiceImpl implements ActivitiInterface {

	@Autowired
	RuntimeService runtimeService;

	@Autowired
	TaskService taskService;

	/*
	 * Starts a new Process instance and returns id of newly created process
	 * instance
	 */
	@Override
	public String startNewProcess(String processName) {
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processName);
		return processInstance.getProcessInstanceId();
	}

	/*
	 * Starts a new Process instance and returns id of newly created process
	 * instance
	 */
	@Override
	public String startNewProcess(String processName, Map<String, Object> processValues) {
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processName, processValues);
		return processInstance.getProcessInstanceId();
	}

	/*
	 * returns all the task for provided user
	 */
	@Override
	public List<Task> getTasksByUser(String assigneeName) {
		return taskService.createTaskQuery().taskAssignee(assigneeName).list();
	}

	/*
	 * returns all the task for provided process id
	 */
	@Override
	public List<Task> getTasksByProcessId(String processId) {
		return taskService.createTaskQuery().processInstanceId(processId).list();
	}

	@Override
	public void completeTask(String taskId, Map<String, Object> params) {
		taskService.complete(taskId, params);
	}

	@Override
	public void completeTask(String taskId) {
		taskService.complete(taskId);
		
	}

	@Override
	public ProcessInstance getProcessInstance(String pid) {
		return runtimeService.createProcessInstanceQuery().processInstanceId(pid).singleResult();
	}

}
