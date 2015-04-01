package reflection.SortAlgorithms;
import LoggerLib.Logger;
import java.security.NoSuchAlgorithmException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Constructor;

interface Sort {}

public class DefaultSortAlgorithms {

	public final static int QUICKSORT = 0;
	public final static int COUNTSORT = 1;
	
	public static Sort getInstance(int type) throws NoSuchAlgorithmException, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException{
	    String[] sortingAlgorithms = {"DefaultSortAlgorithms$QuickSort","DefaultSortAlgorithms$CountingSort"};
		if (type >= 0 && type < sortingAlgorithms.length) {
			Class c = Class.forName(sortingAlgorithms[type]);
			Logger.reportString(sortingAlgorithms[type],"reflection.SortAlgorithms.DefaultSortAlgorithms15");
			Constructor con = c.getConstructors()[0];
			Sort sort = (Sort)con.newInstance(new Object[]{});
			return sort;
		}
		else {
			throw (new NoSuchAlgorithmException("That algorithm does not exist"));
		}
	}

	private static class QuickSort implements Sort {
		private String s = "";
		public QuickSort() {}

		public void setValue(String s) {
			this.s = s;
		}
		public String sort() {
			//yadda yadda
                        return "";
		}
	}

	private static class CountingSort implements Sort {
                        String s;
                        char[] alphabet = {'a','b','c','d','e','f','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		public CountingSort() {}

		public void setAlphabet(String alphabet) {
			this.alphabet = alphabet.toCharArray();
		}
		public void setValue(String s) {
			this.s = s;
		}
		public String sort() {
			//yadda yadda
                        return "";
		}
	}
}
