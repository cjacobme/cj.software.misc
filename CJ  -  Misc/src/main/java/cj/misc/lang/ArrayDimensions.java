package cj.misc.lang;

public class ArrayDimensions
{
	/**
	 * ermittelt für ein Objekt, wie viele Dimensionen es hat, falls es ein Array
	 * sein sollte. Beispiele:
	 * <ul>
	 * <li>ein einfaches Objekt gibt den Wert 0 zurück</li>
	 * <li>ein z.B. {@code char[]} gibt den Wert 1 zurück</li>
	 * <li>ein z.B. {@code String[][][][]} gibt den Wert 4 zurück.</li>
	 * <li>ein {@code null}-Objekt wirft eine {@link NullPointerException}.</li>
	 * </ul>
	 * 
	 * @param pSource
	 *            zu untersuchendes Objekt
	 * @return die Anzahl an Dimensionen
	 * @throws NullPointerException
	 *             es wurde kein Objekt übergeben.
	 */
	public static int getDimensions(Object pSource)
	{
		String lClassname = pSource.getClass().getName();
		int lResult = countBracketsAtStart(lClassname);
		return lResult;
	}

	private static int countBracketsAtStart(String pSource)
	{
		int lResult = 0;
		int lLength = pSource.length();
		for (int bI = 0; bI < lLength; bI++)
		{
			if (pSource.charAt(bI) != '[')
			{
				break;
			}
			lResult++;
		}
		return lResult;
	}
}
