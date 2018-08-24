package cn.entity;

/**
 * Heart entity. @author MyEclipse Persistence Tools
 */

public class Heart implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private Integer heartId;
	private Oldman oldman;
	private String heartDateTime;

	// Constructors

	/** default constructor */
	public Heart() {
	}

	/** full constructor */
	public Heart(Oldman oldman, String heartDateTime) {
		this.oldman = oldman;
		this.heartDateTime = heartDateTime;
	}

	// Property accessors

	public Integer getHeartId() {
		return this.heartId;
	}

	public void setHeartId(Integer heartId) {
		this.heartId = heartId;
	}

	public Oldman getOldman() {
		return this.oldman;
	}

	public void setOldman(Oldman oldman) {
		this.oldman = oldman;
	}

	public String getHeartDateTime() {
		return this.heartDateTime;
	}

	public void setHeartDateTime(String heartDateTime) {
		this.heartDateTime = heartDateTime;
	}

}