package Notepad.NotepadMenuListeners;

import java.awt.event.*;

import javax.swing.*;
import Notepad.NotepadDataField;
import Notepad.NotepadDialogs.FontDialog;

import java.awt.*;

//格式菜单监听器
public class FormatMenuListener implements ActionListener
{
    JCheckBoxMenuItem cbCanWrap;        //自动换行菜单项
    NotepadDataField dataField;         //公有数据域

    public FormatMenuListener(NotepadDataField dataField)
    {
        this.dataField=dataField;
    }

    //绑定菜单项
    public void setMenuItems(JCheckBoxMenuItem cbCanWrap)
    {
        this.cbCanWrap=cbCanWrap;
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource()==cbCanWrap)       //自动换行
        {
            if (cbCanWrap.isSelected())     //选中即启动自动换行和以词为单位换行
            {
                dataField.getDocumentArea().setLineWrap(true);
                dataField.getDocumentArea().setWrapStyleWord(true);
            }
            else
            {
                dataField.getDocumentArea().setLineWrap(false);
                dataField.getDocumentArea().setWrapStyleWord(false);
            }   
        }
    }    
}