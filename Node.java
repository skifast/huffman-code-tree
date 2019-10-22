import java.util.Arrays;

public class Node{
	/*Methods included are getter and setter methods.
	 * This class also includes the information held 
	 * by the nodes */
	int frequency;
	char character;
	Node rightChild;
	Node leftChild;

	
	public Node(char givenchar, int givenfrequency, Node givenRight, Node givenLeft){
		this.character = givenchar;
		this.frequency = givenfrequency;
		this.rightChild = givenRight;
		this.leftChild = givenLeft;
	}
	
	public Node(char givenchar, int givenfrequency){
		this.character = givenchar;
		this.frequency = givenfrequency;
		
		//these will all be added later
		this.rightChild = null;
		this.leftChild = null;
	}
	
	public Node(){
		this.character = ' ';
		this.frequency = -1;
		this.rightChild = null;
		this.leftChild = null;
	}
	
	public char getChar(){
		return character;
	}
	
	public void setChar(char givenchar){
		this.character = givenchar;
	}
	
	public int getFrequency(){
		return frequency;
	}
	
	public void getFrequency(int givenFrequency){
		this.frequency = givenFrequency;
	}
	
	public Node getRightChild(){
		return rightChild; 
	}
	
	public void setRightChild(Node givenRight){
		this.rightChild = givenRight;
	}
	
	public Node getLeftChild(){
		return leftChild;
	}
	
	public void setLeftChild(Node givenLeft){
		this.leftChild = givenLeft; 
	}
	
	
}
