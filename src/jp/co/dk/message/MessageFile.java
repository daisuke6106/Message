package jp.co.dk.message;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 
 * MessageFileは、メッセージファイルを表すクラスです。<p>
 * <br>
 * ResourceBundleを保持しており、メッセージIDに紐づくメッセージ本文の取得などのメッセージプロパティファイルに対しての操作を行います。<br>
 * 
 * @version 1.0
 * @author D.Kanno
 */
class MessageFile {

	ResourceBundle resouceBundle;
	
	/**
	 * コンストラクタ<p>
	 * <br>
	 * 指定のメッセージプロパティファイルにてメッセージファイルのインスタンスを生成します。
	 * @param file メッセージプロパティファイル
	 */
	MessageFile(File file) {
		this(file, Locale.getDefault());
	}
	
	/**
	 * コンストラクタ<p>
	 * <br>
	 * 指定のメッセージプロパティファイル、ロケールにてメッセージファイルのインスタンスを生成します。
	 * @param file メッセージプロパティファイル
	 */
	MessageFile(File file, Locale locale) {
		if (file == null) throw new java.lang.IllegalArgumentException("File, which is the argument has not been set.");
		if (locale == null) throw new java.lang.IllegalArgumentException("Local is not set.");
		String baseName = this.getBaseName(file.getPath());
		this.resouceBundle = ResourceBundle.getBundle(baseName, locale);
	}
	
	/**
	 * コンストラクタ<p>
	 * <br>
	 * 指定のメッセージプロパティファイルにてメッセージファイルのインスタンスを生成します。
	 * @param file メッセージプロパティファイル
	 */
	MessageFile(String file) {
		this(file, Locale.getDefault());
	}
	
	/**
	 * コンストラクタ<p>
	 * <br>
	 * 指定のメッセージプロパティファイル、ロケールにてメッセージファイルのインスタンスを生成します。
	 * @param file メッセージプロパティファイル
	 */
	MessageFile(String file, Locale locale) {
		if (file == null || file.equals("")) throw new java.lang.IllegalArgumentException("File, which is the argument has not been set.");
		if (locale == null) throw new java.lang.IllegalArgumentException("Local is not set.");
		String baseName = this.getBaseName(file);
		this.resouceBundle = ResourceBundle.getBundle(baseName, locale);
	}
	
	/**
	 * メッセージ本文を取得します<p>
	 * 
	 * @param メッセージID
	 * @return メッセージ本文
	 */
	String getMessage(String messageId) {
		return this.resouceBundle.getString(messageId);
	}
	
	/**
	 * メッセージ本文を指定の埋込み文字列で置換したメッセージ本文を取得します。<p>
	 * 
	 * @param str 埋め込み文字列
	 * @param メッセージ本文
	 */
	String getMessage(String messageId, List<String> replaceList) {
		String message = this.getMessage(messageId);
		if (replaceList == null) return message;
		for (int i = 0; i < replaceList.size(); i++) {
			String param = replaceList.get(i);
			StringBuilder sb = new StringBuilder();
			sb.append('{').append(i).append('}');
			if (param != null) {
				message = message.replace(sb.toString(), param);
			} else {
				message = message.replace(sb.toString(), "null");
			}
		}
		return message;
	}
	
	/**
	 * メッセージプロパティファイルに定義されているメッセージIDの一覧を取得します。<p>
	 * 
	 * @param メッセージID一覧
	 */
	List<String> getKeyList() {
		List<String> keyList = new ArrayList<String>();
		for (Enumeration<String> enumerations = this.resouceBundle.getKeys(); enumerations
				.hasMoreElements();) {
			keyList.add(enumerations.nextElement());
		}
		return keyList;
	}
	
	/**
	 * メッセージプロパティファイルに定義されているメッセージ本文の一覧を取得します。<p>
	 * 
	 * @param メッセージ本文一覧
	 */
	List<String> getValueList() {
		List<String> keyList = new ArrayList<String>();
		for (Enumeration<String> enumerations = this.resouceBundle.getKeys(); enumerations
				.hasMoreElements();) {
			keyList.add(this.resouceBundle.getString(enumerations.nextElement()));
		}
		return keyList;
	}
	
	protected String getBaseName(String file) {
		if (file == null || file.equals("")) throw new java.lang.IllegalArgumentException("File, which is the argument has not been set.");
		if (file.split("\\.").length > 2) throw new java.lang.IllegalArgumentException("Dot has multiple definitions. Please specify a backslash or slash directory");
		int index = file.lastIndexOf('.');
		if (index != -1) file = file.substring(0, index);
		file = file.replace('/', '.').replace('\\', '.');
		return file;
	}
}
