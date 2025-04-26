package com.cognizant.userservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@Table(name = "user_tbl")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String username;

	@Enumerated(EnumType.STRING)
	private Role role;

	private String firstName;
	private String lastName;

	@Column(unique = true)
	private String email;
	private String password;
	private Long mobile;
	private Boolean active;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false) // Preventing the modification
	private Date createdAt;

	private Long createdBy;
	private Long updatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	@PrePersist
	protected void onCreate() {
		createdAt = new Date();
		updatedAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		updatedAt = new Date();
	}

	public enum Role {
		USER, ADMIN, MODERATOR
	}

}
