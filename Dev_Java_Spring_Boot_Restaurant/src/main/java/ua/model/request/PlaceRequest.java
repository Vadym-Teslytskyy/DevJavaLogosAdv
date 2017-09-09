package ua.model.request;


import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import ua.validation.annotation.UniquePlace;
import ua.validation.flag.PlaceFlag;

public class PlaceRequest {
	
	private Integer id;
	
	@NotBlank(message="Поле не може бути пустим", groups = { PlaceFlag.class })
	@Pattern(regexp = "^[1-9][0-9]*| *$", message = "Поле не може бути нулем або символом", groups = {
			PlaceFlag.class })
	private String countofPeople;
	
	@UniquePlace(message="Такий столик вже існує", groups = PlaceFlag.class)
	@NotBlank(message="Поле не може бути пустим", groups = { PlaceFlag.class })
	@Pattern(regexp = "^[1-9][0-9]*| *$", message = "Поле не може бути нулем або символом", groups = {
			PlaceFlag.class })
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
