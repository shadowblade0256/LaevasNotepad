package Notepad.NotepadDialogs;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.io.*;
import java.awt.*;
import Notepad.NotepadDataField;
import Notepad.UndoableTextArea;
import java.awt.event.*;
import java.util.*;

public class GotoDialog extends JDialog implements ActionListener
{
    JLabel lbLineNumber;        //行号框标签
    JTextField tfLineNumber;    //行号框
    JPanel pnLineNumber;        //行号框控件组
    JButton btGoto;             //转到按钮

    NotepadDataField dataField;     //公有数据域

    public GotoDialog(NotepadDataField dataField)
    {
        super();
        setTitle("转到");
        setBounds(600,250,150,90);
        setEnabled(false);
        this.dataField=dataField;
        //开启自动换行时，转到功能不可用
        setEnabled(true);
        initLayout();
        initListener();
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        if (dataField.getDocumentArea().getLineWrap()==true)
        {
            JOptionPane.showMessageDialog(null,"开启自动换行时本操作不可执行","提示",JOptionPane.WARNING_MESSAGE);
            setVisible(false);
            dispose();
        }
    }
    
    //初始化布局
    void initLayout()
    {
        setLayout(new FlowLayout());
        lbLineNumber=new JLabel("行号：");
        tfLineNumber=new JTextField(5);
        pnLineNumber=new JPanel();
        pnLineNumber.setLayout(new BoxLayout(pnLineNumber,BoxLayout.X_AXIS));
        pnLineNumber.add(lbLineNumber);
        pnLineNumber.add(tfLineNumber);
        btGoto=new JButton("转到");
        add(pnLineNumber);
        add(btGoto);
    }

    //初始化监听器
    void initListener()
    {
        btGoto.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e)
    {
        //单击转到按钮时，将光标移至对应行开头并关闭对话框
        if (e.getSource()==btGoto)
        {
            int targetLineNumber=-1;
            try
            {
                targetLineNumber=Integer.parseInt(tfLineNumber.getText());
            }
            catch (NumberFormatException nfe)   //如果输入的不是整数
            {
                JOptionPane.showMessageDialog(this,"行号必须为整数\n请输入有效的行号","提示",
                    JOptionPane.WARNING_MESSAGE);
            }
            //如果输入行数超过总行数
            if (Integer.parseInt(tfLineNumber.getText())>dataField.getDocumentArea().getLineCount())
                JOptionPane.showMessageDialog(this,"行号超过了总行数","提示",JOptionPane.WARNING_MESSAGE);
            else
            {
                int resultCaret=-1;
                try
                {
                    resultCaret = dataField.getDocumentArea().getLineStartOffset(targetLineNumber - 1);
                } 
                catch (BadLocationException e1)
                {}
                dataField.getDocumentArea().setCaretPosition(resultCaret);
                dispose();
            }
        }
    }
}