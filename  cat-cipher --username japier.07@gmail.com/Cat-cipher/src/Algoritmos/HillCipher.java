package Algoritmos;

import Controller.Crypto;
import Controller.Funciones;

public class HillCipher {
	static boolean debug = true;
	
	public static String code(int[] key, String mensaje)
	{
		int[][] keyMatrix = calcularKey(key);
		return encode(keyMatrix,mensaje);
	}
	public static String encode(int[][] keyMatrix, String mensaje)
	{
		int i=0,j=0;

		int tama = (mensaje.length())%3;
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
		}while(determinante==0||determinante==2||determinante==13||determinante==26||determinante<0);
		return keyMatrix;
	}
	public static String decode(int[] key, String mensaje)
	{
		int[][] keyMatrix = new int[3][3];
		keyMatrix=calcularKey(key);	
		int x = findX(keyMatrix);
		keyMatrix=getMatrixCofactores(keyMatrix);
		keyMatrix=getTranspuesta(keyMatrix);
		
		for(int i = 0; i < keyMatrix.length; i++)
			for(int j = 0; j < keyMatrix.length;j++)
				keyMatrix[i][j]= Funciones.getModulo(keyMatrix[i][j]*x);
		
		return encode(keyMatrix,mensaje);
	}
	private static int findX(int[][] key)
	{
		int det = Funciones.getModulo(calcularDeterminante(key));
		for(int i = 0; i < Crypto.AlfabetoNumber;i++)
		{
			double x = i*Crypto.AlfabetoNumber+1;
			if(x%det==0)
				return (i*Crypto.AlfabetoNumber+1)/det;
		}
		return -1;
	}
	private static int[][] getTranspuesta(int[][] keyPrimaMatrix)
	{
		for(int i=0; i<keyPrimaMatrix.length; i++)
			for(int j=0; j<keyPrimaMatrix.length; j++)
			{
				int aux=keyPrimaMatrix[i][j];
				keyPrimaMatrix[i][j]=keyPrimaMatrix[j][i];
				keyPrimaMatrix[j][i]=aux;
			}
		return keyPrimaMatrix;
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
	public static int calcularDeterminante(int[][] keyMatrix)
	{
		int det=0;
		int max = keyMatrix.length;
			for(int i = 0; i <max-1; i++)
			{
				int pos = 1;
				int neg = 1;
				for(int j=0; j < max; j++)
				{
					int x = j;
					int y = (j+i)%max;
					if(debug)
					System.out.println("+"+pos+"*"+keyMatrix[x][y]);
					pos *= keyMatrix[x][y];
					if(debug)
					System.out.println("-"+neg+"*"+keyMatrix[x][max-1-y]);
					neg *= keyMatrix[x][max-1-y];
				}
				det += pos-neg;
		}
		if(debug)
			System.out.println("determinante = "+det);
		return det;
	}
}
