
public class AlbumWeb extends Album {
	
	private Boolean CodigoPromocional;

	public AlbumWeb(String[] paisesParticipantes) {
		super(paisesParticipantes);
		CodigoPromocional = true;
	}
	
	public boolean tieneCodigoPromocinal() {
		return CodigoPromocional;
	}
	
	@Override
	public void canjearCodigoPromocional() {
		CodigoPromocional = false;
	}

}
