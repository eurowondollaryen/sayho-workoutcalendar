package com.sayho.workoutcalendar.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sayho.workoutcalendar.service.ChartService;

@RestController
public class ChartController {
	@Autowired
	ChartService service;
	
	@RequestMapping("/chart/getMonthlyChartData")
	public Map<String, Object> getMonthlyChartData() throws Exception {
		//List<Workout> workoutList = service.workoutList();
		Map<String, Object> result = new HashMap<>();
		result.put("status", true);
		result.put("datetime", new Date());
		result.put("data", 1);
		return result;
	}
}
