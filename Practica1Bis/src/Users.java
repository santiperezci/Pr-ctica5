import java.beans.Statement;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Users {
	private Connection conexion;
	private Statement sentencia;
	private  String controlador;
	private  String nombre_bd;
	private  String usuarioBD;
	private  String passwordBD;
	int numUsers;
	User [] nombreUsers;
	
	public Users() {
		//System.out.println("Construyendo usuarios");
		nombreUsers=new User[100];
		int i=0;
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			this.controlador="sun.jdbc.odbc.JdbcOdbcDriver";
			this.nombre_bd="Amazonia.accdb";
			this.usuarioBD="";
		    this.passwordBD="";
	    
		} 
		catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    try{
	        conexion=DriverManager.getConnection("jdbc:ucanaccess://"+this.nombre_bd,this.usuarioBD,this.passwordBD);
	    }catch (SQLException e){
	         e.printStackTrace();
	         //return false;
	    }
	    /*try {
	        this.sentencia=(Statement) this.conexion.createStatement(
	        ResultSet.TYPE_SCROLL_INSENSITIVE,
	        ResultSet.CONCUR_READ_ONLY);
	    }

	    catch (SQLException e) {
	        e.printStackTrace();

	    }*/
	    ResultSet rs;
	    String sql="SELECT * FROM Usuarios";
	    PreparedStatement stm;
	    try {
			stm=conexion.prepareStatement(sql);
			//stm.setString(1, "12345");
			rs=stm.executeQuery();
			while(rs.next()) {
				String nombre = rs.getString("Nombre"); 
				String mail = rs.getString("email"); ; 
				String pass = rs.getString("password"); ;
				nombreUsers[i]=new User(nombre,mail,pass);
				numUsers++;
				i++;
			}
			conexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	   /* try {
			FileReader fr = new FileReader("users.txt"); 
			BufferedReader br = new BufferedReader(fr);
			String usuario=br.readLine();
			while(usuario!=null) {
				//System.out.println(usuario);
				
				String[] parts = usuario.split(",");
				String nombre = parts[0]; 
				String mail = parts[1]; 
				String pass = parts[2];
				nombreUsers[i]=new User(nombre,mail,pass);
				numUsers++;
				usuario=br.readLine();
				i++;
			}
			br.close();
		}
		catch(IOException iox) {
			System.out.println(iox.getMessage());
		}*/
		
		/*nombreUsers[0]=new User("Javier", "javier@gmail.com", "javier123");
		nombreUsers[1]=new User("Santiago",	"santiperezci@gmail.com", "pass123");
		nombreUsers[2]=new User("Daniel", "tradeos@gmail.com", "daniel123");
		numUsers=3;*/
		
		
	}
	public boolean verificar(User usuario) {
	
		boolean existe=false;
		for(int i=0;i<numUsers;i++) {
			//
			
			if(usuario.name.equals(nombreUsers[i].name) && nombreUsers[i].email.equals(usuario.email) 
					&& nombreUsers[i].password.equals(usuario.password))  {
				
				existe=true;
				
				break;
			}
		}
		return existe;
	}
	public boolean addUsuario(String usuario, String email, String contraseña) {
		boolean added=false;
		User nuevoUser;
		nuevoUser=new User(usuario,email,contraseña);
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			this.controlador="sun.jdbc.odbc.JdbcOdbcDriver";
			this.nombre_bd="Amazonia.accdb";
			this.usuarioBD="";
		    this.passwordBD="";
	    
		} 
		catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    try{
	        conexion=DriverManager.getConnection("jdbc:ucanaccess://"+this.nombre_bd,this.usuarioBD,this.passwordBD);
	    }catch (SQLException e){
	         e.printStackTrace();
	         return false;
	    }
	    try {
			PreparedStatement stmt = conexion.prepareStatement("INSERT INTO Usuarios VALUES (?,?,?)");
			stmt.setString(1,usuario);
			stmt.setString(2,contraseña);
			stmt.setString(3,email);
			stmt.executeUpdate();
			conexion.close();
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	    
	    
		/*try {
			FileWriter fr=new FileWriter("users.txt",true);
			fr.write("\n" + usuario + "," + email + "," + contraseña);
			fr.flush();
			fr.close();
			nombreUsers[numUsers+1]=nuevoUser;
			numUsers++;
			added=true;
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}*/
		
		//return true;
	}
	
}
