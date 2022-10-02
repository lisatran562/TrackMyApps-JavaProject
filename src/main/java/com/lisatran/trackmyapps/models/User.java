package com.lisatran.trackmyapps.models;

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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="users")
public class User {
	// attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message="First name is required!")
    @Size(min=1, max=30, message="First name must be at least 1 character")
    private String firstName;
    
    @NotNull(message="Last name is required!")
    @Size(min=2, max=30, message="Last name must be between 2 characters")
    private String lastName;
    
    @NotNull(message="Email is required!")
    @Email(message="Please enter a valid email!")
    private String email;
    
    @NotNull(message="Password is required!")
    @Size(min=8, max=128, message="Password must be between 8 and 128 characters") 
    private String password;     // will be hashed, make sure the max is at least 128
    

    @Transient 
    @Size(min=8, max=128, message="Confirm Password must be between 8 and 128 characters")
    private String confirm;
    
    private String github;
    
    private String linkedin;
    
    private String resume;
    
//     MANY-TO-MANY
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
    		name = "favorites",
    		joinColumns = @JoinColumn(name = "user_id"),
    		inverseJoinColumns = @JoinColumn(name = "job_id")
    		)
    private List<Job> favoritedJobs;

    // ONE-TO-MANY
    
	
	@OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    private List<Job> jobs;
	
	@OneToMany(mappedBy="user", fetch = FetchType.LAZY)
	private List<Archive> archives;
	
    // createdAt & updatedAt : optional
    
    @Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;  
    
	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
   
    // constructor
    public User() {}
    
	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public String getConfirm() {
		return confirm;
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
	
	
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public List<Job> getJobs() {
		return jobs;
	}
	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	public List<Job> getFavoritedJobs() {
		return favoritedJobs;
	}
	public void setFavoritedJobs(List<Job> favoritedJobs) {
		this.favoritedJobs = favoritedJobs;
	}
	public String getGithub() {
		return github;
	}
	public void setGithub(String github) {
		this.github = github;
	}
	public String getLinkedin() {
		return linkedin;
	}
	public void setLinkedin(String linkedin) {
		this.linkedin = linkedin;
	}
	public String getResume() {
		return resume;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}
	public List<Archive> getArchives() {
		return archives;
	}
	public void setArchives(List<Archive> archives) {
		this.archives = archives;
	}

	
}
