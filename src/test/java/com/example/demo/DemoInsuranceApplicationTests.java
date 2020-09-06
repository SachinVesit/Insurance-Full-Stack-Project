package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.services.ActivitiInterface;

@SpringBootTest
class DemoInsuranceApplicationTests {

	@Autowired
	ActivitiInterface activitiInterface;

	@Test
	void contextLoads() {
	}

	@Test
	public void whenManagerApproves_theHRAction() {
		// Raise new request
		Map<String, Object> values = new HashMap<>();
		values.put("scrutinizer", "SCRUTINIZERLEVEL1");
		String pid = activitiInterface.startNewProcess("insurance_approval", values);

		// Check if task is assigned to manager
		List<Task> tasks = activitiInterface.getTasksByUser("SCRUTINIZERLEVEL1");
		activitiInterface.getTasksByProcessId(pid);

		assertTrue(tasks.stream().anyMatch(t -> t.getProcessInstanceId().equals(pid)), "Task not found with manager");

		String taskId = tasks.stream().filter(t -> t.getProcessInstanceId().equals(pid)).findFirst().get().getId();

		Map<String, Object> params = new HashMap<>();
		params.put("ACTION", "APPROVE");
		params.put("underwriter", "UNDERWRITERLEVEL2");
		activitiInterface.completeTask(taskId, params);

		List<Task> taskss = activitiInterface.getTasksByUser("UNDERWRITERLEVEL2");
		activitiInterface.getTasksByProcessId(pid);

		assertTrue(taskss.stream().anyMatch(t -> t.getProcessInstanceId().equals(pid)), "Task not found with manager");

		String taskI = taskss.stream().filter(t -> t.getProcessInstanceId().equals(pid)).findFirst().get().getId();

		activitiInterface.completeTask(taskI);

		// Check if process ended
		ProcessInstance processInstance = activitiInterface.getProcessInstance(pid);
		assertNull(processInstance, "Process Instance did not complete");
	}

	@Test
	public void whenManagerApproves_theHRActin() {
		// Raise new request
		Map<String, Object> values = new HashMap<>();
		values.put("scrutinizer", "SCRUTINIZERLEVEL1");
		String pid = activitiInterface.startNewProcess("insurance_approval", values);

		// Check if task is assigned to manager
		List<Task> tasks = activitiInterface.getTasksByUser("SCRUTINIZERLEVEL1");
		activitiInterface.getTasksByProcessId(pid);

		assertTrue(tasks.stream().anyMatch(t -> t.getProcessInstanceId().equals(pid)), "Task not found with manager");

		String taskId = tasks.stream().filter(t -> t.getProcessInstanceId().equals(pid)).findFirst().get().getId();

		Map<String, Object> params = new HashMap<>();
		params.put("ACTION", "REJECT");
		activitiInterface.completeTask(taskId, params);

		List<Task> taskss = activitiInterface.getTasksByUser("UNDERWRITERLEVEL2");
		activitiInterface.getTasksByProcessId(pid);

		assertTrue(taskss.stream().anyMatch(t -> t.getProcessInstanceId().equals(pid)), "Task not found with manager");

		// Check if process ended
		ProcessInstance processInstance = activitiInterface.getProcessInstance(pid);
		assertNull(processInstance, "Process Instance did not complete");
	}

}
