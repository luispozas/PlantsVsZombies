package tp.p3.utility;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files ;
import java.nio.file.InvalidPathException;
import tp.p3.exceptions.FileContentsException;

public class MyFileUtils {
	
	public static final String wrongPrefixMsg = "unknown game attribute: ";
	public static final String lineTooLongMsg = "too many words on line commencing: ";
	public static final String lineTooShortMsg = "missing data on line commencing: ";
		
	
	// returns true if string argument is a valid filename
	public static boolean isValidFilename(String filename) {
		try {
			Paths.get(filename);
			return true;
		} catch (InvalidPathException ipe) {
			return false;
		}
	}
	// returns true if file with that name exists (in which case, it may not be accessible )
	public static boolean fileExists(String filename) {
		try {
			Path path = Paths.get(filename);
			return Files.exists (path) && Files.isRegularFile(path);
		} catch (InvalidPathException ipe) {
			return false; // filename invalid => file cannot exist
		}
	}
	// returns true if file with that name exists and is readable
	public static boolean isReadable(String filename) {
		try {
			Path path = Paths.get(filename);
			return Files.exists (path) && Files.isRegularFile(path) && Files.isReadable(path);
		} catch (InvalidPathException ipe) {
			return false; // filename invalid => file cannot exist , never mind be readable
		}
	}
		
	public static String[] loadLine(BufferedReader inStream, String prefix, boolean isList) throws IOException, FileContentsException{
		String[] words;
		String line = inStream.readLine().trim();
		if (!line.startsWith(prefix + ":")) throw new FileContentsException(wrongPrefixMsg + prefix);
		String contentString = line.substring(prefix.length()+1).trim();
		if (!contentString.equals("")) {
			if (!isList){
				words = contentString.split ("\\s+");
				if (words.length != 1) throw new FileContentsException(lineTooLongMsg + prefix);
			}else words = contentString.split (",\\s*");
		}else{
			if (!isList) throw new FileContentsException(lineTooShortMsg + prefix);
			words = new String[0];
		}
		return words;
	}
}
