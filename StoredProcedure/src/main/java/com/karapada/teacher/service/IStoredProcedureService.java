package com.karapada.teacher.service;

import java.util.List;

import com.karapada.teacher.model.StudentsDTO;

public interface IStoredProcedureService 
{
	

    public void insertDataUsingStoredProcedure(String systemID, String inventoryId, String unitName, String address, String names);
    
    public StudentsDTO fetchSingleDataUsingStoredProcedure(int sno);

	public List<StudentsDTO> fetchListDataUsingStoredProcedure(int sno);


}
