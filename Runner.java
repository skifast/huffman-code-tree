/*
 * Valerie Hetherington
 * Huffman Coding tree
 * 2/27/18
 * 
 * This program compresses and 
 * decompresses files based on the 
 * frequency of characters
 */

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

public class Runner{
	static FileIO fileReader;
	static String fileName1 = "testfile.txt";
	static String fileName2 = "writefile.txt";
	static String fileName3 = "decodefile.txt";
	static HashMap<Character, Integer> hmap; 
	static HashMap<Character, String> codeMap; 
	static Heaps masterHeap;
	
	public static void main(String[] args) throws IOException{
		masterHeap = new Heaps();
		fileReader = new FileIO();
		
		//creates a frequency map
		hmap = fileReader.readAndAssemble(fileName1);
		
		//creates a organized tree of nodes
		asemble();
		//creates a binary hashmap
		encode();
		
		//compresses the original file and creates a new file
		fileReader.encode(codeMap, fileName1, fileName2);
		
		//compresses the original file and creates a new file
		fileReader.readAndDessemble(masterHeap, fileName2, fileName3);
	}
	
	//creates a tree  based off of frequency of each character
	public static void asemble(){
		Iterator<Character> nameMap = hmap.keySet().iterator();
		while(nameMap.hasNext()){
			char character = nameMap.next();
			//new node
			Node newNode = new Node(character, hmap.get(character));
			//adds to the tree
			masterHeap.insert(newNode);
		}
		//organizes tree
		while(masterHeap.getArrayCutoff() != 0){
			Node firstNode = masterHeap.extractMin();
			Node secondNode = masterHeap.extractMin();
			//cannot compress files writen in Spanish because of ñ
			Node newNode = new Node('ñ', firstNode.getFrequency() + secondNode.getFrequency(),
					firstNode, secondNode);
			//reinserts the conjoined node
			masterHeap.insert(newNode);
		}
	}
	
	public static void encode(){
		Node[] nodeArray = masterHeap.givenArray;
		Node currentNode = nodeArray[0];
		String currentString = "";
		
		codeMap = new HashMap <Character, String>();
		//the first item in the array is needed for an inorder iteration
		inOrder(currentNode, currentString);
	}
	
	public static void inOrder(Node groot, String currentString){
		if(groot == null){
			return;
		}
		//currentString creates the binary code
		inOrder(groot.getLeftChild(), currentString + "0");
		//if the groot is not a conjoining node (it actually contains a value),
		//a character will be present along with a frequency
		if(groot.getChar() != 'ñ' ){
			//once an actual node is reached, it is added to the code map
			//along with its code
			codeMap.put(groot.getChar(), currentString);
		}
		//recursive call to add to the currentString
		inOrder(groot.getRightChild(), currentString + "1");
	}
	
}
