package jp.co.dk.message;

import jp.co.dk.message.AbstractMessage;

public class DummyMessage extends AbstractMessage {

	public static DummyMessage M0001 = new DummyMessage("M0001");
	
	public static DummyMessage M0002 = new DummyMessage("M0002");

	public static DummyMessage M0003 = new DummyMessage("M0003");

	protected DummyMessage(String messageId) {
		super(messageId);
	}

}
