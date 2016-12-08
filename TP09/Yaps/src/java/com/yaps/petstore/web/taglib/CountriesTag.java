package com.yaps.petstore.web.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.yaps.petstore.common.constant.Countries;
import com.yaps.petstore.common.constant.CreditCardTypes;

public class CountriesTag extends SimpleTagSupport {

    // ======================================
    // =           Business Methods         =
    // ======================================
    public void doTag() throws JspException, IOException {

        StringBuffer buf = new StringBuffer();

        // <select>
        buf.append("<select name='country'>");

        // Gets all the credit card types
        String countries[] = Countries.getAll();
        for (int i = 0; i < countries.length; i++) {

            // <option>
            buf.append("<option value='").append(countries[i]).append("'>");

            // value
            buf.append(countries[i]);

            // </option>
            buf.append("</option>");
        }

        // </select>
        buf.append("</select>");

        // Display
        getJspContext().getOut().println(buf);

    }

}
