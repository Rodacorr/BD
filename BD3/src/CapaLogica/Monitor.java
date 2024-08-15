package CapaLogica;

public class Monitor {
	private int cantLectores;
	private boolean escribiendo;
	
	public Monitor() {
		cantLectores = 0;
		escribiendo = false;
	}
	
	public synchronized void comienzoLectura() {
		while (escribiendo == true) {
			try {
				wait();
			}catch (InterruptedException e) {
				
			}
		}
	cantLectores++;
	}
	
	public synchronized void terminoLectura() {
		cantLectores--;
		if(cantLectores == 0) {
			notify();
		}
	}
	
	public synchronized void comienzoEscritura() {
		while(escribiendo == true || cantLectores >0) {
			try {
				wait();
			}catch (InterruptedException e) {
				
			}
		}
	escribiendo = true;
	}
	
	public synchronized void terminoEscritura() {
		escribiendo = false;
		notify();
	}
}
