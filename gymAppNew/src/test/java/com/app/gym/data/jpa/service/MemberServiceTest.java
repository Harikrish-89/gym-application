/*package com.app.gym.data.jpa.service;

import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.validation.constraints.AssertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.app.gym.data.jpa.SampleDataJpaApplication;
import com.app.gym.data.jpa.domain.Constants;
import com.app.gym.data.jpa.domain.Member;
import com.app.gym.data.jpa.domain.MemberHistory;
import com.app.gym.data.jpa.exceptions.MemberNotFoundException;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SampleDataJpaApplication.class)
public class MemberServiceTest {
	
	@Autowired 
	MemberService memberService;
	
	@Test
	public void addNewMemberTest(){
		
		Member testMember = new Member();
		testMember.setAddress("MaraiMalaiNagar");
		testMember.setName("hariKris");
		Calendar c = new GregorianCalendar();
		Date currPackRechargeDate = c.getTime();
		c.add(Calendar.DATE, 30);
		Date currPackEnddate=c.getTime();
		testMember.setCurrPackRechargeDate(currPackRechargeDate);
		testMember.setCurrPackageEndDate(currPackEnddate);
		testMember.setEmail("harikrishnans@gmail.com");
		testMember.setPackageType(Constants.packageMap.get(30));
		Member insertedMember=memberService.addNewMember(testMember);
		assertTrue(insertedMember.getEmail().equals("harikrishnans@gmail.com"));
	}
	@Test
	public void findMemberandaddNewMemberHistoryTest() throws MemberNotFoundException{
		Member member = memberService.findMemberByMemberId(50L);
		//member.setAddress("chennai");
		//Member updatedMember=memberService.updateExistingMember(member);
		assertTrue(member.getEmail().equals("harikrishnansridhar@gmail.com"));
		//assertTrue(updatedMember.getAddress().equals("chennai"));
		MemberHistory membHist=new MemberHistory();
		membHist.setPackageType(member.getPackageType());
		membHist.setMember(member);
		membHist.setPackageEndDate(member.getCurrPackageEndDate());
		membHist.setPackReachDate(member.getCurrPackRechargeDate());
		MemberHistory insertedMembHist= memberService.addNewMemberHistory(membHist);
		assertTrue(insertedMembHist.getMember().getId().equals(member.getId()));
	}
	
	@Test
	public void findMemberHistoryByMemberIdTest() throws MemberNotFoundException{
		
		List<MemberHistory> membHist = memberService.getMemberHistoryByMemberId(50L);
		System.out.println("MemberHistory Detaisl:::"+membHist.get(0));
		assertTrue(membHist.size()>0);
		
	}

	
}
*/