package observer;

/***
 * The ConcreteMember class is an implementation of the Member interface. It has three constructors: one that takes
 * an UndoableStringBuilder object as an argument, one that takes a Sender object as an argument, and one that takes both a
 * Sender and an UndoableStringBuilder object as arguments.
 */
public class ConcreteMember implements Member{

    private Sender sender;
    private UndoableStringBuilder usbSC;
    //Constructor
    public ConcreteMember(UndoableStringBuilder usb) {
        this.usbSC = usb;
    }
    //Constructor
    public ConcreteMember(Sender sender) {
        this.sender = sender;
        this.sender.register(this);
    }
    //Constructor
    public ConcreteMember(Sender sender,UndoableStringBuilder usb) {
        this.sender = sender;
        this.usbSC = usb;
        sender.register(this);
    }

    public UndoableStringBuilder getUsbSC(){
        return this.usbSC;
    }

    public Sender getSender() {
        return sender;
    }

    public void setUsbSC(UndoableStringBuilder usbSC) {
        this.usbSC = usbSC;
    }

    /***
     *  Update the state of the ConcreteMember object. The update method takes an UndoableStringBuilder object as an
     *  argument and updates the usbSC field of the ConcreteMember object with the value of this argument.
     * @param usb
     */
    @Override
    public void update(UndoableStringBuilder usb) {
        this.usbSC = usb;
    }

    @Override
    public String toString() {
        return usbSC.toString();
    }
}
