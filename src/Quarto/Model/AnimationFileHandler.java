package Quarto.Model;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * The class AnimationFileHandler is used to read and write actions to and from a bin file in order to play an animation of the previous game played.
 * the AnimationFileHandler is controlled by a Quarto Model.
 * When a game is played the Quaro model accceses methods of the AnimationFileHandler to write to animation.bin
 * When a an animation is of a previous game is played, the quarto model again acces methods of the AnimationFileHandler to read strings of actions from the animation.bin file to animate.
 *
 * @author Delawar Jalat
 * @version 1.0 24-4-2021 14:26
 */

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
                throw new IOException("Error using animation source file");
            }
        } else {
            Files.createFile(ANIMATIONFILE);
            try (DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(String.valueOf(ANIMATIONFILE),true)))){
            dataOutputStream.writeUTF(initialAction);
            } catch (IOException ioException){
                throw new IOException("Error using animation source file");
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


    public void cteateActions() throws IOException {
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

    public static void setCount(int count) {
        AnimationFileHandler.count = count;
    }

    public List<String> getActions() {
        return actions;
    }
}
