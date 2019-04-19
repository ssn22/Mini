package db.mongodb;

import java.text.ParseException;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;

// create tables for mongodb all pipelines
public class MongoDBTableCreation {
	//run as java application to create mongodb tables with index
	public static void main(String[] args) throws ParseException{
		MongoClient mongoClient = new MongoClient();
		MongoDatabase db = mongoClient.getDatabase(MongoDBUtil.DB_NAME);
		
		// remove old tables
		db.getCollection("users").drop();
		db.getCollection("items").drop();
		
		// create new tables, populate data and create index
		db.getCollection("users").insertOne(new Document().append("first_name", "John")
				.append("last_name", "Smith").append("password","3229c1097a0fd282d586be050").append("user_id","1111"));
		// make sure user_id is unique
		IndexOptions indexOptions = new IndexOptions().unique(true);
		
		//use 1 for ascending index, -1 for descending index. different to mysql, users table in mdb also has history
		db.getCollection("users").createIndex(new Document("user_id", 1),indexOptions);
		// items table also has categories
		db.getCollection("items").createIndex(new Document("item_id", 1),indexOptions);
		
		mongoClient.close();
		System.out.println("Import is done successfully");
	}
}
