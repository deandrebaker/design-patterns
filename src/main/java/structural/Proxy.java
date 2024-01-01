package structural;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Proxy {
    public static void main(String[] args) {
        ThirdPartyYouTubeLib lib = new ThirdPartyYouTubeClass();
        ThirdPartyYouTubeLib proxy = new CachedYouTubeClass(lib);

        System.out.println("List videos...");
        for (String video : proxy.listVideos()) {
            System.out.println(video);
        }

        System.out.println("Get video 1...");
        System.out.println(proxy.getVideoInfo(1));

    }
}

interface ThirdPartyYouTubeLib {
    List<String> listVideos();

    String getVideoInfo(int id);

    void downloadVideo(int id);
}

class ThirdPartyYouTubeClass implements ThirdPartyYouTubeLib {
    final List<String> videos;

    public ThirdPartyYouTubeClass() {
        this.videos = new ArrayList<>();
        videos.add("Cats vs Dogs");
        videos.add("Terminator");
        videos.add("Rush Hour");
    }

    @Override
    public List<String> listVideos() {
        return videos;
    }

    @Override
    public String getVideoInfo(int id) {
        return videos.get(id);
    }

    @Override
    public void downloadVideo(int id) {
        System.out.printf("Downloading video %s%n", id);
    }
}

class CachedYouTubeClass implements ThirdPartyYouTubeLib {
    final ThirdPartyYouTubeLib service;
    List<String> listCache;
    HashMap<Integer, String> downloads;
    String videoCache;
    boolean needReset;

    public CachedYouTubeClass(ThirdPartyYouTubeLib service) {
        this.service = service;
        this.listCache = null;
        this.videoCache = null;
    }

    @Override
    public List<String> listVideos() {
        if (this.listCache == null || this.needReset) {
            this.listCache = this.service.listVideos();
        }
        return this.listCache;
    }

    @Override
    public String getVideoInfo(int id) {
        if (this.videoCache == null || this.needReset) {
            this.videoCache = this.service.getVideoInfo(id);
        }
        return this.videoCache;
    }

    @Override
    public void downloadVideo(int id) {
        if (!this.downloads.containsKey(id) || this.needReset) {
            this.service.downloadVideo(id);
            this.downloads.put(id, this.service.getVideoInfo(id));
        }
    }
}