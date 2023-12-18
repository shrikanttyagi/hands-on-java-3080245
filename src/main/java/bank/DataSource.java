package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class DataSource {
public static Connection connect(){
  String db_file = "jdbc:sqlite:resources/bank.db";
  Connection connection = null;
  try{
  connection = DriverManager.getConnection(db_file); 
  System.out.println("Connection Successfull"); 
  }
  catch(SQLException e){
  e.printStackTrace();}
  return connection;
  }

  public static Customer getCustomer(String username){
    String sql = "select * from customers where username = ?";
    Customer customer = null; 
    try(Connection connection = connect();
        PreparedStatement statement = connection.prepareStatement(sql))
       {
         statement.setString(1, username);
        try (ResultSet result = statement.executeQuery()){
          customer =  new Customer(result.getInt("id"), 
          result.getString("name"), result.getString("username"),
          result.getString("password"), result.getInt("account_id"));
        }
        catch(SQLException e){
          e.printStackTrace();
        };
      }catch(SQLException e){
     e.printStackTrace();
    }  
    return customer;
  }

  public static Account getAccount(int id ){
    String sql = "select * from accounts where id = ?";
    Account account = null;
   try(Connection connection = connect();
   PreparedStatement statement = connection.prepareStatement(sql))
   {
    statement.setInt(1, id);
    try (ResultSet result = statement.executeQuery())
    {
      account = new Account(result.getInt("id"),
      result.getString("type"),
      result.getDouble("balance"));
    }
    catch(SQLException e){
      e.printStackTrace();

    }
   } catch(SQLException e){
    e.printStackTrace();
   }
   return account;
  }




  public static void main (String[] args){
  Customer customer = getCustomer("telloy3x@bigcartel.com");
  //System.out.println(customer.getName());
  Account account = getAccount(customer.getAccountid());
  System.out.println(account.getBalance());
  }
}



