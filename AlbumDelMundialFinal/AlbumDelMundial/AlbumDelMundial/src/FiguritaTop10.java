
public class FiguritaTop10 extends Figurita{
	
	private String tipoBalon;
	private String sede;
	
	public FiguritaTop10(String paisSede, String paisFinalista, String tipoBalon,Integer valorBase,double valorFinal) {
		super(paisFinalista,valorBase,valorFinal);
		this.tipoBalon = tipoBalon;
		sede = paisSede;
	}
	
	public String consultarTipoBalon() {
		return tipoBalon;
	}
	

}
