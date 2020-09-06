package com.example.demo.services;

import java.util.List;
import java.util.Map;

import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

public interface ActivitiInterface {
	String startNewProcess(String processName);

	String startNewProcess(String processName, Map<String, Object> processValues);
	
	List<Task> getTasksByUser(String assigneeName);
	
	List<Task> getTasksByProcessId(String processId);
	
	void completeTask(String taskId, Map<String, Object> params);
	
	void completeTask(String taskId);
	
	ProcessInstance getProcessInstance(String pid);
}
