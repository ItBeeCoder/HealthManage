package cn.entity;

/**
 * Pillow entity. @author MyEclipse Persistence Tools
 */

public class Pillow implements java.io.Serializable {

	// Fields

	private Integer pillowId;
	private String pillowNumber;
	private Oldman oldman;
	private String statement;
	private String configurationTime;

	// Constructors

	/** default constructor */
	public Pillow() {
	}

	/** minimal constructor */
	public Pillow(String pillowNumber) {
		this.pillowNumber = pillowNumber;
	}

	/** full constructor */
	public Pillow(Integer pillowId, String pillowNumber, Oldman oldman,
			String statement, String configurationTime) {
		super();
		this.pillowId = pillowId;
		this.pillowNumber = pillowNumber;
		this.oldman = oldman;
		this.statement = statement;
		this.configurationTime = configurationTime;
	}

	// Property accessors

	public Integer getPillowId() {
		return this.pillowId;
	}

	public void setPillowId(Integer pillowId) {
		this.pillowId = pillowId;
	}

	public String getPillowNumber() {
		return pillowNumber;
	}

	public void setPillowNumber(String pillowNumber) {
		this.pillowNumber = pillowNumber;
	}

	public Oldman getOldman() {
		return this.oldman;
	}

	public void setOldman(Oldman oldman) {
		this.oldman = oldman;
	}

	public String getStatement() {
		return this.statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	public String getConfigurationTime() {
		return this.configurationTime;
	}

	public void setConfigurationTime(String configurationTime) {
		this.configurationTime = configurationTime;
	}

}