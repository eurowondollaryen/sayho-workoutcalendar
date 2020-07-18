package com.sayho.workoutcalendar.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sayho.workoutcalendar.model.Workout;
import com.sayho.workoutcalendar.service.MainService;

@RestController
public class MainController {
	@Autowired
	MainService service;
	
	@RequestMapping("/main/getworkoutcode")
	public Map<String, Object> mainView() throws Exception {//@Responsebody를 String 앞에 붙이면 문자열 그자체를 반환함
		List<Workout> workoutList = service.workoutList();
		Map<String, Object> result = new HashMap<>();
		result.put("status", true);
		result.put("datetime", new Date());
		result.put("data", workoutList);
		return result;
	}
}
