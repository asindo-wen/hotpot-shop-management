package com.test.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.test.enity.Desk;

@Service
public interface DeskService {
	
	List<Desk> selectAll();

}
