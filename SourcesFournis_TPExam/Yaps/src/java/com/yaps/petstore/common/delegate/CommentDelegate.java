package com.yaps.petstore.common.delegate;

import java.rmi.RemoteException;
import java.util.Collection;

import com.yaps.petstore.common.dto.CommentDTO;
import com.yaps.petstore.common.exception.CheckException;
import com.yaps.petstore.common.exception.CreateException;
import com.yaps.petstore.common.exception.FinderException;
import com.yaps.petstore.common.exception.RemoveException;
import com.yaps.petstore.common.exception.UpdateException;

public abstract class CommentDelegate implements CommentServiceRemote{

	@Override
	public CommentDTO createComment(CommentDTO commentDTO) throws CreateException, CheckException, RemoteException {
		return getCommentService().createComment(commentDTO);
	}

	@Override
	public CommentDTO findComment(String commentId) throws FinderException, CheckException, RemoteException {
		return getCommentService().findComment(commentId);
	}

	@Override
	public void deleteComment(String commentId) throws RemoveException, CheckException, RemoteException {
		getCommentService().deleteComment(commentId);
	}

	@Override
	public void updateComment(CommentDTO commentDTO) throws UpdateException, CheckException, RemoteException {
		getCommentService().updateComment(commentDTO);
	}

	@Override
	public Collection findComments() throws FinderException, RemoteException {
		return getCommentService().findComments();
	}

	@Override
	public Collection findCommentsFromCustomer(String customerId) throws FinderException, RemoteException {
		return getCommentService().findCommentsFromCustomer(customerId);
	}
	
	// ======================================
    // =            Protected methods         =
    // ======================================
    protected abstract CommentServiceRemote getCommentService() throws RemoteException;

}
