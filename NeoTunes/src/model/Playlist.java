package model;

import java.util.ArrayList;

public class Playlist {

    private String name;
    private String id;
    private ArrayList<Audio> audios;

    public Playlist(String name) {
        this.name = name;
        this.audios = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Audio> getAudios() {
        return audios;
    }

    /**
     * Shows a list of the instances of Audio stored in the audios ArrayList
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
     * Adds an instance of Audio to the audios Array List
     * @param newAudio Audio
     * @return boolean
     * pos: Adds an instance of Audio to the audios Array List
     */
    public boolean addAudio(Audio newAudio){
        audios.add(newAudio);
        return true;
    }

    /**
     * Deletes an element of audios Array List
     * @param audioIndex int
     * @return boolean
     * pos: Deletes a determined element in the audios Array list
     */
    public boolean deleteAudio(int audioIndex){
        audios.remove(audioIndex);
        return true;
    }

}
