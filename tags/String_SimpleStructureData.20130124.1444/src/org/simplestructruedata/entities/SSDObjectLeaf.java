/**
 * 
 */
package org.simplestructruedata.entities;

/**
 * @author Jean Villete
 *
 */
public class SSDObjectLeaf extends SSDObject {

	private String					value;
	
	public SSDObjectLeaf(String identifier) {
		super(identifier);
	}
	
	public SSDObjectLeaf(String identifier, String value) {
		super(identifier);
		this.value = value;
	}

	// GETTERS AND SETTERS //
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
