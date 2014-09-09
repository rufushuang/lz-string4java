/*
* LZString4Java By Rufus Huang 
* https://github.com/rufushuang/lz-string4java
* MIT License
* 
* Port from original JavaScript version by pieroxy 
* https://github.com/pieroxy/lz-string
*/

package rufus.lzstring4javarhinotest;

import java.io.IOException;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.NativeObject;
import org.mozilla.javascript.Script;
import org.mozilla.javascript.Scriptable;


public class LZString102InRhino {

	protected static Script COMPILED_LZ;
	protected static Context context;
	protected static Scriptable scope;
	protected static NativeObject OBJ_LZString;
//	protected static Function FUNC_compressToBase64;
//	protected static Function FUNC_decompressFromBase64;
//	protected static Function FUNC_compressToUTF16;
//	protected static Function FUNC_decompressFromUTF16;
	protected static Function FUNC_compress;
	protected static Function FUNC_decompress;
	static {
		// should change above JDK6, or just use Nashorn instead of Rhino?
		context = Context.enter();
		scope = context.initStandardObjects();
		context.evaluateString(scope, LZStringInRhino.LZ_STRING_JS, "script", 1, null);
		OBJ_LZString = (NativeObject) scope.get("LZString", scope);
		FUNC_compress = (Function) OBJ_LZString.get("compress", scope);
		FUNC_decompress = (Function) OBJ_LZString.get("decompress", scope);
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
	}
}
