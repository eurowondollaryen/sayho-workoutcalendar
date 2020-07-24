package com.sayho.workoutcalendar.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sayho.workoutcalendar.service.ChartService;

@RestController
public class ChartController {
	@Autowired
	ChartService service;
	
	@RequestMapping("/chart/getMonthlyChartData")
	public Map<String, Object> getMonthlyChartData(@RequestParam Map<String, Object> record,
			HttpServletRequest request) throws Exception {
		List<Map<String, Object>> chartData = service.getMonthlyChartData(record);
		Map<String, Object> result = new HashMap<>();
		result.put("status", true);
		result.put("datetime", new Date());
		result.put("data", chartData);
		return result;
	}
}
