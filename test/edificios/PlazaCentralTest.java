package edificios;
import java.util.ArrayList;

import junit.framework.Assert;
import mapa.*;
import unidades.Aldeano;
import juego.*;
import excepciones.*;

import java.util.ArrayList;

import org.junit.Test;

import mapa.excepcionesMapa.*;

public class PlazaCentralTest {

    private Jugador jugador;

    public PlazaCentralTest()
    {
        jugador = new Jugador();
    }
    
    @Test
    public void seCreaEnLaPrimerCaja() throws casilleroInvalido,tamanioDeMapaInvalido,cajaEstaOcupada,superaLimitePoblacional
    {
        Mapa mapa = new Mapa(15,15);
        Casillero casillero = mapa.obtenerCasillero(0,0);
        Caja caja = mapa.asignarCajaACasillero(casillero);
    	PlazaCentral plaza =  new PlazaCentral(caja, mapa,jugador);
        Assert.assertEquals(caja, plaza.obtenerEspacioOcupado());

    }

    @Test
    public void seCreaConCasilleroComoParametro() throws casilleroInvalido, cajaEstaOcupada, tamanioDeMapaInvalido,superaLimitePoblacional
    {
        Mapa mapa = new Mapa(15,15);
        Casillero casillero = mapa.obtenerCasillero(5,7);
    	PlazaCentral plaza = new PlazaCentral (casillero,mapa,this.jugador);
    	Caja caja = mapa.asignarCajaACasillero(casillero);
        Assert.assertEquals(caja, plaza.obtenerEspacioOcupado());

    }

    //Arreglar

    @Test
    public void seCreaAldeanoConRallyLibre() throws casilleroInvalido, casilleroEstaOcupado, cajaEstaOcupada,
                                                    tamanioDeMapaInvalido, superaLimitePoblacional
    {
        Mapa mapa = new Mapa(15,15);
        Casillero casillero = mapa.obtenerCasillero(3,2);
        PlazaCentral plaza = new PlazaCentral (casillero,mapa,this.jugador);
    	plaza.crearAldeano();
    	casillero = plaza.getPuntoRally();
    	ArrayList<Aldeano> aldeanos = plaza.getAldeanos();
    	
    	Assert.assertEquals(aldeanos.get(0) , casillero.obtenerElemento());

    }

    //Arreglar
    
   @Test
   public void seReparaEdificio() throws casilleroInvalido, casilleroEstaOcupado, cajaEstaOcupada,
                                    tamanioDeMapaInvalido, superaLimitePoblacional
   {
        Mapa mapa = new Mapa(15,15);
        Casillero casillero = mapa.obtenerCasillero(1,9);
        PlazaCentral plaza = new PlazaCentral (casillero,mapa,this.jugador);
   	    plaza.recibirDanio(40);
   	    plaza.crearAldeano();
	    ArrayList<Aldeano> aldeanos = plaza.getAldeanos();
	    aldeanos.get(0).repararEdificio(plaza);
	    Assert.assertEquals(plaza.getVida(), 450-40+25);
  
   }
   
   @Test
   public void seRecibeDanio() throws casilleroInvalido, cajaEstaOcupada, tamanioDeMapaInvalido,superaLimitePoblacional
   {
       Mapa mapa = new Mapa(15,15);
       Casillero casillero = mapa.obtenerCasillero(3,7);
       PlazaCentral plaza = new PlazaCentral (casillero,mapa,this.jugador);
       plaza.recibirDanio(60);
   	
       Assert.assertEquals(plaza.getVida(), 450-60);

   }
   
   @Test 
   public void seCreaEnCajaYElMapaLaReconoceEnCadaCasillero() throws tamanioDeMapaInvalido, casilleroInvalido, cajaEstaOcupada,superaLimitePoblacional
   {

        int fila = 3;
        int columna = 3;
        Mapa mapa = new Mapa(15,15);
        Casillero casillero = mapa.obtenerCasillero(fila,columna);
        Caja caja = mapa.asignarCajaACasillero(casillero);

        PlazaCentral plaza = new PlazaCentral (casillero,mapa,this.jugador);
   		Assert.assertEquals(casillero.obtenerElemento(), plaza);
   		
   		casillero = mapa.obtenerCasillero(fila+1, columna);
   		Assert.assertEquals(casillero.obtenerElemento(), plaza);
   		
   		casillero = mapa.obtenerCasillero(fila+1 , columna+1);
   		Assert.assertEquals(casillero.obtenerElemento(), plaza);
   		
   		casillero = mapa.obtenerCasillero(fila, columna+1);
   		Assert.assertEquals(casillero.obtenerElemento(), plaza);
   	
   }
   
   @Test(expected = casilleroEstaOcupado.class)
   public void intentarCrearAldeanoConRallyPointOcupadoLanzaExcepcion() throws casilleroEstaOcupado,
                                        tamanioDeMapaInvalido, casilleroInvalido, cajaEstaOcupada,
                                            superaLimitePoblacional
   {
       Mapa mapa = new Mapa(15,15);
       Casillero casillero = mapa.obtenerCasillero(0,0);
       PlazaCentral plaza = new PlazaCentral (casillero,mapa,this.jugador);
       plaza.crearAldeano();
       plaza.crearAldeano();

   }
}
