package Notepad.NotepadMenuListeners;

import javax.swing.*;
import Notepad.NotepadDataField;
import Notepad.NotepadDialogs.*;

import java.awt.event.*;
import java.io.*;
import java.util.Calendar;

//编辑菜单项监听器
public class EditMenuListener implements ActionListener
{
    //公有数据域
    NotepadDataField dataField;

    private JMenuItem mnUndo;       //撤销
    private JMenuItem mnRedo;       //重做

    private JMenuItem mnCut;        //剪切
    private JMenuItem mnCopy;       //复制
    private JMenuItem mnPaste;      //粘贴
    private JMenuItem mnDelete;     //删除

    private JMenuItem mnBingSearch; //使用Bing搜索
    private JMenuItem mnFind;       //查找
    private JMenuItem mnFindNext;   //查找下一个
    private JMenuItem mnReplace;    //替换
    private JMenuItem mnGoto;       //转到

    private JMenuItem mnSelectAll;  //全选
    private JMenuItem mnDateTime;   //日期/时间

    String prevKeyword;                     //上一次使用查找命令时的关键词

    //初始化编辑菜单项监听器并绑定数据域
    public EditMenuListener(NotepadDataField dataField)
    {
        this.dataField=dataField;
        prevKeyword="";
    }

    //绑定剪切、复制、粘贴、删除
    public void setClipboardCommands(JMenuItem mnCut,JMenuItem mnCopy,
                                    JMenuItem mnPaste,JMenuItem mnDelete)
    {
        this.mnCut=mnCut;
        this.mnCopy=mnCopy;
        this.mnPaste=mnPaste;
        this.mnDelete=mnDelete;
    }

    //绑定撤销、重做
    public void setUndoAndRedo(JMenuItem mnUndo,JMenuItem mnRedo)
    {
        this.mnUndo=mnUndo;
        this.mnRedo=mnRedo;
    }

    //绑定Bing搜索、查找、查找下一个、替换、转到
    public void setLocationItems(JMenuItem mnBingSearch,JMenuItem mnFind,JMenuItem mnFindNext,
                                JMenuItem mnReplace,JMenuItem mnGoto)
    {
        this.mnBingSearch=mnBingSearch;
        this.mnFind=mnFind;
        this.mnFindNext=mnFindNext;
        this.mnReplace=mnReplace;
        this.mnGoto=mnGoto;
    }

    //绑定全选、日期/时间
    public void setSpecialItems(JMenuItem mnSelectAll,JMenuItem mnDateTime)
    {
        this.mnSelectAll=mnSelectAll;
        this.mnDateTime=mnDateTime;
    }

    //处理菜单项行为
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource()==mnUndo)                  //撤销
            dataField.getDocumentArea().undo();
        else if (e.getSource()==mnRedo)             //重做
            dataField.getDocumentArea().redo();
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
        else if (e.getSource()==mnFind)         //查找
        {
            new FindDialog(dataField,prevKeyword);
        }
        else if (e.getSource()==mnFindNext)     //查找下一个
        {
            findNext();
        }
        else if (e.getSource()==mnReplace)      //替换
        {
            new ReplaceDialog(dataField,prevKeyword);
        }
        else if (e.getSource()==mnGoto)         //转到
        {
            new GotoDialog(dataField);
        }
        else if (e.getSource()==mnSelectAll)    //全选
        {
            dataField.getDocumentArea().setSelectionStart(0);
            dataField.getDocumentArea().setSelectionEnd(dataField.getDocumentArea().getText().length());
        }
        else if (e.getSource()==mnDateTime)     //日期/时间
        {
            Calendar currentDateTime=Calendar.getInstance();
            String dateTime=currentDateTime.get(Calendar.YEAR)+"/"
                            +(currentDateTime.get(Calendar.MONTH)+1)+"/"
                            +currentDateTime.get(Calendar.DAY_OF_MONTH)+
                            " "+currentDateTime.get(Calendar.HOUR_OF_DAY)+":"
                            +(currentDateTime.get(Calendar.MINUTE)<10?"0":"")
                            +currentDateTime.get(Calendar.MINUTE)+":"
                            +(currentDateTime.get(Calendar.SECOND)<10?"0":"")
                            +currentDateTime.get(Calendar.SECOND);
            dataField.getDocumentArea().setText(dataField.getDocumentArea().getText()+dateTime);
        }

    }

    //实现"查找下一项"功能，默认支持循环、区分大小写、向下查找
    public void findNext()
    {
        int resultCaret=-2;    //标识结果位置
        int currentCaret=dataField.getDocumentArea().getCaretPosition();    //标识当前光标位置
        resultCaret=dataField.getDocumentArea().getText().indexOf(prevKeyword,currentCaret);
        if (resultCaret==-1)        //允许循环且未找到时，从头再找一次
            resultCaret=dataField.getDocumentArea().getText().indexOf(prevKeyword);
        
        //找到后高亮显示，未找到则弹出对话框提示用户未找到
        if (resultCaret!=-1)
        {
            dataField.getDocumentArea().setCaretPosition(resultCaret);
            dataField.getDocumentArea().setSelectionStart(resultCaret);
            dataField.getDocumentArea().setSelectionEnd(resultCaret+prevKeyword.length());
        }
        else
        {
            JOptionPane.showMessageDialog(null,"找不到\""+prevKeyword+"\"","提示",JOptionPane.WARNING_MESSAGE);
        }
    }
}