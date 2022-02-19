package sample;

public enum SongFilePath {
    SONG1("/resources/Songs/Agdal-Soyb.mp3"),
    SONG2("/resources/Songs/Anything - Soyb & Amine Maxwell.mp3"),
    SONG3("/resources/Songs/Autumn 2011 - Loxbeats.mp3"),
    SONG4("/resources/Songs/Come On Lets Go - Le Gang.mp3"),
    SONG5("/resources/Songs/Flamingo - Lesion X.mp3"),
    SONG6("/resources/Songs/Lodhi - Pali Gap.mp3"),
    SONG7("/resources/Songs/Lost Memories - AERØHEAD.mp3"),
    SONG8("/resources/Songs/Morning Routine - Ghostrifter.mp3"),
    SONG9("/resources/Songs/Ohayo - Smith the Mister.mp3"),
    SONG10("/resources/Songs/Spaceship - Lesion X.mp3");

    String filePath;

    SongFilePath(String filePath) {
        this.filePath = filePath;
    }

    String getFilePath() {
        return filePath;
    }
}
