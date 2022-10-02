package model;

public class Player{
    private String nickname;
    private String name;
    private double points;
    private int lives;
    private Level level;


    public Player(String nickname, String name, Level level){
        this.nickname = nickname;
        this.name = name;
        this.lives = 5;
        this.points = 10;
        this.level = level;

    }

    public String getNickname(){
        return this.nickname;
    }

    public void setNickname(String newNickname){
        this.nickname = newNickname;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String newName){
        this.name = name;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double newPoints) {
        this.points = newPoints;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int newLives) {
        this.lives = newLives;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level newLevel) {
        this.level = newLevel;
    }
}
