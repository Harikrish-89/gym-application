package com.app.gym.data.jpa.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.app.gym.data.jpa.domain.Member;
import com.app.gym.data.jpa.domain.MemberDailyLogin;


public interface MemberDailyLoginRepository extends CrudRepository<MemberDailyLogin, Long>  {

	List<MemberDailyLogin> findMemberDailyLoginByMemberId(Long memberId);
	
	List<MemberDailyLogin> findMemberDailyLoginByLogInDateOrderByMemberId(Date login);
	
	List<MemberDailyLogin> findMemberDailyLoginByMemberAndLogInDateOrderByIdDesc(Member member,Date logInDate);
	
	MemberDailyLogin save(MemberDailyLogin login);
	
	List<MemberDailyLogin> findMemberDailyLoginByLogInDateBetween(Date fromDate,Date toDate);
}
