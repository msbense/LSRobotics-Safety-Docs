
package lsrobotics.safety.docs;

/**
 *
 * @author msbense
 */                                 /*We do not have a storage method set in stone,
                                      so I created an interface to manage data 
                                      generally. 
                                      
                                      Feel free to change the interface so I know
                                      what would be useful to the front-end*/
public interface IDataSource {
    public void Upload(Report r); //upload a report to the internet/a database
    
    public Report Download(String fileName);
    
    public void SaveLocal(Report r); //save it locally; we probably won't have a
                                     //connection at competitions
    public Report LoadLocal(String fileName);
}

