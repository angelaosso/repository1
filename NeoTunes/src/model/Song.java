package model;

public class Song extends Audio{

    private String album;
    private double value;
    private int soldTimes;
    private Genre genre;

    public Song(String name, String url, double duration, String album, double value, Genre genre) {
        super(name, url, duration);
        this.album = album;
        this.value = value;
        this.soldTimes = 0;
        this.genre = genre;
    }
}
