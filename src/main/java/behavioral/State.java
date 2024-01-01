package behavioral;

public class State {
    public static void main(String[] args) {
        AudioPlayer player = new AudioPlayer();

        player.clickPlay();
        
        player.clickPrevious();

        player.clickLock();

        player.clickNext();
    }
}

class AudioPlayer {
    PlayerState state;
    boolean playing = false;

    public AudioPlayer() {
        this.state = new ReadyState(this);
    }

    public void changeState(PlayerState state) {
        System.out.printf("Changed state from %s to %s%n", this.state.getName(), state.getName());
        this.state = state;
    }

    public void clickLock() {
        System.out.println("Click lock");
        this.state.clickLock();
    }

    public void clickPlay() {
        System.out.println("Click play");
        this.state.clickPlay();
    }

    public void clickNext() {
        System.out.println("Click next");
        this.state.clickNext();
    }

    public void clickPrevious() {
        System.out.println("Click previous");
        this.state.clickPrevious();
    }

    public void startPlayback() {
        System.out.println("Starting playback...");
        playing = true;
    }

    public void stopPlayback() {
        System.out.println("Stopping playback...");
        playing = false;
    }

    public void nextSong() {
        System.out.println("Skipping to next song...");
    }

    public void previousSong() {
        System.out.println("Going back to previous song...");
    }

    public void fastForward(int time) {
        System.out.println("Fast forwarding to " + time + "...");
    }

    public void rewind(int time) {
        System.out.println("Rewinding to " + time + "...");
    }
}

abstract class PlayerState {
    AudioPlayer player;

    public PlayerState(AudioPlayer player) {
        this.player = player;
    }

    public abstract void clickLock();

    public abstract void clickPlay();

    public abstract void clickNext();

    public abstract void clickPrevious();

    public abstract String getName();
}

class LockedState extends PlayerState {
    public LockedState(AudioPlayer player) {
        super(player);
    }

    @Override
    public void clickLock() {
        if (player.playing) {
            player.changeState(new PlayingState(player));
        } else {
            player.changeState(new ReadyState(player));
        }
    }

    @Override
    public void clickPlay() {

    }

    @Override
    public void clickNext() {

    }

    @Override
    public void clickPrevious() {

    }

    @Override
    public String getName() {
        return "Locked";
    }
}

class ReadyState extends PlayerState {
    public ReadyState(AudioPlayer player) {
        super(player);
    }

    @Override
    public void clickLock() {
        player.changeState(new LockedState(player));
    }

    @Override
    public void clickPlay() {
        player.startPlayback();
        player.changeState(new PlayingState(player));
    }

    @Override
    public void clickNext() {
        player.nextSong();
    }

    @Override
    public void clickPrevious() {
        player.previousSong();
    }

    @Override
    public String getName() {
        return "Ready";
    }
}

class PlayingState extends PlayerState {
    public PlayingState(AudioPlayer player) {
        super(player);
    }

    @Override
    public void clickLock() {
        player.changeState(new LockedState(player));
    }

    @Override
    public void clickPlay() {
        player.stopPlayback();
        player.changeState(new ReadyState(player));
    }

    @Override
    public void clickNext() {
        player.nextSong();
    }

    @Override
    public void clickPrevious() {
        player.previousSong();
    }

    @Override
    public String getName() {
        return "Playing";
    }
}