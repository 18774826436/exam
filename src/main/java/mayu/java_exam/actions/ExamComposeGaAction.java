package mayu.java_exam.actions;

import com.opensymphony.xwork2.ActionSupport;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component("examComposeGa")
@Scope("prototype")

public class ExamComposeGaAction extends ActionSupport {
    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }
}
