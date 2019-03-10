package com.arkainfoteck.helpmate.Model;

public class Person {
    String personName;

    boolean selected;
    public Person(String name) {
        this.personName = name;

    }
    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    public String getPersonName() {
        return personName;
    }
    public void setPersonName(String personName) {
        this.personName = personName;
    }
}

