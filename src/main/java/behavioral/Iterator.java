package behavioral;

import java.util.ArrayList;
import java.util.List;

public class Iterator {
    public static void main(String[] args) {
        SocialNetwork network = new Facebook();
        SocialSpammer spammer = new SocialSpammer();

        System.out.println("Spamming friends...");
        spammer.send(network.createFriendsIterator(), "how are you?");

        System.out.println("Spamming coworkers...");
        spammer.send(network.createCoworkersIterator(), "meeting in the conference room");
    }
}

class Profile {
    String name;
    String type;

    public Profile(String name, String type) {
        this.name = name;
        this.type = type;
    }
}

interface SocialNetwork {
    ProfileIterator createFriendsIterator();

    ProfileIterator createCoworkersIterator();
}

class Facebook implements SocialNetwork {
    List<Profile> people;

    public Facebook() {
        List<Profile> people = new ArrayList<>();
        people.add(new Profile("Tom", "coworker"));
        people.add(new Profile("Karin", "coworker"));
        people.add(new Profile("Bob", "friend"));
        people.add(new Profile("Sam", "coworker"));
        people.add(new Profile("Matt", "friend"));
        this.people = people;
    }

    @Override
    public ProfileIterator createFriendsIterator() {
        return new FacebookIterator(this, "friend");
    }

    @Override
    public ProfileIterator createCoworkersIterator() {
        return new FacebookIterator(this, "coworker");
    }
}

interface ProfileIterator {
    Profile getNext();

    boolean hasMore();
}

class FacebookIterator implements ProfileIterator {
    Facebook facebook;
    String type;
    int index;

    public FacebookIterator(Facebook facebook, String type) {
        this.facebook = facebook;
        this.type = type;
        this.index = 0;
        while (this.index < this.facebook.people.size()) {
            if (this.facebook.people.get(this.index).type.equals(this.type)) {
                break;
            }
            this.index += 1;
        }
    }

    @Override
    public Profile getNext() {
        Profile profile = this.facebook.people.get(this.index);
        this.index += 1;

        while (this.index < this.facebook.people.size()) {
            if (this.facebook.people.get(this.index).type.equals(this.type)) {
                break;
            }
            this.index += 1;
        }

        return profile;
    }

    @Override
    public boolean hasMore() {
        return this.index < this.facebook.people.size();
    }
}

class SocialSpammer {
    public void send(ProfileIterator iterator, String message) {
        while (iterator.hasMore()) {
            Profile profile = iterator.getNext();
            System.out.printf("Hello %s, %s%n", profile.name, message);
        }
    }
}