package com.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.enity.Desk;
import com.test.service.DeskService;

@RestController
@RequestMapping("/desk")
@CrossOrigin(allowCredentials = "true")
public class DeskController {
	@Autowired
	DeskService deskservice;
	
	@GetMapping("/selectAllDesk")
	@ResponseBody
	public List<Desk> selectAll() {

		return deskservice.selectAll();
	}
}
