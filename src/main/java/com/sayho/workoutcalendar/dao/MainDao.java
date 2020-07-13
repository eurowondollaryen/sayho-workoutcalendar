package com.sayho.workoutcalendar.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sayho.workoutcalendar.model.Workout;

@Repository
public class MainDao {
	@Autowired
	private SqlSession sqlSession;
	
	private static String NAME_SPACE = "com.sayho.workoutcalendar.dao.MainDao.";
 
 	public List<Workout> workoutList(){
 		return sqlSession.selectList(NAME_SPACE+"testQuery");
	}
}
