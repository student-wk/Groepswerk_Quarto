package Quarto.Model;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AnimationFileHandler {
    private final static Path ANIMATIONFILE = Paths.get("resources"+ File.separator+"animation"+ File.separator+"animation.bin");
    public List<String> actions;
    public static int count;

    public AnimationFileHandler()  {
        count = 0;
    }

    public void initiateFile(String initialAction) throws IOException {
        if (Files.exists(ANIMATIONFILE)){
            Files.delete(ANIMATIONFILE);
            Files.createFile(ANIMATIONFILE);
            try (DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(String.valueOf(ANIMATIONFILE),true)))){
            dataOutputStream.writeUTF(initialAction);
            } catch (IOException ioException){
            ioException.printStackTrace();
            }
        } else {
            Files.createFile(ANIMATIONFILE);
            try (DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(String.valueOf(ANIMATIONFILE),true)))){
            dataOutputStream.writeUTF(initialAction);
            } catch (IOException ioException){
                ioException.printStackTrace();
            }
        }
    }

    public void addAction(String action) throws IOException {
        try (DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(String.valueOf(ANIMATIONFILE),true)))){
            dataOutputStream.writeUTF(action);
        } catch (IOException ioException){
            throw new IOException("Error using animation source file");
        }
    }

    public void printout() {
        List<String> list = new ArrayList<>();
        try (DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(String.valueOf(ANIMATIONFILE))))){
            while (dataInputStream.available() > 0) {
                System.out.println(dataInputStream.readUTF());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> binFile2List(String bestand) throws QuartoException {
        ArrayList<String> list = new ArrayList<>();
        try (DataInputStream is = new DataInputStream(new BufferedInputStream(new FileInputStream(String.valueOf(ANIMATIONFILE))))) {
            try {
                while (true) {
                    String action = is.readUTF();
                    list.add(action);
                }
            } catch (EOFException e1) {
                //Alles OK; bestand ten einde
            } catch (IOException e2) {
                throw new QuartoException("Error while reading source file " + bestand, e2);
            }
            return list;
        } catch (IOException e3) {
            throw new QuartoException("The source file " + bestand + " can't be opened",e3);
        }
    }

    public void cteateActions() throws QuartoException, IOException {
        this.actions = new ArrayList<>();
        try (DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(String.valueOf(ANIMATIONFILE))))) {
            while (dataInputStream.available() > 0) {
                String action = dataInputStream.readUTF();
                actions.add(action);
            }
        }
    }

    public String getAction(){
        if (count == actions.size()){
            count = 0;
        }
        return actions.get(count++);

    }

    public  int getCount() {
        return count;
    }

    public static void setCount(int count) {
        AnimationFileHandler.count = count;
    }

    public List<String> getActions() {
        return actions;
    }
}
