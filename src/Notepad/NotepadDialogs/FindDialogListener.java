package Notepad.NotepadDialogs;

import javax.swing.*;
import Notepad.NotepadDataField;
import java.awt.event.*;

//查找对话框监听器
public class FindDialogListener implements ActionListener
{
    //标明查找方向的常量
    static final boolean FORWARD = true,
                        BACKWARD = false;
    
    protected JTextField tfKeywordField;      //查找框
    protected JCheckBox cbIsCaseSensitive;    //区分大小写复选框
    protected JCheckBox cbCanWrap;            //循环复选框
    protected JRadioButton rbForward;         //向下查找
    protected JRadioButton rbBackward;        //向上查找
    protected JButton btFindNext;             //查找下一个按钮

    String prevKeyword;                     //上一次查找使用的关键词
    NotepadDataField dataField;             //公有数据域

    //初始化监听器并绑定数据域和上一次关键词
    FindDialogListener(NotepadDataField dataField,String prevKeyword)
    {
        this.dataField=dataField;
        this.prevKeyword=prevKeyword;
    }

    //绑定查找框
    void setTextField(JTextField tfKeywordField)
    {
        this.tfKeywordField=tfKeywordField;
    }

    //绑定查找选项复选框
    void setSpecialCheckBox(JCheckBox cbIsCaseSensitive,JCheckBox cbCanWrap)
    {
        this.cbIsCaseSensitive=cbIsCaseSensitive;
        this.cbCanWrap=cbCanWrap;
    }

    //绑定搜索方向复选框
    void setDirectionRadioButton(JRadioButton rbForward,JRadioButton rbBackward)
    {
        this.rbForward=rbForward;
        this.rbBackward=rbBackward;
    }

    //绑定查找按钮
    void setFindNextButton(JButton btFindNext)
    {
        this.btFindNext=btFindNext;
    }

    //单击查找下一个按钮时，按要求查找
    public void actionPerformed(ActionEvent e)
    {
        int resultCaret=-2;    //标识结果位置
        int currentCaret=dataField.getDocumentArea().getCaretPosition();    //标识当前光标位置
        if (rbForward.isSelected())     //如果是向下查找
        {
            if (cbIsCaseSensitive.isSelected())     //如果区分大小写
                resultCaret=dataField.getDocumentArea().getText().indexOf(tfKeywordField.getText(),currentCaret);
            else                                    //不区分大小写
                resultCaret=dataField.getDocumentArea().getText().toLowerCase()
                            .indexOf(tfKeywordField.getText().toLowerCase(),currentCaret);
            //如果允许循环且当前未找到，从头再找一次
            if (resultCaret==-1 && cbCanWrap.isSelected())
            {
                if (cbIsCaseSensitive.isSelected())     //如果区分大小写
                    resultCaret=dataField.getDocumentArea().getText().indexOf(tfKeywordField.getText());
                else                                    //不区分大小写
                    resultCaret=dataField.getDocumentArea().getText().toLowerCase()
                            .indexOf(tfKeywordField.getText().toLowerCase());
            }
        }
        else if (rbBackward.isSelected())    //向上查找
        {
            if (cbIsCaseSensitive.isSelected())     //如果区分大小写
                resultCaret=dataField.getDocumentArea().getText().lastIndexOf(tfKeywordField.getText(),currentCaret);
            else                                    //不区分大小写
                resultCaret=dataField.getDocumentArea().getText().toLowerCase()
                            .lastIndexOf(tfKeywordField.getText().toLowerCase(),currentCaret);
            //如果允许循环且当前未找到，从头再找一次
            if (resultCaret==-1 && cbCanWrap.isSelected())
            {
                if (cbIsCaseSensitive.isSelected())     //如果区分大小写
                    resultCaret=dataField.getDocumentArea().getText().lastIndexOf(tfKeywordField.getText());
                else                                    //不区分大小写
                    resultCaret=dataField.getDocumentArea().getText().toLowerCase()
                            .lastIndexOf(tfKeywordField.getText().toLowerCase());
            }
        }

        //找到后高亮显示并记录关键词，未找到则弹出对话框提示用户未找到
        if (resultCaret!=-1)
        {
            dataField.getDocumentArea().setCaretPosition(resultCaret);
            dataField.getDocumentArea().setSelectionStart(resultCaret);
            dataField.getDocumentArea().setSelectionEnd(resultCaret+tfKeywordField.getText().length());
            prevKeyword=dataField.getDocumentArea().getText();
        }
        else
        {
            JOptionPane.showMessageDialog(null,"找不到\""+tfKeywordField.getText()+"\"","提示",JOptionPane.WARNING_MESSAGE);
        }
        prevKeyword=tfKeywordField.getText();
    }
}