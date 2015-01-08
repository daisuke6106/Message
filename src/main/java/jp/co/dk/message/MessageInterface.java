package jp.co.dk.message;

import java.util.List;

/**
 * MessageInterfaceは、メッセージを定義する定数クラスが実装するインターフェースです。<br>
 * 
 * @version 1.0
 * @author D.Kanno
 */
public interface MessageInterface {
	
	/**
	 * メッセージIDを取得します<p>
	 * 
	 * @return メッセージID
	 */
	public String getMessageId();
	
	/**
	 * メッセージ本文を取得します<p>
	 * 
	 * @return メッセージ本文
	 */
	public String getMessage();
	
	/**
	 * メッセージ本文を指定の埋込み文字列で置換したメッセージ本文を取得します。<p>
	 * 
	 * @param str 埋め込み文字列
	 * @return メッセージ本文
	 */
	public String getMessage(String str);
	
	/**
	 * メッセージ本文を指定の埋込み文字列で置換したメッセージ本文を取得します。<p>
	 * 
	 * @param str 埋め込み文字列
	 * @return メッセージ本文
	 */
	public String getMessage(String[] str);
	
	/**
	 * メッセージ本文を指定の埋込み文字列で置換したメッセージ本文を取得します。<p>
	 * 
	 * @param list 埋め込み文字列
	 * @return メッセージ本文
	 */
	public String getMessage(List<String> list);
	
}
