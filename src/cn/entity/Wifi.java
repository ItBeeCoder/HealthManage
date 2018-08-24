package cn.entity;

/**
 * Wifi entity. @author MyEclipse Persistence Tools
 */

public class Wifi implements java.io.Serializable {

	// Fields

	private Integer wifiId;
	private String wifiNumber;
	private Oldman oldman;
	private String statement;
	private String configurationTime;

	// Constructors

	/** default constructor */
	public Wifi() {
	}

	/** minimal constructor */
	public Wifi(String wifiNumber) {
		this.wifiNumber = wifiNumber;
	}

	/** full constructor */

	public Wifi(Integer wifiId, String wifiNumber, Oldman oldman,
			String statement, String configurationTime) {
		super();
		this.wifiId = wifiId;
		this.wifiNumber = wifiNumber;
		this.oldman = oldman;
		this.statement = statement;
		this.configurationTime = configurationTime;
	}

	// Property accessors

	public Integer getWifiId() {
		return wifiId;
	}

	public void setWifiId(Integer wifiId) {
		this.wifiId = wifiId;
	}

	public String getWifiNumber() {
		return wifiNumber;
	}

	public void setWifiNumber(String wifiNumber) {
		this.wifiNumber = wifiNumber;
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