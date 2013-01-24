/**
 * 
 */
package org.simplestructruedata.commons;

/**
 * @author Jean Villete
 *
 */
public interface SSDDefaultConstants {

	char					OPENS_BRACES = '{';
	char					CLOSES_BRACES = '}';
	char					QUOTATION_MARKS = '"';
	char					COMMA = ',';
	char					OPENS_BRACKETS = '[';
	char					CLOSES_BRACKETS = ']';
	char					ASSIGN = '=';
	char					ESCAPE = '\\';
	char					NEW_LINE = '\n';
	char					TAB = '\t';
	
	/**
	 * These are the reserved characters: 	{ (opens braces)
	 * 										} (closes braces)
	 * 										" (quotation marks)
	 * 										, (comma)
	 * 										[ (opens brackets)
	 * 										] (closes brackets)
	 * 
	 * These are characters to escape: 		\\ (escape)
	 * 										\n (new line)
	 * 										\t (a tab)
	 */
	char[]					RESERVED_CHARACTERS = new char[]{OPENS_BRACES,
															CLOSES_BRACES, 
															QUOTATION_MARKS, 
															COMMA,
															OPENS_BRACKETS,
															CLOSES_BRACKETS,
															ASSIGN, 
															ESCAPE, 
															NEW_LINE, 
															TAB};
	
	String					ROOT_OBJECT = "root_object";
	
	String					DEFAULT_ENCODING = "UTF-8";
}
