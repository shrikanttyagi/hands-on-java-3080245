package bank;
import javax.security.auth.login.LoginException;

public class Authenticator {
public static Customer login(String username,String password) throws LoginException{
  Customer customer = DataSource.getCustomer(username);
  if (customer == null)
{
  throw new LoginException("user not found");
}

if (password.equals(customer.getPassword())){
    customer.setAuthenticated(true);
}
return customer;
}
public static void logout(String username){
  Customer customer = DataSource.getCustomer(username);
  customer.setAuthenticated(false);
}


}
