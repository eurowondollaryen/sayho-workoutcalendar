package com.sayho.workoutcalendar.util;

import java.util.Iterator;
import java.util.Map;

public class DataChecker {
	public int hashMapNullCheck(Map<String, Object> inp) {
		int ret = 0;
		Iterator<String> keys = inp.keySet().iterator();
		while(keys.hasNext()) {
			String key = keys.next();
			if(inp.get(key) == null) ret--;
		}
		return ret;
	}
}
