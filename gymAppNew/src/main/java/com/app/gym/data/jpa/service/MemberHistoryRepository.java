package com.app.gym.data.jpa.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import com.app.gym.data.jpa.domain.MemberHistory;

public interface MemberHistoryRepository extends CrudRepository<MemberHistory, Long> {
	
	List<MemberHistory> findMemberHistoryByMemberId(Long memberId);
	
	MemberHistory save(MemberHistory memberHistory);
	
	List<MemberHistory> findMemberHistoryByPackReachDateBetween(Date fromDate,Date toDate);
	

}
