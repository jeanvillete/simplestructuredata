/**
 * 
 */
package org.simplestructruedata.entities;

import org.simplestructruedata.exception.SSDIllegalIdentifier;


/**
 * @author Jean Villete
 *
 */
public abstract class SSDObject {

	private String							identifier;
	
	
	public SSDObject(String identifier) {
		identifier = identifier.trim();
		if (identifier ==  null || identifier.isEmpty()) {
			throw new SSDIllegalIdentifier("identifier argument can't be null or empty");
		}
		this.identifier = identifier;
	}
	
	// GETTERS AND SETTERS //
	public String getIdentifier() {
		return identifier;
	}
	
}
