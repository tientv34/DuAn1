/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import ViewModels.ModelKhuVuc;
import java.awt.Component;
import java.awt.Image;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Admin
 */
public class KhuVucJList1 extends DefaultListCellRenderer implements ListCellRenderer<Object> {

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object valueObject, int index, boolean isSelected, boolean hasFocus) {
        ModelKhuVuc khuVuc = (ModelKhuVuc) valueObject;
        setText(khuVuc.getTenKhuVuc());
        String url = "";
        if (khuVuc.getImageID() == null) {
            url = "planet-earth.png";
        }else{
            url = khuVuc.getImageID().getDuongDanAnh();
        }
        ImageIcon icon = new ImageIcon("src\\main\\resources\\ImageLogin\\" + url);
        Image image = icon.getImage().getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
        ImageIcon icon1 = new ImageIcon(image);
        setIcon(icon1);
        setBorder(new EmptyBorder(5, 10, 5, 5));
        setIconTextGap(10);
        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        setEnabled(true);
        setFont(list.getFont());
        return this;
    }
}
