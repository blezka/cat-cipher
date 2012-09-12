package Algoritmos;

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
			if(j==key.length)0
				j=0;
		}
		return null;
	}
}
