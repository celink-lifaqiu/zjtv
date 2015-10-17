package com.magic.core.compontents.menu;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Yin Jian Feng
 * Date: 13-4-10
 * Time: 下午11:40
 * To change this template use File | Settings | File Templates.
 */
public class MenuContainer {
    public static enum MENU_TYPE{HEAD_MENU, SUB_MENU}
    private MenuItem activeMenu = null;
    private List<MenuItem> menuItemList = new ArrayList<MenuItem>();

    public void addMenuItem(MenuItem menuItem){
        menuItemList.add(menuItem);
    }

    public MenuItem getMenuItem(String menuId){
        for (MenuItem menuItem : menuItemList) {
            if (menuId.equals(menuItem.getMenuId()))
                return menuItem;
        }
        return null;
    }

    public List<MenuItem> getMenuItemList(){
        Collections.sort(menuItemList, new Comparator<MenuItem>() {
            @Override
            public int compare(MenuItem o1, MenuItem o2) {
                return o1.getSerialNumber().compareTo(o2.getSerialNumber());
            }
        });
        return menuItemList;
    }

    public void activeMemu(String menuId){
//      recursive find MenuItem with given menuId
        List<MenuItem> menuItems = new ArrayList<MenuItem>();
        findMenuItem(menuId, getMenuItemList(), menuItems);
        MenuItem menuItem = menuItems.get(0);
//      active selected menu and its parents also
        if (StringUtils.isNotEmpty(menuId)){
            if (activeMenu != null) {
    //          inactive previous actived menu and its parents also
                inactiveMenuItem(activeMenu);
            }
            activeMenuItem(menuItem);
            activeMenu = menuItem;
        }
    }

    public void clear(){
        if (activeMenu != null) {
            inactiveMenuItem(activeMenu);
            activeMenu = null;
        }
    }

    private List<MenuItem> findMenuItem(String menuId,  List<MenuItem> list, List<MenuItem> result){
        try {
            for (MenuItem menuItem : list) {
                recursiveMenuItems(menuId, menuItem, result);
                if (result != null&&!result.isEmpty()) {
                    return result;
                }
             }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }
    private void recursiveMenuItems(String menuId, MenuItem menuItem, List<MenuItem> result) throws CloneNotSupportedException {
        if (menuId.equals(menuItem.getMenuId())) {
            result.add(menuItem);
            return;
        }
        for (MenuItem item : menuItem.getMenuItemList()) {
            recursiveMenuItems(menuId, item, result);
        }
    }

    private void activeMenuItem(MenuItem menuItem){
        menuItem.setActived(true);
        if (menuItem.getParent()!=null)
            activeMenuItem(menuItem.getParent());
        return;
    }
    private void inactiveMenuItem(MenuItem menuItem){
        menuItem.setActived(false);
        if (menuItem.getParent()!=null)
            inactiveMenuItem(menuItem.getParent());
        return;
    }

//    public void clean(){
//        menuItemList.clear();
//    }

    public MenuItem getActiveMenu() {
        return activeMenu;
    }

    public LinkedList<MenuItem> getBreadcrumb(){
        LinkedList<MenuItem> list = new LinkedList<MenuItem>();
        if (activeMenu != null) {
            recursiveActivedMenu(list, activeMenu);
        }
        return list;
    }
    private void recursiveActivedMenu(LinkedList<MenuItem> list, MenuItem menuItem){
        list.push(menuItem);
        if (menuItem.getParent()!=null)
            recursiveActivedMenu(list, menuItem.getParent());
        else
            return;
    }
}
