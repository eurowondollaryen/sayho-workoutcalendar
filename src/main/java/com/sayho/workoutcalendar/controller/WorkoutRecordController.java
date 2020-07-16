package com.sayho.workoutcalendar.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sayho.workoutcalendar.service.WorkoutRecordService;

@RestController
public class WorkoutRecordController {
	@Autowired
	WorkoutRecordService service;
	
	@RequestMapping("/insertWorkoutRecord")
	public Map<String, Object> insertWorkoutRecord(@RequestParam Map<String, Object> record,
			HttpServletRequest request) throws Exception {//@Responsebody를 String 앞에 붙이면 문자열 그자체를 반환함
		System.out.println(record);
		record.put("restTimeSec", Integer.parseInt((String)record.get("restTimeSec")));
		record.put("sets", Integer.parseInt((String)record.get("sets")));
		record.put("weight", Integer.parseInt((String)record.get("weight")));
		//record.get("restTimeSec")
		int insertResult = service.insertWorkoutRecord(record);
		Map<String, Object> result = new HashMap<>();
		result.put("status", true);
		result.put("datetime", new Date());
		result.put("data", insertResult);
		return result;
	}
}
