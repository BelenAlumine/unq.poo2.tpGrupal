package elementosDelSistemaTest;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import accionesDeProyecto.EstadoDelDesafio;
import accionesDeProyecto.EstadoEnCurso;
import accionesDeProyecto.EstadoNoRealizado;
import elementosDelSistema.Desafio;

class DesafioTest {

	Desafio desafio1;
	EstadoDelDesafio estadoNoRealizado;
	EstadoDelDesafio estadoEnCurso;
	
	@BeforeEach
	void setup() {
		desafio1 = new Desafio(5, 5);
		estadoNoRealizado = new EstadoNoRealizado();
		estadoEnCurso = new EstadoEnCurso();
	}

	@Test
	void validacionDeEstado() {
		//Verifico que el estado sea el inicial (no realizado)
		assertTrue(desafio1.getEstadoDelDesafio() instanceof EstadoNoRealizado);
		
		//setteo un estado nuevo y verifico que no siga siendo el anterior, despu�s verifico que sea el que sette�
		desafio1.setEstadoDelDesafio(estadoEnCurso);
		assertFalse(desafio1.getEstadoDelDesafio() instanceof EstadoNoRealizado);
		assertTrue(desafio1.getEstadoDelDesafio() instanceof EstadoEnCurso); 
	}
	
	@Test
	void estadoInicial() {
		assertEquals(5, desafio1.getDificultad());
		assertEquals(0, desafio1.getRecompensa());
		assertEquals(5, desafio1.getMuestrasARecolectar());
		assertEquals(LocalDate.now(), desafio1.getFechaActual());
		
		assertEquals(0, desafio1.getMuestrasRecolectadas().size());
	}

}