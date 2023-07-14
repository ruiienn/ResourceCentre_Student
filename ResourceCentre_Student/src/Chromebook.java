
public class Chromebook extends Item{
	private String os;

	public Chromebook(String assetTag, String description, String os) {
		super(assetTag, description);
		this.os = os;
	}

	public String getOs() {
		return os;
	}
	
	public String toString(){
        return String.format("%-15s %-30s %-15s", getAssetTag(), getDescription(), getOs());

	}
}


