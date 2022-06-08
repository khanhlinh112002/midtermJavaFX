package com.example.demo;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
public class Animal {
    private final StringProperty id;
    private final StringProperty name;


    public Animal()
    {
        id = new SimpleStringProperty(this, "id");
        name = new SimpleStringProperty(this, "name");


    }

    public StringProperty idProperty() { return id; }
    public String getId() { return id.get(); }
    public void setId(String newId) { id.set(newId); }

    public StringProperty nameProperty() { return name; }
    public String getName() { return name.get(); }
    public void setName(String newName) { name.set(newName); }



}
