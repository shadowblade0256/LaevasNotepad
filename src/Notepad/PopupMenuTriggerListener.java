package Notepad;

import java.awt.event.*;

//弹出式菜单触发操作监听器
public class PopupMenuTriggerListener extends MouseAdapter
{
    NotepadPopupMenu popMenu;
    
    public PopupMenuTriggerListener(NotepadPopupMenu popMenu)
    {
        this.popMenu=popMenu;
    }
    public void mousePressed(MouseEvent e)
    {
        mouseClicked(e);
    }
    public void mouseReleased(MouseEvent e)
    {
        mouseClicked(e);
    }
    public void mouseClicked(MouseEvent e)
    {
        //如果可以激发弹出式菜单，就在当前光标位置弹出菜单
        if (e.isPopupTrigger())
            popMenu.show(e.getComponent(),e.getX(),e.getY());
    }
}