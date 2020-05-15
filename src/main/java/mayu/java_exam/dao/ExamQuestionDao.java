package mayu.java_exam.dao;

import java.util.List;

import mayu.java_exam.entity.BankBlankFillingQuestion;
import mayu.java_exam.entity.BankChoiceQuestion;
import mayu.java_exam.entity.BankJudgeQuestion;
import mayu.java_exam.entity.Exam;
import mayu.java_exam.entity.ExamQuestion;

public interface ExamQuestionDao {
	ExamQuestion findById(int id);
	List<ExamQuestion> findAll();
	List<ExamQuestion> findByExam(Exam e);
	
	List<ExamQuestion> findByBankChoiceQuestion(BankChoiceQuestion q);
	List<ExamQuestion> findByBankBlankFillingQuestion(BankBlankFillingQuestion q);
	List<ExamQuestion> findByBankJudgeQuestion(BankJudgeQuestion q);
	
	void save(ExamQuestion eq);
	void update(ExamQuestion eq);
	void delete(ExamQuestion eq);

}