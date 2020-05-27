package Notepad.NotepadMenuListeners;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.*;
import Notepad.*;
import java.awt.event.*;

//文件菜单监听器
public class FileMenuListener implements ActionListener
{
    JMenuItem mnNew;        //新建
    JMenuItem mnOpen;       //打开
    JMenuItem mnSave;       //保存
    JMenuItem mnSaveAs;     //另存为
    JMenuItem mnExit;       //退出

    NotepadDataField dataField;     //公有数据域

    public FileMenuListener(NotepadDataField dataField)
    {
        this.dataField=dataField;
    }

    //绑定菜单项
    public void setMenuItems(JMenuItem mnNew,JMenuItem mnOpen,JMenuItem mnSave,
                            JMenuItem mnSaveAs,JMenuItem mnExit)
    {
        this.mnNew=mnNew;
        this.mnOpen=mnOpen;
        this.mnSave=mnSave;
        this.mnSaveAs=mnSaveAs;
        this.mnExit=mnExit;
    }

    //单击对应菜单项时，执行相关操作
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource()==mnNew)      //新建
        {
            if (dataField.isSaved())    //如果已保存，重置文本域和当前文件信息
            {
                dataField.getDocumentArea().setText("");
                dataField.setFile(new File("Untitled"));
                dataField.setSaved(false);
            }
            else                        //否则直接重置文本域和当前文件信息
            {
                int saveConfirmChoice=saveConfirm();
                if (saveConfirmChoice!=JOptionPane.CANCEL_OPTION && saveConfirmChoice!=JOptionPane.CLOSED_OPTION)
                {
                    dataField.getDocumentArea().setText("");
                    dataField.setFile(new File("Untitled"));
                    dataField.setSaved(false);
                }
            }
        }
        else if (e.getSource()==mnOpen)     //打开
        {
            if (dataField.isSaved())    //如果已保存，通过打开框载入文件状态，并将文件载入文本域
            {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                fileChooser.setFileFilter(new TextFileFilter());
                int saveChoice = fileChooser.showOpenDialog(null);
                if (saveChoice == JFileChooser.APPROVE_OPTION)
                {
                    dataField.setFile(fileChooser.getSelectedFile());
                    dataField.fileLoad();
                }
            }
            else                        //否则先保存，再通过打开框载入文件状态，并将文件载入文本域
            {
                int saveConfirmChoice=saveConfirm();
                if (saveConfirmChoice!=JOptionPane.CANCEL_OPTION && saveConfirmChoice!=JOptionPane.CLOSED_OPTION)
                {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                    fileChooser.setFileFilter(new TextFileFilter());
                    int saveChoice = fileChooser.showOpenDialog(null);
                    if (saveChoice == JFileChooser.APPROVE_OPTION)
                    {
                        dataField.setFile(fileChooser.getSelectedFile());
                        dataField.fileLoad();
                    }
                }
            }
        }
        else if (e.getSource()==mnSave)     //保存
        {
            if (dataField.isSaved())    //保存过直接按照原路径写入
            {
                dataField.fileSave();
                dataField.setSaved(true);
            }
            else                        //否则等同于另存为
                saveAs();
        }
        else if (e.getSource()==mnSaveAs)   //另存为
            saveAs();
        else if (e.getSource()==mnExit)     //退出
            Runtime.getRuntime().exit(0);
    }

    //弹出保存确认对话框，并执行对应操作
    public int saveConfirm()
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
                return JOptionPane.YES_OPTION;
            case JOptionPane.NO_OPTION:         //否则不做任何操作
                return JOptionPane.NO_OPTION;
            case JOptionPane.CANCEL_OPTION:     //若取消，不做任何操作，并且需要在调用处取消操作
                return JOptionPane.CANCEL_OPTION;
            default:
                return -900;
        }
    }

    //实现另存为动作
    public void saveAs()
    {
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
    }
}