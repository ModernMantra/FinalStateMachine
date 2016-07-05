public class Nodes {
    private boolean accept;
    private boolean start;
    private String alphabet;
    private int next_nodes;
    private int id;

    public Nodes(){
        this.accept = false;
        this.start = false;
        this.next_nodes = 0;
        this.alphabet = "";
        this.id = -1;
    }

    public void setID(int id) {

        this.id = id;
    }

    public void setStart(boolean b) {

        this.start = b;
    }

    public String getAlphabet() {

        return this.alphabet;
    }

    public void setAlphabet(String a) {

        this.alphabet = a;
    }

    public int getDest() {

        return this.next_nodes;
    }

    public void setDest(int dest) {

        this.next_nodes = dest;
    }

    public boolean getAccept() {

        return this.accept;
    }

    public void setAccept(boolean b) {

        this.accept = b;
    }
}
