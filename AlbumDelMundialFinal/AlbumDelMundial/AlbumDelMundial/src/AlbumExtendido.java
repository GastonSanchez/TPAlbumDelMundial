
public class AlbumExtendido extends Album{
	
	private Figurita[] listaTop10;
	
	public AlbumExtendido(String[] paisesParticipantes) {
		super(paisesParticipantes);
	}

	@Override
	public boolean tieneCodigoPromocinal() {
		return false;
	}
}
