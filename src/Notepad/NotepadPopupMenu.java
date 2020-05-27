package Notepad;

import java.awt.event.*;
import javax.swing.*;
import java.io.*;

//右键弹出式菜单，带监听器
public class NotepadPopupMenu extends JPopupMenu implements ActionListener
{
    JMenuItem mnUndo;           //撤销
    JMenuItem mnCut;            //剪切
    JMenuItem mnCopy;           //复制
    JMenuItem mnPaste;          //粘贴
    JMenuItem mnDelete;         //删除
    JMenuItem mnSelectAll;      //全选
    JMenuItem mnBingSearch;     //Bing搜索

    NotepadDataField dataField; //公有数据域

    public NotepadPopupMenu(NotepadDataField dataField)
    {
        super();
        addMenuItems();
        setMenuItemListener();
        this.dataField=dataField;
    }

    //初始化菜单项
    void addMenuItems()
    {
        mnUndo=new JMenuItem("撤销(U)",'U');
        mnCut=new JMenuItem("剪切(T)",'T');
        mnCopy=new JMenuItem("复制(C)",'C');
        mnPaste=new JMenuItem("粘贴(V)",'V');
        mnDelete=new JMenuItem("删除(L)",'L');
        mnSelectAll=new JMenuItem("全选(A)",'A');
        mnBingSearch=new JMenuItem("使用Bing搜索...");

        add(mnUndo);
        addSeparator();
        add(mnCut);
        add(mnCopy);
        add(mnPaste);
        add(mnDelete);
        add(mnSelectAll);
        addSeparator();
        add(mnBingSearch);
    }

    //设置菜单项监听器
    void setMenuItemListener()
    {
        mnUndo.addActionListener(this);
        mnCopy.addActionListener(this);
        mnPaste.addActionListener(this);
        mnCut.addActionListener(this);
        mnDelete.addActionListener(this);
        mnSelectAll.addActionListener(this);
        mnBingSearch.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource()==mnUndo)                  //撤销
            dataField.getDocumentArea().undo();
        else if (e.getSource()==mnCut)              //剪切
            dataField.getDocumentArea().cut();
        else if (e.getSource()==mnCopy)             //复制
            dataField.getDocumentArea().copy();
        else if (e.getSource()==mnPaste)            //粘贴
            dataField.getDocumentArea().paste();
        else if (e.getSource()==mnDelete)           //删除
            dataField.getDocumentArea().replaceSelection("");
        else if (e.getSource()==mnBingSearch)       //使用Bing搜索
        {
            String[] website = new String[2];
            website[0]="C:/Program Files (x86)/Internet Explorer/iexplore.exe";
            website[1]="cn.bing.com/?q="+dataField.getDocumentArea().getSelectedText();
            try
            {
                Runtime.getRuntime().exec(website);
            }
            catch (IOException ioe)
            {
                JOptionPane.showMessageDialog(null,"无法调用本机上的Internet Explorer","错误",JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (e.getSource()==mnSelectAll)    //全选
        {
            dataField.getDocumentArea().setSelectionStart(0);
            dataField.getDocumentArea().setSelectionEnd(dataField.getDocumentArea().getText().length());
        }
    }
}