package cine;

public class GestorDeReservas {

	private Cine cine;
	private ProcesadorDePagos procesadorDePagos;

	public GestorDeReservas(Cine cine, ProcesadorDePagos procesadorDePagos) {
		this.cine = cine;
		this.procesadorDePagos = procesadorDePagos;
	}

	public synchronized void intentarReservar(Cliente cliente, int filaDeseada, int columnaDeseada) {
		System.out.println("El cliente " + cliente.getNombre() + " " + cliente.getApellidos()
				+ " quiere reservar el asiento " + (columnaDeseada + 1) + " de la fila " + (filaDeseada + 1));

		if (this.cine.verificarDisponibilidad(filaDeseada, columnaDeseada)) {
			System.out.println("Ese asiento está disponible. Se va a proceder con el pago.");
			procesarReserva(cliente, filaDeseada, columnaDeseada);

		} else {
			System.out.println("Ese asiento no está disponible, se va a buscar uno nuevo según su cercanía... ");
			int intentosColumna = 0;
			int nuevaFilaDeseada = filaDeseada;
			int nuevaColumnaDeseada = columnaDeseada;

			/*
			 * Se busca elegir un asiento próximo en esa misma fila, viendo la
			 * disponibilidad en cada uno de los asientos. Se van viendo los asientos hasta
			 * llegar al extremo derecho. Si todavía no se ha encontrado asiento, se empieza
			 * a buscar desde el extremo izquierdo hasta llegar al asiento donde se empezó a
			 * buscar Si no se encuentra se busca asiento en una fila más alta, hasta llegar
			 * a arriba del todo que se busca entonces a las más cercanas a la pantalla
			 */

			while ((!buscarAsientoAlternativo(nuevaFilaDeseada, nuevaColumnaDeseada))) {

				nuevaColumnaDeseada++;
				intentosColumna++;
				// Si no se encuentra ningún asiento disponible en esa fila pasa a otra
				if (intentosColumna == this.cine.getNumColumnas()) {
					nuevaFilaDeseada++;
					intentosColumna = 0;
				}

				if (nuevaFilaDeseada == this.cine.getNumFilas()) {
					nuevaFilaDeseada = 0;
				}

				if (nuevaColumnaDeseada == this.cine.getNumColumnas()) {
					nuevaColumnaDeseada = 0;
				}

			}

			System.out.println("El asiento " + (nuevaColumnaDeseada + 1) + " de la fila " + (nuevaFilaDeseada + 1)
					+ " está disponible, se va a proceder a realizar el pago.");
			procesarReserva(cliente, nuevaFilaDeseada, nuevaColumnaDeseada);

		}

	}

	public boolean buscarAsientoAlternativo(int filaDeseada, int columnaDeseada) {

		if (this.cine.verificarDisponibilidad(filaDeseada, columnaDeseada)) {
			return true;
		} else {
			return false;
		}
	}

	public void procesarReserva(Cliente cliente, int fila, int columna) {
		System.out.println("El cliente quiere reservar el asiento mediante la cuenta " + cliente.getCuentaBancaria()
				+ " asociada al correo electrónico " + cliente.getCorreo());
		if (this.procesadorDePagos.procesarPago(cliente.getCuentaBancaria(), cliente.getFondos())) {
			cine.reservarAsiento(fila, columna);
			System.out.println("El cliente " + cliente.getNombre() + " " + cliente.getApellidos()
					+ " ha conseguido reservar el asiento");
		} else {
			System.out.println("El cliente " + cliente.getNombre() + " " + cliente.getApellidos()
					+ " no ha conseguido reservar el asiento. Solo tenía " + cliente.getFondos() + "€");
		}

	}

	public Cine getCine() {
		return cine;
	}

	public void setCine(Cine cine) {
		this.cine = cine;
	}

	public ProcesadorDePagos getProcesadorDePagos() {
		return procesadorDePagos;
	}

	public void setProcesadorDePagos(ProcesadorDePagos procesadorDePagos) {
		this.procesadorDePagos = procesadorDePagos;
	}

}
