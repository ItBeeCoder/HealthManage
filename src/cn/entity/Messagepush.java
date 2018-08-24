package cn.entity;

/**
 * Messagepush entity. @author MyEclipse Persistence Tools
 */

public class Messagepush implements java.io.Serializable {

	// Fields

	private Integer messagePushId;
	private Oldman oldman;
	private String pushTime;
	private String title;
	private String content;

	// Constructors

	/** default constructor */
	public Messagepush() {
	}

	/** minimal constructor */
	public Messagepush(Oldman oldman) {
		this.oldman = oldman;
	}

	/** full constructor */
	public Messagepush(Oldman oldman, String pushTime, String title,
			String content) {
		this.oldman = oldman;
		this.pushTime = pushTime;
		this.title = title;
		this.content = content;
	}

	// Property accessors

	public Integer getMessagePushId() {
		return this.messagePushId;
	}

	public void setMessagePushId(Integer messagePushId) {
		this.messagePushId = messagePushId;
	}

	public Oldman getOldman() {
		return this.oldman;
	}

	public void setOldman(Oldman oldman) {
		this.oldman = oldman;
	}

	public String getPushTime() {
		return this.pushTime;
	}

	public void setPushTime(String pushTime) {
		this.pushTime = pushTime;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}