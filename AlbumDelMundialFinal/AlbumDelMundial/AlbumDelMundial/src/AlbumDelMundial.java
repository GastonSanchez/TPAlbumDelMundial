import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class AlbumDelMundial implements IAlbumDelMundial {

	private HashMap<Integer, Participante> listaDeParticipantes;
	private HashMap<Integer, Participante> listaDeParticipantesCompletos;
	Fabrica fab;
	Random r = new Random();
	private Tupla<Integer, Boolean> codigoGanadorSorteo;

	public AlbumDelMundial() {
		super();
		listaDeParticipantes = new HashMap<>();
		listaDeParticipantesCompletos = new HashMap<>();
		
		codigoGanadorSorteo = new Tupla<Integer, Boolean>(r.nextInt(10)+1, false);
		

	}

	@Override
	public int registrarParticipante(int dni, String nombre, String tipoAlbum) {
		if (listaDeParticipantes.containsKey(dni)) {
			throw new RuntimeException("El Participante ya esta registrado");
		} else if (tipoAlbum != "Web" && tipoAlbum != "Tradicional" && tipoAlbum != "Extendido") {
			throw new RuntimeException("El tipo de album seleccionado es incorrecto");
		} else {
			Participante p = new Participante(dni, nombre, tipoAlbum);
			listaDeParticipantes.put(dni, p);
			return p.obtenerCodigoUnico();
		}
	}

	@Override
	public void comprarFiguritas(int dni) {
		if (!listaDeParticipantes.containsKey(dni)) {
			throw new RuntimeException("El Participante no se encuentra registrado");
		} else {
			fab = new Fabrica();
			listaDeParticipantes.get(dni).agregarFiguritaAColeccion(fab.generarSobre(4));
			listaDeParticipantes.get(dni).moverRepetidas();
		}
	}

	@Override
	public void comprarFiguritasTop10(int dni) {
		if (!listaDeParticipantes.containsKey(dni)) {
			throw new RuntimeException("El Participante no se encuentra registrado");
		} else if (listaDeParticipantes.get(dni).consultarTipoAlbum() != "Extendido") {
			throw new RuntimeException(
					"El Participante no tiene el album correcto para comprar este tipo de figuritas");
		} else {
			fab = new Fabrica();
			listaDeParticipantes.get(dni).agregarFiguritaAColeccion(fab.generarSobreTop10(4));
		}
	}

	@Override
	public void comprarFiguritasConCodigoPromocional(int dni) {
		if (!listaDeParticipantes.containsKey(dni)) {
			throw new RuntimeException("El Participante no se encuentra registrado");
		} else if (!listaDeParticipantes.get(dni).tieneCodigoPromocional()) {
			throw new RuntimeException(
					"El Participante no tiene el album correcto para comprar este tipo de figuritas");
		} else {
			fab = new Fabrica();
			listaDeParticipantes.get(dni).canjearCodigoPromocional(fab.generarSobre(4));
			listaDeParticipantes.get(dni).moverRepetidas();
		}
	}

	@Override
	public List<String> pegarFiguritas(int dni) {
		if (!listaDeParticipantes.containsKey(dni)) {
			throw new RuntimeException("El Participante no se encuentra registrado");
		}
		ArrayList<String> pegadas = new ArrayList<>();
		pegadas = listaDeParticipantes.get(dni).pegarEnElAlbum();
		
		verificarAlbumCompleto(dni);
		
		return pegadas;
		/**
		 * Busca entre las figuritas del participante cuales aún no están en el album
		 * y las asocia. Devuelve una lista con las figuritas asociadas. De cada
		 * figurita se devuelve un string "$pais-$numeroJugador"
		 */
	}

	private void verificarAlbumCompleto(int dni) { //Si el participante completo el album lo agrega a la lista de completos
		if (listaDeParticipantes.get(dni).completoElAlbum()) {
			if(listaDeParticipantes.get(dni).consultarTipoAlbum() == "Tradicional") {
				listaDeParticipantes.get(dni).asignarPremio("Pelota");
			} else if (listaDeParticipantes.get(dni).consultarTipoAlbum() == "Extendido") {
				listaDeParticipantes.get(dni).asignarPremio("Pelota Y Viaje");
			} else {
				listaDeParticipantes.get(dni).asignarPremio("Camiseta oficial de la selecci�n");
			}
			
			listaDeParticipantesCompletos.put(dni, listaDeParticipantes.get(dni));
		}
	}
	
	
	@Override
	public boolean llenoAlbum(int dni) {
		if (!listaDeParticipantes.containsKey(dni)) {
			throw new RuntimeException("El Participante no se encuentra registrado");
		}
		return listaDeParticipantesCompletos.containsKey(dni);
		/**
		 * Verifica si el participante identificado por dni ya completó el album.
		 * Devuelve true si está completo, sino false. Este metodo debe resolverse en
		 * O(1) Si el participante no está registrado, se debe lanzar una excepción.
		 */
	}

	@Override
	public String aplicarSorteoInstantaneo(int dni) {
		if (!listaDeParticipantes.containsKey(dni)) {
			throw new RuntimeException("El Participante no se encuentra registrado");
		} else if (listaDeParticipantes.get(dni).consultarTipoAlbum() != "Tradicional") {
			throw new RuntimeException("El Participante no tiene codigo para el sorteo");
		} else if (codigoGanadorSorteo.getS2()) {
			throw new RuntimeException("El sorteo ya tiene un ganador");
		} else {
			if (listaDeParticipantes.get(dni).consultarCodigoSorteo().equals(codigoGanadorSorteo.getS1())) {
				String[] premios = fab.getPremiosInstantaneos();
				listaDeParticipantes.get(dni).asignarPremio(premios[r.nextInt(3)]);
				codigoGanadorSorteo.setS2(true);
				return listaDeParticipantes.get(dni).getPremio();
			} else {
				return "El participante no gano el sorteo";
			}
		}
	}

	@Override
	public int buscarFiguritaRepetida(int dni) {
		if (!listaDeParticipantes.containsKey(dni)) {
			throw new RuntimeException("El Participante no se encuentra registrado");
		} else {
			return listaDeParticipantes.get(dni).buscarRepetida();
		}
	}

	@Override
	public boolean intercambiar(int dni, int codFigurita) {
		if (!listaDeParticipantes.containsKey(dni)) {
			throw new RuntimeException("El Participante no se encuentra registrado");
		}
		Participante p1 = listaDeParticipantes.get(dni);
		for (Participante participante : listaDeParticipantes.values()) {
			if (participante.consultarTipoAlbum().equals(p1.consultarTipoAlbum())
					&& participante.getDni() != p1.getDni()) {
				Figurita figuParticipante = participante.buscarFiguritaPorValor(codFigurita);
				if (figuParticipante != null) {
					Figurita figup1 = p1.buscarFiguritaPorValor(codFigurita);
					figuParticipante.quitarReperida();
					figup1.quitarReperida();
					p1.agregarFiguritaAColeccion(figuParticipante);
					participante.agregarFiguritaAColeccion(figup1);
					return true;
				}
			}
		}
		return false;
		/* Dado el dni de un participante A y el codigo de una figurita, 
		 * 1)se busca entre los participantes con mismo tipo de album si alguno tiene repetida alguna
		 * figurita que le falte al participante A con un valor menor o igual al de la
		 * figurita a cambiar. 
		 * 2)En caso de encontrar alguno, realiza el intercambio y
		 * devuelve true. Si no se encuentra ninguna para cambiar, devuelve false.
		 * 
		 * Si el participante no es dueño de la figurita a
		 * cambiar, se debe lanzar una excepción.*/
	}

	@Override
	public boolean intercambiarUnaFiguritaRepetida(int dni) {
		if (!listaDeParticipantes.containsKey(dni)) {
			throw new RuntimeException("El Participante no se encuentra registrado");
		}
		Participante p1 = listaDeParticipantes.get(dni);
		if (!p1.consultarColeccionFiguritasRepetidas().isEmpty()) {
			return intercambiar(dni, buscarFiguritaRepetida(dni));
		}
		return false;
		 //Dado el dni de un participante, busca una figurita repetida e intenta
		 //intercambierla Devuelve true si pudo intercambiarla. Sino, devuelve false.
	}

	

	@Override
	public String darNombre(int dni) {
		if (!listaDeParticipantes.containsKey(dni)) {
			throw new RuntimeException("El Participante no se encuentra registrado");
		}
		return listaDeParticipantes.get(dni).getNombre();
		/**
		 * Dado el dni de un participante, se devuelve el nombre del mismo.
		 * 
		 * Si el participante no está registrado, se debe lanzar una excepción.
		 */
	}

	@Override
	public String darPremio(int dni) {
		Participante p1 = listaDeParticipantes.get(dni);
		if (!listaDeParticipantes.containsKey(dni)) {
			throw new RuntimeException("El Participante no se encuentra registrado");
		} else if (!listaDeParticipantesCompletos.containsKey(dni)) {
			throw new RuntimeException("El Participante no completo el album");
		}
		if (p1.consultarTipoAlbum().equals("Tradicional")) {
			p1.asignarPremio("Pelota");
		} else if (p1.consultarTipoAlbum().equals("Extendido")) {
			p1.asignarPremio("Pelota y Viaje");
		} else {
			p1.asignarPremio("Camiseta de la selección");
		}
		return p1.getPremio();
		/**
		 * Dado el dni de un participante, devuelve el premio correspondiente por llenar
		 * el album.
		 * 
		 * Si el participante no está registrado, se debe lanzar una excepcion. Si no
		 * tiene el album completo, se debe lanzar una excepcion.
		 */
	}

	@Override
	public String listadoDeGanadores() {
		StringBuilder ganadores = new StringBuilder();
		for (Participante participante : listaDeParticipantesCompletos.values()) {
			ganadores.append(participante.toString() + ", Premio: " + participante.getPremio() + ". ");
		}
		return ganadores.toString();
	}

	@Override
	public List<String> participantesQueCompletaronElPais(String nombrePais) {
		ArrayList<String> participantes = new ArrayList<>();
		for (Participante participante : listaDeParticipantes.values()) {
			if(participante.completoElPais(nombrePais)) {
				participantes.add(participante.toString() + ", Album: " + participante.consultarTipoAlbum());
			};
		}
		return participantes;
	}

	@Override
	public String toString() {
		StringBuilder info = new StringBuilder();
		info.append("Participaron del AlbumDelMundial: \n");
		for(Participante p : listaDeParticipantes.values()) {
			info.append(p.toString() + " con un album " + p.consultarTipoAlbum() +"\n");
		}
		return info.toString();
	}
	
	
}
