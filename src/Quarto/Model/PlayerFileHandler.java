package Quarto.Model;

import java.io.*;
import java.util.*;

/**
 * This class contains two static methods used to interact with the source file "ranking.bin".
 *
 * @author Willem Kuijpers
 * @version 1.0 16-5-2021 16:13
 */
public class PlayerFileHandler {

    /**
     * This method recovers the player ranking from the source file and returns it to an arraylist.
     *
     * @param fileName the relative path of the source file.
     * @throws QuartoException if it can't read or open the source file.
     * @return An arraylist with player objects, sorted by their ranking.
     * */

    public static ArrayList<Player> binFile2List(String fileName) throws IOException {
        ArrayList<Player> list = new ArrayList<>();
        try (DataInputStream is = new DataInputStream(new BufferedInputStream(new FileInputStream(fileName)))) {
            try {
                while (true) {
                    String naam = is.readUTF();
                    int score = is.readInt();
                    Player player = new Player(naam, score);
                    list.add(player);
                }
            } catch (EOFException e1) {
            } catch (IOException e2) {
                throw new IOException("Error while reading source file " + fileName, e2);
            }
            Collections.sort(list);
            return list;
        } catch (IOException e3) {
            throw new IOException("The source file " + fileName + " can't be opened",e3);
        }
    }

    /**
    * This method is used to write a list of players to the source file.
     *
     * @throws QuartoException if it can't write to the source file.
    * */

    public static void list2BinFile(ArrayList<Player> list, String fileName) throws IOException {
        try (DataOutputStream os = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)))) {
            for (Player player : list) {
                os.writeUTF(player.getName());
                os.writeInt(player.getScore());
            }
        } catch (IOException e) {
            throw new IOException("Error while writing to source file" + fileName, e);
        }
    }

}
