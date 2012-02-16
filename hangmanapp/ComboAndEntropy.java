import java.util.HashSet;
import java.util.Set;

/**
 * This class provides a convenient pairing of a letter combination and
 * an entropy value. It is useful for functions returning the letter combination with
 * the largest entropy.
 */
public class ComboAndEntropy {
	

	@Override
	public String toString() {
		return "ComboAndEntropy [combo=" + combo + ", entropy=" + entropy + "]";
	}

	public final Set<Character> combo;
	public final double entropy;
	
	public ComboAndEntropy(Set<Character> combo, double entropy) {
		this.combo = combo;
		this.entropy = entropy;
	}
	


}
