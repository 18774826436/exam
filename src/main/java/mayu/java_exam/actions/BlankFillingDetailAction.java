package mayu.java_exam.actions;
//查询填空题
import javax.annotation.Resource;

import mayu.java_exam.dao.BankQuestionDao;
import mayu.java_exam.entity.BankBlankFillingQuestion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Component("blankFillingDetail")
@Scope("prototype")
public class BlankFillingDetailAction extends ActionSupport {
	
	private static final long serialVersionUID = -3038382114712565555L;
	private final static Logger logger = LoggerFactory.getLogger(BlankFillingDetailAction.class);
	
	private BankBlankFillingQuestion question;
	@Resource 
	private BankQuestionDao bankQuestionDao;
	
	public BankBlankFillingQuestion getQuestion() {
		return question;
	}
	public void setQuestion(BankBlankFillingQuestion question) {
		this.question = question;
	}

	@Override
	public String execute() throws Exception {
		ActionContext ctx = ActionContext.getContext();
		String qid = ctx.getParameters().get("qid").getValue();
		logger.info("根据id查找填空题详细信息");
		question = bankQuestionDao.findBlankFillingById(Integer.parseInt(qid.trim()));
		
		return SUCCESS;
	}
	
}
