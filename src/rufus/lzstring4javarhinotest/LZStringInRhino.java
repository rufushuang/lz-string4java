/*
* LZString4Java By Rufus Huang 
* https://github.com/rufushuang/lz-string4java
* MIT License
* 
* Port from original JavaScript version by pieroxy 
* https://github.com/pieroxy/lz-string
*/

package rufus.lzstring4javarhinotest;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.NativeObject;
import org.mozilla.javascript.Script;
import org.mozilla.javascript.Scriptable;


public class LZStringInRhino {

	protected static StringBuffer getStringFromJSFile(File file) throws IOException {
		// file.length() is in bytes count, char counts can be smaller.
		// anyway, ensure the capacity. bigger is ok.
		StringBuffer sb = new StringBuffer((int) file.length());
		FileReader fr = null;
		try {
			fr = new FileReader(file);
			char[] buff = new char[65536];
			int len;
			while ((len = fr.read(buff)) > 0) {
				sb.append(buff, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				if (fr != null)
					fr.close();
			} catch (IOException e) {
			}
		}
		return sb;
	}
	
	public static StringBuffer getStringFromJSFile(File file, String encoding) throws IOException {
		// file.length() is in bytes count, char counts can be smaller.
		// anyway, ensure the capacity. bigger is ok.
		StringBuffer sb = new StringBuffer((int) file.length());
		BufferedReader fr = null;
		try {
			fr = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding));
			char[] buff = new char[65536];
			int len;
			while ((len = fr.read(buff)) > 0) {
				sb.append(buff, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				if (fr != null)
					fr.close();
			} catch (IOException e) {
			}
		}
		return sb;
	}
	
	public static void writeStringToFile(File file, String str, String strencoding) throws IOException {
		BufferedOutputStream bos = null;
		try {
			bos = new BufferedOutputStream(new FileOutputStream(file));
			bos.write(str.getBytes(strencoding));
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				if (bos != null)
					bos.close();
			} catch (IOException e) {
			}
		}
	}
	
	protected static String LZ_STRING_JS_FILE_PATH = System.getProperty("user.dir") + File.separatorChar + "refence" + File.separatorChar + "lz-string-1.3.3-min.js";
	protected static String LZ_STRING_JS;
	protected static Script COMPILED_LZ;
	protected static Context context;
	protected static Scriptable scope;
	protected static NativeObject OBJ_LZString;
	protected static Function FUNC_compressToBase64;
	protected static Function FUNC_decompressFromBase64;
	protected static Function FUNC_compressToUTF16;
	protected static Function FUNC_decompressFromUTF16;
	protected static Function FUNC_compress;
	protected static Function FUNC_decompress;
	static {
		init();
	}
	
	public static void init() {
		try {
			LZ_STRING_JS = getStringFromJSFile(new File(LZ_STRING_JS_FILE_PATH)).toString();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		// should change above JDK6, or just use Nashorn instead of Rhino?
		context = Context.enter();
		scope = context.initStandardObjects();
		context.evaluateString(scope, LZ_STRING_JS, "script", 1, null);
		OBJ_LZString = (NativeObject) scope.get("LZString", scope);
		FUNC_compressToBase64 = (Function) OBJ_LZString.get("compressToBase64", scope);
		FUNC_decompressFromBase64 = (Function) OBJ_LZString.get("decompressFromBase64", scope);
		FUNC_compressToUTF16 = (Function) OBJ_LZString.get("compressToUTF16", scope);
		FUNC_decompressFromUTF16 = (Function) OBJ_LZString.get("decompressFromUTF16", scope);
		FUNC_compress = (Function) OBJ_LZString.get("compress", scope);
		FUNC_decompress = (Function) OBJ_LZString.get("decompress", scope);
	}

	public static String compressToBase64(String input) {
		return (String) FUNC_compressToBase64.call(context, scope, scope, new Object[] { input });
	}

	public static String decompressFromBase64(String input) {
		return (String) FUNC_decompressFromBase64.call(context, scope, scope, new Object[] { input });
	}

	public static String compressToUTF16(String input) {
		return (String) FUNC_compressToUTF16.call(context, scope, scope, new Object[] { input });
	}

	public static String decompressFromUTF16(String input) {
		return (String) FUNC_decompressFromUTF16.call(context, scope, scope, new Object[] { input });
	}

	public static String compress(String input) {
		return (String) FUNC_compress.call(context, scope, scope, new Object[] { input });
	}

	public static String decompress(String input) {
		return (String) FUNC_decompress.call(context, scope, scope, new Object[] { input });
	}

	public static void main(String[] args) throws IOException {
		String input;
//		input = "hello";
		input = "hello1hello2hello3hello4hello5hello6hello7hello8hello9helloAhelloBhelloChelloDhelloEhelloF";
	
		System.out.println(decompress(compress(input)));
		System.out.println(decompressFromBase64(compressToBase64(input)));
		System.out.println(decompressFromUTF16(compressToUTF16(input)));	
	}
}
