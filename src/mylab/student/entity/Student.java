package mylab.student.entity;

import mylab.student.exception.InvalidGradeException;

public class Student {
	private String studentId;
	private String name;
	private String major;
	private int grade;
	
	public Student() {
		System.out.println("기본생성자 호출됨");
	}
	
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) throws InvalidGradeException {
		if(grade > 4) {
			String errMessage = String.format("학년은 1~4 사이여야 합니다. (변경을 요청한 학년: %d)", grade);
			throw new InvalidGradeException(errMessage);
		}
		this.grade = grade;
	}

}
