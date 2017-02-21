package com.yaps.petstore.web.servlet;

import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebForm;
import com.meterware.httpunit.WebLink;
import com.meterware.httpunit.WebResponse;
import com.yaps.petstore.AbstractTestCase;
import junit.framework.TestSuite;

/**
 * This class tests the CreateComment servlet
 */
public class CreateCommentServletTest extends AbstractTestCase {

    private WebConversation webConversation = new WebConversation();
    private static final String URL_PETSTORE = "http://localhost:8080/petstore";

    public CreateCommentServletTest(final String s) {
        super(s);
    }

    public static TestSuite suite() {
        return new TestSuite(CreateCommentServletTest.class);
    }

    //==================================
    //=            Test cases          =
    //==================================
    /**
     * This method ensures that creating an object works.
     */
    public void testServletCreateValidComment() throws Exception {
    	/*
        // Sign in
        WebResponse customerPage = signInAsJobsCustomer();
        // ... click on the create comment link
        WebLink createCommentLink = getTheFirstWebLinkThatMatches(customerPage, "createcomment");
        WebResponse newpage = createCommentLink.click();
    	 */
    	// get the searchcomments page for customerId bill000
        WebResponse newpage = webConversation.getResponse(URL_PETSTORE + "/createcomment.jsp?customerId=job5");
        // ... and check the contents
        // ... and get the form
        WebForm createCommentForm = newpage.getFormWithName("commentForm");

        // Sets parameter to the web page
        createCommentForm.setParameter("title", "Title of a test new comment!");
        createCommentForm.setParameter("text", "This is a new comment text");

        // Submits the form
        createCommentForm.submit();
        // If after clicking the submit button an error occurs,
        // the page title is 'YAPS Error'
        WebResponse currentPage = webConversation.getCurrentPage();
        if ("YAPS Error".equals(currentPage.getTitle()))
            throw new Exception("An error has occured");
    }

    /**
     * This test tries to create an object with invalid values.
     */
    public void testServletCreateCommentWithInvalidTitle() throws Exception {
        // Sign in
        WebResponse customerPage = signInAsJobsCustomer();
        // ... click on the create comment link
        WebLink createCommentLink = getTheFirstWebLinkThatMatches(customerPage, "createcomment");
        WebResponse newpage = createCommentLink.click();
        // ... and get the form
        WebForm createCommentForm = newpage.getFormWithName("commentForm");

        // clear the title input 
        createCommentForm.setParameter("title", "");
        createCommentForm.setParameter("text", "OK!");
        // Submits the form
        createCommentForm.submit();
        // If after clicking the submit button an error occurs,
        // the page title is 'YAPS Error'
        WebResponse currentPage = webConversation.getCurrentPage();
        assertEquals("YAPS Error", currentPage.getTitle());
        String responseText = currentPage.getText();
        int pos = responseText.indexOf("title and text have to be filled");
        assertTrue(pos > 10);
    }

    /**
     * This method ensures that creating an object works only .
     */
    public void testServletCreateValidCommentAfterSignOff() throws Exception {
        // Sign off and try to create a comment
        webConversation.getResponse(URL_PETSTORE + "/signoff");
        WebResponse newpage = webConversation.getResponse(URL_PETSTORE + "/createcomment.jsp");
        // ... and get the form
        WebForm createCommentForm = newpage.getFormWithName("commentForm");

        // Sets parameter to the web page
        createCommentForm.setParameter("title", "Title of a test new comment!");
        createCommentForm.setParameter("text", "This is a new comment text");

        // Submits the form
        createCommentForm.submit();
        // If after clicking the submit button an error occurs,
        // the page title is 'YAPS Error'
        WebResponse currentPage = webConversation.getCurrentPage();
        assertEquals("YAPS Error", currentPage.getTitle());
        String responseText = currentPage.getText();
        int pos = responseText.indexOf("You should sign on to post a comment");
        assertTrue(pos > 10);
    }

    //==================================
    //=         Private Methods        =
    //==================================
    private WebResponse signInAsJobsCustomer() throws Exception {
        // Gets the Sign On Web Page
        WebResponse signOnPage = webConversation.getResponse(URL_PETSTORE + "/signon.jsp");
        WebForm existingCustomerForm = signOnPage.getFormWithName("existingCustomerForm");
        // Sets parameter to the web page
        existingCustomerForm.setParameter("customerId", "job5");
        existingCustomerForm.setParameter("password", "job5");
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
