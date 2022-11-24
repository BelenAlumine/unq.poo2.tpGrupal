package accionesDeProyecto;

import elementosDelSistema.DesafioDeUsuario;

public abstract class EstadoDelDesafio {
	//Desafio desafio;
	
	public abstract void revisarEstadoDelDesafio(DesafioDeUsuario desafio);
	public abstract void cambiarDeEstado(DesafioDeUsuario desafio);
}
