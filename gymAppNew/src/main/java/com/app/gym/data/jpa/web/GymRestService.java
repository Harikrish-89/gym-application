package com.app.gym.data.jpa.web;

import java.security.Principal;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.gym.data.jpa.domain.Constants;
import com.app.gym.data.jpa.domain.Member;
import com.app.gym.data.jpa.domain.MemberDailyLogin;
import com.app.gym.data.jpa.domain.MemberHistory;
import com.app.gym.data.jpa.domain.MemberLogin;
import com.app.gym.data.jpa.exceptions.MemberNotFoundException;
import com.app.gym.data.jpa.service.MemberService;

@RestController
//@CrossOrigin
@RequestMapping(value="/gym",
produces={MediaType.APPLICATION_JSON_VALUE})
public class GymRestService {

	@Autowired
	MemberService memberService;
	
	@RequestMapping("/user")
	  public Principal user(Principal user) {
	    return user;
	  }
	  
	@RequestMapping(value="/addmember",consumes={MediaType.APPLICATION_JSON_VALUE},method=RequestMethod.POST)
	public Member addNewMemberAndMemberHistory(@RequestBody Member member){
		Calendar c = new GregorianCalendar();
		c.setTime(member.getCurrPackRechargeDate());
		//member.setCurrPackRechargeDate(c.getTime());
		int noOfDays = Constants.packageRevMap.get(member.getPackageType());
		c.add(Calendar.DATE, noOfDays);
		member.setCurrPackageEndDate(c.getTime());
		member.setStatus(Constants.ACTIVE);
		Member insertedMember = memberService.addNewMember(member);
		MemberHistory memberHistory= new MemberHistory();
		memberHistory.setMember(insertedMember);
		memberHistory.setPackReachDate(insertedMember.getCurrPackRechargeDate());
		memberHistory.setPackageEndDate(insertedMember.getCurrPackageEndDate());
		memberHistory.setPackageType(insertedMember.getPackageType());
		memberHistory.setRechargeType(Constants.JOIN);
		memberService.addNewMemberHistory(memberHistory);
		return insertedMember;
		
	}
	
	@RequestMapping(value="/updateExisting",consumes={MediaType.APPLICATION_JSON_VALUE},method=RequestMethod.POST)
	public Member updateExistingMember(@RequestBody Member member){
		int noOfDays= Constants.packageRevMap.get(member.getPackageType());
		Calendar c= new GregorianCalendar();
		c.setTime(member.getCurrPackRechargeDate());
		c.add(Calendar.DATE, noOfDays);
		member.setCurrPackageEndDate(c.getTime());
		member.setStatus(Constants.ACTIVE);
		Member updatedMember=memberService.updateExistingMember(member);
		MemberHistory memberHistory= new MemberHistory();
		memberHistory.setMember(updatedMember);
		memberHistory.setPackReachDate(updatedMember.getCurrPackRechargeDate());
		memberHistory.setPackageEndDate(updatedMember.getCurrPackageEndDate());
		memberHistory.setPackageType(updatedMember.getPackageType());
		memberHistory.setRechargeType(Constants.RENEWAL);
		memberService.addNewMemberHistory(memberHistory);
		return updatedMember;
		
	}
	@RequestMapping(value="/extendExisting",consumes={MediaType.APPLICATION_JSON_VALUE},method=RequestMethod.POST)
	public Member extendExistingMember(@RequestBody Member member){
		member.setStatus(Constants.ACTIVE);
		Member updatedMember=memberService.updateExistingMember(member);
		MemberHistory memberHistory= new MemberHistory();
		memberHistory.setMember(updatedMember);
		memberHistory.setPackReachDate(updatedMember.getCurrPackRechargeDate());
		memberHistory.setPackageEndDate(updatedMember.getCurrPackageEndDate());
		memberHistory.setPackageType(updatedMember.getPackageType());
		memberHistory.setRechargeType(Constants.EXTENSION);
		memberService.addNewMemberHistory(memberHistory);
		return updatedMember;
		
	}
/*	@RequestMapping(value="/updateExpired",consumes={MediaType.APPLICATION_JSON_VALUE},method=RequestMethod.POST)
	public Member updateExpiredMember(@RequestBody Member member){
		Calendar c = new GregorianCalendar();
		member.setCurrPackRechargeDate(c.getTime());
		int noOfDays = Constants.packageRevMap.get(member.getPackageType());
		c.add(Calendar.DATE, noOfDays);
		member.setCurrPackageEndDate(c.getTime());
		member.setStatus(Constants.ACTIVE);
		Member updatedMember = memberService.updateExistingMember(member);
		MemberHistory memberHistory= new MemberHistory();
		memberHistory.setMember(updatedMember);
		memberHistory.setPackReachDate(updatedMember.getCurrPackRechargeDate());
		memberHistory.setPackageEndDate(updatedMember.getCurrPackageEndDate());
		memberHistory.setPackageType(updatedMember.getPackageType());
		return updatedMember;
		
	}*/
	@RequestMapping(value="/getMember",params={"id"},method=RequestMethod.GET)
	public Member getMemberById(@RequestParam(value="id") Long id) throws MemberNotFoundException{
		
		return memberService.findMemberByMemberId(id);
		
	}
	@RequestMapping(value="/getAllMembers",method=RequestMethod.GET)
	public List<Member> getAllMembers() throws MemberNotFoundException{
		
		return memberService.findAllMembers();
		
	}
	
	@RequestMapping(value="/getMemberHistory",params={"id"},method=RequestMethod.GET)
	public List<MemberHistory> getMemberHistoryByMemberId(@RequestParam(value="id")Long id) throws MemberNotFoundException{
	
		return memberService.getMemberHistoryByMemberId(id);
		
	}
	

	
	@RequestMapping(value="/getDailyLoginDetailsForMemberId",params={"id"},method=RequestMethod.GET)
	public List<MemberDailyLogin> getDailyLoginDetailsForMemberId(@RequestParam(value="id")Long id) throws MemberNotFoundException, ParseException{
		return memberService.getMemberLoginByMemberId(id);
	}
	
	@RequestMapping(value="/getDailyLoginDetailsForDate",params={"date"},method=RequestMethod.GET)
	public List<MemberDailyLogin> getDailyLoginDetailsForMemberId(@RequestParam(value="date")Date date) throws MemberNotFoundException, ParseException{
		return memberService.getMemberLoginByDate(date);
	}
	@RequestMapping(value="/getLoginDetailsByDate",params={"fromDate","toDate"},method=RequestMethod.GET)
	public List<MemberDailyLogin> getLoginDetailsByDate(@RequestParam(value="fromDate")Date fromDate, @RequestParam(value="toDate") Date toDate) throws MemberNotFoundException, ParseException{
		return memberService.getLoginByFromAndTo(fromDate, toDate);
	}
	@RequestMapping(value="/getPackageDetailsByDate",params={"fromDate","toDate"},method=RequestMethod.GET)
	public List<MemberHistory> getPackageDetailsByDate(@RequestParam(value="fromDate")Date fromDate, @RequestParam(value="toDate") Date toDate) throws MemberNotFoundException, ParseException{
		return memberService.getPackageDetailsFromAndTo(fromDate, toDate);
	}
}
