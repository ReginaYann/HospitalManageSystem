

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Jdbc {
    public static void main(String[] args) {
    	//hospital
        //user=root 
        //password=123 
        String URL = "jdbc:mysql://localhost/hospital?";
        String user = "root";
        String password = "ylj12138";
        String username = "yyt";
        String psw = "12138";
        String sql = "INSERT INTO Patient(username,password) VALUES(?,?)";
        
        try (Connection con = DriverManager.getConnection(URL,user,password);
        		PreparedStatement pst = con.prepareStatement(sql)){
            //Class.forName("com.mysql.cj.jdbc.Driver");
            pst.setString(1, username);
            pst.setString(2, psw);
            pst.executeUpdate();
            System.out.println("≤Â»Î≥…π¶");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}