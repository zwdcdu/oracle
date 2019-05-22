package com.panda.error;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.io.PrintWriter;
import java.io.StringWriter;

/*
统一的异常处理类
该类的调用由struts.xml中的
<global-results>
<global-exception-mappings>
<action name="errorProcessor" class="com.panda.error.ErrorProcessor">
共同设置
*/
public class ErrorProcessor extends ActionSupport {
	private Exception exception ;
	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}
	public String execute() {
		//放到值栈顶，因为信息被放到栈顶了 所以在jsp文件可以取到：<s:property />
		//ActionContext.getContext().getValueStack().push(exception.getMessage());

		//也可以通过addActionError（），使得页面可以取到：<s:actionerror />
		/*
		StringWriter   sw   =   new StringWriter();
		exception.printStackTrace(new PrintWriter(sw));
		//addActionError(exception.getMessage()); //exception.getMessage()不能返回sql语句的错误消息
		//addActionError(sw.toString()); //返回最详细的StackTrace错误消息
		*/
		exception.printStackTrace();
		if (exception.getCause()!=null)
			addActionError(exception.getCause().getMessage());//主要用于接收SQL语句的异常
		else
			addActionError(exception.getMessage());//返回普通错误消息
		return ERROR ;
	}
}
