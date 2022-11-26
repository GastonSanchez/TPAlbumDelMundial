import java.util.ArrayList;

public abstract class Album {

	private static int codigoUnico = 1;
	private Integer codigo;
	private Pais[] listaDePaises;
	private boolean completado;

	
	public Album(String[] listaDePaises) {
		this.codigo = codigoUnico;
		this.listaDePaises= new Pais[32];
		for (int i = 0; i < listaDePaises.length; i++) {
			this.listaDePaises[i]= new Pais(listaDePaises[i]);
		}
		this.completado = false;
		aumentar();
	}
	
	private void aumentar () {//Para que el siguiente album que se cree tenga un codigo distinto
		codigoUnico ++;
	}

	public Pais[] consultarListaDePaises() {
		return listaDePaises;
	}

	public ArrayList<String> pegarFiguritas(ArrayList<Figurita> figuritas) {
		ArrayList<String> pegadas = new ArrayList<>();
		for (Figurita figu : figuritas) {
			if (!figu.consultarRepetida() && !figu.estaPegada()) {
				for (Pais pais : listaDePaises) {

					if (pais.consultarNombre() == figu.consultarNombreDelPais() && !pais.consultarCompletado() && !pais.tieneEstaFigurita(figu)) {
						pais.pegarFigurita(figu);
						figu.marcarComoPegada();
						pegadas.add(figu.consultarNombreDelPais() + ", " + figu.consultarNumero() + ". ");
					} else if (pais.tieneEstaFigurita(figu) && !figu.estaPegada()) {
						figu.asignarRepetida();
					}
				}
			}
		}
		return pegadas;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public abstract boolean tieneCodigoPromocinal();

	public int getNumSorteo() {
		return 0;
	}
	
	public void canjearCodigoPromocional() {
		
	}
	
	public boolean estaVacio() {
		boolean vacio=true;
		for (Pais pais : listaDePaises) {
			vacio= vacio && pais.estaVacio();
		}
		return vacio;
	}

	public boolean estaCompleto() { //consulta si cada pais esta completo, si alguno no lo esta devulve false
		this.completado = true;
		for(Pais pais : listaDePaises) {
			completado = completado && pais.consultarCompletado();
		}
		
		return completado;
	}

	public boolean completoElPais(String nombrePais) {
		for(Pais pais : listaDePaises) {
			if(pais.consultarNombre() == nombrePais && pais.consultarCompletado()) {
				return true;
			}
		}
		return false;
	}

}
