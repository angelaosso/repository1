package model;

import java.util.ArrayList;
import java.util.Random;

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

    /**
     * Checks if the playlists has only songs, only podcasts, or both songs and podcasts
     * @return int
     */
    public int checkPlaylistContent(){

        boolean hasSong = false;
        boolean hasPodcast = false;

        for (int i = 0; i < audios.size(); i++){
            if (audios.get(i) instanceof Song){
                hasSong = true;
            }
            if (audios.get(i) instanceof Podcast){
                hasPodcast = true;
            }
        }

        if (hasPodcast && hasSong){
            return 1;
        }
        else if (hasSong){
            return 2;
        }
        else {
            return 3;
        }

    }

    /**
     * Generates the playlist's ID according to its contents by iterating with different patterns over a random number 6 x 6 matrix
     * @return String
     */
    public String generateId(){
        Random random = new Random();
        int[][] idMatrix = new int[6][6];
        String matrixPrint = "";

        for (int i = 0; i < idMatrix.length; i++){
            for (int j = 0; j < idMatrix.length; j++){
                idMatrix[i][j] = random.nextInt(10);
                matrixPrint += idMatrix[i][j] + "";
            }
            matrixPrint += "\n";
        }

        System.out.println(matrixPrint);

        int contentType = checkPlaylistContent();
        StringBuilder playlistId = new StringBuilder();

        if (contentType == 2){
            for (int i = idMatrix.length - 1; i >= 0; i--){
                playlistId.append(idMatrix[i][0]);
            }
            for (int i = 1; i < idMatrix.length - 1; i++){
                playlistId.append(idMatrix[i][i]);
            }
            for (int i = idMatrix.length - 1; i >= 0; i--){
                playlistId.append(idMatrix[i][idMatrix.length - 1]);
            }
            return playlistId.toString();
        }
        else if (contentType == 3){

            for (int i = 0; i < idMatrix[0].length / 2; i++){
                playlistId.append(idMatrix[0][i]);
            }
            for (int i = 1; i < idMatrix.length; i++){
                playlistId.append(idMatrix[i][idMatrix.length/2 - 1]);
            }
            for (int i = idMatrix.length - 1; i > 0; i--){
                playlistId.append(idMatrix[i][idMatrix.length/2]);
            }
            for (int i = idMatrix.length/2; i < idMatrix.length; i++){
                playlistId.append(idMatrix[0][i]);
            }
        }
        else {

            int count = 0;

            for (int i = idMatrix.length - 1; i >= 0; i--){
                if (i <= 2){
                    count++;
                }
                for (int j = idMatrix.length - 1; j >= count; j--){
                    if (i % 2 == 0) {
                        if (j % 2 != 0){
                            playlistId.append(idMatrix[i][j]);
                        }
                    }
                    else{
                        if (j % 2 == 0){
                            playlistId.append(idMatrix[i][j]);
                        }
                    }
                }
            }
        }
        return "Playlist's ID: " + playlistId;
    }


}
