package ece373.catan.card;

public class RoadBuildingCard extends DevelopmentCard{
	private String title;
	private String description;
	private boolean usedOnce;
	
	public RoadBuildingCard() {
		title = "Road Buidling Card";
		description = "Place 1 new road as if you had just built it.";
		usedOnce = false;
				
	}
	
	public void setUsedOnce(boolean used_once) {
		usedOnce = used_once;
		return;
	}
	
	public boolean getUsedOnce() {
		return usedOnce;
	}
}
