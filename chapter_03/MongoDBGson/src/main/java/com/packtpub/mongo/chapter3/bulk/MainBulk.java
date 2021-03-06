/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.packtpub.mongo.chapter3.bulk;

import com.mongodb.*;

public class MainBulk {

    public static void main(String args[]) {
        try {
            MongoClient mongoClient = new MongoClient(new ServerAddress("200.200.200.204", 49161) );
            // To connect to mongodb server
            DB db = mongoClient.getDB("sampledb");

            DBCollection collection = db.getCollection("javastuff");

            BulkWriteOperation builder = collection.initializeUnorderedBulkOperation();

            builder.insert(new BasicDBObject("item", "A1"));
            builder.insert(new BasicDBObject("item", "A2"));
            builder.insert(new BasicDBObject("item", "A3"));

            builder.find(new BasicDBObject("item", "A1")).updateOne(new BasicDBObject("$set", new BasicDBObject("A1", "AX")));

            BulkWriteResult result = builder.execute();

            System.out.println("Bulk Completed: Inserted documents " + result.getInsertedCount());
            System.out.println("Bulk Completed: Modified documents " + result.getModifiedCount());

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}
