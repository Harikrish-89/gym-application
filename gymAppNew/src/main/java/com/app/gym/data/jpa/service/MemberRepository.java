package com.app.gym.data.jpa.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;

import com.app.gym.data.jpa.domain.Member;



public interface MemberRepository extends CrudRepository<Member, Long> {
		
	List<Member> findAllByOrderByIdAsc();
	
	Member findMemberById(Long id);
	
	Member save(Member member);
	
}
