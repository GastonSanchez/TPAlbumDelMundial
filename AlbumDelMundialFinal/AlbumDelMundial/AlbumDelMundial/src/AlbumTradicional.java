
public class AlbumTradicional extends Album{

	
	private Integer numSorteo;

	public AlbumTradicional(String[] paisesParticipantes, Integer numSorteo) {
		super(paisesParticipantes);
		this.numSorteo = numSorteo;
	}

	@Override
	public boolean tieneCodigoPromocinal() {
		return false;
	}
	@Override
	public int getNumSorteo() {
		return numSorteo;
	}
	
	
}
