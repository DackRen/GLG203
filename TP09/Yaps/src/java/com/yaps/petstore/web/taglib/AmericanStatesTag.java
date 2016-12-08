package com.yaps.petstore.web.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.yaps.petstore.common.constant.AmericanStates;


public class AmericanStatesTag extends SimpleTagSupport {

    // ======================================
    // =           Business Methods         =
    // ======================================
    public void doTag() throws JspException, IOException {

        StringBuffer buf = new StringBuffer();

        // <select>
        buf.append("<select name='state'>");

        // Gets all the credit card types
        String americanStates[] = AmericanStates.getAll();
        for (int i = 0; i < americanStates.length; i++) {

            // <option>
            buf.append("<option value='").append(americanStates[i]).append("'>");

            // value
            buf.append(americanStates[i]);

            // </option>
            buf.append("</option>");
        }

        // </select>
        buf.append("</select>");

        // Display
        getJspContext().getOut().println(buf);

    }

}
