package observer;

import java.util.EmptyStackException;
import java.util.InputMismatchException;
import java.util.Stack;
/**
 * StringBuilder with undo support
 * java.lang.StringBuilder - class with modifier <b>final</b>,
 * so no inheritance, use delegation.
*/
interface Action{
    void undo();
}

class UndoableStringBuilder {

    private StringBuilder stringBuilder; // delegate
    /**
     * Operations that are the reverse of those performed.
     * That is, when append is called, it is placed on the stack
     * "delete" operation. When calling undo() it
     * will be executed.    */
    private Stack<Action> actions = new Stack<>();

    // constructor
    public UndoableStringBuilder() {
        stringBuilder = new StringBuilder();
    }

    public UndoableStringBuilder reverse() {
        stringBuilder.reverse();
        Action action = new Action(){
            public void undo() {
                stringBuilder.reverse();
            }
        };
        actions.add(action);
        return this;
    }

    public UndoableStringBuilder append(String str) {
        stringBuilder.append(str);

        Action action = new Action(){
            public void undo() {
                stringBuilder.delete(
                        stringBuilder.length() - str.length(),
                        stringBuilder.length());
            }
        };
        actions.add(action);
        return this;
    }

    public UndoableStringBuilder insert(int offset, String str) {
        stringBuilder.insert(offset, str);
        Action action = new Action(){
            public void undo() {
                stringBuilder.delete(offset, offset+str.length());
            }
        };
        actions.add(action);
        return this;
    }

    public UndoableStringBuilder delete(int start, int end) {
        String deleted = stringBuilder.substring(start, end);
        stringBuilder.delete(start, end);
        Action action = new Action(){
            public void undo() {
                stringBuilder.insert(start, deleted);
            }
        };
        actions.add(action);
        return this;
    }

    public UndoableStringBuilder replace(int start, int end, String str) {
        String deleted = stringBuilder.substring(start, end);
        stringBuilder.replace(start, end, str);
        Action action = new Action(){
            public void undo() {
                stringBuilder.replace(start, start+str.length(), deleted);
            }
        };
        actions.add(action);
        return this;
    }

  public void undo(){
        if(!actions.isEmpty()){
            actions.pop().undo();
        }
    }

    public String toString() {
        return stringBuilder.toString();
    }
}
//
///**
// * A class to improve StringBuilder class by
// * save operations and be able to undo them.
// * important to know, there is no forward option just undo.
// *
// * @author Elor Israeli
// * @author Roni Michaeli
// * @version 1.0 4 Nov 2022
// */
//public class UndoableStringBuilder {
//    /**
//     * Set the class variables to use undo function.
//     * Using Stack class to save the last operation each time
//     */
//    private StringBuilder stringBuilder;
//    private Stack<String> operationsList;
//
//    /**
//     * The undo function:
//     * Get the last operation from the stack if there is,
//     * modify our object's string builder to the last operation,
//     */
//    public void undo() {
//        try {
//            this.stringBuilder = new StringBuilder(operationsList.pop());
//        } catch (EmptyStackException e) {
//            System.err.println("Don't try to undo, The stack is empty");
//        }catch (Exception e){
//            System.err.println("The exception is: " + e);
//        }
//    }
//
//    /**
//     * A constructor to create new object of our class.
//     * Initialize the string builder and stack variables.
//     */
//    public UndoableStringBuilder() {
//        this.stringBuilder = new StringBuilder();
//        this.operationsList = new Stack<String>();
//    }
//
//    /**
//     * Push the current string to our object's stack.
//     * Append the string we get to our object's string builder sequence.
//     *
//     * @param str the string to append, the characters sequence to append
//     * @return the object UndoableStringBuilder after appending and updating the stack
//     */
//    public UndoableStringBuilder append(String str) {
//        try {
//            if (str.equals("")) {
//                System.err.println("The value empty");
//            }
//            operationsList.push(this.stringBuilder.toString());
//            this.stringBuilder.append(str);
//            return this;
//        } catch (NullPointerException e) {
//            System.err.println("please do not enter Null");
//        } catch (InputMismatchException e) {
//            System.err.println("please put only a string");
//        } catch (Exception e) {
//            System.err.println("please enter a valid string, the exception is: " + e);
//        }
//        return this;
//    }
//
//    /**
//     * Push the current string to our object's stack,
//     * Removes the characters in a substring of this sequence.
//     * The substring begins at the specified start and extends to the
//     * character at index end - 1 or to the end of the sequence if no such character exists.
//     * If start is equal to end, no changes are made.
//     *
//     * @param start the start position of deleting
//     * @param end   the end position of deleting
//     * @return the object UndoableStringBuilder after deleting and updating the stack
//     */
//    public UndoableStringBuilder delete(int start, int end) {
//        try {
//            if ((start > end) || (start < 0) || (start > stringBuilder.length())) {
//                System.err.println("Enter a valid range to delete (start<=end): 0-" + stringBuilder.length() + " ");
//                return this;
//            } else if (end > stringBuilder.length()) {
//                System.err.println("I deleted but " + stringBuilder.length() + " will be enough..");
//            } else if (start == end)
//                System.err.println("Nothing have been deleted");
//
//            operationsList.push(this.stringBuilder.toString());
//            this.stringBuilder.delete(start, end);
//            return this;
//        } catch (NullPointerException e) {
//            System.err.println("please do not enter Null");
//        } catch (InputMismatchException e) {
//            System.err.println("please put only a integers");
//        } catch (Exception e) {
//            System.err.println("please enter a valid string, the exception is: " + e);
//        }
//        return this;
//    }
//
//
//    /**
//     * Push the current string to our object's stack.
//     * Insert the string we get into the string builder
//     * in the offset position we get.
//     *
//     * @param offset the position of adding the string
//     * @param str the string to add
//     * @return the object UndoableStringBuilder after inserting and updating the stack
//     */
//    public UndoableStringBuilder insert(int offset, String str) {
//        try {
//            if ((offset > stringBuilder.length()) || (offset < 0)) {
//                System.err.println("Enter offset in range: 0-" + stringBuilder.length() + " ");
//                return this;
//            }
//            if (str.equals("")) {
//                System.err.println("The value empty");
//            }
//            operationsList.push(this.stringBuilder.toString());
//            this.stringBuilder.insert(offset, str);
//            return this;
//        } catch (NullPointerException e) {
//            System.err.println("please do not enter Null");
//        } catch (InputMismatchException e) {
//            System.err.println("please put only 'offset' as a integer and 'str' as string");
//        } catch (Exception e) {
//            System.err.println("please enter a valid string, the exception is: " + e);
//        }
//        return this;
//    }
//
//
//    /**
//     * Push the current string to our object's stack.
//     * Replace the string located from start position we get
//     * to end position we get.
//     *
//     * @param start the start position to replace the string
//     * @param end   the end position to replace the string
//     * @param str   the string to replace
//     * @return the object UndoableStringBuilder after replacing and updating the stack
//     */
//    public UndoableStringBuilder replace(int start, int end, String str) {
//        try {
//            if ((start == end) && (str.equals(""))) {
//                System.err.println("The value is empty in same position");
//            }
//            operationsList.push(this.stringBuilder.toString());
//            this.stringBuilder.replace(start, end, str);
//            return this;
//        } catch (StringIndexOutOfBoundsException e) {
//            System.err.println("Enter a valid range to replace (start<=end): 0-" + stringBuilder.length() + "");
//        } catch (NullPointerException e) {
//            System.err.println("please do not enter Null");
//        } catch (InputMismatchException e) {
//            System.err.println("please put only integers in 'start' and 'end', and string in 'str'");
//        } catch (Exception e) {
//            System.err.println("please enter a valid string, the exception is: " + e);
//        }
//        return this;
//    }
//
//    /**
//     * Push the current string to our object's stack.
//     * Reverse the string, notice if something special happened.
//     *
//     * @return the object UndoableStringBuilder after reversing and updating the stack
//     */
//    public UndoableStringBuilder reverse() {
//        if (this.stringBuilder.toString().equals("")) {
//            System.err.println("The empty string reversing");
//        } else if ((stringBuilder.toString().equals(stringBuilder.reverse().toString()))
//                && (stringBuilder.length() > 2)) { // palindrome with 3 characters and above
//            System.err.println("Palindrome appearance");
//            stringBuilder.reverse(); // to back it to original after changed (reversely) in 'else if'
//        } else {
//            stringBuilder.reverse(); // reverse the string builder because changed in 'else if' check and failed.
//        }
//        operationsList.push(this.stringBuilder.toString());
//        this.stringBuilder.reverse();
//        return this;
//    }
//
//    /**
//     * Printing the current object's string builder and stack.
//     *
//     * @return characters sequence of the object's string builder
//     */
//    public String printStack() {
//        return "UndoableStringBuilder{" +
//                "stringBuilder=" + stringBuilder +
//                ", operationsList=" + operationsList +
//                '}';
//    }
//
//    /**
//     * print the string of out object's string builder.
//     *
//     * @return the string builder string
//     */
//    @Override
//    public String toString() {
//        return stringBuilder.toString();
//    }
//}
