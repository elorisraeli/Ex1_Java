
import observer.ConcreteMember;
import observer.GroupAdmin;
import observer.JvmUtilities;
import observer.Member;
import observer.UndoableStringBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class is used to test the observer pattern.
 * It is used to test the observer and observable pattern.
 * with the UndoableStringBuilder, GroupAdmin and ConcreteMember classes.
 */
public class Tests {
    /**
     * Set the test variables.
     * Using GroupAdmin to test the sender.
     * Using ConcreteMember to test the member.
     * Using UndoableStringBuilder to test the usb object.
     * Using Logger to print the test results.
     */
    public static final Logger logger = LoggerFactory.getLogger(Tests.class);
    GroupAdmin groupAdmin = new GroupAdmin();
    UndoableStringBuilder usb = new UndoableStringBuilder();


    /**
     * This test is the test we got as an example in the assignment.
     */
    @Test
    public void test(){
        String s1 = "Alice";
        String s2 = "Bob";

        logger.info(()->JvmUtilities.objectFootprint(s1));

        logger.info(()->JvmUtilities.objectFootprint(s1,s2));

        logger.info(()->JvmUtilities.objectTotalSize(s1));

        logger.info(() -> JvmUtilities.jvmInfo());
        System.out.println("Done test");
    }

    /**
     * This test using BeforeEach to initialize the variables before each test.
     * Also prints the object footprints size of the variables.
     */
    @BeforeEach
    public void setUp(){
        groupAdmin = new GroupAdmin();
        usb = new UndoableStringBuilder();
        groupAdmin.setUsb(usb);
        System.out.println("--------------------------------------------");
        System.out.println(" ");
        System.out.println("GroupAdmin size: " + JvmUtilities.objectTotalSize(groupAdmin));
        System.out.println("UndoableStringBuilder size: " + JvmUtilities.objectTotalSize(usb));
    }

    /**
     * This test using AfterEach to print the
     * object footprints size of the variables after each test.
     * Also set the variables to null after each test.
     */
    @AfterEach
    public void heapMemory(){
        System.out.println("GroupAdmin size: " + JvmUtilities.objectTotalSize(groupAdmin));
        System.out.println("UndoableStringBuilder size: " + JvmUtilities.objectTotalSize(usb));
        System.out.println(" ");
        System.out.println("--------------------------------------------");
        System.out.println("  ");
        groupAdmin = null;
        usb = null;
    }

    /**
     *  This test is to check the register method in GroupAdmin class
     *  The test is to check if the member is added to the list
     */
    @Test
    void register() {
        usb.append("to be or not to be");
        System.out.println(usb);
        usb.replace(3, 5, "eat");
        System.out.println(usb);
        usb.replace(17, 19, "eat");
        System.out.println(usb);
        usb.reverse();
        System.out.println(usb);
        ConcreteMember concreteMember = new ConcreteMember();
        groupAdmin.register(concreteMember);
        concreteMember.update(groupAdmin.getUsb());
        assertEquals(concreteMember.getUsb(), usb);
        assertEquals(groupAdmin.getMembers().get(groupAdmin.getMembers().size()-1), concreteMember);
        System.out.println("Done register");
    }


    /**
     *  This test is to check the unregister method in GroupAdmin class
     *  The test is to check if the member is removed from the list
     */
    @Test
    void unregister() {
        usb.append("to be or not to be");
        System.out.println(usb);
        ConcreteMember concreteMember = new ConcreteMember();

        groupAdmin.register(concreteMember);
        concreteMember.update(groupAdmin.getUsb());
        assertEquals(groupAdmin.getMembers().get(groupAdmin.getMembers().size()-1), concreteMember);
        assertEquals(concreteMember.getUsb(), usb);

        groupAdmin.unregister(concreteMember);
        assertEquals(groupAdmin.getMembers().size(), 0);

        System.out.println("Done unregister");
    }

    /**
     *  This test is to check the insert method in GroupAdmin class
     *  The test is to check if the member is updated after the insert method is called
     */
    @Test
    void insert() {
        ConcreteMember concreteMember = new ConcreteMember();
        groupAdmin.register(concreteMember);
        usb.append("to be or not to be");
        usb.insert(9, "(the other option) ");
        concreteMember.update(groupAdmin.getUsb());
        assertEquals(concreteMember.getUsb(), usb);
        System.out.println("Done insert");

    }

    /**
     * This test is to check the append method in GroupAdmin class
     * The test is to check if the member is updated after the append method is called
     */
    @Test
    void append() {
        ConcreteMember concreteMember = new ConcreteMember();
        groupAdmin.register(concreteMember);
        usb.append("to be or not to be");
        usb.append("? that is the the question");
        concreteMember.update(groupAdmin.getUsb());
        assertEquals(concreteMember.getUsb(), usb);
        System.out.println("Done append");
    }

    /**
     * This test is to check the delete method in GroupAdmin class
     * The test is to check if the member is updated after the delete method is called
     */
    @Test
    void delete() {
        ConcreteMember concreteMember = new ConcreteMember();
        groupAdmin.register(concreteMember);
        usb.append("to be or not to be");
        usb.append("? that is the the question");
        usb.delete(9, 19);
        concreteMember.update(groupAdmin.getUsb());
        assertEquals(concreteMember.getUsb(), usb);
        System.out.println("Done delete");
    }

    /**
     * This test is to check the undo method in GroupAdmin class
     * The test is to check if the member is updated after the undo method is called
     */
    @Test
    void undo() {
        ConcreteMember concreteMember = new ConcreteMember();
        groupAdmin.register(concreteMember);
        usb.undo();
        concreteMember.update(groupAdmin.getUsb());
        assertEquals(concreteMember.getUsb(), usb);
        System.out.println("Done undo");
    }

    /**
     * This test is to check the updateAll method in GroupAdmin class
     * The test is to check if the members are updated after the updateAll method is called
     */
    @Test
    void updateAll() {
        ConcreteMember concreteMember1 = new ConcreteMember();
        ConcreteMember concreteMember2 = new ConcreteMember();
        ConcreteMember concreteMember3 = new ConcreteMember();
        ConcreteMember concreteMember4 = new ConcreteMember();
        groupAdmin.register(concreteMember1);
        groupAdmin.register(concreteMember2);
        groupAdmin.register(concreteMember3);
        groupAdmin.register(concreteMember4);
        usb.append("to be or not to be");
        groupAdmin.append(". Extra text in the USB");
        assertEquals(concreteMember1.getUsb(), usb);
        assertEquals(concreteMember2.getUsb(), usb);
        assertEquals(concreteMember3.getUsb(), usb);
        assertEquals(concreteMember4.getUsb(), usb);
        System.out.println("Done updateAll");
    }


}
