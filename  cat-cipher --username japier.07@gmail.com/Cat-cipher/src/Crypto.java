
public class Crypto
{
	static boolean debug = false;

	public static void main(String... args)
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
		
		
}
	}

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
				if(debug)
					System.out.println(l+"-"+(char)(l+0x61));
				mensaje+=(char)(l+0x61);
			}
			else
			{
				if(debug)
					System.out.println();
				mensaje+=" ";
			}
		}
		return mensaje;
	}

}