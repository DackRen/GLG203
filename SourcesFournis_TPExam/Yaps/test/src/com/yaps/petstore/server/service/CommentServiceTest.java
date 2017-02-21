package com.yaps.petstore.server.service;

import com.yaps.petstore.AbstractTestCase;
import com.yaps.petstore.common.dto.*;
import com.yaps.petstore.common.exception.*;
import com.yaps.petstore.server.service.customer.CustomerService;
import com.yaps.petstore.server.service.comment.CommentService;
import junit.framework.TestSuite;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Date;

/**
 * This class tests the CommentService class
 */
public final class CommentServiceTest extends AbstractTestCase {

    public CommentServiceTest(final String s) {
        super(s);
    }

    public static TestSuite suite() {
        return new TestSuite(CommentServiceTest.class);
    }

    //==================================
    //=            Test cases          =
    //==================================
    /**
     * This method ensures that creating an object works. It first finds the object,
     * makes sure it doesn't exist, creates it and checks it then exists.
     */
    public void testServiceCreateComment() throws Exception {
        final String id = getUniqueId();
        CommentDTO commentDTO = null;

        // Creates an object
        final String commentId = createComment(id);

        // Ensures that the object exists
        try {
            commentDTO = findComment(commentId);
        } catch (ObjectNotFoundException e) {
            fail("Object has been created it should be found");
        }

        // Checks that it's the right object
        checkComment(commentDTO, id);

        // Cleans the test environment
        deleteComment(commentId);

        try {
            findComment(commentId);
            fail("Object has been deleted it shouldn't be found");
        } catch (ObjectNotFoundException e) {
        }
    }

    /**
     * This test ensures that the method selectAllWhere works.
     */
    public void testDomainFindAllCommentsForASpecifiedCustomer() throws Exception {
        // Create Customer
        String customerId = getCustomerService().getUniqueId();
        final CustomerDTO customerDTO = new CustomerDTO(customerId, "firstname" + customerId, "lastname" + customerId);
        getCustomerService().createCustomer(customerDTO);

        // Create 3 Comments from this customer
        final String id1 = getUniqueId();
        String id = id1;
        CommentDTO commentDTO = new CommentDTO(id, "This is the title " + id + "!", "text" + id, new Date(), customerId);
        getCommentService().createComment(commentDTO);
        final String id2 = getUniqueId();
        id = id2;
        commentDTO = new CommentDTO(id, "This is the title " + id + "!", "text" + id, new Date(), customerId);
        getCommentService().createComment(commentDTO);
        final String id3 = getUniqueId();
        id = id3;
        commentDTO = new CommentDTO(id, "This is the title " + id + "!", "text" + id, new Date(), customerId);
        getCommentService().createComment(commentDTO);

        Collection<?> collection = getCommentService().findCommentsFromCustomer(customerId);
        assertEquals(3, collection.size());
        		
        // Cleans the test environment
    	getCommentService().deleteComment(id1);
    	getCommentService().deleteComment(id2);
    	getCommentService().deleteComment(id3);
    	getCustomerService().deleteCustomer(customerId);    	
    }

    	
    //==================================
    //=          Private Methods       =
    //==================================
    private CommentService getCommentService() throws RemoteException {
        return new CommentService();
    }

    private CustomerService getCustomerService() throws RemoteException{
        return new CustomerService();
    }

    private CommentDTO findComment(final String id) throws FinderException, CheckException, RemoteException {
        final CommentDTO commentDTO = getCommentService().findComment(id);
        return commentDTO;
    }

    // Creates a Customer and a comment linked to the customer
    private String createComment(final String id) throws CreateException, CheckException, RemoteException {
        // Create Customer
        String customerId = getCustomerService().getUniqueId();
        final CustomerDTO customerDTO = new CustomerDTO("custo" + customerId, "firstname" + id, "lastname" + id);
        getCustomerService().createCustomer(customerDTO);

        // Create Comment
        final CommentDTO commentDTO = new CommentDTO(id, "This is the title " + id + "!", "text" + id, new Date(), customerDTO.getId());

        final CommentDTO result = getCommentService().createComment(commentDTO);
        return result.getId();
    }

    private void deleteComment(final String commentId) throws RemoveException, CheckException, RemoteException, FinderException {
    	final CommentDTO commentDTO = getCommentService().findComment(commentId);
    	getCommentService().deleteComment(commentId);
    	getCustomerService().deleteCustomer(commentDTO.getCustomerId());
    }

    private void checkComment(final CommentDTO commentDTO, final String id) {
        assertEquals("title", "This is the title " + id + "!", commentDTO.getTitle());
        assertEquals("text", "text" + id, commentDTO.getText());
    }

    private String getUniqueId() throws RemoteException {
		return getCommentService().getUniqueId();
	}
}
