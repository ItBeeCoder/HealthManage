package cn.entity;

/**
 * Breath entity. @author MyEclipse Persistence Tools
 */

public class Breath implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private Long breathId;
	
	private Oldman oldman;
	
	private String breathDateTime;

	// Constructors

	/** default constructor */
	public Breath() {
	}

	/** full constructor */
	public Breath(Oldman oldman, String breathDateTime) {
		this.oldman = oldman;
		this.breathDateTime = breathDateTime;
	}

	// Property accessors

	public Long getBreathId() {
		return this.breathId;
	}

	public void setBreathId(Long breathId) {
		this.breathId = breathId;
	}

	public Oldman getOldman() {
		return this.oldman;
	}

	public void setOldman(Oldman oldman) {
		this.oldman = oldman;
	}

	public String getBreathDateTime() {
		return this.breathDateTime;
	}

	public void setBreathDateTime(String breathDateTime) {
		this.breathDateTime = breathDateTime;
	}

}