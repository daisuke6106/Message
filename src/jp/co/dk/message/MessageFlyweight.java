package jp.co.dk.message;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * 
 * MessageFlyweightは、メッセージファイルのインスタンスを保持し管理するクラスです。<p>
 * <br>
 * Flyweightパターンを使用し、メッセージファイルのインスタンスを内部的に保持し指定のメッセージファイルが初めて読み込まれる場合はインスタンスを生成し、<br>
 * すでに生成されたインスタンスがある場合は、キャッシュされているインスタンスを返却します。<br>
 * 
 * @version 1.0
 * @author D.Kanno
 */
class MessageFlyweight {

	protected static Map<String, Map<Locale, MessageFile>> messageFileMap;

	private MessageFlyweight() {}

	static {
		messageFileMap = new HashMap<String, Map<Locale, MessageFile>>();
	}
	
	/**
	 * 指定のメッセージファイルインスタンスを取得します。<p>
	 * 
	 * @param baseName メッセージプロパティファイルパス
	 * @return メッセージファイルインスタンス
	 */
	static synchronized MessageFile getInstance(String baseName) {
		return getInstance(baseName, Locale.getDefault());
	}
	
	/**
	 * 指定のロケールにてメッセージファイルインスタンスを取得します。<p>
	 * 
	 * @param baseName メッセージプロパティファイルパス
	 * @param locale ロケール
	 * @return メッセージファイルインスタンス
	 */
	static synchronized MessageFile getInstance(String baseName, Locale locale) {
		Map<Locale, MessageFile> localMessageMap = messageFileMap.get(baseName);
		if (localMessageMap == null) {
			localMessageMap = new HashMap<Locale, MessageFile>();
			messageFileMap.put(baseName, localMessageMap);
		}
		MessageFile messageFile = localMessageMap.get(locale);
		if (messageFile == null) {
			messageFile = new MessageFile(baseName, locale);
			localMessageMap.put(locale, messageFile);
		}
		return messageFile;
	}

}
