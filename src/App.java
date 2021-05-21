
public class App {

	public static void main(String[] args) {
		Torneo caber = new Torneo("archivo.in");
		if(caber.leerArchivo())
			System.out.println("Archivo leido correctamente.");
		if(caber.archivoSalida("archivo.out"))
			System.out.println("Archivo creado correctamente.");

	}

}
