package cn.entity;

/**
 * Movement entity. @author MyEclipse Persistence Tools
 */

public class Movement implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private Long movementId;
	private Oldman oldman;
	private String movementDateTime;
	private String start;
	private String stop;

	// Constructors

	/** default constructor */
	public Movement() {
	}

	/** minimal constructor */
	public Movement(Oldman oldman, String movementDateTime) {
		this.oldman = oldman;
		this.movementDateTime = movementDateTime;
	}

	/** full constructor */
	public Movement(Oldman oldman, String movementDateTime, String start,
			String stop) {
		this.oldman = oldman;
		this.movementDateTime = movementDateTime;
		this.start = start;
		this.stop = stop;
	}

	// Property accessors

	public Long getMovementId() {
		return this.movementId;
	}

	public void setMovementId(Long movementId) {
		this.movementId = movementId;
	}

	public Oldman getOldman() {
		return this.oldman;
	}

	public void setOldman(Oldman oldman) {
		this.oldman = oldman;
	}

	public String getMovementDateTime() {
		return this.movementDateTime;
	}

	public void setMovementDateTime(String movementDateTime) {
		this.movementDateTime = movementDateTime;
	}

	public String getStart() {
		return this.start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getStop() {
		return this.stop;
	}

	public void setStop(String stop) {
		this.stop = stop;
	}

}