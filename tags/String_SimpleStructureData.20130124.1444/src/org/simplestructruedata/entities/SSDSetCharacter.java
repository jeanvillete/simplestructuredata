/**
 * 
 */
package org.simplestructruedata.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jean Villete
 *
 */
public class SSDSetCharacter {

	private List<Character> string = new ArrayList<Character>();
	
	public SSDSetCharacter() {
	}
	
	public void add(Character character) {
		this.string.add(character);
	}
	
	public void clear() {
		this.string.clear();
	}
	
	public String getString() {
		char[] string = new char[this.string.size()];
		int i = 0;
		for (char character : this.string) {
			string[i++] = character;
		}
		return new String(string);
	}
	
}
