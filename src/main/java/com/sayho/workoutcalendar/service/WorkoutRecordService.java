package com.sayho.workoutcalendar.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sayho.workoutcalendar.dao.WorkoutRecordDao;
import com.sayho.workoutcalendar.model.WorkoutRecord;

@Service
public class WorkoutRecordService {
	@Autowired
	WorkoutRecordDao dao;
	
	public int insertWorkoutRecord(Map<String, Object> record){
			return dao.insertWorkoutRecord(record);
	}
}
