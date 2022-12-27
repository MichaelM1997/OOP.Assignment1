package observer;

public class ConcreteMember implements Member{

    private Sender sender;
    private UndoableStringBuilder usbSC;


    public ConcreteMember(UndoableStringBuilder usb) {
        this.usbSC = usb;
    }
    public ConcreteMember(Sender sender) {
        this.sender = sender;
        this.sender.register(this);
    }
    public ConcreteMember(Sender sender,UndoableStringBuilder usb) {
        this.sender = sender;
        this.usbSC = usb;
        sender.register(this);
    }
    @Override
    public void update(UndoableStringBuilder usb) {
        this.usbSC = usb;
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

    @Override
    public String toString() {
        return usbSC.toString();
    }
}
