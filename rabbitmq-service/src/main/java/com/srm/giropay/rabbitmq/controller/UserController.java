package com.srm.giropay.rabbitmq.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.srm.giropay.rabbitmq.dto.UserDetails;
import com.srm.giropay.rabbitmq.service.UserService;

@RestController
@RequestMapping(path = "/userservice")
@AllArgsConstructor
public class UserController {

	private UserService userService;


	@RequestMapping(path = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> sendMessage(@RequestBody UserDetails user) {
		return userService.sendMessage(user);

	

	}
}
