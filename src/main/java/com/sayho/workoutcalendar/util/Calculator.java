package com.sayho.workoutcalendar.util;

public class Calculator {
	public float calculateVolume(int weight, String reps) {
		float result = 0.17f;//FOR TEST
		//System.out.println(reps);
		String[] eachReps = reps.split("\\|");
		
		Integer totalReps = 0;
		for(int i = 0; i < eachReps.length; ++i) {
			totalReps += Integer.parseInt(eachReps[i]);
		}
		result = weight * totalReps;
		System.out.println("totalReps : " + totalReps);
		System.out.println("result : " + result);
		return result;
	}
}
