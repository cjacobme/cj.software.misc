package cj.misc.lang;

import org.junit.Assert;
import org.junit.Test;

public class ArrayDimensionsTest
{
	@Test
	public void twoSimpleChars()
	{
		char[][] lArray = new char[25][32];
		int lActual = ArrayDimensions.getDimensions(lArray);
		Assert.assertEquals(2, lActual);
	}

	@Test
	public void fourSimpleLongs()
	{
		long[][][][] lArray = new long[13][14][15][16];
		int lActual = ArrayDimensions.getDimensions(lArray);
		Assert.assertEquals(4, lActual);
	}

	@Test
	public void threeStrings()
	{
		String[][][] lArray = new String[14][15][16];
		int lActual = ArrayDimensions.getDimensions(lArray);
		Assert.assertEquals(3, lActual);
	}

	@Test
	public void noArray()
	{
		String lName = "Franz Liszt";
		int lActual = ArrayDimensions.getDimensions(lName);
		Assert.assertEquals(0, lActual);
	}

	@Test(expected = NullPointerException.class)
	public void nullThrowsException()
	{
		ArrayDimensions.getDimensions(null);
	}
}
