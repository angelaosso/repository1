package model;

public abstract class Audio implements Playable{

    private String name;
    private String url;
    private double duration;
    private int reproducedTimes;

    public Audio(String name, String url, double duration) {
        this.name = name;
        this.url = url;
        this.duration = duration;
        this.reproducedTimes = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public int getReproducedTimes() {
        return reproducedTimes;
    }

    public void setReproducedTimes(int reproducedTimes) {
        this.reproducedTimes = reproducedTimes;
    }
}
