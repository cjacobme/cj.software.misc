package cj.misc.junit;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import cj.misc.junit.group.FastRunningTests;
import cj.misc.junit.group.IntegerationTests;
import cj.misc.junit.group.SlowRunningTests;

public class SecondTest
{
	private Logger logger = LogManager.getFormatterLogger();

	@Test
	@Category(
	{ SlowRunningTests.class, FastRunningTests.class
	})
	public void mediumMethod() throws InterruptedException
	{
		this.logger.info("SecondTest.mediumMethod() now falls asleep...");
		TimeUnit.SECONDS.sleep(3);
		this.logger.info("SecondTest.mediumMethod() woke up");
	}

	@Test
	@Category(
	{ SlowRunningTests.class
	})
	public void slowMethod() throws InterruptedException
	{
		this.logger.info("SecondTest.slowMethod() now falls asleep...");
		TimeUnit.SECONDS.sleep(8);
		this.logger.info("SecondTest.slowMethod() woke up");
	}

	@Test
	@Category(IntegerationTests.class)
	public void interactWithLegacySystem() throws InterruptedException
	{
		for (int bI = 0; bI < 20; bI++)
		{
			this.logger.info("this is the integration test");
			TimeUnit.SECONDS.sleep(1);
		}
	}
}
