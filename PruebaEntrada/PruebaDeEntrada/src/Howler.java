public class Howler {
    public Howler(long shadow){
        super();
    }
    private Howler(int moon){
        super();
    }
}

class Wolf extends Howler{
    protected Wolf(String stars){
        super(2L);
    }
    public Wolf(){
        this("stars");
    }
}
