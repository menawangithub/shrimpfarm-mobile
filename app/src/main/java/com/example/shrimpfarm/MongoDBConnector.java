//package com.example.shrimpfarm;
//
//import com.mongodb.client.MongoClient;
//import com.mongodb.client.MongoClients;
//import com.mongodb.client.MongoDatabase;
//
//public class MongoDBConnector {
//
//  public static MongoDatabase connectToDB() {
//	String connectionString = "mongodb+srv://menawanproject:kowan123@cluster0/test?retryWrites=true&w=majority";
//	try (MongoClient mongoClient = MongoClients.create(connectionString)) {
//	  return mongoClient.getDatabase("test");
//	} catch (Exception e) {
//	  e.printStackTrace();
//	  return null;
//	}
//  }
//}