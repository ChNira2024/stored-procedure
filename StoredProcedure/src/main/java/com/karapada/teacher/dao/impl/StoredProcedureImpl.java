package com.karapada.teacher.dao.impl;

import java.sql.Types;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.karapada.teacher.dao.IStoredProcedureDao;
import com.karapada.teacher.model.StudentsDTO;

@Repository
public class StoredProcedureImpl implements IStoredProcedureDao
{
	private static final Logger logger = LoggerFactory.getLogger(StoredProcedureImpl.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
    private DataSource dataSource;
	
	public void insertDataUsingStoredProcedure(String systemID, String inventoryId, String unitName, String address, String names) {
    

        String insertQuery = "{call insert_student_data3(?, ?, ?, ?, ?)}";
        jdbcTemplate.update(insertQuery, systemID, inventoryId, unitName, address, names);
    }
	
	
	
	@Override
	public StudentsDTO fetchSingleDataUsingStoredProcedure(int sno) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("get_single_student_data_bysno")
                .declareParameters(
                        new SqlParameter("sno_in", Types.INTEGER),
                        new SqlOutParameter("sname_out", Types.VARCHAR),
                        new SqlOutParameter("saddrs_out", Types.VARCHAR),
                        new SqlOutParameter("ssal_out", Types.FLOAT)
                );

        SqlParameterSource in = new MapSqlParameterSource().addValue("sno_in", sno);
        Map<String, Object> resultMap = jdbcCall.execute(in);

        StudentsDTO studentDTO = new StudentsDTO();
        studentDTO.setSno(sno);
        studentDTO.setSname((String) resultMap.get("sname_out"));
        studentDTO.setSaddrs((String) resultMap.get("saddrs_out"));
        studentDTO.setSsal((double) resultMap.get("ssal_out"));

        return studentDTO;
	}
	
	@Override
	public List<StudentsDTO> fetchListDataUsingStoredProcedure(int sno) {
	    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

	    SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
	            .withProcedureName("get_list_students_data_bysno")
	            .declareParameters(
	                    new SqlParameter("sno_in", Types.INTEGER),
	                    new SqlOutParameter("student_data", Types.REF_CURSOR,
	                            new StudentRowMapper())
	            );

	    SqlParameterSource in = new MapSqlParameterSource().addValue("sno_in", sno);
	    Map<String, Object> resultMap = jdbcCall.execute(in);

	    List<StudentsDTO> studentList = (List<StudentsDTO>) resultMap.get("student_data");
	    return studentList;
	}
}

/*
 CREATE OR REPLACE PROCEDURE insert_student_data3(
    systemID IN VARCHAR2,
    inventiryId IN VARCHAR2,
    unitName IN VARCHAR2,
    address IN VARCHAR2,
    names IN VARCHAR2
)
IS
    new_ps_msg_id NUMBER;
BEGIN
    -- Get the next value from the sequence
    SELECT PS_MESSAGE_ID_SEQ.NEXTVAL INTO new_ps_msg_id FROM DUAL;
    
    -- Insert data into PS_INPUT_MESSAGE_STATUS_UPDATE table using the generated ID
    INSERT INTO PS_INPUT_MESSAGE_STATUS_UPDATE (PS_MESSAGE_ID, SYSTEM_ID, PS_INVENTIRY_ID) 
    VALUES (new_ps_msg_id, systemID, inventiryId);
    
    -- Insert data into PS_MESSAGE_DETAILS using the generated ID
    INSERT INTO PS_MESSAGE_DETAILS (PS_MESSAGE_ID, SYSTEM_ID, UNIT_NAME) 
    VALUES (new_ps_msg_id, systemID, unitName);
    
    -- Insert data into PS_HIT_DETAILS using the generated ID
    INSERT INTO PS_HIT_DETAILS (PS_MESSAGE_ID, ADDRESS, NAMES) 
    VALUES (new_ps_msg_id, address, names);
    
    -- Display the generated ID
    DBMS_OUTPUT.PUT_LINE('Generated PS_MESSAGE_ID: ' || new_ps_msg_id);
END;
 */
//CREATE SEQUENCE PS_MESSAGE_ID_SEQ START WITH 1 INCREMENT BY 1 NOCACHE NOCYCLE;



/*
 * 
create or replace PROCEDURE get_single_student_data_bysno (
    sno_in NUMBER,
    sname_out OUT VARCHAR2,
    saddrs_out OUT VARCHAR2,
    ssal_out OUT FLOAT
)
AS
BEGIN
    -- Declare variables to store fetched data
    DECLARE
        v_sname STUDENTS.SNAME%TYPE;
        v_saddrs STUDENTS.SADDRS%TYPE;
        v_ssal STUDENTS.SSAL%TYPE;
    BEGIN
        -- Fetch the data from the STUDENTS table based on sno_in
        SELECT SNAME, SADDRS, SSAL
        INTO v_sname, v_saddrs, v_ssal
        FROM STUDENTS
        WHERE SNO = sno_in; -- Here, sno_in acts as a filter condition

        -- Assign fetched data to output variables
        sname_out := v_sname;
        saddrs_out := v_saddrs;
        ssal_out := v_ssal;
    EXCEPTION
        -- Handle exception if no row found for the provided sno_in
        WHEN NO_DATA_FOUND THEN
            -- Set output variables to null
            sname_out := null;
            saddrs_out := null;
            ssal_out := null;
    END;
END;
 */
/*
 CREATE OR REPLACE PROCEDURE get_list_students_data_bysno (
    sno_in NUMBER,
    student_data OUT SYS_REFCURSOR
)
AS
BEGIN
    -- Open a cursor to fetch multiple rows of data
    OPEN student_data FOR
    SELECT SNO, SNAME, SADDRS, SSAL -- Include SNO in the select statement
    FROM STUDENTS
    WHERE SNO = sno_in; -- Filter based on sno_in

    -- Handle exception if no data is found for the provided sno_in
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            -- Return an empty cursor
            OPEN student_data FOR
            SELECT NULL AS SNO, NULL AS SNAME, NULL AS SADDRS, NULL AS SSAL -- Include NULL AS SNO
            FROM DUAL
            WHERE 1 = 0;
END;
/
 */
//create table STUDENTS(SNO NUMBER,SNAME VARCHAR2(100),SADDRS VARCHAR2(200),SSAL FLOAT);