import java.io.FileReader;
import java.util.Properties;

public class ProductoOferta extends Producto {
	int descuento;
	private float precioFinal;
	public ProductoOferta(String nombreProd, String categoria, String subCategoria) {
		super(nombreProd, categoria, subCategoria);
		try(FileReader reader =  new FileReader(nombreProd)) {
			//System.out.println(nombreProd);
	        Properties properties = new Properties();
	        properties.load(reader);
	        descuento = Integer.valueOf(properties.getProperty("descuento"));
	        float d=(float)descuento/100;
	        precioFinal=this.precio -( this.precio *d);
	        //System.out.println(valoracion);
	        reader.close();
		}
		catch (Exception e) {
	    	   System.out.println(e.getMessage());
	       }
	}
	/*public ProductoOferta(String nombreProd, String categoria, String subCategoria, int descuento) {
		super(nombreProd, categoria, subCategoria);
		this.descuento=descuento;
		precioFinal=this.precio - this.precio * (descuento / 100);
	}*/

	public float getPrecioFinal() {
		
		return precioFinal;
	}
	
}
