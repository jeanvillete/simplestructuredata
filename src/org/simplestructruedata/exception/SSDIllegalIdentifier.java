/**
 * 
 */
package org.simplestructruedata.exception;

/**
 * @author villjea
 *
 */
public class SSDIllegalIdentifier extends SSDException {

	private static final long serialVersionUID = 6888291089622549937L;

	public SSDIllegalIdentifier() { }

	public SSDIllegalIdentifier(String message) {
		super(message);
	}
	
	public SSDIllegalIdentifier(Exception e) {
		super(e);
	}
	
}
