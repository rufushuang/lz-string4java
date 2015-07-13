lz-string4java
=========
Port javascript lz-string in JAVA version. 

Port from original JavaScript version by pieroxy
https://github.com/pieroxy/lz-string

Done some of language optimization after porting.

Porting Version CAUTION !!
=========
Original LZString is not all version compatible.

From version 1.3.3 to version 1.4.4, there are some incompatible changes in compressToBase64/decompressFromBase64. Same as EncodedURIComponent. Use different will result in failure.

So just keep appropriate/same version on your web page javascript side and server java side.

IF YOU USE LZ-String VERSION WHICH BACKWARDS OF 1.4.4 and have problem with Base64/URIComponent, go to the release part and get the 1.3.3 release to have a try.

## License
MIT License


## Rhino Testing
See rufus.lzstring4javarhinotest.LZStringTest. Only the testing package need rhino.

If you're not intrestring in testing, just get the rufus.lzstring4java package only, no rhino or any other third party package is needed.