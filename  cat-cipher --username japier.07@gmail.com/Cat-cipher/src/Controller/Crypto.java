package Controller;
import Algoritmos.Caesar;
import Algoritmos.HillCipher;
import Algoritmos.PlayFair;
import Algoritmos.Vigenere;
import Interfaz.FramePrincipal;


public class Crypto
{
	static boolean debug = false;
	public static boolean sentido = true;
	public static int AlfabetoNumber=26;
	private FramePrincipal fp;
	public static void main(String... args)
	{
		new Crypto();
		
	}
	public Crypto()
	{
		fp = new FramePrincipal(this);		
	}
	public String encodecode(Object ... args)
	{
		int index = (Integer)args[0];
		int[] llave  = Funciones.numerar(((String)args[1]));
		if(index==0)
		{
			int key = Funciones.sumarKey(llave);
			String mensaje = (String)args[2];
			String deco = Caesar.code(key, Funciones.numerar(mensaje));
			System.out.println(deco);
			return deco;
		}
		else if(index==1)
		{
			int key = Funciones.sumarKey(llave);
			String mensaje = (String)args[2];
			String deco = Caesar.decode(key, Funciones.numerar(mensaje));
			System.out.println(deco);
			return deco;
		}
		else if(index==2)
		{
			String mensaje= (String)args[2];
			return HillCipher.Encode(llave, mensaje);
//			return PlayFair.code((String)args[1], mensaje);
		}
		else
			return "null";
	}
	public void otro(String ... args)
	{
		boolean kset = false;
		boolean methodset = false;
		int method = -1;
		String k = "";
		String mensaje="";
		
		for (String m : args)
		{
			if(kset == true)
			{	
				mensaje+=m+" ";
			}
			if (kset == false && methodset == true)
			{
				if (m.endsWith("."))
				{
				kset=true;
				}
				k+=m.substring(0,m.length()-1);
				if(debug)
				System.out.println("key="+k);
				
			}
			if(methodset == false)
			{
				methodset = true;			
				try
				{
					method = Integer.parseInt(m);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				if(debug)
				System.out.println("method="+method);
			}	
		}
				
		System.out.print("Encriptando...k="+k);
		System.out.println(" m="+mensaje);
		
		String coded = "";
		String decoded = "";
		
		switch(method)
		{
			case 1:
				coded = Caesar.code(k,mensaje);
				System.out.print("Encriptado Caesar Cypher=");
				System.out.println(coded);
		
				decoded = Caesar.decode(k,coded);
				System.out.print("Desencriptado Caesar Cypher=");
				System.out.println(decoded);
				break;
				
			case 2:
				coded = Vigenere.code(k,mensaje);
				System.out.println("Encriptado Vigenere=");
				System.out.println(coded);
				
				decoded = Vigenere.decode(k,coded);
				System.out.println("Desencriptado Vigenere=");
				System.out.println(decoded);
				break;
		
			case 3:
				coded = PlayFair.code(k,mensaje);
				System.out.println("Encriptado PlayFair=");
				System.out.println(coded);
				
				decoded = PlayFair.decode(k,coded);
				System.out.println("Desencriptado PlayFair=");
				System.out.println(decoded);
				break;
			case 4:
				break;
				
		}
	}
}