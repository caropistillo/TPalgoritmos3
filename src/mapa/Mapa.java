package mapa;

import java.util.ArrayList;

import mapa.excepcionesMapa.casilleroInvalido;
import mapa.excepcionesMapa.tamanioDeMapaInvalido;
import unidades.Entidad;
import edificios.Castillo;
import mapa.excepcionesMapa.*;
import juego.*;
import excepciones.SuperaLimitePoblacional;

public class Mapa
{
    private int tamanioFilas;
    private int tamanioColumnas;
    private int tamanioCajas;
    private int tamanioCastillo;

    private ArrayList<ArrayList<Casillero>> matriz = new ArrayList<ArrayList<Casillero>>();
    private ArrayList<Fila> filas = new ArrayList<Fila>();
    private ArrayList<Columna> columnas = new ArrayList<Columna>();
    private ArrayList<Caja> cajas = new ArrayList<Caja>();

    //Crea los mxn casilleros (m=tamanioFilas, n=tamanioColumnas)
    private void crearCasilleros()
    {
        for(int i=0;i<tamanioFilas;i++)
        {
            ArrayList<Casillero> fila = new ArrayList<Casillero>();
            matriz.add(fila);
            
            for(int j=0;j<tamanioColumnas;j++)
            {
                Casillero casillero = new Casillero();
                
                casillero.setColumna(j);
                casillero.setFila(i);
                
                fila.add(casillero);
            }
        }
    }

    //Devuelve el array de Casilleros (fila de la matriz) en la posicion pasada por parametro
    private ArrayList<Casillero> obtenerFila(int posicion)
    {
        return matriz.get(posicion);
    }

    //Devuelve el casillero del Mapa en la posicion pasada por parametro
    public Casillero obtenerCasillero(int numeroFila,int numeroColumna)
    {
        return this.obtenerFila(numeroFila).get(numeroColumna);
    }

    //Crea las m filas con las referencias a los casilleros ya creados
    private void crearFilas()
    {
        for(int i=0;i<tamanioFilas;i++)
        {
            Fila fila = new Fila(this.tamanioFilas);
            for(int j=0;j<tamanioColumnas;j++)
                fila.referenciarCasillero(this.obtenerFila(i).get(j));

            filas.add(fila);
        }

    }

    //Crean las n columnas con las referencias a los casilleros ya creados
    private void crearColumnas()
    {
        for(int i=0;i<tamanioColumnas;i++)
        {
            Columna columna = new Columna(this.tamanioColumnas);
            for(int j=0;j<tamanioFilas;j++)
                columna.referenciarCasillero(this.obtenerFila(j).get(i));

            columnas.add(columna);
        }

    }

    private void crearCajas()
    {
    	
        int inicioFilas=0;
        int finFilas=2;
        int inicioColumnas=0;
        int finColumnas = 2;

        
        for(int k=0;k<this.cantidadDeCajas();k++)
        {
        	
            Caja caja = new Caja(this.tamanioCajas);
            for (int i = inicioFilas; i < finFilas; i++)
            {
            
                for (int j = inicioColumnas; j < finColumnas; j++)
                {
                    caja.referenciarCasillero(this.obtenerFila(i).get(j));
                }   
            }
            
            cajas.add(caja);
            inicioColumnas++;
            finColumnas++;
            if(finColumnas==this.tamanioColumnas+1)
            {
                inicioColumnas=0;
                finColumnas=2;
                inicioFilas++;
                finFilas++;
            }
        }
    }

    public void crearCastilloNoroeste(Jugador jugador) throws cajaEstaOcupada, SuperaLimitePoblacional
    {
        Caja caja = new Caja(this.tamanioCastillo);
        for(int i=11;i<15;i++)
        {
            for(int j=4;j<8;j++)
            {
                caja.referenciarCasillero(this.obtenerFila(i).get(j));
            }
        }
        Castillo castilloSuperior = new Castillo(caja,this,jugador);
        jugador.asignarCastillo(castilloSuperior);
    }

    public void crearCastilloSureste(Jugador jugador) throws cajaEstaOcupada, SuperaLimitePoblacional
    {
        Caja caja = new Caja(this.tamanioCastillo);
        for(int i=this.obtenerTamanioFilas()-15;i<this.obtenerTamanioFilas()-11;i++)
        {
            for(int j=this.obtenerTamanioColumnas()-8;j<this.obtenerTamanioColumnas()-4;j++)
            {
                caja.referenciarCasillero(this.obtenerFila(i).get(j));
            }

        }
        Castillo castilloInferior = new Castillo(caja,this,jugador);
        jugador.asignarCastillo(castilloInferior);
    }

    //Constructor
    public Mapa (int filas, int columnas)
    {
    		this.tamanioFilas = columnas;
            this.tamanioColumnas = filas;
            this.tamanioCajas = 4;
            this.tamanioCastillo = 16;

            this.crearCasilleros();
            this.crearFilas();
            this.crearColumnas();        
            this.crearCajas();
            
    }

    private int cantidadDeCajas()
    {
        return (this.tamanioFilas-1)*(this.tamanioColumnas-1);
    }

    public ArrayList<Fila>obtenerFilas()
    {
        return this.filas;
    }

    public ArrayList<Columna>obtenerColumnas()
    {
        return this.columnas;
    }

    public ArrayList<Caja>obtenerCajas()
    {
        return this.cajas;
    }

    public int obtenerTamanioFilas()
    {
        return this.tamanioFilas;
    }

    public int obtenerTamanioColumnas()
    {
        return this.tamanioColumnas;
    }

    public int obtenerTamanioCajas()
    {
        return this.tamanioCajas;
    }


    public void cambiarContenidoDeCasillero(int fila, int columna,Entidad contenido) throws casilleroEstaOcupado, cajaEstaOcupada
    {
        Casillero casillero = this.obtenerCasillero(fila,columna);
        casillero.cambiarContenido(contenido);
        contenido.ubicarEn(casillero);
    }


    public Entidad obtenerElemento(int fila,int columna)
    {
        return this.obtenerCasillero(fila,columna).obtenerElemento();
    }

    /*

    public boolean puedoColocarUnidad(int fila, int columna) throws casilleroEstaOcupado
    {
        return this.puedoColocarUnidad(this.obtenerCasillero(fila,columna));
    }

    public boolean puedoColocarUnidad(Casillero casillero) throws casilleroEstaOcupado
    {
        boolean puedoColocar = true;
        if(casillero.estaOcupado())
        {
            throw new casilleroEstaOcupado();
        }
        return puedoColocar;
    }


    public boolean puedoColocarEdificio(int fila, int columna) throws cajaEstaOcupada, casilleroInvalido
    {
        return this.puedoColocarEdificio(this.obtenerCasillero(fila,columna));
    }

    public boolean puedoColocarEdificio(Casillero casillero) throws cajaEstaOcupada, casilleroInvalido
    {
        boolean puedoColocar = true;
        if(this.asignarCajaACasillero(casillero).estaOcupada())
        {
            throw new cajaEstaOcupada();
        }
        return puedoColocar;
    }

*/
    public Caja asignarCajaACasillero(Casillero casillero) throws casilleroInvalido
    {
        Caja caja = new Caja(this.obtenerTamanioCajas());

        for(int i=0;i<cajas.size();i++)
        {
            if(casillero.obtenerFila(this)==this.ultimaFila()
                    ||(casillero.obtenerColumna(this)==this.ultimaColumna()))
            {
                throw new casilleroInvalido(); //excepcion
            }

            else if (this.obtenerCajas().get(i).obtenerPrimerCasillero() == casillero)
            {
                caja = this.obtenerCajas().get(i);
            }

        }
        return caja;
        //cambiar lanzando excepcion si el casillero no existe
    }


    private Fila ultimaFila()
    {
        return this.obtenerFilas().get(this.tamanioFilas-1);
    }

    public Fila obtenerFila(Casillero casillero)
    {
        Fila fila = new Fila(this.tamanioFilas);

        for(int i=0;i<this.tamanioFilas;i++)
        {
            if(this.obtenerFilas().get(i).contiene(casillero))
            {
                return this.obtenerFilas().get(i);
            }
        }

        return fila;
    }
    public int obtenerFilaInt(Casillero casillero)
    {
    	int i = 0;
        for( i=0;i<this.tamanioFilas;i++)
        {
            if(this.obtenerFilas().get(i).contiene(casillero))
            {
                return i;
            }
        }
        return i;
    }
    
    public int obtenerColumnaInt(Casillero casillero)
    {
    	int i=0;
        for( i=0;i<this.tamanioColumnas;i++)
        {
            if(this.obtenerColumnas().get(i).contiene(casillero))
            {
                return i;
            }
        }

        return i;
    }

    private Columna ultimaColumna()
    {
        return this.obtenerColumnas().get(tamanioColumnas-1);
    }

    public Columna obtenerColumna(Casillero casillero)
    {
        Columna columna = new Columna(this.tamanioColumnas);

        for(int i=0;i<this.tamanioColumnas;i++)
        {
            if(this.obtenerColumnas().get(i).contiene(casillero))
            {
                return this.obtenerColumnas().get(i);
            }
        }

        return columna;
    }

    public int obtenerTamanio()
    {
        return this.obtenerTamanioColumnas()*this.obtenerTamanioFilas();
    }

    /*public Castillo obtenerCastilloSuperior()
    {
        return this.castilloSuperior;
    }

    public Castillo obtenerCastilloInferior()
    {
        return this.castilloInferior;
    }*/

    public ArrayList<Casillero> obtenerCasillerosCircundantes(Caja caja)
    {
        ArrayList<Casillero> casilleros = new ArrayList<Casillero>();
        Casillero primerCasillero = caja.obtenerCasillero(0);
        Casillero segundoCasillero = caja.obtenerCasillero(1);
        Casillero tercerCasillero = caja.obtenerCasillero(2);
        Casillero cuartoCasillero = caja.obtenerCasillero(3);

        if(this.obtenerFilaInt(primerCasillero)>0)
        {
            casilleros.add(this.obtenerCasilleroSuperior(primerCasillero));
            casilleros.add(this.obtenerCasilleroSuperior(segundoCasillero));
        }
        if(this.obtenerColumnaInt(primerCasillero)>0)
        {
            casilleros.add(this.obtenerCasilleroIzquierda(primerCasillero));
            casilleros.add(this.obtenerCasilleroIzquierda(tercerCasillero));
        }
        if(this.obtenerFilaInt(cuartoCasillero)<this.obtenerTamanioFilas()-1)
        {
            casilleros.add(this.obtenerCasilleroInferior(tercerCasillero));
            casilleros.add(this.obtenerCasilleroInferior(cuartoCasillero));
        }
        if(this.obtenerColumnaInt(cuartoCasillero)<this.obtenerTamanioColumnas()-1)
        {
            casilleros.add(this.obtenerCasilleroDerecha(segundoCasillero));
            casilleros.add(this.obtenerCasilleroDerecha(cuartoCasillero));
        }

        return casilleros;        
    }


    public Casillero obtenerCasilleroSuperior(Casillero casillero)
    {
        int fila = obtenerFilaInt(casillero);
        int columna = obtenerColumnaInt(casillero);
        return this.obtenerCasillero(fila-1,columna);
    }

    public Casillero obtenerCasilleroInferior(Casillero casillero)
    {
        int fila = obtenerFilaInt(casillero);
        int columna = obtenerColumnaInt(casillero);
        return this.obtenerCasillero(fila+1,columna);
    }

    public Casillero obtenerCasilleroDerecha(Casillero casillero)
    {
        int fila = obtenerFilaInt(casillero);
        int columna = obtenerColumnaInt(casillero);
        return this.obtenerCasillero(fila,columna+1);
    }

    public Casillero obtenerCasilleroIzquierda(Casillero casillero)
    {
        int fila = obtenerFilaInt(casillero);
        int columna = obtenerColumnaInt(casillero);
        return this.obtenerCasillero(fila,columna-1);
    }

}
