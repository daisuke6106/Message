package jp.co.dk.message;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * AbstractMessageは、メッセージを列挙する定数クラスが実装する基底クラスです。<p>
 * <br>
 * 指定されたメッセージIDをもとに、プロパティファイルよりメッセージ本文を取得します。<br>
 * <br>
 * メッセージ本文を取得時にメッセージの一部を指定の文字列に書き換えることができます。<br>
 * その場合、メッセージ本文の置換したい一部分を{0}等、{数字}という形式にて定義してください。<br>
 * <br>
 * メッセージ取得時のメソッドに埋め込み文字列を渡すことによって埋め込み文字列によって置換したメッセージ本文を受け取ることができます。<br>
 * 埋め込み文字列が複数ある場合、Listや配列にて渡すことにで複数の埋込み文字列に対応することができます。<br>
 * その際には、Listや配列の要素番号が{数字}に定義された数字に紐づいて置換処理が行われます。
 * <br>
 * プロパティファイルの指定方法<br>
 * <br>
 * プロパティファイルを指定する場合はコンストラクタにファイルパスを指定してください。
 * 省略された場合、本抽象クラスを実装しているクラスがあるパッケージ内にあるメッセージプロパティファイルが使用されます。
 * <br>
 * 地域化リソースにも対応しており、プログラム実行時の地域設定を切り替えることによってプログラム内部に変更を加えることなくメッセージ本文を日本語・英語等、各地域言語によって出力することが可能です。<br>
 * <br>
 * 地域切り替え設定はシステムプロパティーのuser.language（日本ならja）、user.country（日本ならJP）、user.variant（日本なら空文字列）を使って生成される。<br>
 * 日本の場合は「ja_JP」になる。<br>
 * <br> 
 * 実行環境が日本の場合、まず、ファイル名に「_ja_JP」が付いたプロパティーファイルがあればそれを読み込みます。<br>
 * 無い場合、ファイル名に「_ja」が付いたプロパティーファイルがあればそれを読み込みます。<br>
 * （ja_JPやjaは、大文字でも小文字でも可能）<br>
 * それも無ければ、ファイル名そのもののプロパティーファイルを読み込みます。<br>
 * <br>
 * ・メッセージ定義例<br>
 * 実行例、実行結果は以下の通りです。<br>
 * <code>
 * TestMessage.java -----------------------------------------------------------------------<br>
 * <br>
 * public class TestMessage extends AbstractMessage {<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;// メッセージ本文 <br>
 * &nbsp;&nbsp;&nbsp;&nbsp;public static final TEST_MESSAGE = new TestMessage("MESSAGE_ID_01");<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;// コンストラクタ<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;protected ConsoleMessage(String messageId) {<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;super(messageId);<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;}<br>
 * }<br>
 * ----------------------------------------------------------------------------------------<br>
 * <br>
 * TestMessage.properties -----------------------------------------------------------------<br>
 * <br>
 * # メッセージ<br>
 * MESSAGE_ID_01=デフォルトのテストメッセージです。<br>
 * <br>
 * ----------------------------------------------------------------------------------------<br>
 * <br>
 * TestMessage_ja.properties --------------------------------------------------------------<br>
 * <br>
 * # メッセージ<br>
 * MESSAGE_ID_01=jaのテストメッセージです。<br>
 * <br>
 * ----------------------------------------------------------------------------------------<br>
 * <br>
 * TestMessage_ja_JP.properties --------------------------------------------------------------<br>
 * <br>
 * # メッセージ<br>
 * MESSAGE_ID_01=ja_JPのテストメッセージです。<br>
 * <br>
 * ----------------------------------------------------------------------------------------<br>
 *<br> 
 * TestMessage_us.properties --------------------------------------------------------------<br>
 * <br>
 * # Message<br>
 * MESSAGE_ID_01=This is Test Message.<br>
 * <br>
 * ----------------------------------------------------------------------------------------<br>
 * <br>
 * 上記のプログラムを準備した状況にて以下のシステムプロパティを指定した場合の出力例は以下のとおりです。<br>
 * <br>
 * ① user.language、user.countryを両方指定した場合、<br>
 * 　 java -Duser.language=ja,user.country=JP ...<br>
 *    <br>
 *    実行結果=ja_JPのテストメッセージです。<br>
 * <br>
 * ② user.languageのみ指定した場合、<br>
 * 　 java -Duser.language=ja ...<br>
 *    <br>
 *    実行結果=jaのテストメッセージです。<br>
 * <br>
 * ③ user.languageをusなどja以外を指定した場合、<br>
 * 　 java -Duser.language=us ...<br>
 *    <br>
 *    実行結果=This is Test Message.<br>
 * <br>
 * ④ システムプロパティを指定しなかった場合、<br>
 * 　 java ...<br>
 *    <br>
 *    実行結果=デフォルトのテストメッセージです。<br>
 * <br>
 * 
 * </code> 
 * @version 1.0
 * @author D.Kanno
 */
public abstract class AbstractMessage implements MessageInterface {

	protected String messageId;

	private MessageFile messageFile;
	
	/**
	 * コンストラクタ<p>
	 * 
	 * 指定されたメッセージIDをもとにメッセージ定数クラスを生成します。
	 * 
	 * @param messageId メッセージID
	 */
	protected AbstractMessage(String messageId) {
		this.messageId = messageId;
		StringBuilder sb = new StringBuilder();
		sb.append(this.getClass().getName().replace('.', '/'));
		this.messageFile = MessageFlyweight.getInstance(sb.toString());
	}
	
	/**
	 * コンストラクタ<p>
	 * 
	 * 指定されたメッセージプロパティファイルパス、メッセージIDをもとにメッセージ定数クラスを生成します。
	 * 
	 * @param file      メッセージプロパティファイルパス
	 * @param messageId メッセージID
	 */
	protected AbstractMessage(String file, String messageId) {
		this.messageId = messageId;
		this.messageFile = MessageFlyweight.getInstance(file);
	}
	
	@Override
	public String getMessageId() {
		return this.messageId;
	}
	
	@Override
	public String getMessage() {
		return messageFile.getMessage(this.messageId);
	}
	
	@Override
	public String getMessage(String str) {
		List<String> list = new ArrayList<String>();
		list.add(str);
		return messageFile.getMessage(this.messageId, list);
	}
	
	@Override
	public String getMessage(String[] str) {
		return messageFile.getMessage(this.messageId, Arrays.asList(str));
	}
	
	@Override
	public String getMessage(List<String> list) {
		return messageFile.getMessage(this.messageId, list);
	}
}
