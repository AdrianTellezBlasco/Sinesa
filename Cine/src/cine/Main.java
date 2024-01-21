package cine;

public class Main {

	public static void main(String[] args) {

		// Las filas del cine
		int filas = 10;
		// Los asientos que hay en cada fila
		int columnas = 10;
		int dineroEntrada = 15;
		// El dinero máximo con el que pueden aparecer los clientes
		int dineroCliente = 50;

		Cine cine = new Cine(filas, columnas, "Sinesa");
		ProcesadorDePagos procesadorDePagos = new ProcesadorDePagos(cine, dineroEntrada);
		GestorDeReservas gestorDeReservas = new GestorDeReservas(cine, procesadorDePagos);
		Visualizador visualizador = new Visualizador(cine);

		Thread th = new Thread(visualizador);
		th.start();

		// Hacemos que el cine siempre esté en funcionamiento
		while (true) {
			try {
				
				Cliente cliente = new Cliente(gestorDeReservas, cine, dineroCliente);
				th = new Thread(cliente);
				th.start();

				// Simulamos que cada cierto tiempo viene un cliente
				Thread.sleep(1500);

				// Cuando el cine se llene, lo vaciamos y volvemos a empezar
				if (cine.estaLleno()) {
					System.out.println("El cine está lleno y la película ha empezado. " + cine.getCuentaCine()
							+ " ha recaudado " + cine.getRecaudacionTotal() + "€");
					System.out.println("Vaciando la sala para que comience la siguiente película...");

					// Simulamos que se está vaciando el cine
					Thread.sleep(3000);

					cine.reiniciarAsientos(filas, columnas);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
