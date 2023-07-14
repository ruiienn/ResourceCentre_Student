public class Camcorder extends Item {
    private int opticalZoom;

    public Camcorder(String assetTag, String description, int opticalZoom) {
        super(assetTag, description);
        this.opticalZoom = opticalZoom;
    }

    public int getOpticalZoom() {
        return opticalZoom;
    }

    public String toString() {
        return String.format("%-15s %-30s %-15d", getAssetTag(), getDescription(), getOpticalZoom());
    }
}
