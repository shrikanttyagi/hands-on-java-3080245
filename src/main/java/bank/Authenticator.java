package bank;
import javax.security.auth.login.LoginException;
import bank.

public class Authenticator {
public static Customer login(String username,String password) throws LoginException{
  Customer customer = DataSource.getCustomer(username);



}


}
