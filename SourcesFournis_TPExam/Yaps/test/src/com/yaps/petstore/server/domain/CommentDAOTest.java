package com.yaps.petstore.server.domain;

import com.yaps.petstore.AbstractTestCase;
import com.yaps.petstore.server.domain.customer.Customer;
import com.yaps.petstore.server.domain.customer.CustomerDAO;
import com.yaps.petstore.server.domain.comment.Comment;
import com.yaps.petstore.server.domain.comment.CommentDAO;
import com.yaps.petstore.common.exception.*;
import junit.framework.TestSuite;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Date;

/**
 * This class tests the CommentDAO class
 */
public final class CommentDAOTest extends AbstractTestCase {
    private final CommentDAO _commentDAO = new CommentDAO();
    private final CustomerDAO _customerDAO = new CustomerDAO();

    public CommentDAOTest(final String s) {
        super(s);
    }

    public static TestSuite suite() {
        return new TestSuite(CommentDAOTest.class);
    }

    //==================================
    //=            Test cases          =
    //==================================
    /**
     * This test tries to find an object with a invalid identifier.
     */
    public void testDomainFindCommentWithInvalidValues() throws Exception {

        // Finds an object with a unknown identifier
        final String id = getUniqueId();
        try {
            findComment(id);
            fail("Object with unknonw id should not be found");
        } catch (ObjectNotFoundException e) {
        }

        // Finds an object with an empty identifier
        try {
            _commentDAO.findByPrimaryKey(new String());
            fail("Object with empty id should not be found");
        } catch (ObjectNotFoundException e) {
        }

        // Finds an object with a null identifier
        try {
        	_commentDAO.findByPrimaryKey(null);
            fail("Object with null id should not be found");
        } catch (ObjectNotFoundException e) {
        }
    }

    /**
     * This test ensures that the method findAll works. It does a first findAll, creates
     * a new object and does a second findAll.
     */
    public void testDomainFindAllComments() throws Exception {
        final String id = getUniqueId();

        // First findAll
        final int firstSize = findAllComments();

        // Create an object
        final String commentId = createComment(id);

        // Ensures that the object exists
        try {
            findComment(commentId);
        } catch (ObjectNotFoundException e) {
            fail("Object has been created it should be found");
        }

        // second findAll
        final int secondSize = findAllComments();

        // Checks that the collection size has increase of one
        if (firstSize + 1 != secondSize) fail("The collection size should have increased by 1");

        // Cleans the test environment
        removeComment(id, commentId);

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
        // Create a new Customer
    	String customerId = _customerDAO.getUniqueId();
        final Customer customer = new Customer(customerId, "firstname" + customerId, "lastname" + customerId);
        _customerDAO.insert(customer);

        // Create 3 Comments from this customer
        final String id1 = getUniqueId();
        String id = id1;
        Comment comment = new Comment(id, "title" + id, "text" + id, customer, new Date());
        _commentDAO.insert(comment);
        final String id2 = getUniqueId();
        id = id2;
        comment = new Comment(id, "title" + id, "text" + id, customer, new Date());
        _commentDAO.insert(comment);
        final String id3 = getUniqueId();
        id = id3;
        comment = new Comment(id, "title" + id, "text" + id, customer, new Date());
        _commentDAO.insert(comment);

        // Ensures that the objects exist
        try {
            findComment(id1);
            findComment(id2);
            findComment(id3);
        } catch (ObjectNotFoundException e) {
            fail("Object has been created it should be found");
        }

        // Ensures that the selectAllWhere method works
        String whereClause = "CUSTOMER_FK = '" + customerId + "'";
        Collection collection = _commentDAO.selectAllWhere(whereClause, "");
        assertEquals(3, collection.size());
        
        // Cleans the test environment
    	_commentDAO.remove(id1);
    	_commentDAO.remove(id2);
    	_commentDAO.remove(id3);
        _customerDAO.remove(customer.getId());

        try {
            findComment(id1);
            fail("Object has been deleted it shouldn't be found");
        } catch (ObjectNotFoundException e) {
        }
    }

    /**
     * This method ensures that creating an object works. It first finds the object,
     * makes sure it doesn't exist, creates it and checks it then exists.
     */
    public void testDomainCreateComment() throws Exception {
        final String id = getUniqueId();
        Comment comment = null;

        // Creates an object
        final String commentId = createComment(id);

        // Ensures that the object exists
        try {
            comment = findComment(commentId);
        } catch (ObjectNotFoundException e) {
            fail("Object has been created it should be found");
        }

        // Cleans the test environment
        removeComment(id, commentId);

        try {
            findComment(commentId);
            fail("Object has been deleted it shouldn't be found");
        } catch (ObjectNotFoundException e) {
        }
    }

    /**
     * This test tries to create an object with a invalid values.
     */
    public void testDomainCreateCommentWithInvalidValues() throws Exception {

        // Creates an object with an empty values
        try {
            final Comment comment = new Comment(new String(), new String());
            comment.checkData();
            fail("Object with empty values should not be created");
        } catch (CheckException e) {
        }

        // Creates an object with an null values
        try {
            final Comment comment = new Comment(null, null);
            comment.checkData();
            fail("Object with null values should not be created");
        } catch (CheckException e) {
        }
    }

    /**
     * This test tries to update an object with a invalid values.
     */
    public void testDomainUpdateCommentWithInvalidValues() throws Exception {

        // Creates an object
        final String id = getUniqueId();
        final String commentId = createComment(id);

        // Ensures that the object exists
        Comment comment = null;
        try {
            comment = findComment(commentId);
        } catch (ObjectNotFoundException e) {
            fail("Object has been created it should be found");
        }

        // Updates the object with empty values
        try {
            comment.setTitle(new String());
            comment.setText(new String());
            comment.checkData();
            fail("Updating an object with empty values should break");
        } catch (CheckException e) {
        }

        // Updates the object with null values
        try {
            comment.setTitle(new String());
            comment.setText(new String());
            comment.checkData();
            fail("Updating an object with null values should break");
        } catch (CheckException e) {
        }

        // Ensures that the object still exists
        try {
            comment = findComment(commentId);
        } catch (ObjectNotFoundException e) {
            fail("Object should be found");
        }

        // Cleans the test environment
        removeComment(id, commentId);

        try {
            findComment(commentId);
            fail();
        } catch (ObjectNotFoundException e) {
        }
    }

    /**
     * This test make sure that updating an object success
     */
    public void testDomainUpdateComment() throws Exception {
        final String id = getUniqueId();

        // Creates an object
        final String commentId = createComment(id);

        // Ensures that the object exists
        Comment comment = null;
        try {
            comment = findComment(commentId);
        } catch (ObjectNotFoundException e) {
            fail("Object has been created it should be found");
        }

        // Updates the object with new values
        updateComment(comment, id + 1);

        // Ensures that the object still exists
        Comment commentUpdated = null;
        try {
            commentUpdated = findComment(commentId);
        } catch (ObjectNotFoundException e) {
            fail("Object should be found");
        }

        // Checks that the object values have been updated
        checkComment(commentUpdated, id + 1);

        // Cleans the test environment
        removeComment(id, commentId);

        try {
            findComment(commentId);
            fail("Object has been deleted it shouldn't be found");
        } catch (ObjectNotFoundException e) {
        }
    }

    //==================================
    //=         Private Methods        =
    //==================================
    private Comment findComment(final String commentId) throws FinderException, CheckException {
        final Comment comment = (Comment) _commentDAO.findByPrimaryKey(commentId);
        return comment;
    }

    private int findAllComments() throws FinderException {
        try {
            return _commentDAO.findAll().size();
        } catch (ObjectNotFoundException e) {
            return 0;
        }
    }

    // Creates a customer first and then a comment linked to this customer and returns the key
    private String createComment(final String id) throws CreateException, CheckException {
        // Create Customer
    	String customerId = _customerDAO.getUniqueId();
        final Customer customer = new Customer("custo" + customerId, "firstname" + customerId, "lastname" + customerId);
        _customerDAO.insert(customer);
        // Create Comment
        final Comment comment = new Comment(id, "title" + id, "text" + id, customer, new Date());
        _commentDAO.insert(comment);
        return comment.getId();
    }

    private void updateComment(final Comment comment, final String id) throws UpdateException, CreateException, ObjectNotFoundException {
        comment.setTitle("title" + id);
        comment.setText("text" + id);
        _commentDAO.update(comment);
    }

    private void removeComment(final String id, final String commentId) throws RemoveException, ObjectNotFoundException {
    	Comment comment = (Comment)_commentDAO.findByPrimaryKey(id);
    	Customer customer = comment.getCustomer();
    	_commentDAO.remove(commentId);
        _customerDAO.remove(customer.getId());
    }

    private void checkComment(final Comment comment, final String id) {
        assertEquals("title", "title" + id, comment.getTitle());
        assertEquals("text", "text" + id, comment.getText());
        assertNotNull("Customer", comment.getCustomer());
    }

    protected String getUniqueId() {
    	String id = _commentDAO.getUniqueId();
    	return id;
    }    

}
