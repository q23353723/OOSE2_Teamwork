package model;

import javafx.beans.property.*;

public class Powerbank implements Cloneable {
    private String id;
    private boolean available;
    private int capacity;
    private StringProperty idProperty = new SimpleStringProperty();
    private StringProperty capacityProperty = new SimpleStringProperty();
    private DoubleProperty capacityBarProperty = new SimpleDoubleProperty();

    public Powerbank(){};

    public void setPowerbank(String id, int capacity, boolean available) {
        this.id = id;
        this.capacity = capacity;
        this.available = available;
        this.idProperty.set(id);
        this.capacityBarProperty.set(capacity/4000.0);
        this.capacityProperty.set(Integer.toString(capacity) + "mAh");
    }

    public String getId() {
        return id;
    }

    public boolean getAvailable() {
        return available;
    }

    public int getCapacity() { return capacity; }

    public StringProperty idProperty() {
        return idProperty;
    }
    public StringProperty capacityProperty() {
        return capacityProperty;
    }
    public DoubleProperty capacityBarProperty() {
        return capacityBarProperty;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public Powerbank clone() {
        Object obj = null;
        try {
            obj = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return (Powerbank)obj;
    }
}
