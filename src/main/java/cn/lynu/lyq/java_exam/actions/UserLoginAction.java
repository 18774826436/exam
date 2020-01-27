package cn.lynu.lyq.java_exam.actions;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.lynu.lyq.java_exam.dao.StudentDao;
import cn.lynu.lyq.java_exam.entity.Student;
@Component("userLogin")
@Scope("prototype")
public class UserLoginAction extends ActionSupport {
	private static final long serialVersionUID = 5090548832375142158L;
	private final static Logger logger = LoggerFactory.getLogger(UserLoginAction.class);
	
	private String registerNo;
	private String password;
	private String name;
	private String id;
	@Resource
	private StudentDao studentDao;

	public String getRegisterNo() {
		return registerNo;
	}
	public void setRegisterNo(String registerNo) {
		this.registerNo = registerNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
	@Override
	public String execute() throws Exception {
		logger.info("用户登陆");
//		logger.info(name);
//		logger.info(registerNo);
//		logger.info(password);
		Student stu = studentDao.findByRegNoAndPassword(registerNo, password);//用户名密码登录，查表
//		logger.info(stu.toString());
//		Student stu =studentDao.findById(33);
//	    logger.info(id);
		if(stu!=null){
			logger.info(stu.toString());
			name=stu.getName();
			ActionContext ctx=ActionContext.getContext();
			ctx.getSession().put("USER_INFO", stu);
		}else{
			this.addActionError("学号或密码错误，请重新输入！");
//			return SUCCESS;
		}
		return SUCCESS;
	}
	
	public String logout() throws Exception{
		logger.info("用户退出登陆");
		ActionContext ctx=ActionContext.getContext();
		ctx.getSession().remove("USER_INFO");
		this.addActionMessage("退出登陆成功！");
		return SUCCESS;
	}
}
