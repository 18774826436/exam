package cn.lynu.lyq.java_exam.actions;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.lynu.lyq.java_exam.dao.ExamDao;
import cn.lynu.lyq.java_exam.dao.GradeDao;
import cn.lynu.lyq.java_exam.dao.StudentExamScoreDao;
import cn.lynu.lyq.java_exam.entity.Grade;
import cn.lynu.lyq.java_exam.entity.Student;

@Component("scoreStats")
@Scope("prototype")
public class ScoreStatsAction extends ActionSupport {
	private static final long serialVersionUID = -1980837224909869714L;
	private final static Logger logger = LoggerFactory.getLogger(ScoreStatsAction.class);
	
	@Resource
	private GradeDao gradeDao;
	@Resource
	private ExamDao examDao;
	@Resource
	private StudentExamScoreDao studentExamScoreDao;
	
	private List<Grade> gradeList = new ArrayList<>();
	private List<String> examNameList = new ArrayList<>();
	private String classSearch;
	private String examNameSearch;
	private List<Student> examAbsentStudentList=new ArrayList<>();
	
	public List<Grade> getGradeList() {
		return gradeList;
	}
	public void setGradeList(List<Grade> gradeList) {
		this.gradeList = gradeList;
	}
	public List<String> getExamNameList() {
		return examNameList;
	}
	public void setExamNameList(List<String> examNameList) {
		this.examNameList = examNameList;
	}
	public String getClassSearch() {
		return classSearch;
	}
	public void setClassSearch(String classSearch) {
		this.classSearch = classSearch;
	}
	public String getExamNameSearch() {
		return examNameSearch;
	}
	public void setExamNameSearch(String examNameSearch) {
		this.examNameSearch = examNameSearch;
	}
	public List<Student> getExamAbsentStudentList() {
		return examAbsentStudentList;
	}
	public void setExamAbsentStudentList(List<Student> examAbsentStudentList) {
		this.examAbsentStudentList = examAbsentStudentList;
	}
	
	@Override
	public String execute() throws Exception {
		ActionContext ctx =ActionContext.getContext();
		if(ctx.getSession().containsKey("USER_INFO")){
			gradeList = gradeDao.findAll();
			examNameList = examDao.findAllDistinctExamName();
			return SUCCESS;
		}else{
			this.addActionError("您还没有登录，请登录后再点击进入");
			return ERROR;
		}
	}
	
	public String executeForSearch() throws Exception {
		gradeList = gradeDao.findAll();
		examNameList = examDao.findAllDistinctExamName();
		
		classSearch = classSearch!=null?classSearch:"";
		examNameSearch = examNameSearch!=null?examNameSearch:"";
		
		logger.info("统计缺考考生");
		//只有一个班级和一个考试名称选中时，返回缺考考生
		if(!classSearch.equals("") && !classSearch.contains(",") && !examNameSearch.equals("")){  
			examAbsentStudentList = studentExamScoreDao.getAbsentStudentsForExamName(classSearch,examNameList.get(Integer.parseInt(examNameSearch)-1));
		}
		return SUCCESS;
	}

}
