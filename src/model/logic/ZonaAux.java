package model.logic;

import model.data_structures.ListaSencillamenteEncadenada;

public class ZonaAux extends Zona implements Comparable<ZonaAux>
{

	public ZonaAux(String pNombre, long pPerimetro, long pArea, int pId, ListaSencillamenteEncadenada<Punto> pCoord) 
	{
		super(pNombre, pPerimetro, pArea, pId, pCoord);
	}
	
	@Override
	public int compareTo(ZonaAux o)
	{
		return super.getCoordenadas().size() - o.getCoordenadas().size();
	}

}
