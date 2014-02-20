package jp.co.dk.message;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;

import jp.co.dk.test.template.TestCaseTemplate;

import org.junit.Test;

public class TestMessageFile extends TestCaseTemplate{

	@Test
	public void constractor() {
		// NULLを渡した場合、例外が送出されること。
		try {
			String nullString = null;
			new MessageFile(nullString);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "File, which is the argument has not been set.");
		}
		
		// 空文字を渡した場合、例外が送出されること。
		try {
			new MessageFile("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "File, which is the argument has not been set.");
		}
		
		// 拡張子ありのファイル名を渡した場合、正常に読み込めること
		try {
			MessageFile messageFile = new MessageFile("jp/co/dk/message/TestMessage.properties");
			assertEquals(messageFile.getMessage("M0001"), "ja_JPのテストメッセージ{0}");
		} catch (IllegalArgumentException e) {
			fail(e);
		}
		
		// 拡張子なしのファイル名を渡した場合、正常に読み込めること
		try {
			MessageFile messageFile = new MessageFile("jp/co/dk/message/TestMessage");
			assertEquals(messageFile.getMessage("M0001"), "ja_JPのテストメッセージ{0}");
		} catch (IllegalArgumentException e) {
			fail(e);
		}
		
		// ファイルが存在しない場合、例外が発生すること
		try {
			new MessageFile("jp/co/dk/message/NothingFile");
			fail();
		} catch (MissingResourceException e) {
			assertEquals(e.getMessage(), "Can't find bundle for base name jp.co.dk.message.NothingFile, locale ja_JP");
		}
		
		// ====================================================================================================
		
		// NULLを渡した場合、例外が送出されること。
		try {
			File nullFile = null;
			new MessageFile(nullFile);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "File, which is the argument has not been set.");
		}
		
		// 空文字を渡した場合、例外が送出されること。
		try {
			new MessageFile(new File(""));
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "File, which is the argument has not been set.");
		}
		
		// 拡張子ありのファイル名を渡した場合、正常に読み込めること
		try {
			MessageFile messageFile = new MessageFile(new File("jp/co/dk/message/TestMessage.properties"));
			assertEquals(messageFile.getMessage("M0001"), Locale.getDefault()+"のテストメッセージ{0}");
		} catch (IllegalArgumentException e) {
			fail(e);
		}
		
		// 拡張子なしのファイル名を渡した場合、正常に読み込めること
		try {
			MessageFile messageFile = new MessageFile(new File("jp/co/dk/message/TestMessage"));
			assertEquals(messageFile.getMessage("M0001"), Locale.getDefault()+"のテストメッセージ{0}");
		} catch (IllegalArgumentException e) {
			fail(e);
		}
		
		// ファイルが存在しない場合、例外が発生すること
		try {
			new MessageFile(new File("jp/co/dk/message/NothingFile"));
			fail();
		} catch (MissingResourceException e) {
			assertEquals(e.getMessage(), "Can't find bundle for base name jp.co.dk.message.NothingFile, locale ja_JP");
		}
		
		// ====================================================================================================
		
		// NULLを渡した場合、例外が送出されること。
		try {
			Locale nullLocal  = null;
			new MessageFile("jp/co/dk/message/TestMessage.properties", nullLocal);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Local is not set.");
		}
		
		// NULLを渡した場合、例外が送出されること。
		try {
			Locale nullLocal  = null;
			new MessageFile(new File("jp/co/dk/message/TestMessage.properties"), nullLocal);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Local is not set.");
		}
		
		// ENGLISHのロケールにてインスタンスを生成した場合、enのロケールファイルをよみこむこと
		try {
			MessageFile messageFile = new MessageFile("jp/co/dk/message/TestMessage.properties", Locale.ENGLISH);
			assertEquals(messageFile.getMessage("M0001"), "enのテストメッセージ{0}");
		} catch (IllegalArgumentException e) {
			fail(e);
		}
		
		// USのロケールにてインスタンスを生成した場合、en_USのロケールファイルをよみこむこと
		try {
			MessageFile messageFile = new MessageFile("jp/co/dk/message/TestMessage.properties", Locale.US);
			assertEquals(messageFile.getMessage("M0001"), "en_USのテストメッセージ{0}");
		} catch (IllegalArgumentException e) {
			fail(e);
		}
		
		// CANADAのロケールにてインスタンスを生成した場合、enのロケールファイルをよみこむこと
		try {
			MessageFile messageFile = new MessageFile("jp/co/dk/message/TestMessage.properties", Locale.CANADA);
			assertEquals(messageFile.getMessage("M0001"), "enのテストメッセージ{0}");
		} catch (IllegalArgumentException e) {
			fail(e);
		}
		
		// 定義されていないのロケールにてインスタンスを生成した場合、現在のロケールファイルをよみこむこと
		try {
			MessageFile messageFile = new MessageFile("jp/co/dk/message/TestMessage.properties", Locale.FRANCE);
			assertEquals(messageFile.getMessage("M0001"), Locale.getDefault()+"のテストメッセージ{0}");
		} catch (IllegalArgumentException e) {
			fail(e);
		}
	}
	
	@Test
	public void getMessage() {
		MessageFile messageFile = new MessageFile("jp/co/dk/message/TestMessage.properties");
		
		// NULLを渡した場合、例外が送出されること。
		try {
			messageFile.getMessage("");
			fail();
		} catch (MissingResourceException e) {
			assertEquals(e.getMessage(), "Can't find resource for bundle java.util.PropertyResourceBundle, key ");
		}
		
		// 存在しないメッセージIDを渡した場合、例外が送出されること。
		try {
			messageFile.getMessage("EXXX");
			fail();
		} catch (MissingResourceException e) {
			assertEquals(e.getMessage(), "Can't find resource for bundle java.util.PropertyResourceBundle, key EXXX");
		}
		
		// 存在するメッセージIDを渡した場合、正常に取得できること。
		try {
			assertEquals(messageFile.getMessage("M0001"), Locale.getDefault()+"のテストメッセージ{0}");
		} catch (MissingResourceException e) {
			fail(e);
		}
		
		// ====================================================================================================
		// 置換文字列にNULLを渡した場合、置換されずに返却されること。
		try {
			assertEquals(messageFile.getMessage("M0001", null), Locale.getDefault()+"のテストメッセージ{0}");
		} catch (MissingResourceException e) {
			fail(e);
		}
		
		// 置換文字列に空リストを渡した場合、置換されずに返却されること。
		try {
			assertEquals(messageFile.getMessage("M0001", new ArrayList<String>()), Locale.getDefault()+"のテストメッセージ{0}");
		} catch (MissingResourceException e) {
			fail(e);
		}
		
		// 置換文字列を１つを渡した場合、置換され、返却されること。
		try {
			List<String> replaceStrings = new ArrayList<String>();
			replaceStrings.add("test1");
			assertEquals(messageFile.getMessage("M0001", replaceStrings), Locale.getDefault()+"のテストメッセージtest1");
		} catch (MissingResourceException e) {
			fail(e);
		}
		
		// 置換文字列を複数を渡した場合、置換され、返却されること。
		try {
			List<String> replaceStrings = new ArrayList<String>();
			replaceStrings.add("test1");
			replaceStrings.add("test2");
			assertEquals(messageFile.getMessage("M0001", replaceStrings), Locale.getDefault()+"のテストメッセージtest1");
		} catch (MissingResourceException e) {
			fail(e);
		}
		
		// 置換文字列を複数を渡した場合、置換され、返却されること。
		try {
			List<String> replaceStrings = new ArrayList<String>();
			replaceStrings.add("test1");
			replaceStrings.add("test2");
			replaceStrings.add("test3");
			assertEquals(messageFile.getMessage("M0003", replaceStrings), Locale.getDefault()+"のテストメッセージtest1,test2,test3");
		} catch (MissingResourceException e) {
			fail(e);
		}
		
		// 置換文字列にnullが含まれていた場合、nullと置換され、返却されること。
		try {
			List<String> replaceStrings = new ArrayList<String>();
			replaceStrings.add("test1");
			replaceStrings.add(null);
			replaceStrings.add("test3");
			assertEquals(messageFile.getMessage("M0003", replaceStrings), Locale.getDefault()+"のテストメッセージtest1,null,test3");
		} catch (MissingResourceException e) {
			fail(e);
		}
	}
	
	@Test
	public void getKeyList() {
		MessageFile messageFile = new MessageFile("jp/co/dk/message/TestMessage.properties");
		
		// メッセージIDの一覧が返却されること
		try {
			List<String> actual = messageFile.getKeyList();
			assertThat(actual.contains("M0001"), is (true));
			assertThat(actual.contains("M0002"), is (true));
			assertThat(actual.contains("M0003"), is (true));
			assertThat(actual.size(), is (3));
		} catch (MissingResourceException e) {
			fail(e);
		}
	}
	
	@Test
	public void getValueList() {
		MessageFile messageFile = new MessageFile("jp/co/dk/message/TestMessage.properties");
		
		// メッセージ本文の一覧が返却されること
		try {
			List<String> actual = messageFile.getValueList();
			assertThat(actual.contains(Locale.getDefault()+"のテストメッセージ{0}"), is (true));
			assertThat(actual.contains(Locale.getDefault()+"のテストメッセージ{0},{1}"), is (true));
			assertThat(actual.contains(Locale.getDefault()+"のテストメッセージ{0},{1},{2}"), is (true));
			assertThat(actual.size(), is (3));
		} catch (MissingResourceException e) {
			fail(e);
		}
	}
	
	@Test
	public void getBaseName() {
		MessageFile messageFile = new MessageFile("jp/co/dk/message/TestMessage.properties");
		
		// NULLを渡した場合、例外が送出されること。
		try {
			String nullString = null;
			messageFile.getBaseName(nullString);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "File, which is the argument has not been set.");
		}
		
		// 空文字を渡した場合、例外が送出されること。
		try {
			messageFile.getBaseName("");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "File, which is the argument has not been set.");
		}
		
		// 置換する文字列がない場合、その文字列がそのまま返却されること
		try {
			assertEquals(messageFile.getBaseName("test"), "test");
		} catch (IllegalArgumentException e) {
			fail(e);
		}
		
		// 引数にスラッシュが含まれていた場合、.に変換し、返却されること、拡張子が削除されていること
		try {
			assertEquals(messageFile.getBaseName("jp/co/dk/message/TestMessage.properties"), "jp.co.dk.message.TestMessage");
		} catch (IllegalArgumentException e) {
			fail(e);
		}
		
		// 引数にバックスラッシュが含まれていた場合、.に変換し、返却されること、拡張子が削除されていること
		try {
			assertEquals(messageFile.getBaseName("jp\\co\\dk\\message\\TestMessage.properties"), "jp.co.dk.message.TestMessage");
		} catch (IllegalArgumentException e) {
			fail(e);
		}
		
		// 引数に拡張子が削除されていたとしても、正常に処理が行われること。
		try {
			assertEquals(messageFile.getBaseName("jp\\co\\dk\\message\\TestMessage"), "jp.co.dk.message.TestMessage");
		} catch (IllegalArgumentException e) {
			fail(e);
		}
		
		// 引数にドットが複数設定されていた場合、例外が発生すること
		try {
			messageFile.getBaseName("jp.co.dk.message.TestMessage");
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(), "Dot has multiple definitions. Please specify a backslash or slash directory");
		}
		
	}
}
