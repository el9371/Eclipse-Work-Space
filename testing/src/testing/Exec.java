package testing;

import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.*;

public class Exec {
	public static void main(String[] args) {
		int tmp = 3;
		String target = "There 3map alpha\n delta are";
	    String regEx = "There " + tmp + "map (.*) are";



	    // ���Խ�(regEx)�� �������� �����,
	    Pattern pat = Pattern.compile(regEx);
	    // ������ Ÿ�� ��Ʈ��(target)�� ��ġ��Ų��.
	    Matcher match = pat.matcher(target);



	    System.out.println(match.find());  // true
	    System.out.println(match.group(1)); // 2
	     
	     
	}
}

