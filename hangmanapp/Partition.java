import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This class provides the functions needed to partition a list of words, and compute
 * entropy metrics useful for determining the goodness of particular partition. It also
 * has functions for building up masks that that define the different partitions.
 */
public class Partition {
	


	/**
	 * @param word the word from which we're building a mask
	 * @param chars the letters we are building a mask on
	 * @param maskChar the mystery mask character, representing an unknown character
	 * @return a masking of the word showing the info based on a given set of letters
	 */	
	public static char[] makeMask(char[] word, Set<Character> chars, char maskChar) {
		char[] mask = (char[])word.clone();
		for (int i = 0; i < mask.length; i++) {
			if (!chars.contains(mask[i])) {
				mask[i] = maskChar;
			}
		}
		return mask;
	}	



	
	/**
	 * @param words a list of words
	 * @param chars a set of characters used to make a partition
	 * @return a partition of the words based on possible masks built
	 */
	public static Map<CharArray, List<char[]>> makePartition(List<char[]> words, Set<Character> chars) {
		Map<CharArray, List<char[]>> partition = new HashMap<CharArray, List<char[]>>();
		for (char[] word : words) {
			char[] mask = makeMask(word, chars, '_');
			CharArray maskCA = new CharArray(mask);
			if (partition.containsKey(maskCA)) {
				partition.get(maskCA).add(word);
			} else {
				List<char[]> aNewPartition = new LinkedList<char[]>();
				aNewPartition.add(word);
				partition.put(maskCA, aNewPartition);
			}
		}
		return partition;
	}	
		
	/**
	 * This is mostly useful for debugging purposes.
	 * @param partition represented as using a map
	 * @return a string representation of the partition in two lines.
	 */
	public static String toString(Map<CharArray, List<char[]>> partition) {
		StringBuilder s = new StringBuilder();
		String NL = System.getProperty("line.separator");
		
		int i = 0;
		for (Map.Entry<CharArray, List<char[]>> entry : partition.entrySet()) {
			i += 1;
			String mask = new String(entry.getKey().c);
			s.append("partition " + i + ") n=" + entry.getValue().size() + " " + mask + NL);
			int j = 0;
			for (char[] word : entry.getValue()) {
				s.append(String.valueOf(word) + ", ");
				j++;
				if (j >= 10) {
					break;
				}
			}
			s.append("\n");
		}
		return s.toString();
	}
	
	/**
	 * @param partition a partition of words based on possible masks
	 * @return the shannon entropy of the partition
	 */
	public static double entropy(Map<CharArray, List<char[]>> partition) {
		int totalSize = 0;
		for (List<char[]> list : partition.values()) {
			totalSize += list.size();
		}
		double result = 0.0;
		for (List<char[]> list : partition.values()) {
			double p = (double)list.size() / (double)totalSize;			
			result += - p * Math2.log2(p);
		}		
		return result;
	}




}
