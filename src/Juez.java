import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class Juez implements Comparator<Concursante> {

	private int[] ganadoresDistancia;
	private int[] ganadoresConsistencia;

	public int[] getGanadoresDistancia() {
		return ganadoresDistancia;
	}

	public int[] getGanadoresConsistencia() {
		return ganadoresConsistencia;
	}

	public Juez(LinkedList<Concursante> concursantes) {
		this.ganadoresDistancia = calculaGanadorDistancia(concursantes);
		this.ganadoresConsistencia = calculaGanadorConsistencia(concursantes);
	}

	public int[] calculaGanadorDistancia(LinkedList<Concursante> concursantes) {
		int ganadores[] = new int[3];
		Comparator<Concursante> c = Collections.reverseOrder(); // Ordenar la lista sgun la distancia máxmia alcanzada
																// de mayor a menor para el podio de ganadores por
																// distancia
		Collections.sort(concursantes, c);
		for (int i = 0; i < 3; i++) {
			ganadores[i] = concursantes.get(i).getId();// Los 3 primeros elementos son los ganadoes, la lista queda
														// ordenada de mayor a menor distancia
		}

		return ganadores;
	}

	public int[] calculaGanadorConsistencia(LinkedList<Concursante> concursantes) {
		int ganadores[] = new int[3];
		double podio[] = { Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE };

		for (Concursante c : concursantes) {
			if (!c.isDescalificado()) {
				for (int i = 0; i < 3; i++) {
					if (c.getDiferenciaEntreTiros() < podio[i]) {
						// hacer shift desde i, pisando el último elemento
						shift(podio, ganadores, i);
						podio[i] = c.getDiferenciaEntreTiros();
						ganadores[i] = c.getId();
						break;
					}
				}
			}
		}
		return ganadores;
	}

	private static void shift(double[] vp, int[] vg, int posicionDesde) {
		for (int i = 2; i > posicionDesde; i--) {
			vp[i] = vp[i - 1];
			vg[i] = vg[i - 1];
		}
	}

	@Override
	public int compare(Concursante o1, Concursante o2) {
		if (o1.getDistanciaTotal() == o2.getDistanciaTotal())
			return 0;
		else if (o1.getDistanciaTotal() > o2.getDistanciaTotal())
			return 1;
		else
			return -1;
	}

}
