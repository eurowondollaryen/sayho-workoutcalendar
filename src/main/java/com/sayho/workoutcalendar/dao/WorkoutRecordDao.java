package com.sayho.workoutcalendar.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class WorkoutRecordDao {
	@Autowired
	private SqlSession sqlSession;
	
	private static String NAME_SPACE = "com.sayho.workoutcalendar.dao.WorkoutRecordDao.";
 
 	public int insertWorkoutRecord(Map<String, Object> record){
 		return sqlSession.insert(NAME_SPACE+"insertWorkoutRecord", record);
	}
}
