package jp.co.dk.message;

import java.util.ArrayList;
import java.util.List;

import jp.co.dk.test.template.TestCaseTemplate;

import org.junit.Test;

public class AbstractMessageTest extends TestCaseTemplate{
	
	@Test
	public void getMessageId() {
		
		// 引数に設定されているメッセージIDが取得されること。
		assertThat(DummyAbstractMessage.M0001.getMessageId(), is ("M0001"));
		
		// 引数に設定されているメッセージIDが取得されること。
		assertThat(DummyAbstractMessage.M0002.getMessageId(), is ("M0002"));
	}
	
	@Test
	public void getMessage() {
		
		// 引数にメッセージIDのみで定義してある場合、
		// メッセージクラス名と同じパッケージに存在し、メッセージクラス名と同じメッセージファイルを読み込むこと
		// （以下の場合、DummyAbstractMessage.propertiesファイルを読み込んでいること。）
		assertThat(DummyAbstractMessage.M0001.getMessage(), is ("ダミーメッセージ{0}"));
		
		// 引数にファイル名と、メッセージIDのみで定義してある場合、
		// 指定のメッセージファイルを読み込むこと
		// （以下の場合、UnDefaultDummyAbstractMessage.propertiesファイルを読み込んでいること。）
		assertThat(DummyAbstractMessage.M0002.getMessage(), is ("デフォルトでないダミーメッセージ{0},{1}"));
		
		// ====================================================================================================
		
		// 置換文字列に値が設定されていた場合、置換されて取得されること
		assertThat(DummyAbstractMessage.M0001.getMessage("test"), is ("ダミーメッセージtest"));
		
		// 置換文字列に値が設定されていた場合、置換されて取得されること
		assertThat(DummyAbstractMessage.M0002.getMessage("test"), is ("デフォルトでないダミーメッセージtest,{1}"));
		
		// ====================================================================================================
		
		// 置換文字列に値が設定されていた場合、置換されて取得されること
		String[] replaceStrArray = {"test"};
		assertThat(DummyAbstractMessage.M0001.getMessage(replaceStrArray), is ("ダミーメッセージtest"));
		
		// 置換文字列に値が設定されていた場合、置換されて取得されること
		assertThat(DummyAbstractMessage.M0002.getMessage(replaceStrArray), is ("デフォルトでないダミーメッセージtest,{1}"));
		
		// ====================================================================================================

		// 置換文字列に値が設定されていた場合、置換されて取得されること
		List<String> replaceStrList = new ArrayList<String>();
		replaceStrList.add("test");
		assertThat(DummyAbstractMessage.M0001.getMessage(replaceStrList), is ("ダミーメッセージtest"));
		
		// 置換文字列に値が設定されていた場合、置換されて取得されること
		assertThat(DummyAbstractMessage.M0002.getMessage(replaceStrList), is ("デフォルトでないダミーメッセージtest,{1}"));
		
	}
	

}

class DummyAbstractMessage extends AbstractMessage {
	
	/** テストメッセージ１ */
	public static final DummyAbstractMessage M0001 = new DummyAbstractMessage("M0001"); 
	
	/** テストメッセージ２ */
	public static final DummyAbstractMessage M0002 = new DummyAbstractMessage("jp/co/dk/message/UnDefaultDummyAbstractMessage.properties", "M0002"); 
	
	protected DummyAbstractMessage(String messageId) {
		super(messageId);
	}
	
	protected DummyAbstractMessage(String file, String messageId) {
		super(file, messageId);
	}
}
