package Notepad.NotepadDialogs;

import javax.swing.*;
import java.awt.event.*;
import Notepad.NotepadDataField;

//替换对话框监听器
public class ReplaceDialogListener extends FindDialogListener
{
    JTextField tfReplaceWith;       //替换框
    JButton btReplaceFirst;         //替换按钮
    JButton btReplaceAll;           //全部替换按钮

    ReplaceDialogListener(NotepadDataField dataField,String prevKeyword)
    {
        super(dataField,prevKeyword);
    }

    //绑定替换框
    void setReplaceWithField(JTextField tfReplaceWith)
    {
        this.tfReplaceWith=tfReplaceWith;
    }
    //绑定替换按钮
    void setReplaceFirstButton(JButton btReplaceFirst)
    {
        this.btReplaceFirst=btReplaceFirst;
    }
    //绑定全部替换按钮
    void setReplaceAllButton(JButton btReplaceAll)
    {
        this.btReplaceAll=btReplaceAll;
    }

    public void actionPerformed(ActionEvent e)
    {
        super.actionPerformed(e);
        if (e.getSource()==btReplaceFirst)
            replaceFirst();
        else if (e.getSource()==btReplaceAll)
            replaceAll();
    }

    //实现替换按钮功能
    public void replaceFirst()
    {
        int resultCaret=-2;    //标识结果位置
        int currentCaret=dataField.getDocumentArea().getCaretPosition();    //标识当前光标位置
        if (cbIsCaseSensitive.isSelected())     //如果区分大小写
        {
            resultCaret=dataField.getDocumentArea().getText().indexOf(tfKeywordField.getText());
        }
        else
        {                                    //不区分大小写
            resultCaret=dataField.getDocumentArea().getText().toLowerCase()
                        .indexOf(tfKeywordField.getText().toLowerCase());
        }
        //如果允许循环且当前未找到，从头再找一次
        if (resultCaret==-1 && cbCanWrap.isSelected())
        {
            if (cbIsCaseSensitive.isSelected())     //如果区分大小写
                resultCaret=dataField.getDocumentArea().getText().indexOf(tfKeywordField.getText());
            else                                    //不区分大小写
                resultCaret=dataField.getDocumentArea().getText().toLowerCase()
                        .indexOf(tfKeywordField.getText().toLowerCase());
        }
        //找到后高亮显示、执行替换并记录关键词，未找到则弹出对话框提示用户未找到
        if (resultCaret!=-1)
        {
            dataField.getDocumentArea().setText(dataField.getDocumentArea().getText().
                replaceFirst(tfKeywordField.getText(),tfReplaceWith.getText()));
                
            dataField.getDocumentArea().setCaretPosition(resultCaret);
            dataField.getDocumentArea().setSelectionStart(resultCaret);
            dataField.getDocumentArea().setSelectionEnd(resultCaret+tfReplaceWith.getText().length());
        }
        /*else
        {
            JOptionPane.showMessageDialog(null,"找不到\""+tfKeywordField.getText()+"\"","提示",JOptionPane.WARNING_MESSAGE);
        }*/
        prevKeyword=tfKeywordField.getText();
    }

    //实现全部替换按钮功能
    void replaceAll()
    {
        int resultCaret=-1;    //标识结果位置
        int currentCaret=dataField.getDocumentArea().getCaretPosition();    //标识当前光标位置
        if (cbIsCaseSensitive.isSelected())     //如果区分大小写
        {
            resultCaret=dataField.getDocumentArea().getText().indexOf(tfKeywordField.getText(),currentCaret);
        }
        else
        {                                    //不区分大小写
            resultCaret=dataField.getDocumentArea().getText().toLowerCase()
                        .indexOf(tfKeywordField.getText().toLowerCase(),currentCaret);
        }
        //如果允许循环且当前未找到，从头再找一次
        if (resultCaret==-1 && cbCanWrap.isSelected())
        {
            if (cbIsCaseSensitive.isSelected())     //如果区分大小写
                resultCaret=dataField.getDocumentArea().getText().indexOf(tfKeywordField.getText());
            else                                    //不区分大小写
                resultCaret=dataField.getDocumentArea().getText().toLowerCase()
                        .indexOf(tfKeywordField.getText().toLowerCase());
        }
        //找到后执行替换并记录关键词，未找到则弹出对话框提示用户未找到
        if (resultCaret!=-1)
        {
            String replaceResult=dataField.getDocumentArea().getText().
                    replaceAll(tfKeywordField.getText(),tfReplaceWith.getText());
            dataField.getDocumentArea().setText(replaceResult);
        }
        /*else
        {
            JOptionPane.showMessageDialog(null,"找不到\""+tfKeywordField.getText()+"\"","提示",JOptionPane.WARNING_MESSAGE);
        }*/
        prevKeyword=tfKeywordField.getText();
    }
}