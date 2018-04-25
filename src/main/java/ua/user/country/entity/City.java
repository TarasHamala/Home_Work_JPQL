package ua.user.country.entity;

import javax.persistence.*;

@Entity
@Table (name = "city")
public class City extends BaseEntity{
	
	@Column (name = "name")
	private String name;
	
	@OneToOne(fetch = FetchType.LAZY,cascade = { CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "country_id")
	private Country country;

	public City() {
		
	}

	public Country getCountry() {
		return country;
	}


	public void setCountry(Country country) {
		this.country = country;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "City [Id=" + getId() + " name=" + name + "]";
	}

	
	
	

}
