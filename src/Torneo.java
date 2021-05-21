import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Torneo {
	private LinkedList<Concursante> concursantes;
	private String nArch;
	

	public boolean leerArchivo() {
		Scanner arch = null;

		try {
			arch = new Scanner(new File(nArch));
			int cantConcursantes = Integer.parseInt(arch.nextLine()); //La priemara linea del archivo nos dice cuantos participantes son
			for (int i = 0; i < cantConcursantes; i++) { // por cada participante que tome los lanzamientos
				ArrayList<Lanzamiento> actual = new ArrayList<Lanzamiento>(3);//Creo un arrList de 3 por concursante
				for(int tiro = 0; tiro<3; tiro++) { //Todos los participantes tienen 3 lanzamientos
					String datos[] = (arch.nextLine()).split("	"); //Tomo la línea y la corto en 2 para parsear los datos
					Lanzamiento l = new Lanzamiento(Double.parseDouble(datos[0]), Double.parseDouble(datos[1]));// datos[0] = distancia | datos[1] = angulo
					actual.add(l); //Agrego el lanzamiento al array de lanzamientos creado
				}
				concursantes.add(new Concursante(actual,i+1));//Uso el ctor de concursante con el array de lanzamientos
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			arch.close();
		}
		return true;
	}
	
	
	public Torneo(String nArch) {
		super();
		this.concursantes = new LinkedList<Concursante>();
		this.nArch = nArch;
	}


	public LinkedList<Concursante> getConcursantes() {
		return concursantes;
	}


	public boolean archivoSalida(String nombreArchivoSalida) {
		FileWriter archivo = null;
		PrintWriter pw = null;

		try {
			archivo = new FileWriter(nombreArchivoSalida);
			pw = new PrintWriter(archivo);
			Juez j = new Juez(concursantes);
			int ganadoresDistancia[] = j.calculaGanadorDistancia(concursantes);
			int ganadoresConsistencia[] = j.calculaGanadorConsistencia(concursantes);
			for (int i : ganadoresConsistencia) {
				if(i != 0)
					pw.print(i + " ");
			}
			pw.print( "\n");
			for (int i : ganadoresDistancia) {
				pw.print(i + " ");
			}
			pw.print( "\n");
			//
			//Aca va lo que tenemos que hacer
			//
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (archivo != null) {
				try {
					archivo.close();
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}
			}
		}
		return true;
	}	
}
