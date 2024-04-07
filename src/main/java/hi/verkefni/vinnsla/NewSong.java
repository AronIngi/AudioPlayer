package hi.verkefni.vinnsla;

public class NewSong {
    private String nafn;
    private String path;

    /**
     * Smiður: gefur áskrifandanum nafn
     * @param nafn nafn áskrifanda
     */
    public NewSong(String nafn, String path)
    {
        this.nafn = nafn;
        this.path = path;
    }

    /**
     * setur nafn áskrifanda
     * @param nafn nýtt nafn áskrifanda
     */
    public void setNafn(String nafn)
    {
        this.nafn = nafn;
    }

    /**
     * skilar nafni áskrifanda
     * @return nafn áskrifanda
     */
    public String getNafn()
    {
        return nafn;
    }

    public void setPath(String path)
    {
        this.path = path;
    }
    public String getPath()
    {
        return path;
    }
}