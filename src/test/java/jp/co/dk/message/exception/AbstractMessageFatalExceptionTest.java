package jp.co.dk.message.exception;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import jp.co.dk.message.MessageInterface;
import jp.co.dk.test.template.TestCaseTemplate;

import org.junit.Test;

public class AbstractMessageFatalExceptionTest extends TestCaseTemplate {

	@Test
	public void constractor() {
		DummyAbstractMessageFatalException DummyAbstractMessageFatalException1 = new DummyAbstractMessageFatalException(DummyMessage.M0001);
		assertEquals(DummyAbstractMessageFatalException1.getMessageObj(), DummyMessage.M0001);
		assertEquals(DummyAbstractMessageFatalException1.getMessage(), "デフォルトのテストメッセージ{0}");
		assertEquals(DummyAbstractMessageFatalException1.getEmbeddedStrList().size(), 0);
		assertNull(DummyAbstractMessageFatalException1.getThrowable());
		
		DummyAbstractMessageFatalException DummyAbstractMessageFatalException2 = new DummyAbstractMessageFatalException(DummyMessage.M0001, "test");
		assertEquals(DummyAbstractMessageFatalException2.getMessageObj(), DummyMessage.M0001);
		assertEquals(DummyAbstractMessageFatalException2.getMessage(), "デフォルトのテストメッセージtest");
		assertEquals(DummyAbstractMessageFatalException2.getEmbeddedStrList().size(), 1);
		assertNull(DummyAbstractMessageFatalException2.getThrowable());
		
		String[] strs = {"test1", "test2"};
		DummyAbstractMessageFatalException DummyAbstractMessageFatalException3 = new DummyAbstractMessageFatalException(DummyMessage.M0002, strs);
		assertEquals(DummyAbstractMessageFatalException3.getMessageObj(), DummyMessage.M0002);
		assertEquals(DummyAbstractMessageFatalException3.getMessage(), "デフォルトのテストメッセージtest1,test2");
		assertEquals(DummyAbstractMessageFatalException3.getEmbeddedStrList().size(), 2);
		assertNull(DummyAbstractMessageFatalException3.getThrowable());
		
		List<String> strList = new ArrayList<String>();
		strList.add("test1");
		strList.add("test2");
		strList.add("test3");
		DummyAbstractMessageFatalException DummyAbstractMessageFatalException4 = new DummyAbstractMessageFatalException(DummyMessage.M0003, strList);
		assertEquals(DummyAbstractMessageFatalException4.getMessageObj(), DummyMessage.M0003);
		assertEquals(DummyAbstractMessageFatalException4.getMessage(), "デフォルトのテストメッセージtest1,test2,test3");
		assertEquals(DummyAbstractMessageFatalException4.getEmbeddedStrList().size(), 3);
		assertNull(DummyAbstractMessageFatalException4.getThrowable());
	}
	
	@Test
	public void constractorWithThrowable() {
		IllegalArgumentException sampleException = new IllegalArgumentException();
		
		DummyAbstractMessageFatalException DummyAbstractMessageFatalException1 = new DummyAbstractMessageFatalException(DummyMessage.M0001, sampleException);
		assertEquals(DummyAbstractMessageFatalException1.getMessageObj(), DummyMessage.M0001);
		assertEquals(DummyAbstractMessageFatalException1.getMessage(), "デフォルトのテストメッセージ{0}");
		assertEquals(DummyAbstractMessageFatalException1.getEmbeddedStrList().size(), 0);
		assertNotNull(DummyAbstractMessageFatalException1.getThrowable());
		assertEquals(DummyAbstractMessageFatalException1.getThrowable(), sampleException);
		
		DummyAbstractMessageFatalException DummyAbstractMessageFatalException2 = new DummyAbstractMessageFatalException(DummyMessage.M0001, "test", sampleException);
		assertEquals(DummyAbstractMessageFatalException2.getMessageObj(), DummyMessage.M0001);
		assertEquals(DummyAbstractMessageFatalException2.getMessage(), "デフォルトのテストメッセージtest");
		assertEquals(DummyAbstractMessageFatalException2.getEmbeddedStrList().size(), 1);
		assertNotNull(DummyAbstractMessageFatalException2.getThrowable());
		assertEquals(DummyAbstractMessageFatalException1.getThrowable(), sampleException);
		
		String[] strs = {"test1", "test2"};
		DummyAbstractMessageFatalException DummyAbstractMessageFatalException3 = new DummyAbstractMessageFatalException(DummyMessage.M0002, strs, sampleException);
		assertEquals(DummyAbstractMessageFatalException3.getMessageObj(), DummyMessage.M0002);
		assertEquals(DummyAbstractMessageFatalException3.getMessage(), "デフォルトのテストメッセージtest1,test2");
		assertEquals(DummyAbstractMessageFatalException3.getEmbeddedStrList().size(), 2);
		assertNotNull(DummyAbstractMessageFatalException3.getThrowable());
		assertEquals(DummyAbstractMessageFatalException1.getThrowable(), sampleException);
		
		List<String> strList = new ArrayList<String>();
		strList.add("test1");
		strList.add("test2");
		strList.add("test3");
		DummyAbstractMessageFatalException DummyAbstractMessageFatalException4 = new DummyAbstractMessageFatalException(DummyMessage.M0003, strList, sampleException);
		assertEquals(DummyAbstractMessageFatalException4.getMessageObj(), DummyMessage.M0003);
		assertEquals(DummyAbstractMessageFatalException4.getMessage(), "デフォルトのテストメッセージtest1,test2,test3");
		assertEquals(DummyAbstractMessageFatalException4.getEmbeddedStrList().size(), 3);
		assertNotNull(DummyAbstractMessageFatalException4.getThrowable());
		assertEquals(DummyAbstractMessageFatalException1.getThrowable(), sampleException);
	}

}

class DummyAbstractMessageFatalException extends AbstractMessageFatalException {

	private static final long serialVersionUID = 8373506079463398008L;

	public DummyAbstractMessageFatalException(MessageInterface msg) {
		super(msg);
	}
	
	public DummyAbstractMessageFatalException(MessageInterface msg, String str){
		super(msg, str);
	}
	
	public DummyAbstractMessageFatalException(MessageInterface msg, List<String> list){
		super(msg, list);
	}
	
	public DummyAbstractMessageFatalException(MessageInterface msg, String[] str){
		super(msg, str);
	}
	
	public DummyAbstractMessageFatalException(MessageInterface msg, Throwable throwable){
		super(msg, throwable);
	}
	
	public DummyAbstractMessageFatalException(MessageInterface msg, String str, Throwable throwable){
		super(msg, str, throwable);
	}
	
	public DummyAbstractMessageFatalException(MessageInterface msg, List<String> list,Throwable throwable){
		super(msg, list, throwable);
	}
	
	public DummyAbstractMessageFatalException(MessageInterface msg, String[] str, Throwable throwable){
		super(msg, str, throwable);
	}
}
