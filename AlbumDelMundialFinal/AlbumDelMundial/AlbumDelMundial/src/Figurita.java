import java.util.Objects;

public class Figurita {

	private Integer numero;
	private Integer valorBase;
	private String nombreDelPais;
	private double valorFinal;
	private boolean pegada;
	private boolean repetida;
	
	
	
	

	public Figurita(Integer numero,String nombreDelPais, Integer valorBase, double valorFinal) {
		super();
		this.numero = numero;
		this.valorBase = valorBase;
		this.nombreDelPais = nombreDelPais;
		this.valorFinal = valorFinal;
		pegada = false;
	}
	public Figurita(String nombreDelPais, Integer valorBase, double valorFinal) {
		this.valorBase = valorBase;
		this.nombreDelPais = nombreDelPais;
		this.valorFinal = valorFinal;
		pegada = false;
	}

	public boolean estaPegada() {
		return pegada;
	}
	
	public double obtenerValorFinal() {
		return valorFinal;
	}
	
	public void marcarComoPegada() {
		this.pegada=true;
	}
	
	public void registrarValorFinal(double valor) {
		valorFinal=valor;
	}
	
	public int consultarValorBase() {
		return valorBase;
	}
	
	public boolean consultarRepetida() {
		return repetida;
	}
	
	public void asignarRepetida() {
		repetida=true;
	}
	
	public void quitarReperida() {
		repetida=false;
	}

	public Integer consultarNumero() {
		return numero;
	}


	public String consultarNombreDelPais() {
		return nombreDelPais;
	}

	public boolean estaRepetida() {
		return repetida;
	}	
}
