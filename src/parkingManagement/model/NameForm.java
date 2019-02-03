package parkingManagement.model;

public class NameForm {
	private String name="";
	private String greetingText="";
	private String currentTime="";
	private String id="";
	private String idText = "";
	private String currentTimeText = "";
	public String getCurrentTime() {
		return currentTime;
	}
	public void setCurrentTime(String currentTime) {
		this.currentTime = currentTime;
	}
	public String getCurrentTimeText() {
		return currentTimeText;
	}
	public void setCurrentTimeText(String currentTimeText) {
		this.currentTimeText = currentTimeText;
	}
	public String getName() {
		return name;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}	
	public void setIdText(String idText) {
		this.idText = idText;
	}
	public String getIdText() {
		return idText;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGreetingText() {
		return greetingText;
	}
	public void setGreetingText(String greetingText) {
		this.greetingText = greetingText;
	}
}
