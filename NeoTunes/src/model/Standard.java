package model;

public class Standard extends Consumer{

    private int playlistAmountMax;
    private int boughtSongsMax;
    private int tempListenedSongs;

    public Standard(String nickname, String id) {
        super(nickname, id);
        playlistAmountMax = 20;
        boughtSongsMax = 100;
        tempListenedSongs = 0;
    }

    public int getPlaylistAmountMax() {
        return playlistAmountMax;
    }

    public void setPlaylistAmountMax(int playlistAmountMax) {
        this.playlistAmountMax = playlistAmountMax;
    }

    public int getBoughtSongsMax() {
        return boughtSongsMax;
    }

    public void setBoughtSongsMax(int boughtSongsMax) {
        this.boughtSongsMax = boughtSongsMax;
    }

    public int getTempListenedSongs() {
        return tempListenedSongs;
    }

    public void setTempListenedSongs(int tempListenedSongs) {
        this.tempListenedSongs = tempListenedSongs;
    }
}
