package com.jagsrpg.jagsrolls.results;

import java.io.OutputStreamWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.dispatcher.StrutsResultSupport;

import com.opensymphony.xwork2.ActionInvocation;

public class StringResult extends StrutsResultSupport {

	private static final long serialVersionUID = 4993372281337744174L;

	protected void doExecute(String finalLocation, ActionInvocation invocation)
			throws Exception {
		HttpServletResponse response = (HttpServletResponse) invocation
				.getInvocationContext().get(HTTP_RESPONSE);
		response.setContentType("text/html");
		OutputStreamWriter writer = new OutputStreamWriter(response
				.getOutputStream());
		writer.write(finalLocation);
		writer.close();
	}

}
