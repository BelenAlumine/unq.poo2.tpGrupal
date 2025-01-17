package accionesDeProyectoTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import accionesDeProyecto.RestriccionCombinada;
import accionesDeProyecto.RestriccionPorFecha;
import accionesDeProyecto.RestriccionPorFinDeSemana;
import accionesDeProyecto.RestriccionPorSemana;
import accionesDeProyecto.RestriccionTemporal;
import accionesGenerales.RecomendacionDeDesafio;
import elementosDelSistema.AreaGeografica;
import elementosDelSistema.Desafio;
import elementosDelSistema.DesafioDeUsuario;
import elementosDelSistema.Muestra;
import elementosDelSistema.PerfilUsuario;
import elementosDelSistema.Proyecto;
import elementosDelSistema.Usuario;

import static org.mockito.Mockito.*;

class RestriccionCombinadaTest {
	
	AreaGeografica areaGeografica;
	
	PerfilUsuario perfil;
	RecomendacionDeDesafio recomendacion;
	
	RestriccionPorFecha restriccionPorFechaNoRest;
	RestriccionPorFecha restriccionPorFechaRest;
	RestriccionPorSemana restriccionPorSemana;
	RestriccionPorFinDeSemana restriccionPorFinDeSemana;
	RestriccionCombinada restriccionCombinadaRest;
	RestriccionCombinada restriccionCombinadaNoRest;
	
	Usuario usuario;
	
	Muestra muestra;
	
	Desafio desafio1;
	Desafio desafio2;
	Desafio desafio3;
	Desafio desafio4;
	Desafio desafio5;
	Desafio desafio6;
	Desafio desafioMock;
	
	DesafioDeUsuario desafioUsuario1;
	DesafioDeUsuario desafioUsuario2;
	
	Proyecto proyecto;
	
	@BeforeEach
	void setUp() {
		areaGeografica = new AreaGeografica(0.0, 0.0, 1);
		
		restriccionPorFechaNoRest = new RestriccionPorFecha(LocalDate.of(2022, 1, 1), LocalDate.of(2022, 12, 31));
		restriccionPorFechaRest = new RestriccionPorFecha(LocalDate.of(2020,10,12), LocalDate.of(2020, 12, 31));
		restriccionPorSemana = new RestriccionPorSemana();
		restriccionPorFinDeSemana = new RestriccionPorFinDeSemana();
		restriccionCombinadaRest = new RestriccionCombinada();
		restriccionCombinadaNoRest = new RestriccionCombinada();
		
		usuario = new Usuario("String", perfil, recomendacion);
		
		muestra = new Muestra(usuario, 0.0, 1.0);
		
		desafio1 = new Desafio(1, 2, 3, restriccionPorFechaNoRest, areaGeografica);
		desafio2 = new Desafio(1, 2, 3, restriccionPorFechaRest, areaGeografica);
		
		desafio3 = new Desafio(1, 2, 3, restriccionPorSemana, areaGeografica);
		desafio4 = new Desafio(1, 2, 3, restriccionPorFinDeSemana, areaGeografica);
		desafio5 = new Desafio(1, 2, 3, restriccionCombinadaRest, areaGeografica);
		desafio6 = new Desafio(1, 2, 3, restriccionCombinadaNoRest, areaGeografica);
		
		desafioMock = mock(Desafio.class);
		desafioUsuario1 = new DesafioDeUsuario(desafio1);
		desafioUsuario2 = new DesafioDeUsuario(desafio2);
		
		proyecto = new Proyecto("String1", "String2");
	}


	@Test
	void agregarRestricciones() {
		//Creo lista restricciones y le cargo una restricción para comparar en el assert
		List<RestriccionTemporal> restricciones = new ArrayList<RestriccionTemporal>();
		restricciones.add(restriccionPorFechaRest);
		//Creo otra lista para comparar que se cargue correctamente
		List<RestriccionTemporal> restricciones1 = new ArrayList<RestriccionTemporal>();
		restricciones1.add(restriccionPorFechaNoRest);
		restricciones1.add(restriccionPorSemana);
		
	
		restriccionCombinadaRest.agregarRestriccion(restriccionPorFechaRest);
		assertEquals(restricciones, restriccionCombinadaRest.getRestricciones());
		
		restriccionCombinadaNoRest.agregarRestriccion(restriccionPorFechaNoRest);
		restriccionCombinadaNoRest.agregarRestriccion(restriccionPorSemana);
		assertEquals(restricciones1, restriccionCombinadaNoRest.getRestricciones());
	}
	
	@Test
	void chequearRestriccionDesafioNoRestringido() {
		restriccionCombinadaNoRest.agregarRestriccion(restriccionPorFechaNoRest);
		restriccionCombinadaNoRest.agregarRestriccion(restriccionPorSemana);
		restriccionCombinadaNoRest.restringir(desafioUsuario1);
		
//		when(desafioMock.getFechaActual()).thenReturn(LocalDate.of(2022, 9, 22));
		assertEquals(false, desafioUsuario1.isDesafioRestringido());
	}
	
	// El assert se cumple solo en los dias de semana debido al uso del LocalUpdate.now() que se guia por la fecha actual
	@Test
	void chequearRestriccionDesafioRestringido() {
		restriccionCombinadaRest.agregarRestriccion(restriccionPorFechaRest);
		restriccionCombinadaRest.agregarRestriccion(restriccionPorSemana);
		restriccionCombinadaRest.restringir(desafioUsuario2);
		
//		when(desafioMock.getFechaActual()).thenReturn(LocalDate.of(2022, 11, 22));
		assertEquals(true, desafioUsuario2.isDesafioRestringido());
	}
}
