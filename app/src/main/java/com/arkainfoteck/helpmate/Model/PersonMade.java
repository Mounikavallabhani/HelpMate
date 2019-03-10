package com.arkainfoteck.helpmate.Model;

public class PersonMade {

    String personName;

    boolean selected;
    public PersonMade(String name) {
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
