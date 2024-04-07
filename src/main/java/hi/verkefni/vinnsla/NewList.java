package hi.verkefni.vinnsla;

public class NewList {
    private String nafn;

    /**
     * Smiður: gefur áskrifandanum nafn
     * @param nafn nafn áskrifanda
     */
    public NewList(String nafn)
    {
        this.nafn = nafn;
    }

    /**
     * setur nafn áskrifanda
     * @param nafn nýtt nafn áskrifanda
     */
    public void set(String nafn)
    {
        this.nafn = nafn;
    }

    /**
     * skilar nafni áskrifanda
     * @return nafn áskrifanda
     */
    public String get()
    {
        return nafn;
    }
}