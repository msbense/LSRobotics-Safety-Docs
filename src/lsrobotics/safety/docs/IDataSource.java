
package lsrobotics.safety.docs;

/**
 *
 * @author msbense
 */                                 /*We do not have a storage method set in stone
                                      but generally we will be working with these
                                      four operations; the actual object can change*/
public interface IDataSource {
    public void Upload(Report r); //upload a report to the internet/a database
    
    public Report Download();
    
    public void SaveLocal(Report r); //save it locally; we probably won't have a
                                     //connection at competitions
    public Report LoadLocal();
}

