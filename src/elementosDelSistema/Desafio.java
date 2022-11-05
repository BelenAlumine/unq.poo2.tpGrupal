package elementosDelSistema;

import java.time.LocalDate;
import accionesDeProyecto.EstadoDelDesafio;
import accionesDeProyecto.EstadoNoRealizado;
import accionesDeProyecto.RestriccionTemporal;
import accionesGenerales.ValoracionDesafio;

public class Desafio {
	int muestrasRecolectadas = 0;
	int muestrasARecolectar;
	int dificultad; //agregar un throw exception para que la dificultad no pueda ser m�s de 5
	int recompensa;
	AreaGeografica areaGeografica;
	EstadoDelDesafio estadoDelDesafio = new EstadoNoRealizado();
	LocalDate fechaActual = LocalDate.now();
	RestriccionTemporal restriccion;
	boolean desafioRestringido = false;
	private ValoracionDesafio valoracion;


	
//	public Desafio(int muestrasARecolectar, int dificultad, RestriccionTemporal restriccion, AreaGeografica areaGeografica) {
//		this.muestrasARecolectar = muestrasARecolectar;
//		this.dificultad = dificultad;
//		this.restriccion = restriccion;
//	}
	
	public Desafio(int muestrasARecolectar, int dificultad, int recompensa, RestriccionTemporal restriccion, AreaGeografica area) {
		this.muestrasARecolectar = muestrasARecolectar;
		this.dificultad = dificultad;
		this.restriccion = restriccion;
		this.recompensa = recompensa;
		this.areaGeografica = area;
		this.valoracion = new ValoracionDesafio();
	}

	
	public int getMuestrasRecolectadas() {
		return muestrasRecolectadas;
	}

	public int getMuestrasARecolectar() {
		return muestrasARecolectar;
	}

	public int getDificultad() {
		return dificultad;
	}

	public int getRecompensa() {
		return recompensa;
	}
	
	
	public boolean isDesafioRestringido() {
		return desafioRestringido;
	}
	
	public void setDesafioRestringido(boolean desafioRestringido) {
		this.desafioRestringido = desafioRestringido;
	}
	
	public RestriccionTemporal getRestriccion() {
		return restriccion;
	}
	
	public void setRestriccion(RestriccionTemporal restriccion) {
		this.restriccion = restriccion;
	}
	
	public EstadoDelDesafio getEstadoDelDesafio() {
		return estadoDelDesafio;
	}

	public void setEstadoDelDesafio(EstadoDelDesafio estadoDelDesafio) {
		this.estadoDelDesafio = estadoDelDesafio;
	}


	public LocalDate getFechaActual() {
		return fechaActual;
	}
	
	// toma el lugar de agregarMuestraRecolectada()
	public int sumarMuestraCargada() {
		return muestrasRecolectadas++;
	}
	
	public void agregarVotacion(int votacion) throws Exception {
		valoracion.agregarValoracion(votacion);
	}
	
	public int getVotacion() {
		return valoracion.obtenerValoracion();
	}

	
	public boolean leFaltanMuestrasARecolectar() {
		return this.getMuestrasARecolectar() > this.getMuestrasRecolectadas();
	}
	
	public boolean esUnDesafioCompletado() {
		return this.getMuestrasARecolectar() == this.getMuestrasRecolectadas();
	}
	
	public float porcentajeDeCompletitud() {
		return ((float) this.muestrasRecolectadas / this.muestrasARecolectar) * 100;
	}

}
