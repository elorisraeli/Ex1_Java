package observer;

// This class is describing the member who gets the updates

/**
 * This class is describing the member who gets the updates,
 * This class is describing the observer the on who get updates from the sender
 *
 * @author Elor Israeli
 * @author Roni Michaeli
 * @version 1.0 30 Dec 2022
 */
public class ConcreteMember implements Member{
    /**
     * Set the class variable usb.
     * Using UndoableStringBuilder to save the updates from the sender.
     * This UndoableStringBuilder object is the same object that the sender has.
     */
    private UndoableStringBuilder usb;

    /**
     * This is a constructor of the class,
     * Initialize the usb variable with an empty UndoableStringBuilder object.
     */
    public ConcreteMember(){
        this.usb = new UndoableStringBuilder();
    }

    /**
     * This is a constructor of the class,
     * this actually is a shallow copy of the usb object.
     * @param usb the UndoableStringBuilder object to initialize the usb variable
     */
    public ConcreteMember(UndoableStringBuilder usb){
        this.usb = usb;
    }

    /**
     * This function is used to update the member with the new usb object,
     * it is used to update the member with the new usb object that the sender has.
     *
     * @param usb the new usb object to update the member with
     */
    @Override
    public void update(UndoableStringBuilder usb) {
        this.usb = usb;
    }

    /**
     * This function is used to get the usb object of the member.
     *
     * @return the usb object of the member
     */
    public UndoableStringBuilder getUsb() {
        return usb;
    }

    /**
     * This function is used to set the usb object of the member.
     *
     * @param usb the new usb object to set the member with
     */
    public void setUsb(UndoableStringBuilder usb) {
        this.usb = usb;
    }

    /**
     * This function is used to get the string of the usb object of the member.
     *
     * @return the string of the usb object of the member
     */
    public String toString(){
        return this.usb.toString();
    }
}
