package jp.co.dk.message;

import jp.co.dk.message.AbstractMessage;

public class TestMessage extends AbstractMessage {

	public static TestMessage M0001 = new TestMessage("M0001");
	
	public static TestMessage M0002 = new TestMessage("M0002");

	public static TestMessage M0003 = new TestMessage("M0003");

	protected TestMessage(String messageId) {
		super(messageId);
	}

}
