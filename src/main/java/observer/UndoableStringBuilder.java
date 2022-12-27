package observer;

import java.util.Stack;

/**
 * This class uses the build-in StringBuilder with support of the undo operation by using a Stack
 */
public class UndoableStringBuilder {

    Stack<String> memory = new Stack<String>();
    StringBuilder strbld = new StringBuilder();

    public UndoableStringBuilder(String s) {
        this.strbld = new StringBuilder(s);
        memory.push(s);
    }
    public UndoableStringBuilder(){}

    public UndoableStringBuilder append(String str){
        memory.push(strbld.toString());
        this.strbld.append(str);
        return this;
    }

    /**
     * Gets two Integers as parameters and remove all characters between the two integers.
     * If start is equal to end, no changes are made.
     *
     * This methode support the undo() methode by using a stack.
     *
     * If start is smaller than 0 or end is grater than String length or start is bigger than end
     * the method will throw exception.
     *
     * @param start The parameter of the start index (included)
     * @param end The parameter of the end index (excluded)
     * @return This object (the string without the part we wanted to delete)
     * @throws StringIndexOutOfBoundsException Illegal index
     */
    public UndoableStringBuilder delete(int start, int end) throws Exception {
        if (start < 0 || end > strbld.length() || start > end) {throw new StringIndexOutOfBoundsException("index error");};
        if(start == end) {return null;}
        memory.push(strbld.toString());
        this.strbld.delete(start, end);
        return this;
    }

    /**
     *
     * Inserts the string into this character sequence.
     * The characters of the String argument are inserted,
     * in order, into this sequence at the indicated offset,
     * moving up any characters originally above that position and increasing the length of
     * this sequence by the length of the argument.
     *
     * This methode support the undo() methode by using a stack.
     *
     * If the offset parameter is smaller than 0 or bigger than the String length
     * the method will throw exception.
     *
     * @param offset The offset, it's where to start insert the new string
     * @param str the string to insert
     * @return This object (a new string with the string that we want to insert)
     * @throws StringIndexOutOfBoundsException Illegal index
     */
    public UndoableStringBuilder insert(int offset, String str) throws Exception {
        if (offset<0 || offset > strbld.length()) {throw new StringIndexOutOfBoundsException("index error");}
        memory.push(strbld.toString());
        this.strbld.insert(offset, str);
        return this;
    }

    /**
     * Replaces the characters in a substring of this sequence with characters in the specified String.
     * The substring begins at the specified start and extends to the character at index end - 1 or to the end
     * of the sequence if no such character exists.
     *
     * This methode support the undo() methode by using a stack.
     *
     * If start is smaller than 0 or end is grater than String length or start is bigger than end
     * the method will throw exception.
     *
     * @param start The beginning index, inclusive.
     * @param end The ending index, exclusive.
     * @param str String that will replace previous contents.
     * @return This object
     * @throws StringIndexOutOfBoundsException index error
     */
    public UndoableStringBuilder replace(int start,int end, String str) throws Exception {
        if (str == "null") {throw new NullPointerException("null pointer exception");}
        if (start < 0 || end > strbld.length() || start > end) {throw new StringIndexOutOfBoundsException("index error");}
        memory.push(strbld.toString());
        this.strbld.replace(start, end, str);
        return this;
    }

    /**
     * Causes this character sequence to be replaced by the reverse of the sequence.
     * If there are any surrogate pairs included in the sequence, these are treated as
     * single characters for the reverse operation.
     *
     * This methode support the undo() methode by using a stack.
     *
     * @return This object
     */
    public UndoableStringBuilder reverse(){
        memory.push(strbld.toString());
        this.strbld.reverse();
        return this;
    }

    /**
     * Doing undo to last method that activate on the string, for example, let "Michael" be a string,
     * if we will use the reverse() method we will get "leahciM", then, if we will use the undo()
     * method we will get "Michael" back again.
     * The undo method working with a stack that declare in the UndoableStringBuilder class
     * and every time that you use undo() the method will try to do pop() from the stack and if stack is empty
     * it won't do anything
     *
     * @return This object
     */
    public UndoableStringBuilder undo(){
        try{
            this.strbld = new StringBuilder(memory.pop());
        }
        catch (Exception e) {
            return null;
        }
        return this;
    }

    @Override
    public String toString() {
        return strbld.toString();
    }
}
