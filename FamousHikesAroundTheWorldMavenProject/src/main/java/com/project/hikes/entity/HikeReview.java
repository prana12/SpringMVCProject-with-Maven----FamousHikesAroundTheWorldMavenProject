package com.project.hikes.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="reviews")
public class HikeReview {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="review_id")
	private int id;

	@Column(name="review")
	@NotNull(message="Review is required")
	private String review;

	@Column(name="create_date")
	private Date createDate = new Date();

	//add new field for user(also setter/getter methods)
	@OneToOne(cascade={CascadeType.DETACH,CascadeType.MERGE,
			CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="created_by")
	private HikeUser createdBy;
	

	public HikeReview(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public HikeUser getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(HikeUser createdBy) {
		this.createdBy = createdBy;
	}

	@Override
	public String toString() {
		return "HikeReview [id=" + id + ", review=" + review + ", createDate=" + createDate + ", createdBy=" + createdBy
				+ "]";
	}

}
