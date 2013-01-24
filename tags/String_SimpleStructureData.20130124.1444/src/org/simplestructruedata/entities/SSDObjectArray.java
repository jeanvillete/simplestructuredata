/**
 * 
 */
package org.simplestructruedata.entities;

import java.util.ArrayList;
import java.util.List;

import org.simplestructruedata.exception.SSDException;

/**
 * @author Jean Villete
 *
 */
public class SSDObjectArray extends SSDObject {

	private List<SSDObject>			elements = new ArrayList<SSDObject>();
	
	public SSDObjectArray(String identifier) {
		super(identifier);
	}

	public void addElement(SSDObject currentObject) {
		this.elements.add(currentObject);
	}
	
	public SSDObject getElement(int index) {
		return this.elements.get(index);
	}
	
	public SSDObjectNode getNode(int index) {
		SSDObjectNode objectNode;
		try {
			objectNode = (SSDObjectNode) this.getElement(index);
		} catch (Exception e) {
			throw new SSDException("error trying get a SSDObjectNode with the index: " + index + "\n" + e.getMessage());
		}
		return objectNode;
	}
	
	public SSDObjectLeaf getLeaf(int index) {
		SSDObjectLeaf objectLeaf;
		try {
			objectLeaf = (SSDObjectLeaf) this.getElement(index);
		} catch (Exception e) {
			throw new SSDException("error trying get a SSDObjectLeaf with the index: " + index + "\n" + e.getMessage());
		}
		return objectLeaf;
	}
	
	public SSDObjectArray getArray(int index) {
		SSDObjectArray objectArray;
		try {
			objectArray = (SSDObjectArray) this.getElement(index);
		} catch (Exception e) {
			throw new SSDException("error trying get a SSDObjectArray with the index: " + index + "\n" + e.getMessage());
		}
		return objectArray;
	}
	
	public Integer getSize() {
		return this.elements.size();
	}
	
	public String getNextIdentifier() {
		return this.getSize().toString();
	}
	
	// GETTERS AND SETTERS //
	public List<SSDObject> getElements() {
		return this.elements;
	}
	
}
