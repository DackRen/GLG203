package com.yaps.petstore.common.delegate;

import com.yaps.petstore.common.dto.CommentDTO;
import com.yaps.petstore.common.exception.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

/**
 * This interface gives a remote view of the CommentService. Any distant client that wants to call
 * a method on the CommentService has to use this interface. Because it extends the Remote interface,
 * every method must throw RemoteException.
 */
public interface CommentServiceRemote extends Remote {

    // ======================================
    // =           Business methods         =
    // ======================================

    /**
     * Given a CommentDTO object, this method creates a Comment. It first transforms
     * a CommentDTO into a Comment domain object, uses the Comment object to apply the
     * business rules for creation. It then transforms back the Comment object into a
     * CommentDTO.
     *
     * @param commentDTO cannot be null.
     * @return the created CommentDTO
     * @throws DuplicateKeyException is thrown if an object with the same id
     *                               already exists in the system
     * @throws CreateException       is thrown if a DomainException is caught
     *                               or a system failure is occurs
     * @throws CheckException        is thrown if a invalid data is found
     * @throws RemoteException       is thrown if a remote call fails
     */
    CommentDTO createComment(CommentDTO commentDTO) throws CreateException, CheckException, RemoteException;

    /**
     * Given an id this method uses the Comment domain object to load all the data of this
     * object. It then transforms this object into a CommentDTO and returns it.
     *
     * @param commentId identifier
     * @return CommentDTO
     * @throws ObjectNotFoundException is thrown if no object with this given id is found
     * @throws FinderException         is thrown if a DomainException is caught
     *                                 or a system failure is occurs
     * @throws CheckException        is thrown if a invalid data is found
     * @throws RemoteException         is thrown if a remote call fails
     */
    CommentDTO findComment(String commentId) throws FinderException, CheckException, RemoteException;

    /**
     * Given an id, this method finds a Comment domain object and then calls its deletion
     * method.
     *
     * @param commentId identifier
     * @throws RemoveException is thrown if a DomainException is caught
     *                         or a system failure is occurs
     * @throws CheckException        is thrown if a invalid data is found
     * @throws RemoteException is thrown if a remote call fails
     */
    void deleteComment(String commentId) throws RemoveException, CheckException, RemoteException;

    /**
     * Given a CommentDTO object, this method updates a Comment. It first transforms
     * a CommentDTO into a Comment domain object and uses the Comment object to apply the
     * business rules for modification.
     *
     * @param commentDTO cannot be null.
     * @throws UpdateException is thrown if a DomainException is caught
     *                         or a system failure is occurs
     * @throws CheckException        is thrown if a invalid data is found
     * @throws RemoteException is thrown if a remote call fails
     */
    void updateComment(CommentDTO commentDTO) throws UpdateException, CheckException, RemoteException;

    /**
     * This method return all the comments from the system. It uses the Comment domain object
     * to get the data. It then transforms this collection of Comment object into a
     * collection of CommentDTO and returns it.
     *
     * @return a collection of CommentDTO
     * @throws ObjectNotFoundException is thrown if the collection is empty
     * @throws RemoteException         is thrown if a remote call fails
     */
    Collection findComments() throws FinderException, RemoteException;

    /**
     * This method return all the comments from a specified customer. It uses the Comment domain object
     * to get the data. It then transforms this collection of Comment object into a
     * collection of CommentDTO and returns it.
     *
     * @param customerId identifier
     * @return a collection of CommentDTO
     * @throws ObjectNotFoundException is thrown if the collection is empty
     * @throws RemoteException         is thrown if a remote call fails
     */
    Collection findCommentsFromCustomer(String customerId) throws FinderException, RemoteException;
}
