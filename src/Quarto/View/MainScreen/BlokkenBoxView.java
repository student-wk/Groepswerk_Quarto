package Quarto.View.MainScreen;

import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

/**
 * @author Willem Kuijpers
 * @version 1.0 6-5-2021 15:22
 */
public class BlokkenBoxView extends GridPane {

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button button10;
    private Button button11;
    private Button button12;
    private Button button13;
    private Button button14;
    private Button button15;
    private Button button16;

    private Button[] buttonArray = new Button[16];


    /*
    Buttons in array plaatsen, getter voor array maken. Zo kan ik in presenter updateview een for each loop maken die shapes aan
    buttons toevoegt.
    * */

    public BlokkenBoxView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        this.button1 = new Button();
        this.button2 = new Button();
        this.button3 = new Button();
        this.button4 = new Button();
        this.button5 = new Button();
        this.button6 = new Button();
        this.button7 = new Button();
        this.button8 = new Button();
        this.button9 = new Button();
        this.button10 = new Button();
        this.button11 = new Button();
        this.button12 = new Button();
        this.button13 = new Button();
        this.button14 = new Button();
        this.button15 = new Button();
        this.button16 = new Button();
    }

    private void layoutNodes() {
        this.add(button1,0,0);
        this.add(button2,1,0);
        this.add(button3,2,0);
        this.add(button4,3,0);
        this.add(button5,4,0);
        this.add(button6,5,0);
        this.add(button7,6,0);
        this.add(button8,7,0);
        this.add(button9,0,1);
        this.add(button10,1,1);
        this.add(button11,2,1);
        this.add(button12,3,1);
        this.add(button13,4,1);
        this.add(button14,5,1);
        this.add(button15,6,1);
        this.add(button16,7,1);

        this.setGridLinesVisible(true);
    }



/*    public Button[] maakButtonArray() {
        this.getChildren().addAll(buttonArray);
        return buttonArray;
    }*/

/*    private void maakButtonArray(Button button) {
        int i = 0;
        for (button : BlokkenBoxView.get) {
            buttonArray[i] = button;
            i++;
        }
    }*/

    public Button getButton1() {
        return button1;
    }

    public Button getButton2() {
        return button2;
    }

    public Button getButton3() {
        return button3;
    }

    public Button getButton4() {
        return button4;
    }

    public Button getButton5() {
        return button5;
    }

    public Button getButton6() {
        return button6;
    }

    public Button getButton7() {
        return button7;
    }

    public Button getButton8() {
        return button8;
    }

    public Button getButton9() {
        return button9;
    }

    public Button getButton10() {
        return button10;
    }

    public Button getButton11() {
        return button11;
    }

    public Button getButton12() {
        return button12;
    }

    public Button getButton13() {
        return button13;
    }

    public Button getButton14() {
        return button14;
    }

    public Button getButton15() {
        return button15;
    }

    public Button getButton16() {
        return button16;
    }

    public Button[] getButtonArray() {
        return buttonArray;
    }
}
