import java.util.ArrayList;

public class Concursante implements Comparable<Concursante>{
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

	public Concursante(ArrayList<Lanzamiento> lanzamientos,int id) {
		this.lanzamientos = lanzamientos;
		this.descalificado = false;
		this.distanciaTotal = 0;
		this.diferenciaEntreTiros = 0;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void calcularDistanciaTotal() {
		for (Lanzamiento lanzamiento : lanzamientos) {
			if (Math.abs(lanzamiento.getAngulo()) > 90) {
				descalificado = true;
			} else if (Math.abs(lanzamiento.getAngulo()) > 30) {
				distanciaTotal += (lanzamiento.getDistancia() * 0.8);
			} else {
				distanciaTotal += lanzamiento.getDistancia();
			}
		}
	}

	public void calcularDiferenciaDeTiros() {
		diferenciaEntreTiros += Math.abs(lanzamientos.get(0).getDistancia() - lanzamientos.get(1).getDistancia());
		diferenciaEntreTiros += Math.abs(lanzamientos.get(1).getDistancia() - lanzamientos.get(2).getDistancia());
		diferenciaEntreTiros += Math.abs(lanzamientos.get(2).getDistancia() - lanzamientos.get(0).getDistancia());
	}

	@Override
	public int compareTo(Concursante o) {
		if(this.getDistanciaTotal() == o.getDistanciaTotal())
			return 0;
		else if (this.getDistanciaTotal() > o.getDistanciaTotal())
			return 1;
		else
			return -1;
	}


}
