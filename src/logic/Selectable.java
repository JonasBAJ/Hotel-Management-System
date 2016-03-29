package logic;


import javafx.beans.value.ObservableValue;

public interface Selectable
{
    void setSelected(boolean selected);

    ObservableValue<Boolean> getSelected();
}
