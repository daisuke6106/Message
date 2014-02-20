package jp.co.dk.message.exception;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import jp.co.dk.message.MessageInterface;
import jp.co.dk.test.template.TestCaseTemplate;

import org.junit.Test;

public class TestAbstractMessageException extends TestCaseTemplate {

	@Test
	public void constractor() {
		DummyAbstractMessageException dummyAbstractMessageException1 = new DummyAbstractMessageException(TestMessage.M0001);
		assertEquals(dummyAbstractMessageException1.getMessageObj(), TestMessage.M0001);
		assertEquals(dummyAbstractMessageException1.getMessage(), "デフォルトのテストメッセージ{0}");
		assertEquals(dummyAbstractMessageException1.getEmbeddedStrList().size(), 0);
		assertNull(dummyAbstractMessageException1.getThrowable());
		
		DummyAbstractMessageException dummyAbstractMessageException2 = new DummyAbstractMessageException(TestMessage.M0001, "test");
		assertEquals(dummyAbstractMessageException2.getMessageObj(), TestMessage.M0001);
		assertEquals(dummyAbstractMessageException2.getMessage(), "デフォルトのテストメッセージtest");
		assertEquals(dummyAbstractMessageException2.getEmbeddedStrList().size(), 1);
		assertNull(dummyAbstractMessageException2.getThrowable());
		
		String[] strs = {"test1", "test2"};
		DummyAbstractMessageException dummyAbstractMessageException3 = new DummyAbstractMessageException(TestMessage.M0002, strs);
		assertEquals(dummyAbstractMessageException3.getMessageObj(), TestMessage.M0002);
		assertEquals(dummyAbstractMessageException3.getMessage(), "デフォルトのテストメッセージtest1,test2");
		assertEquals(dummyAbstractMessageException3.getEmbeddedStrList().size(), 2);
		assertNull(dummyAbstractMessageException3.getThrowable());
		
		List<String> strList = new ArrayList<String>();
		strList.add("test1");
		strList.add("test2");
		strList.add("test3");
		DummyAbstractMessageException dummyAbstractMessageException4 = new DummyAbstractMessageException(TestMessage.M0003, strList);
		assertEquals(dummyAbstractMessageException4.getMessageObj(), TestMessage.M0003);
		assertEquals(dummyAbstractMessageException4.getMessage(), "デフォルトのテストメッセージtest1,test2,test3");
		assertEquals(dummyAbstractMessageException4.getEmbeddedStrList().size(), 3);
		assertNull(dummyAbstractMessageException4.getThrowable());
	}
	
	@Test
	public void constractorWithThrowable() {
		IllegalArgumentException sampleException = new IllegalArgumentException();
		
		DummyAbstractMessageException dummyAbstractMessageException1 = new DummyAbstractMessageException(TestMessage.M0001, sampleException);
		assertEquals(dummyAbstractMessageException1.getMessageObj(), TestMessage.M0001);
		assertEquals(dummyAbstractMessageException1.getMessage(), "デフォルトのテストメッセージ{0}");
		assertEquals(dummyAbstractMessageException1.getEmbeddedStrList().size(), 0);
		assertNotNull(dummyAbstractMessageException1.getThrowable());
		assertEquals(dummyAbstractMessageException1.getThrowable(), sampleException);
		
		DummyAbstractMessageException dummyAbstractMessageException2 = new DummyAbstractMessageException(TestMessage.M0001, "test", sampleException);
		assertEquals(dummyAbstractMessageException2.getMessageObj(), TestMessage.M0001);
		assertEquals(dummyAbstractMessageException2.getMessage(), "デフォルトのテストメッセージtest");
		assertEquals(dummyAbstractMessageException2.getEmbeddedStrList().size(), 1);
		assertNotNull(dummyAbstractMessageException2.getThrowable());
		assertEquals(dummyAbstractMessageException1.getThrowable(), sampleException);
		
		String[] strs = {"test1", "test2"};
		DummyAbstractMessageException dummyAbstractMessageException3 = new DummyAbstractMessageException(TestMessage.M0002, strs, sampleException);
		assertEquals(dummyAbstractMessageException3.getMessageObj(), TestMessage.M0002);
		assertEquals(dummyAbstractMessageException3.getMessage(), "デフォルトのテストメッセージtest1,test2");
		assertEquals(dummyAbstractMessageException3.getEmbeddedStrList().size(), 2);
		assertNotNull(dummyAbstractMessageException3.getThrowable());
		assertEquals(dummyAbstractMessageException1.getThrowable(), sampleException);
		
		List<String> strList = new ArrayList<String>();
		strList.add("test1");
		strList.add("test2");
		strList.add("test3");
		DummyAbstractMessageException dummyAbstractMessageException4 = new DummyAbstractMessageException(TestMessage.M0003, strList, sampleException);
		assertEquals(dummyAbstractMessageException4.getMessageObj(), TestMessage.M0003);
		assertEquals(dummyAbstractMessageException4.getMessage(), "デフォルトのテストメッセージtest1,test2,test3");
		assertEquals(dummyAbstractMessageException4.getEmbeddedStrList().size(), 3);
		assertNotNull(dummyAbstractMessageException4.getThrowable());
		assertEquals(dummyAbstractMessageException1.getThrowable(), sampleException);
	}

}

class DummyAbstractMessageException extends AbstractMessageException {

	public DummyAbstractMessageException(MessageInterface msg) {
		super(msg);
	}
	
	public DummyAbstractMessageException(MessageInterface msg, String str){
		super(msg, str);
	}
	
	public DummyAbstractMessageException(MessageInterface msg, List<String> list){
		super(msg, list);
	}
	
	public DummyAbstractMessageException(MessageInterface msg, String[] str){
		super(msg, str);
	}
	
	public DummyAbstractMessageException(MessageInterface msg, Throwable throwable){
		super(msg, throwable);
	}
	
	public DummyAbstractMessageException(MessageInterface msg, String str, Throwable throwable){
		super(msg, str, throwable);
	}
	
	public DummyAbstractMessageException(MessageInterface msg, List<String> list,Throwable throwable){
		super(msg, list, throwable);
	}
	
	public DummyAbstractMessageException(MessageInterface msg, String[] str, Throwable throwable){
		super(msg, str, throwable);
	}
}
