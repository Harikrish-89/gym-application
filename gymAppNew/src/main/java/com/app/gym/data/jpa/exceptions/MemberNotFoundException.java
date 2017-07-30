package com.app.gym.data.jpa.exceptions;

public class MemberNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public MemberNotFoundException(Long id){
		super("Member with Id:"+id+"Not found in database");
	}

}
