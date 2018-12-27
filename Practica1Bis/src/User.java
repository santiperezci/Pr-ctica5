import java.io.FileReader;
import java.util.Properties;

public class User {
protected String name;
protected /*static*/  String email;
protected /*static*/ String password;

public String getName(){
	return name;
}

public  /*static*/ String getEmail(){
	return email;
}

public /*static*/ String getPassword(){
	return password;
}

public void setName(String name){
	this.name=name;
}

public void setEmail(String email){
	this.email=email;
}

public void setPassword(String password){
	this.password=password;
}

protected User (String Name, String Email, String Password){
	name=Name;
	email=Email;
	password=Password;
}
}
