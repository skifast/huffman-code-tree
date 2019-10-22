
import java.util.Arrays;

public class Heaps{
	//the array cutoff is the index of the last int being processed
	int arrayCutoff;
	Node[] givenArray;
	int root; 
	

	public Heaps(){
		//starts out as a list of length zero
		//this will become bigger later obviously
		givenArray = new Node[0];
		
		//the last index is one less than the length of the array
		arrayCutoff = 0;
		
		
	}
	
	//gets the max value to index 0 of the array
	public void minHeapify(int root){ 
		//left node
		int left = root * 2 + 1;
		//right node
		int right = root * 2 + 2;
		
		int least = root;
		
		//first we need to make sure that the index we are attempting to acess
		//is actually included in the array
		//arrayCutoff + 1 will give how many nodes there are in the array
		if(left <= (arrayCutoff) && givenArray[left].getFrequency() < givenArray[least].getFrequency()){
			//the least number will eventually become the root
			least = left;
		}
		if(right <= (arrayCutoff) && givenArray[right].getFrequency() < givenArray[least].getFrequency()){
			least = right;
		}
		
		if(least != root){
			swap(root, least);
			
		}
		
		//the array is minheapified if the root is 0
		//at this point it has gone through the whole tree
		if(root == 0){
			return;
		}
		
		
		//otherwise look at the next root
		minHeapify(root-1);
	}
		
	
	//switches the values at two parameterized indexes
	public void swap(int firstIndex, int secondIndex){
		
		if(firstIndex == -1){
			for(int i = 0; i < givenArray.length; i++){
				System.out.println(String.valueOf(givenArray[i].getChar()));
			}
		}
		
		//switch two numbers at specified index
		//the holding int is necessary to that one of the values does not get lost
		Node holding = givenArray[firstIndex];
		givenArray[firstIndex] = givenArray[secondIndex];
		givenArray[secondIndex] = holding;
	}
	
	//need to assign this to an array
	//size of array is the index of the last int that we want to look at
	public void insert(Node intsert){
//		
//		for(int i = 0; i< arrayCutoff; i++){
//			if(givenArray[i].getFrequency() == intsert.getFrequency()){
//				givenArray[i].setConflictFlights(intsert.getAirline() + " " + 
//			intsert.getTime() + " " + intsert.getRunway());
//				intsert.setConflictFlights(givenArray[i].getAirline() + " " + 
//						givenArray[i].getFrequency() + " " + givenArray[i].getRunway() + ", ");
//			}
//		}
		//setUpConflicts(intsert, arrayCutoff);
		if(givenArray.length == 0){
			arrayCutoff = 0;
			
			//intsert is the only node to be in the list
			//increase the list to accomidate the node
			Node[] returnArray = new Node[2];
			
			//add the node
			returnArray[0] = intsert;
			
			for(int i = 0; i < arrayCutoff; i++){
				returnArray[i] =  givenArray[i];
			}
			givenArray = returnArray;
		}
		
		else if(arrayCutoff == givenArray.length - 1){
			arrayCutoff += 1; 
			
			//make a new array that is double the size of the original
			Node[] returnArray = new Node[givenArray.length * 2];
			
			//copy the information from the original to the new one
			for(int i = 0; i < arrayCutoff; i++){
				returnArray[i] =  givenArray[i];
			}
			
			returnArray[arrayCutoff] = intsert; 
			
			givenArray = returnArray;
		}

		else{
			arrayCutoff += 1; 
			
			givenArray[arrayCutoff] = intsert; 
		}
		//root is reset because the array cutoff is incrimented
		root = (arrayCutoff) / 2 -1 ;
		if(root < 0){
			root = 0;
		}
	}
	
	public int getRoot(){
		root = (arrayCutoff) / 2 -1 ;
		if(root < 0){
			return 0;
		}
		return root;
	}
	
	
	//returns the min value to the user after removing it from the array
	public Node extractMin(){

		//get the min value to the top of the array
		minHeapify(root);
		
		//switch the value at the top of the array with the very last value
		Node holding = givenArray[0];
		givenArray[0] = givenArray[arrayCutoff];
		givenArray[arrayCutoff] = holding;
		
		//givenArray[arrayCutoff] = -1;
		
		arrayCutoff -= 1;
		
		return holding;
		
	}
	
	public int getArrayCutoff(){
		return arrayCutoff;
	}
	
	
	
}
