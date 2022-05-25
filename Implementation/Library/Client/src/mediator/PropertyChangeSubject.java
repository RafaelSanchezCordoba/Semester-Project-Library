package mediator;

import java.beans.PropertyChangeListener;

/**
 * The property change subject interface.
 * @author Rafael Sánchez Córdoba.
 * @version 1.0 09/04/22
 */
public interface PropertyChangeSubject {
    /**
     * Add property change listener, just with the listener
     * @param listener
     * The listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener);

    /**
     * Add property change listener with a name and the listener
     * @param name
     * The name
     * @param listener
     * The listener
     */
    public void addPropertyChangeListener(String name, PropertyChangeListener listener);

    /**
     * Remove property change listener, just with the listener
     * @param listener
     * The listener
     */
    public void removePropertyChangeListener(PropertyChangeListener listener);

    /**
     * Remove property change listener with a name and the listener
     * @param name
     * The name
     * @param listener
     * The listener
     */
    public void removePropertyChangeListener(String name, PropertyChangeListener listener);
}
