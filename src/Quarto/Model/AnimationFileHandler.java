package Quarto.Model;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class AnimationFileHandler {
    private final static Path ANIMATIONFILE = Paths.get("resources"+ File.separator+"animation"+ File.separator+"animation.bin");


    public AnimationFileHandler()  {
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
            ioException.printStackTrace();
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

    public String getAction(){
        String action = null;
        try (DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(String.valueOf(ANIMATIONFILE))))){
//            while (dataInputStream.available() > 0) {
//                action = dataInputStream.readUTF();
//            }
            action = dataInputStream.readUTF();


        } catch (IOException e) {
            e.printStackTrace();
        }
        return action;
    }
}
