package com.sayho.workoutcalendar.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class WorkoutRecordDao {
	@Autowired
	private SqlSession sqlSession;
	
	private static String NAME_SPACE = "com.sayho.workoutcalendar.dao.WorkoutRecordDao.";
	
	public List<Map<String, Object>> selectWorkoutRecord(Map<String, Object> record) {
		return sqlSession.selectList(NAME_SPACE+"selectWorkoutRecord", record);
	}
 	public int insertWorkoutRecord(Map<String, Object> record){
 		return sqlSession.insert(NAME_SPACE+"insertWorkoutRecord", record);
	}
 	public int deleteWorkoutRecord(Map<String, Object> record){
 		/* NEED TO REPLACE SPLIT FUNCTION TO SUBSTR LATER - 20200721 */
 		String seqList = (String) record.get("seqList");
 		String[] seqArr = seqList.split("\\|");//| is regex so "\\" is needed
 		
 		int retVal = 0;
 		for(int i = 0; i < seqArr.length; ++i) {
 			retVal += sqlSession.delete(NAME_SPACE+"deleteWorkoutRecord", Integer.parseInt(seqArr[i]));
 		}
 		return retVal;
	}
}
