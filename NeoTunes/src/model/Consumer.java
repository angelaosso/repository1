package model;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Consumer extends User{

    private double songTotalTime;
    private double podcastTotalTime;
    private ArrayList<Integer> countListenedGenre;
    private ArrayList<Integer> countListenedCategory;
    private ArrayList<String> nameOfListenedArtist;
    private ArrayList<Integer> countOfListenedArtists;
    private ArrayList<String> nameOfListenedContCreators;
    private ArrayList<Integer> countOfListenedContCreators;
    private ArrayList<Playlist> playlists;
    private ArrayList<BoughtSong> boughtSongs;


    public Consumer(String nickname, String id) {
        super(nickname, id);
        this.songTotalTime = 0;
        this.podcastTotalTime = 0;
        countListenedGenre = new ArrayList<>(Arrays.asList(0, 0, 0, 0));
        countListenedCategory = new ArrayList<>(Arrays.asList(0, 0, 0, 0));
        nameOfListenedArtist = new ArrayList<>();
        countOfListenedArtists = new ArrayList<>();
        nameOfListenedContCreators = new ArrayList<>();
        countOfListenedContCreators = new ArrayList<>();
        playlists = new ArrayList<>();
        boughtSongs = new ArrayList<>();

    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public ArrayList<BoughtSong> getBoughtSongs() {
        return boughtSongs;
    }

    public double getSongTotalTime() {
        return songTotalTime;
    }

    public void setSongTotalTime(double songTotalTime) {
        this.songTotalTime = songTotalTime;
    }

    public double getPodcastTotalTime() {
        return podcastTotalTime;
    }

    public void setPodcastTotalTime(double podcastTotalTime) {
        this.podcastTotalTime = podcastTotalTime;
    }


    /**
     * Shows the consumer's playlists
     * @return String. List of consumer's playlists
     */
    public String showPlaylists(){
        String playlistList = "";
        for (int i = 0; i < playlists.size(); i++){
            playlistList += (i + 1) + ". " + playlists.get(i).getName() + "\n";
        }
        return playlistList;
    }


    /**
     * Description: Adds an Audio object to a specific playlist of the playlist's array list
     * @param playlistIndex int
     * @param newAudio int
     * @return boolean
     * pre: The chosen playlist exists
     * pos: An Audio is added to a playlist
     */
    public boolean addAudioToPlaylist(int playlistIndex, Audio newAudio){
        return playlists.get(playlistIndex).addAudio(newAudio);
    }

    public ArrayList<Integer> getCountListenedGenre() {
        return countListenedGenre;
    }

    public ArrayList<Integer> getCountListenedCategory() {
        return countListenedCategory;
    }

    public ArrayList<String> getNameOfListenedArtist() {
        return nameOfListenedArtist;
    }

    public ArrayList<Integer> getCountOfListenedArtists() {
        return countOfListenedArtists;
    }

    public ArrayList<String> getNameOfListenedContCreators() {
        return nameOfListenedContCreators;
    }

    public ArrayList<Integer> getCountOfListenedContCreators() {
        return countOfListenedContCreators;
    }

    /**
     * Adds an instance of BoughtSong to the boughtSongs ArrayList
     * @param newBoughtSong BoughtSong
     * @return boolean
     */
    public boolean buySong(BoughtSong newBoughtSong){
        return this.boughtSongs.add(newBoughtSong);
    }

    /**
     * Iterates through artists' ArrayList to check if a specific artist is already in the array
     * @param producer Producer
     * @return boolean
     */
    public boolean checkIfArtistInside(Producer producer){
        for (int i = 0; i < nameOfListenedArtist.size(); i++){
            if (producer.getName().equals(nameOfListenedArtist.get(i))){
                return true;
            }
        }
        return false;
    }

    /**
     * Iterates through artists' ArrayList to find a specific one and returns its index in the ArrayList
     * @param producer Producer
     * @return int
     */
    public int findArtistIndex(Producer producer){
        for (int i = 0; i < nameOfListenedArtist.size(); i++){
            if (producer.getName().equals(nameOfListenedArtist.get(i))){
                return i;
            }
        }
        return 0;
    }

    /**
     * Iterates through content creators' ArrayList to check if a specific artist is already in the array
     * @param producer Producer
     * @return boolean
     */
    public boolean checkIfContCreatorInside(Producer producer){
        for (int i = 0; i < nameOfListenedContCreators.size(); i++){
            if (producer.getName().equals(nameOfListenedContCreators.get(i))){
                return true;
            }
        }
        return false;
    }


    /**
     * Iterates through content creators' ArrayList to find a specific one and returns its index in the ArrayList
     * @param producer
     * @return
     */
    public int findContCreatorIndex(Producer producer){
        for (int i = 0; i < nameOfListenedContCreators.size(); i++){
            if (producer.getName().equals(nameOfListenedContCreators.get(i))){
                return i;
            }
        }
        return 0;
    }




    //public boolean deleteAudioFromPlaylist(int playlistIndex, int audioIndex){
        //return playlists.get(playlistIndex).getAudios().remove(playlists.get(playlistIndex).getAudios().get(audioIndex));
    //}

}
