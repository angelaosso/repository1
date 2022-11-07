package model;

import java.util.ArrayList;

public class NeoTunesController {

    private ArrayList<User> users;
    private ArrayList<Audio> audios;

    public NeoTunesController() {

        users = new ArrayList<User>();
        audios = new ArrayList<Audio>();

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
    public String showAudios(){

        String songs = "";

        for (int i = 0; i < audios.size(); i++){
            if (audios.get(i) instanceof Song){
                songs += (i + 1) + ". " + audios.get(i).getName() + "\n";
            }
        }

        String podcasts = "";
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
