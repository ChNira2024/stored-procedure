package com.karapada.teacher.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.karapada.teacher.model.InsertDataRequestDTO;
import com.karapada.teacher.model.StudentsDTO;
import com.karapada.teacher.service.IStoredProcedureService;

@RestController
public class StoredProcedureController {


	@Autowired
	private IStoredProcedureService service;
	
	@PostMapping("/insertDataUsingStoredProcedure")
	public String insertData(@RequestBody InsertDataRequestDTO requestDTO) 
	{
	    service.insertDataUsingStoredProcedure(requestDTO.getSystemID(), requestDTO.getInventoryId(),requestDTO.getUnitName(),requestDTO.getAddress(), requestDTO.getNames());
	    return "Data inserted successfully!";
	}
	
	
	@GetMapping("/fetch/{sno}")
	public ResponseEntity<StudentsDTO> fetchSingleDataById(@PathVariable int sno) 
	{
	   StudentsDTO sdata = service.fetchSingleDataUsingStoredProcedure(sno);
	   System.out.println("sdata"+sdata);
	   if(sdata!=null)
	   {
		   return new ResponseEntity<>(sdata,HttpStatus.OK);
	   }
	   else
	   {
		   return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	   }
	}

	@GetMapping("/fetchList/{sno}")
	public ResponseEntity<List<StudentsDTO>> fetchListDataById(@PathVariable int sno) 
	{
	   List<StudentsDTO> sListDdata = service.fetchListDataUsingStoredProcedure(sno);
	   System.out.println("sListDdata: "+sListDdata);
	   if(sListDdata!=null)
	   {
		   return new ResponseEntity<>(sListDdata,HttpStatus.OK);
	   }
	   else
	   {
		   return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	   }
	}
}