package observer;

// This class is describing the member who gets the updates
public class ConcreteMember implements Member{
    private UndoableStringBuilder usb;

    @Override
    public void update(UndoableStringBuilder usb) {
        this.usb = usb;
    }

    public ConcreteMember(){
        this.usb = new UndoableStringBuilder();
    }

    public ConcreteMember(UndoableStringBuilder usb){
        this.usb = usb;
    }


    public UndoableStringBuilder getUsb() {
        return usb;
    }

    public void setUsb(UndoableStringBuilder usb) {
        this.usb = usb;
    }

}
