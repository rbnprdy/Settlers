package ece373.catan.card;

public class ResourceCard extends Card {
	private ResourceType type;
	
	public ResourceType getType() {
		return type;
	}
	public void setType(ResourceType new_type) {
		type = new_type;
		return;
	}
}
