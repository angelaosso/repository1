package model;

import java.time.LocalDate;

public class BoughtSong {

    private LocalDate TransactionDate;
    private Song boughtSong;


    public BoughtSong(Song boughtSong) {
        this.boughtSong = boughtSong;
        this.TransactionDate = LocalDate.now();
    }

    public LocalDate getTransactionDate() {
        return TransactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        TransactionDate = transactionDate;
    }

    public Song getBoughtSong() {
        return boughtSong;
    }

    public void setBoughtSong(Song boughtSong) {
        this.boughtSong = boughtSong;
    }
}
