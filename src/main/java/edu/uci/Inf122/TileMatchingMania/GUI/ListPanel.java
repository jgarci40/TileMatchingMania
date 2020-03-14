package edu.uci.Inf122.TileMatchingMania.GUI;

import javax.swing.event.*;
import javax.swing.*;
import java.util.function.Consumer;

public class ListPanel extends JPanel implements ListSelectionListener
{
    //lists
    static JList b1;
    Consumer<String> callBack;

    public void setCallBack(Consumer consumer) {
        this.callBack = consumer;
    }

    public ListPanel() {
        b1 = new JList();
        b1.addListSelectionListener(this::valueChanged);
        add(b1);
    }

    public void addList(String strings[]) {
        remove(b1);
        b1 = new JList(strings);
        add(b1);
        b1.addListSelectionListener(this::valueChanged);
        setVisible(true);
    }

    public void valueChanged(ListSelectionEvent e)
    {
        callBack.accept((String)b1.getSelectedValue());
    }
} 