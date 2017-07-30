package com.app.gym.data.jpa.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.app.gym.data.jpa.domain.Member;
import com.app.gym.data.jpa.domain.MemberDailyLogin;
import com.app.gym.data.jpa.domain.MemberHistory;
import com.app.gym.data.jpa.domain.MemberLogin;
import com.app.gym.data.jpa.exceptions.MemberNotFoundException;

@Component("memberService")
@Transactional
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private MemberHistoryRepository memberHistoryRepository;
	@Autowired
	private MemberDailyLoginRepository memberDailyLoginRepository;
	
	@Override
	public Member addNewMember(Member member) {
		// TODO Auto-generated method stub
		return this.memberRepository.save(member);
	}
	
	@Override
	public Member updateExistingMember(Member member) {
		// TODO Auto-generated method stub
		return this.memberRepository.save(member);
	}

	@Override
	public Member findMemberByMemberId(Long memberId) throws MemberNotFoundException {
		Member returnMember=this.memberRepository.findMemberById(memberId);
		if(returnMember == null){
			throw new MemberNotFoundException(memberId);
		}
		return returnMember;
	}

	@Override
	public List<MemberHistory> getMemberHistoryByMemberId(Long memberId) throws MemberNotFoundException {
		// TODO Auto-generated method stub
		Member member=this.memberRepository.findMemberById(memberId);
		if(member == null){
			throw new MemberNotFoundException(memberId);
		}
		return this.memberHistoryRepository.findMemberHistoryByMemberId(memberId);
	}

	@Override
	public MemberHistory addNewMemberHistory(MemberHistory memberHistory) {
		// TODO Auto-generated method stub
		return this.memberHistoryRepository.save(memberHistory);
	}

	@Override
	public MemberLogin performDailyLogin(Long memberId) throws MemberNotFoundException, ParseException {
		// TODO Auto-generated method stub
		Member loginMember = this.memberRepository.findMemberById(memberId);
		if(loginMember == null){
			throw new MemberNotFoundException(memberId);
		}
		SimpleDateFormat dateFormat= new SimpleDateFormat("dd-MM-yy");
		Date loginDate = dateFormat.parse(dateFormat.format(new Date()));
	
		Pageable page = new PageRequest(0, 20);
		List<MemberDailyLogin> membDailyLoginList = this.memberDailyLoginRepository.findMemberDailyLoginByMemberAndLogInDateOrderByIdDesc(loginMember, loginDate);
		return processDailyLoginList(membDailyLoginList,loginDate,loginMember);
		 
	}

	private MemberLogin processDailyLoginList(List<MemberDailyLogin> membDailyLoginList, Date loginDate, Member loginMember) {
		List<MemberDailyLogin> dailyLogInList = membDailyLoginList;
		MemberLogin membLogin=new MemberLogin();
		membLogin.setMember(loginMember);
		Calendar c= new GregorianCalendar();
		// No login was performed yet this is the first entry
		if(dailyLogInList.size() == 0)
		{
			MemberDailyLogin dailyLogin = new MemberDailyLogin();
			dailyLogin.setLogInDate(loginDate);
			dailyLogin.setMember(loginMember);
			dailyLogin.setLogInTime(c.getTime());
			dailyLogin.setLogOutTime(null);
			this.memberDailyLoginRepository.save(dailyLogin);
			membLogin.setLogIn(true);
		}
		if(dailyLogInList.size()>0){
			MemberDailyLogin latestEntry=dailyLogInList.get(0);
			if(latestEntry.getLogOutTime()==null){
				latestEntry.setLogOutTime(c.getTime());
				this.memberDailyLoginRepository.save(latestEntry);
				membLogin.setLogIn(false);
			}
			else{
				MemberDailyLogin dailyLogin = new MemberDailyLogin();
				dailyLogin.setLogInDate(loginDate);
				dailyLogin.setMember(loginMember);
				dailyLogin.setLogInTime(c.getTime());
				dailyLogin.setLogOutTime(null);
				membLogin.setLogIn(true);
				this.memberDailyLoginRepository.save(dailyLogin);
			}
		}
		return membLogin;
	}

	@Override
	public List<MemberDailyLogin> getMemberLoginByMemberId(Long memberId
			) throws MemberNotFoundException {
		Member loginMember = this.memberRepository.findMemberById(memberId);
		if(loginMember == null){
			throw new MemberNotFoundException(memberId);
		}
		return this.memberDailyLoginRepository.findMemberDailyLoginByMemberId(memberId);
	}

	@Override
	public List<MemberDailyLogin> getMemberLoginByDate(Date loginDate
			) {
		// TODO Auto-generated method stub
		return this.memberDailyLoginRepository.findMemberDailyLoginByLogInDateOrderByMemberId(loginDate);
	}

	@Override
	public List<Member> findAllMembers() {
		return memberRepository.findAllByOrderByIdAsc();
	}

	@Override
	public List<MemberDailyLogin> getLoginByFromAndTo(Date fromDate, Date toDate) {
		// TODO Auto-generated method stub
		return memberDailyLoginRepository.findMemberDailyLoginByLogInDateBetween(fromDate, toDate);
	}

	@Override
	public List<MemberHistory> getPackageDetailsFromAndTo(Date fromDate,
			Date toDate) {
		return memberHistoryRepository.findMemberHistoryByPackReachDateBetween(fromDate, toDate);
	}

}
