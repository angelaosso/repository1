package model;

public class Treasure{

    private String name;
    private String url;
    private double points;
    private double[] position;

    public Treasure(String name, String url, double points, double positionX, double positionY){
        this.name = name;
        this.url = url;
        this.points = points;
        this.position = new double[]{positionX, positionY};
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String newUrl) {
        this.url = newUrl;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double newPoints) {
        this.points = newPoints;
    }

    public String getPosition() {
        return "Position: (" + position[0] + ", " + position[1] + ")";
    }

    public void setPosition(double[] position) {
        this.position = position;
    }
}
