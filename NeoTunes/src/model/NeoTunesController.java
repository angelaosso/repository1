package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IllegalFormatCodePointException;
import java.util.Random;

public class NeoTunesController {

    private ArrayList<User> users;
    private ArrayList<Audio> audios;
    private ArrayList<String> ads;
    private Random random = new Random();
    private Genre songGenres[] = Genre.values();
    private Category podcastCategories[] = Category.values();

    public NeoTunesController() {

        users = new ArrayList<User>();
        audios = new ArrayList<Audio>();
        ads = new ArrayList<String>(
                Arrays.asList("Nike - Just Do It", "Coca-Cola - Open Happiness", "M&Ms - Melts in Your Mouth, Not in Your Hands"));
    }

    /**
     * Checks if an ID already exists in the users array list
     * @param id String
     * @return boolean
     * pre: There are objects inside the users array list
     */
    public boolean userIdExists(String id){

        for (int i = 0; i < users.size(); i++){
            if (users.get(i).getId().equals(id)){
                return true;
            }
        }

        return false;

    }

    /**
     * Checks if the consumer of type standard has reached the maximum amount of playlists that they can create (20)
     * @param consumerId int. Index of consumer in users array
     * @return boolean
     * pre: The users array list has at least 1 element
     */
    public boolean standardReachedMaxPlaylist(int consumerId){
        if (users.get(consumerId - 1) instanceof Standard){
            if (((Standard) users.get(consumerId - 1)).getPlaylists().size() == ((Standard) users.get(consumerId - 1)).getPlaylistAmountMax()){
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if there are elements in a consumer´s playlist's array list
     * @param consumerId int. Index of consumer in users array list
     * @return boolean
     * pre: the consumer exists
     */
    public boolean consumerHasPlaylists(int consumerId){
        if (users.get(consumerId - 1) instanceof Consumer){
            if (((Consumer) users.get(consumerId - 1)).getPlaylists().size() >= 1){
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }

    /**
     * Checks if there are any instances of Artist in the users array list
     * @return boolean
     */
    public boolean userHasArtists(){
        for (int i = 0; i < users.size(); i++){
            if (users.get(i) instanceof Artist){
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if there are any instances of Content Creator in the users array list
     * @return boolean
     */
    public boolean userHasContCreators(){
        for (int i = 0; i < users.size(); i++){
            if (users.get(i) instanceof ContentCreator){
                return true;
            }
        }
        return false;
    }

    /**
     * Creates an instance of Standard or Premium Consumer, according to the user's selection
     * @param consumerType int
     * @param nickname String
     * @param id String
     * @return boolean
     * pos: Creates an instance of Consumer class, either Standard or Premium
     */
    public boolean createConsumer(int consumerType, String nickname, String id) {

        User newConsumer;

        if (consumerType == 1){
            newConsumer = new Standard(nickname, id);
        }
        else {
            newConsumer = new Premium(nickname, id);
        }

        return users.add(newConsumer);

    }

    /**
     * Creates an instance of Artist or Content Creator Producer, according to the user's selection
     * @param producerType int
     * @param nickname String
     * @param id String
     * @param name String
     * @param url String
     * @return boolean
     * pos: Creates an instance of Artist or Content Creator Producer
     */
    public boolean createProducer(int producerType, String nickname, String id, String name, String url){

        User newProducer;

        if (producerType == 1){
            newProducer = new Artist(nickname, id, name, url);
        }
        else {
            newProducer = new ContentCreator(nickname, id, name, url);
        }

        return users.add(newProducer);

    }

    /**
     * Creates an instance of Song class, adds it to the audios array list and adds it to the corresponding instance of artist
     * @param name String
     * @param url String
     * @param duration double
     * @param album String
     * @param value double
     * @param genre int
     * @param artistChoice int
     * @return boolean
     * pre: There are instances of the class Artist created
     * pos: Creates an instance of Song class, adds it to the audios array list and adds it to array list of instance of Artist
     */
    public boolean createAudio(String name, String url, double duration, String album, double value, Genre genre, int artistChoice){
        Audio newSong = new Song(name, url, duration, album, value, genre);
        addAudioToProducer(artistChoice, newSong);
        return audios.add(newSong);
    }

    /**
     * Creates an instance of Podcast class, adds it to the audios array list and adds it to the corresponding instance of Artist
     * @param name String
     * @param url String
     * @param duration double
     * @param description String
     * @param category int
     * @param contCreatorChoice int
     * @return Creates an instance of Podcast class, adds it to the audios array list and adds it to array list of audios of instance of Artist
     */
    public boolean createAudio(String name, String url, double duration, String description, Category category, int contCreatorChoice){
        Audio newPodcast = new Podcast(name, url, duration, description, category);
        addAudioToProducer(contCreatorChoice, newPodcast);
        return audios.add(newPodcast);
    }

    /**
     * Creates an instance of Playlist
     * @param consumerIndex int
     * @param name String
     * @return boolean
     * pos: Creates an instance of Playlist and adds it to the playlists Array list
     */
    public boolean createPlaylist(int consumerIndex, String name){
        Playlist newPlaylist = new Playlist(name);
        return addPlaylistToConsumer(consumerIndex, newPlaylist);
    }

    /**
     * Adds instance of Audio to Array List of instance of Producer
     * @param producerIndex int
     * @param newAudio Audio
     * @return boolean
     * pos: Adds instance of Audio to Array List of instance of Producer
     */
    public boolean addAudioToProducer(int producerIndex, Audio newAudio){
        if (users.get(producerIndex - 1) instanceof Producer){
            ((Producer) users.get(producerIndex - 1)).getAudios().add(newAudio);
            return true;
        }
        return false;
    }

    /**
     * Adds instance of Playlist to Array list of Consumer instance
     * @param consumerIndex int
     * @param newPlaylist Playlist
     * @return boolean
     * pre: The instances of Consumer and Playlist exist
     * pos: Adds instance of Playlist to Array list of Consumer instance
     */
    public boolean addPlaylistToConsumer(int consumerIndex, Playlist newPlaylist){
        if (users.get(consumerIndex - 1) instanceof Consumer){
            ((Consumer) users.get(consumerIndex - 1)).getPlaylists().add(newPlaylist);
            return true;
        }
        return false;
    }

    /**
     * Adds an instance of Audio to instance of Playlist of a determined instance of Consumer
     * @param consumerIndex int
     * @param playlistIndex int
     * @param audioIndex int
     * @return boolean
     * pre: The instances of Consumer, Playlist, and Audio exist
     * pos: Adds an instance of Audio to instance of Playlist of a determined instance of Consumer
     */
    public boolean addAudioToPlaylist(int consumerIndex, int playlistIndex, int audioIndex){
        if (users.get(consumerIndex - 1) instanceof Consumer){
            Audio newAudio = audios.get(audioIndex - 1);
            return ((Consumer) users.get(consumerIndex - 1)).addAudioToPlaylist(playlistIndex - 1, newAudio);
        }
        return false;
    }

    /**
     * Deletes an element from Array List of instance of Playlist of a determined Consumer
     * @param consumerIndex int
     * @param playlistIndex int
     * @param audioIndex int
     * @return boolean
     * pre: The instances of Consumer, Playlist, and Audio exist, and there is at least 1 element in the instance of Playlist
     */
    public boolean deleteAudioFromPlaylist(int consumerIndex, int playlistIndex, int audioIndex){

        if (users.get(consumerIndex - 1) instanceof Consumer){
            return ((Consumer) users.get(consumerIndex - 1)).getPlaylists().get(playlistIndex - 1).deleteAudio(audioIndex - 1);
        }
        else {
            return false;
        }
    }

    /**
     * Creates an instance of BoughtSong and adds it to the BoughtSongs ArrayList of a specific Consumer. If the consumer is Standard, the bought songs maximum will be checked, so it complies with it. Also updates the sold times number of the chosen song
     * @param consumerIndex int
     * @param songIndex int
     * @return boolean
     * pre: The chosen consumer and song exist.
     * pos: An instance of BoughtSong is added to an ArrayList of a Consumer
     */
    public boolean buySong(int consumerIndex, int songIndex){

        if (users.get(consumerIndex - 1) instanceof Standard){
            if (((Standard) users.get(consumerIndex - 1)).getBoughtSongsMax() > ((Standard) users.get(consumerIndex - 1)).getBoughtSongs().size()){
                BoughtSong newBoughtSong = new BoughtSong((Song) audios.get(songIndex - 1));
                ((Song) audios.get(songIndex - 1)).setSoldTimes(((Song) audios.get(songIndex - 1)).getSoldTimes() + 1);
                return ((Standard) users.get(consumerIndex - 1)).buySong(newBoughtSong);
            }
            else {
                return false;
            }
        }
        else {
            BoughtSong newBoughtSong = new BoughtSong((Song) audios.get(songIndex - 1));
            return ((Standard) users.get(consumerIndex - 1)).buySong(newBoughtSong);
        }
    }

    public String playAudio(int consumerIndex, int audioIndex){

        Audio audioToPlay = audios.get(audioIndex - 1);

        if (users.get(consumerIndex - 1) instanceof Standard){
            if (audioToPlay instanceof Podcast){
                System.out.println(ads.get(random.nextInt(3)));
            }
            else if (((Standard) users.get(consumerIndex - 1)).getTempListenedSongs() == 2){
                System.out.println(ads.get(random.nextInt(3)));
                ((Standard) users.get(consumerIndex - 1)).setTempListenedSongs(0);
            }
        }

        Consumer consumer = (Consumer) users.get(consumerIndex - 1);
        Producer producer = findAudiosProducer(audios.get(audioIndex - 1));

        updateConsumerData(consumer, audioToPlay);
        updateProducerData(producer, audios.get(audioIndex - 1));
        updateAudioData(audios.get(audioIndex - 1));

        return audios.get(audioIndex - 1).play();
    }

    /**
     * Updates the attributes of the Consumer who is listening to the Audio. Updates the song or podcast total listened time, the genre or category listened times, the producers that the Consumer has listened to.
     * @param consumer Consumer
     * @param playedAudio Audio
     * pre: The consumer and Audio exist
     * pos: Updates the attributes song or podcast total listened time, the genre or category listened times, the producers that the Consumer has listened to.
     */
    public void updateConsumerData(Consumer consumer, Audio playedAudio){

        double audioTime = playedAudio.getDuration();

        if (playedAudio instanceof Song){
            int genreIndex = -1;
            for (int i = 0; i < songGenres.length; i++){
                if (((Song) playedAudio).getGenre().equals(songGenres[i])){
                    genreIndex = i;
                }
            }
            Standard consumerStandard = (Standard) consumer;
            consumerStandard.setTempListenedSongs(consumerStandard.getTempListenedSongs() + 1);
            consumer.setSongTotalTime(consumer.getSongTotalTime() + audioTime);
            consumer.getCountListenedGenre().set(genreIndex, consumer.getCountListenedGenre().get(genreIndex) + 1);
            if (consumer.checkIfArtistInside(findAudiosProducer(playedAudio))){
                consumer.getCountOfListenedArtists().set(consumer.findArtistIndex(findAudiosProducer(playedAudio)), consumer.getCountOfListenedArtists().get(consumer.findArtistIndex(findAudiosProducer(playedAudio))) + 1);
                System.out.println("Count Artist: " + consumer.getCountOfListenedArtists().get(consumer.findArtistIndex(findAudiosProducer(playedAudio))));
            }
            else {
                consumer.getNameOfListenedArtist().add(findAudiosProducer(playedAudio).getName());
                consumer.getCountOfListenedArtists().add(1);
            }

        }
        else if (playedAudio instanceof Podcast){
            int categoryIndex = -1;
            Podcast podcast = (Podcast) playedAudio;
            for (int i = 0; i < podcastCategories.length; i++){
                if (podcast.getCategory().equals(podcastCategories[i])){
                    categoryIndex = i;
                }
            }
            consumer.setPodcastTotalTime(consumer.getPodcastTotalTime() + podcast.getDuration());
            consumer.getCountListenedCategory().set(categoryIndex, consumer.getCountListenedCategory().get(categoryIndex) + 1);
            if (consumer.checkIfContCreatorInside(findAudiosProducer(podcast))){
                consumer.getCountOfListenedContCreators().set(consumer.findContCreatorIndex(findAudiosProducer(podcast)), consumer.getCountOfListenedContCreators().get(consumer.findContCreatorIndex(findAudiosProducer(podcast))) + 1);
                System.out.println("Count Cont Creator: " + consumer.getCountOfListenedContCreators().get(consumer.findContCreatorIndex(findAudiosProducer(podcast))));
            }
            else {
                consumer.getNameOfListenedContCreators().add(findAudiosProducer(podcast).getName());
                consumer.getCountOfListenedContCreators().add(1);
            }
        }
    }

    /**
     * Returns the Producer of a specific Audio instance
     * @param listenedAudio Audio
     * @return Producer
     * pre: The Audio is assigned previously to a Producer
     */
    public Producer findAudiosProducer(Audio listenedAudio){
        for (int i = 0; i < users.size(); i++){
            if (users.get(i) instanceof Producer){
                for (int j = 0; j < ((Producer) users.get(i)).getAudios().size(); j++){
                    if (((Producer) users.get(i)).getAudios().get(j).getName().equals(listenedAudio.getName())){
                        return (Producer) users.get(i);
                    }
                }
            }
        }
        return null;
    }

    /**
     * Updates the amount of times and the total listened time that a Producer has been listened to after a Consumer listens to their audio
     * @param producer User
     * @param playedAudio Audio
     */
    public void updateProducerData(User producer, Audio playedAudio){
        Producer producer1 = (Producer) producer;
        producer1.setListenedTime(producer1.getListenedTime() + playedAudio.getDuration());
        producer1.setReproducedTimes(producer1.getReproducedTimes() + 1);
    }

    /**
     * Updates the reproduced times of an audio
     * @param playedAudio Audio
     * pre: The audio exists
     * pos: Reproduced times of an audio is updated with +1
     */
    public void updateAudioData(Audio playedAudio){
        playedAudio.setReproducedTimes(playedAudio.getReproducedTimes() + 1);
    }
    /**
     * Shows the chosen playlist's ID code
     * @param consumerIndex int
     * @param playlistIndex int
     * @return String
     */
    public String sharePlaylist(int consumerIndex, int playlistIndex){
        Consumer consumer = (Consumer) users.get(consumerIndex - 1);
        return consumer.getPlaylists().get(playlistIndex - 1).generateId();
    }

    public ArrayList<Audio> findMostSoldSong(){
        int soldMaxCount = -1;
        ArrayList<Audio> mostSoldSongs = new ArrayList<>();

        for (int i = 0; i < audios.size(); i++){
            if (audios.get(i) instanceof Song){
                if (((Song) audios.get(i)).getSoldTimes() > soldMaxCount){
                    soldMaxCount = ((Song) audios.get(i)).getSoldTimes();
                }
            }
        }

        for (int i = 0; i < audios.size(); i++){
            if (audios.get(i) instanceof Song) {
                if (((Song) audios.get(i)).getSoldTimes() == soldMaxCount) {
                    mostSoldSongs.add(audios.get(i));
                }
            }
        }
        return mostSoldSongs;
    }

    public String reportMostSoldSong(ArrayList<Audio> mostSoldSongs){
        String report = "";
        Song song;
        for (int i = 0; i < mostSoldSongs.size(); i++){
            song = (Song) mostSoldSongs.get(i);
            System.out.println("song value: " + song.getValue());
            System.out.println("song sold times: " + song.getSoldTimes());
            report += "- " +  song.getName() + ": " + song.getSoldTimes() + "($" + song.getValue() * song.getSoldTimes() + ")";
        }

        if (report == ""){
            return "Not a single song has been sold";
        }
        else {
            return "The most sold song(s): \n" + report;
        }

    }

    /**
     * Calculates the amount of reproductions that each song genre has in the entire platform
     * @return String
     */
    public int[] sumReprodPerGenreCategory(int audioType){
        int[] tempTotalCount;

        if (audioType == 1){
            tempTotalCount = new int[songGenres.length];
            for (int i = 0; i < users.size(); i++){
                if (users.get(i) instanceof Consumer){
                    for (int j = 0; j < ((Consumer) users.get(i)).getCountListenedGenre().size(); j++){
                        tempTotalCount[j] += ((Consumer) users.get(i)).getCountListenedGenre().get(j);
                    }
                }
            }
        }
        else {
            tempTotalCount = new int[podcastCategories.length];
            for (int i = 0; i < users.size(); i++){
                if (users.get(i) instanceof Consumer){
                    for (int j = 0; j < ((Consumer) users.get(i)).getCountListenedCategory().size(); j++){
                        tempTotalCount[j] += ((Consumer) users.get(i)).getCountListenedCategory().get(j);
                    }
                }
            }
        }
        return tempTotalCount;

    }

    public String reportGenreSoldSongs(){
        int[] countSoldSongsPerGenre = new int[songGenres.length];
        double[] totalMoneyPerSongGenre = new double[songGenres.length];
        int genreIndex = 0;

        for (int i = 0; i < audios.size(); i++){
            System.out.println("count i: " + i);
            if (audios.get(i) instanceof Song){
                for (int j = 0; j < songGenres.length; j++){
                    System.out.println("count i: " + i);
                    if (((Song) audios.get(i)).getGenre().equals(songGenres[j])){
                        genreIndex = j;
                    }
                    System.out.println("count i: " + i);
                }
                countSoldSongsPerGenre[genreIndex] += ((Song) audios.get(i)).getSoldTimes();
                totalMoneyPerSongGenre[genreIndex] += ((Song) audios.get(i)).getSoldTimes() * ((Song) audios.get(i)).getValue();
            }
        }

        String report = "Amount of sells by song genre and its corresponding income: ";

        for (int i = 0; i < songGenres.length; i++){
            report += "- " + songGenres[i] + ": " + countSoldSongsPerGenre[i] + " ($" + totalMoneyPerSongGenre[i] + ")\n";
        }

        return report;
    }

    /**
     * Finds the maximum number of reproductions belonging to a specific genre or category and shows the genre or category and its reproductions
     * @return String
     */
    public String reportMostListenedGenreCategory(int audioType){

        int genreMaxCount = -1;
        int[] genreCategoryCount;

        if (audioType == 1){
            genreCategoryCount = sumReprodPerGenreCategory(1);
        }
        else {
            genreCategoryCount = sumReprodPerGenreCategory(2);
        }

        String report = "";

        for (int i = 0; i < genreCategoryCount.length; i++){
            if (genreCategoryCount[i] > genreMaxCount){
                genreMaxCount = genreCategoryCount[i];
            }
        }

        if (audioType == 1){
            for (int i = 0; i < genreCategoryCount.length; i++){
                if (genreCategoryCount[i] == genreMaxCount){
                    report += "- " + songGenres[i] + ": " + genreCategoryCount[i] + "\n";
                }
            }
        }
        else {
            for (int i = 0; i < genreCategoryCount.length; i++){
                if (genreCategoryCount[i] == genreMaxCount){
                    report += "- " + podcastCategories[i] + ": " + genreCategoryCount[i] + "\n";
                }
            }
        }
        return report;
    }

    public String reportMostListenedGenreUser(int consumerIndex){
        Consumer consumer = (Consumer) users.get(consumerIndex - 1);
        int genreMaxCount = -1;
        String report = "The most listened genre(s) listened by " + consumer.getNickname() + ": \n";

        for (int i = 0; i < consumer.getCountListenedGenre().size(); i++){
            if (consumer.getCountListenedGenre().get(i) > genreMaxCount){
                genreMaxCount = consumer.getCountListenedGenre().get(i);
            }
        }

        for (int i = 0; i < consumer.getCountListenedGenre().size(); i++){
            if (consumer.getCountListenedGenre().get(i) == genreMaxCount){
                report += "- " + songGenres[i] + ": " + consumer.getCountListenedGenre().get(i);
            }
        }

        return report;
    }

    public String reportMostListenedCategoryUser(int consumerIndex){
        Consumer consumer = (Consumer) users.get(consumerIndex - 1);
        int categoryMaxCount = -1;
        String report = "The most listened category listened by " + consumer.getNickname() + ": \n";

        for (int i = 0; i < consumer.getCountListenedCategory().size(); i++){
            if (consumer.getCountListenedCategory().get(i) > categoryMaxCount){
                categoryMaxCount = consumer.getCountListenedGenre().get(i);
            }
        }
        for (int i = 0; i < consumer.getCountListenedCategory().size(); i++){
            if (consumer.getCountListenedCategory().get(i) == categoryMaxCount){
                report += "- " + podcastCategories[i] + ": " + consumer.getCountListenedCategory().get(i);
            }
        }
        return report;
    }

    /**
     * Counts the total number of reproductions that each type of audio has in the platform. Returns the song and podcast count.
     * @return String
     */
    public String reportNumberOfReprodAudios(){
        int songCount = 0;
        int podcastCount = 0;

        for (int i = 0; i < audios.size(); i++){
            if (audios.get(i) instanceof Song){
                songCount += audios.get(i).getReproducedTimes();
            }
            else if (audios.get(i) instanceof Podcast){
                podcastCount += audios.get(i).getReproducedTimes();
            }
        }

        return "The number of reproductions of each type of audio are: \n" +
                "- Songs: " + songCount + "\n" +
                "- Podcasts: " + podcastCount + "\n";
    }

    public String reportTop10Audios(){
        ArrayList<Audio> tempSongs = new ArrayList<>();
        ArrayList<Audio> tempPodcasts = new ArrayList<>();

        for (int i = 0; i < audios.size(); i++){
            if (audios.get(i) instanceof Song){
                tempSongs.add(audios.get(i));
            }
            else if (audios.get(i) instanceof Podcast){
                tempPodcasts.add(audios.get(i));
            }
        }

        String songsTop10 = showSongsTop10(sortAudiosArrayList(tempSongs));
        String podcastsTop10 = showPodcastsTop10(sortAudiosArrayList(tempPodcasts));

        return "Songs Top 10: \n" +
                songsTop10 + "\n" +
                "Podcasts Top 10: \n" +
                podcastsTop10;

    }

    /**
     * Sorts  a duplicate of the Producers' ArrayLists and shows each producers' Top 5 according to their reproduction number of times
     * @return String
     */
    public String reportTop5Producers(){

        ArrayList<Producer> tempArtists = new ArrayList<>();
        ArrayList<Producer> tempContCreators = new ArrayList<>();

        for (int i = 0; i < users.size(); i++){
            if (users.get(i) instanceof Artist){
                tempArtists.add((Producer) users.get(i));
            }
            else if (users.get(i) instanceof ContentCreator){
                tempContCreators.add((Producer) users.get(i));
            }
        }

        String artistTop5 = showTop5(sortProducersArray(tempArtists));
        String contCreatorTop5 = showTop5(sortProducersArray(tempContCreators));

        return "Artists Top 5: \n" +
                artistTop5 + "\n" +
                "Content Creators Top 5: \n" +
                contCreatorTop5 + "\n";
    }

    /**
     * Sorts an ArrayList of producers from the producer with the most reproduction times with the least reproduction times
     * @param arrayToSort ArrayList<Producer>
     * @return ArrayList<Producer>
     */
    public ArrayList<Producer> sortProducersArray(ArrayList<Producer> arrayToSort){
        boolean changed = true;
        int j = 0;
        Producer temp;

        while (changed){
            changed = false;
            j++;
            for (int i = 0; i < arrayToSort.size() - j; i++){
                if (arrayToSort.get(i) != null && arrayToSort.get(i + 1) != null){
                    if (arrayToSort.get(i).getReproducedTimes() < arrayToSort.get(i + 1).getReproducedTimes()){
                        temp = arrayToSort.get(i);
                        arrayToSort.set(i, arrayToSort.get(i + 1));
                        arrayToSort.set(i + 1, temp);
                        changed = true;

                    }
                }
            }
        }
        return arrayToSort;
    }

    /**
     * Iterates through Producers' array list to show name and number of reproductions of the first 5 positions
     */
    public String showTop5(ArrayList<Producer> sortedProducers){
        String top5List = "";

        if (sortedProducers.size() < 5){
            for (int i = 0; i < sortedProducers.size(); i++){
                top5List += (i + 1) + ": " + sortedProducers.get(i).getNickname() + "(" + sortedProducers.get(i).getReproducedTimes() + ")\n";
            }
        }
        else {
            for (int i = 0; i < 5; i++){
                top5List += (i + 1) + ": " + sortedProducers.get(i).getNickname() + "(" + sortedProducers.get(i).getReproducedTimes() + ")\n";
            }
        }

        return top5List;
    }

    public ArrayList<Audio> sortAudiosArrayList(ArrayList<Audio> arrayToSort){
        boolean changed = true;
        int j = 0;
        Audio temp;

        while (changed){
            changed = false;
            j++;
            for (int i = 0; i < arrayToSort.size() - j; i++){
                if (arrayToSort.get(i) != null && arrayToSort.get(i + 1) != null){
                    if (arrayToSort.get(i).getReproducedTimes() < arrayToSort.get(i + 1).getReproducedTimes()){
                        temp = arrayToSort.get(i);
                        arrayToSort.set(i, arrayToSort.get(i + 1));
                        arrayToSort.set(i + 1, temp);
                        changed = true;

                    }
                }
            }
        }
        return arrayToSort;
    }

    public String showSongsTop10(ArrayList<Audio> sortedAudios){
        String top10List = "";
        Song tempSong;

        if (sortedAudios.size() < 10){
            for (int i = 0; i < sortedAudios.size(); i++){
                tempSong = (Song) sortedAudios.get(i);
                top10List += (i + 1) + ". " + sortedAudios.get(i).getName() + "(" + tempSong.getGenre() + "): " + tempSong.getReproducedTimes();
            }
        }

        for (int i = 0; i < 10; i++){
            tempSong = (Song) sortedAudios.get(i);
            top10List += (i + 1) + ". " + sortedAudios.get(i).getName() + "(" + tempSong.getGenre() + "): " + tempSong.getReproducedTimes();
        }
        return top10List;
    }

    public String showPodcastsTop10(ArrayList<Audio> sortedAudios){
        String top10List = "";
        Podcast tempPodcast;
        for (int i = 0; i < 10; i++){
            tempPodcast = (Podcast) sortedAudios.get(i);
            top10List += (i + 1) + ". " + sortedAudios.get(i).getName() + "(" + tempPodcast.getCategory() + "): " + tempPodcast.getReproducedTimes();
        }
        return top10List;
    }





    /**
     * Shows a list of the instances of Producer stored in the users Array List
     * @param producerType int
     * @return String
     */
    public String showProducers(int producerType){

        String producerList = "";

        if (producerType == 1){

            for (int i = 0; i < users.size(); i++){
                if (users.get(i) instanceof Artist){
                    producerList += (i + 1) + ". " + ((Artist) users.get(i)).getName() + "\n";
                }
            }

        }
        else {

            for (int i = 0; i < users.size(); i++){
                if (users.get(i) instanceof ContentCreator){
                    producerList += (i + 1) + ". " + ((ContentCreator) users.get(i)).getName() + "\n";
                }
            }

        }
        return producerList;
    }

    /**
     * Shows a list of instances of Consumer stored in the users Array List
     * @return String
     */
    public String showConsumers(){

        String consumerList = "";
        for (int i = 0; i < users.size(); i++){
            if (users.get(i) instanceof Consumer){
                consumerList += (i + 1) + ". " + users.get(i).getNickname() + "\n";
            }
        }
        return consumerList;

    }

    /**
     * Shows a list of the instances of Playlist stored in a Consumer's Arrya List
     * @param consumerIndex int
     * @return String
     */
    public String showConsumersPlaylists(int consumerIndex){
        if (users.get(consumerIndex - 1) instanceof Consumer){
            return ((Consumer) users.get(consumerIndex - 1)).showPlaylists();
        }
        return "The chosen user is not a consumer";
    }

    /**
     * Shows a list of the instances of Audio stored in the audios Array list
     * @return String
     */
    public String showAudios(int audioTypeToShow){

        String songs = "";
        String podcasts = "";

        if (audioTypeToShow == 1){

            for (int i = 0; i < audios.size(); i++){
                if (audios.get(i) instanceof Song){
                    songs += (i + 1) + ". " + audios.get(i).getName() + "\n";
                }
            }

            return "Songs: \n" +
                    songs + "\n";

        }

        else if (audioTypeToShow == 2){

            for (int i = 0; i < audios.size(); i++){
                if (audios.get(i) instanceof Podcast){
                    podcasts += (i + 1) + ". " + audios.get(i).getName() + "\n";
                }
            }
            return "Podcasts: \n" +
                    podcasts + "\n";

        }

        for (int i = 0; i < audios.size(); i++){
            if (audios.get(i) instanceof Song){
                songs += (i + 1) + ". " + audios.get(i).getName() + "\n";
            }
        }


        for (int i = 0; i < audios.size(); i++){
            if (audios.get(i) instanceof Podcast){
                podcasts += (i + 1) + ". " + audios.get(i).getName() + "\n";
            }
        }

        return "Songs: \n" +
                songs + "\n" +
                "Podcasts: \n" +
                podcasts;

    }

    /**
     * Shows a list of instances of Audio stored in a determined playlist of an instance of Consumer
     * @param consumerIndex int
     * @param playlistIndex int
     * @return String
     */
    public String showAudios(int consumerIndex, int playlistIndex){
        if (users.get(consumerIndex - 1) instanceof Consumer){
            return ((Consumer) users.get(consumerIndex - 1)).getPlaylists().get(playlistIndex - 1).showAudios();
        }
        return "Error";
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Audio> getAudios() {
        return audios;
    }






}
