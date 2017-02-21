package com.yaps.petstore.common.delegate;

import java.rmi.RemoteException;

import com.yaps.petstore.server.service.comment.CommentService;

/**
 * This class follows the Delegate design pattern. It's a one to one method
 * with the CommentService class. Each method delegates the call to the
 * CommentService class
 */
public final class CommentLocalDelegate extends CommentDelegate {

    // ======================================
    // =             Attributes             =
    // ======================================
    private static CommentServiceRemote commentServiceRemote;

    // ======================================
    // =            Private methods         =
    // ======================================
    protected CommentServiceRemote getCommentService() throws RemoteException {
    	if ( commentServiceRemote == null )
    		commentServiceRemote = new CommentService();
    	return commentServiceRemote;
    }
}
