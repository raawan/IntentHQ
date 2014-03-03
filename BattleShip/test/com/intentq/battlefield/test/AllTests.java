package com.intentq.battlefield.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestPlayGame.class, TestShipMovements.class,
	TestInputConversionAndValidation.class, TestCommandLineClientIntegrationTest.class})
public class AllTests {

}
