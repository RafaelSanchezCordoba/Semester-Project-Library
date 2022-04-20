package mediator;

import java.beans.PropertyChangeListener;

/**
 * The property change subject interface.
 * @author Rafael Sánchez Córdoba.
 * @version 1.0 09/04/22
 */
public interface PropertyChangeSubject {
    /**
     *
     * @param listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener);

    /**
     *
     * @param name
     * @param listener
     */
    public void addPropertyChangeListener(String name, PropertyChangeListener listener);

    /**
     *
     * @param listener
     */
    public void removePropertyChangeListener(PropertyChangeListener listener);

    /**
     *
     * @param name
     * @param listener
     */
    public void removePropertyChangeListener(String name, PropertyChangeListener listener);
}
