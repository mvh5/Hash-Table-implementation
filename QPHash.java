

public class QPHash {
      String[] hashTable;
      int[] count;
      int sizeOfTable;
      int totalWordsNoRepeats;
      int iteratorIndex;

		public QPHash(){
			//TODO Implement a default constructor for QPHash
         hashTable = new String[200003];
         count = new int[200003];
         sizeOfTable = 200003;
		}
		
		public QPHash(int startSize){
			//TODO Implement a constructor that instantializes the hash array to startSize.
         hashTable = new String[startSize];
         count = new int[startSize];
         sizeOfTable = startSize;
		}

		/**
		 * This function allows rudimentary iteration through the QPHash.
		 * The ordering is not important so long as all added elements are returned only once.
		 * It should return null once it has gone through all elements
		 * @return Returns the next element of the hash table. Returns null if it is at its end.
		 */
		public String getNextKey(){
			//TODO returns the next key in the hash table.
			//You will need external tracking variables to account for this.
         for(int i = iteratorIndex; i < sizeOfTable; i++) {
            if (hashTable[i] != null) {
               iteratorIndex = i + 1;
               return hashTable[i];
            }
         }
         return null;        
		}
		/**
		 * Adds the key to the hash table.
		 * If there is a collision, a new location should be found using quadratic probing.
		 * If the key is already in the hash table, it increments that key's counter.
		 * @param keyToAdd : the key which will be added to the hash table
		 */
		public void insert(String keyToAdd){
			//TODO Implement insert into the hash table.
			//If keyToAdd is already in the hash table, then increment its count.
         int index = Math.abs(keyToAdd.hashCode()) % sizeOfTable;
         int probing = 1;
         while(hashTable[index] != null && !hashTable[index].equals(keyToAdd)) {
            index = (Math.abs(keyToAdd.hashCode()) + probing * probing) % sizeOfTable;
            probing++;
         }
         if (hashTable[index] == null) {
            hashTable[index] = keyToAdd;
            count[index]++;
            totalWordsNoRepeats++;
         } else {//this means the word is already in the array
            count[index]++;
         }      
               
		}
		/**
		 * Returns the number of times a key has been added to the hash table.
		 * @param keyToFind : The key being searched for
		 * @return returns the number of times that key has been added.
		 */
		public int findCount(String keyToFind){
			//TODO Implement findCount such that it returns the number of times keyToFind
			// has been added to the data structure.
         int index = Math.abs(keyToFind.hashCode()) % sizeOfTable;
         int probing = 1;
         while(hashTable[index] != null && !hashTable[index].equals(keyToFind)) {
            index = (Math.abs(keyToFind.hashCode()) + probing * probing) % sizeOfTable;
            probing++;
         }
         if (hashTable[index] == null) {
            return 0;
         } else {//this means the word is already in the array
            return count[index];
         } 
		}

		private int hash(String keyToHash){
			return -1;
			//EXTRA CREDIT: Implement your own String hash function here.
		}
}
