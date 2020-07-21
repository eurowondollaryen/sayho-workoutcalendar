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

import com.sayho.workoutcalendar.model.Workout;
import com.sayho.workoutcalendar.service.WorkoutRecordService;
import com.sayho.workoutcalendar.util.Calculator;
import com.sayho.workoutcalendar.util.DataChecker;

@RestController
public class WorkoutRecordController {
	@Autowired
	WorkoutRecordService service;
	
	@RequestMapping("/wr/selectWorkoutRecord")
	public Map<String, Object> selectWorkoutRecord(@RequestParam Map<String, Object> record,
			HttpServletRequest request) throws Exception {
		List<Map<String, Object>> workoutRecordList = service.selectWorkoutRecord(record);
		Map<String, Object> result = new HashMap<>();
		result.put("status", true);
		result.put("datetime", new Date());
		result.put("data", workoutRecordList);
		return result;
	}
	
	@RequestMapping("/wr/insertWorkoutRecord")
	public Map<String, Object> insertWorkoutRecord(@RequestParam Map<String, Object> record,
			HttpServletRequest request) throws Exception {//@Responsebody를 String 앞에 붙이면 문자열 그자체를 반환함
		DataChecker dc = new DataChecker();//for null check
		Calculator c = new Calculator();//for calculate volume
		Integer restTimeSec = Integer.parseInt((String)record.get("restTimeSec"));
		Integer sets = Integer.parseInt((String)record.get("sets"));
		Integer weight = Integer.parseInt((String)record.get("weight"));
		String reps = (String) record.get("strReps");
		
		record.put("restTimeSec", restTimeSec);
		record.put("sets", sets);
		record.put("weight", weight);
		//CALCULATE VOLUME
		record.put("volume", c.calculateVolume(weight, reps));
		
		/* PREPARE RESULT */
		Map<String, Object> result = new HashMap<>();
		result.put("status", true);
		result.put("datetime", new Date());
		
		if(dc.hashMapNullCheck(record) < 0) result.put("data", "NullExists");
		else result.put("data", service.insertWorkoutRecord(record));
		return result;
	}
	
	@RequestMapping("/wr/deleteWorkoutRecord")
	public Map<String, Object> deleteWorkoutRecord(@RequestParam Map<String, Object> record,
			HttpServletRequest request) throws Exception {
		Map<String, Object> result = new HashMap<>();
		result.put("status", true);
		result.put("datetime", new Date());
		result.put("data", service.deleteWorkoutRecord(record));
		return result;
	}
}
