import observer.ConcreteMember;
import observer.GroupAdmin;
import observer.UndoableStringBuilder;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.openjdk.jol.info.GraphLayout;

import static org.junit.jupiter.api.Assertions.*;

public class Tests {
    public static final Logger logger = LoggerFactory.getLogger(Tests.class);
    // stub method to check external dependencies compatibility
    @Test
    public void test(){
        String s1 = "Alice";
        String s2 = "Bob";

        logger.info(()->JvmUtilities.objectFootprint(s1));

        logger.info(()->JvmUtilities.objectFootprint(s1,s2));

        logger.info(()->JvmUtilities.objectTotalSize(s1));

        logger.info(() -> JvmUtilities.jvmInfo());
    }
    @Test
    public void testRegister() {
        // Create a GroupAdmin object and a Member object
        GroupAdmin groupAdmin = new GroupAdmin("Initial string");
        ConcreteMember member = new ConcreteMember(new UndoableStringBuilder("Hello world"));

        // Register the Member with the GroupAdmin
        groupAdmin.register(member);

        // Check that the Member has been added to the list of members
        assertTrue(groupAdmin.getMembers().contains(member));

        // Check that the Member has received the initial string from the GroupAdmin
        assertEquals("Initial string", member.toString());
    }
    @Test
    void testDoubleRegister() {
        GroupAdmin groupAdmin = new GroupAdmin("Initial string");
        ConcreteMember member = new ConcreteMember(new UndoableStringBuilder("Hello world"));

        // When register twice
        groupAdmin.register(member);
        groupAdmin.register(member);

        // Then the size should be 1
        assertEquals(1, groupAdmin.getMembers().size());
        assertEquals("Initial string", member.getUsbSC().toString());
    }
    @Test
    public void testUnregister() {
        // Create a GroupAdmin object and a Member object
        GroupAdmin groupAdmin = new GroupAdmin("Initial string");
        ConcreteMember member = new ConcreteMember(new UndoableStringBuilder("Hello world"));

        // Register the Member with the GroupAdmin
        groupAdmin.register(member);

        // Unregister the Member from the GroupAdmin
        groupAdmin.unregister(member);

        // Check that the Member has been removed from the list of members
        assertFalse(groupAdmin.getMembers().contains(member));
    }
    @Test
    public void testInsert() throws Exception {
        // Create a GroupAdmin object and a Member object
        GroupAdmin groupAdmin = new GroupAdmin("Initial string");
        ConcreteMember member = new ConcreteMember(new UndoableStringBuilder("Hello world"));

        // Register the Member with the GroupAdmin
        groupAdmin.register(member);

        // Insert a string at the beginning of the GroupAdmin's UndoableStringBuilder
        groupAdmin.insert(0, "Inserted string");

        // Check that the Member's string has been updated with the inserted string
        assertEquals("Inserted stringInitial string", member.toString());
    }
    @Test
    public void testAppend() {
        // Create a GroupAdmin object and a Member object
        GroupAdmin groupAdmin = new GroupAdmin("Initial string");
        ConcreteMember member = new ConcreteMember(new UndoableStringBuilder("Hello world"));

        // Register the Member with the GroupAdmin
        groupAdmin.register(member);

        // Append a string to the GroupAdmin's UndoableStringBuilder
        groupAdmin.append("Appended string");

        // Check that the Member's string has been updated with the appended string
        assertEquals("Initial stringAppended string", member.toString());
    }
    @Test
    public void testDelete() throws Exception {
        // Create a GroupAdmin object and a Member object
        GroupAdmin groupAdmin = new GroupAdmin("Initial string");
        ConcreteMember member = new ConcreteMember(new UndoableStringBuilder("Hello world"));

        // Register the Member with the GroupAdmin
        groupAdmin.register(member);

        // Delete a portion of the GroupAdmin's UndoableStringBuilder
        groupAdmin.delete(7, 14);

        // Check that the Member's string has been updated with the deleted portion removed
        assertEquals("Initial", member.toString());
    }

    @Test
    public void testMemoryUsage() {
        // Initialize the GroupAdmin object
        GroupAdmin groupAdmin = new GroupAdmin("Hello, World!");

        // Measure the memory usage before creating any Member objects
        long before = GraphLayout.parseInstance(groupAdmin).totalSize();

        // Create a large number of Member objects
        for (int i = 0; i < 1000; i++) {
            ConcreteMember member = new ConcreteMember(new UndoableStringBuilder("Hello world"));
            groupAdmin.register(member);
        }

        // Measure the memory usage after creating the Member objects
        long after = GraphLayout.parseInstance(groupAdmin).totalSize();

        // Calculate the difference in memory usage
        long difference = after - before;

        String footprint = JvmUtilities.objectFootprint(groupAdmin);
        long totalSize = GraphLayout.parseInstance(groupAdmin).totalSize();
        String jvmInfo = JvmUtilities.jvmInfo();

        // Verify that there is no unexpected object footprint
        assertTrue(footprint.contains("java.util.ArrayList"), "Unexpected object footprint: " + footprint);
        // The size that given is make sense
        assertTrue(totalSize > 0, "Unexpected total size: " + totalSize);

        // Assert that the difference is below a certain threshold
        assertTrue(difference < 30000);

        // Log results for review
        logger.info(() -> "\nObject footprint: " + footprint);
        logger.info(() -> "\nTotal size: " + totalSize);
        logger.info(() -> "\nJVM info: " + jvmInfo);
    }
    @Test
    public void testUpdate() {
        // Create a concrete member
        ConcreteMember member = new ConcreteMember(new UndoableStringBuilder("Hello"));
        assertEquals(member.getUsbSC().toString(), "Hello");

        // Set the undoable string builder for the sender
        UndoableStringBuilder usb = new UndoableStringBuilder("World");
        member.update(usb);

        // Check that the concrete member has been updated with the new value of the undoable string builder
        assertEquals(member.getUsbSC().toString(), "World");
    }

}
