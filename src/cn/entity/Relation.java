package cn.entity;

/**
 * Relation entity. @author MyEclipse Persistence Tools
 */

public class Relation implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private Integer relationId;
	private Child child;
	private Oldman oldman;
	private String relationship;
	private Integer flag;
	
	// Constructors

	/** default constructor */
	public Relation() {
	}

	/** minimal constructor */
	public Relation(Child child, Oldman oldman) {
		this.child = child;
		this.oldman = oldman;
	}

	/** full constructor */
	public Relation(Child child, Oldman oldman, String relationship,Integer flag) {
		this.child = child;
		this.oldman = oldman;
		this.relationship = relationship;
		this.flag = flag;
	}

	// Property accessors

	public Integer getRelationId() {
		return this.relationId;
	}

	public void setRelationId(Integer relationId) {
		this.relationId = relationId;
	}

	public Child getChild() {
		return this.child;
	}

	public void setChild(Child child) {
		this.child = child;
	}

	public Oldman getOldman() {
		return this.oldman;
	}

	public void setOldman(Oldman oldman) {
		this.oldman = oldman;
	}

	public String getRelationship() {
		return this.relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	
}