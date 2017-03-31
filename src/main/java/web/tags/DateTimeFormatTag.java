package web.tags;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 * @author Sasha
 *
 * Simple tag class for formating date-time field into natural format for user interface
 *
 */
public class DateTimeFormatTag extends SimpleTagSupport {

	StringWriter sw = new StringWriter();
	
	public void doTag() throws JspException, IOException {
		getJspBody().invoke(sw);
		
		getJspContext().getOut().write(parseDateTime(sw.toString()));
	}
	
	private String parseDateTime(String dateTime){
		
		return dateTime.split("T")[0]+" "+dateTime.split("T")[1];	
	}
}
