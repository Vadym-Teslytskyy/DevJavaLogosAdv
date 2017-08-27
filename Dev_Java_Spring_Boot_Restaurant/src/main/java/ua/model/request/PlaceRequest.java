package ua.model.request;

public class PlaceRequest {
	
	private Integer id;
	
	private String countofPeople;
	
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
