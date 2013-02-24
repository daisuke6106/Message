package jp.co.dk.message.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class TestCaseMessage {
	
	@Test
	public void getMessageId() {
		assertEquals(TestMessage.M0001.getMessageId(), "M0001");
		
	}
	
	@Test
	public void getMessage() {
		String message1a = TestMessage.M0001.getMessage();
		String message1b = TestMessage.M0001.getMessage("梅文字");
		String message2  = TestMessage.M0002.getMessage(new String[]{"文字１","文字２"});
		List<String> list = new ArrayList<String>();
		list.add("文字1");
		list.add("文字2");
		list.add("文字3");
		String message3 = TestMessage.M0003.getMessage(list);
		
		// プロパティ=-Duser.language=ja -Duser.country=JP
		String testMessage1a = "ja_JPのテストメッセージ{0}";
		String testMessage1b = "ja_JPのテストメッセージ梅文字";
		String testMessage2  = "ja_JPのテストメッセージ文字１,文字２";
		String testMessage3  = "ja_JPのテストメッセージ文字1,文字2,文字3";
		
		// プロパティ=-Duser.language=ja
//		String testMessage1a = "jpのテストメッセージ{0}";
//		String testMessage1b = "jpのテストメッセージ梅文字";
//		String testMessage2  = "jpのテストメッセージ文字１,文字２";
//		String testMessage3  = "jpのテストメッセージ文字1,文字2,文字3";
//		
//		// プロパティ=なし
//		String testMessage1a = "デフォルトのテストメッセージ{0}";
//		String testMessage1b = "デフォルトのテストメッセージ梅文字";
//		String testMessage2  = "デフォルトのテストメッセージ文字１,文字２";
//		String testMessage3  = "デフォルトのテストメッセージ文字1,文字2,文字3";
//		
		assertEquals(message1a, testMessage1a);
		assertEquals(message1b, testMessage1b);
		assertEquals(message2, testMessage2);
		assertEquals(message3, testMessage3);
	}
	
}
