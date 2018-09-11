package com.offcn.bean;

import java.io.Serializable;

public class Employee implements Serializable{
	private Integer id;
	private String eName;
	private Integer gender;
	private double salary;
	public Employee() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String geteName() {
		return eName;
	}
	public void seteName(String eName) {
		this.eName = eName;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", eName=" + eName + ", gender=" + gender
				+ ", salary=" + salary + "]";
	}
	
}
