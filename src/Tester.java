import java.util.Scanner;
import java.lang.Math;
public class Tester{


   public static void main(String[] args){
   // grab infix exp from user
    Scanner x = new Scanner(System.in);
    // string to store input
    String in;
    // taking user input
    System.out.println("Enter an expression to be evaluated: ");
    in = x.nextLine();
    
    String postfix = infixToPostfix(in);
    // output
    System.out.println("Pre-evaluation of the postfix expression: " + postfix );
    System.out.println("Post-evaluation of the postfix expression: " + postFixEval(postfix));
    
      }  
     // infix to postfix 
    static String infixToPostfix(String infixExp){

    // create a new stack
    LinkedStack op = new LinkedStack();
    // string to store postfix expression
    String exp = "";
    // iterator
    for(int i = 0; i < infixExp.length(); ++i){
      char c = infixExp.charAt(i);
      // if the infix exp is an operand
      if(Character.isDigit(c) || Character.isLetter(c))
         exp += c;
         
        else if(c == '(')
         op.push(c);
        
        else if(c == ')'){
            while(!op.isEmpty() && !op.top().equals('('))
                     // add the popped item from the stack to the expression              
                      exp += op.pop();
               
               op.pop();
  
            }
            else{
            // checking precedence
               while(!op.isEmpty() && precOnStack((Character)op.top()) > preCurr(c))
                  exp += op.pop();
                  
            
             op.push(c);  
            }
         }
            while(!op.isEmpty())
               exp += op.pop();
     
            return exp;
  
        }
        
// eval        
static int postFixEval(String infixExp){
   // new stack
   LinkedStack op = new LinkedStack();
   for(int i = 0; i < infixExp.length(); i++){
      char c = infixExp.charAt(i);
      
         if(Character.isDigit(c)) {
            op.push(c - '0');
            }
          // checking whitespace
          else if(Character.isWhitespace(c)){
            infixExp.substring(1);
            }
           
           
            else{
               // store value of both popped objects into int variables to evaluate
              int first = (Integer)op.pop();
              int second = (Integer)op.pop();
          

             // operations
               if(c == '*'){
                  op.push(second * first);
                  
                  }             
               if(c == '+'){
                  op.push(second + first);
                  
                  }
               if(c == '-'){
                  op.push(second - first);
                  
                  }
               if(c == '/'){
                  op.push(second / first);
                  
                  }
               if(c == '^'){
                  Double d = Math.pow(second, first);
                  Integer a = d.intValue();
                  op.push(a);
                  
                  }

            }
            
        }
        return (Integer)op.pop();
         
        }
       
// precedence method for stack
public static int precOnStack(char c){
   if( c ==  '(')
      return 0;
      
      else if(c == '^')
         return 5;
            
      else if(c == '*')
         return 4;
                  
      else if (c == '/')
         return 4;
                        
      else if(c == '%')
         return 4;
                           
      else if(c == '+')
         return 2;
                                 
      else if(c == '-')
         return 2;
         
         
         return c;

}
// precedence check on current item
public static int preCurr(char c){

   if( c ==  '(')
      return 100;
      
      else if(c == '^')
         return 6;
            
      else if(c == '*')
         return 3;
                  
      else if (c == '/')
         return 3;
                        
      else if(c == '%')
         return 3;
                           
      else if(c == '+')
         return 1;
                                 
      else if(c == '-')
         return 1;
         
         
         return c;



}    

static class LinkedStack {
	private class Node {
		// Instance variables:
		private Object data;
		private Node next;
		/** Creates a node with the given element and next node. */
		public Node(Object e, Node n) {
			this.data = e;
			this.next = n;
		}
	}//end of node class
	
  protected Node top;// reference to the head node
  protected int size;// number of elements in the stack

  /** Creates an empty stack. */
  public LinkedStack() {	// constructs an empty stack
    this.top = null;
    this.size = 0;
  }
  public int size() { return size; }
  
  public boolean isEmpty() {
    if (top == null) return true;
    return false;
  }
  public void push(Object elem) {
    Node v = new Node(elem, top);	// create and link-in a new node, new node's next gets top; 
    this.top = v;                        // equivalent to addFirst operation on linked list.
    this.size++;
  }
  public Object top() throws EmptyStackException {
    if (isEmpty()) 
    		throw new EmptyStackException("Stack is empty.");
    return top.data;
  }
  public Object pop() throws EmptyStackException {
    if (isEmpty()) 
    	throw new EmptyStackException("Stack is empty.");
    
    Object temp = top.data;
    this.top = top.next;	// equivalent to removeFirst operation on linked list.
    this.size--;
    return temp;
  }
}
public static class EmptyStackException extends RuntimeException {  
  public EmptyStackException(String err) {
    super(err);
  }
}


}