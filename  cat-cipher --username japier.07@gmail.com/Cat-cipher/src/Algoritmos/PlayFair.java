package Algoritmos;
import java.awt.Point;

import Controller.Crypto;
import Controller.Funciones;

public class PlayFair
{
	static boolean debug = true;

	public static String code(String k, String m)
	{
		return code(k,Funciones.numerar(m));
	}
	private static String code(String k, int[]m)
	{
		int[][] key = getKeyMatrix(k);
		int[]c = applyRules(key,m);
		return Funciones.parse(c);
	}
	public static String decode(String k, String m)
	{
		return decode(k,Funciones.numerar(m));
	}
	private static String decode(String k, int[]m)
	{
		int[][] key = getKeyMatrix(k);
		int[]c = applyDeRules(key,m);
		return Funciones.parse(c);
	}

	public static int[] applyRules(int[][] k, int[] m)
	{
		boolean adjusted = false;
		if(m.length%2!=0)
		{
			int[] temp = new int[m.length+1];
			for(int x = 0; x < temp.length; x++)
			{
				if(x<m.length)
				{
					temp[x]=m[x];
				}
				else
				{
					temp[x]=23; //X
				}
			}
			m=temp;
			adjusted = true;
		}
		for(int i = 0; i < m.length;i+=2)
		{
			Point a = position(m[i],k);
			Point b = position(m[i+1],k);
			
			if(a==null || b==null)
			System.out.println(m[i]+"="+a+" "+b);
			else
			if(a.x == b.x)
			{
				m[i] = k[a.x][(a.y+1)%5];
				m[i+1] = k[b.x][(b.y+1)%5];				
			}
			else if(a.y == b.y)
			{
				m[i] = k[(a.x+1)%5][a.y];
				m[i+1] = k[(b.x+1)%5][b.y];
			}
			else
			{
				m[i] = k[a.x][b.y];
				m[i+1] = k[b.x][a.y];
			}
		}
		if(adjusted)
		{
			int[] temp = new int[m.length-1];
			for(int x = 0; x < temp.length; x++)
			{
				temp[x]=m[x];
			}
			return temp;
		}
		return m;
	}
	public static int[] applyDeRules(int[][] k, int[] m)
	{
		boolean adjusted = false;
		if(m.length%2!=0)
		{
			int[] temp = new int[m.length+1];
			for(int x = 0; x < temp.length; x++)
			{
				if(x<m.length)
				{
					temp[x]=m[x];
				}
				else
				{
					temp[x]=23; //X
				}
			}
			m=temp;
			adjusted = true;
		}
		for(int i = 0; i < m.length;i+=2)
		{
			Point a = position(m[i],k);
			Point b = position(m[i+1],k);
			if(a==null)System.out.println(m[i]+" "+a);
			if(b==null)System.out.println(m[i+1]+" "+b);
			if(a.x == b.x)
			{
				m[i] = k[a.x][(a.y+4)%5];
				m[i+1] = k[b.x][(b.y+4)%5];
			}
			else if(a.y == b.y)
			{
				m[i] = k[(a.x+4)%5][a.y];
				m[i+1] = k[(b.x+4)%5][b.y];				
			}
			else
			{
				m[i] = k[a.x][b.y];
				m[i+1] = k[b.x][a.y];
			}
		}
		if(adjusted)
		{
			int[] temp = new int[m.length-1];
			for(int x = 0; x < temp.length; x++)
			{
				temp[x]=m[x];
			}
			return temp;
		}
		return m;
	}

	private static Point position(int letra, int[][]k)
	{
		for(int i = 0; i < k.length; i++)
		{
			for(int j=0; j < k[i].length; j++)
			{
				if(k[i][j]==letra)
				{
					return new Point(i,j);
				}
			}
		}
		return null;
	}

	private static int[][] getKeyMatrix(String keyString)
	{
		int[][] key = new int[6][5];
		int[] knum = Funciones.numerar(keyString);
		int letra = 0;
		int letraknum = 0;
		int letraK = 0;
		int i = 0; 
		int j = 0;

		//init
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
				
				if(letra<Crypto.AlfabetoNumber)
				{
				repetidos:
					for(int k = 0; k < key.length; k++)
					{
						for(int l = 0; l < key[k].length; l++)
						{
							if(key[k][l]==-1)
							{
								key[i][j] = letraK;
								j++;
								break repetidos;
							}
							if (letraK == key[k][l])
							{
								if(debug)
								System.out.println("Repeat "+letraK+" "+i+","+j+" "+k+","+l);
								break repetidos;
							}
						}
					}
				}
				else
				{
					key[i][j]=0;
					j++;
				}
			}
			j=0;
			i++;
		}		

		//print
		if(debug)	
		for(i = 0; i < key.length; i++) {for( j = 0; j < key[i].length; j++) System.out.print(key[i][j]+",");System.out.println();}

		return key;
	}
}