package com.app.gym.data.jpa.web;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.app.gym.data.jpa.exceptions.MemberNotFoundException;

@ControllerAdvice
public class ExceptionControllerAdvice {
	
	@ExceptionHandler(MemberNotFoundException.class)
	public void handleMemberNotFound(HttpServletResponse response) throws IOException{
		response.sendError(HttpStatus.BAD_REQUEST.value(), "Provided User Id not found in DB");
	}

}
