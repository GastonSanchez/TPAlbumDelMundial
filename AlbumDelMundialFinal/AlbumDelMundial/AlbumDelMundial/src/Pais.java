import java.util.Iterator;

public class Pais {

	private Integer[] posiciones = new Integer[12];
	private String nombre;
	private Integer ranking;
	private Tupla<Integer, Figurita>[] listaDePosiciones;
	private boolean completado;

	public Pais(String nombre) {
		this.nombre = nombre;
		this.completado = false;
	}

	public boolean consultarCompletado() {
		return completado;
	}

//	public void marcarCompletado() {
//		boolean completo = true;
//		for (int i = 0; i < posiciones.length; i++) {
//			completo = completo && posiciones[i] == 1;
//		}
//		completado = completo;
//	}

	public String consultarNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer consultarRanking() {
		return ranking;
	}

	public void setRanking(Integer ranking) {
		this.ranking = ranking;
	}

	public void pegarFigurita(Figurita figurita) {
		posiciones[figurita.consultarNumero() - 1] = figurita.consultarNumero();
		verificarCompleto();
	}
	
	private void verificarCompleto() { //Despues de pegar una figurita verifica si el pais esta completo
		boolean completo = true;
		for(int i = 0; i< posiciones.length; i++) {
			if(posiciones[i] == null) {
				completo = false;
			}
		}
		this.completado = completo;
	}

	public boolean tieneEstaFigurita(Figurita figu) {
		if (nombre == figu.consultarNombreDelPais() && posiciones[figu.consultarNumero()-1] != null) {
			return true;
		}
		return false;
	}

	public boolean estaVacio() {
		boolean vacio = true;
		for (int i = 0; i < listaDePosiciones.length; i++) {
			vacio = vacio && listaDePosiciones[i] == null;
		}
		return vacio;
	}

}
