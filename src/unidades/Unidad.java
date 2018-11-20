package unidades;

import mapa.Mapeable;
import mapa.Casillero;
import mapa.Mapa;
import excepciones.*
;public abstract class Unidad extends Entidad

{
    protected Casillero casilleroOcupado;
	public static int cantidad = 0;

	//Constructores
	
	public Unidad()
    {
	    this.turnosConstruccion = 1;
	}
	
	//Con coordenadas 
	public Unidad (int fila, int columna, Mapa mapa) throws CasilleroLleno  
    {
        this.turnosConstruccion = 1;
        Casillero casillero = mapa.obtenerCasillero(fila,columna);
        this.ubicar(casillero);
        
     }
	
	//con casillero
	public Unidad (Casillero casillero)  throws CasilleroLleno  
    {
        this.turnosConstruccion = 1;
        this.ubicar(casillero);
        
     }
	
	public void mover(Casillero casillero)throws CasilleroLleno {
		
		this.casilleroOcupado.vaciar();
		//check distancias, movimiento posible
		this.ubicar(casillero);
	}
	
	
	
	public void recibirDanio(int danio) {
		
		this.vida = this.vida - danio;
	}

	public void ubicar(Mapeable mapeable) throws CasilleroLleno
    {
        Casillero casillero = (Casillero) mapeable; 
        if (casillero.estaOcupado()) {
        	throw new CasilleroLleno();
        }
		this.casilleroOcupado = casillero;
        casillero.cambiarContenido(this);
    }
}
