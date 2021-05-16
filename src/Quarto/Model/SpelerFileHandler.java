package Quarto.Model;

import java.io.*;
import java.util.*;

/**
 * @author Willem Kuijpers
 * @version 1.0 16-5-2021 16:13
 */
public class SpelerFileHandler {

    public static ArrayList<Speler> binFile2List(String bestand) throws IOException {
        ArrayList<Speler> list = new ArrayList<>();
        try (DataInputStream is = new DataInputStream(new BufferedInputStream(new FileInputStream(bestand)))) {
            try {
                while (true) {
                    String naam = is.readUTF();
                    int score = is.readInt();
                    Speler speler = new Speler(naam, score);
                    list.add(speler);
                }
            } catch (EOFException e1) {
                //Alles OK; bestand ten einde
            } catch (IOException e2) {
                throw new IOException("Fout bij het lezen van het bronbestand " + bestand, e2);
            }
            Collections.sort(list);
            return list;
        } catch (IOException e3) {
            throw new IOException("Het bronbestand " + bestand + " kan niet geopend worden", e3);
        }
    }

    public static void list2BinFile(ArrayList<Speler> list, String bestand) throws IOException {
        try (DataOutputStream os = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(bestand)))) {
            for (Speler speler : list) {
                os.writeUTF(speler.getNaam());
                os.writeInt(speler.getScore());
            }
        } catch (IOException e) {
            throw new IOException("Problemen bij het wegschrijven naar het doelbestand " + bestand, e);
        }
    }

}
