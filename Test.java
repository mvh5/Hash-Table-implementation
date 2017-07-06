
public class Test {

	public static void main(String[] args) {
		FileInput.init();
		
		//TODO Initialize the hash tables
      ChainingHash chainHash = new ChainingHash();
      QPHash qpHash = new QPHash(); 

		
		//TODO Use the FileInput functions to read the two files.
		// Input the elements of those two files into two hash tables,
		// one file into a ChainingHash object and the other into a QPHash object.
		String[] baconWords = FileInput.readBacon();
      String[] shakespeareWords = FileInput.readShakespeare();
      
      //add elements into chaining hash table
      for(int i = 0; i < baconWords.length; i++) {
         chainHash.insert(baconWords[i]);
      }
      
      //add elements into quadratic probing hash table
      for (int i = 0; i < shakespeareWords.length; i++) {
         qpHash.insert(shakespeareWords[i]);
      }      
		
		//TODO Initialize necessary variables for calculating the square error
		// and most distant word.
      double totalSquareError = 0;
		
		//TODO Iterate through the first hash table and add sum the squared error
		// for these words.
      String word = chainHash.getNextKey();
      String wordHighestFrequencyDifference = word;
      double highestFrequencyDifference = -1;
      while (word != null) {
         
         double frequency1 = chainHash.findCount(word)*1.0 / baconWords.length;
         double frequency2 = qpHash.findCount(word)*1.0 / shakespeareWords.length;
         totalSquareError+= (frequency1 - frequency2) * (frequency1 - frequency2);
         double differenceInFrequency = Math.abs(frequency1 - frequency2);
         //double differenceInFrequency = Math.abs(chainHash.findCount(word) - qpHash.findCount(word));
         if (differenceInFrequency > highestFrequencyDifference) {
            wordHighestFrequencyDifference = word;
            highestFrequencyDifference = differenceInFrequency;
         }   
         
         word = chainHash.getNextKey();
      }   
		
		//TODO Find  words in the second hash table that are not in the first and add their squared error
		// to the total
      word = qpHash.getNextKey();
      while (word != null) {
         if (chainHash.findCount(word) == 0) {
            double frequency = qpHash.findCount(word)*1.0 / shakespeareWords.length;
            totalSquareError+= frequency * frequency;
            
            double differenceInFrequency = frequency;
            if (differenceInFrequency > highestFrequencyDifference) {
               wordHighestFrequencyDifference = word;
               highestFrequencyDifference = differenceInFrequency;
            }
  
         }
         word = qpHash.getNextKey();
      }         
      
		
		//TODO Print the total calculated squared error for the two tables and also the word with the highest distance.
      System.out.println("Total Square Error: " + totalSquareError);
      System.out.println("Most different word: " + wordHighestFrequencyDifference);
	}

}
