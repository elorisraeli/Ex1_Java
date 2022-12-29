package observer;

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
        System.out.println(JvmUtilities.objectTotalSize(usb));
        System.out.println(JvmUtilities.objectFootprint(usb));

        System.out.println();
        usb.undo();
        System.out.println(usb);
        usb.undo();
        System.out.println(usb);
        usb.undo();
        System.out.println(usb);
        System.out.println("--------------------------------------------");
        System.out.println(JvmUtilities.jvmInfo());
        System.out.println(JvmUtilities.objectTotalSize(usb));
        System.out.println(JvmUtilities.objectFootprint(usb));

        System.out.println("--------------------------------------------");
        GroupAdmin groupAdmin = new GroupAdmin();
        System.out.println(JvmUtilities.jvmInfo());
        System.out.println(JvmUtilities.objectTotalSize(groupAdmin));

        System.out.println("--------------------------------------------");
        Member concreteMember = new ConcreteMember(usb);
        groupAdmin.register(concreteMember);
        System.out.println(concreteMember);
        System.out.println(JvmUtilities.objectTotalSize(groupAdmin));
        System.out.println("--------------------------------------------");

        Member concreteMember1 = new ConcreteMember(usb);
        groupAdmin.register(concreteMember1);
        System.out.println(concreteMember1);
        System.out.println(JvmUtilities.objectTotalSize(groupAdmin));
        System.out.println("--------------------------------------------");

        Member concreteMember2 = new ConcreteMember(usb);
        groupAdmin.register(concreteMember2);
        System.out.println(concreteMember2);
        System.out.println(JvmUtilities.objectTotalSize(groupAdmin));
        System.out.println("--------------------------------------------");

        Member concreteMember3 = new ConcreteMember(usb);
        groupAdmin.register(concreteMember3);
        System.out.println(concreteMember3);
        System.out.println(JvmUtilities.objectTotalSize(groupAdmin));
        System.out.println("--------------------------------------------");

        Member concreteMember4 = new ConcreteMember(usb);
        groupAdmin.register(concreteMember4);
        System.out.println(concreteMember4);
        System.out.println(JvmUtilities.objectTotalSize(groupAdmin));


        System.out.println(JvmUtilities.objectFootprint(groupAdmin));

        groupAdmin.append("Hello");
        groupAdmin.append("World");

        System.out.println(concreteMember);
        System.out.println("--------------------------------------------");


    }

}
