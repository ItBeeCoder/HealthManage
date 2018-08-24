package cn.entity;

/**
 * Nurse entity. @author MyEclipse Persistence Tools
 */
public class Nurse implements java.io.Serializable {
	// Fields

	private static final long serialVersionUID = 1L;
	private Integer nurseId;
	private String username;
	private String password;
	private String telephone;

	// Constructors

	/** default constructor */
	public Nurse() {
	}

	/** minimal constructor */
	public Nurse(String username) {
		this.username = username;
	}

	/** full constructor */
	public Nurse(String username, String password, String telephone) {
		this.username = username;
		this.password = password;
		this.telephone = telephone;
	}

	// Property accessors

	public Integer getNurseId() {
		return nurseId;
	}

	public void setNurseId(Integer nurseId) {
		this.nurseId = nurseId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

}