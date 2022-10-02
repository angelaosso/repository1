package model;

public class Level {
    private int number;
    private double pointsToPass;
    private Treasure[] treasures;
    private Enemy[] enemies;
    private Difficulty difficulty;
    private int treasurePoints = 0;
    private int enemyPoints = 0;

    public Level(int number, double pointsToPass){
        this.number = number;
        this.pointsToPass = pointsToPass;
        this.enemies = new Enemy[25];
        this.treasures = new Treasure[50];
        checkDifficulty();
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int newNumber) {
        this.number = number;
    }

    public double getPointsToPass() {
        return pointsToPass;
    }

    public void setPointsToPass(int newPointsToPass) {
        this.pointsToPass = pointsToPass;
    }

    public void setDifficulty(int difficultyNumber){
        this.difficulty = Difficulty.values()[difficultyNumber - 1];
    }

    public Difficulty getDifficult(){
        return this.difficulty;
    }

    public String enemiesToString(){
        String enemyList = "";
        for (int i = 0; i < enemies.length; i++){
            if (enemies[i] != null){
                enemyList += (i + 1) + ": " + enemies[i] + "\n";
            }
        }
        return enemyList;
    }

    public String treasuresToString(){
        String treasureList = "";
        for (int i = 0; i < treasures.length; i++){
            if (treasures[i] != null){
                treasureList += (i + 1) + ": " + treasures[i] + "\n";
            }
        }
        return treasureList;
    }

    public Treasure[] getTreasures() {
        return treasures;
    }

    public Enemy[] getEnemies() {
        return enemies;
    }


    /**
     * Description: Adds an enemy to the enemies array
     * @param newEnemy Enemy
     * @return boolean
     * pos: Enemy added to enemies array
     */
    public boolean addEnemy(Enemy newEnemy){
        for (int i = 0; i < enemies.length; i++){
            if (enemies[i] == null){
                enemies[i] = newEnemy;
                enemyPoints += newEnemy.getPointsPlayerLoses();
                return true;
            }
        }
        return false;
    }

    /**
     * Description: Adds a determined amount of treasures to the treasures array
     * @param newTreasure Treasure
     * @param treasureAmount int
     * @return boolean
     * pre: The treasures array has capacity
     * pos: Treasures stored in the treasures array
     */
    public boolean addTreasure(Treasure newTreasure, int treasureAmount){
        for (int j = 0; j <= treasureAmount; j++){
            for (int i = 0; i < treasures.length; i++){
                if (treasures[i] == null){
                    treasures[i] = newTreasure;
                    treasurePoints += newTreasure.getPoints();

                }
            }
        }
        return true;
    }

    /**
     * Description: Checks if the enemies array is full
     * @return boolean
     */
    public boolean enemiesFull(){
        for (int i = 0; i < enemies.length; i++){
            if (enemies[i] == null){
                return false;
            }
        }
        return true;
    }

    /**
     * Description: Checks if the maximum amount of treasures in a level has been reached
     * @return boolean
     */
    public boolean treasuresFull(){
        for (int i = 0; i < treasures.length; i++){
            if (treasures[i] == null){
                return false;
            }
        }
        return true;
    }


    /**
     * Description: Checks if a given enemy is already inside the enemies arrya
     * @param enemy Enemy
     * @return boolean
     */
    public boolean enemyInside(Enemy enemy){
        for (int i = 0; i < enemies.length; i++){
            if (enemies[i] != null){
                if (enemies[i].equals(enemy)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Description: Checks if the points of the treasures in the level are less, equal or greater than the enemies and changes the level difficulty to HIGH, MEDIUM or LOW, respectively
     * @return String
     * pos: Changes the level's difficulty
     */
    public String checkDifficulty(){
        if (treasurePoints > enemyPoints){
            difficulty = Difficulty.LOW;
        }
        else if (treasurePoints == enemyPoints){
            difficulty = Difficulty.MEDIUM;
        }
        else {
            difficulty = Difficulty.HIGH;
        }
        return "The level's difficulty now is: " + difficulty;
    }

}
