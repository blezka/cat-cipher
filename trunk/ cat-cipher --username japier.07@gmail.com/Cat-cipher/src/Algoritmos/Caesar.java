package Algoritmos;
import Controller.Crypto;
import Controller.Funciones;

public class Caesar{
	static boolean debug = false;

	public static String code(String k, String m)
	{
		try
		{
		int key = Integer.parseInt(k);
		return code(key,Funciones.numerar(m));
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		return "";
	}
	public static String code(int k, int[] m)
	{
		int[] c = codeNum(k,m);
		return Funciones.parse(c);
	}

	public static String decode(String k, String m)
	{
		try
		{
		int key = Integer.parseInt(k);
		return decode(key,Funciones.numerar(m));
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
		return "";
	}
	
	public static String decode(int k, int[] m)
	{
		int[] d = decodeNum(k,m);
		return Funciones.parse(d);
	}

	private static int[] codeNum(int k, int[] m)
	{
		String mensaje= "";
		for(int i = 0; i < m.length; i++)
		{
			if(m[i]>=0)
			{
				m[i] = (m[i]+k) % 26;
				mensaje+=m[i]+",";
			}
		}
		if(debug)
			System.out.println(mensaje);
		return m;
	}
	private static int[] decodeNum(int k, int[] m)
	{
		String mensaje= "";
		for(int i = 0; i < m.length; i++)
		{
			if(m[i]>=0)
			{
				m[i] = (m[i]-k+26) % 26;
				mensaje+=m[i]+",";
			}
		}
		if(debug)
			System.out.println(mensaje);
		return m;
	}

}