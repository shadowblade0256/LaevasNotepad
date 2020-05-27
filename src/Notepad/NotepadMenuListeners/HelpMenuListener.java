package Notepad.NotepadMenuListeners;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;

//帮助菜单项监听器
public class HelpMenuListener implements ActionListener
{
    JMenuItem mnHelpAbout;      //关于
    
    //绑定帮助菜单项
    public void bindHelpAbout(JMenuItem mnHelpAbout)
    {
        this.mnHelpAbout=mnHelpAbout;
    }

    //当选中菜单项
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource()==mnHelpAbout)
            JOptionPane.showMessageDialog(null,
                                "记事本v0.5\nby:Siyuan Wang from AHNU School of CS & Informations\n2020-05-27",
                                "关于",
                                JOptionPane.INFORMATION_MESSAGE);
    }
}