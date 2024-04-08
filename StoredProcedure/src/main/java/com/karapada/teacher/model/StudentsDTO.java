package com.karapada.teacher.model;

public class StudentsDTO {

	private int sno;
	private String sname;
	
	private String saddrs;
	private double ssal;
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSaddrs() {
		return saddrs;
	}
	public void setSaddrs(String saddrs) {
		this.saddrs = saddrs;
	}
	public double getSsal() {
		return ssal;
	}
	public void setSsal(double ssal) {
		this.ssal = ssal;
	}
	public StudentsDTO(int sno, String sname, String saddrs, double ssal) {
		super();
		this.sno = sno;
		this.sname = sname;
		this.saddrs = saddrs;
		this.ssal = ssal;
	}
	public StudentsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "StudentsDTO [sno=" + sno + ", sname=" + sname + ", saddrs=" + saddrs + ", ssal=" + ssal + "]";
	}
	
	
}
