package ui;

import model.Category;
import model.Genre;
import model.NeoTunesController;

import java.util.Scanner;

public class NeoTunesManager {

    private Genre songGenres[] = Genre.values();
    private Category podcastCategories[] = Category.values();

    private Scanner reader;
    private NeoTunesController controller;

    public static void main(String[] args){

        System.out.println("Welcome to NeoTunes");
        NeoTunesManager manager = new NeoTunesManager();

        int option = 0;

        do {
            option = manager.showMenu();
            manager.executeApp(option);

        } while (option != 0);

    }

    public NeoTunesManager() {

        reader = new Scanner(System.in);
        controller = new NeoTunesController();

    }

    /**
     * Shows main menu
     * @return String
     */
    public int showMenu(){
        int menuChoice = 0;
        System.out.println("""
                Pick an option:\s
                1. Register user\s
                2. Register audio\s
                3. Create playlist\s
                4. Edit playlist\s
                5. Share playlist\s
                6. Buy song\s
                7. Play audio\s
                8. Reports\s
                0. Close app
                """);
        menuChoice = reader.nextInt();
        reader.nextLine();
        return menuChoice;

    }

    /**
     * Executes the methods according to the user's menu choice
     * @param menuChoice int
     */
    public void executeApp(int menuChoice){

        switch (menuChoice){
            case 0:
                System.out.println("Signed out :)");
                break;
            case 1:
                System.out.println(registerUser());
                break;
            case 2:
                System.out.println(registerAudio());
                break;
            case 3:
                System.out.println(registerPlaylist());
                break;
            case 4:
                System.out.println(editPlaylist());
                break;
            case 5:
                System.out.println(sharePlaylist());
                break;
            case 6:
                System.out.println(buySong());
                break;
            case 7:
                System.out.println(playAudio());
                break;
            case 8:
                executeReport(reportMenu());
                break;
            default:
                System.out.println("Choose a valid option");
        }

    }

    /**
     * Asks the user the necessary information to create an instance of User and specifically, Consumer or Producer. Creates the object and shows a success or failure message
     * @return String
     */
    public String registerUser(){

        System.out.println("Register a user");

        boolean success;

        System.out.println("""
                Choose the type of user that you want to register:\s
                1. Consumer\s
                2. Producer""");
        int userType = reader.nextInt();
        reader.nextLine();


        System.out.println("Type the user's nickname");
        String nickname = reader.nextLine();

        System.out.println("Type the user's ID");
        String id = reader.nextLine();

        while (controller.userIdExists(id)){
            System.out.println("The ID already exists. Type a valid one");
            System.out.println("Type the user's ID");
            id = reader.nextLine();
        }


        if (userType == 1){

            System.out.println("""
                    Choose the type of consumer:\s
                    1. Standard
                    2. Premium
                    """);

            int consumerType = reader.nextInt();
            reader.nextLine();

            success = controller.createConsumer(consumerType, nickname, id);



        }
        else {

            System.out.println("Type the producer's name: \n");
            String name = reader.nextLine();

            System.out.println("Type the producer's URL");
            String url = reader.nextLine();


            System.out.println("""
                    Choose the type of producer:\s
                    1. Artist\s
                    2. Content creator\s
                    """);

            int produceType = reader.nextInt();
            reader.nextLine();

            success = controller.createProducer(produceType, nickname, id, name, url);
        }

        if (success){
            return "User successfully created";
        }
        else {
            return "Error. User could not be created";
        }

    }

    /**
     * Asks the necessary information to create an instance of Audio, specifically a Song or Podcast, according, to the user's choice. Creates the object and shows a success or failure message
     * @return String
     */
    public String registerAudio(){
        System.out.println("Register an audio");

        System.out.println("Select the type of audio that you want to register: \n" +
                "1. Song\n" +
                "2. Podcast\n");

        int audioChoice = reader.nextInt();
        reader.nextLine();

        System.out.println("Type the name of the audio: \n");
        String name = reader.nextLine();

        System.out.println("Type the URL of the album or image: \n");
        String url = reader.nextLine();

        System.out.println("Type the duration: \n");
        double duration = reader.nextDouble();
        reader.nextLine();

        boolean success;

        if (audioChoice == 1){
            if (controller.userHasArtists()){
                System.out.println("Type the name of the song's album\n");
                String albumName = reader.nextLine();

                System.out.println("Type the value of the song in dollars\n");
                double songValue = reader.nextDouble();
                reader.nextLine();

                System.out.println("Choose the song genre\n");
                System.out.println(showSongGenre());
                int genreChoice = reader.nextInt();
                reader.nextLine();

                System.out.println("Choose the song's artist: \n");
                System.out.println(controller.showProducers(1));
                int artistChoice = reader.nextInt();
                reader.nextLine();

                success = controller.createAudio(name, url, duration, albumName, songValue, songGenres[genreChoice - 1], artistChoice);

            }
            else {
                return "There are no registered artists. Go back to the menu to create one";
            }

        }
        else {
            if (controller.userHasContCreators()){
                System.out.println("Type the podcast's description: \n");
                String description = reader.nextLine();

                System.out.println("Choose the podcast's category: \n");
                System.out.println(showPodcastCategory());
                int categoryChoice = reader.nextInt();
                reader.nextLine();

                System.out.println("Choose the podcast's content creator: \n");
                System.out.println(controller.showProducers(2));
                int contCreatorChoice = reader.nextInt();
                reader.nextLine();

                success = controller.createAudio(name, url, duration, description, podcastCategories[categoryChoice - 1], contCreatorChoice);
            }
            else {
                return "There are no registered content creators. Go back to the menu to create one";
            }
        }

        if (success){
            return  "Audio created successfully";
        }
        else {
            return "Error. Audio could not be created";
        }


    }

    /**
     * Asks the necessary information to create an instance of Playlist and shows a success or failure message
     * @return String
     */
    public String registerPlaylist(){
        boolean success;
        System.out.println("Create a playlist");
        System.out.println("Choose the consumer that wants to create a playlist: \n");
        System.out.println(controller.showConsumers());
        int consumerChoice = reader.nextInt();
        reader.nextLine();

        if (controller.standardReachedMaxPlaylist(consumerChoice)){
            return "The chosen consumer is standard type and has reached the maximum number of created playlists";
        }
        else {
            System.out.println("Type the name of the playlist: \n");
            String name = reader.nextLine();

            success = controller.createPlaylist(consumerChoice, name);

        }

        if (success){
            return "Playlist successfully created";
        }
        else {
            return "Error. Playlist could not be created";
        }
    }

    /**
     * Allows for a determined consumer's playlist to be edited, either adding a song or deleting it.
     * @return String
     */
    public String editPlaylist(){

        boolean success = true;

        System.out.println("Edit a playlist\n");
        System.out.println("Choose the user from which you want to edit a playlist\n");

        System.out.println(controller.showConsumers());
        int consumerChoice = reader.nextInt();
        reader.nextLine();

        if (controller.consumerHasPlaylists(consumerChoice)){
            System.out.println("Choose the playlist that you want to edit: \n");
            System.out.println(controller.showConsumersPlaylists(consumerChoice));
            int playlistChoice = reader.nextInt();
            reader.nextLine();

            System.out.println("Choose the editing option: \n" +
                    "1. Add audio\n" +
                    "2. Delete audio\n");
            int editOption = reader.nextInt();
            reader.nextLine();

            if (editOption == 1){

                System.out.println("Choose the audio that you want to add to the playlist: \n");
                System.out.println(controller.showAudios(3));
                int audioChoice = reader.nextInt();
                reader.nextLine();

                success = controller.addAudioToPlaylist(consumerChoice, playlistChoice, audioChoice);
                return success ? "Audio successfully added" : "Audio could not be added";
            }
            else {
                System.out.println("Choose the audio that you want to delete from the playlist: \n");
                System.out.println(controller.showAudios(consumerChoice, playlistChoice));
                int audioChoice = reader.nextInt();
                reader.nextLine();

                success = controller.deleteAudioFromPlaylist(consumerChoice, playlistChoice, audioChoice);
                return success ? "Audio successfully deleted" : "Audio could not be deleted";
            }
        }
        return "The chosen user does not have any playlists";
    }

    public String sharePlaylist(){
        System.out.println("Share a playlist\n");
        System.out.println("Choose the consumer that wants to share a playlist: \n");
        System.out.println(controller.showConsumers());
        int consumerIndex = reader.nextInt();
        reader.nextLine();

        System.out.println("Choose the playlist that the consumer wants to share: \n");
        System.out.println(controller.showConsumersPlaylists(consumerIndex));
        int playlistIndex = reader.nextInt();
        reader.nextLine();

        return controller.sharePlaylist(consumerIndex, playlistIndex);
    }

    /**
     *
     * @return
     */
    public String buySong(){
        System.out.println("Buy song");

        System.out.println("Choose the consumer that wants to buy a song: \n");
        System.out.println(controller.showConsumers());
        int consumerIndex = reader.nextInt();
        reader.nextLine();

        System.out.println("Choose the song that the consumer wants to buy: \n");
        System.out.println(controller.showAudios(1));
        int songIndex = reader.nextInt();
        reader.nextLine();

        if (controller.buySong(consumerIndex, songIndex)){
            return "Song successfully bought";
        }
        else {
            return "Error. Song could not be bough";
        }
    }

    public String playAudio(){
        System.out.println("Play audio");
        System.out.println("Choose the consumer that wants to play an audio");
        System.out.println(controller.showConsumers());
        int consumerIndex = reader.nextInt();
        reader.nextLine();

        System.out.println("Choose the type of audio\n" +
                "1. Songs \n" +
                "2. Podcasts \n");
        int audioChoice = reader.nextInt();
        reader.nextLine();

        System.out.println(controller.showAudios(audioChoice));
        int audioIndex = reader.nextInt();
        reader.nextLine();

        return controller.playAudio(consumerIndex, audioIndex);
    }

    /**
     * Shows the list of possible reports in the platform and asks the user to choose one of them
     * @return int
     */
    public int reportMenu(){

        System.out.println("""
                Choose the type of report:\s
                1. Total number of reproductions of each audio type\s
                2. Most listened song genre in the platform\s
                3. Most listened song genre by specific user\s
                4. Most listened podcast category in the platform\s
                5. Most listened podcast category by specific user\s
                6. Top 5 of artists and content creators in the platform\s
                7. Top 10 of songs and podcasts in the platform\s
                8. Number of songs sold for each genre\s
                9. Song with greatest number of sales in the platform""");

        int reportChoice = reader.nextInt();
        reader.nextLine();
        return reportChoice;
    }

    public void executeReport(int reportChoice){

        switch (reportChoice){
            case 1:
                System.out.println(controller.reportNumberOfReprodAudios());
                break;
            case 2:
                System.out.println(reportMostListenedSongGenre());
                break;
            case 3:
                System.out.println(reportMostListenedGenreUser());
                break;
            case 4:
                System.out.println(reportMostListenedPodcastCategory());
                break;
            case 5:
                System.out.println(reportMostListenedCategoryUser());
                break;
            case 6:
                System.out.println(controller.reportTop5Producers());
                break;
            case 7:
                System.out.println(controller.reportTop10Audios());
                break;
            case 8:
                System.out.println(controller.reportGenreSoldSongs());
                break;
            case 9:
                System.out.println(controller.reportMostSoldSong(controller.findMostSoldSong()));
                break;
            default:
                System.out.println("Choose a valid option");
                break;
        }

    }

    public String reportMostListenedGenreUser(){
        System.out.println("Most listened genre by a specific user\n");
        System.out.println("Choose the user that you want to analyze: \n");
        System.out.println(controller.showConsumers());
        int consumerIndex = reader.nextInt();
        reader.nextLine();

        return controller.reportMostListenedGenreUser(consumerIndex);
    }

    public String reportMostListenedCategoryUser(){
        System.out.println("Most listened category by a specific user\n");
        System.out.println("Choose the user that you want to analyze: \n");
        System.out.println(controller.showConsumers());
        int consumerIndex= reader.nextInt();
        reader.nextLine();

        return controller.reportMostListenedCategoryUser(consumerIndex);
    }

    public String reportMostListenedSongGenre(){
        return "The most listened song genre(s): \n" +
                controller.reportMostListenedGenreCategory(1);
    }

    public String reportMostListenedPodcastCategory(){
        return "The most listened podcast category: \n" +
                controller.reportMostListenedGenreCategory(2);
    }


    /**
     * Shows a list of the song genres
     * @return String
     */
    public String showSongGenre(){
        String genreList = "";
        for (int i = 0; i < songGenres.length; i++){
            genreList += (i + 1) + ". " + songGenres[i];
        }
        return genreList;
    }

    /**
     * Shows a list of the podcast categories
     * @return
     */
    public String showPodcastCategory(){
        String categoryList = "";
        for (int i = 0; i < podcastCategories.length; i++){
            categoryList += (i + 1) + ". " + podcastCategories[i];
        }
        return categoryList;
    }




}
