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
		String mensaje = (String)args[2];
		if(index==1)
		{
			int key = Funciones.sumarKey(llave);
			String code = mensaje;
			//Caesar
			code = Caesar.decode(key, Funciones.numerar(mensaje));
			code = new StringBuffer(code).reverse().toString();
			if(debug)
				System.out.println(code);
			//Playfair
			code = PlayFair.decode((String)args[1], code);
			if(debug)
				System.out.println(code);
			//Vigenere
			code = Vigenere.decode((String)args[1], code);
			if(debug)
				System.out.println(code);
			//HillCipher
			code = HillCipher.decode(llave,code);
			if(debug)
				System.out.println(code);
			return code;
		}
		else if(index==2)
		{
			String deco = mensaje;
			int key = Funciones.sumarKey(llave);
			//HillCipher			
			deco = HillCipher.code(llave, deco);
			//			//Vigenere
			deco = Vigenere.code((String)args[1], deco);
			if(debug)
				System.out.println(deco);
			//			//Playfair
			deco = PlayFair.code((String)args[1], deco);
			if(debug)
				System.out.println(deco);
			//			//Caesar			
			deco = new StringBuffer(deco).reverse().toString();
			deco = Caesar.code(key, Funciones.numerar(deco));
			if(debug)
				System.out.println(deco);
			return deco;
		}
		else
			return "null";
	}
}