package com.app.gym.data.jpa.web;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.gym.data.jpa.domain.MemberLogin;
import com.app.gym.data.jpa.exceptions.MemberNotFoundException;
import com.app.gym.data.jpa.service.MemberService;

@RestController
//@CrossOrigin
@RequestMapping(value="/daily",
produces={MediaType.APPLICATION_JSON_VALUE})
public class DailyLoginService {
	@Autowired
	MemberService memberService;
	
	@RequestMapping(value="/dailyLogin",params={"id"},method=RequestMethod.GET)
	public MemberLogin performDailyLogin(@RequestParam(value="id")Long id) throws MemberNotFoundException, ParseException{
		return memberService.performDailyLogin(id);
	}
}
