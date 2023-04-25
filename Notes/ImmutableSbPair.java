public final class ImmutableSbPair{
    private final StringBuilder x;
    private final StringBuilder y;

    public ImmutableSbPair(StringBuilder x, StringBuilder y){
        this.x = new StringBuilder(x.toString());
        this.y = new StringBuilder(y.toString());
    }

    public String toString(){
        return "(" + x + "," + y + ")"; 
    }

    public int hashCode(){
        int h = 17;
        h = 31*h + x.hashCode();
        h = 31*h + y.hashCode();
        return h;
    }

    public StringBuilder getX(){
        return new StringBuilder(x.toString());
    }
    
    public StringBuilder getY(){
        return new StringBuilder(y.toString());
    }
}