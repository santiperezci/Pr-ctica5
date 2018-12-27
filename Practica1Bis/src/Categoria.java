import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Categoria {
	private /*static*/ String idCat,nombreCategoria;
	public subCategorias [] nombresSubCategorias;
	public int numSubCategorias;
	boolean wind=false;
	Hashtable<String, String> subCats;
	public Categoria() {
		numSubCategorias=0;
	}
	public Categoria(String nombre, String id, boolean window) {
		nombreCategoria=nombre;
		idCat=id;
		numSubCategorias=0;
		wind=window;
		subCats=new Hashtable<String, String>();
	}
	public /*static*/ String getNombreCat() {
		return nombreCategoria;
	}
	public Hashtable<String,String> getSubCategorias(){
		
		return subCats;
	}
	public /*static*/ String getIdCat() {
		return idCat;
	}
	public void setNombresSubCategorias(String [] nombresSCat) {
		nombresSubCategorias=new subCategorias[nombresSCat.length];
		numSubCategorias=nombresSCat.length;
		
		for (int i=0; i<nombresSCat.length;i++) {
			try {
				nombresSubCategorias[i]=new subCategorias(nombresSCat[i], nombreCategoria.substring(0, 4) +Integer.toString(i), nombreCategoria,wind) ;
				//nombresSubCategorias[i].setNombresProductos(nombresProds);
				if(i==0) {
					//A MODIFICAR
					if(wind==false) {
					System.out.println( nombresSubCategorias[i].getNombrePadre().toUpperCase());
					}
				}
				if(wind==false) {
				System.out.println(nombresSubCategorias[i].getNombreSubCat()+ " "+ nombresSubCategorias[i].getIdCat());
				
				}
				else {
					subCats.put( nombresSubCategorias[i].getNombreSubCat(),nombresSubCategorias[i].getIdCat());
				}
				nombresSubCategorias[i].setNombresProductos(nombresSubCategorias[i].getIdCat());
				nombresSubCategorias[i].setNombresProductosOferta(nombresSubCategorias[i].getIdCat());
			}
			catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
			
		}
		
	}
}
