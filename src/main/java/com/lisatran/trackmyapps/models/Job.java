package com.lisatran.trackmyapps.models;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "jobs")
public class Job {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(updatable = false)
	@NotNull(message = "Company name is required")
	@Size(min = 1, max = 50, message = "Company name must be at least 1 character")
	private String company;

	@NotNull(message = "Position is required")
	@Size(min = 2, max = 50, message = "Position must be at least 2 characters")
	private String position;
	
	@NotNull(message = "Skills required")
	@Size(min = 2, max = 50, message = "Please entered required skills for the position")
	private String skills;

	@NotNull(message = "Location is required")
	@Size(min = 3, max = 50, message = "Position must be at least 3 characters")
	private String location;

	@NotNull(message = "Salary is required")
	@Min(1)
	private BigDecimal salary;

	@NotNull(message = "Job source is required")
	@Size(min = 3, max = 50, message = "Job source must be at least 3 characters")
	private String source;

	@NotNull(message = "Remote entry is required")
	private Boolean remote;


	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "Please add date applied")
	private Date date;
	
	private String status;
	

	// MANY-TO-ONE

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	public Job() {
	};

	// ONE-TO-MANY

	@OneToMany(mappedBy = "job", fetch = FetchType.LAZY)
	private List<Comment> jobComments;

	@OneToMany(mappedBy = "job", fetch = FetchType.LAZY)
	private List<Interview> jobInterviews;
	
	@OneToMany(mappedBy = "job", fetch = FetchType.LAZY)
	private List<Archive> jobArchives;

	// MANY-TO-MANY

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "favorites",
			joinColumns = @JoinColumn(name = "job_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id")
			)
	private List<User> userFavorites;

	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;

	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

	public Long getId() {
		return id;
	}

	public String getCompany() {
		return company;
	}

	public String getPosition() {
		return position;
	}

	public String getLocation() {
		return location;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public String getSource() {
		return source;
	}

	public Boolean getRemote() {
		return remote;
	}


	public Date getCreatedAt() {
		return createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public void setRemote(Boolean remote) {
		this.remote = remote;
	}


	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Comment> getJobComments() {
		return jobComments;
	}

	public void setJobComments(List<Comment> jobComments) {
		this.jobComments = jobComments;
	}

	public List<Interview> getJobInterviews() {
		return jobInterviews;
	}

	public void setJobInterviews(List<Interview> jobInterviews) {
		this.jobInterviews = jobInterviews;
	}

	public List<User> getUserFavorites() {
		return userFavorites;
	}

	public void setUserFavorites(List<User> userFavorites) {
		this.userFavorites = userFavorites;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Archive> getJobArchives() {
		return jobArchives;
	}

	public void setJobArchives(List<Archive> jobArchives) {
		this.jobArchives = jobArchives;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}
	
	

}
