package com.app.gym.data.jpa;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.app.gym.data.jpa.domain.Member;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Convertor {
	
	public static void main(String [] args) throws JsonProcessingException{
		
		
		ObjectMapper mapper = new ObjectMapper();
		Member obj=new Member();
		obj.setId(50L);
		obj.setAddress("chennai");
		obj.setCurrPackageEndDate(new Date(1462085643000L));
		obj.setEmail("harikrishnansridhar@gmail.com");
		obj.setName("hari");
		
		String jsonString = mapper.writeValueAsString(obj);
		System.out.println(jsonString);
		
	
	}

}
