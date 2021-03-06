package mayu.java_exam.dao;

import java.util.List;

import mayu.java_exam.entity.Exam;
import mayu.java_exam.entity.ExamQuestion;
import mayu.java_exam.entity.ExamQuestionAnswer;
import mayu.java_exam.entity.Student;

public interface ExamQuestionAnswerDao {
	
	ExamQuestionAnswer findById(int id);
	List<ExamQuestionAnswer> findAll();
//	List<ExamQuestionAnswer> findForExam(Exam exam);
	
	//针对对每种题库中的题型搜索
//	List<ExamQuestionAnswer> findForBankChoiceQuestion(BankChoiceQuestion q);
//	List<ExamQuestionAnswer> findForBankBlankFillingQuestion(BankBlankFillingQuestion q);
//	List<ExamQuestionAnswer> findForBankJudgeQuestion(BankJudgeQuestion q);
	
	List<ExamQuestionAnswer> findByExamQuestion(ExamQuestion eq);
	ExamQuestionAnswer findByStudentAndExamQuestion(Student student, ExamQuestion examQuestion);
	
	//针对某个学生的对某次考试的搜索，相当于该学生的一份答卷
	List<ExamQuestionAnswer> findByStudentAndExam(Exam exam,Student student);
	
	void save(ExamQuestionAnswer eqa);
	void update(ExamQuestionAnswer eqa);
	void delete(ExamQuestionAnswer eqa);

}