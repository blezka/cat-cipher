package Interfaz;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class Permitidos extends PlainDocument{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	private JTextField text;
	public void insertString(int arg0, String arg1, AttributeSet arg2)
	{
		String ret="";
		for(Character c : arg1.toCharArray())
		{
			if(Character.isLetter(c))
			{
				ret+=c;
			}
		}
		try {
			super.insertString(arg0, ret, arg2);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
