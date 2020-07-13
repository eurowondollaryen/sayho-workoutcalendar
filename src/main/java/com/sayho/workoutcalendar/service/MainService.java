package com.sayho.workoutcalendar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sayho.workoutcalendar.dao.MainDao;
import com.sayho.workoutcalendar.model.Workout;

@Service
public class MainService {
	@Autowired
	MainDao dao;
	
	//전체 사용자 조회
	public List<Workout> workoutList(){
			
			return dao.workoutList();
	}
}
