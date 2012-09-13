package Algoritmos;

import Controller.Crypto;
import Controller.Funciones;

public class HillCipher {
	static int[][] keyMatrix = new int[3][3];
	public static String Encode(int[] key, String mensaje)
	{
		int i=0,j=0;
		while(i!=9)
		{
			keyMatrix[i/3][i%3]=key[j];
			i++;
			j++;
			if(j==key.length)
				j=0;
		}
		int tama = (mensaje.length())%3;
		for(i=2; i<3; i++)
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
	public static int[] multiplicarMatrix(Object ... ob)
	{
		int[] ret=new int[3];
		int index=(Integer)ob[0];
		if(index==0)
		{
			for(int i=0; i<3; i++)
				ret[i]=(((Integer)ob[1])*keyMatrix[i][0]+(Integer)ob[2]*keyMatrix[i][1]+(Integer)ob[3]*keyMatrix[i][2])%Crypto.AlfabetoNumber;
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
		}
		return det;
	}
}
