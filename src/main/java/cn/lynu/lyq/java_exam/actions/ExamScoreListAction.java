package cn.lynu.lyq.java_exam.actions;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.lynu.lyq.java_exam.common.ExamPhase;
import cn.lynu.lyq.java_exam.dao.GradeDao;
import cn.lynu.lyq.java_exam.dao.StudentExamScoreDao;
import cn.lynu.lyq.java_exam.entity.Grade;
import cn.lynu.lyq.java_exam.entity.StudentExamScore;

@Component("examScoreList")
@Scope("prototype")
public class ExamScoreListAction extends ActionSupport {

	private static final long serialVersionUID = 4675761085855839420L;
	private final static Logger logger = LoggerFactory.getLogger(ExamScoreListAction.class);
	private String classSearch;
	private String examNameSearch; 
	
	private List<Grade> gradeList;
	private List<StudentExamScore> examScoreList;
	
	@Resource
	private GradeDao gradeDao;
	@Resource
	private StudentExamScoreDao studentExamScoreDao;

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
	public List<Grade> getGradeList() {
		return gradeList;
	}
	public void setGradeList(List<Grade> gradeList) {
		this.gradeList = gradeList;
	}
	public List<StudentExamScore> getExamScoreList() {
		return examScoreList;
	}
	public void setExamScoreList(List<StudentExamScore> examScoreList) {
		this.examScoreList = examScoreList;
	}

	@Override
	public String execute() throws Exception {
		ActionContext ctx =ActionContext.getContext();
		if(ctx.getSession().containsKey("USER_INFO")){
//			Student stu=(Student)ctx.getSession().get("USER_INFO");
			gradeList = gradeDao.findAll();
			examScoreList = studentExamScoreDao.findByExamPhase(ExamPhase.FINAL_SCORED.getChineseName());
			
			return SUCCESS;
		}else{
			this.addActionError("您还没有登录，请登录后再点击进入");
			return ERROR;
		}
	}
	
	public String executeForSearch() throws Exception {
		gradeList = gradeDao.findAll();
		classSearch = classSearch!=null?classSearch:"";
		logger.info("按照搜索条件检索成绩");
		examScoreList = studentExamScoreDao.findByClassIdAndExamNameAndExamPhase(classSearch, examNameSearch, ExamPhase.FINAL_SCORED.getChineseName());
		return SUCCESS;
	}
	
}
