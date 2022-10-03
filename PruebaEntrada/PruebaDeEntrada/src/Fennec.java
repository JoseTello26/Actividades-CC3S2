class Canine{
    private StringBuilder logger = new StringBuilder();

    public Canine(boolean t){
        logger.append("a");
    }
    public Canine() {
        logger.append("q");
    }

    protected void print(String v) { logger.append(v); }
    protected String view() { return logger.toString(); }
}

class Fox extends Canine{
    public Fox(long x) { print("p"); }
    public Fox(String name)
    {
        this(2);
        print("z");
    }
}

public class Fennec extends Fox{
    public Fennec(int e){
        super("tails");
        print("j");
    }

    public Fennec(short f){
        super("eevee");
        print("m");
    }


    public static void main(String[] args) {
        System.out.println(new Fennec(1).view());
    }
}

