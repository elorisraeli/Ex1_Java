package observer;

import java.util.ArrayList;
import java.util.List;

// This class is describing the update sender to everyone


/**
 * This class is describing the observable the on who send updates to everyone
 *
 */
public class GroupAdmin implements Sender{
    private UndoableStringBuilder usb;
    private ArrayList<Member> members;


    /**
     *  This is the constructor of the class
     */
    public GroupAdmin(){
        this.usb = new UndoableStringBuilder();
        this.members = new ArrayList<Member>();
    }

    /**
     * this is the constructor of the class that takes a UndoableStringBuilder as a parameter and sets it to the usb
     */
    public GroupAdmin(UndoableStringBuilder usb){
        this.usb = usb;
        this.members = new ArrayList<Member>();
    }

    @Override
    /**
     * This method is used to register the member to the group admin list of members to send updates to them
     *
     * @param member the member to be registered
     *
     * @return
     * @throws
     */
    public void register(Member obj) {
        this.members.add(obj);
        updateAll();
    }

    @Override
    /**
     * This method is used to unregister the member from the group admin list of members to send updates to them
     *
     * @param member the member to be unregistered
     *
     * @return
     * @throws
     */
    public void unregister(Member obj) {
        this.members.remove(obj);
    }

    @Override
    /**
     * This method is used to insert the string into this character sequence.
     *
     * @param offset the offset
     * @param obj the string to be inserted
     *
     * @return
     * @throws
     */
    public void insert(int offset, String obj) {
        this.usb.insert(offset, obj);
        updateAll();
    }

    @Override
    /**
     * This method is used to append the specified string to this character sequence.
     *
     * @param obj the string to be appended
     *
     * @return
     * @throws
     */
    public void append(String obj) {
        this.usb.append(obj);
        updateAll();
    }

    @Override
    /**
     * This method is used to remove the characters in a substring of this sequence.
     *
     * @param start the start index
     * @param end the end index
     *
     * @return
     * @throws
     */
    public void delete(int start, int end) {
        this.usb.delete(start, end);
        updateAll();
    }

    @Override
    /**
     * This method is used to erase the last change done to the document, reverting
     * it to an older state.
     *
     * @return
     * @throws
     */
    public void undo() {
        this.usb.undo();
        updateAll();
    }

    /**
     * This method is used to update the usb to all the members
     *
     * @return
     * @throws
     */
    public void updateAll(){
        for (Member member: members) {
            member.update(usb);
        }
    }

    /**
     * This method is used to get the usb
     *
     * @return the usb
     * @throws
     */
    public UndoableStringBuilder getUsb() {
        return usb;
    }



    @Override
    /**
     * This method is used to get the string representation of the usb
     *
     * @return the string representation of the usb
     * @throws
     */
    public String toString(){
        return this.usb.toString();
    }

    public void setUsb(UndoableStringBuilder usb) {
        this.usb = usb;
    }

    /**
     * This method is used to get the members
     *
     * @return the members
     * @throws
     */
    public ArrayList<Member> getMembers() {
        return members;
    }

    /**
     * This method is used to set the members
     *
     * @param members the members to set
     * @throws
     */
    public void setMembers(ArrayList<Member> members) {
        this.members = members;
    }
}
