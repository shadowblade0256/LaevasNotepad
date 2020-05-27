package Notepad.NotepadMenuListeners;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import Notepad.NotepadDataField;

public class ViewMenuListener implements ActionListener
{
    JMenuItem mnZoomIn;             //放大文字
    JMenuItem mnZoomOut;            //缩小文字
    JMenuItem mnZoomDefault;        //恢复文字比例至默认值

    static double textScale=1.0;        //文字缩放比例，默认1.0
    static Font defaultFont;            //文本域默认字体

    NotepadDataField dataField;     //公有数据域

    public ViewMenuListener(NotepadDataField dataField)
    {
        this.dataField=dataField;
        defaultFont=dataField.getTextFont();
    }


    //绑定菜单项
    public void setMenuItem(JMenuItem mnZoomIn,JMenuItem mnZoomOut,JMenuItem mnZoomDefault)
    {
        this.mnZoomIn=mnZoomIn;
        this.mnZoomOut=mnZoomOut;
        this.mnZoomDefault=mnZoomDefault;
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource()==mnZoomIn)            //放大
        {
            textScale+=0.5;
            dataField.getDocumentArea().setFont(new Font(defaultFont.getName(),defaultFont.getStyle(),
                (int)(defaultFont.getSize()*textScale)));
        }
        else if (e.getSource()==mnZoomOut)      //缩小
        {
            textScale-=0.5;
            dataField.getDocumentArea().setFont(new Font(defaultFont.getName(),defaultFont.getStyle(),
                (int)(defaultFont.getSize()*textScale)));
        }
        if (e.getSource()==mnZoomIn)            //恢复文字比例
        {
            textScale=1.0;
            dataField.getDocumentArea().setFont(defaultFont);
        }
    }
}