package bruteForce;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Authentication {
    static Connection con = null;
    final static String url = "jdbc:mysql://localhost:3306/";;
    final static String db = "test";
    final static String driver = "com.mysql.jdbc.Driver";
    final static String dbuser = "root";
    final static String dbpass = "root";

    static int Auth(String value1,String value2){
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(url+db, dbuser, dbpass);
            Statement st = con.createStatement();
            System.out.println("SELECT * FROM login WHERE username = '"+value1+"'");
            ResultSet res = st.executeQuery("SELECT * FROM login WHERE username = '"+value1+"'");
            res.next();
            String uname = res.getString("username");
            String pwd = res.getString("password");
            int disbaled = res.getInt("disabled");
            int wrong_try = res.getInt("wrong_try");

            System.out.println(disbaled + " " + wrong_try);
            if ((value1.equals(uname) && value2.equals(pwd))&& disbaled!=1) {
                return 0;
            }
            else if(disbaled == 1){
                return 1;
            } else if (wrong_try>10){
                Class.forName(driver);
                con = DriverManager.getConnection(url+db, dbuser, dbpass);
                st = con.createStatement();
                st.executeUpdate("UPDATE login SET wrong_try = '"+0+"', disabled=1 where username = '"+ uname+"'");
                return 2;
            } else{
                Class.forName(driver);
                con = DriverManager.getConnection(url+db, dbuser, dbpass);
                st = con.createStatement();
                wrong_try++;
                st.executeUpdate("UPDATE login SET wrong_try = '"+ wrong_try +"' where username = '"+ uname+"'");
                return 2;
            }
        }
        catch(Exception e){
            System.out.println("Error : "+e.getMessage());
        }

        return 2;
    }
}
