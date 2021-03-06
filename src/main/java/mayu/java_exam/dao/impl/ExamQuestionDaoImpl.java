package mayu.java_exam.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import mayu.java_exam.dao.ExamQuestionDao;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mayu.java_exam.entity.BankBlankFillingQuestion;
import mayu.java_exam.entity.BankChoiceQuestion;
import mayu.java_exam.entity.BankJudgeQuestion;
import mayu.java_exam.entity.Exam;
import mayu.java_exam.entity.ExamQuestion;

@Component("examQuestionDao")
@Transactional

public class ExamQuestionDaoImpl implements ExamQuestionDao {
	private final static Logger logger = LoggerFactory.getLogger(ExamQuestionDaoImpl.class);
	@Resource
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public ExamQuestion findById(int id) {
		ExamQuestion eq = sessionFactory.getCurrentSession().load(ExamQuestion.class, id);
		return eq;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<ExamQuestion> findAll(){
		Query q=sessionFactory.getCurrentSession().createQuery("from ExamQuestion");
		return q.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<ExamQuestion> findByExam(Exam exam) {
		Query q=sessionFactory.getCurrentSession().createQuery("from ExamQuestion where exam=?0");
		q.setParameter("0", exam);
		return q.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<ExamQuestion> findByBankChoiceQuestion(BankChoiceQuestion question) {
		logger.debug("question="+question);
		Query q=sessionFactory.getCurrentSession().createQuery("from ExamQuestion where bankChoiceQuestion=?0");
		q.setParameter("0", question);
		return q.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<ExamQuestion> findByBankBlankFillingQuestion(BankBlankFillingQuestion question) {
		Query q=sessionFactory.getCurrentSession().createQuery("from ExamQuestion where bankBlankFillingQuestion=?0");
		q.setParameter("0", question);
		return q.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<ExamQuestion> findByBankJudgeQuestion(BankJudgeQuestion question) {
		Query q=sessionFactory.getCurrentSession().createQuery("from ExamQuestion where bankJudgeQuestion=?0");
		q.setParameter("0", question);
		return q.list();
	}
	
	@Override
	public void save(ExamQuestion eq){
		sessionFactory.getCurrentSession().save(eq);
	}
	@Override
	public void update(ExamQuestion eq){
		sessionFactory.getCurrentSession().update(eq);
	}
	@Override
	public void delete(ExamQuestion eq){
		sessionFactory.getCurrentSession().delete(eq);
	}

}
