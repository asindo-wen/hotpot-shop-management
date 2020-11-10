package com.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.enity.Desk;
import com.test.mapper.DeskMapper;
import com.test.service.DeskService;

@Service
public class DeskServiceImpl implements DeskService{
	@Autowired
	DeskMapper deskmapper;
	
	@Override
	public List<Desk> selectAll(){		
		return deskmapper.selectAll();
	}
}
