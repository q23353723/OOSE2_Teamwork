package main;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import model.Admin;
import model.Powerbank;
import model.User;
import org.bson.Document;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;

public class MongoDBImp implements DBImp {
    MongoClientURI uri;
    MongoClient mongoClient;
    MongoDatabase database;
    MongoCollection<Document> collection;
    MemberFactory factory;
    Powerbank powerbank;

    //連線
    public void connect() {
        uri = new MongoClientURI("mongodb+srv://admin:OOSE2Teamwork@cluster0.fc1ru.mongodb.net/Cluster0?retryWrites=true&w=majority");
        mongoClient = new MongoClient(uri);
        database = mongoClient.getDatabase("test");
    }

    //斷線
    public void disconnect(MongoCursor<Document> cursor) {
        try {
            cursor.close();
        } catch(Exception e) {}
    }

    //插入新的user到資料庫
    @Override
    public void insert(User user) {
        connect();
        collection = database.getCollection("test");
        Document doc = new Document("username", user.getAccount())
                .append("password", user.getPassword())
                .append("email", user.getEmail())
                .append("powerbankId", user.powerbankIdProperty().get());

        collection.insertOne(doc);
    }

    //從資料庫取得user
    @Override
    public User selectUser(String username) {
        connect();
        collection = database.getCollection("test");
        Document doc = collection.find(eq("username", username)).first();

        //用factory建立user物件
        factory = new UserFactory();
        User user = (User) factory.createMember();
        user.set(doc.getString("username"), doc.getString("password"), doc.getString("email"), doc.getString("powerbankId"));
        user.printDetail();

        return user;
    }

    //從資料庫取得admin
    @Override
    public Admin selectAdmin(String username) {
        connect();
        collection = database.getCollection("admin");
        Document doc = collection.find(eq("username", username)).first();

        //用factory建立admin物件
        factory = new AdminFactory();
        Admin admin = (Admin) factory.createMember();
        admin.set(doc.getString("username"), doc.getString("password"), doc.getString("email"));

        return admin;
    }

    //從資料庫中取得available的行動電源數量
    @Override
    public long countPowerbank() {
        connect();
        collection = database.getCollection("powerbank");
        return collection.countDocuments(eq("available", true));
    }

    //從資料庫中取得所有的行動電源
    @Override
    public ArrayList<Powerbank> getPowerbankList() {
        connect();
        ArrayList<Powerbank> list = new ArrayList<>();
        collection = database.getCollection("powerbank");
        MongoCursor<Document> cursor = collection.find().iterator();

        while (cursor.hasNext()) {
            Document doc = cursor.next();
            powerbank = PrototypeProvider.getInstance().getPowerbank();
            powerbank.setPowerbank(doc.getString("_id"), doc.getInteger("capacity"), doc.getBoolean("available"));
            list.add(powerbank);
        }
        disconnect(cursor);
        return list;
    }

    //從資料庫中取得第一筆available的行動電源
    @Override
    public Powerbank getPowerbank() {
        connect();
        collection = database.getCollection("powerbank");
        Document doc = collection.find(eq("available", true)).first();
        if(doc != null) {
            powerbank = PrototypeProvider.getInstance().getPowerbank();
            powerbank.setPowerbank(doc.getString("_id"), doc.getInteger("capacity"), doc.getBoolean("available"));
            return powerbank;
        }
        return null;
    }

    //從資料庫中取得行動電源
    @Override
    public Powerbank getPowerbank(String id) {
        connect();
        collection = database.getCollection("powerbank");
        Document doc = collection.find(eq("_id", id)).first();
        if(doc != null) {
            powerbank = PrototypeProvider.getInstance().getPowerbank();
            powerbank.setPowerbank(doc.getString("_id"), doc.getInteger("capacity"), doc.getBoolean("available"));
            return powerbank;
        }
        return null;
    }

    //更新資料到資料庫
    @Override
    public void update(User user, Powerbank powerbank) {
        connect();
        collection = database.getCollection("test");
        if(user.powerbankIdProperty().get().equals("")) {
            collection.updateOne(eq("username", user.getAccount()), Updates.set("powerbankId", ""));
        }
        else {
            collection.updateOne(eq("username", user.getAccount()), Updates.set("powerbankId", powerbank.getId()));
        }
        collection = database.getCollection("powerbank");
        collection.updateOne(eq("_id", powerbank.getId()), Updates.set("available", powerbank.getAvailable()));
    }
}
