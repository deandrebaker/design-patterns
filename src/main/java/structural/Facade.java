package structural;

import java.util.Objects;

public class Facade {
    public static void main(String[] args) {
        VideoConverter converter = new VideoConverter();
        String convertedVideo = converter.convert("funny-cats-video-ogg", "mp4");
        System.out.println(convertedVideo);
    }
}

class VideoFile {
    public VideoFile(String filename) {
    }
}

class CompressionCodec {
}

class OggCompressionCodec extends CompressionCodec {
}

class MPEG4CompressionCodec extends CompressionCodec {
}

class CodecFactory {
    public CompressionCodec extract(VideoFile file) {
        return new CompressionCodec();
    }
}

class BitrateReader {
    public static String read(String filename, CompressionCodec codec) {
        return "Contents from " + filename;
    }

    public static String convert(String buffer, CompressionCodec codec) {
        return "Converted content";
    }
}

class AudioMixer {
    public String fix(String buffer) {
        return "Fixed audio";
    }
}

class VideoConverter {
    public String convert(String filename, String format) {
        VideoFile file = new VideoFile(filename);
        CompressionCodec sourceCodec = (new CodecFactory()).extract(file);
        CompressionCodec destinationCodec;

        if (Objects.equals(format, "mp4")) {
            destinationCodec = new MPEG4CompressionCodec();
        } else {
            destinationCodec = new OggCompressionCodec();
        }

        String buffer = BitrateReader.read(filename, sourceCodec);
        String result = BitrateReader.convert(buffer, destinationCodec);
        result = (new AudioMixer()).fix(result);

        return result;
    }
}