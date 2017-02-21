package com.yaps.petstore.server.domain;

import java.util.Date;

import com.yaps.petstore.common.exception.CheckException;
import com.yaps.petstore.server.domain.comment.Comment;
import com.yaps.petstore.server.domain.customer.Customer;

import junit.framework.TestCase;
import junit.framework.TestSuite;

public class CommentTest extends TestCase {
    public static TestSuite suite() {
        return new TestSuite(CommentTest.class);
    }

	/** This test check the constructor with 2 arguments. */
	public void testConstructorWith2Arguments() {
		String title = "Un titre assez long.";
		String text = "Mon commentaire ici";
		Comment comment = new Comment(title, text);
		assertEquals(title, comment.getTitle());
		assertEquals(text, comment.getText());
	}

	/** This test tries to create valid comments. */
	public void testCreateValidComments() {
		String title = "Un titre assez long ...";
		String text = "Mon commentaire ici";
		Comment comment = new Comment(title, text);
		assertEquals(title, comment.getTitle());
		assertEquals(text, comment.getText());
		assertTrue(comment.checkTitle());

		try {
			comment.checkData();
		} catch (CheckException e) {
			fail("Comment data is OK!");
		}
	}

	/** This test tries to create comments with invalid title lengths.  */
	public void testCheckTitleWithInvalidLengths() {
		String title = "Le nombre de caractsb res du  titre du commentaire est limit sb une soixantaine!";
		String text = "Mon commentaire ici";
		Comment comment = new Comment(title, text);
		assertFalse(comment.checkTitle());

		title = "Court!";
		comment = new Comment(title, text);
		assertFalse("Ce titre est trop court", comment.checkTitle());
	}

	/** This test checks valid last character in title.  */
	public void testCheckTitleWithValidLastCharacter() {
		String title = "Un titre peut stre termins par un point.";
		String text = "Mon commentaire ici";
		Comment comment = new Comment(title, text);
		assertTrue(comment.checkTitle());
		title = "Un titre peut-il stre termins par un point d'interrogation?";
//		title = "Un titre peut-il stre terns n?";
		comment = new Comment(title, text);
		assertTrue(comment.checkTitle());
		title = "Un titre doit stre termins par un point d'exclamation!";
//		title = "Un titre doit stre terminsexclamation!";
		comment = new Comment(title, text);
		assertTrue(comment.checkTitle());
	}

	/** This test checks invalid last character in title.  */
	public void testCheckTitleWithInvalidLastCharacter() {
		String title = "Un titre doit stre termins par un point";
		String text = "Mon commentaire ici";
		Comment comment = new Comment(title, text);
		assertFalse(comment.checkTitle());

		title = "Un titre ne peut pas stre stre termins par un point-virgule;";
		comment = new Comment(title, text);
		assertFalse(comment.checkTitle());
	}

	/** This test checks valid first character in title.  */
	public void testCheckTitleWithValidFirstCharacter() {
		String title = "Un titre avec majuscule initiale!";
		String text = "Mon commentaire ici";
		Comment comment = new Comment(title, text);
		assertTrue(comment.checkTitle());
		
		title = "20! Pourquoi pas un chiffre!";
		comment = new Comment(title, text);
		assertTrue(comment.checkTitle());
	}

	/** This test checks invalid first character in title.  */
	public void testCheckTitleWithInvalidFirstCharacter() {
		String title = "un titre sans majuscule initiale!";
		String text = "Mon commentaire ici";
		Comment comment = new Comment(title, text);
		assertFalse(comment.checkTitle());
		
		title = "# : premier caractsre interdit!";
		comment = new Comment(title, text);
		assertFalse(title, comment.checkTitle());
		
		for ( char c : ".!?;:,/&@".toCharArray() ) {
			title = c + ": premier caractsre interdit!";
			comment = new Comment(title, text);
			assertFalse(title, comment.checkTitle());
		} 
	}
	
	/** This test tries to create comments with invalid data. */
	public void testCreateCommentsWithInvalidData() {
		String title = "Un titre assez long ... ";
		String text = "Mon commentaire ici";
		Comment comment = new Comment(null, text);
		try {
			comment.checkData();
			fail("Comment data is invalid!");
		} catch (CheckException e) {
		}
		comment = new Comment("", text);
		try {
			comment.checkData();
			fail("Comment data is invalid!");
		} catch (CheckException e) {
		}
		comment = new Comment(title, "");
		try {
			comment.checkData();
			fail("Comment data is invalid!");
		} catch (CheckException e) {
		}
		comment = new Comment(title, null);
		try {
			comment.checkData();
			fail("Comment data is invalid!");
		} catch (CheckException e) {
		}
	}	

	/** This test check the constructor with 5 arguments. */
	public void testConstructorWith5Arguments() {
		String id = null;
		String title = "Un titre assez long.";
		String text = "Mon commentaire ici";
		Customer customer = null;
		Date date = new Date();
		Comment comment = new Comment(id, title, text, customer, date);
		assertEquals(id, comment.getId());
		assertEquals(title, comment.getTitle());
		assertEquals(text, comment.getText());
		assertEquals(customer, comment.getCustomer());
		assertEquals(date, comment.getDate());
	}
}
