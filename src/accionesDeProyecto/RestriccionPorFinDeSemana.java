package accionesDeProyecto;


import elementosDelSistema.DesafioDeUsuario;

public class RestriccionPorFinDeSemana extends RestriccionTemporalSimple  {
	
	public RestriccionPorFinDeSemana() {
		
	}
	
	@Override
	public void restringir(DesafioDeUsuario desafio) {
		
		if (this.restringido(desafio)) {
			desafio.setDesafioRestringido(false);
		} else {
			desafio.setDesafioRestringido(true);
		}
	}

	@Override
	public boolean restringido(DesafioDeUsuario desafio) {
		return this.esFinDeSemana(desafio.getDesafioBase());
	}

	@Override
	public void agregarRestriccion(RestriccionTemporal restriccionPorSemana) {
		// TODO Auto-generated method stub
		
	}
}
