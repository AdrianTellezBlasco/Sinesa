package cine;

public class ProcesadorDePagos {

	private Cine cine;

	private int precioAsiento;

	public ProcesadorDePagos(Cine cine, int precioAsiento) {
		this.cine = cine;
		this.precioAsiento = precioAsiento;
	}

	// Si el cliente tiene más dinero o el necesario para una entrada
	// Entonces se efectua el pago
	public boolean procesarPago(String cuentaCliente, int fondosCliente) {
		if (fondosCliente >= this.precioAsiento) {
			this.cine.agregarRecaudacion(this.precioAsiento);
			return true;
		}
		return false;
	}
}
