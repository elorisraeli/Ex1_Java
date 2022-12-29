
import observer.ConcreteMember;
import observer.GroupAdmin;
import observer.UndoableStringBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

public class Tests {
    public static final Logger logger = LoggerFactory.getLogger(Tests.class);
    // stub method to check external dependencies compatibility
    GroupAdmin groupAdmin = new GroupAdmin();
    UndoableStringBuilder usb = new UndoableStringBuilder();

    @Test
    public void test(){
        String s1 = "Alice";
        String s2 = "Bob";

        logger.info(()->JvmUtilities.objectFootprint(s1));

        logger.info(()->JvmUtilities.objectFootprint(s1,s2));

        logger.info(()->JvmUtilities.objectTotalSize(s1));

        logger.info(() -> JvmUtilities.jvmInfo());
    }

    @BeforeEach
    public void setUp(){
        groupAdmin = new GroupAdmin();
        usb = new UndoableStringBuilder();
        groupAdmin.setUsb(usb);
        System.out.println("--------------------------------------------");
        System.out.println("Before");
        System.out.println("GroupAdmin size: " + JvmUtilities.objectTotalSize(groupAdmin));
        System.out.println("UndoableStringBuilder size: " + JvmUtilities.objectTotalSize(usb));
    }
    @AfterEach
    public void heapMemory(){
        System.out.println("GroupAdmin size: " + JvmUtilities.objectTotalSize(groupAdmin));
        System.out.println("UndoableStringBuilder size: " + JvmUtilities.objectTotalSize(usb));
        System.out.println("After");
        System.out.println("--------------------------------------------");
        System.out.println("  ");
        groupAdmin = null;
        usb = null;
    }




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
        System.out.println("Done register");
    }

    @Test
    void unregister() {
        usb.append("to be or not to be");
        System.out.println(usb);
        ConcreteMember concreteMember = new ConcreteMember();

        groupAdmin.register(concreteMember);
        assertEquals(groupAdmin.getMembers().get(groupAdmin.getMembers().size()-1), concreteMember);
        assertEquals(concreteMember.getUsb(), usb);

        groupAdmin.unregister(concreteMember);
        assertEquals(groupAdmin.getMembers().size(), 0);

        System.out.println("Done unregister");
    }

    @Test
    void insert() {
        ConcreteMember concreteMember = new ConcreteMember();
        groupAdmin.register(concreteMember);
        usb.insert(9, "(the other option) ");
        assertEquals(concreteMember.getUsb(), usb);
        System.out.println("Done insert");

    }

    @Test
    void append() {
        ConcreteMember concreteMember = new ConcreteMember();
        groupAdmin.register(concreteMember);
        usb.append("? that is the the question");
        assertEquals(concreteMember.getUsb(), usb);
        System.out.println("Done append");
    }

    @Test
    void delete() {
        ConcreteMember concreteMember = new ConcreteMember();
        groupAdmin.register(concreteMember);
        usb.delete(9, 19);
        assertEquals(concreteMember.getUsb(), usb);
        System.out.println("Done delete");
    }

    @Test
    void undo() {
        ConcreteMember concreteMember = new ConcreteMember();
        groupAdmin.register(concreteMember);
        usb.undo();
        assertEquals(concreteMember.getUsb(), usb);
        System.out.println("Done undo");
    }

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
        groupAdmin.append(". Extra text in the USB");
        assertEquals(concreteMember1.getUsb(), usb);
        assertEquals(concreteMember2.getUsb(), usb);
        assertEquals(concreteMember3.getUsb(), usb);
        assertEquals(concreteMember4.getUsb(), usb);
        System.out.println("Done updateAll");
    }


}
