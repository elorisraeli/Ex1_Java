package observer;

import java.util.ArrayList;

// This class is describing the update sender to everyone
/**
 *
 */
public class GroupAdmin implements Sender{
    private UndoableStringBuilder usb;
    private ArrayList<Member> members;

    public GroupAdmin(){
        this.usb = new UndoableStringBuilder();
        this.members = new ArrayList<Member>();
    }
    public GroupAdmin(UndoableStringBuilder usb){
        this.usb = usb;
        this.members = new ArrayList<Member>();
    }

    @Override
    public void register(Member obj) {
        this.members.add(obj);
        /* Let this member get the updated UndoableStringBuilder */
        notifyAll(this.usb);
    }

    @Override
    public void unregister(Member obj) {
        this.members.remove(obj);
    }

    @Override
    public void insert(int offset, String obj) {
        this.usb.insert(offset, obj);
        notifyAll(this.usb);
    }

    @Override
    public void append(String obj) {
        this.usb.append(obj);
        notifyAll(this.usb);
    }

    @Override
    public void delete(int start, int end) {
        this.usb.delete(start, end);
//        notifyAll(this.usb);
    }

    @Override
    public void undo() {
        this.usb.undo();
        notifyAll(this.usb);
    }

    public void notifyAll(UndoableStringBuilder usb){
        this.usb = usb;
        for (Member member: members){
            member.update(this.usb);
        }
    }

    public UndoableStringBuilder getUsb() {
        return usb;
    }

    public void setUsb(UndoableStringBuilder usb) {
        this.usb = usb;
        notifyAll(this.usb);
    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<Member> members) {
        this.members = members;
    }
}
