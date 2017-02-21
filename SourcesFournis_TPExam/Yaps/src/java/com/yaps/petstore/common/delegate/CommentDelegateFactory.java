package com.yaps.petstore.common.delegate;

public class CommentDelegateFactory  {
	private static CommentDelegate instance = null;
	
	public CommentDelegate createCommentDelegate() {
		if ( instance == null ) {
			String s = System.getProperty("useRMI");
			if ( s != null )
				instance = new CommentRemoteDelegate();
			else
				instance = new CommentLocalDelegate();
		}
		return instance;
	}
}
