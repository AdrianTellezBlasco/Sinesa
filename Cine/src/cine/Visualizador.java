package cine;

public class Visualizador implements Runnable {
	private Cine cine;

	public Visualizador(Cine cine) {
		this.cine = cine;
	}

	public void mostrarEstadoAsientos() {
		
		//Si un asiento está ocupado pinta X, si no lo está pinta O
		for (int i = 0; i < cine.getAsientos().length; i++) {
			for (int j = 0; j < cine.getAsientos()[0].length; j++) {
				if (this.cine.getAsientos()[i][j].isOcupado()) {
					System.out.print("[X] ");
				} else {
					System.out.print("[O] ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}

	@Override
	public void run() {
		while (true) {
			try {
				synchronized (cine) {

					cine.wait();

					mostrarEstadoAsientos();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
