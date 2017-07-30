package com.app.gym.data.jpa.service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.app.gym.data.jpa.domain.Member;
import com.app.gym.data.jpa.domain.MemberDailyLogin;
import com.app.gym.data.jpa.domain.MemberHistory;
import com.app.gym.data.jpa.domain.MemberLogin;
import com.app.gym.data.jpa.exceptions.MemberNotFoundException;

public interface MemberService {
	
	Member addNewMember(Member member);
	
	Member findMemberByMemberId(Long memberId) throws MemberNotFoundException;
	
	List<MemberHistory> getMemberHistoryByMemberId(Long memberId) throws MemberNotFoundException;
	
	MemberHistory addNewMemberHistory(MemberHistory memberHistory);

	Member updateExistingMember(Member member);
	
	MemberLogin performDailyLogin(Long memberId) throws MemberNotFoundException, ParseException;
	
	List<MemberDailyLogin> getMemberLoginByMemberId(Long memberId) throws MemberNotFoundException;
	
	List<MemberDailyLogin> getMemberLoginByDate(Date loginDate);
	
	List<Member> findAllMembers();
	
	List<MemberDailyLogin> getLoginByFromAndTo(Date fromDate,Date toDate);
	
	List<MemberHistory> getPackageDetailsFromAndTo(Date fromDate,Date toDate);
	
}
