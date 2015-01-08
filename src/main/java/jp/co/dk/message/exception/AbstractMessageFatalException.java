package jp.co.dk.message.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.co.dk.message.MessageInterface;

/**
 * AbstractMessageFatalExceptionは、MessageInterfaceを実装したメッセージ定数クラスにて致命的例外を発生させる際に使用する致命的例外抽象クラスです。<p>
 * 
 * @version 1.0
 * @author D.Kanno
 */
public abstract class AbstractMessageFatalException extends RuntimeException {
	
	/**
	 * シリアルバージョンID
	 */
	private static final long serialVersionUID = -7091947384630633864L;

	private MessageInterface messageObj;
	
	private List<String> embeddedStrList;
	
	private Throwable throwable;
	
	
	/**
	 * コンストラクタ<p>
	 * 
	 * 指定のメッセージで例外を生成します。
	 * 
	 * @param msg メッセージ定数インスタンス
	 * @since 1.0
	 */
	public AbstractMessageFatalException(MessageInterface msg){
		super(msg.getMessage());
		this.messageObj = msg;
	}
	
	/**
	 * コンストラクタ<p>
	 * 
	 * 指定のメッセージで例外を生成します。
	 * 
	 * @param msg メッセージ定数インスタンス
	 * @param str メッセージ埋め込み文字列
	 * @since 1.0
	 */
	public AbstractMessageFatalException(MessageInterface msg, String str){
		super(msg.getMessage(str));
		this.messageObj = msg;
		List<String> list = new ArrayList<String>();
		list.add(str);
		this.embeddedStrList = list;
	}
	
	/**
	 * コンストラクタ<p>
	 * 
	 * 指定のメッセージ、埋め込み文字列一覧で例外を生成します。
	 * 
	 * @param msg メッセージ定数インスタンス
	 * @param list メッセージ埋め込み文字列一覧
	 * @since 1.0
	 */
	public AbstractMessageFatalException(MessageInterface msg, List<String> list){
		super(msg.getMessage(list));
		this.messageObj      = msg;
		this.embeddedStrList = list;
	}
	
	/**
	 * コンストラクタ<p>
	 * 
	 * 指定のメッセージ、埋め込み文字列一覧で例外を生成します。
	 * 
	 * @param msg メッセージ定数インスタンス
	 * @param str メッセージ埋め込み文字列一覧
	 * @since 1.0
	 */
	public AbstractMessageFatalException(MessageInterface msg, String[] str){
		super(msg.getMessage(str));
		this.messageObj      = msg;
		this.embeddedStrList = Arrays.asList(str);
	}
	
	
	/**
	 * コンストラクタ<p>
	 * 
	 * 指定のメッセージ、例外で例外を生成します。
	 * 
	 * @param msg メッセージ定数インスタンス
	 * @param throwable 例外インスタンス
	 * @since 1.0
	 */
	public AbstractMessageFatalException(MessageInterface msg, Throwable throwable){
		super(msg.getMessage(), throwable);
		this.messageObj = msg;
		this.throwable  = throwable;
	}
	
	/**
	 * コンストラクタ<p>
	 * 
	 * 指定のメッセージ、埋め込み文字列、例外で例外を生成します。
	 * 
	 * @param msg メッセージ定数インスタンス
	 * @param str メッセージ埋め込み文字列
	 * @param throwable 例外インスタンス
	 * @since 1.0
	 */
	public AbstractMessageFatalException(MessageInterface msg, String str, Throwable throwable){
		super(msg.getMessage(str), throwable);
		this.messageObj      = msg;
		List<String> list = new ArrayList<String>();
		list.add(str);
		this.embeddedStrList = list;
		this.throwable       = throwable;
		
		
	}
	
	/**
	 * コンストラクタ<p>
	 * 
	 * 指定のメッセージ、埋め込み文字列一覧、例外で例外を生成します。
	 * 
	 * @param msg メッセージ定数インスタンス
	 * @param list メッセージ埋め込み文字列一覧
	 * @param throwable 例外インスタンス
	 * @since 1.0
	 */
	public AbstractMessageFatalException(MessageInterface msg, List<String> list,Throwable throwable){
		super(msg.getMessage(list), throwable);
		this.messageObj      = msg;
		this.embeddedStrList = list;
		this.throwable       = throwable;
	}
	
	/**
	 * コンストラクタ<p>
	 * 
	 * 指定のメッセージ、埋め込み文字列一覧、例外で例外を生成します。
	 * 
	 * @param msg メッセージ定数インスタンス
	 * @param str メッセージ埋め込み文字列一覧
	 * @param throwable 例外インスタンス
	 * @since 1.0
	 */
	public AbstractMessageFatalException(MessageInterface msg, String[] str, Throwable throwable){
		super(msg.getMessage(str), throwable);
		this.messageObj      = msg;
		this.embeddedStrList = Arrays.asList(str);
		this.throwable       = throwable;
	}
	
	/**
	 * メッセージオブジェクトを取得します。<p>
	 * 
	 * @return メッセージオブジェクト
	 */
	public MessageInterface getMessageObj() {
		return this.messageObj;
	}
	
	/**
	 * メッセージ本文を取得します。<p>
	 * 
	 * 埋め込み文字列にて変換されたメッセージ本文を取得します。
	 * 
	 * @return メッセージ本文
	 * @since 1.0
	 */
	public String getMessage() {
		if (this.embeddedStrList != null) {
			return this.messageObj.getMessage(this.embeddedStrList);
		} else {
			return this.messageObj.getMessage();
		}
	}
	
	/**
	 * メッセージ本文への埋込み文字列を取得します。<p>
	 * 
	 * メッセージ本文への埋込み文字列のコピーを取得します。
	 * 埋込み文字列がない場合、空のリストを返却します。
	 * 
	 * @return メッセージ本文
	 * @since 1.0
	 */
	public List<String> getEmbeddedStrList() {
		if (this.embeddedStrList == null) return new ArrayList<String>();
		return new ArrayList<String>(this.embeddedStrList);
	}
	
	/**
	 * 例外オブジェクトを取得します。<p>
	 * 
	 * 本例外クラスに保持している例外オブジェクトを取得します。
	 * 保持している例外がない場合、nullを返却します。
	 * 
	 * @return 例外オブジェクト
	 * @since 1.0
	 */
	public Throwable getThrowable() {
		return this.throwable;
	}
}
