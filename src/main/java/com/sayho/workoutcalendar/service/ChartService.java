package com.sayho.workoutcalendar.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sayho.workoutcalendar.dao.ChartDao;

@Service
public class ChartService {
	@Autowired
	ChartDao dao;
	
	//전체 사용자 조회
	public List<Map<String, Object>> getMonthlyChartData(Map<String, Object> record){
			
			return dao.getMonthlyChartData(record);
	}
}
