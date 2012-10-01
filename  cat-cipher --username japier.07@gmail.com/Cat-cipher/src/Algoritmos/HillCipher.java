package Algoritmos;

import Controller.Crypto;
import Controller.Funciones;

public class HillCipher {
	static boolean debug = false;

	public static String code(int[] key, String mensaje)
	{
		int[][] keyMatrix = calcularKey(key);
		return encode(keyMatrix,mensaje);
	}
	public static String encode(int[][] keyMatrix, String mensaje)
	{
		int i=0,j=0;

		int tama = (mensaje.length())%3;
		tama = ((tama==0)?3:tama);
		for(i=tama; i<3; i++)
		{
			mensaje+="x";
		}
		int[] result = new int[mensaje.length()];
		int[] mensa = Funciones.numerar(mensaje);
		for(i=0; i<mensa.length-1; i+=3)
		{
			int[] dat = multiplicarMatrix(keyMatrix,0, mensa[i], mensa[i+1], mensa[i+2]);
			for(j=0; j<3; j++)
				result[i+j]=dat[j];
		}
		return Funciones.parse(result);
	}
	private static int[][] calcularKey(int[] key)
	{
		int[][] keyMatrix = new int[3][3];
		int i=0,j=0,h=-1, determinante;
		while(i!=9)
		{
			keyMatrix[i/3][i%3]=key[j];
			i++;
			j++;
			if(j==key.length)
			{
				j=0;
			}
		}
		if (debug)
			for(int x=0; x< keyMatrix.length; x++)
			{
				for(int y=0; y < keyMatrix[x].length; y++)
					System.out.print(keyMatrix[x][y]+",");
				System.out.println();
			}
		do
		{
			if(h!=-1)
			{
				keyMatrix[(h/3)%3][h%3]=(keyMatrix[(h/3)%3][h%3]+1)%Crypto.AlfabetoNumber;
				//				keyMatrix[h][1]=(keyMatrix[h][1]+1)%Crypto.AlfabetoNumber;
				//				keyMatrix[h][2]=(keyMatrix[h][2]+1)%Crypto.AlfabetoNumber;
			}
			determinante=Funciones.getModulo(calcularDeterminante(keyMatrix));
			//h = (h+1)%3;
			h++;
			if(debug)
			print(keyMatrix);
		}while(determinante==0||determinante%2==0||determinante%13==0||determinante%26==0||determinante<0);
//		return keyMatrix;
		return new int[][]{{1,2,3},{3,1,1},{4,2,1}};
	}
	public static String decode(int[] key, String mensaje)
	{

		int[][] keyMatrix = new int[3][3];
		keyMatrix=calcularKey(key);
		int x = findX(keyMatrix);
		if(debug)System.out.println("X="+x);
		keyMatrix=getMatrixCofactores(keyMatrix);
		if(debug)
		{
			System.out.println("Adjunta");	
			print(keyMatrix);
		}
		keyMatrix=getTranspuesta(keyMatrix);
		if(debug)
		{
			System.out.println("Transpuesta");
			print(keyMatrix);
		}


		for(int i = 0; i < keyMatrix.length; i++)
			for(int j = 0; j < keyMatrix.length;j++)
				keyMatrix[i][j]= Funciones.getModulo(keyMatrix[i][j]*x);
		if(debug)
		{
			System.out.println("Modulo (k*x)");
			print(keyMatrix);
		}

		return encode(keyMatrix,mensaje);
	}
	private static void print(int[][] k)
	{
		for(int i = 0; i < k.length; i++)
		{
			for(int j=0; j < k[i].length; j++)
			{
				System.out.print(k[i][j]+",");
			}
			System.out.println();
		}

	}
	private static int findX(int[][] key)
	{
		int det = Funciones.getModulo(calcularDeterminante(key));
		for(int i = 1; i < Crypto.AlfabetoNumber+1;i++)
		{
			double x = i*Crypto.AlfabetoNumber+1;
			x = x%det;
			if(x==0)
			{
				return (i*Crypto.AlfabetoNumber+1)/det;
			}
		}
		return 1;
	}
	private static int[][] getTranspuesta(int[][] keyPrimaMatrix)
	{
		int [][] prim = new int[keyPrimaMatrix.length][keyPrimaMatrix.length];
		for(int i=0; i<keyPrimaMatrix.length; i++)
			for(int j=0; j<keyPrimaMatrix.length; j++)
			{
				int aux=keyPrimaMatrix[i][j];
				prim[i][j]=keyPrimaMatrix[j][i];
				prim[j][i]=aux;
			}
		return prim;
	}
	private static int[][] getMatrixCofactores(int[][] keyMatrix)
	{
		int signo=1;
		int max = keyMatrix.length;
		int[][] key = new int[max][max];
		for(int i=0; i<max; i++)
		{
			for(int j=0; j<max; j++)
			{
				int[][] keyPrimaMatrix = new int[max-1][max-1];
				for(int ip = 0; ip < max-1 ; ip++)
					for(int jp=0; jp < max-1 ; jp++)
						keyPrimaMatrix[ip][jp]=keyMatrix[ip>=i?(ip+1)%max:ip][jp>=j?(jp+1)%max:jp];
				key[i][j] = signo*calcularDeterminante(keyPrimaMatrix);
				signo*=-1;
			}
		}
		return key;
	}
	public static int[] multiplicarMatrix(int[][] keyMatrix,int... ob)
	{
		int[] ret=new int[3];
		int index=(Integer)ob[0];
		if(index==0)
		{
			for(int i=0; i<3; i++)
			{
				ret[i]=Funciones.getModulo(ob[1]*keyMatrix[i][0]+ob[2]*keyMatrix[i][1]+ob[3]*keyMatrix[i][2]);
				if(debug)
					System.out.print(ret[i]+",");
			}
		}
		if(debug)
			System.out.println();
		return ret;
	}
	public static int calcularDeterminante(int[][] key)
	{
		int det=0;
		int max = key.length;
		int signo = 1;
		if(max == 2)
		{
			det = key[0][0]*key[1][1]-key[0][1]*key[1][0];
		}
		if(max == 3)
		{
			det += key[0][0]*(key[1][1]*key[2][2]-key[1][2]*key[2][1]);
			det -= key[0][1]*(key[1][0]*key[2][2]-key[1][2]*key[2][0]);
			det += key[0][2]*(key[1][0]*key[2][1]-key[1][1]*key[2][0]);
		}

		//		det= det%Crypto.AlfabetoNumber;
		if(debug)
			System.out.println("determinante = "+det);
		return det;
	}
}
