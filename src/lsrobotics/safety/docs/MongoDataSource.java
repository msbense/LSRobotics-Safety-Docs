package lsrobotics.safety.docs;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
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
class MongoDataSource implements IDataSource {  
                                                
    //MongoDB is a database application: mongodb.org
    //Perfect for storing safety docs
    
    //Behavior: 
    //    Upload takes a report, uploads it to MongoDB;
    //    Download Takes a name, an retrieves it from the database              
    //    SaveLocal serializes a report into XML, and saves it to the app directory
    //    LoadLocal takes a filename, loads an xml file, and returns it as a report
    
    private String URI;
    private String username;
    private String password;
    
    public MongoDataSource(String ip, String username, String password){
        URI = "mongodb://" + username + ":" + password + "@" + ip + "/admin";
        this.username = username;
        this.password = password;
    }
    public void Upload(Report r) {
        try {
            Mongo client = new Mongo(new MongoURI(URI));
            DB adminDatabase = client.getDB("admin");
            adminDatabase.authenticate(username, password.toCharArray());
            DB data = client.getDB("lsh");
            DBCollection col = data.getCollection("reports");
            BasicDBObject dbreport = new BasicDBObject("name", r.name);
            col.insert(dbreport);
        } catch (UnknownHostException ex) {
            System.out.println("MongoDataSource, Upload: Cannot connect to database");
        }

    }

    public Report Download(String fileName) {
        Report r = null;
        try {
            Mongo client = new Mongo(new MongoURI(URI));
            DB adminDatabase = client.getDB("admin");
            adminDatabase.authenticate(username, password.toCharArray());
            DB data = client.getDB("lsh");
            DBCollection col = data.getCollection("reports");
            DBCursor reportCursor = col.find(new BasicDBObject("name", fileName));
            DBObject dbreport = reportCursor.next();
            r = new Report();
            r.name = (String) dbreport.get("name");
        } catch (UnknownHostException ex) {
            System.out.println("MongoDataSource, Download: Cannot connect to database");
        }
        
        if (r != null) 
            return r;
        else 
            return new Report();
    }
    
    public void SaveLocal(Report r) {
        XStream serializer = new XStream();       //XML Serializer: saves objects to local machine
        serializer.alias("report", Report.class); 
        String data = serializer.toXML(r);
        System.out.println(data);
        try {
            System.out.println("Opening writer...");
            BufferedWriter writer = new BufferedWriter(new FileWriter(r.name + ".xml")); //writes to the app directory for now
            System.out.println("Writing data...");
            writer.write(data);
            System.out.println("Closing...");
            writer.close();
        } catch (IOException ex) {
            System.out.println("Could not open a file to write");
        }
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
