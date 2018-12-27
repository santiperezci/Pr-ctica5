import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class Producto {
	//public static void main (String[] arg){
		public String nombreProducto ,categoria, subCategoria, prodPath;
		float precio, valoracion;
		int nUnidades;
public Producto(String nombreProd, String categoria, String subCategoria) {
	
	try(FileReader reader =  new FileReader(nombreProd)) {
		//System.out.println(nombreProd);
		prodPath=nombreProd;
        Properties properties = new Properties();
        properties.load(reader);
        this.categoria=categoria;
        this.subCategoria=subCategoria;
        nombreProducto = properties.getProperty("nombreProducto");
        //System.out.println(nombreProducto);
        precio =Float.valueOf( properties.getProperty("precio"));
        //System.out.println(precio);
        valoracion = Float.valueOf(properties.getProperty("valoracion"));
        nUnidades = Integer.valueOf(properties.getProperty("unidades"));
        //System.out.println(valoracion);
        reader.close();
        //System.out.println("Producto creado");
        
       }catch (Exception e) {
    	   System.out.println(e.getMessage());
       }
	/*catch(IOException ioe) {
		System.out.println(e.getMessage());
	}*/

	}
	public synchronized boolean descontarUnidades(int nU) {
		boolean exito=false;
		Properties properties = new Properties();
		String unidades=String.valueOf(this.nUnidades - nU);
		properties.setProperty("nombreProducto",nombreProducto) ;
		String prize=String.valueOf(precio);
		properties.setProperty("precio",prize) ;
		String valoration=String.valueOf(valoracion);
		properties.setProperty("valoracion",valoration) ;
		properties.setProperty("unidades",unidades) ;
		 try {
			 properties.store(new FileWriter(prodPath),"");
			 exito=true;
			
		 }
		 catch(IOException ioex) {
			 System.out.println(ioex.getMessage());
		 }
		return exito;
	}
}
