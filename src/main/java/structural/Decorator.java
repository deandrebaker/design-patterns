package structural;

import java.io.*;
import java.util.Base64;

public class Decorator {
    public static void main(String[] args) {
        String filename = "text.txt";
        File file = new File(filename);
        while (true) {
            try {
                if (file.createNewFile()) break;
            } catch (IOException e) {
                System.out.println("Could not create file");
                throw new RuntimeException(e);
            }

            if (!file.delete()) {
                System.out.println("Could not delete file");
                return;
            }
        }
        System.out.printf("File created: %s%n", file.getName());

        DataSource ds = new FileDataSource(filename);
        ds = new CaseSwitchDecorator(ds);
        ds = new EncryptionDecorator(ds);
        String content = "Lorem Ipsum";

        ds.writeData(content);
        ds.readData();

        if (file.delete()) {
            System.out.printf("File deleted: %s%n", file.getName());
        }
    }
}

interface DataSource {
    void writeData(String data);

    String readData();
}

class FileDataSource implements DataSource {
    final String filename;

    public FileDataSource(String filename) {
        this.filename = filename;
    }

    @Override
    public void writeData(String data) {
        try (FileWriter fw = new FileWriter(this.filename)) {
            System.out.println("Writing to file...");
            System.out.println(data);
            fw.write(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String readData() {
        String data;
        try (FileReader fr = new FileReader(this.filename); BufferedReader br = new BufferedReader(fr)) {
            System.out.println("Reading from file...");
            data = br.readLine();
            System.out.println(data);
        } catch (IOException e) {
            throw new RuntimeException();
        }
        return data;
    }
}

class DataSourceDecorator implements DataSource {
    protected DataSource wrappee;

    public DataSourceDecorator(DataSource wrappee) {
        this.wrappee = wrappee;
    }

    @Override
    public void writeData(String data) {
        this.wrappee.writeData(data);
    }

    @Override
    public String readData() {
        return this.wrappee.readData();
    }
}

class EncryptionDecorator extends DataSourceDecorator {
    public EncryptionDecorator(DataSource wrappee) {
        super(wrappee);
    }

    @Override
    public void writeData(String data) {
        System.out.println("Encrypting data...");
        System.out.println(data);
        String encryptedData = Base64.getEncoder().encodeToString(data.getBytes());
        this.wrappee.writeData(encryptedData);
    }

    @Override
    public String readData() {
        String encryptedData = this.wrappee.readData();
        System.out.println("Decrypting data...");
        String data = new String(Base64.getDecoder().decode(encryptedData));
        System.out.println(data);
        return data;
    }
}


class CaseSwitchDecorator extends DataSourceDecorator {
    public CaseSwitchDecorator(DataSource wrappee) {
        super(wrappee);
    }

    @Override
    public void writeData(String data) {
        System.out.println("Switching casing...");
        System.out.println(data);
        StringBuilder sb = new StringBuilder();
        for (char c : data.toCharArray()) {
            if (Character.isUpperCase(c)) {
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(Character.toUpperCase(c));
            }
        }
        this.wrappee.writeData(sb.toString());
    }

    @Override
    public String readData() {
        String encryptedData = this.wrappee.readData();
        System.out.println("Unswitching casing...");
        StringBuilder sb = new StringBuilder();
        for (char c : encryptedData.toCharArray()) {
            if (Character.isUpperCase(c)) {
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(Character.toUpperCase(c));
            }
        }
        String data = sb.toString();
        System.out.println(data);
        return data;
    }
}