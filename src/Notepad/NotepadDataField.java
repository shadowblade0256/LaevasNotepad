package Notepad;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

//公有数据域，存储文件、文本域和文本域字体
public class NotepadDataField 
{
    UndoableTextArea taDocumentArea;        //可撤销文本域
    File currentFile;                       //当前文件
    Font textFont;                          //文本域字体
    Boolean saved;                          //当前文件是否已保存

    public NotepadDataField(UndoableTextArea taDocumentArea,File currentFile)
    {
        this.taDocumentArea=taDocumentArea;
        this.currentFile=currentFile;
        this.textFont=new Font("SimSun",Font.PLAIN,18);     //默认字体Courier New，普通，18号
        taDocumentArea.setFont(textFont);
        this.saved=taDocumentArea.saved;                    //将文本域和数据域的已保存状态链接到一起
    }

    public NotepadDataField(UndoableTextArea taDocumentArea,File currentFile,Font defaultFont)
    {
        this(taDocumentArea,currentFile);
        this.textFont=defaultFont;
        taDocumentArea.setFont(textFont);
    }

    //更新当前文件状态
    public void setFile(File newFile)
    {
        currentFile=newFile;
        if (newFile.getName()!="Untitled") 
            saved=true;                         //不是未命名("Untitled")说明是外部打开的文件，应默认已保存
        else saved=false;                       //"Untitled"是新建文本文件，默认未保存
    }

    //获取文本域
    public UndoableTextArea getDocumentArea()
    {
        return taDocumentArea;
    }

    //获取文件信息
    public File getFile()
    {
        return currentFile;
    }

    //获取文本域字体
    public Font getTextFont()
    {
        return textFont;
    }

    //返回currentFile是否被保存过
    public boolean isSaved()
    {
        return saved;
    }

    //设置currentFile是否已被保存
    public void setSaved(boolean saved)
    {
        this.saved=saved;
    }
    
    //将currentFile指向的文件内容载入到文本域内
    //本版本(v0.5)中暂只支持UTF-8编码
    public void fileLoad()
    {
    	FileReader fileRead = null;	//文件字符流
		try 
		{
			fileRead = new FileReader(currentFile);
		} 
		catch (FileNotFoundException e) 
		{
			JOptionPane.showMessageDialog(null,"文件未找到","",JOptionPane.ERROR_MESSAGE);
		}
    	BufferedReader bufRead=new BufferedReader(fileRead);
    	String fileContent="";		//储存文件内容的字符串
        String lineBuffer="";       //读入内容的行缓冲区

        //从开头按行读取文件，一直到末尾
    	try
    	{
    	    lineBuffer=bufRead.readLine();
    	    while (lineBuffer!=null)
            {
                fileContent+=lineBuffer+"\n";
                lineBuffer=bufRead.readLine();
            }
    	}
    	catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
    	taDocumentArea.setText(fileContent);
    	try
        {
            bufRead.close();
            fileRead.close();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

    //将当前文本域的内容存储到currentFile所指示的文件中
    //本版本(v0.5)默认以UTF-8格式存储
    public void fileSave()
    {
        FileWriter fileWrite=null;
        try
        {
            fileWrite=new FileWriter(currentFile);
        }
        catch (IOException ioe)
        {
            JOptionPane.showMessageDialog(null,"初始化文件写入流失败","",JOptionPane.ERROR_MESSAGE);
        }
        BufferedWriter bufWrite=new BufferedWriter(fileWrite);
        Scanner textScan=new Scanner(new String(taDocumentArea.getText()));     //文本域读取器
        String lineBuffer="";                                       //文本域的行缓冲区

        //从开头按行写入文件，一直到末尾
        try
        {
            lineBuffer=textScan.nextLine();
            while (lineBuffer!=null)
            {
                bufWrite.write(lineBuffer);
                bufWrite.newLine();
                lineBuffer=textScan.nextLine();
            }
        }
        catch (NoSuchElementException nsee)
        {
            //出现此异常说明写入完毕，应该结束写入，此异常不需处理
        }
        catch (IOException ioe)
        {
            JOptionPane.showMessageDialog(null,"未知的I/O错误","",JOptionPane.ERROR_MESSAGE);
        }

        saved=true;    //完毕后，将文件标记为已保存状态

        try
        {
            textScan.close();
            bufWrite.close();
            fileWrite.close();
        }
        catch (IOException ioe)
        {
            JOptionPane.showMessageDialog(null,"关闭I/O流失败","",JOptionPane.ERROR_MESSAGE);
        }
    }
}