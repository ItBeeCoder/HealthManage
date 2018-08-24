package cn.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Child entity. @author MyEclipse Persistence Tools
 */

public class Child implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private Integer childId;
	private String username;
	private String childuseraccount;
	private String password;
	private String address;
	private String telephone;

	private Role role;
	private Set relations = new HashSet(0);

	// Constructors

	/** default constructor */
	public Child() {
	}

	/** minimal constructor */
	public Child(String username) {
		this.username = username;
	}

	/** full constructor */
	public Child(Integer childId, String username,String  childuseraccount,String password,
			String address, String telephone, Role role, Set relations) {
		super();
		this.childId = childId;
		this.username = username;
		this.childuseraccount=childuseraccount;
		this.password = password;
		this.address = address;
		this.telephone = telephone;
		this.role = role;
		this.relations = relations;
	}

	// Property accessors

	public Integer getChildId() {
		return this.childId;
	}

	public void setChildId(Integer childId) {
		this.childId = childId;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	public String getChilduseraccount() {
		return childuseraccount;
	}

	public void setChilduseraccount(String childuseraccount) {
		this.childuseraccount = childuseraccount;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Set getRelations() {
		return this.relations;
	}

	public void setRelations(Set relations) {
		this.relations = relations;
	}

}