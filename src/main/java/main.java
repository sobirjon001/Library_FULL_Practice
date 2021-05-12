import java.lang.reflect.Array;
import java.util.*;

public class main {

  public static void main(String[] args) {


    Set<String> set = new HashSet<>(Arrays.asList("a", "b","c"));
    Set<String> set2 = new HashSet<>(Arrays.asList("d", "e","f"));

    int count = 0;
    for ( Iterator<String> it = set.iterator(), it2 = set2.iterator(); it.hasNext() && it2.hasNext()  ;  count++      ) {
      String each = it.next();
      if(each.equals("a")) System.out.println("Hooray!");
      System.out.println("the letter is "+ each);
      System.out.println("this is " + count + " iteration");
    }



  }


}
