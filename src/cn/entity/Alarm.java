package cn.entity;

/**
 * Alarm entity. @author MyEclipse Persistence Tools
 */

public class Alarm implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private Integer alarmId;
	private Oldman oldman;
	private String type;
	private String realOrNot;
	private String alarmTime;

	// Constructors

	/** default constructor */
	public Alarm() {
	}

	/** minimal constructor */
	public Alarm(Oldman oldman) {
		this.oldman = oldman;
	}

	/** full constructor */
	public Alarm(Oldman oldman, String type, String realOrNot, String alarmTime) {
		this.oldman = oldman;
		this.type = type;
		this.realOrNot = realOrNot;
		this.alarmTime = alarmTime;
	}

	// Property accessors

	public Integer getAlarmId() {
		return this.alarmId;
	}

	public void setAlarmId(Integer alarmId) {
		this.alarmId = alarmId;
	}

	public Oldman getOldman() {
		return this.oldman;
	}

	public void setOldman(Oldman oldman) {
		this.oldman = oldman;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRealOrNot() {
		return this.realOrNot;
	}

	public void setRealOrNot(String realOrNot) {
		this.realOrNot = realOrNot;
	}

	public String getAlarmTime() {
		return this.alarmTime;
	}

	public void setAlarmTime(String alarmTime) {
		this.alarmTime = alarmTime;
	}

}