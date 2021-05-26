import java.util.ArrayList;

public class Concursante implements Comparable<Concursante> {
	private ArrayList<Lanzamiento> lanzamientos = new ArrayList<Lanzamiento>(3);
	private boolean descalificado;
	private double distanciaTotal;
	private double diferenciaEntreTiros;
	private int id;

	public boolean isDescalificado() {
		return descalificado;
	}

	public double getDistanciaTotal() {
		this.calcularDistanciaTotal();
		return distanciaTotal;
	}

	public double getDiferenciaEntreTiros() {
		this.calcularDiferenciaDeTiros();
		return diferenciaEntreTiros;
	}

	public Concursante(ArrayList<Lanzamiento> lanzamientos, int id) {
		this.lanzamientos = lanzamientos;
		this.descalificado = false;
		this.distanciaTotal = 0;
		this.diferenciaEntreTiros = 0;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void calcularDistanciaTotal() {  // 360/12 = 30 cada hora son 30° - 
										    //entre 11 y 13 ... angulo <= |30°|
										    //entre 13 y 15  o 9 y 11 ...|30°| < angulo <= |90°|
											//Descalificado -> angulo >|90°|
		for (Lanzamiento lanzamiento : lanzamientos) {
			if (Math.abs(lanzamiento.getAngulo()) > 90) {//No suma a la distancia total
				descalificado = true;
			} else if (Math.abs(lanzamiento.getAngulo()) > 30) {//Suma un 80%
				distanciaTotal += (lanzamiento.getDistancia() * 0.8);
			} else {// suma un 100%
				distanciaTotal += lanzamiento.getDistancia();
			}
		}
	}

	public void calcularDiferenciaDeTiros() { // |l1-l2| + |l2-l3| + |l3 - l1|
		diferenciaEntreTiros += Math.abs(lanzamientos.get(0).getDistancia() - lanzamientos.get(1).getDistancia());
		diferenciaEntreTiros += Math.abs(lanzamientos.get(1).getDistancia() - lanzamientos.get(2).getDistancia());
		diferenciaEntreTiros += Math.abs(lanzamientos.get(2).getDistancia() - lanzamientos.get(0).getDistancia());
	}

	@Override
	public int compareTo(Concursante o) {
		if (this.getDistanciaTotal() == o.getDistanciaTotal())
			return 0;
		else if (this.getDistanciaTotal() > o.getDistanciaTotal())
			return 1;
		else
			return -1;
	}

}
