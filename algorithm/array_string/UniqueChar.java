import java.util.HashSet;
import java.util.Set;


public class UniqueChar{
    public static boolean isUnique(String s){
        if ( s == null)
            return true;

        Set<Character> set = new HashSet<Character>(); 
        for (int i = 0 ;i < s.length(); i ++ ){
            Character c = s.charAt(i);
            if (!set.contains(c)){
                set.add(c);
            }
            else
                return false;
        }
        return true;
    }

    public static boolean isUnique1(String s){
        if (s == null)
            return true;

        for(int i = 0 ; i < s.length(); i ++ ){
            Character c = s.charAt(i);
            for ( int j = i + 1; j < s.length(); j ++){
                Character c1 = s.charAt(j);
                if (c1 == c)
                    return false;
            }
        }
        return true;
    }

    public static void main(String args[]){
        String s = null;
        System.out.println(isUnique(s));
        System.out.println(isUnique1(s));
        s = "fuck this";
        System.out.println(isUnique(s));
        System.out.println(isUnique1(s));
        s =  "fuck this shit";
        System.out.println(isUnique(s));
        System.out.println(isUnique1(s));
    }
}


