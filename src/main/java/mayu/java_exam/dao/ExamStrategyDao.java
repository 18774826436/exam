package mayu.java_exam.dao;

import java.util.List;

import mayu.java_exam.entity.Exam;
import mayu.java_exam.entity.ExamStrategy;

public interface ExamStrategyDao {

	List<ExamStrategy> findAll();
	List<ExamStrategy> findByExam(Exam exam);
	ExamStrategy findById(int id);
	void save(ExamStrategy e);
	void update(ExamStrategy e);
	void delete(ExamStrategy e);

}