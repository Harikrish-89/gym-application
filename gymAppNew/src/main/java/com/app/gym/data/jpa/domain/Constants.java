package com.app.gym.data.jpa.domain;

import java.util.HashMap;
import java.util.Map;

public class Constants {
	
	public static Map<Integer,String> packageMap= new HashMap<Integer,String>(){
	{
		put(30,"MONTHLY");
		put(90,"QUARTERLY");
		put(180,"HALFYEARLY");
		put(365,"YEARLY");
		
	}};
	public static Map<String,Integer> packageRevMap= new HashMap<String,Integer>(){
	{
		put("MONTHLY",30);
		put("QUARTERLY",90);
		put("HALFYEARLY",180);
		put("YEARLY",365);
		
	}};

	public static final String ACTIVE="ACTIVE";
	public static final String INACTIVE="INACTIVE";
	public static final String JOIN="JOINING";
	public static final String RENEWAL="RENEWAL";
	public static final String EXTENSION="EXTENSION";
}
