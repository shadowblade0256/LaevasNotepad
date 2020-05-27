package Notepad;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.undo.*;

import java.awt.Toolkit;
import java.awt.event.*;

//可撤消的文本区，支持撤销与重做功能
public class UndoableTextArea extends JTextArea implements UndoableEditListener,KeyListener
{
    UndoManager undoManager;        //撤销管理器

    Boolean saved;                  //传递给外部的NotepadDataField，表示文档是否已保存

    //初始化空可撤销文本区
    public UndoableTextArea()
    {
        this(new String());
    }

    //初始化有内容的可撤销文本区
    public UndoableTextArea(String text)
    {
        super(text);
        saved=false;
        createUndoManager();
        getDocument().addUndoableEditListener(this);
        addKeyListener(this);
        getInputMap().put(KeyStroke.getKeyStroke("control H"),"none");
    }

    //初始化撤销管理器
    void createUndoManager()
    {
        undoManager=new UndoManager();
        undoManager.setLimit(100);
    }

    //撤销
    public void undo()
    {
        try
        {
            undoManager.undo();
        }
        catch (CannotUndoException cue)
        {
            Toolkit.getDefaultToolkit().beep();
        }
    }

    //重做
    public void redo()
    {
        try
        {
            undoManager.redo();
        }
        catch (CannotRedoException cre)
        {
            Toolkit.getDefaultToolkit().beep();
        }
    }

    //当编辑发生时，添加此编辑到撤销管理器中
    public void undoableEditHappened(UndoableEditEvent e)
    {
        saved=false;                        //编辑发生时，文件即切换为未保存状态
        undoManager.addEdit(e.getEdit());
    }

    //监听键盘，当按下Ctrl+Z(Ctrl+Y)时，执行撤销(重做)指令
    public void keyPressed(KeyEvent e)
    {
        //Ctrl+Z，撤销
        if (e.isControlDown() && e.getKeyCode()==KeyEvent.VK_Z)
            undo();
        //Ctrl+Y，重做
        else if (e.isControlDown() && e.getKeyCode()==KeyEvent.VK_Y)
            redo();
    }

    public void keyTyped(KeyEvent e)
    {}

    public void keyReleased(KeyEvent e)
    {}
}