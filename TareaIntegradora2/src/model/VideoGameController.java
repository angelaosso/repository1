package model;

import java.lang.management.PlatformLoggingMXBean;

public class VideoGameController {
    private Level[] levels;
    private Player[] players;
    private Treasure[] treasures;
    private Enemy[] enemies;
    private double[] screenResolution;
    private String[] resNames;
    private double[] resX;
    private double[] resY;


    public VideoGameController() {
        this.levels = new Level[10];
        this.players = new Player[20];
        this.treasures = new Treasure[50];
        this.enemies = new Enemy[25];
        this.screenResolution = new double[2];
        this.resNames = new String[]{"SD", "QHD", "HD", "FHD", "QHD", "UHD", "UHD 8K"};
        this.resX = new double[]{640, 960, 1280, 1920, 2560, 3840, 7680};
        this.resY = new double[]{480, 540, 720, 1080, 1440, 2160, 4320};
    }

    public String levelsToString() {
        String levelList = "";
        for (int i = 0; i < levels.length; i++) {
            if (levels[i] != null) {
                levelList += levels[i].getNumber() + ": " + levels[i].getPointsToPass() + "\n";
            }
        }
        return levelList;
    }

    public String playersToString() {
        String playerList = "";
        for (int i = 0; i < players.length; i++) {
            if (players[i] != null) {
                playerList += (i + 1) + ". " + players[i].getName() + " (" + players[i].getNickname() + ")" + "\n" +
                        "   Points: " + players[i].getPoints() + "\n" +
                        "   Level: " + players[i].getLevel().getNumber() + "\n";
            }
        }
        return playerList;
    }

    public String enemiesToString() {
        String enemyList = "";
        for (int i = 0; i < enemies.length; i++) {
            if (enemies[i] != null) {
                enemyList += (i + 1) + ": " + enemies[i].getName() + ". \n" +
                        "   Points if player wins: " + enemies[i].getPointsPlayerWins() + "\n" +
                        "   Points if player loses: " + enemies[i].getPointsPlayerLoses() + "\n" +
                        "   Position: " + enemies[i].getPosition() + "\n" +
                        "   Type: " + enemies[i].getType() + "\n";
            }
        }
        return enemyList;
    }

    public String treasuresToString() {
        String treasureList = "";
        for (int i = 0; i < treasures.length; i++) {
            if (treasures[i] != null) {
                treasureList += (i + 1) + ": " + treasures[i].getName() + "\n" +
                        "Points: " + treasures[i].getPoints() + "\n";
                        //"Position: " + treasures[i].getPosition() + "\n";
            }
        }
        return treasureList;
    }

    public String resToString() {
        String resList = "";
        for (int i = 0; i < resNames.length; i++) {
            resList += (i + 1) + ". " + resNames[i] + ": " + resX[i] + "x" + resY[i] + "\n";
        }
        return resList;
    }

    public String enemyTypeToString() {
        String typesList = "";
        EnemyType type[] = EnemyType.values();
        for (int i = 0; i < type.length; i++) {
            typesList += (i + 1) + ": " + type[i] + "\n";
        }
        return typesList;
    }

    public EnemyType getEnemyType(int typeNumber) {
        EnemyType type[] = EnemyType.values();
        return type[typeNumber - 1];
    }


    public Level[] getLevels() {
        return levels;
    }

    public Player[] getPlayers() {
        return players;
    }

    public Treasure[] getTreasures() {
        return treasures;
    }

    public Enemy[] getEnemies() {
        return enemies;
    }

    public double[] getScreenResolution() {
        return screenResolution;
    }

    public void setScreenResolution(double resX, double resY) {
        this.screenResolution[0] = resX;
        this.screenResolution[1] = resY;
    }

    public double[] getResX() {
        return resX;
    }

    public double[] getResY() {
        return resY;
    }


    /**
     * Description: Creates a player and stores it in the players array
     *
     * @param name     String
     * @param nickname String
     * @return boolean
     * pre: The players array is not full and the player's nickname does not exist
     * pos: Stores the player in the players array
     */
    public boolean createPlayer(String name, String nickname) {
        Player newPlayer = new Player(name, nickname, levels[0]);
        return addPlayer(newPlayer);
    }

    /**
     * Description: Creates a level and stores it in the levels array
     *
     * @param number int
     * @param points double
     *               pos: Stores the level in the levels array
     */
    public void createLevel(int number, double points) {
        Level newLevel = new Level(number, points);
        addLevel(newLevel);

    }


    /**
     * Description: Creates an enemy and stores it in the enemies array
     *
     * @param name              String
     * @param typeNumber        int
     * @param pointsPlayerWins  double
     * @param pointsPlayerLoses double
     * @param positionX         double
     * @param positionY         double
     * @return boolean
     * pos: Stores an enemy in the enemies array
     */
    public boolean createEnemy(String name, int typeNumber, double pointsPlayerWins, double pointsPlayerLoses, double positionX, double positionY) {
        Enemy newEnemy = new Enemy(name, typeNumber, pointsPlayerWins, pointsPlayerLoses, positionX, positionY);
        return addEnemy(newEnemy);

    }

    /**
     * Description: Creates a treasure and stores it in the treasures array
     *
     * @param name   String
     * @param url    String
     * @param points double
     * @return boolean
     * pos: Stores a treasure in the treasures array
     */
    public boolean createTreasure(String name, String url, double points) {
        Treasure newTreasure = new Treasure(name, url, points);
        return addTreasure(newTreasure);
    }

    /**
     * Description: Adds a player to the players array
     *
     * @param newPlayer Player
     * @return boolean
     * pre: The array is not full and the new player's nickname does not exist
     * pos: Adds a player to the players array
     */
    public boolean addPlayer(Player newPlayer) {
        for (int i = 0; i < players.length; i++) {
            if (players[i] == null) {
                players[i] = newPlayer;
                return true;
            }
        }
        return false;
    }

    /**
     * Description: Adds a level to an available position in the levels array
     *
     * @param newLevel Level
     * @return boolean
     * pre: There is an available position in the levels array
     * pos: Stores a level in the levels array
     */
    public boolean addLevel(Level newLevel) {
        for (int i = 0; i < levels.length; i++) {
            if (levels[i] == null) {
                levels[i] = newLevel;
                return true;
            }
        }
        return false;
    }

    /**
     * Description: Adds an enemy to an available position in the enemies array
     *
     * @param newEnemy Enemy
     * @return boolean
     * pre: There is an available position in the enemies array
     * pos: Stores an enemy in the enemies array
     */
    public boolean addEnemy(Enemy newEnemy) {
        for (int i = 0; i < enemies.length; i++) {
            if (enemies[i] == null) {
                enemies[i] = newEnemy;
                return true;
            }
        }
        return false;
    }


    /**
     * Description: Adds a treasure to an available position in the treasures array
     *
     * @param newTreasure Treasure
     * @return boolean
     * pre: There is an available position in the treasures array
     * pos: Stores a treasure in the treasures array
     */
    public boolean addTreasure(Treasure newTreasure) {
        for (int i = 0; i < treasures.length; i++) {
            if (treasures[i] == null) {
                treasures[i] = newTreasure;
                return true;
            }
        }
        return false;
    }


    /**
     * Description: Adds an enemy to a level's enemies array
     *
     * @param levelNumber int
     * @param enemyNumber int
     * @return boolean
     * pre: The enemy is not already inside the level's enemies array
     * pos: Stores an enemy in the level's enemies array
     */
    public boolean addEnemyToLevel(int levelNumber, int enemyNumber) {
        Enemy enemy = enemies[enemyNumber - 1];
        Level level = levels[levelNumber - 1];

        if (level.enemyInside(enemy)) {
            return false;
        }
        return levels[levelNumber - 1].addEnemy(enemy);
    }

    /**
     * Description: Creates and adds a determined amount of a type of treasure to the level's treasures array
     * @param levelNumber    int
     * @param treasureNumber int
     * @param treasureAmount int
     * @return boolean
     * pre: The level's treasure array has capacity to store the amount of treasures indicated by the user
     * pos: Stores a determined amount of a type of treasure in the level's treasures array
     */
    //PREGUNTAR
    public boolean addTreasureToLevel(int levelNumber, int treasureNumber, int treasureAmount) {
        Treasure treasureShape = treasures[treasureNumber - 1];
        Level level = levels[levelNumber - 1];
        boolean success = false;

        for (int i = 0; i < treasureAmount; i++){
            double positionX = Math.ceil((Math.random() + 1) * screenResolution[0]);
            double positionY = Math.ceil((Math.random() + 1) * screenResolution[1]);

            double[] position = new double[]{positionX, positionY};

            Treasure newTreasure = new Treasure(treasureShape.getName(), treasureShape.getUrl(), treasureShape.getPoints());
            newTreasure.setPosition(position);

            if (treasuresHasCapacity(treasureAmount, level)) {
                levels[levelNumber - 1].addTreasure(newTreasure);
                System.out.println("Añadiendo tesoro");
                success = true;
            }
        }

        return success;
    }

    /**
     * Description: Checks if the amount of available spots in the treasures array has enough space to store a determined amount of new treasures
     *
     * @param treasureAmount int
     * @return boolean
     */
    public boolean treasuresHasCapacity(int treasureAmount, Level level) {
        int availableSpots = 0;
        for (int i = 0; i < level.getTreasures().length; i++) {
            if (level.getTreasures()[i] == null) {
                availableSpots++;
            }
        }
        if (availableSpots >= treasureAmount) {
            return true;
        }
        return false;
    }

    /**
     * Description: Updates the difficulty of a level
     *
     * @param levelNumber int
     * @return String
     */
    public String updateLevelDifficulty(int levelNumber) {
        return levels[levelNumber - 1].checkDifficulty();
    }


    /**
     * Description: Checks if the players array is full
     *
     * @return boolean
     */
    public boolean playersFull() {
        for (int i = 0; i < players.length; i++) {
            if (players[i] == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Description: Checks if the enemies array is full
     *
     * @return boolean
     */
    public boolean enemiesFull() {
        for (int i = 0; i < enemies.length; i++) {
            if (enemies[i] == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if the treasures array is full
     *
     * @return boolean
     */
    public boolean treasuresFull() {
        for (int i = 0; i < treasures.length; i++) {
            if (treasures[i] == null) {
                return false;
            }
        }
        return true;
    }


    /**
     * Description: Checks if a nickname exists
     *
     * @param nickname String
     * @return boolean
     */
    public boolean nicknameExists(String nickname) {
        for (int i = 0; i < players.length; i++) {
            if (players[i] != null) {
                if (players[i].getNickname().equals(nickname)) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * Description: Changes the points of a player
     *
     * @param numberPlayer int
     * @param newPoints    double
     * @return boolean
     * pre: The player exists
     * pos: Updates the points of the player
     */
    public boolean updatePlayerPoints(int numberPlayer, double newPoints) {
        players[numberPlayer - 1].setPoints(newPoints);
        return true;

    }


    /**
     * Description: Updates the player's level according to the player's points
     *
     * @param numberPlayer int
     * @return boolean
     * pos: Changes the player's level if the points are sufficient
     */
    public boolean updatePlayerLevel(int numberPlayer) {
        int currentLevel = players[numberPlayer - 1].getLevel().getNumber();
        while (players[numberPlayer - 1].getPoints() >= levels[players[numberPlayer - 1].getLevel().getNumber()].getPointsToPass()) {
            players[numberPlayer - 1].setLevel(levels[players[numberPlayer - 1].getLevel().getNumber() + 1]);
        }

        if (currentLevel != players[numberPlayer - 1].getLevel().getNumber()) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * Description: Calculate how many points a player is missing to level up
     *
     * @param numberPlayer int
     * @return boolean
     */
    public String calculateMissingPoints(int numberPlayer) {
        double missingPoints = players[numberPlayer - 1].getLevel().getPointsToPass() - players[numberPlayer - 1].getPoints();
        return "The player is missing " + missingPoints + " to pass to level " + (players[numberPlayer - 1].getLevel().getNumber() + 1);
    }

    /**
     * Creates a list with the enemies and treasures from a level chosen level
     * @param levelNumber int. Index + 1 of chosen level
     * @return String. List with the enemies and treasures
     */
    public String treasuresEnemiesReport(int levelNumber){

        String treasuresList = "";
        String enemiesList = "";

        Level chosenLevel = levels[levelNumber - 1];

        for (int i = 0; i < chosenLevel.getTreasures().length; i++){
            if (chosenLevel.getTreasures()[i] != null){
                treasuresList += chosenLevel.getTreasures()[i].getName() + ", ";
            }
        }

        for (int j = 0; j < chosenLevel.getEnemies().length; j++){
            if (chosenLevel.getEnemies()[j] != null){
                enemiesList += chosenLevel.getEnemies()[j].getName() + ", ";
            }
        }

        return "Level " + chosenLevel.getNumber() + " : \n" +
                "   Treasures list: \n" +
                "       " + treasuresList + "\n" +
                "   Enemies list:  \n" +
                "       " + enemiesList + "\n";

    }


    /**
     * Calculates the number of a certain treasure type in the whole game
     * @param treasureNumber int. Index + 1 of chosen treasure in the treasures array
     * @return int. Treasure type count
     */
    public int treasureTypeReport(int treasureNumber) {
        Treasure chosenTreasure = treasures[treasureNumber - 1];
        int treasureCount = 0;

        for (int i = 0; i < levels.length; i++) {
            for (int j = 0; j < levels[i].getTreasures().length; j++) {
                if (levels[i].getTreasures()[j] != null) {
                    if (levels[i].getTreasures()[j].getName().equals(chosenTreasure.getName())) {
                        treasureCount++;
                    }
                }
            }
        }

        return treasureCount;

    }

    /**
     * Calculates the number of a certain enemy type in the whole game
     * @param enemyNumber int. Index + 1 of chosen treasure in the treasures array
     * @return int. Treasure type count
     */
    public int enemyTypeReport(int enemyNumber) {
        Enemy chosenEnemy = enemies[enemyNumber - 1];
        int enemyCount = 0;

        for (int i = 0; i < levels.length; i++) {
            for (int j = 0; j < levels[i].getEnemies().length; j++) {
                if (levels[i].getEnemies()[j] != null) {
                    if (levels[i].getEnemies()[j].getType().equals(chosenEnemy.getType())) {
                        enemyCount++;
                    }
                }
            }
        }

        return enemyCount;
    }


    /**
     * Finds the enemy that makes a player lose the most points when the player loses
     * @return String. Sentence with the most powerful enemy and the level where it is
     */
    public String findMostPowerfulEnemy() {

        Enemy levelPowerfulEnemy = levels[0].getEnemies()[0];
        Enemy mostPowerfulEnemy = levels[0].getEnemies()[0];
        double levelMaxPoints = -1;
        double gameMaxPoints = -1;
        double levelNumber = 0;


        for (int i = 0; i < levels.length; i++) {
            for (int j = 0; j < levels[i].getEnemies().length; j++) {
                if (levels[i].getEnemies()[j] != null) {
                    if (levels[i].getEnemies()[j].getPointsPlayerLoses() > levelMaxPoints) {
                        levelPowerfulEnemy = levels[i].getEnemies()[j];
                        levelMaxPoints = levels[i].getEnemies()[j].getPointsPlayerLoses();
                    }
                }
            }

            if (levelPowerfulEnemy.getPointsPlayerLoses() > gameMaxPoints) {
                mostPowerfulEnemy = levelPowerfulEnemy;
                gameMaxPoints = levelPowerfulEnemy.getPointsPlayerLoses();
                levelNumber = i + 1;
            }
        }
        return "The most powerful enemy in the game is " + mostPowerfulEnemy.getName() + " and you can find it in level " + levelNumber;
    }

    /**
     * Description: Calculates the number of repetitions of a treasure type in the whole game
     * @param treasuresNum int. Index number of the treasure type that is going to be analyzed by its repetitions
     * @return int number of repetitions of a treasure type in the whole game
     */
    public int calculateAmountOfTreasureInGame(int treasuresNum){
        int levelCount = 0;

        for (int j = 0; j < levels.length; j++) {
            if (levels[j] != null) {
                for (int d = 0; d < levels[j].getTreasures().length; d++) {
                    if (levels[j].getTreasures()[d] != null) {
                        if (levels[j].getTreasures()[d].getName().equals(treasures[treasuresNum].getName())) {
                            levelCount++;
                        }
                    }
                }
            }
        }
        return levelCount;
    }


    /**
     * Description: Compares the number of treasures of every kind repeat themselves in the whole game and stores the most repeated ones in an array
     * @return Treasure[]. Most repeated treasures in the whole game
     */
    public Treasure[] findMostRepeatedTreasure(){

        int treasuresNum = 0;
        int gameCount;

        int gameMaxCount = -1;

        for (int k = 0; k < treasures.length; k++) {
            if (treasures[k] != null) {
                treasuresNum++;
            }
        }

        Treasure[] mostRepeatedTreasures = new Treasure[treasuresNum];
        int emptyTreasureIndex = 0;
        int[] treasureCounts = new int[treasuresNum];

        for (int i = 0; i < treasuresNum; i++){
            gameCount = calculateAmountOfTreasureInGame(i);
            treasureCounts[i] = gameCount;
            if (gameCount > gameMaxCount){
                gameMaxCount = gameCount;
            }
        }

        for (int j = 0; j < treasuresNum; j++){
            if (treasureCounts[j] == gameMaxCount){
                mostRepeatedTreasures[emptyTreasureIndex] = treasures[j];
                emptyTreasureIndex++;
            }
        }
        return mostRepeatedTreasures;
    }


    /**
     * Description: Shows the most repeated treasure in the whole game, or a list of the most repeated treasures, in case the treasures repetitions are tied
     * @param mostRepeatedTreasures Treasure[]
     * @return String
     */
    public String showMostRepeatedTreasures(Treasure[] mostRepeatedTreasures){
        String listMostRepeatedTreasures = "";
        for (int i = 0; i < mostRepeatedTreasures.length; i++){
            if (mostRepeatedTreasures[i] != null){
                listMostRepeatedTreasures += "\n" + "   " + mostRepeatedTreasures[i].getName();
            }
        }

        if (listMostRepeatedTreasures.equals("")){
            return "There are no treasures registered to show which one's the most repeated";
        }

        return "The treasure(s) with the most repetitions in the whole game are : " + "\n" +
                listMostRepeatedTreasures;
    }

    public Player[] sortPlayersArray(){
        Player[] sortedPlayers = players;

        boolean changed = true;
        int j = 0;
        Player temp;

        while (changed){
            changed = false;
            j++;
            for (int i = 0; i < sortedPlayers.length - j; i++){
                if (sortedPlayers[i] != null && sortedPlayers[i + 1] != null){
                    if (sortedPlayers[i].getPoints() < sortedPlayers[i + 1].getPoints()){
                        temp = sortedPlayers[i];
                        sortedPlayers[i] = sortedPlayers[i +1];
                        sortedPlayers[i + 1] = temp;
                        changed = true;

                    }
                }
            }
        }
        return sortedPlayers;
    }

    public String showTop5(Player[] sortedPlayers){
        String top5List = "";
        for (int i = 0; i < 5; i++){
            top5List += (i + 1) + ": " + sortedPlayers[i].getNickname() + "(" + sortedPlayers[i].getPoints() + ")\n";
        }
        return top5List;
    }


    /**
     * Description: Runs through the enemies array and calculates the total number of consonants in the enemies' names
     * @return int. Total number of consonants in the enemies' names
     */
    public int calculateConsonantsInEnemies() {
        int totalConsonants = 0;
        for (int i = 0; i < enemies.length; i++) {
            if (enemies[i] != null) {
                String name = enemies[i].getName();
                totalConsonants += calculateConsonantsInName(name);
            }
        }
        return totalConsonants;
    }


    /**
     * Description: Goes through a String and counts the number of consonants present in it
     * @param name String
     * @return int. Number of consonants in the name
     */
    public int calculateConsonantsInName(String name) {
        int consonants = 0;
        for (int j = 0; j < name.length(); j++) {
            if (name.charAt(j) != 'a' && name.charAt(j) != 'e' && name.charAt(j) != 'i' && name.charAt(j) != 'o' && name.charAt(j) != 'u' && name.charAt(j) >= 'a' && name.charAt(j) <= 'z') {
                consonants++;
            }
        }
        return consonants;
    }


}
