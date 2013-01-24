import java.io.File;

import org.simplestructruedata.data.SSDContextManager;
import org.simplestructruedata.entities.SSDObjectLeaf;
import org.simplestructruedata.entities.SSDObjectNode;

public class Main {

	public static void main(String[] args) {
//		String dataBase = "{result1 = {message = \"my \\\\ \\\"sample \\\\ message 1\", anotherObj = {column = \"value\"}, code = \"1\"}, result2 = {message = \"my sample message 2\", code = \"2\"}}";
//		System.out.println(dataBase);
//		
//		// FORMAL WAY
//		SSDContextManager formalCtx = SSDContextManager.build(dataBase);
//		SSDObjectNode result1 = (SSDObjectNode) formalCtx.getRootObject().get("result1");
//		SSDObjectLeaf message1 = (SSDObjectLeaf) result1.get("message");
//		System.out.println(message1.getValue());
//		// PRINTING THE CURRENT STRUCTURE
//		System.out.println(formalCtx.getAsString());
//		
//		//INFORMAL WAY
//		SSDContextManager informalCtx = SSDContextManager.build(dataBase);
//		System.out.println(informalCtx.getRootObject().getNode("result2").getLeaf("message").getValue());
//		System.out.println(informalCtx.getRootObject().getNode("result1").getNode("anotherObj").getLeaf("column").getValue());
//		// PRINTING THE CURRENT STRUCTURE
//		System.out.println(informalCtx.getAsString());
		
		File input = new File("c:\\Documents and Settings\\villjea\\Desktop\\temp\\javabee.desc.ssd");
		SSDContextManager ctx = SSDContextManager.build(input);
//		SSDObjectLeaf leaf = ctx.getRootObject().getArray("parameters-array").getArray(2).getLeaf(2);
//		
//		System.out.println(leaf.getIdentifier());
//		System.out.println(leaf.getValue());
		
		System.out.print(ctx.toString());
	}
	
}