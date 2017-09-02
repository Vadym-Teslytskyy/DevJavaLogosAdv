package ua.model.view;

public class PlaceView {
	
	private Integer id;
	
	private Integer countofPeople;
	
	private Integer number;
	
	private boolean isFree;

	public PlaceView(Integer id, Integer countofPeople, Integer number, boolean isFree) {
		this.id = id;
		this.countofPeople = countofPeople;
		this.number = number;
		this.isFree = isFree;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCountofPeople() {
		return countofPeople;
	}

	public void setCountofPeople(Integer countofPeople) {
		this.countofPeople = countofPeople;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public boolean getIsFree() {
		return isFree;
	}

	public void setIsFree(boolean isFree) {
		this.isFree = isFree;
	}
	
	
	
	

}
