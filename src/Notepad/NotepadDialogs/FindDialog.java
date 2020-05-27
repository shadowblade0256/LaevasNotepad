package Notepad.NotepadDialogs;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.FontUIResource;

import Notepad.NotepadDataField;
import java.awt.event.*;
import java.util.Enumeration;
import java.awt.*;

//查找对话框
public class FindDialog extends JDialog
{
    NotepadDataField dataField;     //公有数据域

    JLabel lbKeyword;               //查找框标签
    JTextField tfKeywordField;      //查找框
    JPanel pnKeywordComponents;     //查找框控件组

    JCheckBox cbIsCaseSensitive;        //区分大小写复选框
    JCheckBox cbCanWrap;                //是否循环复选框
    JPanel pnSpecial;                  //特殊选项控件组

    JRadioButton rbForward;             //向前查找单选框
    JRadioButton rbBackward;            //向后查找单选框
    ButtonGroup btgFindDirection;       //查找方向单选框组
    JPanel pnFindDirectionComponents;   //查找方向控件组

    JPanel pnBottom;                //底部面板
    JPanel pnLeft;                  //左侧面板

    JButton btFindNext;             //查找下一个
    JButton btCancel;               //取消
    JPanel pnButtons;               //按钮面板

    String prevKeyword;                 //上一次查找的关键词
    FindDialogListener lisFindDialog;   //查找对话框行为监听器

    public FindDialog(NotepadDataField dataField,String prevKeyword)
    {
        setTitle("查找");
        setBounds(100, 100, 420, 140);
        this.dataField=dataField;
        this.prevKeyword=prevKeyword;
        initLayout();
        initListener();
        setVisible(true);
        setResizable(false);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    //初始化布局
    void initLayout()
    {
        setLayout(new FlowLayout());

        //初始化查找框控件组
        lbKeyword=new JLabel("查找内容(N)：");
        tfKeywordField=new JTextField(25);
        tfKeywordField.setText(prevKeyword);
        pnKeywordComponents=new JPanel();
        pnKeywordComponents.setLayout(new BoxLayout(pnKeywordComponents,BoxLayout.Y_AXIS));
        pnKeywordComponents.setAlignmentX(JDialog.LEFT_ALIGNMENT);
        pnKeywordComponents.add(lbKeyword);
        pnKeywordComponents.add(tfKeywordField);

        //初始化特殊选项控件组
        cbIsCaseSensitive=new JCheckBox("区分大小写(C)");
        cbCanWrap=new JCheckBox("循环(R)");
        pnSpecial=new JPanel();
        cbCanWrap.setSelected(true);    //默认允许循环
        pnSpecial.setLayout(new BoxLayout(pnSpecial,BoxLayout.Y_AXIS));
        pnSpecial.setAlignmentX(JDialog.LEFT_ALIGNMENT);
        pnSpecial.add(cbIsCaseSensitive);
        pnSpecial.add(cbCanWrap);
        
        //初始化查找方向控件组
        rbForward=new JRadioButton("向下");
        rbBackward=new JRadioButton("向上");
        btgFindDirection=new ButtonGroup();
        btgFindDirection.add(rbForward);
        btgFindDirection.add(rbBackward);
        rbForward.setSelected(true);            //默认向下查找
        pnFindDirectionComponents=new JPanel();
        pnFindDirectionComponents.setBorder(BorderFactory.createTitledBorder("查找方向"));
        pnFindDirectionComponents.setLayout(new BoxLayout(pnFindDirectionComponents,BoxLayout.X_AXIS));
        pnFindDirectionComponents.setAlignmentX(JDialog.LEFT_ALIGNMENT);
        pnFindDirectionComponents.add(rbForward);
        pnFindDirectionComponents.add(rbBackward);

        //初始化按钮
        btFindNext=new JButton("查找下一个(F)");
        btCancel=new JButton("取消");
        pnButtons=new JPanel();
        pnButtons.setLayout(new GridLayout(2,1));
        pnButtons.add(btFindNext);
        pnButtons.add(btCancel);
        
        //设置助记符(使用Alt+F可快速定位)
        cbIsCaseSensitive.setMnemonic('C');
        cbCanWrap.setMnemonic('R');
        btFindNext.setMnemonic('F');

        //整合底部
        pnBottom=new JPanel();
        pnBottom.setLayout(new GridLayout(1,2));
        pnBottom.setAlignmentX(JDialog.LEFT_ALIGNMENT);
        pnBottom.add(pnSpecial);
        pnBottom.add(pnFindDirectionComponents);
        
        //整合左侧
        pnLeft=new JPanel();
        pnLeft.setLayout(new BoxLayout(pnLeft,BoxLayout.Y_AXIS));
        pnLeft.setAlignmentX(JDialog.LEFT_ALIGNMENT);
        pnLeft.add(pnKeywordComponents);
        pnLeft.add(pnBottom);
        
        //整合布局
        add(pnLeft);
        add(pnButtons);
    }

    //初始化监听器
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
        lisFindDialog=new FindDialogListener(dataField,prevKeyword);
        lisFindDialog.setTextField(tfKeywordField);
        lisFindDialog.setSpecialCheckBox(cbIsCaseSensitive,cbCanWrap);
        lisFindDialog.setDirectionRadioButton(rbForward,rbBackward);
        lisFindDialog.setFindNextButton(btFindNext);
        btFindNext.addActionListener(lisFindDialog);
    }


}