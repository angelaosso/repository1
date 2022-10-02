package model;

public class Enemy {

    private String name;
    private EnemyType type;
    private double pointsPlayerWins;
    private double PointsPlayerLoses;
    private double[] position;

    public Enemy(String name, int typeNumber, double pointsPlayerWins, double PointsPlayerLoses, double positionX, double positionY){
        this.name = name;
        this.type = EnemyType.values()[typeNumber];
        this.pointsPlayerWins = pointsPlayerWins;
        this.PointsPlayerLoses = PointsPlayerLoses;
        this.position = new double[]{positionX, positionY};
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public EnemyType getType() {
        return type;
    }


    public double getPointsPlayerWins() {
        return pointsPlayerWins;
    }

    public void setPointsPlayerWins(double newPointsPlayerWins) {
        this.pointsPlayerWins = newPointsPlayerWins;
    }

    public double getPointsPlayerLoses() {
        return PointsPlayerLoses;
    }

    public void setPointsPlayerLoses(double newPointsPlayerLoses) {
        this.PointsPlayerLoses = newPointsPlayerLoses;
    }

    public String getPosition() {
        return "Position: (" + position[0] + ", " + position[1] + ")";
    }

    public void setPosition(double[] position) {
        this.position = position;
    }
}
