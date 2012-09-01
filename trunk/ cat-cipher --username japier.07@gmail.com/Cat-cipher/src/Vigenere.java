public class Vigenere
{
	static boolean debug = false;
	public static String code(String k, String m)
	{
		return code(Crypto.numerar(k),Crypto.numerar(m));
	}
	public static String code(int[] k, int[]m)
	{
		int[] c = new int[m.length];
		for(int i = 0; i < m.length; i++)
		{
		if(m[i]>=0)
		{
			c[i]=(m[i]+k[i%k.length])%26;
		if(debug)
		System.out.println(m[i]+"-"+c[i]);
		}
		else
		{
		c[i]=-1;//espacio
		}
		}
		return Crypto.parse(c);
	}
	public static String decode(String k, String m)
	{
		return decode(Crypto.numerar(k),Crypto.numerar(m));
	}
	public static String decode(int[] k, int[]m)
	{
		int[] d = new int[m.length];
		for(int i = 0; i < m.length; i++)
		{
		if(m[i]>=0)
		{
			d[i]=(m[i]-k[i%k.length]+26)%26;
		}
		else
		{
		d[i] = -1;//espacio
		}
		}
		return Crypto.parse(d);
	}
}

