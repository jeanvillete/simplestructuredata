/**
 * 
 */
package org.simplestructruedata.exception;

/**
 * @author villjea
 *
 */
public class SSDIllegalArgument extends IllegalArgumentException {

	private static final long serialVersionUID = 1857030767462392120L;

	public SSDIllegalArgument() { }

	public SSDIllegalArgument(String message) {
		super(message);
	}
	
	public SSDIllegalArgument(Exception e) {
		super(e);
	}
	
}
