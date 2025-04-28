package com.crm.customer_service.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "customers")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message="Name cannot be blank")
    @Size(min=2, max=30, message="Name must be between 2 to 50 characters")
    private String name;

    @Column(nullable = false, unique = true, name = "emailId")
    @NotBlank(message="Email cannot be blank")
    @Email(message="Invalid Email address format")
    private String email;

    @Column(nullable = false, unique = true)
    @Pattern(regexp = "^\\d{10}$", message = "Mobile number must be 10 digits")
    private String phone;

    private String interactions;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getInteractions() {
		return interactions;
	}

	public void setInteractions(String interactions) {
		this.interactions = interactions;
	}
    
    

}