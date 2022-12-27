package observer;

import java.util.ArrayList;

public class GroupAdmin implements Sender{
    private UndoableStringBuilder usb = new UndoableStringBuilder();
    private ArrayList <Member> members = new ArrayList<>();

    public GroupAdmin(String str){
        this.usb = new UndoableStringBuilder(str);
    }

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

    public void notifyObserver(){
        for (Member member : members) {
            member.update(usb);
        }
    }

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

    @Override
    public void unregister(Member member) {
        if (members.contains(member)){
            members.remove(member);
            member.update(new UndoableStringBuilder(member.toString()));
        }
    }

    @Override
    public void insert(int offset, String str) throws Exception {
        usb.insert(offset,str);
        notifyObserver();
    }

    @Override
    public void append(String str) {
        usb.append(str);
        notifyObserver();
    }

    @Override
    public void delete(int start, int end) throws Exception {
        usb.delete(start, end);
        notifyObserver();
    }

    @Override
    public void undo() {
        usb.undo();
        notifyObserver();
    }
}
