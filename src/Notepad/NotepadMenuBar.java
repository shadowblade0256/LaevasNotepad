package Notepad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Notepad.NotepadMenuListeners.*;

//窗体菜单栏类
public class NotepadMenuBar extends JMenuBar
{
    //菜单栏项
    JMenu mnFile;       //文件菜单
    JMenu mnEdit;       //编辑菜单
    JMenu mnFormat;     //格式菜单
    JMenu mnView;       //查看菜单
    JMenu mnHelp;       //帮助菜单

    //文件菜单
    JMenuItem mnFileNew;            //新建
    JMenuItem mnFileOpen;           //打开
    JMenuItem mnFileSave;           //保存
    JMenuItem mnFileSaveAs;         //另存为
    JMenuItem mnFileExit;           //退出

    //编辑菜单
    JMenuItem mnEditUndo;           //撤消
    JMenuItem mnEditRedo;           //重做
    JMenuItem mnEditCut;            //剪切
    JMenuItem mnEditCopy;           //复制
    JMenuItem mnEditPaste;          //粘贴
    JMenuItem mnEditDelete;         //删除
    JMenuItem mnEditBingSearch;     //使用Bing搜索
    JMenuItem mnEditFind;           //查找
    JMenuItem mnEditFindNext;       //查找下一个
    JMenuItem mnEditReplace;        //替换
    JMenuItem mnEditGoto;         //转到
    JMenuItem mnEditSelectAll;      //全选
    JMenuItem mnEditDateTime;       //时间/日期

    //格式菜单
    JCheckBoxMenuItem mnFormatAutoWrap;     //自动换行

    //查看菜单
    JMenu mnViewZoom;               //缩放
    JMenuItem mnViewZoomIn;         //缩放->放大
    JMenuItem mnViewZoomOut;        //缩放->缩小
    JMenuItem mnViewZoomDefault;    //缩放->恢复默认比例

    //帮助菜单
    JMenuItem mnHelpAbout;          //关于

    //公有数据域
    NotepadDataField dataField;

    FileMenuListener lisFileMenu;
    EditMenuListener lisEditMenu;
    FormatMenuListener lisFormatMenu;
    ViewMenuListener lisViewMenu;
    HelpMenuListener lisHelpMenu;

    NotepadMenuBar(NotepadDataField dataField)
    {
        super();

        //载入数据域，初始化菜单项
        this.dataField=dataField;
        addFileMenu();
        addEditMenu();
        addFormatMenu();
        addViewMenu();
        addHelpMenu();

        //设置各菜单的监听器
        setFileMenuListener();
        setEditMenuListener();
        setFormatMenuListener();
        setViewMenuListener();
        setHelpMenuListener();
    }
    
    //初始化文件菜单
    void addFileMenu()
    {
        mnFile=new JMenu("文件(F)");
        mnFileNew=new JMenuItem("新建(N)",'N');
        mnFileOpen=new JMenuItem("打开(O)",'O');
        mnFileSave=new JMenuItem("保存(S)...",'S');
        mnFileSaveAs=new JMenuItem("另存为(A)...",'A');
        mnFileExit=new JMenuItem("退出(X)",'X');

        mnFile.setMnemonic('F');
        mnFileNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,KeyEvent.CTRL_DOWN_MASK));
        mnFileOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,KeyEvent.CTRL_DOWN_MASK));
        mnFileSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,KeyEvent.CTRL_DOWN_MASK));
        
        mnFile.add(mnFileNew);
        mnFile.add(mnFileOpen);
        mnFile.add(mnFileSave);
        mnFile.add(mnFileSaveAs);
        mnFile.addSeparator();
        mnFile.add(mnFileExit);

        add(mnFile);
    }

    //初始化编辑菜单
    void addEditMenu()
    {
        mnEdit=new JMenu("编辑(E)");
        mnEditUndo=new JMenuItem("撤消(U)",'U');
        mnEditRedo=new JMenuItem("重做(Y)",'Y');
        mnEditCut=new JMenuItem("剪切(T)",'T');
        mnEditCopy=new JMenuItem("复制(C)",'C');
        mnEditPaste=new JMenuItem("粘贴(P)",'P');
        mnEditDelete=new JMenuItem("删除(L)",'L');
        mnEditBingSearch=new JMenuItem("使用Bing搜索...");
        mnEditFind=new JMenuItem("查找(F)...",'F');
        mnEditFindNext=new JMenuItem("查找下一个(N)",'N');
        mnEditReplace=new JMenuItem("替换(R)...",'R');
        mnEditGoto=new JMenuItem("转到(G)...",'G');
        mnEditSelectAll=new JMenuItem("全选(A)",'A');
        mnEditDateTime=new JMenuItem("时间/日期(D)",'D');

        mnEdit.setMnemonic('E');
        mnEditUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,KeyEvent.CTRL_DOWN_MASK));
        mnEditRedo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y,KeyEvent.CTRL_DOWN_MASK));
        mnEditCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,KeyEvent.CTRL_DOWN_MASK));
        mnEditCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,KeyEvent.CTRL_DOWN_MASK));
        mnEditPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,KeyEvent.CTRL_DOWN_MASK));
        mnEditBingSearch.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,KeyEvent.CTRL_DOWN_MASK));
        mnEditFind.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,KeyEvent.CTRL_DOWN_MASK));
        mnEditFindNext.setAccelerator(KeyStroke.getKeyStroke("F3"));
        mnEditReplace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,KeyEvent.CTRL_DOWN_MASK));
        mnEditGoto.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,KeyEvent.CTRL_DOWN_MASK));
        mnEditSelectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,KeyEvent.CTRL_DOWN_MASK));
        mnEditDateTime.setAccelerator(KeyStroke.getKeyStroke("F5"));
        
        mnEdit.add(mnEditUndo);
        mnEdit.add(mnEditRedo);
        mnEdit.addSeparator();
        mnEdit.add(mnEditCut);
        mnEdit.add(mnEditCopy);
        mnEdit.add(mnEditPaste);
        mnEdit.add(mnEditDelete);
        mnEdit.addSeparator();
        mnEdit.add(mnEditBingSearch);
        mnEdit.add(mnEditFind);
        mnEdit.add(mnEditFindNext);
        mnEdit.add(mnEditReplace);
        mnEdit.add(mnEditGoto);
        mnEdit.addSeparator();
        mnEdit.add(mnEditSelectAll);
        mnEdit.add(mnEditDateTime);
        
        add(mnEdit);
    }

    //初始化格式菜单
    void addFormatMenu()
    {
        mnFormat=new JMenu("格式(O)");
        mnFormatAutoWrap=new JCheckBoxMenuItem("自动换行(W)");

        mnFormat.setMnemonic('O');
        mnFormatAutoWrap.setMnemonic('W');

        mnFormat.add(mnFormatAutoWrap);

        add(mnFormat);
    }

    //初始化查看菜单
    void addViewMenu()
    {
        mnView=new JMenu("查看(V)");
        mnViewZoom=new JMenu("缩放(Z)");
        mnViewZoomIn=new JMenuItem("放大");
        mnViewZoomOut=new JMenuItem("缩小");
        mnViewZoomDefault=new JMenuItem("恢复默认缩放");

        mnView.setMnemonic('V');
        mnViewZoom.setMnemonic('Z');
        mnViewZoomIn.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS,KeyEvent.CTRL_DOWN_MASK));
        mnViewZoomOut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS,KeyEvent.CTRL_DOWN_MASK));
        mnViewZoomDefault.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_0,KeyEvent.CTRL_DOWN_MASK));

        mnViewZoom.add(mnViewZoomIn);
        mnViewZoom.add(mnViewZoomOut);
        mnViewZoom.add(mnViewZoomDefault);
        mnView.add(mnViewZoom);

        add(mnView);
    }

    //初始化帮助菜单
    void addHelpMenu()
    {
        mnHelp=new JMenu("帮助(H)");
        mnHelpAbout=new JMenuItem("关于(A)...",'A');

        mnHelp.setMnemonic('H');

        mnHelp.add(mnHelpAbout);
        
        add(mnHelp);
    }

    //设置帮助菜单监听器
    void setHelpMenuListener()
    {
        lisHelpMenu=new HelpMenuListener();
        lisHelpMenu.bindHelpAbout(mnHelpAbout);
        mnHelpAbout.addActionListener(lisHelpMenu);
    }

    //设置编辑菜单监听器
    void setEditMenuListener()
    {
        lisEditMenu=new EditMenuListener(dataField);
        lisEditMenu.setUndoAndRedo(mnEditUndo, mnEditRedo);
        lisEditMenu.setClipboardCommands(mnEditCut, mnEditCopy, mnEditPaste, mnEditDelete);
        lisEditMenu.setLocationItems(mnEditBingSearch,mnEditFind,mnEditFindNext,mnEditReplace,mnEditGoto);
        lisEditMenu.setSpecialItems(mnEditSelectAll,mnEditDateTime);
        mnEditUndo.addActionListener(lisEditMenu);
        mnEditRedo.addActionListener(lisEditMenu);
        mnEditCut.addActionListener(lisEditMenu);
        mnEditCopy.addActionListener(lisEditMenu);
        mnEditPaste.addActionListener(lisEditMenu);
        mnEditDelete.addActionListener(lisEditMenu);
        mnEditBingSearch.addActionListener(lisEditMenu);
        mnEditFind.addActionListener(lisEditMenu);
        mnEditFindNext.addActionListener(lisEditMenu);
        mnEditReplace.addActionListener(lisEditMenu);
        mnEditGoto.addActionListener(lisEditMenu);
        mnEditSelectAll.addActionListener(lisEditMenu);
        mnEditDateTime.addActionListener(lisEditMenu);
    }

    //设置文件菜单监听器
    void setFileMenuListener()
    {
        lisFileMenu=new FileMenuListener(dataField);
        lisFileMenu.setMenuItems(mnFileNew,mnFileOpen,mnFileSave,mnFileSaveAs,mnFileExit);
        mnFileNew.addActionListener(lisFileMenu);
        mnFileOpen.addActionListener(lisFileMenu);
        mnFileSave.addActionListener(lisFileMenu);
        mnFileSaveAs.addActionListener(lisFileMenu);
        mnFileExit.addActionListener(lisFileMenu);
    }

    //设置格式菜单监听器
    void setFormatMenuListener()
    {
        lisFormatMenu=new FormatMenuListener(dataField);
        lisFormatMenu.setMenuItems(mnFormatAutoWrap);
        mnFormatAutoWrap.addActionListener(lisFormatMenu);
    }

    //设置查看菜单监听器
    void setViewMenuListener()
    {
        lisViewMenu=new ViewMenuListener(dataField);
        lisViewMenu.setMenuItem(mnViewZoomIn,mnViewZoomOut,mnViewZoomDefault);
        mnViewZoomIn.addActionListener(lisViewMenu);
        mnViewZoomOut.addActionListener(lisViewMenu);
        mnViewZoomDefault.addActionListener(lisViewMenu);
    }
}
