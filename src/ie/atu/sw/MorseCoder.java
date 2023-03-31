package ie.atu.sw;

import java.io.*;
import java.util.*;

public class MorseCoder {
    private Map<Character, String> morseMap; // Morse code lookup table


    public MorseCoder() {
        // Initialize the Morse code lookup table
        morseMap = new HashMap<>();
        morseMap.put('A', ".-");
        morseMap.put('B', "-...");
        morseMap.put('C', "-.-.");
        morseMap.put('D', "-..");
        morseMap.put('E', ".");
        morseMap.put('F', "..-.");
        morseMap.put('G', "--.");
        morseMap.put('H', "....");
        morseMap.put('I', "..");
        morseMap.put('J', ".---");
        morseMap.put('K', "-.-");
        morseMap.put('L', ".-..");
        morseMap.put('M', "--");
        morseMap.put('N', "-.");
        morseMap.put('O', "---");
        morseMap.put('P', ".--.");
        morseMap.put('Q', "--.-");
        morseMap.put('R', ".-.");
        morseMap.put('S', "...");
        morseMap.put('T', "-");
        morseMap.put('U', "..-");
        morseMap.put('V', "...-");
        morseMap.put('W', ".--");
        morseMap.put('X', "-..-");
        morseMap.put('Y', "-.--");
        morseMap.put('Z', "--..");
        morseMap.put('0', "-----");
        morseMap.put('1', ".----");
        morseMap.put('2', "..---");
        morseMap.put('3', "...--");
        morseMap.put('4', "....-");
        morseMap.put('5', ".....");
        morseMap.put('6', "-....");
        morseMap.put('7', "--...");
        morseMap.put('8', "---..");
        morseMap.put('9', "----.");
        morseMap.put(' ', "/");
    }


	public void encodeFile(String inputFilePath, String outputFilePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath));
        String line;
        while ((line = reader.readLine()) != null) {
            // Convert each character to its Morse code equivalent and write to the output file
            for (char c : line.toUpperCase().toCharArray()) {
                String code = morseMap.get(c);
                if (code != null) {
                    writer.write(code + " ");
                }
            }
            writer.newLine();
        }
        reader.close();
        writer.close();
    }

    public void decodeFile(String inputFilePath, String outputFilePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath));
        StringBuilder result = new StringBuilder();
        //Collection<String> vals = morseMap.values();
        String line;
        while ((line = reader.readLine()) != null) {
            // Convert each character to its Morse code equivalent and write to the output file
            String[] words = line.split(" / ");//splits the message into words using the /
            for (String word : words) {//for each word
                String[] letters = word.split(" ");//split the word into letters using " "
                for (String letter : letters) {//for each letter
                // find the corresponding value
                	for ( Map.Entry<Character, String> entry : morseMap.entrySet()) {
                		Character key = entry.getKey();
                		String val = entry.getValue();
                	    if(letter.equals(val) ) {
                	    	 result.append(key);
                	    }
                	}
                }
                result.append(" ");
                }
                result.append(" ");
            }
            writer.write(result+ " ");
            //result.toString().trim();
            writer.newLine();
            reader.close();
            writer.close();
        }


	public String fileToString(String file) {
		 StringBuilder data = new StringBuilder();
		try {
	        File myObj = new File(file);
	        Scanner myReader = new Scanner(myObj);
	        while (myReader.hasNextLine()) {
	          data.append(myReader.nextLine());

	        }
	        myReader.close();
	      } catch (FileNotFoundException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	      }
		return data.toString();
	}

}
