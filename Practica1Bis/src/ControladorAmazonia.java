
import javafx.scene.control.TextArea;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControladorAmazonia  {
	@FXML
	TextArea Alimentos;
	@FXML
	Pane mainPane;
	@FXML
	ListView listaElectronica, listaAlimentos, listaLibros, listaModa, listaProductos;
	@FXML
	Button registrarUsuario, btnComprar;
	@FXML
	Label lblProductos;
	//GridPane gp=(GridPane)mainPane;
	static User usuario;
	static Users usuarios;
	static boolean usuarioRegistrado=false;
	static Categorías amazonia;
	Hashtable<String, String> subCatsEl;
	Hashtable<String, String> subCatsAl;
	Hashtable<String, String> subCatsLibs;
	Hashtable<String, String> subCatsMod;
	String idSubCatActiva;
	List<Producto> listProds;
	@FXML
    public void initialize(){
		//Alimentos.setText("Colacao");
		/*Label label1 = new Label("Categoria 1");
		label1.setTranslateX(300);
		mainPane.getChildren().add(label1);*/
		usuarioRegistrado=false;
		//System.out.println( "Bienvenido a nuestra web, again");
		idSubCatActiva="";
		leerPropiedades();
		usuarios=new Users();
		if(usuarios.verificar(usuario)==false) {
			//System.out.println("Usuario no registrado");
			registrarUsuario.setVisible(true);
		}
		else {
			usuarioRegistrado=true;
			btnComprar.setDisable(false);
		}
		amazonia=new Categorías(true);
		//usuarios=new Users();
		//List<String> electro=new ArrayList<String>();
		//electro.add("uno");
		//electro.add("dos");
		//Collections.copy(electro,);
		subCatsEl=amazonia.nombresCategorias[2].getSubCategorias();
		subCatsAl=amazonia.nombresCategorias[0].getSubCategorias();
		subCatsLibs=amazonia.nombresCategorias[3].getSubCategorias();
		subCatsMod=amazonia.nombresCategorias[1].getSubCategorias();
		listaElectronica.getItems().addAll(subCatsEl.keySet());
		listaAlimentos.getItems().addAll(subCatsAl.keySet());
		listaLibros.getItems().addAll(subCatsLibs.keySet());
		listaModa.getItems().addAll(subCatsMod.keySet());
	}
	@FXML
	public void comprarProducto(ActionEvent event) {
		String producto=listaProductos.getSelectionModel().getSelectedItem().toString();
		if(!producto.equals("Productos en Oferta")) {
			Alimentos.setText("Ha comprado: " + producto );
			Producto pr=listProds.get( listaProductos.getSelectionModel().getSelectedIndex());
			HiloComprarProducto hcp=new HiloComprarProducto(pr,1);
			ExecutorService executorService = Executors.newCachedThreadPool();
			executorService.execute(hcp);	
			mostrarProductos(idSubCatActiva);
		}
	}
	@FXML
	  public void listarProductosEl(MouseEvent event) {
		//Alimentos.setText("hola");
		Alimentos.setText(listaElectronica.getSelectionModel().getSelectedItem().toString());
		lblProductos.setText("PRODUCTOS-Electrónica");
		String sc=listaElectronica.getSelectionModel().getSelectedItem().toString();
		String idSC=subCatsEl.get(sc);
		idSubCatActiva=idSC;
		mostrarProductos(idSC);
	}
	public void mostrarProductos(String idSC) {
		subCategorias subCt= amazonia.encontrarSubCategoria(idSC);
		//subCt.setNombresProductos(idSC);
		
		listProds=subCt.getProductos();
		List<ProductoOferta> listProdsOferta;
		listProdsOferta=subCt.getProductosOferta();
		listaProductos.getItems().clear();
		for (Producto prod: listProds){
			if(prod!=null){
				listaProductos.getItems().add("Nombre: " + prod.nombreProducto + " Precio: " + prod.precio + " Valoración: " + prod.valoracion 
						+ " Nº Unidades: " + prod.nUnidades);
			}
		}
		listaProductos.getItems().add("Productos en Oferta");
		for (Producto prod: listProdsOferta){
			if(prod!=null){
				listaProductos.getItems().add("Nombre: " + prod.nombreProducto + " Precio: " + prod.precio + " Valoración: " + prod.valoracion);
			}
		}
		//subCt.listar();
	}
	@FXML
	  public void listarProductosAl(MouseEvent event) {
		lblProductos.setText("PRODUCTOS-Alimentos");
		String sc=listaAlimentos.getSelectionModel().getSelectedItem().toString();
		String idSC=subCatsAl.get(sc);
		mostrarProductos(idSC);
		//subCt.listar();
	}
	
	@FXML
	  public void listarProductosLib(MouseEvent event) {
		//Alimentos.setText("hola");
		Alimentos.setText(listaLibros.getSelectionModel().getSelectedItem().toString());
		lblProductos.setText("PRODUCTOS-Libros");
		String sc=listaLibros.getSelectionModel().getSelectedItem().toString();
		String idSC=subCatsLibs.get(sc);
		mostrarProductos(idSC);
		}
	@FXML
	  public void listarProductosMod(MouseEvent event) {
		//Alimentos.setText("hola");
		Alimentos.setText(listaModa.getSelectionModel().getSelectedItem().toString());
		lblProductos.setText("PRODUCTOS-Moda");
		String sc=listaModa.getSelectionModel().getSelectedItem().toString();
		String idSC=subCatsMod.get(sc);
		mostrarProductos(idSC);
	
		
	}
	@FXML
		public void regUsuario(ActionEvent event) {
		 try {
			 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("registro.fxml"));
			 Parent root = fxmlLoader.load();
			 Stage stage = new Stage();
			 stage.initModality(Modality.APPLICATION_MODAL);
			 stage.setOpacity(1);
			 stage.setTitle("My New Stage Title");
			 stage.setScene(new Scene(root, 850,850));
			 stage.showAndWait();
			 }
			 catch(IOException ioe) {
				 ioe.printStackTrace();
			 }
	}
	@FXML
	public void botonizado(ActionEvent e) {
		
	}
	public static void leerPropiedades() {
		String email="", username="", password="";
	
		try(FileReader reader =  new FileReader("config")) {
	        Properties properties = new Properties();
	        properties.load(reader);
	        email = properties.getProperty("email");
	        username = properties.getProperty("username");
	        password = properties.getProperty("password");
		}
	        catch (Exception e) {
	       e.printStackTrace();
	       }
		
	        if(email!=null && username!=null && password!=null) {
	        	usuario=new User(username, email, password);
	        	/*@FXML
	        	try {
	        		FXMLLoader fxmlLoader = new FXMLLoader(ControladorAmazonia.class.getClass().getClassLoader().getResource("registro.fxml"));
		        	 Parent root = fxmlLoader.load();
		       		 Stage stage = new Stage();
		       		 stage.initModality(Modality.APPLICATION_MODAL);
		       		 stage.setOpacity(1);
		       		 stage.setTitle("My New Stage Title");
		       		 stage.setScene(new Scene(root, 850,850));
		       		 stage.showAndWait();
	        	}
	       		 catch(IOException ioe) {
	       			 ioe.printStackTrace();
	       		 
	        	System.out.println(usuario.email);
		        System.out.println(usuario.name);
		        System.out.println(usuario.password);
	       		 }*/
	        }
	        
	}

}