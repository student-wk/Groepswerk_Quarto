package Quarto.Model;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Willem Kuijpers
 * @version 1.0 24-4-2021 14:43
 */
public class TestKlasse {

    public static void main(String[] args) throws IOException {

        Path animationFile = Paths.get("resources"+File.separator+"animation"+ File.separator+"animation.bin");
        if (Files.exists(animationFile)){
            try (DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(String.valueOf(animationFile),false)))){
                dataOutputStream.writeUTF("sfdsadsa");
                dataOutputStream.writeUTF("sdsa");
                dataOutputStream.writeUTF("ik enbenn");
                dataOutputStream.writeUTF("ikben de beste");
            } catch (IOException ioException){
                ioException.printStackTrace();
            }
        } else {
            System.out.println("does not exitst");
            Files.createFile(animationFile);
            try (DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(String.valueOf(animationFile),false)))){
                dataOutputStream.writeUTF("sfdsadsa");
                dataOutputStream.writeUTF("sdsa");
                dataOutputStream.writeUTF("ik enbenn");
                dataOutputStream.writeUTF("ikben de beste");
            } catch (IOException ioException){
                ioException.printStackTrace();
            }
        }

        try (DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(String.valueOf(animationFile))))){
           while (dataInputStream.available() > 0) {
               System.out.println(dataInputStream.readUTF());;
           }

        } catch (FileNotFoundException e){
            System.out.println("geen bestand iets fout");
        }

        System.out.println(animationFile.toString());

        /*
        Speler joske = new Speler("Joske");
        Speler jefke = new Speler("Jefke");

        SpelerRanking ranking = new SpelerRanking();

        try {
            ranking.scoreFile2List();
        } catch (QuartoException e) {
            e.printStackTrace();
        }

        System.out.println(ranking.getHighScoresRanking()); //niks


        try {
            ranking.addScoreWinningPlayer(joske);
        } catch (
                QuartoException e) {
            e.printStackTrace();
        }

        try {
            ranking.addScoreLosingPlayer(jefke);
        } catch (
                QuartoException e) {
            e.printStackTrace();
        }

        try {
            ranking.scoreFile2List();
        } catch (QuartoException e) {
            e.printStackTrace();
        }

        System.out.println(ranking.getHighScoresRanking());//joske 1 jefke 0

        Speler jo = new Speler("Joske");
        Speler je = new Speler("Jefke");

        try {
            ranking.addScoreWinningPlayer(jo);
        } catch (
                QuartoException e) {
            e.printStackTrace();
        }

        try {
            ranking.addScoreLosingPlayer(je);
        } catch (
                QuartoException e) {
            e.printStackTrace();
        }

        try {
            ranking.scoreFile2List();
        } catch (QuartoException e) {
            e.printStackTrace();
        }

        System.out.println(ranking.getHighScoresRanking());//joske 2 jefke 0

        Speler j = new Speler("Jefke");
        Speler frans = new Speler("Frans",10);

        try {
            ranking.addScoreWinningPlayer(j);
        } catch (
                QuartoException e) {
            e.printStackTrace();
        }

        try {
            ranking.addScoreLosingPlayer(frans);
        } catch (
                QuartoException e) {
            e.printStackTrace();
        }

        try {
            ranking.scoreFile2List();
        } catch (QuartoException e) {
            e.printStackTrace();
        }

        System.out.println(ranking.getHighScoresRanking());//joske 2 jefke 1 frans 0

        try {
            ranking.addScoreWinningPlayer(frans);
        } catch (
                QuartoException e) {
            e.printStackTrace();
        }

        try {
            ranking.addScoreWinningPlayer(frans);
        } catch (
                QuartoException e) {
            e.printStackTrace();
        }

        try {
            ranking.addScoreWinningPlayer(frans);
        } catch (
                QuartoException e) {
            e.printStackTrace();
        }

        try {
            ranking.addScoreWinningPlayer(frans);
        } catch (
                QuartoException e) {
            e.printStackTrace();
        }

        try {
            ranking.addScoreWinningPlayer(jefke);
        } catch (
                QuartoException e) {
            e.printStackTrace();
        }

        try {
            ranking.addScoreWinningPlayer(jefke);
        } catch (
                QuartoException e) {
            e.printStackTrace();
        }

        System.out.println(ranking.getHighScoresRanking());//frans 4 jefke 3 joske 2

        try {
            ranking.clearRankingFile();
        } catch (QuartoException e) {
            e.printStackTrace();
        }

        try {
            ranking.scoreFile2List();
        } catch (QuartoException e) {
            e.printStackTrace();
        }*/

        SpelerRanking ranking = new SpelerRanking();

//        try {
//            ranking.scoreFile2List();
//        } catch (QuartoException e) {
//            e.printStackTrace();
//        }
//
//
//        System.out.println(ranking.getHighScoresRanking());//niks


    }
}
