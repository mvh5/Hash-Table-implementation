

public class ChainingHash {
      ChainingNode[] hashTable;
      int tableSize;
      ChainingNode allElements;
      ChainingNode iteratorNode;
      
		public ChainingHash(){
			//TODO Implement a default constructor for ChainingHash
         hashTable = new ChainingNode[200000];
         tableSize = 200000;
		}
		
		public ChainingHash(int startSize){
			//TODO Implement a constructor that instantializes the hash array to startSize.
         hashTable = new ChainingNode[startSize];
         tableSize = startSize;
		}

		/**
		 * This function allows rudimentary iteration through the ChainingHash.
		 * The ordering is not important so long as all added elements are returned only once.
		 * It should return null once it has gone through all elements
		 * @return Returns the next element of the hash table. Returns null if it is at its end.
		 */
		public String getNextKey(){
         if (iteratorNode != null) {
            String nextKey = iteratorNode.word;
            iteratorNode = iteratorNode.next;
            return nextKey;
         }
         return null;   
			//TODO returns the next key in the hash table.
			//You will need external tracking variables to account for this.
		}
		/**
		 * Adds the key to the hash table.
		 * If there is a collision, it should be dealt with by chaining the keys together.
		 * If the key is already in the hash table, it increments that key's counter.
		 * @param keyToAdd : the key which will be added to the hash table
		 */
		public void insert(String keyToAdd){
         
			//TODO Implement insert into the hash table.
			//If keyToAdd is already in the hash table, then increment its count.
         int index = Math.abs(keyToAdd.hashCode()) % tableSize;
         if (hashTable[index] == null) {
            hashTable[index] = new ChainingNode(keyToAdd);
            
            ChainingNode temp =allElements;
            allElements = new ChainingNode(keyToAdd);
            allElements.next = temp;
            iteratorNode = allElements;
         } else {
            if (getNode(keyToAdd,hashTable[index]) == null) {
               ChainingNode temp = hashTable[index];
               hashTable[index] = new ChainingNode(keyToAdd);
               hashTable[index].next = temp;
               
               ChainingNode temp2 =allElements;
               allElements = new ChainingNode(keyToAdd);
               allElements.next = temp2;
               iteratorNode = allElements;
            } else {//word already in the table, just increment count
               ChainingNode temp = getNode(keyToAdd,hashTable[index]);
               temp.count = temp.count + 1;
            }   
            
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
         int index = Math.abs(keyToFind.hashCode()) % tableSize;
         ChainingNode node = hashTable[index];
         ChainingNode target = getNode(keyToFind,node);
         if (target == null) {
            return 0;
         } else {
            return target.count;
         }     
		}

		private int hash(String keyToHash){
			return -1;
			//EXTRA CREDIT: Implement your own String hash function here.
		}
      
      public ChainingNode getNode(String word, ChainingNode node) {
         ChainingNode current = node;
         while (current != null) {
            if (current.word.equals(word)) {
               return current;
            }
            current = current.next;
         }
         return null;
      }         
         
}
