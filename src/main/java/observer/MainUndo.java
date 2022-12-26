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

        JvmUtilities.jvmInfo();

        System.out.println();
        usb.undo();
        System.out.println(usb);
        usb.undo();
        System.out.println(usb);
        usb.undo();
        System.out.println(usb);


        JvmUtilities.jvmInfo();

    }
}
