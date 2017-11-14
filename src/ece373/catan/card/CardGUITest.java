package ece373.catan.card;

public class CardGUITest {
	public static void main(String[] args) {
		
		CardGUI newGUI;
		ResourceCard rc1 = new ResourceCard();
		rc1.setType(ResourceType.SHEEP);
		
		newGUI = new CardGUI(rc1);
	
	}
}
