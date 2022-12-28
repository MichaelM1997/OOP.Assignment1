package observer;

import java.util.ArrayList;
/***
This is a Java class that implements the Sender interface and represents a group administrator who is responsible for
maintaining a list of members and a message that is shared with all the members of the group.

The group administrator maintains an instance of the UndoableStringBuilder class, which represents a string that
can be modified using various methods such as insert, append, and delete, and also allows undoing the most recent modification
 using the undo method.

The group administrator has methods for registering and unregistering members, and for modifying the message using
the insert, append, and delete methods. When a modification is made to the message, the group administrator calls the
notifyObserver method, which in turn calls the update method on each of the registered members, passing the modified message
as an argument. This allows the members to be notified of the change and update their copy of the message accordingly.
 */
public class GroupAdmin implements Sender{
    private UndoableStringBuilder usb = new UndoableStringBuilder();
    private ArrayList <Member> members = new ArrayList<>();
    //Constructor
    public GroupAdmin(String str){
        this.usb = new UndoableStringBuilder(str);
    }
    //Constructor
    public GroupAdmin(UndoableStringBuilder usb){
        this.usb = usb;
    }

    public UndoableStringBuilder getUsb() {
        return usb;
    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    public void setUsb(UndoableStringBuilder usb) {
        this.usb = usb;
    }

    public void setMembers(ArrayList<Member> members) {
        this.members = members;
    }

    /***
     * The notifyObserver() method looping through the ArrayList list of Member objects and calling
     * the update method on each of them, passing in the usb object as an argument.
     */
    public void notifyObserver(){
        for (Member member : members) {
            member.update(usb);
        }
    }

    /***
     * this method adds a Member object to the members ArrayList and calls the update method on it,
     * passing in the usb object as an argument.
     * @param member
     */
    @Override
    public void register(Member member) {
        if(!members.contains(member)){
            members.add(member);
            member.update(usb);
        }
        else{
            System.err.println("Member is already registered");
        }
    }

    /***
     * this method removes a Member object from the members ArrayList and calls the update method on it,
     * passing in a new UndoableStringBuilder object constructed from the toString representation of the Member object as an argument.
     * @param member
     */
    @Override
    public void unregister(Member member) {
        if (members.contains(member)){
            members.remove(member);
            member.update(new UndoableStringBuilder(member.toString()));
        }
    }

    /***
     * Inserts the string into this character sequence. The characters of the String argument are inserted,
     * in order, into this sequence at the indicated offset, moving up any characters originally above that position
     * and increasing the length of this sequence by the length of the argument.
     *
     * This methode also using the notifyObserver() function to notify and update all the members in the ArrayList.
     * @param offset The offset, it's where to start insert the new string
     * @param str the string to insert
     * @throws Exception StringIndexOutOfBoundsException Illegal index
     */
    @Override
    public void insert(int offset, String str) throws Exception {
        usb.insert(offset,str);
        notifyObserver();
    }

    /***
     * Appends the specified string to this character sequence.
     * The characters of the String argument are appended, in order, increasing the length of this sequence by
     * the length of the argument. If str is null, then the four characters "null" are appended.
     *
     * This methode also using the notifyObserver() function to notify and update all the members in the ArrayList.
     * @param str a string
     */
    @Override
    public void append(String str) {
        usb.append(str);
        notifyObserver();
    }

    /***
     * Gets two Integers as parameters and remove all characters between the two integers.
     * If start is equal to end, no changes are made.
     *
     * This methode also using the notifyObserver() function to notify and update all the members in the ArrayList.
     *
     * @param start The parameter of the start index (included)
     * @param end The parameter of the end index (excluded)
     * @throws Exception StringIndexOutOfBoundsException Illegal index
     */
    @Override
    public void delete(int start, int end) throws Exception {
        usb.delete(start, end);
        notifyObserver();
    }

    /***
     * Doing undo to last method that activate on the string.
     *
     * This methode also using the notifyObserver() function to notify and update all the members in the ArrayList.
     */
    @Override
    public void undo() {
        usb.undo();
        notifyObserver();
    }
}
