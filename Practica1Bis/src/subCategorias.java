import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class subCategorias implements Organization{
	private String idSubCat,nombreSubCategoria, nombrePadre;
	String [] nombresProductos, nombresProductosOferta;
	public Producto []  Productos;
	public ProductoOferta [] ProductosOferta;
	int numProductos=0, numProductosOferta=0;
	List<Producto> listProds;
	List<ProductoOferta> listProdsOferta;
	boolean wind=false;
	public subCategorias(String nombre, String id, String papi,boolean window) {
		Productos =new Producto[20];
		ProductosOferta=new ProductoOferta[5];
		nombreSubCategoria=nombre;
		idSubCat=id;
		nombrePadre=papi;
		listProds=new ArrayList<Producto>();
		listProdsOferta=new ArrayList<ProductoOferta>();
	}
	public String getNombreSubCat() {
		return nombreSubCategoria;
	}
	public String getIdCat() {
		return idSubCat;
	}
	public String getNombrePadre() {
		return nombrePadre;
	}
	public void setIdCat(String id) {
		idSubCat=id;
	}
	public void setNombresProductos(String idSubCat) {
		//System.arraycopy(nombresProds,0,nombresProductos,0,nombresProds.length);
		try {
		String path = new File(".").getCanonicalPath()+ "\\productos\\" +idSubCat;
		//System.out.println(path+ "\\productos\\" +idSubCat);
		File dir = new File(path );
		String[] ficheros = dir.list();
		int numFicheros=ficheros.length;
		if (ficheros == null) {
			//A MODIFICAR
			  System.out.println("No hay ficheros en el directorio especificado");
		}
		
		else { 
			  for (int i=0;i<numFicheros;i++) {
			    //System.out.println(ficheros[i]);
			    numProductos++;
			    Productos[i]=new Producto(path+ "\\" +ficheros[i],nombrePadre,nombreSubCategoria);
			   
			  }
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	public void setNombresProductosOferta(String idSubCat) {
		try {
			String path = new File(".").getCanonicalPath()+ "\\productosOferta\\" +idSubCat;
			//System.out.println(path+ "\\productos\\" +idSubCat);
			File dir = new File(path );
			String[] ficheros = dir.list();
			int numFicheros=ficheros.length;
			if (ficheros == null) {
				//A MODIFICAR	
				  System.out.println("No hay ficheros en el directorio especificado");
			}
			
			else { 
				  for (int i=0;i<numFicheros;i++) {
				    //System.out.println(ficheros[i]);
				    numProductosOferta++;
				    ProductosOferta[i]=new ProductoOferta(path+ "\\" +ficheros[i],nombrePadre,nombreSubCategoria);
				   
				  }
				}
			}
			catch(Exception ex) {
				System.out.println(ex.getMessage());
			}	
	}
	public void listar() {
		//A MODIFICAR?
		System.out.println("Lista de productos");
		System.out.println("Número de productos:" + numProductos);
		System.out.println(idSubCat);
		for(int i=0;i<numProductos;i++) {
			System.out.println("Nombre: " + Productos[i].nombreProducto);
			System.out.println("Precio: " + Productos[i].precio);
			System.out.println("Valoración :" + Productos[i].valoracion);
		}
	}
	public  List<Producto> getProductos() {
		
		listProds=new ArrayList<>(Arrays.asList(Productos));
		return listProds;
	}
public  List<ProductoOferta> getProductosOferta() {
		
		listProdsOferta=new ArrayList<>(Arrays.asList(ProductosOferta));
		return listProdsOferta;
	}
	public void listarProductosOferta() {
		//A MODIFICAR?
		System.out.println("Lista de productos en oferta");
		System.out.println("Número de productos en oferta:" + numProductosOferta);
		System.out.println(idSubCat);
		for(int i=0;i<numProductosOferta;i++) {
			System.out.println("Nombre: " + ProductosOferta[i].nombreProducto);
			System.out.println("Precio: " + ProductosOferta[i].precio);
			System.out.println("Valoración: " + ProductosOferta[i].valoracion);
			System.out.println("Descuento: " + ProductosOferta[i].descuento);
			System.out.println("Precio final: " + ProductosOferta[i].getPrecioFinal());
		}
	}

}