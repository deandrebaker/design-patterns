package structural;

public class Bridge {
    public static void main(String[] args) {
        Tv tv = new Tv();
        RemoteControl rc = new RemoteControl(tv);
        System.out.printf("TV is on: %s%n", tv.isEnabled());
        System.out.println("Toggle power...");
        rc.togglePower();
        System.out.printf("TV is on: %s%n", tv.isEnabled());

        Radio radio = new Radio();
        AdvancedRemoteControl arc = new AdvancedRemoteControl(radio);
        System.out.printf("Radio volume: %s%n", radio.getVolume());
        arc.mute();
        System.out.println("Mute...");
        System.out.printf("Radio volume: %s%n", radio.getVolume());
    }
}

interface Device {
    boolean isEnabled();

    void enable();

    void disable();

    int getVolume();

    void setVolume(int volume);

    int getChannel();

    void setChannel(int channel);
}

class Tv implements Device {
    boolean on;
    int volume;
    int channel;

    public Tv() {
        this.on = false;
        this.volume = 50;
        this.channel = 1;
    }

    @Override
    public boolean isEnabled() {
        return this.on;
    }

    @Override
    public void enable() {
        this.on = true;
    }

    @Override
    public void disable() {
        this.on = false;
    }

    @Override
    public int getVolume() {
        return this.volume;
    }

    @Override
    public void setVolume(int volume) {
        if (volume < 0) {
            this.volume = 0;
        } else if (volume > 100) {
            this.volume = 100;
        } else {
            this.volume = volume;
        }
    }

    @Override
    public int getChannel() {
        return this.channel;
    }

    @Override
    public void setChannel(int channel) {
        if (channel < 0) {
            this.channel = 0;
        } else if (channel > 100) {
            this.channel = 100;
        } else {
            this.channel = volume;
        }
    }
}

class Radio implements Device {
    boolean on;
    int volume;
    int channel;

    public Radio() {
        this.on = false;
        this.volume = 50;
        this.channel = 1000;
    }

    @Override
    public boolean isEnabled() {
        return this.on;
    }

    @Override
    public void enable() {
        this.on = true;
    }

    @Override
    public void disable() {
        this.on = false;
    }

    @Override
    public int getVolume() {
        return this.volume;
    }

    @Override
    public void setVolume(int volume) {
        if (volume < 0) {
            this.volume = 0;
        } else if (volume > 100) {
            this.volume = 100;
        } else {
            this.volume = volume;
        }
    }

    @Override
    public int getChannel() {
        return this.channel;
    }

    @Override
    public void setChannel(int channel) {
        if (channel < 0) {
            this.channel = 0;
        } else if (channel > 2000) {
            this.channel = 2000;
        } else {
            this.channel = volume;
        }
    }
}

class RemoteControl {
    protected Device device;

    public RemoteControl(Device device) {
        this.device = device;
    }

    public void togglePower() {
        if (this.device.isEnabled()) {
            this.device.disable();
        } else {
            this.device.enable();
        }
    }

    public void volumeDown() {
        this.device.setVolume(this.device.getVolume() - 10);
    }

    public void volumeUp() {
        this.device.setVolume(this.device.getVolume() + 10);
    }

    public void channelDown() {
        this.device.setChannel(this.device.getChannel() - 1);
    }

    public void channelUp() {
        this.device.setChannel(this.device.getChannel() + 1);
    }
}

class AdvancedRemoteControl extends RemoteControl {
    public AdvancedRemoteControl(Device device) {
        super(device);
    }

    public void mute() {
        this.device.setVolume(0);
    }
}