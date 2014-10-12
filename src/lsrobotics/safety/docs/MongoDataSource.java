/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lsrobotics.safety.docs;

import com.thoughtworks.xstream.XStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
        
    }

    public void SaveLocal(Report r) {
        XStream serializer = new XStream(); //XML Serializer: saves objects to local
        serializer.alias("report", Report.class);
        String data = serializer.toXML(r);  //machine
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(r.name + ".xml")); //writes to the app directory for now
            writer.write(data);
            writer.close();
        } catch (IOException ex) {
            System.out.println("Could not open a file to write");
        }
    }
    
    public Report Download(String fileName) {
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
