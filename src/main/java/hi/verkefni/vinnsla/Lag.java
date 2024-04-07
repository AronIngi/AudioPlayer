package hi.verkefni.vinnsla;

public class Lag {
    private String nafn;
    private String fileName;
    private int length;

    public Lag(String[] line)
    {
        this.fileName = line[0];
        this.nafn = line[1];
    }

    /**
     * getter nafn skránnar
     * @return heiti skráarinnar sem er tengd við lagið
     */
    public String getFileName()
    {
        return fileName;
    }

    /**
     * skilar titil lagsins þegar reynt er að nota Lag sem streng
     * @return titill lagsins
     */
    public String toString()
    {
        return nafn;
    }
}
