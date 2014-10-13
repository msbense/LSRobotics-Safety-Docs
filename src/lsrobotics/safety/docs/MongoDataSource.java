package lsrobotics.safety.docs;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoURI;
import com.thoughtworks.xstream.XStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author msbense
 */
class MongoDataSource implements IDataSource {  //MongoDB is a database 
                                                //application: mongodb.org
                                                //Perfect for storing safety docs
    public MongoDataSource(){
        
    }
    public void Upload(Report r) {
        try {
            Mongo client = new Mongo(new MongoURI("mongodb://root:LSCHSR5181@192.168.1.15/admin"));
            DB data = client.getDB("lsh");
            DBCollection col = data.getCollection("reports");
            BasicDBObject dbreport = new BasicDBObject("name", r.name);
            col.insert(dbreport);
        } catch (UnknownHostException ex) {
            System.out.println("MongoDataSource, Upload: Cannot connect to database");
        }

    }

    public void SaveLocal(Report r) {
        XStream serializer = new XStream();       //XML Serializer: saves objects to local machine
        serializer.alias("report", Report.class); 
        String data = serializer.toXML(r);  
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(r.name + ".xml")); //writes to the app directory for now
            writer.write(data);
            writer.close();
        } catch (IOException ex) {
            System.out.println("Could not open a file to write");
        }
    }
    
    public Report Download(String fileName) {
        
        try {
            Mongo client = new Mongo(new MongoURI("mongodb://root:LSCHSR5181@192.168.1.15/admin"));
        } catch (UnknownHostException ex) {
            System.out.println("MongoDataSource, Download: Cannot connect to database");
        }
        
        
        return new Report(); //todo
    }
    
    public Report LoadLocal(String fileName) {
        fileName += ".xml";
        XStream serializer = new XStream();
        serializer.alias("report", Report.class);
        Report r = null;
        try {
            Scanner scanner = new Scanner(new File(fileName));
            String xml = scanner.useDelimiter("\\Z").next(); //read the entire file
            r = (Report) serializer.fromXML(xml);
            return r;
        } catch (FileNotFoundException ex) {
            System.out.println("Could not load the file requested");
        }
        if (r != null) return r;
        else return new Report();
    }
    
}
