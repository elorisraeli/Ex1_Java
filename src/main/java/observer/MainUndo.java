package observer;

import java.util.Arrays;

public class MainUndo {
    public static void main(String[] args) {
        UndoableStringBuilder usb = new UndoableStringBuilder();
        usb.append("to be or not to be");
        System.out.println(usb);
        usb.replace(3, 5, "eat");
        System.out.println(usb);
        usb.replace(17, 19, "eat");
        System.out.println(usb);
        usb.reverse();
        System.out.println(usb);

        System.out.println(JvmUtilities.jvmInfo());

        System.out.println();
        usb.undo();
        System.out.println(usb);
        usb.undo();
        System.out.println(usb);
        usb.undo();
        System.out.println(usb);

        System.out.println(JvmUtilities.jvmInfo());


        GroupAdmin groupAdmin = new GroupAdmin();

        ConcreteMember concreteMember1 = new ConcreteMember();
        groupAdmin.register(concreteMember1);
        groupAdmin.setUsb(usb);
//        groupAdmin.notifyAll(usb);
        groupAdmin.append(", Elor and Roni");
        System.out.println(concreteMember1.getUsb());

        ConcreteMember concreteMember2 = new ConcreteMember();
        concreteMember2.update(groupAdmin.getUsb());
        System.out.println(concreteMember2.getUsb());
        System.out.println(groupAdmin.getUsb());
//        concreteMember2.setUsb(groupAdmin.getUsb());
//        groupAdmin.register(concreteMember2);
        System.out.println(Arrays.asList(groupAdmin.getMembers()));
        groupAdmin.delete(0, 20);
        System.out.println(concreteMember2.getUsb());
        System.out.println(groupAdmin.getUsb());

        ConcreteMember concreteMember3 = new ConcreteMember(usb);
        groupAdmin.insert(5, "the king, ");
        System.out.println(concreteMember3.getUsb());

        System.out.println(JvmUtilities.objectTotalSize(usb));





    }
}
