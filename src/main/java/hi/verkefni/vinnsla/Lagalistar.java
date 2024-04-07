package hi.verkefni.vinnsla;

import javafx.event.ActionEvent;
import hi.verkefni.vinnsla.Bag;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Lagalistar {


    private static final Bag<Lagalisti> listar = new Bag<Lagalisti>() {
    };

    private static int index;

    public static void addListi(String path)
    {
        try {
            Scanner sc = new Scanner(new File(path));
            listar.add(new Lagalisti(path, sc.nextLine().substring(1)));
        }catch(IOException io)
        {System.out.println("io");}
    }
    public static int size()
    {
        return listar.size();
    }
    /**
     * nær í lagalistann á með index i
     * @param i index til að ná í listann
     * @return listinn með index i
     */
    public static Lagalisti getListi(int i)
    {
        return listar.peek(i);
    }

    /**
     * nær í núverandi index
     * @return
     */
    public static int getIndex()
    {
        return index;
    }

    /**
     * setur nýtt index
     * @param i nýtt index
     */
    public static void setIndex(int i)
    {
        index = i;
    }


}
