
package prbpackage;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author Diego
 * @param <E>
 */
public class MyList<E extends Object> extends JList<E>{

    private final DefaultListModel model;
    
    public MyList() {
        model = new DefaultListModel();
        setModel(model);
    }

    @Override
    public ListCellRenderer getCellRenderer() {
        return new DefaultListCellRenderer(){
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean selected, boolean focus) {
             ListItem item = new  ListItem();
             item.setItem(value);
             return item;
            }
           
    };
    }
    public void addItem (Item item){
        model.addElement(item);
    }
}
