package model;

import java.util.ArrayList;

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
    private ArrayList<Song> boughtSongs;

    public Consumer(String nickname, String id) {
        super(nickname, id);
        this.songTotalTime = 0;
        this.podcastTotalTime = 0;
        countListenedGenre = new ArrayList<Integer>();
        countListenedCategory = new ArrayList<Integer>();
        nameOfListenedArtist = new ArrayList<String>();
        countOfListenedArtists = new ArrayList<Integer>();
        nameOfListenedContCreators = new ArrayList<String>();
        countOfListenedContCreators = new ArrayList<Integer>();
        playlists = new ArrayList<Playlist>();
        boughtSongs = new ArrayList<Song>();

    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
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

    //public boolean deleteAudioFromPlaylist(int playlistIndex, int audioIndex){
        //return playlists.get(playlistIndex).getAudios().remove(playlists.get(playlistIndex).getAudios().get(audioIndex));
    //}

}
