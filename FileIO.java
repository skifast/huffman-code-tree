import io.github.pixee.security.BoundedLineReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class FileIO {
	
	HashMap<Character, Integer> hmap;
	
	public HashMap<Character, Integer> readAndAssemble(String fileName){
		
		BufferedReader br = null;
		
		//new array list
		hmap = new HashMap<Character, Integer>();

		try{
			File file = new File(fileName);
			
			FileReader fr = new FileReader(file);
			br = new BufferedReader(fr);
			
			String line;
			
			while((line= BoundedLineReader.readLine(br, 5_000_000)) != null){
				//the files provided divide up the information at each character
				String[] currentLine = line.split("");
				//now there is an array of all of the characters in the file
				for(int i = 0; i< currentLine.length; i++){
					for(int k = 0; k< currentLine[i].length(); k++){
						String s = currentLine[i];
						mapLetter(s.charAt(k));
					}
				}
			}
			if(hmap.size() == 0){
				//if this is the case, the file is empty
				System.out.println("There is nothing to read. Sorry!");
			}
			readMap();

		} catch (IOException ioe){
			System.out.println("error");
		}
		finally
		{
			try{
				if(br!=null)
					br.close();
			}catch(Exception ex){
				System.out.println("Error in closing the BufferedReader text");
			}
		}
		return hmap;

	}
	
	public void encode(HashMap <Character, String> givenMap, String FilenameReading, String FilenameWriting){
		BufferedReader br = null;
		BufferedWriter bw = null;

		try{
			/*New File object*/
			File fileWriting = new File(FilenameWriting);

			FileWriter fw = new FileWriter(fileWriting);
			bw = new BufferedWriter(fw);
			
			File fileReading = new File(FilenameReading);
			
			FileReader fr = new FileReader(fileReading);
			br = new BufferedReader(fr);
			
			String line;
			
			while((line= BoundedLineReader.readLine(br, 5_000_000)) != null){
				//the files provided divide up the information at each space
				String[] currentLine = line.split("");
			
				//now there is an array of all of the words in the file
				for(int i = 0; i< currentLine.length; i++){
					String writeToFile = givenMap.get(currentLine[i].charAt(0));
					bw.write(writeToFile);
					bw.flush();
				}
			}
			
			bw.close();
			
		

		} catch (IOException ioe){
			ioe.printStackTrace();
		}
		finally
		{
			try{
				if(bw!=null)
					bw.close();
			}catch(Exception ex){
				System.out.println("Error in closing the BufferedWriter text");
			}
		}
		
	}
	
	//reads in a file and decodes it into a new file
	public void readAndDessemble(Heaps heap, String fileNameRead, String fileNameWrite){
		BufferedReader br = null;
		BufferedWriter bw = null;
		
		try{
			File file = new File(fileNameRead);
			FileReader fr = new FileReader(file);
			br = new BufferedReader(fr);
			
			File file2 = new File(fileNameWrite);
			FileWriter fw = new FileWriter(file2);
			bw = new BufferedWriter(fw);
			
			String line;
			
			while((line= BoundedLineReader.readLine(br, 5_000_000)) != null){
				String newString = "";
				
					Node currentnode = heap.givenArray[0];
					for(int i = 0; i < line.length(); i++){
						
						if(line.charAt(i) == '1'){
							currentnode = currentnode.getRightChild();
							//the node is not a char if it is an ñ
							if(currentnode.getChar() != 'ñ'){
								newString += currentnode.getChar();
								currentnode = heap.givenArray[0];
							}
						}
						else{
							currentnode = currentnode.getLeftChild();
							if(currentnode.getChar() != 'ñ'){
								newString += currentnode.getChar();
								currentnode = heap.givenArray[0];
							}
						}
					}
					bw.write(newString);
					bw.flush();
			}
			bw.close();
			

		} catch (IOException ioe){
			System.out.println("error");
		}
		finally
		{
			try{
				if(br!=null)
					br.close();
			}catch(Exception ex){
				System.out.println("Error in closing the BufferedReader text");
			}
		}
	}

	//returns a char based on code given
	public char decode(Heaps heap, String line){
		Node currentNode = heap.givenArray[0];
		for(int i = 0; i< line.length(); i++){
			//if this is the case, a node with a char has been found
			if(currentNode.getChar() != 'ñ'){
				break;
			}
			if(line.charAt(i) == '1'){
				currentNode = currentNode.getRightChild();
			}
			else{
				currentNode = currentNode.getLeftChild();
			}
		}
		return currentNode.getChar();
		
	}
	
	//adds char to map or adds to the frequency
	public void mapLetter(char vroom){;
		if(hmap.get(vroom) == null){
			hmap.put(vroom, 1);
		}
		else{
			int q = hmap.get(vroom);
			q += 1; 
			hmap.put(vroom, q);
		}
		
	}
	
	public void readMap(){
		System.out.println(Arrays.asList(hmap));
	}

	
}
