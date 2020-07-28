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
	
	//전체 부위에 대해 모든 데이터 조회
	public List<Map<String, Object>> getMonthlyChartData(Map<String, Object> record){
			
			return dao.getMonthlyChartData(record);
	}
	
	//최근 n개월간 부위에 대한 데이터 조회
	public List<Map<String, Object>> getMonthlyChartDataByPart(Map<String, Object> record){
		
		return dao.getMonthlyChartDataByPart(record);
	}
}
