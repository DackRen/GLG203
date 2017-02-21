package com.yaps.petstore.common.delegate;

import java.rmi.Naming;
import java.rmi.RemoteException;

import com.yaps.petstore.common.rmi.RMIConstant;

/**
 * This class follows the Delegate design pattern. It's a one to one method
 * with the CommentService class. Each method delegates the call to the
 * CommentService class
 */
public final class CommentRemoteDelegate extends CommentDelegate implements RMIConstant {

    // ======================================
    // =             Attributes             =
    // ======================================
    private static CommentServiceRemote commentServiceRemote;

    // ======================================
    // =            Private methods         =
    // ======================================
    protected CommentServiceRemote getCommentService() throws RemoteException {
        try {
            commentServiceRemote = (CommentServiceRemote) Naming.lookup(CUSTOMER_SERVICE_URL);
        } catch (Exception e) {
            throw new RemoteException("Lookup exception", e);
        }
        return commentServiceRemote;
    }
}
