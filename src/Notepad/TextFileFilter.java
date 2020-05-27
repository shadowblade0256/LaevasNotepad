package Notepad;

import javax.swing.filechooser.FileFilter;
import java.io.*;

//筛选文本文件和目录的文件过滤器，用于打开/保存对话框
public class TextFileFilter extends FileFilter
{
    public boolean accept(File f)
    {
        if (f.isDirectory()) return true;
        String fileName=f.getName();
        if (fileName.lastIndexOf('.')==-1)
            return false;
        return fileName.contains(".txt");
    }

    public String getDescription() 
    {
        return null;
    }
}