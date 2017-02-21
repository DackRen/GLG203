package com.yaps.petstore.common.dto;
import java.util.Date;


/**
 * This class follows the Data Transfert Object design pattern and for that implements the
 * markup interface DataTransfertObject. It is a client view of a Comment. This class only
 * transfers data from a distant service to a client.
 */
@SuppressWarnings("serial")
public final class CommentDTO implements DataTransfertObject {

    // ======================================
    // =             Attributes             =
    // ======================================
    private String _id;
    private String _title;
    private String _text;
    private Date _date;
	private String _customerId;


    // ======================================
    // =            Constructors            =
    // ======================================
    public CommentDTO() {
    }

    public CommentDTO(final String id, final String title, final String text) {
        this(id, title, text, null, null);
    }

    public CommentDTO(final String id, final String title, final String text, Date date, String customerId) {
        setId(id);
        setTitle(title);
        setText(text);
        setDate(date);
        setCustomerId(customerId);
    }

    // ======================================
    // =         Getters and Setters        =
    // ======================================
    public String getId() {
        return _id;
    }

    public void setId(final String id) {
        _id = id;
    }

    public String getTitle() {
        return _title;
    }

    public void setTitle(final String title) {
        _title = title;
    }

    public String getText() {
        return _text;
    }

    public void setText(String text) {
        _text = text;
    }

	public Date getDate() {
		return _date;
	}

	public void setDate(Date _date) {
		this._date = _date;
	}

	public String getCustomerId() {
		return _customerId;
	}

	public void setCustomerId(String customerId) {
		this._customerId = customerId;
	}

    public String toString() {
        final StringBuffer buf = new StringBuffer();
        buf.append("CommentDTO{");
        buf.append("id=").append(getId());
        buf.append(",title=").append(getTitle());
        buf.append(",text=").append(getText());
        buf.append(",date=").append(getDate());
        buf.append(",customerId=").append(getCustomerId());
        buf.append('}');
        return buf.toString();
    }
}
