package smallD;

public class Main {
	public static void main(String args[]) throws Exception {
		Exec.resetData();
		int operationNumber = 10000;
		Exec alpha = new Exec();
		for (int i = 0; i < operationNumber; i ++)
			{alpha.exec();System.out.println(i);}
	}
}
