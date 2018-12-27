import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Registrar extends Application {
	
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		/*String saludo;
		saludo=ResourceBundle.getBundle("Etiquetas.properties").getString("etq_saludo");*/
		URL res=getClass().getClassLoader().getResource("registro.fxml");
		Parent root=FXMLLoader.load(res);
		Scene scene=new Scene(root);
		try {
		stage.setTitle(ResourceBundle.getBundle("Etiquetas").getString("etq_saludo"));
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		stage.setScene(scene);
		stage.show();	
	}
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}*/
	
}
