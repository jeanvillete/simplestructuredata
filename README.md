# simplestructuredata
An alternative data structure to XML and JSON [IN PROGRESS]

A few time ago I would like to have a data structure flexible and really simple!

Nobody can wonder about XML's power (representation data), really it's very good, but I was looking for a new way to representation data that could be as just flexible as XML, but furthermore, simpler.

Well, I didn't find out actually what I was looking for!

And this is my motto that I've created by myself the structure and I've called as SSD, an acronym to Simple Structure Data, and you'll understand why it is Simple (at least I hope so!).

The sample representation data can be understand as;

sample 1

{
        root_attribute_as_leaf = "an information leaf"
        , root_attribute_as_object = {
                an_inner_attribute_as_leaf = "an inner information leaf"
        }
        , root_attribute_as_array = [
                "this is a simple information leaf that is inside an array"
                , {
                        array_attribute = "simple information leaf that is inside an object that is inside an array"
                }
        ]
}
You can say, I think I saw it in somewhere...

... and you're right, a lot of languages makes use of structures like that, for example, JSON do.

But let us talk more about SSD;

All the information represented by SSD is represented as a Tree Data Structure, where you have a "root", so "nodes" and "leaf" objects and data.

In the sample above the root object are the first BRACES

sample 2

{
        ...
}
Inside the Root Object, you can have NodeObjects?, ArrayObjects? and LeafObjects?.

First of all you must understand that;

Objects can have attributes, and the RootObject? is an Object, so;

sample 3

{ //root object
        root_object_attribute = "leaf value"
}
The sample above (sample 3) is valid, and the root must (or can) have attributes, that these attributes might be another objects (NodeObjects?, ArrayObjects? or LeafObjects?) that on that case above the attribute is a LeafObject?.

Going straight ahead, let's start with the library, that is nothing more than a parser to SSD, it'll help us understand some points!

Take a look at this Java code;

...
        String input = // lets supose that we have here the string of the sample 1
        SSDContextManager ssdCtx = SSDContextManager.build(input)

        // getting the root
        SSDRootObject root = ssdCtx.getRootObject();
...
Once you have the root object, you can browse throughout information inside it, as the three following code, we're printing the first leaf object value.

formal way

...
        SSDObject ssdObject = root.get("root_attribute_as_leaf");
        SSDObjectLeaf ssdLeaf = (SSDObjectLeaf) ssdObject;
        System.out.println(ssdLeaf.getValue());
...
informal way

...
        SSDObjectLeaf ssdLeaf = root.getLeaf("root_attribute_as_leaf");
        System.out.println(ssdLeaf.getValue());
...
unchecked

...
        System.out.println(root.getLeaf("root_attribute_as_leaf").getValue());
...
Take a look at this full example, and enjoy the ways that you can use the library. (All are unchecked)

getting an attribute object and printing its parameter value

...
        System.out.println(root.getNode("root_attribute_as_object").getLeaf("an_inner_attribute_as_leaf"));
...
printing a leaf object inside our sample array

...
        System.out.println(root.getArray("root_attribute_as_array").getLeaf(0));
...
getting a node object inside our sample array, and printing the leaf value from that object

...
        System.out.println(root.getArray("root_attribute_as_array").getNode(1).getLeaf("array_attribute"));
...
After all, you can print the current state inside the context;

printing/getting the String SSD from the context

...
        System.out.print(ssdCtx.toString());
...
printing the context data to a file

...
        File targetFile = new File("/myDirectory/myFile.txt");
        ssdCtx.toFile(targetFile);
...
Enjoy it and see you!
