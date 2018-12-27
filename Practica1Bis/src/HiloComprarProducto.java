
public class HiloComprarProducto extends Thread{
	private Producto prod;
	int nuN;
	public HiloComprarProducto(Producto prod, int nuN) {
		this.prod=prod;
		this.nuN=nuN;
	}
	public void run(){
		prod.descontarUnidades(nuN);
	}
}
