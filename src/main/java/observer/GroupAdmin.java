package observer;

import java.util.ArrayList;
import java.util.List;

// This class is describing the update sender to everyone


/**
 * A class to improve UndoableStringBuilder class with some more functionality,
 * This class is describing the observable the on who send updates to everyone
 *
 * @author Elor Israeli
 * @author Roni Michaeli
 * @version 1.0 30 Dec 2022
 */
public class GroupAdmin implements Sender{
    /**
     * Set the class variables to use new functions.
     * Using ArrayList to save the members of the group.
     */
    private UndoableStringBuilder usb;
    private ArrayList<Member> members;


    /**
     *  This is a constructor of the class,
     *  Initialize the usb and members variables.
     */
    public GroupAdmin(){
        this.usb = new UndoableStringBuilder();
        this.members = new ArrayList<Member>();
    }

    /**
     * This is a constructor of the class,
     * @param usb the UndoableStringBuilder object to initialize the usb variable
     * For the members variable we initialize it with an empty ArrayList.
     */
    public GroupAdmin(UndoableStringBuilder usb){
        this.usb = usb;
        this.members = new ArrayList<Member>();
    }

    @Override
    /**
     * This method is used to register the member
     * to the group admin list of members to send updates to them,
     * also update the member with the current usb object.
     *
     * @param member the member to be registered to the group admin list of members
     */
    public void register(Member obj) {
        this.members.add(obj);
    }

    @Override
    /**
     * This method is used to unregister the member
     * from the group admin list of members to send updates to them
     *
     * @param member the member to be unregistered from the group admin list of members
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
     */
    public void insert(int offset, String obj) {
        this.usb.insert(offset, obj);
        updateAll();
    }

    @Override
    /**
     * This method is used to append the specified string to this character sequence.
     *
     * @param obj the string to be appended to the end of this character sequence of the usb object
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
     */
    public void delete(int start, int end) {
        this.usb.delete(start, end);
        updateAll();
    }

    @Override
    /**
     * This method is used to erase the last change done to the document, reverting
     * it to an older state.
     */
    public void undo() {
        this.usb.undo();
        updateAll();
    }

    /**
     * This method is used to update the usb to all the members
     * in the group admin list of members.
     */
    public void updateAll(){
        for (Member member: members) {
            member.update(usb);
        }
    }

    /**
     * This method is used to get the usb
     *
     * @return the usb of the group admin
     */
    public UndoableStringBuilder getUsb() {
        return usb;
    }


    @Override
    /**
     * This method is used to get the string representation of the usb
     *
     * @return the string representation of the usb
     */
    public String toString(){
        return this.usb.toString();
    }

    /**
     * This method is used to set the usb
     *
     * @param usb the UndoableStringBuilder object to set the usb variable
     */
    public void setUsb(UndoableStringBuilder usb) {
        this.usb = usb;
    }

    /**
     * This method is used to get the members
     * of the group admin list of members
     *
     * @return the members of the group admin list of members
     */
    public ArrayList<Member> getMembers() {
        return members;
    }

    /**
     * This method is used to set the members
     *
     * @param members the members to set the group admin list of members
     */
    public void setMembers(ArrayList<Member> members) {
        this.members = members;
    }
}
