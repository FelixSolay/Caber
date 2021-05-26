import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Test;

public class CaberTest {

	@Test
	public void test_Tiros_Descalificados() {
		ArrayList<Lanzamiento> lanzamientos = new ArrayList<Lanzamiento>(3);
		lanzamientos.add(new Lanzamiento(1.0, -120.0));
		lanzamientos.add(new Lanzamiento(1.0, -120.0));
		lanzamientos.add(new Lanzamiento(1.0, -120.0));
		Concursante c = new Concursante(lanzamientos,9);
		c.calcularDistanciaTotal();
		c.calcularDiferenciaDeTiros();
		
		Assert.assertEquals(0.0, c.getDistanciaTotal(), 0.0);
		Assert.assertEquals(0.0, c.getDiferenciaEntreTiros(), 0.0);
	}
	
	public void test_concursante_descalificado() {
		ArrayList<Lanzamiento> lanzamientos = new ArrayList<Lanzamiento>(3);
		lanzamientos.add(new Lanzamiento(1.0, -120.0));
		lanzamientos.add(new Lanzamiento(1.0, 91.0));
		lanzamientos.add(new Lanzamiento(1.0, 123.0));
		Concursante cPerdedor = new Concursante(lanzamientos,9);
		
		LinkedList<Concursante> concursantes = new LinkedList<Concursante> ();
		concursantes.add(cPerdedor);
		
		Juez j = new Juez(concursantes);
		int[] vacio = new int[3];
		Assert.assertArrayEquals(vacio, j.calculaGanadorDistancia(concursantes));
		Assert.assertArrayEquals(vacio, j.calculaGanadorConsistencia(concursantes));
		
	}
	
	public void test_Ejemplo() {
		ArrayList<Lanzamiento> lanzamientos = new ArrayList<Lanzamiento>(3);
		lanzamientos.add(new Lanzamiento(1.49, 5.0));
		lanzamientos.add(new Lanzamiento(1.5, 3.0));
		lanzamientos.add(new Lanzamiento(1.51, -3.0));
		Concursante c1 = new Concursante(lanzamientos,1);
	
		lanzamientos.add(new Lanzamiento(2.1, 6.0));
		lanzamientos.add(new Lanzamiento(2.0, -6.0));
		lanzamientos.add(new Lanzamiento(1.0, 2.0));
		Concursante c2 = new Concursante(lanzamientos,2);
		
		lanzamientos.add(new Lanzamiento(2.3, -35.0));
		lanzamientos.add(new Lanzamiento(1.3, -92.0));
		lanzamientos.add(new Lanzamiento(1.0, 1.0));
		Concursante c3 = new Concursante(lanzamientos,3);
		
		LinkedList<Concursante> concursantes = new LinkedList<Concursante> ();
		concursantes.add(c1);
		concursantes.add(c2);
		concursantes.add(c3);
		
		Juez j = new Juez(concursantes);
		int[] ganaDistancia = {2,1,3};
		int[] ganaConsistencia = {1,2};
		Assert.assertArrayEquals(ganaDistancia, j.calculaGanadorDistancia(concursantes));
		Assert.assertArrayEquals(ganaConsistencia, j.calculaGanadorConsistencia(concursantes));
		
	}
	
	public void test_Tiros_Maxima_distancia() {
		ArrayList<Lanzamiento> lanzamientos = new ArrayList<Lanzamiento>(3);
		lanzamientos.add(new Lanzamiento(Double.MAX_VALUE, -30.0));
		lanzamientos.add(new Lanzamiento(Double.MAX_VALUE, -81.0));
		lanzamientos.add(new Lanzamiento(Double.MAX_VALUE, 10.0));
		Concursante c = new Concursante(lanzamientos,5);
		c.calcularDistanciaTotal();
		c.calcularDiferenciaDeTiros();
		
		Assert.assertEquals(Double.MAX_VALUE, c.getDistanciaTotal(), 0.0);
		Assert.assertEquals(Double.MAX_VALUE, c.getDiferenciaEntreTiros(), 0.0);
	}

	
	public void test_Probar_Angulo_Limite() {
		ArrayList<Lanzamiento> lanzamientos = new ArrayList<Lanzamiento>(3);
		lanzamientos.add(new Lanzamiento(1.0, 1.0)); // Va el valor total
		lanzamientos.add(new Lanzamiento(1.0, 45.0));// va el 80%
		lanzamientos.add(new Lanzamiento(1.0, 100.0));// no tiene que sumar nada
		Concursante c = new Concursante(lanzamientos,5);
		c.calcularDistanciaTotal();
		c.calcularDiferenciaDeTiros();
		
		Assert.assertEquals(1.0+0.8, c.getDistanciaTotal(), 0.0);
	}
}
