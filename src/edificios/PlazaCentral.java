package edificios;

import java.util.ArrayList;

import excepciones.*;
import mapa.excepcionesMapa.*;
import unidades.*;
import mapa.*;
import juego.*;


public class PlazaCentral extends Edificio {

	private ArrayList<Aldeano> aldeanos;
	
	
	// constructores
	
	// le indico todos los casilleros que ocupa
	public PlazaCentral(Caja lugarOcupado, Mapa mapa, Jugador jugador) throws cajaEstaOcupada, superaLimitePoblacional
    {
        super(lugarOcupado,mapa,jugador);
		this.aldeanos = new ArrayList<Aldeano>();
		this.vida = 450;
		this.costo = 100;
		this.turnosConstruccion = 3;
		this.velocidadReparacion = 25;
		this.cajaOcupada = lugarOcupado;
		this.cajaOcupada.llenar(this);
		
	}

	
	// indico el donde empieza
	public PlazaCentral(Casillero casilleroInicial, Mapa mapa, Jugador jugador) throws casilleroInvalido,
																	cajaEstaOcupada, superaLimitePoblacional
	{
		super(casilleroInicial, mapa, jugador);
		this.aldeanos = new ArrayList<Aldeano>();
		this.vida = 450;
		this.costo = 100;
		this.turnosConstruccion = 3;
		this.velocidadReparacion = 25;
		this.cajaOcupada.llenar(this);

	}


	public ArrayList<Aldeano> getAldeanos(){
		
		return aldeanos;
	}
	
	public void crearAldeano() throws casilleroEstaOcupado, superaLimitePoblacional
	{
		
		Aldeano unAldeano = new Aldeano(this.puntoRally);
		// isRallyPoint ocupado? excepcion
		
		this.aldeanos.add(unAldeano);

	}
	
	
	public void ubicar(Mapeable mapa)
    {
        return;
    }
}
