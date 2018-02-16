package cj.misc.junit;

import static org.assertj.core.api.Assertions.assertThat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import cj.misc.junit.group.FastRunningTests;

@Category(
{ FastRunningTests.class
})
public class FirstTest
{
	private Logger logger = LogManager.getFormatterLogger();

	@Test
	public void firstMethod()
	{
		this.logger.info("FirstTest.firstMethod()");
		assertThat(true);
	}

	@Test
	public void secondMethod()
	{
		this.logger.info("FirstTest.secondMethod()");
		assertThat(true);
	}
}
