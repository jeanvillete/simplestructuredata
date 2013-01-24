/**
 * 
 */
package org.simplestructruedata.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.simplestructruedata.commons.SSDDefaultConstants;
import org.simplestructruedata.commons.SSDUtils;
import org.simplestructruedata.entities.SSDObject;
import org.simplestructruedata.entities.SSDObjectArray;
import org.simplestructruedata.entities.SSDObjectLeaf;
import org.simplestructruedata.entities.SSDObjectNode;
import org.simplestructruedata.entities.SSDSetCharacter;
import org.simplestructruedata.exception.SSDException;
import org.simplestructruedata.exception.SSDIllegalArgument;

/**
 * @author Jean Villete
 *
 */
public class SSDContextManager {
	
	private List<SSDObject>			heap = new ArrayList<SSDObject>();
	private int						currentReference = 0;
	
	public class SSDRootObject extends SSDObjectNode {
		public SSDRootObject() {
			super(SSDDefaultConstants.ROOT_OBJECT);
		}
	}
	
	private SSDContextManager() {
		this.addToHeap(new SSDRootObject());
	}
	
	public static SSDContextManager build() {
		return build("{}");
	}
	
	public static SSDContextManager build(File fileBase) {
		if (fileBase == null || !fileBase.isFile()) {
			throw new SSDIllegalArgument("the argument fileBase is null or is not a valid file");
		}
		try {
			FileInputStream fis = new FileInputStream(fileBase);
			InputStreamReader isr = new InputStreamReader(fis, SSDDefaultConstants.DEFAULT_ENCODING);
			BufferedReader br = new BufferedReader(isr);
			StringBuffer string = new StringBuffer();
			String reading = null;
			while ((reading = br.readLine()) != null) {
				string.append(reading);
			}
			fis.close();
			isr.close();
			br.close();
			return build(string.toString());
		} catch (FileNotFoundException e) {
			throw new SSDException(e);
		} catch (IOException e) {
			throw new SSDException(e);
		}
	}
	
	public static SSDContextManager build(String dataBase) {
		if (dataBase == null || dataBase.isEmpty()) {
			throw new SSDIllegalArgument("the argument dataBase is null or is empty");
		}
		dataBase = dataBase.trim();
		if (dataBase.charAt(0) != SSDDefaultConstants.OPENS_BRACES) {
			throw new SSDException("The string base must starts with:" + SSDDefaultConstants.OPENS_BRACES);
		} else {
			SSDContextManager ctx = new SSDContextManager();
			
			SSDSetCharacter string = new SSDSetCharacter();
			for (int i = 1; i < dataBase.length(); i++) {
				char currentChar = dataBase.charAt(i);
				if (SSDUtils.isReservedCharacter(currentChar)) {
					// special escapade character
					if (currentChar == SSDDefaultConstants.ESCAPE) {
						currentChar = dataBase.charAt(++i);
						string.add(currentChar);
						continue;
					}
					// special assignment or comma characters
					if (currentChar == SSDDefaultConstants.ASSIGN || currentChar == SSDDefaultConstants.COMMA) {
						currentChar = dataBase.charAt(++i);
					}
					// special opens or closes braces characters
					if (currentChar == SSDDefaultConstants.OPENS_BRACES) {
						if (ctx.getCurrentObject() instanceof SSDObjectArray) {
							SSDObjectArray objectArray = (SSDObjectArray)ctx.getCurrentObject();
							ctx.addToHeap(new SSDObjectNode(objectArray.getNextIdentifier()));
						} else {
							ctx.addToHeap(new SSDObjectNode(string.getString()));
						}
						string.clear();
						continue;
					} else if (currentChar == SSDDefaultConstants.CLOSES_BRACES) {
						ctx.closeObject();
						string.clear();
						continue;
					}
					// special opens or closes brackets characters
					if (currentChar == SSDDefaultConstants.OPENS_BRACKETS) {
						if (ctx.getCurrentObject() instanceof SSDObjectArray) {
							SSDObjectArray objectArray = (SSDObjectArray)ctx.getCurrentObject();
							ctx.addToHeap(new SSDObjectArray(objectArray.getNextIdentifier()));
						} else {
							ctx.addToHeap(new SSDObjectArray(string.getString()));
						}
						string.clear();
						continue;
					} else if (currentChar == SSDDefaultConstants.CLOSES_BRACKETS) {
						ctx.closeObject();
						string.clear();
						continue;
					}
					// special quotation marks character
					if (currentChar == SSDDefaultConstants.QUOTATION_MARKS) {
						if (ctx.getCurrentObject() instanceof SSDObjectLeaf) {
							SSDObjectLeaf objectLeaf = (SSDObjectLeaf) ctx.getCurrentObject();
							objectLeaf.setValue(string.getString());
							ctx.closeObject();
						} else if (ctx.getCurrentObject() instanceof SSDObjectArray) {
							SSDObjectArray objectArray = (SSDObjectArray)ctx.getCurrentObject();
							ctx.addToHeap(new SSDObjectLeaf(objectArray.getNextIdentifier()));
						} else {
							ctx.addToHeap(new SSDObjectLeaf(string.getString()));
						}
						string.clear();
						continue;
					}
				}
				string.add(currentChar);
			}
			return ctx;
		}
	}
	
	private void addToHeap(SSDObject ssdObject) {
		this.heap.add(this.currentReference++, ssdObject);
	}
	
	private SSDObject getCurrentObject() {
		return this.heap.get(this.currentReference-1);
	}
	
	private SSDObject getAboveObject() {
		return this.heap.get(this.currentReference-2);
	}
	
	private void closeObject() {
		if (this.getCurrentObject() instanceof SSDRootObject) {
			return;
		}
		SSDObject object = this.getAboveObject();
		if (object instanceof SSDObjectNode) {
			SSDObjectNode nodeObject = (SSDObjectNode) object;
			nodeObject.addAttribute(this.getCurrentObject());
		} else if (object instanceof SSDObjectArray) {
			SSDObjectArray objectArray = (SSDObjectArray) object;
			objectArray.addElement(this.getCurrentObject());
		} else throw new SSDException("Invalid type to this point");
		this.currentReference--;
	}
	
	public SSDRootObject getRootObject() {
		if (this.heap.size() > 1) {
			int currentSize = this.heap.size();
			for (int i = 1; i < currentSize; i++ ) {
				this.heap.remove(currentSize - i);
			}
		}
		return (SSDRootObject) this.heap.get(0);
	}
	
	@Override
	public String toString() {
		return this.toString(this.getRootObject(), null, "");
	}
	
	public void toFile(File targetFile) {
		if (targetFile == null) {
			throw new SSDException("The parameter targetFile can't be null");
		}
		try {
			if (!targetFile.exists()) {
				targetFile.createNewFile();
			}
			
			FileOutputStream fos = new FileOutputStream(targetFile);
			OutputStreamWriter osw = new OutputStreamWriter(fos, SSDDefaultConstants.DEFAULT_ENCODING);
			PrintWriter pw = new PrintWriter(osw);
			pw.print(this.toString());
			pw.close();
			osw.close();
			fos.close();
		} catch (Exception e) {
			throw new SSDException(e);
		}
	}
	
	private String toString(SSDObject object, String identifier, String currentTab) {
		StringBuffer returning = new StringBuffer();
		if (identifier != null && !identifier.isEmpty()) {
			returning.append(object.getIdentifier() + " = ");
		}
		
		if (object instanceof SSDObjectLeaf) {
			SSDObjectLeaf objectLeaf = (SSDObjectLeaf)object;
			returning.append("\"");
			returning.append(SSDUtils.formatEscapes(objectLeaf.getValue()));
			returning.append("\"\n");
		} else if (object instanceof SSDObjectNode) {
			SSDObjectNode objectNode = (SSDObjectNode)object;
			returning.append("{\n");
			List<SSDObject> attributes = new ArrayList<SSDObject>(objectNode.getAttributes());
			int attributeSize = attributes.size();
			if (attributeSize > 0) {
				for (int i = 0; i < attributeSize; i++) {
					String nextTab = currentTab+"\t";
					returning.append(nextTab);
					if (i > 0) {
						returning.append(", ");
					}
					SSDObject attribute = attributes.get(i);
					returning.append(this.toString(attribute, attribute.getIdentifier(), nextTab));
				}
			}
			returning.append(currentTab);
			returning.append("}\n");
		} else if (object instanceof SSDObjectArray) {
			SSDObjectArray objectArray = (SSDObjectArray)object;
			returning.append("[\n");
			List<SSDObject> elements = objectArray.getElements();
			for (int i = 0; i < elements.size(); i++ ) {
				String nextTab = currentTab+"\t";
				returning.append(nextTab);
				if (i > 0) {
					returning.append(", ");
				}
				returning.append(this.toString(elements.get(i), null, nextTab ));
			}
			returning.append(currentTab);
			returning.append("]\n");
		} else throw new SSDException("Invalid type to this point");
		return returning.toString();
	}
}
