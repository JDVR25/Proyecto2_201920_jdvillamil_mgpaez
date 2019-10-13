package model.logic;

public class NodoMallaVial implements Comparable<NodoMallaVial>
{
	private int id;
	
	private double longitud;
	
	private double latitud;

	public NodoMallaVial(int pId, double pLongitud, double pLatitud)
	{
		id = pId;
		
		longitud = pLongitud;
		
		latitud = pLatitud;
	}
	
	public int darId()
	{
		return id;
	}
	
	public double darLongitud()
	{
		return longitud;
	}
	
	public double darLatitud()
	{
		return latitud;
	}
	
	//TODO pendiente
	@Override
	public int compareTo(NodoMallaVial o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
