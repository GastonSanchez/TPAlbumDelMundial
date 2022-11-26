import java.util.ArrayList;
import java.util.List;

public class Participante {

	private int dni;
	private String nombre;
	private String tipoAlbum;
	private ArrayList<Figurita> coleccionFiguritas;
	private ArrayList<Figurita> coleccionFiguritasRepetidas;
	private String premio;
	private Album album;

	public Participante(int dni, String nombre, String tipoAlbum) {
		this.dni = dni;
		this.nombre = nombre;
		this.tipoAlbum = tipoAlbum;
		this.album = elegirAlbum(tipoAlbum);
		this.premio = new String();
		this.coleccionFiguritas = new ArrayList<>();
		this.coleccionFiguritasRepetidas = new ArrayList<>();
	}

	private Album elegirAlbum(String tipoAlbum) {
		Fabrica f = new Fabrica();
		this.tipoAlbum = tipoAlbum;
		if (tipoAlbum == "Web") {
			return f.crearAlbumWeb();
		} else if (tipoAlbum == "Tradicional") {
			return f.crearAlbumTradicional();
		} else {
			return f.crearAlbumExtendido();
		}
	}

	public boolean tieneFiguritas() {
		if (coleccionFiguritas.isEmpty()) {
			return false;
		}
		return true;
	}

	public ArrayList<Figurita> consultarColeccionFiguritasRepetidas() {
		return coleccionFiguritasRepetidas;
	}

	public String consultarTipoAlbum() {
		return tipoAlbum;
	}

	public void asignarPremio(String premio) {
		this.premio = premio;
	}

	public boolean agregarFiguritaAColeccion(List<Figurita> sobre) {
			for (Figurita figu : sobre) {
				for (Figurita f2 : coleccionFiguritas) {
					if(f2.consultarNombreDelPais().equals(figu.consultarNombreDelPais()) && f2.consultarNumero().equals(figu.consultarNumero())) {
						figu.asignarRepetida();
					}
				}
			}
			for (Figurita figu : sobre) {
				if(!figu.consultarRepetida()) {
					coleccionFiguritas.add(figu);
				} else {
					coleccionFiguritas.add(figu);
					coleccionFiguritasRepetidas.add(figu);
				}
			}
			return true;
	}

	public boolean agregarFiguritaAColeccion(Figurita figurita) {
		try {
			coleccionFiguritas.add(figurita);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void canjearCodigoPromocional(List<Figurita> sobre) {
		album.canjearCodigoPromocional();
		agregarFiguritaAColeccion(sobre);
	}

	public Album consultarAlbum() {
		return album;
	}

	public ArrayList<String> pegarEnElAlbum() {
		ArrayList<String> pegadas = new ArrayList<>();
		pegadas = album.pegarFiguritas(this.coleccionFiguritas);
		return pegadas;
	}

	public void moverRepetidas() { //Mueve las figus repetidas de una coleccion a otra
		
		for (Figurita figu : coleccionFiguritas) {
			if (figu.consultarRepetida() && !coleccionFiguritasRepetidas.contains(figu)) {
				coleccionFiguritasRepetidas.add(figu);
			}
		}
		
	}
	
	public boolean quitarFiguritaDeColeccion(Figurita figurita) {
		if (this.coleccionFiguritas.contains(figurita)) {
			this.coleccionFiguritas.remove(figurita);
			return true;
		} else {
			return false;
		}
	}

	public void asignarFiguritaRepetida(Figurita figurita) {
		if (this.coleccionFiguritas.contains(figurita)) {
			figurita.asignarRepetida();
		}
	}

	public int obtenerCodigoUnico() {
		return this.album.getCodigo();

	}

	public boolean tieneCodigoPromocional() {

		return this.album.tieneCodigoPromocinal();
	}

	public String getNombre() {
		return nombre;
	}

	public String getPremio() {
		return premio;
	}

	@Override
	public String toString() {
		return "Nombre: " + nombre + ", DNI: " + dni;
	}

	public int buscarRepetida() {

		if(!coleccionFiguritasRepetidas.isEmpty()) {
			return coleccionFiguritasRepetidas.get(0).consultarValorBase();
		}
		return -1;
	}

	public Integer consultarCodigoSorteo() {
		return album.getNumSorteo();
	}

	public int getDni() {
		return dni;
	}

	public Figurita buscarFiguritaPorValor(int valor) {
		for (Figurita figu : coleccionFiguritasRepetidas) {
			if (figu.consultarValorBase() <= valor) {
				coleccionFiguritas.remove(figu);
				coleccionFiguritasRepetidas.remove(figu);
				return figu;
			}
		}
		return null;
	}

	public Figurita buscarFiguritaPorCodigo(int valor) {
		for (Figurita figu : coleccionFiguritasRepetidas) {
			if (figu.consultarValorBase() <= valor) {
				return figu;
			}
		}
		return null;
	}

	public boolean completoElAlbum() {
		
		return album.estaCompleto();
	}

	public boolean completoElPais(String nombrePais) {
		
		return album.completoElPais(nombrePais);
	}

}
