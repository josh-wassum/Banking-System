import java.sql.*;

/**
** This class handles my Database connections.
 * As an extension of handling the connection
 * it also handles all database manipulations.
 **/
public class Connector {

    // This method handles connecting to the database.
    private static Connection get_db(){
      try {
          return DriverManager.getConnection(
                  "jdbc:mysql://localhost:3306/bank_data","root","DADhair72!"
          );
      } catch (Exception e) {
          System.out.println(e);
          return null;
      }
    }

    // Uses a SQL Prepared Statement to create a user.
    public static void createUser(String name, String email, String password) {

      PreparedStatement pstmt = null;
      String query = "INSERT INTO users(user_name, user_email, user_password)" + "VALUES(?, ?, ?)";

      try {
          Connection con = get_db();
          pstmt = con.prepareStatement(query);
          pstmt.setString(1, name);
          pstmt.setString(2, email);
          pstmt.setString(3, password);

          int status = pstmt.executeUpdate();

          if (status > 0) {
              System.out.println("Record inserted successfully");
          }
          con.close();
      } catch (Exception e) {
          System.out.println(e);
      }
    }

    // Uses a SQL Prepared statement to create an account.
    public static void createAccount(double balance, int userId) {

        PreparedStatement pstmt = null;
        String query = "INSERT INTO accounts(balance, user_id)" + "VALUES(?, ?)";

        try {
            Connection con = get_db();
            pstmt = con.prepareStatement(query);
            pstmt.setDouble(1, balance);
            pstmt.setInt(2, userId);


            int status = pstmt.executeUpdate();

            if (status > 0) {
                System.out.println("Record inserted successfully");
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Uses a SQL statement to find a user.
    public static ResultSet findUserByLogin(String email, String password){

      String query = "SELECT * FROM users WHERE user_email = " + "'" + email + "'" + " AND user_password = " + password;
      try {
          Connection con = get_db();

          Statement stmt = con.createStatement();

          ResultSet rs = stmt.executeQuery(query);
          rs.next();
          int count = rs.getInt(1);

          if (count > 0) {
              System.out.println("You are authenticated");
              return rs;
          } else {
              return null;
          }
      } catch (Exception e) {
          System.out.println(e);
      }
    return null;
    }

      // Uses a Statement to find a user account. Created using a join.
    public static ResultSet findAccountByLogin(String email, String password){

        String query = "SELECT users.*, accounts.account_number, accounts.balance FROM users" +
                " INNER JOIN accounts" +
                " ON accounts.user_id = users.user_id" +
                " HAVING users.user_email = "
                + "'" + email + "'"
                + " AND users.user_password = " + password;

        try {
            Connection con = get_db();

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            int count = rs.getInt(1);

            if (count > 0) {
                System.out.println("You are authenticated");
                return rs;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    // Uses a SQL prepared statement to edit the balance of an account.
    public static void editBalance(int accountId, double balance) {
          PreparedStatement pstmt = null;
          String query = "UPDATE accounts SET balance = ? WHERE account_number = ?";

          try {
              Connection con = get_db();
              pstmt = con.prepareStatement(query);
              pstmt.setDouble(1, balance);
              pstmt.setInt(2, accountId);

              int status = pstmt.executeUpdate();

              if (status > 0) {
                  System.out.println("Record updated successfully!");
              }
              con.close();
          } catch (Exception e){
              System.out.println(e);
          }
    }

    // Uses a SQL Prepare Statement to remove a user and an account
    public static void removeAccount(int userId){
        PreparedStatement pstmt = null;
        String query = "DELETE FROM users WHERE user_id = ?";

        try {
            Connection con = get_db();
            pstmt = con.prepareStatement(query);
            pstmt.setDouble(1, userId);

            int status = pstmt.executeUpdate();

            if (status > 0) {
                System.out.println("Records deleted successfully!");
            }
            con.close();
        } catch (Exception e){
            System.out.println(e);
        }
    }
}
