/**
 * 
 */
package org.simplestructruedata.commons;

import org.simplestructruedata.entities.SSDSetCharacter;

/**
 * @author Jean Villete
 *
 */
public class SSDUtils {
	
	public static boolean isReservedCharacter(char character) {
		for (char reservedCharacter : SSDDefaultConstants.RESERVED_CHARACTERS)
			if (reservedCharacter == character)
				return true;
		return false;
	}
	
	public static String formatEscapes(String base) {
		if (base == null) {
			throw new IllegalArgumentException("parameter base can't be null");
		}
		SSDSetCharacter string = new SSDSetCharacter();
		for (int i = 0; i < base.length(); i++) {
			char character = base.charAt(i);
			if (isReservedCharacter(character)) {
				string.add(SSDDefaultConstants.ESCAPE);
			}
			string.add(character);
		}
		return string.getString();
	}

}
