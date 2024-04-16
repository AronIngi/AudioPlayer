package hi.verkefni.vinnsla;

import hi.verkefni.vidmot.ListiController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.net.NetworkInterface;
import java.net.URL;
import java.util.Scanner;

public class Lagalisti {
    private final String path;
    private final String name;
    private ObservableList<Lag> listi;
    private int index;

    /**
     * Smiður: setur path lagalistans sem notendagildi og upphafstillir lagalistann
     * @param path address á skránna sem lagalistinn notar
     */
    public Lagalisti(String path, String name)
    {
        this.path = path;
        this.name = name;
        listi = FXCollections.observableArrayList();
    }

    /**
     * les úr nafnaskrá og setur lögin öll lögin í listanum inn í lagalistann
     * @param nafnaskra heiti skránnar sem inniheldur nöfn laganna
     * @throws IOException
     */
    public boolean lesaLog(String path) throws IOException
    {
        File f = new File(path);
        Scanner sc = new Scanner(f);
        if(!sc.hasNext())
            return false;
        while(sc.hasNextLine())
        {
                String line = sc.nextLine();
                System.out.println(line);
                if(line.charAt(0) != '#') {
                    String[] properties = line.split(",");
                    Lag lag = new Lag(properties);
                    listi.add(lag);
                }
        }

        index = 0;
        return true;
    }

    /***
     * Bætir við lagi í listann
     * @param data strengur sem geymir upplýsingar um nafn lags og path á lagið
     */
    public void addLag(String data)
    {
        String[] properties = data.split(",");
        Lag lag = new Lag(properties);
        listi.add(lag);
    }

    /**
     * setur index sem notað er til að spila og stilla núverandi lag.
     * @param index
     */
    public void setIndex(int index)
    {
        this.index = index;
    }

    /**
     * nær í núverandi lag
     * @return núverandi lag
     */
    public Lag getIndex()
    {
        return listi.get(index);
    }

    /**
     * Nær í næsta lag ef einhver lög eru eftir, setur næsta lag sem fyrsta lagið í listanum
     * @return næsta lag
     */
    public Lag getNext()
    {
        if(index < listi.size()-1)
            index++;
        else
            index = 0;
        return listi.get(index);
    }

    /**
     * eyðir upplýsingunum úr listanum
     */
    public void deleteList()
    {
        while(listi.listIterator().hasNext())
        {
            listi.remove(listi.listIterator().next());
        }
    }

    /**
     * skilar lagalistanum
     * @return lagalistinn
     */
    public ObservableList<Lag> getListi()
    {
        return listi;
    }

    /**
     * nær í heiti skránnar sem listinn notar til að fá upplýsingar um lögin
     * @return heiti skráar lagalistans
     */
    public String getPath()
    {
        return path;
    }

    /***
     * skilar nafni lagalistans
     * @return nafn lagalistans
     */
    public String getName() {return name;}

}
