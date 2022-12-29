# OOP.Assignment1

This project implements the Observer pattern in Java, using the ConcreteMember and GroupAdmin classes.

## calss 

*GroupAdmin
This is a Java class that implements the Sender interface and represents a group administrator who is responsible for
maintaining a list of members and a message that is shared with all the members of the group.

The group administrator maintains an instance of the UndoableStringBuilder class, which represents a string that
can be modified using various methods such as insert, append, and delete, and also allows undoing the most recent modification
 using the undo method.

The group administrator has methods for registering and unregistering members, and for modifying the message using
the insert, append, and delete methods. When a modification is made to the message, the group administrator calls the
notifyObserver method, which in turn calls the update method on each of the registered members, passing the modified message
as an argument. This allows the members to be notified of the change and update their copy of the message accordingly.

*ConcreteMember
The ConcreteMember class is an implementation of the Member interface. It has three constructors: one that takes
 an UndoableStringBuilder object as an argument, one that takes a Sender object as an argument, and one that takes both a
 Sender and an UndoableStringBuilder object as arguments.

This project also using the JvmUtilities class to get information about memory usege.
The JvmUtilities class is a utility class that provides methods for tracking the Java Virtual Machine (JVM) resources allocated by the Operating System.
The jvmInfo method returns information about the JVM, including the process id, total memory allocated at the beginning of the program, and the number of available cores.
The objectFootprint and objectTotalSize methods calculate the shallow and deep size footprint, respectively, of the given object or objects. The memoryStats method combines these two methods and returns a string with both the total size and footprint of the given object.
The ProcessHandle class is a class in the Java API that provides access to information about native processes. The Runtime class is a class in the Java API that provides access to the Java runtime system. The GraphLayout class is a class in the org.openjdk.jol package that provides methods for analyzing the layout of objects in memory.
