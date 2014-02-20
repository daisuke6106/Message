package jp.co.dk.message;

import jp.co.dk.message.exception.TestAbstractMessageException;
import jp.co.dk.message.exception.TestAbstractMessageFatalException;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	// jp.co.dk.message
	TestAbstractMessage.class,
	TestCaseMessage.class, 
	TestMessageFile.class, 
	TestMessageFlyweight.class,
	
	// jp.co.dk.message.exception
	TestAbstractMessageException.class, 
	TestAbstractMessageFatalException.class, 
})
public class TestAll {}
