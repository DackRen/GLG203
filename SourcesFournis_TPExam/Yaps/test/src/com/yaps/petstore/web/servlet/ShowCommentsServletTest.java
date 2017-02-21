package com.yaps.petstore.web.servlet;

import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebForm;
import com.meterware.httpunit.WebLink;
import com.meterware.httpunit.WebResponse;
import com.yaps.petstore.AbstractTestCase;
import junit.framework.TestSuite;

/**
 * This class tests the SearchComment servlet
 */
public class ShowCommentsServletTest extends AbstractTestCase {

    private WebConversation webConversation = new WebConversation();
    private static final String URL_PETSTORE = "http://localhost:8080/petstore";

    public ShowCommentsServletTest(final String s) {
        super(s);
    }

    public static TestSuite suite() {
        return new TestSuite(ShowCommentsServletTest.class);
    }

    //==================================
    //=            Test cases          =
    //==================================
    /**
     * This method ensures that all comments are displayed when the customer has not sign in
     */
    public void testServletShowAllComments() throws Exception {
        WebResponse homePage = webConversation.getResponse(URL_PETSTORE + "/index.jsp");
        // ... click on the search comment link
        WebLink searchCommentLink = getTheFirstWebLinkThatMatches(homePage, "searchcomments");
        WebResponse page = searchCommentLink.click();
        // ... and check the contents
        String contents = page.getText();
        String expected = "Comments from all customers";
        int pos = contents.indexOf(expected);
        assertTrue("Text not found: " + expected, pos != -1);

        expected = "The cat I received was dead!";
        pos = contents.indexOf(expected);
        assertTrue("Text not found: " + expected, pos != -1);

        expected = "Give me another one!";
        pos = contents.indexOf(expected);
        assertTrue("Text not found: " + expected, pos != -1);

        expected = "My Chihuahua is amazing!";
        pos = contents.indexOf(expected);
        assertTrue("Text not found: " + expected, pos != -1);
    }

    /**
     * This test ensures that only customer (Bill Gates) comments are displayed when he has sign in
     */
    public void testServletShowGatesComments() throws Exception {
    	// get the searchcomments page for customerId bill000
        WebResponse page = webConversation.getResponse(URL_PETSTORE + "/searchcomments?customerId=bill000");
        // ... and check the contents
        String contents = page.getText();
        String expected = "Comments from bill000";
        int pos = contents.indexOf(expected);
        assertTrue("Text not found: " + expected, pos != -1);

        expected = "The cat I received was dead!";
        pos = contents.indexOf(expected);
        assertTrue("Text not found: " + expected, pos != -1);

        expected = "Give me another one!";
        pos = contents.indexOf(expected);
        assertTrue("Text not found: " + expected, pos != -1);

        String notExpected = "My Chihuahua is amazing!";
        pos = contents.indexOf(notExpected);
        assertTrue("Comments from Steve Jobs should not ne displayed here : " + notExpected, pos == -1);
    }

    //==================================
    //=         Private Methods        =
    //==================================
    private WebResponse signInAsGatesCustomer() throws Exception {
        // Gets the Sign On Web Page
        WebResponse signOnPage = webConversation.getResponse(URL_PETSTORE + "/signon.jsp");
        WebForm existingCustomerForm = signOnPage.getFormWithName("existingCustomerForm");
        // Sets parameter to the web page
        existingCustomerForm.setParameter("customerId", "bill000");
        existingCustomerForm.setParameter("password", "bill000");
        // Submits the form
        return existingCustomerForm.submit();
    }

    private WebLink getTheFirstWebLinkThatMatches(WebResponse jspPage, String textToMatch) throws Exception {
        // Gets all the links that the page has
        WebLink[] jspLinks = jspPage.getLinks();

        // Looks for links that match the text we look for
        for (int i = 0; i < jspLinks.length; i++) {
            String url = jspLinks[i].getURLString();
            if (url.indexOf(textToMatch) != -1)
                return jspLinks[i];
        }

        return null;
    }
}
