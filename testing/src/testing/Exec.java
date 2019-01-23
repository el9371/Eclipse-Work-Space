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



	    // 정규식(regEx)을 패턴으로 만들고,
	    Pattern pat = Pattern.compile(regEx);
	    // 패턴을 타겟 스트링(target)과 매치시킨다.
	    Matcher match = pat.matcher(target);



	    System.out.println(match.find());  // true
	    System.out.println(match.group(1)); // 2
	     
	     
	}
}

