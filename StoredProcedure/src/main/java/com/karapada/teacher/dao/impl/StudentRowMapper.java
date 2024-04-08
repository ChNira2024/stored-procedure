package com.karapada.teacher.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.karapada.teacher.model.StudentsDTO;

public class StudentRowMapper implements RowMapper<StudentsDTO> {
    @Override
    public StudentsDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        StudentsDTO studentDTO = new StudentsDTO();
        studentDTO.setSno(rs.getInt("SNO"));
        studentDTO.setSname(rs.getString("SNAME"));
        studentDTO.setSaddrs(rs.getString("SADDRS"));
        studentDTO.setSsal(rs.getFloat("SSAL"));
        return studentDTO;
    }
}

