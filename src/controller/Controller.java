package controller;

import java.util.Scanner;

import com.sun.tracing.dtrace.ModuleName;

import model.data_structures.IEstructura;
import model.data_structures.ListaSencillamenteEncadenada;
import model.logic.MVCModelo;
import model.logic.NodoZona;
import model.logic.Viaje;
import view.MVCView;

public class Controller {

	/* Instancia del Modelo*/
	private MVCModelo modelo;

	/* Instancia de la Vista*/
	private MVCView view;

	private boolean cargaRealizada;
	
	public static final int N = 20;


	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new MVCView();
		modelo = new MVCModelo();
		cargaRealizada = false;
	}

	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		String dato = "";
		String respuesta = "";

		while( !fin ){
			view.printMenu();

			int option = lector.nextInt();
			switch(option){
			case 1:
				if(!cargaRealizada)
				{
					view.iniciarCarga();
					modelo = new MVCModelo(); 
					modelo.cargarDatosCSV(1);
					modelo.cargarDatosCSV(2);
					modelo.cargarDatosNodos();
					modelo.cargarDatosZonas();
					view.finalizarCarga(modelo);
				}
				else
				{
					view.errorDatosYaCargados();
				}
				break;

			case 2:
				try
				{
					if(cargaRealizada)
					{
						view.solicitarNumLetras();
						dato = lector.nextLine();
						int numLetras = Integer.parseInt(dato);
						if(numLetras < 0)
						{
							view.errorEnteroNegativo();
						}
						else
						{
							view.informacionLetras(modelo.letrasMasFrecuentesNombreZona(), numLetras);
						}
					}
					else
					{
						view.errorDatosNoCargados();
					}
				}
				catch (NumberFormatException e)
				{
					view.errorEntero();				}
				break;

			case 3:
				try
				{
					if(cargaRealizada)
					{
						view.solicitarLatitud();
						dato = lector.nextLine();
						double latitud = Double.parseDouble(dato); 

						view.solicitarLongitud();
						dato = lector.nextLine();
						double longitud = Double.parseDouble(dato);

						ListaSencillamenteEncadenada<NodoZona> lista = modelo.darNodosDelimitantesDeZona(latitud, longitud);
						view.darNodosZona(lista);
					}
					else
					{
						view.errorDatosNoCargados();
					}
				}
				catch (NumberFormatException e)
				{
					view.errorNum();
				}
				break;

			case 4: 
				try
				{
					if(cargaRealizada)
					{
						view.pedirLimiteBajo();
						dato = lector.nextLine();
						double low = Double.parseDouble(dato);
						
						view.pedirLimiteAlto();
						dato = lector.nextLine();
						double high = Double.parseDouble(dato);
						
						ListaSencillamenteEncadenada<Viaje> lista = modelo.tiemposPrimerTrimestreDentroDeRango(low, high);
						view.darInfoViajes(lista);
					}
					else
					{
						view.errorDatosNoCargados();
					}
				}
				catch (NumberFormatException e)
				{
					view.errorNum();
				}
				break;

			case 5: 
				//TODO pendiente
				break;

			case 6: 
				//TODO pendiente
				break;

			case 7: 
				//TODO pendiente
				break;

			case 8: 
				//TODO pendiente
				break;

			case 9: 
				//TODO pendiente
				break;

			case 10: 
				//TODO pendiente
				break;

			case 11: 
				//TODO pendiente
				break;

			case 12: 
				System.out.println("--------- \n Hasta pronto !! \n---------"); 
				lector.close();
				fin = true;
				break; 

			default: 
				System.out.println("--------- \n Opcion Invalida !! \n---------");
				break;
			}
		}

	}	
}
