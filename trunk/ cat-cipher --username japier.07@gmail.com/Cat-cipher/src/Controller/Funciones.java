package Controller;

public class Funciones {
	/** String a numeros*/
	public static int[] numerar(String mensaje)
	{
		int[] num = new int[mensaje.length()];
		int i=0;
		for(char c: mensaje.toCharArray())
		{
			num[i]=Character.getNumericValue(c)-10;
			i++;
		}
		return num;
	}

	/** Numeros a String*/
	public static String parse(int[] m)
	{
		String mensaje = "";
		for (int l : m)
		{
			if(l>=0)
			{
				if(Crypto.debug)
					System.out.println(l+"-"+(char)(l+0x61));
				mensaje+=(char)(l+0x61);
			}
			else
			{
				if(Crypto.debug)
					System.out.println();
				mensaje+=" ";
			}
		}
		return mensaje;
	}

	public static int sumarKey(int[] numerar) {
		int ret=0;
		for(int i : numerar)
		{
			ret+=i;
		}
		return ret;
	}
}
