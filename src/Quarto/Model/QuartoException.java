package Quarto.Model;

import java.io.IOException;

/**
 * @author Willem Kuijpers
 * @version 1.0 23-4-2021 09:11
 */
public class QuartoException extends Exception {

    public QuartoException(String msg) {super(msg);
    }

    public QuartoException (Throwable cause) {
        super(cause);
    }

    public QuartoException(String message, Throwable cause) {
        super(message, cause);
    }
}
