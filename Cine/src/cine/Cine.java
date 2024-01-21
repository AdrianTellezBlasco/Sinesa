package cine;

public class Cine {

	private Asiento[][] asientos;
	private int recaudacionTotal;
	private String cuentaCine;

	public Cine(int filas, int columnas, String cuentaCine) {
		this.asientos = inicializarAsientos(filas, columnas);
		this.recaudacionTotal = 0;
		this.cuentaCine = cuentaCine;
	}

	public Asiento[][] getAsientos() {
		return asientos;
	}

	public void setAsientos(Asiento[][] asientos) {
		this.asientos = asientos;
	}

	public int getRecaudacionTotal() {
		return recaudacionTotal;
	}

	public void setRecaudacionTotal(int recaudacionTotal) {
		this.recaudacionTotal = recaudacionTotal;
	}

	public String getCuentaCine() {
		return cuentaCine;
	}

	public void setCuentaCine(String cuentaCine) {
		this.cuentaCine = cuentaCine;
	}

	public Asiento[][] inicializarAsientos(int filas, int columnas) {

		Asiento[][] asientos = new Asiento[filas][columnas];

		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				asientos[i][j] = new Asiento(i + 1, j + 1);
			}
		}
		return asientos;
	}

	public int getNumFilas() {
		return this.asientos.length;
	}

	public int getNumColumnas() {
		return this.asientos[0].length;
	}

	public boolean verificarDisponibilidad(int fila, int columna) {
		if (this.asientos[fila][columna].isOcupado()) {
			return false;
		}
		return true;
	}

	public void reservarAsiento(int fila, int columna) {
		this.asientos[fila][columna].setOcupado(true);
	}

	public boolean estaLleno() {

		for (int i = 0; i < asientos.length; i++) {
			for (int j = 0; j < asientos[0].length; j++) {
				if (!this.asientos[i][j].isOcupado()) {
					return false;
				}
			}
		}
		return true;
	}

	public void agregarRecaudacion(int recaudacion) {
		this.recaudacionTotal += recaudacion;
	}

	public void reiniciarAsientos(int filas, int columnas) {
		this.asientos = inicializarAsientos(filas, columnas);
	}

}
