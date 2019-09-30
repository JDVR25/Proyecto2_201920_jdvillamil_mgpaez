package model.logic;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import com.opencsv.CSVReader;
import model.data_structures.IEstructura;
import model.data_structures.ListaSencillamenteEncadenada;
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

	private boolean mesesOrdenados;

	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public MVCModelo()
	{
		horas = new ListaSencillamenteEncadenada<Viaje>();

		dias = new ListaSencillamenteEncadenada<Viaje>();

		meses = new ListaSencillamenteEncadenada<Viaje>();

		mesesOrdenados = false;
	}

	//Requerimiento funcional 1
	/**
	 * Carga los datos del trimestre. 
	 * @param trimestre. el numero del trimestre.
	 */
	public void cargarDatos(int trimestre)
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

	public int darZonaMenor()
	{
		int respuesta = 999999999;
		for(Viaje temp: meses)
		{
			if(temp.darIdDestino() < respuesta)
			{
				respuesta = temp.darIdDestino();
			}
			if(temp.darIDOrigen() < respuesta)
			{
				respuesta = temp.darIDOrigen();
			}
		}
		return respuesta;
	}

	public int darZonaMayor()
	{
		int respuesta = 0;
		for(Viaje temp: meses)
		{
			if(temp.darIdDestino() > respuesta)
			{
				respuesta = temp.darIdDestino();
			}
			if(temp.darIDOrigen() > respuesta)
			{
				respuesta = temp.darIDOrigen();
			}
		}
		return respuesta;
	}

	//Requerimiento funcional 2
	/**
	 * Consultar el tiempo promedio de viaje y su desviación estándar de los viajes entre una
		zona de origen y una zona destino para un mes dado. Reportar el caso especial en que No
		exista información al respecto.
	 * @param mes. Mes que el usuario desea consultar. 
	 * @param idOrigen. Id de la zona de origen. 
	 * @param idDestino. Id de la zona de destino. 
	 * @return Viaje. El viaje. 
	 */
	public Viaje consultarViajeMes(int mes, int idOrigen, int idDestino)
	{
		Viaje respuesta = null;
		Iterator<Viaje> iterador = meses.iterator();
		boolean encontrado = false;
		while(iterador.hasNext() && !encontrado)
		{
			Viaje temp = iterador.next();
			if(temp.darHoraOMesODia() == mes && temp.darIdDestino() == idDestino && temp.darIDOrigen() == idOrigen)
			{
				encontrado = true;
				respuesta = temp;
			}
		}
		return respuesta;
	}

	//Requerimiento funcional 3
	/**
	 * Consultar la información de los N viajes con mayor tiempo promedio para un mes
		dado. La información debe mostrarse ordenada de mayor a menor por el tiempo
		promedio de los viajes. Mostrar los resultados indicando para cada viaje su zona origen,
		zona destino, el tiempo promedio de viaje y su desviación estándar
	 * @param mes. EL mes dado por usuario. 
	 * @param cuantos. Cuantos viajes se desea consultar. 
	 * @return lista con los mayores viajes. (Ordenados descendientemente)
	 */
	public IEstructura<Viaje> viajesMayorTiempoMes(int mes, int cuantos)
	{
		IEstructura<Viaje> respuesta = new ListaSencillamenteEncadenada<Viaje>();
		if(!mesesOrdenados)
		{
			ordenarPorTiempo(meses);
			mesesOrdenados = true;
		}
		Iterator<Viaje> iterador = meses.iterator();
		int i = 0;
		while(iterador.hasNext() && i < cuantos)
		{
			Viaje temp = iterador.next();
			if(temp.darHoraOMesODia() == mes)
			{
				respuesta.addLast(temp);
				i++;
			}
		}
		return respuesta;
	}

	
	//Requerimiento funcional 4 
	/**
	 * Comparar los tiempos promedios de los viajes para una zona dada contra cada zona X
		en un rango de zonas dado [Zona menor, Zona Mayor] en ambos sentidos (zona dada –
		zona X vs. zona X – zona dada) para un mes dado.
	 * @param mes. Mes que se desea consultar. 
	 * @param zona. Zona que se desea consultar. 
	 * @param zonaMenor. Zona menor. 
	 * @param zonaMayor. Zona mayor. 
	 * @return
	 */
	public Stack<Viaje>[] darViajesRangoZonasMes(int mes, int zona, int zonaMenor, int zonaMayor)
	{
		Stack<Viaje>[] viajes = (Stack<Viaje>[]) new Object[2];
		Stack<Viaje> zonaRango = new Stack<Viaje>();
		Stack<Viaje> rangoZona = new Stack<Viaje>();

		viajes[0] = zonaRango;
		viajes[1] = rangoZona;

		for(int i = zonaMayor; i >= zonaMenor; i--)
		{
			zonaRango.push(consultarViajeMes(mes, zona, i));
			rangoZona.push(consultarViajeMes(mes, i, zona));
		}

		return viajes;
	}


	//Requerimiento funcional 5
	/**
	 * Consultar el tiempo promedio de viaje y su desviación estándar de los viajes entre una
		zona de origen y una zona destino para un día dado de la semana. Reportar el caso
		especial en que No exista información al respecto
	 * @param dia. El dia que el usuario desea consultar. 
	 * @param idOrigen. El id de la zona de origen. 
	 * @param idDestino. EL id de la zona de destino. 
	 * @return
	 */
	public Viaje consultarViajeDia(int dia, int idOrigen, int idDestino)
	{
		Viaje respuesta = null;
		Iterator<Viaje> iterador = dias.iterator();
		boolean found = false;
		while(iterador.hasNext() && !found)
		{
			Viaje temp = iterador.next();
			
			if(temp.darHoraOMesODia() == dia && temp.darIDOrigen() == idOrigen && temp.darIdDestino() == idDestino)
			{
				found = true;
				respuesta = temp;
			}
		}
		return respuesta;
	}

	//Requerimiento funcional 6
	/**
	 * Consultar la información de los N viajes con mayor tiempo promedio para un día dado.
		La información debe mostrarse ordenada de mayor a menor por el tiempo promedio de
		los viajes. Mostrar los resultados indicando para cada viaje su zona origen, zona destino, el
		tiempo promedio de viaje y su desviación estándar.
	 * @param dia. Dia que se desea consultar
	 * @param cuantos. Cuantos dias se dedsean consultar.
	 * @return una lista de los viajes con mayor tiempo en un dia. 
	 */
	public IEstructura<Viaje> viajesMayorTiempoDia(int dia, int cuantos)
	{
		ListaSencillamenteEncadenada<Viaje> respuesta = new ListaSencillamenteEncadenada<Viaje>();
		
		ordenarPorTiempo(dias);
		
		Iterator<Viaje> iterador = dias.iterator();
		
		
		for(int i = 0; i < cuantos && iterador.hasNext(); )
		{
			Viaje temp = iterador.next();
			
			if(temp.darHoraOMesODia() == dia)
			{
				
				respuesta.addLast(temp);
				
				i++;
				
			}
		}
		return respuesta;
	}

	//Requerimiento funcional 7 no requiere metodo adicional(usa el metodo que esta en req 5)
	/**
	 * Comparar los tiempos promedios de los viajes para una zona dada contra cada zona X
		en un rango de zonas dado [Zona menor, Zona Mayor] en ambos sentidos (zona dada –
		zona X vs. zona X – zona dada) para un día dado.
	 * @param dia
	 * @param zona
	 * @param zonaMenor
	 * @param zonaMayor
	 * @return
	 */
	public Stack<Viaje>[] darViajesRangoZonasDia(int dia, int zona, int zonaMenor, int zonaMayor)
	{
		Stack<Viaje>[] viajes = (Stack<Viaje>[]) new Object[2];
		Stack<Viaje> zonaRango = new Stack<Viaje>();
		Stack<Viaje> rangoZona = new Stack<Viaje>();

		viajes[0] = zonaRango;
		viajes[1] = rangoZona;

		for(int i = zonaMayor; i >= zonaMenor; i--)
		{
			zonaRango.push(consultarViajeDia(dia, zona, i));
			rangoZona.push(consultarViajeDia(dia, i, zona));
		}

		return viajes;
	}

	//Requerimiento funcional 8
	/**
	 * Consultar los viajes entre una zona de origen y una zona destino en una franja horaria
		(hora inicial – hora final) dada. La franja horaria se define con horas enteras. Mostrar los
		viajes indicando el tiempo promedio de viaje y su desviación estándar para cada hora
		entera iniciando en la hora inicial y terminando en la hora final.
	 * @param horaInicial
	 * @param horaFinal
	 * @param idOrigen
	 * @param idDestino
	 * @return una lista de viajes.
	 */
	public IEstructura<Viaje> viajesFranjaHoraria(int horaInicial, int horaFinal, int idOrigen, int idDestino)
	{
		IEstructura<Viaje> respuesta = new ListaSencillamenteEncadenada<Viaje>();
		
		for (int i = horaInicial; i <= horaFinal; i++)
		{
			respuesta.addLast(consultarViajeHora(i, idOrigen, idDestino)); 
		}
		
		return respuesta;
	}

	//Requerimiento funcional 9
	
	/**
	 * Consultar la información de los N viajes con mayor tiempo promedio para una hora
		dada. La información debe mostrarse ordenada de mayor a menor por el tiempo
		promedio de los viajes. Mostrar los resultados indicando para cada viaje su zona origen,
		zona destino, el tiempo promedio de viaje y su desviación estándar.
	 * @param hora
	 * @param cuantos
	 * @return una lista de viajes. 
	 */
	public IEstructura<Viaje> viajesMayorTiempoHora(int hora, int cuantos)
	{
		ListaSencillamenteEncadenada<Viaje> respuesta = new ListaSencillamenteEncadenada<Viaje>();
		
		ordenarPorTiempo(horas);
		
		Iterator<Viaje> iterador = horas.iterator();
		
		
		for(int i = 0; i < cuantos && iterador.hasNext(); )
		{
			Viaje temp = iterador.next();
			
			if(temp.darHoraOMesODia() == hora)
			{
				
				respuesta.addLast(temp);
				
				i++;
				
			}
		}
		return respuesta;
	}

	//Requerimiento funcional 10
	
	public Viaje consultarViajeHora(int hora, int idOrigen, int idDestino)
	{
		Viaje respuesta = null;
		Iterator<Viaje> iterador = horas.iterator();
		boolean encontrado = false;
		while(iterador.hasNext() && !encontrado)
		{
			Viaje temp = iterador.next();
			if(temp.darHoraOMesODia() == hora && temp.darIdDestino() == idDestino && temp.darIDOrigen() == idOrigen)
			{
				encontrado = true;
				respuesta = temp;
			}
		}
		return respuesta;
	}

	/**
	 * Generar una gráfica ASCII que muestre el tiempo promedio de los viajes entre una
		zona origen y una zona destino para cada hora del día. Cada * en la gráfica corresponde a
		1 minuto. 
	 * @param hora. 
	 * @param idOrigen
	 * @param idDestino
	 * @return Viaje. 
	 */
	public Queue<Viaje> viajesDeTodaHora(int idOrigen, int idDestino)
	{
		Queue<Viaje> respuesta = new Queue<Viaje>();

		for(int i = 0; i < 24; i++)
		{
			respuesta.enqueue(consultarViajeHora(i, idOrigen, idDestino));
		}

		return respuesta;
	}

	/**
	 * Ordena por tiempo.
	 * @param lista.
	 */
	public void ordenarPorTiempo(ListaSencillamenteEncadenada<Viaje> lista)
	{
		//Creacion del arreglo a ordenar, no cuenta como tiempo de ordenamiento
		Object[] arreglo = lista.toArray();
		Object[] aux = new Object[lista.size()];
		//Medicion del tiempo
		long tInicial = System.currentTimeMillis();

		//Aqui poner el ordenamiento
		mergeSort(arreglo, aux, 0, lista.size() -1);

		long tFinal = System.currentTimeMillis();
		//Guardado del arreglo ordenado, no cuenta como tiempo de ordenamiento
		Nodo<Viaje> temp = lista.darNodo(0);
		for(int i = 0; i < arreglo.length; i++)
		{
			temp.cambiarElemento((Viaje) arreglo[i]);
			temp = temp.darSiguiente();
		}

	}

	public void mergeSort(Object[] arreglo, Object[] aux, int inicio, int last)
	{
		if(inicio < last)
		{
			int mitad = (inicio + last) / 2;
			mergeSort(arreglo, aux, inicio, mitad);
			mergeSort(arreglo, aux, mitad + 1, last);
			merge(arreglo, aux, inicio, mitad, last);
		}
	}
	
	public void merge(Object[] arreglo, Object[] aux, int inicio, int midle, int last)
	{
		
		for(int i = inicio; i <= last; i++)
		{
			aux[i] = (Viaje) arreglo[i];
		}
		
		int i = inicio;
		int l = midle+1;
		for(int pos = inicio; pos <= last; pos++)
		{
			if(i > midle)
			{
				arreglo[pos] = aux[l];
				l++;
			}
			else if(l > last)
			{
				arreglo[pos] = aux[i];
				i++;
			}
			else if(((Viaje) aux[i]).compareTo((Viaje) aux[l]) > 0)
			{
				arreglo[pos] = aux[i];
				i++;
			}
			else
			{
				arreglo[pos] = aux[l];
				l++;
			}
		}
	}
}