package jp.co.dk.message;

import jp.co.dk.message.exception.AbstractMessageExceptionTest;
import jp.co.dk.message.exception.AbstractMessageFatalExceptionTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	// jp.co.dk.message
	AbstractMessageTest.class,
	MessageTest.class, 
	MessageFileTest.class, 
	MessageFlyweightTest.class,
	
	// jp.co.dk.message.exception
	AbstractMessageExceptionTest.class, 
	AbstractMessageFatalExceptionTest.class, 
})
public class AllTest {}
