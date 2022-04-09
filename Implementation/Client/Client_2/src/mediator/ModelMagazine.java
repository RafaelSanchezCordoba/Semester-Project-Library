package mediator;

import model.Magazine;

public interface ModelMagazine extends PropertyChangeSubject{
    void addMagazine(Magazine magazine);
    void removeMagazine(int id);
}
