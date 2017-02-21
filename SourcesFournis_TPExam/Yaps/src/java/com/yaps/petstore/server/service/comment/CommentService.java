package com.yaps.petstore.server.service.comment;

import com.yaps.petstore.common.delegate.CommentServiceRemote;
import com.yaps.petstore.common.dto.CommentDTO;
import com.yaps.petstore.common.exception.*;
import com.yaps.petstore.common.logging.Trace;
import com.yaps.petstore.server.domain.comment.Comment;
import com.yaps.petstore.server.domain.comment.CommentDAO;
import com.yaps.petstore.server.domain.customer.Customer;
import com.yaps.petstore.server.domain.customer.CustomerDAO;
import com.yaps.petstore.server.service.AbstractRemoteService;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * This class is a facade for all comment services.
 */
public final class CommentService extends AbstractRemoteService implements CommentServiceRemote {

    // ======================================
    // =             Attributes             =
    // ======================================
    private static final CommentDAO _dao = new CommentDAO();
    private static final CustomerDAO _customerDAO = new CustomerDAO();

    // ======================================
    // =            Constructors            =
    // ======================================
    public CommentService() throws RemoteException {
    }

    // ======================================
    // =           Business methods         =
    // ======================================
    public CommentDTO createComment(final CommentDTO commentDTO) throws CreateException, CheckException {
        final String mname = "createComment";
        Trace.entering(getCname(), mname, commentDTO);

        if (commentDTO == null)
            throw new CreateException("Comment object is null");

        // Finds the customer
        Customer customer = null;
        try {
            customer = (Customer)_customerDAO.findByPrimaryKey(commentDTO.getCustomerId());
        } catch (FinderException e) {
            throw new CreateException("Customer must exist to create an order");
        }
        // Transforms DTO into domain object
        final Comment comment = new Comment(commentDTO.getId(), commentDTO.getTitle(), commentDTO.getText(), customer, commentDTO.getDate());

        comment.checkData();

        // Creates the object
        _dao.insert(comment);

        // Transforms domain object into DTO
        final CommentDTO result = transformComment2DTO(comment);

        Trace.exiting(getCname(), mname, result);
        return result;
    }

    public CommentDTO findComment(final String commentId) throws FinderException, CheckException {
        final String mname = "findComment";
        Trace.entering(getCname(), mname, commentId);

    	checkId(commentId);
        // Finds the object
        final Comment comment = (Comment)_dao.findByPrimaryKey(commentId);

        // Transforms domain object into DTO
        final CommentDTO commentDTO = transformComment2DTO(comment);

        Trace.exiting(getCname(), mname, commentDTO);
        return commentDTO;
    }

    public void deleteComment(final String commentId) throws RemoveException, CheckException {
        final String mname = "deleteComment";
        Trace.entering(getCname(), mname, commentId);

    	checkId(commentId);

        // Checks if the object exists
        try {
        	_dao.findByPrimaryKey(commentId);
        } catch (FinderException e) {
            throw new RemoveException("Comment must exist to be deleted");
        }

        // Deletes the object
        try {
        	_dao.remove(commentId);
        } catch (ObjectNotFoundException e) {
            throw new RemoveException("Comment must exist to be deleted");
        }
    }

    public void updateComment(final CommentDTO commentDTO) throws UpdateException, CheckException {
        final String mname = "updateComment";
        Trace.entering(getCname(), mname, commentDTO);

        if (commentDTO == null)
            throw new UpdateException("Comment object is null");

    	checkId(commentDTO.getId());

    	final Comment comment;

        // Checks if the object exists
        try {
        	comment = (Comment)_dao.findByPrimaryKey(commentDTO.getId());
        } catch (FinderException e) {
            throw new UpdateException("Comment must exist to be updated");
        }

        // Transforms DTO into domain object
        comment.setTitle(commentDTO.getTitle());
        comment.setText(commentDTO.getText());

        comment.checkData();
        
        // Updates the object
        try {
        	_dao.update(comment);
        } catch (ObjectNotFoundException e) {
            throw new UpdateException("Comment must exist to be updated");
        }
    }

    public Collection findComments() throws FinderException {
        final String mname = "findComments";
        Trace.entering(getCname(), mname);

        // Finds all the objects
        final Collection comments = _dao.selectAllWhere("", " DATE DESC ");

        // Transforms domain objects into DTOs
        final Collection commentsDTO = transformComments2DTOs(comments);

        Trace.exiting(getCname(), mname, new Integer(commentsDTO.size()));
        return commentsDTO;
    }

    public Collection findCommentsFromCustomer(String customerId) throws FinderException {
        final String mname = "findCommentsFromCustomer";
        Trace.entering(getCname(), mname);

        // Finds all the objects
        String whereClause = "CUSTOMER_FK = '" + customerId + "'";
        final Collection comments = _dao.selectAllWhere(whereClause, " DATE DESC ");

        // Transforms domain objects into DTOs
        final Collection commentsDTO = transformComments2DTOs(comments);

        Trace.exiting(getCname(), mname, new Integer(commentsDTO.size()));
        return commentsDTO;
    }

    // ======================================
    // =          Private Methods           =
    // ======================================
    private CommentDTO transformComment2DTO(final Comment comment) {
        final CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setTitle(comment.getTitle());
        commentDTO.setText(comment.getText());
        commentDTO.setDate(comment.getDate());
        commentDTO.setCustomerId(comment.getCustomer().getId());
        return commentDTO;
    }

    private Collection transformComments2DTOs(final Collection comments) {
        final Collection commentsDTO = new ArrayList();
        for (Iterator iterator = comments.iterator(); iterator.hasNext();) {
            final Comment comment = (Comment) iterator.next();
            commentsDTO.add(transformComment2DTO(comment));
        }
        return commentsDTO;
    }

    /**
     * This method returns a unique identifer generated by the system. 
     *
     * @return a unique identifer
     */
    public final String getUniqueId() {
        return _dao.getUniqueId("Comment");
    }

}
