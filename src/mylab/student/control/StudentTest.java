package mylab.student.control;

import mylab.student.entity.Student;
import mylab.student.exception.InvalidGradeException;

public class StudentTest {

	public static void main(String[] args) {
		Student student = new Student();
		
		student.setStudentId("3223368749");
		student.setName("김민수");
		student.setMajor("컴퓨터공학");
		try {
			student.setGrade(3); // 3학년으로 설정
		} catch (InvalidGradeException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("학번 : " + student.getStudentId());
		System.out.println("이름 : " + student.getName());
		System.out.println("전공 : " + student.getMajor());
		System.out.println("학년 : " + student.getGrade());

		try {
			student.setGrade(5); // 5학년으로 변경
			System.out.println("학년 : " + student.getGrade());
		} catch (InvalidGradeException e) {
			System.out.println(e.getMessage());
		}
	}

}
