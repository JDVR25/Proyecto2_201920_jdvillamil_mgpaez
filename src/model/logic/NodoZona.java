package model.logic;

public class NodoZona
{
	private String zona;
	
	private Punto coordenada;

	public NodoZona(String pZona, long pLongitud, long pLatitud)
	{
		zona = pZona;
		
		coordenada = new Punto(pLongitud, pLatitud);
	}
	
	public String darZona()
	{
		return zona;
	}
	
	public Punto darCoordenada()
	{
		return coordenada;
	}
}
