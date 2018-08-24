package cn.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Oldman entity. @author MyEclipse Persistence Tools
 */

public class Oldman implements java.io.Serializable {

	// Fields

	/**
	 * author:shaozq
	   2017-7-30上午08:22:14
	 */
	private static final long serialVersionUID = 1L;

	private Integer oldManId;
	
	//////private String account;
	private String username;
	private String Oldmanuseraccount;
	private String password;
	private String gender;
	private Double height;
	private Double weight;
	private Integer age;
	private String illness;
	private String address;
	private String telephone;

	private Role role;
	private Nurse nurse;
	private Pillow pillow;
	private Wifi wifi;

	private Set sleepqualities = new HashSet(0);
	private Set movements = new HashSet(0);
	private Set messagepushs = new HashSet(0);
	private Set alarms = new HashSet(0);
	private Set relations = new HashSet(0);
	private Set hearts = new HashSet(0);
	private Set breaths = new HashSet(0);

	// Constructors

	/** default constructor */
	public Oldman() {
	}

	/** minimal constructor */
	public Oldman(String Oldmanuseraccount, String password) {
		this.Oldmanuseraccount =Oldmanuseraccount;
		this.password = password;
	}

	public Oldman(Integer oldManId, String username,String Oldmanuseraccount, String password,
			String gender, Double height, Double weight, Integer age,
			String illness, String address, String telephone, Role role,
			Nurse nurse, Pillow pillow, Wifi wifi, Set sleepqualities,
			Set movements, Set messagepushs, Set alarms, Set relations,
			Set hearts, Set breaths) {
		super();
		this.oldManId = oldManId;
		this.username = username;
		this.Oldmanuseraccount=Oldmanuseraccount;
		this.password = password;
		this.gender = gender;
		this.height = height;
		this.weight = weight;
		this.age = age;
		this.illness = illness;
		this.address = address;
		this.telephone = telephone;
		this.role = role;
		this.nurse = nurse;
		this.pillow = pillow;
		this.wifi = wifi;
		this.sleepqualities = sleepqualities;
		this.movements = movements;
		this.messagepushs = messagepushs;
		this.alarms = alarms;
		this.relations = relations;
		this.hearts = hearts;
		this.breaths = breaths;
	}

	// Property accessors

	public Integer getOldManId() {
		return this.oldManId;
	}

	public void setOldManId(Integer oldManId) {
		this.oldManId = oldManId;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	public String getOldmanuseraccount() {
		return Oldmanuseraccount;
	}

	public void setOldmanuseraccount(String oldmanuseraccount) {
		Oldmanuseraccount = oldmanuseraccount;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Double getHeight() {
		return this.height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getWeight() {
		return this.weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getIllness() {
		return this.illness;
	}

	public void setIllness(String illness) {
		this.illness = illness;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Nurse getNurse() {
		return nurse;
	}

	public void setNurse(Nurse nurse) {
		this.nurse = nurse;
	}

	public Pillow getPillow() {
		return pillow;
	}

	public void setPillow(Pillow pillow) {
		this.pillow = pillow;
	}

	public Wifi getWifi() {
		return wifi;
	}

	public void setWifi(Wifi wifi) {
		this.wifi = wifi;
	}

	public Set getSleepqualities() {
		return this.sleepqualities;
	}

	public void setSleepqualities(Set sleepqualities) {
		this.sleepqualities = sleepqualities;
	}

	public Set getMovements() {
		return this.movements;
	}

	public void setMovements(Set movements) {
		this.movements = movements;
	}

	public Set getMessagepushs() {
		return this.messagepushs;
	}

	public void setMessagepushs(Set messagepushs) {
		this.messagepushs = messagepushs;
	}

	public Set getAlarms() {
		return this.alarms;
	}

	public void setAlarms(Set alarms) {
		this.alarms = alarms;
	}

	public Set getRelations() {
		return this.relations;
	}

	public void setRelations(Set relations) {
		this.relations = relations;
	}

	public Set getHearts() {
		return this.hearts;
	}

	public void setHearts(Set hearts) {
		this.hearts = hearts;
	}

	public Set getBreaths() {
		return this.breaths;
	}

	public void setBreaths(Set breaths) {
		this.breaths = breaths;
	}

}