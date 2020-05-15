package mayu.java_exam.dao;

import java.util.List;

import mayu.java_exam.entity.Grade;

public interface GradeDao {
	
	Grade findById(int id);
	List<Grade> findAll();
	List<Grade> findByName(String name);
	void save(Grade g);
	void update(Grade g);
	void delete(Grade g);

}