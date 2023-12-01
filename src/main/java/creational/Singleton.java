package creational;

import java.util.ArrayList;
import java.util.List;

public class Singleton {
    public static void main(String[] args) {
        Database foo = Database.getInstance();
        foo.query("select...");
        foo.query("Insert");
        Database bar = Database.getInstance();
        bar.history();
    }
}

class Database {
    private static Database instance;
    List<String> queries;

    private Database() {
        this.queries = new ArrayList<>();
    }

    public static Database getInstance() {
        if (Database.instance == null) {
            Database.instance = new Database();
        }

        return Database.instance;
    }

    public void query(String sql) {
        this.queries.add(sql);
        System.out.println("Executing query:\n" + sql + "\n");
    }

    public void history() {
        System.out.println("History:");
        for (String query : this.queries) {
            System.out.println(query);
        }
        System.out.println();
    }
}