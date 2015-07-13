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
		System.out.println("\nLZStringInRhino(133).compressToBase64 time(ms): " + (System.currentTimeMillis() - now) + "\n" + compressed);
//		LZStringInRhino.writeStringToFile(new File("C:\\compressed.json"), compressed, "UTF-8");
		now = System.currentTimeMillis();
		compressed = LZStringInRhino144.compressToBase64(input);
		System.out.println("\nLZStringInRhino144.compressToBase64 time(ms): " + (System.currentTimeMillis() - now) + "\n" + compressed);
//		LZStringInRhino.writeStringToFile(new File("C:\\compressed.json"), compressed, "UTF-8");
		now = System.currentTimeMillis();
		compressed = LZString.compressToBase64(input);
		System.out.println("\nLZString.compressToBase64 time(ms): " + (System.currentTimeMillis() - now) + "\n" + compressed);
//		LZStringInRhino.writeStringToFile(new File("C:\\compressed2.json"), compressed, "UTF-8");

		// compressToEncodedURIComponent test
		now = System.currentTimeMillis();
		compressed = LZStringInRhino144.compressToEncodedURIComponent(input);
		System.out.println("\nLZStringInRhino144.compressToEncodedURIComponent time(ms): " + (System.currentTimeMillis() - now) + "\n" + compressed);

		now = System.currentTimeMillis();
		compressed = LZString.compressToEncodedURIComponent(input);
		System.out.println("\nLZString.compressToEncodedURIComponent time(ms): " + (System.currentTimeMillis() - now) + "\n" + compressed);

	}
	
}