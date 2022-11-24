
package prbpackage;

import javax.swing.Icon;

public class Item {
    private String text="";
    private Icon icon;

    public Item() {
    }

    public Item(String text, Icon icon) {
        this.icon = icon;
        this.text=text;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the icon
     */
    public Icon getIcon() {
        return icon;
    }

    /**
     * @param icon the icon to set
     */
    public void setIcon(Icon icon) {
        this.icon = icon;
    }
}
