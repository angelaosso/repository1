package ui;
import java.util.Scanner;

import model.EnemyType;
import model.VideoGameController;
import java.util.Random;
import java.lang.Math;

public class VideoGameManager {

    public static boolean exitApp = false;
    static Scanner reader = new Scanner(System.in);
    public static VideoGameController gameController = new VideoGameController();
    public static Random rand = new Random();
    private static Object EnemyType;

    public static void main(String[] args){

        menu();

    }

    /**
     * Shows the main menu of the app and the menus of each of the game's elements
     */
    public static void menu(){
        System.out.println("Choose your screen resolution: ");
        System.out.println(gameController.resToString());
        int userChoiceRes = reader.nextInt();
        reader.nextLine();
        double resX = gameController.getResX()[userChoiceRes - 1];
        double resY = gameController.getResY()[userChoiceRes - 1];
        gameController.setScreenResolution(resX, resY);

        createLevels();
        System.out.println(gameController.levelsToString());

        while (!exitApp){
            createLevels();
            System.out.println("Main menu");
            System.out.println("1: Player\n" +
                    "2: Level\n" +
                    "3: Enemy\n" +
                    "4: Treasure\n" +
                    "5: Reports\n" +
                    "6: Exit app\n");
            String userMenuChoice = reader.nextLine();

            switch (userMenuChoice){
                case "1":
                    switch (playerMenu()){
                        case "1":
                            if (addPlayer()){
                                System.out.println("Player successfully created");
                            }
                            else{
                                System.out.println("The maximum player amount (20) has been reached. You cannot create more");
                            }
                            break;
                        case "2":
                            if (updatePlayerPoints()){
                                System.out.println("Update successful");
                            }
                            else {
                                System.out.println("Update unsuccessful");
                            }
                            break;
                        case "3":
                            if (updatePlayerLevel()){
                                System.out.println("Update successful");
                            }
                            else {
                                System.out.println("Update unsuccessful");
                            }
                            break;
                        case "4":
                            if (gameController.playersToString().equals("")){
                                System.out.println("There are no created players");
                            }
                            else {
                                System.out.println(gameController.playersToString());
                            }
                            break;
                    }
                    break;
                case "2":
                    switch (levelMenu()){
                        case "1":
                            if (gameController.enemiesToString().equals("")){
                            System.out.println("There are no enemies created. Go back to the menu to create one");
                            }
                            else{
                                if (addEnemyToLevel()){
                                    System.out.println("Enemy added successfully");
                                }
                                else {
                                    System.out.println("The enemy is already in this level. You cannot add it again");
                                }
                            }

                            break;
                        case "2":
                            if (gameController.treasuresToString().equals("")){
                                System.out.println("There are no treasures created. Go back to the menu to create one");
                            }
                            else {
                                if (addTreasureToLevel()){
                                    System.out.println("Treasure(s) successfully added");
                                }
                                else {
                                    System.out.println("The level's treasures are full or the capacity is not enough for the amount of treasures that you want to add");
                                }
                            }
                            break;
                    }
                    break;
                case "3":
                    switch (enemyMenu()){
                        case "1":
                            if (addEnemy()){
                                System.out.println("Enemy successfully created");
                            }
                            else {
                                System.out.println("The maximum enemy amount (25) has been reached. You cannot create more");
                            }
                            break;
                        case "2":
                            if (gameController.enemiesToString().equals("")){
                                System.out.println("There are no enemies created");
                            }
                            else {
                                System.out.println(gameController.enemiesToString());
                            }
                            break;

                    }
                    break;
                case "4":
                    switch (treasureMenu()){
                        case "1":
                            if (addTreasure()){
                                System.out.println("Treasure successfully created");
                            }
                            else {
                                System.out.println("The maximum enemy amount (50) has been reached. You cannot create more");
                            }
                            break;
                        case "2":
                            if (gameController.treasuresToString().equals("")){
                                System.out.println("There are no created treasures");
                            }
                            else {
                                System.out.println(gameController.treasuresToString());
                            }
                            break;

                    }
                    break;
                case "5":

                    switch (reportMenu()){
                        case "1":
                            System.out.println(reportTreasuresEnemies());
                            break;
                        case "2":
                            System.out.println(treasureTypeReport());
                            break;
                        case "3":
                            System.out.println(enemyTypeReport());
                            break;
                        case "4":
                            System.out.println(reportMostRepeatedTreasure());
                            break;
                        case "5":
                            System.out.println(reportPowerfulEnemy());
                            break;
                        case "6":
                            System.out.println(reportEnemiesConsonants());
                            break;
                        case "7":
                            System.out.println(top5Report());

                    }
                    break;

                case "6":
                    System.out.println("Signed out :)");
                    exitApp = true;
            }
        }

    }

    /**
     * Description: Shows the available options about the players
     * @return String. List of options about players
     */
    public static String playerMenu(){
        System.out.println("""
                1: Add player
                2: Change score of a player
                3: Level up a player
                4: List of players
                """);
        return reader.nextLine();
    }

    /**
     * Description: Shows the available options about the levels
     * @return String. List of options about levels
     */
    public static String levelMenu(){
        System.out.println("""
                1: Add enemy to level
                2: Add treasure to level
                """);
        return reader.nextLine();
    }

    /**
     * Description: Shows the available options about the enemies
     * @return String. List of options about enemies
     */
    public static String enemyMenu(){
        System.out.println("""
                1: Create enemy
                2: List of enemies
                """);
        return reader.nextLine();
    }

    /**
     * Description: Shows the available options about the treasures
     * @return String. List of options about treasures
     */
    public static String treasureMenu(){
        System.out.println("""
                1: Create treasure
                2: List of treasures
                """);
        return reader.nextLine();
    }

    public static String reportMenu(){
        System.out.println("""
                1: List of treasures and enemies of a level
                2: Amount of a treasure type in all levels
                3: Amount of an enemy type in all levels
                4: Most repeated treasure in all levels
                5: Most powerful enemy
                6: Amount of consonants in the enemies' names
                7: Players Top 5
                """);
        return reader.nextLine();
    }


    /**
     * Creates 10 levels with increasing random points
     */
    public static void createLevels(){
        double points;
        for (int i = 0; i <= 9; i++){
            if (i == 0){
                gameController.createLevel(1, 20);
            }
            else {
                points = (rand.nextDouble() + 1) * 10 * (i + 1) + gameController.getLevels()[i - 1].getPointsToPass();
                gameController.createLevel(i + 1, Math.ceil(points));
            }
        }
    }

    /**
     * Description: Asks for the necessary to create a player, checks if the nickname already exists, checks if the maximum capacity of players hasn't been reached and created a player.
     * @return boolean
     */
    public static boolean addPlayer(){
        if (gameController.playersFull()){
            return false;
        }
        System.out.println("Add new player\n");

        System.out.println("Name: ");
        String name = reader.nextLine();

        System.out.println("Nickname: ");
        String nickname = reader.nextLine();
        while (gameController.nicknameExists(nickname)){
            System.out.println("The nickname " + nickname + "already exists. Type a new one:");
            nickname = reader.nextLine();
        }

        if (gameController.createPlayer(name, nickname)){
            return true;
        }
        return false;
    }

    /**
     * Description: Asks for the necessary to create an enemy, checks if the maximum capacity of enemies hasn't been reached and creates an enemy with a randdom position.
     * @return boolean
     */
    public static boolean addEnemy(){
        if (gameController.enemiesFull()){
            return false;
        }
        else {
            System.out.println("Add new enemy\n");

            System.out.println("Name: ");
            String name = reader.nextLine();

            System.out.println("Choose the enemy Type: ");
            System.out.println(gameController.enemyTypeToString());
            int userChoiceType = reader.nextInt();

            System.out.println("Type the amount of points the player will gain if the enemy is beaten: ");
            double pointsPlayerWins = reader.nextInt();

            System.out.println("Type the amount of points the player will lose if the enemy wins: ");
            double pointsPlayerLoses = reader.nextInt();

            double positionX =  Math.ceil((Math.random()) * gameController.getScreenResolution()[0]);
            double positionY =  Math.ceil((Math.random()) * gameController.getScreenResolution()[1]);

            return gameController.createEnemy(name, userChoiceType - 1, pointsPlayerWins, pointsPlayerLoses, positionX, positionY);
        }
    }

    /**
     * Description: Asks for the necessary to create a treasure, checks if the maximum capacity of treasures hasn't been reached and creates a treasure with a random position.
     * @return boolean
     */
    public static boolean addTreasure(){
        if (gameController.treasuresFull()){
            return false;
        }

        System.out.println("Add new treasure\n");

        System.out.println("Name: ");
        String name = reader.nextLine();

        System.out.println("URL: ");
        String url = reader.nextLine();

        System.out.println("Points: ");
        double points = reader.nextInt();
        reader.nextLine();

        return gameController.createTreasure(name, url, points);

    }


    /**
     * Description: Asks what level the user wants to add an enemy to. Checks if the enemy is already in this level, and if not, add the enemy
     * @return boolean
     * pos: Adds enemy to level
     */
    public static boolean addEnemyToLevel(){
        System.out.println("Add enemy to a level \n");
        System.out.println("Choose the level: \n");

        System.out.println(gameController.levelsToString());
        int levelNumber = reader.nextInt();

        if (gameController.getLevels()[levelNumber - 1].enemiesFull()){
            System.out.println("The maximum amount of enemies (25) has been added to this level");
            return false;
        }
        else {
            System.out.println("Choose the enemy that you want to add");

            System.out.println(gameController.enemiesToString());
            int enemyNumber = reader.nextInt();
            reader.nextLine();

            if (gameController.addEnemyToLevel(levelNumber, enemyNumber)){
                System.out.println(gameController.updateLevelDifficulty(levelNumber));
                return true;
            }
            else {
                return false;
            }

        }
    }

    /**
     * Description: Asks what level the user wants to add a treasure to and the amount of treasures of the selected type. Checks is the level has capacity to store them, and if it does, stores the treasures in the level
     * @return boolean
     */
    public static boolean addTreasureToLevel(){
        System.out.println("Add treasure to a level \n");
        System.out.println("Choose the level: \n");

        System.out.println(gameController.levelsToString());
        int levelNumber = reader.nextInt();

        if (gameController.getLevels()[levelNumber - 1].treasuresFull()){
            System.out.println("The maximum amount of treasures (50) has been added to this level. You cannot add more");
            return false;
        }
        else {
            System.out.println("Choose the treasure that you want to add");

            System.out.println(gameController.treasuresToString());
            int treasureNumber = reader.nextInt();

            System.out.println("Type the number of treasures that you want to add");
            int treasureAmount = reader.nextInt();

            reader.nextLine();

            if (gameController.addTreasureToLevel(levelNumber, treasureNumber, treasureAmount)){
                System.out.println(gameController.updateLevelDifficulty(levelNumber));
                return true;
            }
            else {
                return false;
            }

        }
    }

    /**
     * Description: Asks the user which player's points are going to be updated and what the new points are. Changes the player's points
     * @return boolean
     */
    public static boolean updatePlayerPoints(){
        System.out.println("Update player points");
        System.out.println("Choose the player: ");
        System.out.println(gameController.playersToString());
        int playerNumber = reader.nextInt();

        System.out.println("Current points: " + gameController.getPlayers()[playerNumber - 1].getPoints());

        System.out.println("Type the new amount of points of the player: \n");
        double newPoints = reader.nextDouble();

        if (gameController.updatePlayerPoints(playerNumber, newPoints)){
            return true;
        }
        return false;
    }

    /**
     * Description: Asks for the player whose level wants to be updated. Checks the player's score and if it corresponds to a higher level. If it does, updates the level, if it doesn't, it shows the amount of points necessary to pass to the next level
     * @return boolean
     */
    public static boolean updatePlayerLevel(){
        System.out.println("Update player level \n");
        System.out.println("Choose the player: \n");
        System.out.println(gameController.playersToString());
        int playerNumber = reader.nextInt();

        System.out.println("Current level: " + gameController.getPlayers()[playerNumber - 1].getLevel().getNumber());

        if (gameController.updatePlayerLevel(playerNumber)){
            System.out.println("New level: " + gameController.getPlayers()[playerNumber - 1].getLevel().getNumber());
            reader.nextLine();
            return true;
        }
        else {
            System.out.println(gameController.calculateMissingPoints(playerNumber));
            reader.nextLine();
            return false;
        }
    }


    public static String treasureTypeReport(){
        System.out.println("Number of a treasure in the whole game\n");
        System.out.println("Choose the treasure type that you want to investigate\n");

        System.out.println(gameController.treasuresToString());

        int treasureNumber = reader.nextInt();

        int treasureCount = gameController.treasureTypeReport(treasureNumber);

        if (treasureCount == 0){
            return "There are no " + gameController.getTreasures()[treasureNumber - 1].getName() + " in the game\n";
        }
        else {
            return  "There are " + treasureCount + gameController.getTreasures()[treasureNumber - 1].getName() + "(s) in the game";
        }
    }

    public static String enemyTypeReport(){
        System.out.println("Number of an enemy of a certain type in the whole game\n");
        System.out.println("Choose the enemy type that you want to investigate\n");

        System.out.println(gameController.enemyTypeToString());

        int enemyNumber = reader.nextInt();

        int enemyCount = gameController.enemyTypeReport(enemyNumber);

        if (enemyCount == 0){
            return "There are no " + gameController.getEnemyType(enemyNumber) + " in the game\n";
        }
        else {
            return  "There are " + enemyCount + gameController.getEnemies()[enemyNumber - 1].getType() + "(s) in the game";
        }
    }

    public static String reportPowerfulEnemy(){
        return  gameController.findMostPowerfulEnemy();
    }

    public static String reportEnemiesConsonants(){
        int consonantsNumber = gameController.calculateConsonantsInEnemies();
        return "There are " + consonantsNumber + " consonants in the enemies' names";
    }

    public static String reportTreasuresEnemies(){
        System.out.println("Treasures and enemies of a level");

        System.out.println("Select the level");
        System.out.println(gameController.levelsToString());

        int chosenLevel = reader.nextInt();

        return gameController.treasuresEnemiesReport(chosenLevel);
        }


    public static String reportMostRepeatedTreasure(){
        return gameController.showMostRepeatedTreasures(gameController.findMostRepeatedTreasure());
    }

    public static String top5Report (){
        return gameController.showTop5(gameController.sortPlayersArray());
    }

}


