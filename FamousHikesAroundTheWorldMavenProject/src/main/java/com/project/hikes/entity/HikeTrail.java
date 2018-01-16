package com.project.hikes.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

import com.project.hikes.validation.HikeDistanceOnlyPositiveNumbers;

@Entity
@Table(name="trails")
public class HikeTrail {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	@NotNull(message="Name is required")
	@Size(min=3, message="Name must be at least 3 characters")
	private String name;
	
	@Column(name="location")
	@NotNull(message="Location is required")
	private String location;
	
	@Column(name="distance")
	@NotNull(message="Distance is required")
	//@Min(value= 0, message="Distance must be positive value")
	@HikeDistanceOnlyPositiveNumbers
	private Float distance;
	
	@Column(name="duration")
	@NotNull(message="Duration is required")
	private String duration;
	
	@Column(name="best_time")
	@NotNull(message="Best Time is required")
	private String bestTime;
	
	@Column(name="image_url")
	@NotNull(message="Image URL is required")
	private String imageUrl;
	
	@Column(name="description")
	@NotNull(message="Description is required")
	private String description;
	
	@Column(name="create_date")
	private Date createDate = new Date();
	
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="trail_id")
	private List<HikeReview> reviews;
	
	
	public HikeTrail(){}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Float getDistance() {
		return distance;
	}

	public void setDistance(Float distance) {
		this.distance = distance;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getBestTime() {
		return bestTime;
	}

	public void setBestTime(String bestTime) {
		this.bestTime = bestTime;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public List<HikeReview> getReviews() {
		return reviews;
	}


	public void setReviews(List<HikeReview> reviews) {
		this.reviews = reviews;
	}

	
	@Override
	public String toString() {
		return "HikeTrail [id=" + id + ", name=" + name + ", location=" + location + ", distance=" + distance
				+ ", duration=" + duration + ", bestTime=" + bestTime + ", imageUrl=" + imageUrl + ", description="
				+ description + ", createDate=" + createDate + ", reviews=" + reviews + "]";
	}


	//add hike review
	public void addReview(HikeReview review){
		//System.out.println("Inside addReview method, review= "+review.getReview());;
		if(reviews==null){
			reviews = new ArrayList<HikeReview>();
		}
		reviews.add(review);
	}
	
}
