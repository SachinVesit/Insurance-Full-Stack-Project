package com.example.demo.delegates;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.services.UtilityService;

@Component("GetScrutinizerDelegate")
public class GetScrutinizerDelegate implements JavaDelegate {
	
	@Autowired
	UtilityService utilityService;

	@Override
	public void execute(DelegateExecution execution) {
		execution.setVariable("scrutinizer_level1", utilityService.getScrutinizerLevel1());
	}
}
