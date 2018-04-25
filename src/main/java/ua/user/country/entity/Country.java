package ua.user.country.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table (name = "country")
public class Country extends BaseEntity {
	
	@Column (name = "name")
	private String name;
	
	@OneToMany(mappedBy = "country", cascade = {CascadeType.ALL})
	private List<City> cities = new ArrayList<>();

	public Country() {
	
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Country [Id=" + getId() + " name=" + name + "]";
	}
	
	
	
	

}
