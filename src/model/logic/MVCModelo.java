package model.logic;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import com.opencsv.CSVReader;
import model.data_structures.IEstructura;
import model.data_structures.ListaSencillamenteEncadenada;
import model.data_structures.MaxHeapCP;
import model.data_structures.Nodo;
import model.data_structures.Queue;
import model.data_structures.Stack;


/**
 * Definicion del modelo del mundo
 *
 */
public class MVCModelo 
{
	/**
	 * Atributos del modelo del mundo
	 */
	private ListaSencillamenteEncadenada<Viaje> horas;

	private ListaSencillamenteEncadenada<Viaje> dias;

	private ListaSencillamenteEncadenada<Viaje> meses;
	
	private ListaSencillamenteEncadenada<NodoMallaVial> nodos;
	
	private ListaSencillamenteEncadenada<Zona> zonas;
	//Para el 1A usar Tabla de hash separate chaining, que sea de tamano 27 para facilitar las cosas, la llave sera la letra inicial necesarios add set y getset
	//En el 2A Usar cola de prioridad para los nodos
	//Para el 3A usar arbloes binarios.

	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public MVCModelo()
	{
		horas = new ListaSencillamenteEncadenada<Viaje>();

		dias = new ListaSencillamenteEncadenada<Viaje>();

		meses = new ListaSencillamenteEncadenada<Viaje>();
		
		nodos = new ListaSencillamenteEncadenada<NodoMallaVial>();
		
		zonas = new ListaSencillamenteEncadenada<Zona>();

	}

	//Requerimiento funcional 1
	/**
	 * Carga los datos del trimestre. 
	 * @param trimestre. el numero del trimestre.
	 */
	public void cargarDatosCSV(int trimestre)
	{
		CSVReader reader = null;
		try
		{
			reader = new CSVReader(new FileReader("./data/bogota-cadastral-2018-" + trimestre + "-All-HourlyAggregate.csv"));
			for(String[] param : reader)
			{
				try
				{
					Viaje nuevo = new Viaje(Integer.parseInt(param[0]), Integer.parseInt(param[1]),
							Integer.parseInt(param[2]), Double.parseDouble(param[3]), Double.parseDouble(param[4]),
							Double.parseDouble(param[5]), Double.parseDouble(param[6]));
					horas.addLast(nuevo);
				}
				catch(NumberFormatException e)
				{

				}
			}

			reader = new CSVReader(new FileReader("./data/bogota-cadastral-2018-"+ trimestre + "-All-MonthlyAggregate.csv"));
			for(String[] param : reader)
			{
				try
				{
					Viaje nuevo = new Viaje(Integer.parseInt(param[0]), Integer.parseInt(param[1]),
							Integer.parseInt(param[2]), Double.parseDouble(param[3]), Double.parseDouble(param[4]),
							Double.parseDouble(param[5]), Double.parseDouble(param[6]));
					meses.addLast(nuevo);
				}
				catch(NumberFormatException e)
				{

				}
			}

			reader = new CSVReader(new FileReader("./data/bogota-cadastral-2018-"+ trimestre + "-All-WeeklyAggregate.csv"));
			for(String[] param : reader)
			{
				try
				{
					Viaje nuevo = new Viaje(Integer.parseInt(param[0]), Integer.parseInt(param[1]),
							Integer.parseInt(param[2]), Double.parseDouble(param[3]), Double.parseDouble(param[4]),
							Double.parseDouble(param[5]), Double.parseDouble(param[6]));
					dias.addLast(nuevo);
				}
				catch(NumberFormatException e)
				{

				}
			}

		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (reader != null)
			{
				try
				{
					reader.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}

		}
	}

	public int darNumViajesMes()
	{
		return meses.size();
	}

	public int darNumViajesHora()
	{
		return horas.size();
	}

	public int darNumViajesDia()
	{
		return dias.size();
	}
	
	public int darNumNodos()
	{
		return nodos.size();
	}
	
	public int darNumZonas()
	{
		return zonas.size();
	}
	
	//Parte A
	public ListaSencillamenteEncadenada<String> letrasMasFrecuentesNombreZona(int n)
	{
		return null;
	}
	
	public MaxHeapCP<NodoMallaVial> darNodosDelimitantesDeZona(double latitud, double longitud)
	{
		//TODO pedir aclaracion sobre lo que toca hacer el enunciado es confuso, otra vez
		return null;
	}
	
	public ListaSencillamenteEncadenada<Viaje> tiemposPrimerTrimestreDentroDeRango(double minimo, double maximo)
	{
		return null;
	}
	
	//Parte B
	//Nota el tipo de lo que retorna los metodos es sugerido, si se hacen cambios es posible que surjan errores en controller
	public ListaSencillamenteEncadenada<Zona> darZonasMasAlNorte(int n)
	{
		return null;
	}
	
	public MaxHeapCP<NodoMallaVial> darNodosMallaVial(double latitud, double longitud)
	{
		//TODO pedir aclaracion sobre lo que toca hacer el enunciado es confuso, otra vez
		return null;
	}
	
	public ListaSencillamenteEncadenada<Viaje> tiemposPrimerTrimestreConDesvEstandEnRango(double minimo, double maximo)
	{
		return null;
	}
	
	//Parte C
	public ListaSencillamenteEncadenada<Viaje> darTiemposZonaOrigenHora(int idOrigen, int hora)
	{
		return null;
	}
	
	public ListaSencillamenteEncadenada<Viaje> darTiemposZonaDestRangoHoras(int idOrigen, int horaMin, int horaMax)
	{
		return null;
	}
	
	public ListaSencillamenteEncadenada<Zona> zonasMasNodos(int n)
	{
		return null;
	}
	
	public ListaSencillamenteEncadenada<Double> datosFaltantesPrimerSemestre()
	{
		return null;
	}
}