package mylab.student.control;

import mylab.student.entity.Student;
import mylab.student.exception.InvalidGradeException;

public class StudentTest {

	public static void main(String[] args) {
		Student student = new Student();
		
		student.setStudentId("3223368749");
		student.setName("��μ�");
		student.setMajor("��ǻ�Ͱ���");
		try {
			student.setGrade(3); // 3�г����� ����
		} catch (InvalidGradeException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("�й� : " + student.getStudentId());
		System.out.println("�̸� : " + student.getName());
		System.out.println("���� : " + student.getMajor());
		System.out.println("�г� : " + student.getGrade());

		try {
			student.setGrade(5); // 5�г����� ����
			System.out.println("�г� : " + student.getGrade());
		} catch (InvalidGradeException e) {
			System.out.println(e.getMessage());
		}
	}

}
