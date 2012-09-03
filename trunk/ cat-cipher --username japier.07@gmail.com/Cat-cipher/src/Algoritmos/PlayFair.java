package Algoritmos;
import Controller.Crypto;
import Controller.Funciones;

public class PlayFair
{
	static boolean debug = true;

	public static String code(String k, String m)
	{
		return code(k,Funciones.numerar(m));
	}
	public static String code(String k, int[]m)
	{
		int[][] key = getKeyMatrix(k);
		int[]c = applyRules(key,m);
		return Funciones.parse(c);
	}
	public static String decode(String k, String m)
	{
		return decode(k,Funciones.numerar(m));
	}
	public static String decode(String k, int[]m)
	{
		int[][] key = getKeyMatrix(k);
		int[]c = applyDeRules(key,m);
		return Funciones.parse(c);
	}

	public static int[] applyRules(int[][] k, int[] m)
	{
		return m;
	}
	public static int[] applyDeRules(int[][] k, int[] m)
	{
		return m;
	}

	private static int[][] getKeyMatrix(String keyString)
	{
		int[][] key = new int[5][5];
		int[] knum = Funciones.numerar(keyString.replace("i","j"));
		int letra = 0;
		int letraknum = 0;
		int letraK = 0;
		int i = 0; 
		int j = 0;
		
		for(int x = 0; x < key.length; x++)
		for(int y = 0; y < key[x].length; y++)
		key[x][y] = -1;
		
		if(debug)
		{
		for(int x = 0; x < knum.length; x++)
		System.out.print(knum[x]+",");
		System.out.println();
}

		while(i < key.length)
		{
			while(j < key[i].length)
			{
				boolean repeat = false;

				if(letraknum < knum.length)
				{
					letraK = knum[letraknum];
					letraknum++;
				}
				else
				{
					letraK = letra;
					letra++;
				}

				for(int k = 0; k <= i; k++)
				{
					for(int l = 0; l <= j; l++)
					{
						if (letraK == key[k][l])
						{
							repeat = true;
							System.out.println("Repeat "+letraK+" "+i+","+j+" "+k+","+l);
						}
					}
				}
				if (!repeat)
				{
					key[i][j] = letraK;
					j++;

				}

			}
			j=0;
			i++;
		}		

		//print
		for(i = 0; i < key.length; i++) {for( j = 0; j < key[i].length; j++) System.out.print(key[i][j]+",");System.out.println();}

		return key;
	}
}