/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lsrobotics.safety.docs;

import com.thoughtworks.xstream.XStream;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MSB1x_000
 */
class MongoDataSource implements IDataSource {  //MongoDB is a database 
                                                //application: mongodb.org
                                                
                                                //Perfect for storing safety docs
    public void Upload(Report r) {
        
    }

    public void SaveLocal(Report r) {
        XStream serializer = new XStream(); //XML Serializer: saves objects to local
        String data = serializer.toXML(r);  //machine
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(r.name)); //writes to the app directory for now
            writer.write(data);
            writer.close();
        } catch (IOException ex) {
            System.out.println("Could not open a file to write");
        }
    }


    public Report Download() {
        return new Report(); //todo
    }
    
    public Report LoadLocal() {
        return new Report(); //todo
    }
    
}
