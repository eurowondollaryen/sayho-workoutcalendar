package com.sayho.workoutcalendar.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ChartDao {
	@Autowired
	private SqlSession sqlSession;
	
	private static String NAME_SPACE = "com.sayho.workoutcalendar.dao.ChartDao.";
 
 	public List<Map<String, Object>> getMonthlyChartData(){
 		return sqlSession.selectList(NAME_SPACE+"testQuery");
	}
}
