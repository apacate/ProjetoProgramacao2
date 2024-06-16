
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;



public class ConexaoMysql {
    
    public Connection ConectaBD(){
        Connection conn =null;
        
        try {
            
            String url = "jdbc:mysql://localhost:3306/programacao2?user=root&password=password";
            conn=DriverManager.getConnection(url);
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "ConexaoMysql  "+erro.getMessage());
        }
        
        return conn;
    }
}
 

