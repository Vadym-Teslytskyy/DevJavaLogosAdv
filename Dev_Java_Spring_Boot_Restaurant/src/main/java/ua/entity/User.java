package ua.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="_user")
public class User extends AbstractEntity{

	private String email;
	
	private String password;
	
	private Role role;
	
	private String photoUrl;

	private int version;
	
	@OneToOne(fetch=FetchType.LAZY)
	private Place place;
	
	@OneToMany(mappedBy="user")
	List<Meal> tastedMeals = new ArrayList<>();

	public List<Meal> getTastedMeals() {
		return tastedMeals;
	}

	public void setTastedMeals(List<Meal> tastedMeals) {
		this.tastedMeals = tastedMeals;
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
}
