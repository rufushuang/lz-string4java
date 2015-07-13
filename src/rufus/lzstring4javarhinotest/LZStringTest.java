/*
* LZString4Java By Rufus Huang 
* https://github.com/rufushuang/lz-string4java
* MIT License
* 
* Port from original JavaScript version by pieroxy 
* https://github.com/pieroxy/lz-string
*/

package rufus.lzstring4javarhinotest;

import java.io.File;
import java.io.IOException;

import rufus.lzstring4java.LZString;

public class LZStringTest {
	
	public static void main(String[] args) throws IOException {
		String input;
		input = "hello1hello2hello3hello4hello5hello6hello7hello8hello9helloAhelloBhelloChelloDhelloEhelloF";
//		StringBuffer sb = LZStringInRhino.getStringFromJSFile(new File("C:\\large.json"), "GBK");
//		input = sb.toString();
		String compressed;
		long now = System.currentTimeMillis();
		compressed = LZStringInRhino.compressToBase64(input);
		System.out.println("LZStringInRhino.compressToBase64 time(ms): " + (System.currentTimeMillis() - now));
		LZStringInRhino.writeStringToFile(new File("C:\\compressed.json"), compressed, "UTF-8");
		now = System.currentTimeMillis();
		compressed = LZString.compressToBase64(input);
		System.out.println("LZString.compressToBase64 time(ms): " + (System.currentTimeMillis() - now));
		LZStringInRhino.writeStringToFile(new File("C:\\compressed2.json"), compressed, "UTF-8");
	}
	
}
