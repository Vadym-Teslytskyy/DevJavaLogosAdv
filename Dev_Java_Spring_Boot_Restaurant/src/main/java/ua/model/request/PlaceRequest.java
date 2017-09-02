package ua.model.request;

import org.hibernate.validator.constraints.NotBlank;

public class PlaceRequest {
	
	private Integer id;
	
	@NotBlank(message="Поле не може бути пустим")
	private String countofPeople;
	
	@NotBlank(message="Поле не може бути пустим")
	private String number;
	
	private String isFree;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCountofPeople() {
		return countofPeople;
	}

	public void setCountofPeople(String countofPeople) {
		this.countofPeople = countofPeople;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getIsFree() {
		return isFree;
	}

	public void setIsFree(String isFree) {
		this.isFree = isFree;
	}
	
	

}
