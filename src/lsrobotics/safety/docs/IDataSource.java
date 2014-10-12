
package lsrobotics.safety.docs;

/**
 *
 * @author msbense
 */
public interface IDataSource {
    public void Upload(Report r); //upload a report to the internet/a database
    
    public Report Download();
    
    public void SaveLocal(Report r); //save it locally; we probably won't have a
                                     //connection at competitions
    public Report LoadLocal();
}

