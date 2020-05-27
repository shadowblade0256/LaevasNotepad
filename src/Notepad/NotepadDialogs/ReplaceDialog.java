package Notepad.NotepadDialogs;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

import Notepad.NotepadDataField;

//替换对话框类
public class ReplaceDialog extends FindDialog
{
    JLabel lbReplaceWith;           //替换词框标签
    JTextField tfReplaceWith;       //替换词框
    JPanel pnReplaceWith;           //替换词框面板

    JButton btReplaceFirst;         //替换下一个按钮
    JButton btReplaceAll;           //全部替换按钮

    ReplaceDialogListener lisReplaceDialog;     //替换对话框监听器

    public ReplaceDialog(NotepadDataField dataField,String prevKeyword)
    {
        super(dataField,prevKeyword);
        setTitle("替换");
        setBounds(100, 100, 420, 180);
    }

    //初始化替换对话框布局
    void initLayout()
    {
        super.initLayout();
        setLayout(new FlowLayout());
        lbReplaceWith=new JLabel("替换为(P)：");
        tfKeywordField.setColumns(35);
        tfReplaceWith=new JTextField(35);
        pnReplaceWith=new JPanel();
        pnReplaceWith.setLayout(new BoxLayout(pnReplaceWith,BoxLayout.Y_AXIS));
        pnReplaceWith.setAlignmentX(JDialog.LEFT_ALIGNMENT);
        pnReplaceWith.add(lbReplaceWith);
        pnReplaceWith.add(tfReplaceWith);

        pnLeft=new JPanel();
        pnLeft.setLayout(new BoxLayout(pnLeft,BoxLayout.Y_AXIS));
        pnLeft.setAlignmentX(JDialog.LEFT_ALIGNMENT);
        pnLeft.add(pnKeywordComponents);
        pnLeft.add(pnReplaceWith);
        pnBottom.remove(pnFindDirectionComponents);
        pnLeft.add(pnBottom);

        btReplaceFirst=new JButton("替换(R)");
        btReplaceAll=new JButton("全部替换(A)");
        btReplaceFirst.setMnemonic('R');
        btReplaceAll.setMnemonic('A');
        pnButtons.setLayout(new GridLayout(4,1));
        pnButtons.remove(btCancel);
        pnButtons.add(btReplaceFirst);
        pnButtons.add(btReplaceAll);
        pnButtons.add(btCancel);

        remove(pnButtons);
        add(pnLeft);
        add(pnButtons);
    }

    void initListener()
    {
        //添加取消按钮监听器
        btCancel.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                dispose();
            }
        });

        //添加其余各控件的监听器
        lisReplaceDialog=new ReplaceDialogListener(dataField,prevKeyword);
        lisReplaceDialog.setTextField(tfKeywordField);
        lisReplaceDialog.setReplaceWithField(tfReplaceWith);
        lisReplaceDialog.setSpecialCheckBox(cbIsCaseSensitive,cbCanWrap);
        lisReplaceDialog.setDirectionRadioButton(rbForward,rbBackward);
        lisReplaceDialog.setFindNextButton(btFindNext);
        lisReplaceDialog.setReplaceFirstButton(btReplaceFirst);
        lisReplaceDialog.setReplaceAllButton(btReplaceAll);
        btFindNext.addActionListener(lisReplaceDialog);
        btReplaceFirst.addActionListener(lisReplaceDialog);
        btReplaceAll.addActionListener(lisReplaceDialog);
    }
}