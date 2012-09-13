package Algoritmos;

import java.io.ObjectInputStream.GetField;

import Controller.Crypto;
import Controller.Funciones;

public class HillCipher {
	static int[][] keyMatrix = new int[3][3];
	static int[][] keyPrimaMatrix = new int[3][3];
	public static String Encode(int[] key, String mensaje)
	{
		int i=0,j=0;
		calcularKey(key);
		int tama = (mensaje.length())%3;
		for(i=tama; i<3; i++)
		{
			mensaje+="x";
		}
		int[] result = new int[mensaje.length()];
		int[] mensa = Funciones.numerar(mensaje);
		for(i=0; i<mensa.length-1; i+=3)
		{
			int[] dat = multiplicarMatrix(0, mensa[i], mensa[i+1], mensa[i+2]);
			for(j=0; j<3; j++)
				result[i+j]=dat[j];
		}
		return Funciones.parse(result);
	}
	private static void calcularKey(int [] key)
	{
		int i=0,j=0,h=-1, determinante;
		while(i!=9)
		{
			keyMatrix[i/3][i%3]=key[j];
			i++;
			j++;
			if(j==key.length)
				j=0;
		}
		do
		{
			if(h!=-1)
			{
				keyMatrix[(h/3)%3][h%3]=(keyMatrix[(h/3)%3][h%3]+1)%Crypto.AlfabetoNumber;
//				keyMatrix[h][1]=(keyMatrix[h][1]+1)%Crypto.AlfabetoNumber;
//				keyMatrix[h][2]=(keyMatrix[h][2]+1)%Crypto.AlfabetoNumber;
			}
			determinante=calcularDeterminante(0);
			//h = (h+1)%3;
			h++;
		}while(determinante==0||determinante==2||determinante==13||determinante==26||determinante<0);
	}
	public static String decode(int[] key, String mensaje)
	{
		getMatrixCofactores();
		getTranspuesta();
		return "";
	}
	private static void getTranspuesta()
	{
		int aux;
		for(int i=0; i<2; i++)
			for(int j=0; j<3; j++)
			{
				aux=keyPrimaMatrix[i][j];
				keyPrimaMatrix[i][j]=keyPrimaMatrix[j][i];
				keyPrimaMatrix[j][i]=aux;
			}
	}
	private static void getMatrixCofactores()
	{
		int signo=1;
		for(int i=0; i<3; i++)
		{
			for(int j=0; j<3; j++)
			{
				keyPrimaMatrix[i][j]=signo* (keyMatrix[(i+1)%3][(j+1)%3]*keyMatrix[(i+2)%3][(j+2)%3]
						-keyMatrix[(i+1)%3][(j+2)%3]*keyMatrix[(i+2)%3][(j+1)%3]);
				signo*=-1;
			}
		}
	}
	public static int[] multiplicarMatrix(Object ... ob)
	{
		int[] ret=new int[3];
		int index=(Integer)ob[0];
		if(index==0)
		{
			for(int i=0; i<3; i++)
				ret[i]=(((Integer)ob[1])*keyMatrix[0][i]+(Integer)ob[2]*keyMatrix[1][i]+(Integer)ob[3]*keyMatrix[2][i])%Crypto.AlfabetoNumber;
		}
		return ret;
	}
	public static int calcularDeterminante(Object ... ob)
	{
		int det=0;
		int index = (Integer)ob[0];
		if(index==0)
		{
			det = (keyMatrix[0][0]*(keyMatrix[1][1]*keyMatrix[2][2]-keyMatrix[2][1]*keyMatrix[1][2])-
					keyMatrix[0][1]*(keyMatrix[0][1]*keyMatrix[2][2]-keyMatrix[0][2]*keyMatrix[2][1])+
					keyMatrix[0][2]*(keyMatrix[0][1]*keyMatrix[1][2]-keyMatrix[0][2]*keyMatrix[1][1]));
			det = Funciones.getModulo(det);
		}
		return det;
	}
}
