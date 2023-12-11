import java.io.Serializable;

public class EmptyQueueException extends RuntimeException implements Serializable {
    // Add a serialVersionUID field
    private static final long serialVersionUID = 1L;

    public EmptyQueueException() {
        this(null);
    }

    public EmptyQueueException(String message) {
        super(message);
    }
}
