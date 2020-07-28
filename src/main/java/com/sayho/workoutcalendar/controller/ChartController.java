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
		record.put("partCod", "11");
		List<Map<String, Object>> chartDataChest = service.getMonthlyChartDataByPart(record);
		record.put("partCod", "12");
		List<Map<String, Object>> chartDataBack = service.getMonthlyChartDataByPart(record);
		record.put("partCod", "21");
		List<Map<String, Object>> chartDataLeg = service.getMonthlyChartDataByPart(record);
		
		
		Map<String, Object> result = new HashMap<>();
		result.put("status", true);
		result.put("datetime", new Date());
		result.put("chartDataChest", chartDataChest);
		result.put("chartDataBack", chartDataBack);
		result.put("chartDataLeg", chartDataLeg);
		return result;
	}
}
