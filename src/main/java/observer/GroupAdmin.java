package observer;

import java.util.ArrayList;

// This class is describing the update sender to everyone
/**
 *
 */
public class GroupAdmin implements Sender{
    private UndoableStringBuilder usb;
    private ArrayList<Member> concreteMembers;

    @Override
    public void register(Member obj) {
        this.concreteMembers.add(obj);
    }

    @Override
    public void unregister(Member obj) {
        this.concreteMembers.remove(obj);
    }

    @Override
    public void insert(int offset, String obj) {
        this.usb.insert(offset, obj);
        updateUSB(this.usb);
    }

    @Override
    public void append(String obj) {
        this.usb.append(obj);
        updateUSB(this.usb);
    }

    @Override
    public void delete(int start, int end) {
        this.usb.delete(start, end);
        updateUSB(this.usb);
    }

    @Override
    public void undo() {
        this.usb.undo();
        updateUSB(this.usb);
    }

    public void updateUSB(UndoableStringBuilder usb){
        for (Member member:concreteMembers) {
            member.update(usb);
        }
    }

    public UndoableStringBuilder getUsb() {
        return usb;
    }

    public void setUsb(UndoableStringBuilder usb) {
        this.usb = usb;
    }

    public ArrayList<Member> getConcreteMembers() {
        return concreteMembers;
    }

    public void setConcreteMembers(ArrayList<Member> concreteMembers) {
        this.concreteMembers = concreteMembers;
    }
}
