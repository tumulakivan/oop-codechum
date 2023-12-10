public class Music {
    public int duration;
    public String genre;

    Music(int duration, String genre) {
        this.duration = duration;
        this.genre = genre;
    }

    Music(int duration, String genre, char durationType) {
        if (durationType == 'h')
            this.duration = duration * 60;
        else if (durationType == 'd')
            this.duration = duration * 1440;
        else
            this.duration = duration;
        this.genre = genre;
    }
}