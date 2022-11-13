//Pregunta 1.4

package P1;

import java.util.ArrayList;
import java.util.List;



public class Remove {
    public void remove(List<Character> chars){
        char end = 'z';
        chars.removeIf(c->{char start = 'a'; return start <=c && c<=end;});
    }

    public static void main(String[] args) {
        Remove r = new Remove();
        List<Character> chars = new ArrayList<>();
        chars.add('a');
        chars.add('@');
        r.remove(chars);
        System.out.println(chars.isEmpty());
    }
}
