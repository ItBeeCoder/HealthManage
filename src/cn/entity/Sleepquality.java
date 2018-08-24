package cn.entity;

/**
 * Sleepquality entity. @author MyEclipse Persistence Tools
 */

public class Sleepquality implements java.io.Serializable {

	// Fields

	private Integer sleepQualityId;
	private Oldman oldman;
	private String sleepDate;
	private String goToBed;
	private String getUp;
	private Integer socre;

	// Constructors

	/** default constructor */
	public Sleepquality() {
	}

	/** minimal constructor */
	public Sleepquality(Oldman oldman, String sleepDate) {
		this.oldman = oldman;
		this.sleepDate = sleepDate;
	}

	/** full constructor */
	public Sleepquality(Oldman oldman, String sleepDate, String goToBed,
			String getUp, Integer socre) {
		this.oldman = oldman;
		this.sleepDate = sleepDate;
		this.goToBed = goToBed;
		this.getUp = getUp;
		this.socre = socre;
	}

	// Property accessors

	public Integer getSleepQualityId() {
		return this.sleepQualityId;
	}

	public void setSleepQualityId(Integer sleepQualityId) {
		this.sleepQualityId = sleepQualityId;
	}

	public Oldman getOldman() {
		return this.oldman;
	}

	public void setOldman(Oldman oldman) {
		this.oldman = oldman;
	}

	public String getSleepDate() {
		return this.sleepDate;
	}

	public void setSleepDate(String sleepDate) {
		this.sleepDate = sleepDate;
	}

	public String getGoToBed() {
		return this.goToBed;
	}

	public void setGoToBed(String goToBed) {
		this.goToBed = goToBed;
	}

	public String getGetUp() {
		return this.getUp;
	}

	public void setGetUp(String getUp) {
		this.getUp = getUp;
	}

	public Integer getSocre() {
		return this.socre;
	}

	public void setSocre(Integer socre) {
		this.socre = socre;
	}

}