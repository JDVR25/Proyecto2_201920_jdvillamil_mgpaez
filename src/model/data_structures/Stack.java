package model.data_structures;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class Stack<E>
{

	/**
	 * Constante de serializaci�n.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Atributo que indica la cantidad de elementos que han sido almacenados en la lista.
	 */
	protected int cantidadElementos;

	/**
	 * Primer nodo de la lista.
	 */
	protected Nodo<E> primerNodo;
	
	/**
	 * Construye la lista vac�a.
	 * <b>post: </b> Se ha inicializado el primer nodo en null
	 */
	public Stack() 
	{
		primerNodo = null;
	}

	/**
	 * Se construye una nueva lista cuyo primer nodo  guardar� al elemento que llega por par�metro. Actualiza el n�mero de elementos.
	 * @param nPrimero el elemento a guardar en el primer nodo
	 * @throws NullPointerException si el elemento recibido es nulo
	 */
	public Stack(E nPrimero) throws NullPointerException
	{
		primerNodo = new Nodo<E>(nPrimero);
		cantidadElementos = 1;
	}

	public int indexOf(E element) 
	{
		int posicion = -1;
		Nodo<E> actual = primerNodo;
		int posActual = 0;
		boolean listo = false;
		while(actual != null && !listo)
		{
			if(actual.darElemento().equals((element)))
			{
				posicion = posActual;
				listo = true;
			}
			else
			{
				posActual ++;
				actual = actual.darSiguiente();
			}
		}

		return posicion;
	}

	/**
	 * Agrega un elemento que llega como parametro a la primera posicion de la lista
	 * @param elemento 
	 */
	public void push(E elemento) 
	{
		boolean agregado = false;

		if(primerNodo == null)
		{
			primerNodo = new Nodo<E>(elemento);
			cantidadElementos = 1;
			agregado = true;
		}
		else
		{
			Nodo<E> nuevoPrimero= new Nodo<E>(elemento);
			nuevoPrimero.cambiarSiguiente(primerNodo);
			primerNodo = nuevoPrimero; 
			cantidadElementos++; 
			agregado = true;
		}
	}

	/**
	 * Remueve el primer elemento de la lista
	 */
	public E pop() 
	{
		E removed = null;
		if (primerNodo != null)
		{
			removed = primerNodo.darElemento();
			primerNodo = primerNodo.darSiguiente();
			cantidadElementos--;
		}
		return removed; 
	}
	
	public E getFirst()
	{
		return primerNodo.darElemento();
	}
	
	public boolean isEmpty()
	{
		return cantidadElementos > 0? false: true;
	}
	
	//Codigo basado en lo desarrollado en el ejercicio de nivel 9 de APO 2 (Honores) 201910
}
