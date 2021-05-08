package Quarto.View.MainScreen;

import Quarto.Model.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.util.Arrays;

/**
 * @author Willem Kuijpers
 * @version 1.0 7-5-2021 20:38
 */
public class BlokkenBoxPresenter {

    private Quarto model;
    private BlokkenBoxView view;
    private BlokkenBoxShape[] blokkenShapes;
    private BlokkenBoxShape shape;
    private Button[] buttonArray;

    public BlokkenBoxPresenter(Quarto model, BlokkenBoxView view) {
        this.model = model;
        this.view = view;
        updateView();
        eventHandlers();
    }

    private void updateView() {
        blokkenShapes = new BlokkenBoxShape[model.getBlokkenBox().getBlokkenSet().size()];
        this.maakBlokkenShapes();
        Button[] buttonArray = {this.view.getButton1(), this.view.getButton2(), this.view.getButton3(),
                this.view.getButton4(), this.view.getButton4(), this.view.getButton5(), this.view.getButton6(),
                this.view.getButton7(),this.view.getButton8(),this.view.getButton9(),this.view.getButton9(),
                this.view.getButton10(),this.view.getButton11(),this.view.getButton12(),this.view.getButton13(),
                this.view.getButton14(),this.view.getButton15(),this.view.getButton16()};

        for (int i = 0; i < blokkenShapes.length-1; i++) {
            buttonArray[i].setShape(blokkenShapes[i]);
        }
        //voegt de juiste blokkenShape aan het juiste vakje van BlokkenBoxView toe
        // mss Shape als image aan button toevoegen
        // https://www.tutorialspoint.com/how-to-add-an-image-to-a-button-action-in-javafx
    }

    public BlokkenBoxShape[] maakBlokkenShapes() {
        int i = 0;
        for (Blok blok : model.getBlokkenBox().getBlokkenSet()) {
            shape = new BlokkenBoxShape(blok);
            blokkenShapes[i] = shape;
            i++;
        }
        return blokkenShapes;
    }

    public Button[] maakButtonArray() {


        return buttonArray;
    }

/*    public void linkShapesaanButtons() {
        blokkenShapes = new BlokkenBoxShape[model.getBlokkenBox().getBlokkenSet().size()];
        this.maakBlokkenShapes();
        this.view.maakButtonArray();
        Button[] buttonArray = this.view.getButtonArray();

        for (int i = 0; i < blokkenShapes.length-1; i++) {
            buttonArray[i].setShape(blokkenShapes[i]);
        }
    }*/

    private void eventHandlers() {
        //bij het klikken op een vakje de juiste index meegeven aan kiesBlok()
        // een if methode maken zodat alleen buttons met een shape eraan verbonden een actie kunnen uitvoeren.
        view.getButton1().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                /*
                * als button een blokShape heeft, dan geeft de knop iets door, anders niet. Nog testen!
                * */
                if (view.getButton1().getShape().hasProperties() == true) {
                    model.kiesBlok(1);
                    updateView();
                }
            }
        });
        view.getButton2().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (view.getButton2().getShape().hasProperties() == true) {
                    model.kiesBlok(2);
                    updateView();
                }
            }
        });
        view.getButton3().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (view.getButton3().getShape().hasProperties() == true) {
                    model.kiesBlok(3);
                    updateView();
                }
            }
        });
        view.getButton4().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (view.getButton4().getShape().hasProperties() == true) {
                    model.kiesBlok(4);
                    updateView();
                }
            }
        });
        view.getButton5().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (view.getButton5().getShape().hasProperties() == true) {
                    model.kiesBlok(5);
                    updateView();
                }
            }
        });
        view.getButton6().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (view.getButton6().getShape().hasProperties() == true) {
                    model.kiesBlok(6);
                    updateView();
                }
            }
        });
        view.getButton7().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (view.getButton7().getShape().hasProperties() == true) {
                    model.kiesBlok(7);
                    updateView();
                }
            }
        });
        view.getButton8().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (view.getButton8().getShape().hasProperties() == true) {
                    model.kiesBlok(8);
                    updateView();
                }
            }
        });
        view.getButton9().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (view.getButton9().getShape().hasProperties() == true) {
                    model.kiesBlok(9);
                    updateView();
                }
            }
        });
        view.getButton10().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (view.getButton10().getShape().hasProperties() == true) {
                    model.kiesBlok(10);
                    updateView();
                }
            }
        });
        view.getButton11().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (view.getButton11().getShape().hasProperties() == true) {
                    model.kiesBlok(11);
                    updateView();
                }
            }
        });
        view.getButton12().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (view.getButton12().getShape().hasProperties() == true) {
                    model.kiesBlok(12);
                    updateView();
                }
            }
        });
        view.getButton13().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (view.getButton13().getShape().hasProperties() == true) {
                    model.kiesBlok(13);
                    updateView();
                }
            }
        });
        view.getButton14().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (view.getButton14().getShape().hasProperties() == true) {
                    model.kiesBlok(14);
                    updateView();
                }
            }
        });
        view.getButton15().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (view.getButton15().getShape().hasProperties() == true) {
                    model.kiesBlok(15);
                    updateView();
                }
            }
        });
        view.getButton16().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (view.getButton16().getShape().hasProperties() == true) {
                    model.kiesBlok(16);
                    updateView();
                }
            }
        });
    }

    public BlokkenBoxShape[] getBlokkenShapes() {
        return blokkenShapes;
    }


}
