package model;

import java.util.ArrayList;

public abstract class Producer extends User{

    private String name;
    private String url;
    private int reproducedTimes;
    private double listenedTime;
    private ArrayList<Audio> audios;

    public Producer(String nickname, String id, String name, String url) {
        super(nickname, id);
        this.name = name;
        this.url = url;
        this.reproducedTimes = 0;
        this.listenedTime = 0;
        audios = new ArrayList<Audio>();
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

    public int getReproducedTimes() {
        return reproducedTimes;
    }

    public void setReproducedTimes(int reproducedTimes) {
        this.reproducedTimes = reproducedTimes;
    }

    public double getListenedTime() {
        return listenedTime;
    }

    public void setListenedTime(double listenedTime) {
        this.listenedTime = listenedTime;
    }

    public ArrayList<Audio> getAudios() {
        return audios;
    }


}
