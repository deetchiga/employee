package net.guides.springboot2.springboot2jpacrudexample.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Department")
public class Department {

	private long id;
	private String Deptname;
	
	public Department() {
		
	}
	
	public Department(String Deptname) {
		this.Deptname = Deptname;
		}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name = "Dept_name",nullable = false)
	public String getDeptname() {
		return Deptname;
	}
	public void setDeptname(String Deptname) {
		this.Deptname = Deptname;
	}
	
	
	@Override
	public String toString() {
		return "department [id=" + id + ", dept_name=" + Deptname + "]";

	}
	
}
