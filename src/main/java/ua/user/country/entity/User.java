package ua.user.country.entity;

import javax.persistence.*;

@Entity
@Table (name = "user")

public class User extends BaseEntity {

	@Column (name = "full_name")
	private String fullName;
	
	@Column (name = "age")
	private int age;
	
	@OneToOne (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "country_id")
	private Country country;
	
	@OneToOne (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "city_id")
	private City city;

	public User() {
		
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "User [Id=" + getId() +" fullName=" + fullName + ", age=" + age + "]";
	}

	


	
	
	
}
