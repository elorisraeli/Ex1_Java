package observer;

// This class is describing the member who gets the updates
public class ConcreteMember implements Member{
    private UndoableStringBuilder usb;

    public ConcreteMember(UndoableStringBuilder usb){
        this.usb = usb;
    }

    @Override
    public void update(UndoableStringBuilder usb) {
        this.usb = usb;
    }


    public UndoableStringBuilder getUsb() {
        return usb;
    }

    public void setUsb(UndoableStringBuilder usb) {
        this.usb = usb;
    }
}
