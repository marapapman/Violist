package testcases.standalone;

import LoggerLib.Logger;
public class ArrayMultiInstanceAssignmentSimple {
	public void foo() {
		String[][] x = {{"X"}};
		String[][] y = {{"Y"}, {"Z", "W"}};
		y[1] = x[0];
			Logger.reportString(y[0][0],"ArrayMultiInstanceAssignmentSimple");
	}
	public static void main(String[] args) {
		new ArrayMultiInstanceAssignmentSimple().foo();
	}
}
