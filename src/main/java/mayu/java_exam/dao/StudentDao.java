package mayu.java_exam.dao;

import java.io.File;
import java.util.List;

import mayu.java_exam.entity.Grade;
import mayu.java_exam.entity.Student;

public interface StudentDao {
	Student findByRegNoAndPassword(String name, String password);
	List<Student> findStudentForSearch(String regNo, String name, Boolean gender, String gradeName);
	
	Student findById(int id);
	List<Student> findAll();
	List<Student> findByGrade(Grade g);
	
	void save(Student s);
	void update(Student s);
	void delete(Student s);
	
	int importFromTxt(File file);

}