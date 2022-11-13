//Pregunta 1.3

package P1;

interface Secret{
    String magic(double d);
}

class Secret1 implements Secret{
    public String magic(double d){
        return "Poof";
    }
}

public class Secrets{
    public static void main(String[] args) {
        //Secret1 s1 = new Secret1();
        //System.out.println(s1.magic(2));
        Secret s = d->"Poof";
        System.out.println(s.magic(2));
    }
}
