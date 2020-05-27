package Notepad;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

//记事本窗体
public class NotepadWindow extends JFrame 
{
    static final long serialVersionUID = 0L;

    NotepadMenuBar menuBar;
    NotepadPopupMenu popUp;
    UndoableTextArea taDocumentArea;
    JScrollPane pnDocumentPane;
    NotepadDataField dataField;
    File currentFile;

    PopupMenuTriggerListener lisPMTrigger;

    NotepadWindow()
    {
        try
        {
            Runtime.getRuntime().exec("cmd chcp 65001");    //本程序使用UTF-8编码
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        setTitle("记事本");
        setBounds(100, 100, 700, 500);
        initGlobalFontSetting(new Font("SimSun",Font.PLAIN,12));
        initDataField();
        initLayout();
        addPopupMenu();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addCloseCheckListener();
    }

    //设置全局字体
    static void initGlobalFontSetting(Font fnt)
    {
        FontUIResource fontRes = new FontUIResource(fnt);
        for (Enumeration keys = UIManager.getDefaults().keys(); keys.hasMoreElements();)
        {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if(value instanceof FontUIResource)
                UIManager.put(key, fontRes);
        }
    }

    //初始化布局
    void initLayout()
    {
        setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));

        menuBar=new NotepadMenuBar(dataField);
        taDocumentArea.setCaretPosition(0);
        pnDocumentPane=new JScrollPane(taDocumentArea);
        pnDocumentPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        pnDocumentPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        setJMenuBar(menuBar);
        add(pnDocumentPane);
    }

    //初始化数据域
    void initDataField()
    {
        taDocumentArea=new UndoableTextArea();
        currentFile=new File("Untitled");
        dataField=new NotepadDataField(taDocumentArea,currentFile);
    }

    //设置关闭时检查文件是否保存的监听器
    void addCloseCheckListener()
    {
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                if (!dataField.isSaved())
                {
                    int choice=JOptionPane.showConfirmDialog(null,"当前文档未保存，是否保存？",
                            "提示",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);

                    switch (choice)
                    {
                        case JOptionPane.YES_OPTION:        //是则打开保存对话框，保存文件
                            JFileChooser fileChooser=new JFileChooser();
                            fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                            fileChooser.setFileFilter(new TextFileFilter());
                            int saveChoice=fileChooser.showSaveDialog(null);
                            if (saveChoice==JFileChooser.APPROVE_OPTION)
                            {
                                dataField.setFile(fileChooser.getSelectedFile());
                                dataField.fileSave();
                                dataField.setSaved(true);
                            }
                        case JOptionPane.NO_OPTION:         //否则不做任何操作
                        case JOptionPane.CANCEL_OPTION:     //若取消，不做任何操作，并且不关闭窗口
                    }
                    if (choice!=JOptionPane.CANCEL_OPTION)
                        dispose();
                }
            }
        });
    }

    //添加弹出式菜单
    void addPopupMenu()
    {
        popUp=new NotepadPopupMenu(dataField);
        lisPMTrigger=new PopupMenuTriggerListener(popUp);
        addMouseListener(lisPMTrigger);
        dataField.taDocumentArea.addMouseListener(lisPMTrigger);
    }
}