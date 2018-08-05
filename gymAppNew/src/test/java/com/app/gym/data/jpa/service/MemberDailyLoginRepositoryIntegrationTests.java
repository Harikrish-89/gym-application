/*package com.app.gym.data.jpa.service;

import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.app.gym.data.jpa.SampleDataJpaApplication;
import com.app.gym.data.jpa.domain.Member;
import com.app.gym.data.jpa.domain.MemberDailyLogin;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SampleDataJpaApplication.class)
public class MemberDailyLoginRepositoryIntegrationTests {
	
	@Autowired
	private MemberDailyLoginRepository dailyLoginReporsitory;
	@Autowired
	private MemberRepository memberRepository;
	
	@Test
	public void testInsertion() throws ParseException{
		Member member = this.memberRepository.findMemberById(50L);
		MemberDailyLogin memberLogin= new MemberDailyLogin();
		memberLogin.setMember(member);
		Date logInTime =  new GregorianCalendar().getTime();
		memberLogin.setLogInTime(logInTime);
		memberLogin.setLogOutTime(logInTime);
		SimpleDateFormat dateFormat= new SimpleDateFormat("dd-MM-yy");
		Date loginDate = dateFormat.parse(dateFormat.format(new Date()));
		memberLogin.setLogInDate(loginDate);
		MemberDailyLogin insertedval=this.dailyLoginReporsitory.save(memberLogin);
		assertTrue(insertedval.getMember().getId()==50);
		
	}
	@Test
	public void testExtraction() throws ParseException{
		Member member = this.memberRepository.findMemberById(1110L);
		assertTrue(member == null);
		SimpleDateFormat dateFormat= new SimpleDateFormat("dd-MM-yy");
		Date loginDate = dateFormat.parse(dateFormat.format(new Date()));
	
		Pageable page = new PageRequest(0, 20);
		List<MemberDailyLogin> dailyLogin=this.dailyLoginReporsitory.
				findMemberDailyLoginByMemberAndLogInDateOrderByIdDesc(member,loginDate);
		assertTrue(dailyLogin.size()==0);
	}
}
*/