package jp.co.dk.message;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import jp.co.dk.test.template.TestCaseTemplate;

import org.junit.Test;

public class MessageFlyweightTest extends TestCaseTemplate {
	
	@Test
	public void constractor() {
		MessageFlyweight sut = new MessageFlyweight();
	}
	
	@Test
	public void getInstance() {
		// 空の状態でgetInstanceを実行した場合、キャッシュに保持された上でMessageFileインスタンスが返却されること。
		// キャッシュインスタンスを初期化
		MessageFlyweight.messageFileMap = new HashMap<String, Map<Locale, MessageFile>>();
		// getInstanceを実行
		MessageFile messageFile = MessageFlyweight.getInstance("jp/co/dk/message/DummyMessage.properties");
		// キャッシュに保持されたインスタンスを取得し、インスタンスが一致するか確認
		Map<Locale, MessageFile> messageFileMap = MessageFlyweight.messageFileMap.get("jp/co/dk/message/DummyMessage.properties");
		assertThat(new Integer(messageFileMap.size()), is (new Integer(1)));
		assertThat(messageFileMap.get(Locale.getDefault()), is (messageFile));
		
		
		// キャッシュされている状態でgetInstanceを実行した場合、キャッシュに保存されたMessageFileインスタンスが返却されること。
		// キャッシュインスタンスを初期化
		// getInstanceを実行
		messageFile = MessageFlyweight.getInstance("jp/co/dk/message/DummyMessage.properties");
		// キャッシュに保持されたインスタンスを取得し、インスタンスが一致するか確認
		messageFileMap = MessageFlyweight.messageFileMap.get("jp/co/dk/message/DummyMessage.properties");
		assertThat(new Integer(messageFileMap.size()), is (new Integer(1)));
		assertThat(messageFileMap.get(Locale.getDefault()), is (messageFile));
		
		// キャッシュされている状態でgetInstanceを実行した場合、キャッシュに保存されたMessageFileインスタンスが返却されること。
		// キャッシュインスタンスを初期化
		// getInstanceを実行
		messageFile = MessageFlyweight.getInstance("jp/co/dk/message/DummyMessage.properties", Locale.US);
		// キャッシュに保持されたインスタンスを取得し、インスタンスが一致するか確認
		messageFileMap = MessageFlyweight.messageFileMap.get("jp/co/dk/message/DummyMessage.properties");
		assertThat(new Integer(messageFileMap.size()), is (new Integer(2)));
		assertThat(messageFileMap.get(Locale.US), is (messageFile));
		
	}

}
