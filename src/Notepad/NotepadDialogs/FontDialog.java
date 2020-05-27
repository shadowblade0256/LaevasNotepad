package Notepad.NotepadDialogs;

import javax.swing.*;
import Notepad.NotepadDataField;

public class FontDialog extends JDialog
{
    NotepadDataField dataField;     //公有数据域

    public FontDialog(NotepadDataField dataField)
    {
        super();
        this.dataField=dataField;
    }
}