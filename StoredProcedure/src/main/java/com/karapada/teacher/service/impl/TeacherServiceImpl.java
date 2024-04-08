package com.karapada.teacher.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karapada.teacher.dao.IStoredProcedureDao;
import com.karapada.teacher.model.StudentsDTO;
import com.karapada.teacher.service.IStoredProcedureService;

@Service
public class TeacherServiceImpl implements IStoredProcedureService
{
	private static final Logger logger = LoggerFactory.getLogger(TeacherServiceImpl.class);
	
	@Autowired
	private IStoredProcedureDao iStoredProcedureDao;


	@Override
	public void insertDataUsingStoredProcedure(String systemID, String inventoryId, String unitName, String address,String names) 
	{	
		iStoredProcedureDao.insertDataUsingStoredProcedure(systemID, inventoryId, unitName, address, names);
	}

	@Override
	public StudentsDTO fetchSingleDataUsingStoredProcedure(int sno) 
	{
		return  iStoredProcedureDao.fetchSingleDataUsingStoredProcedure(sno);
	}
	
	@Override
	public List<StudentsDTO> fetchListDataUsingStoredProcedure(int sno) 
	{
		return  iStoredProcedureDao.fetchListDataUsingStoredProcedure(sno);
	}
	
	 

}
