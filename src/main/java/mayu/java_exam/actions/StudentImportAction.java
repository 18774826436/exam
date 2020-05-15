package mayu.java_exam.actions;
//导入学生信息
import java.io.File;

import javax.annotation.Resource;

import mayu.java_exam.dao.StudentDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

@Component("studentImport")
@Scope("prototype")
public class StudentImportAction extends ActionSupport {
	private static final long serialVersionUID = 3132888016394172355L;
	private final static Logger logger = LoggerFactory.getLogger(StudentImportAction.class);
	
	private File studentImportFile;//文件类
	private String studentImportFileFileName;//文件名
	private String studentImportFileContentType;//文件类型
	private String studentFilePath;//文件路径
	
	@Resource
	private StudentDao studentDao;
	
	public File getStudentImportFile() {
		return studentImportFile;
	}
	public void setStudentImportFile(File studentImportFile) {
		this.studentImportFile = studentImportFile;
	}
	public String getStudentImportFileFileName() {
		return studentImportFileFileName;
	}
	public void setStudentImportFileFileName(String studentImportFileFileName) {
		this.studentImportFileFileName = studentImportFileFileName;
	}
	//导入文件
	public String getStudentImportFileContentType() {
		return studentImportFileContentType;
	}
	public void setStudentImportFileContentType(String studentImportFileContentType) {
		this.studentImportFileContentType = studentImportFileContentType;
	}
	//判断文件类型
	public String getStudentFilePath() {
		return studentFilePath;
	}
	public void setStudentFilePath(String studentFilePath) {
		this.studentFilePath = studentFilePath;
	}
	//文件路径
	@Override
	public String execute() throws Exception {  
		logger.info("学生信息导入");
		int cnt = studentDao.importFromTxt(studentImportFile);
		logger.debug("studentImportFile="+studentImportFile);
		logger.debug("studentFilePath="+studentFilePath);
		this.addActionMessage(cnt+"条学生信息已经导入");
		return SUCCESS;
	}
	
}
