package com.magic.core.compontents.menu;

import org.springframework.security.access.ConfigAttribute;

import java.io.Serializable;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Yin Jian Feng
 * Date: 13-4-10
 * Time: 下午11:40
 * To change this template use File | Settings | File Templates.
 */
public class MenuItem implements Cloneable, Serializable{
    private Integer index;
    private String id;
    private MenuContainer.MENU_TYPE menuType;
    private String menuId;
    private String label;
    private String icon;
    private String uri;
    private Collection<ConfigAttribute> permissions;
    private Integer serialNumber;
    private Boolean actived = false;
    private Boolean visible = true;
    private MenuItem parent = null;
    private List<MenuItem> menuItemList = new ArrayList<MenuItem>();

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MenuContainer.MENU_TYPE getMenuType() {
        return menuType;
    }

    public void setMenuType(MenuContainer.MENU_TYPE menuType) {
        this.menuType = menuType;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Collection<ConfigAttribute> getPermissions() {
        return permissions;
    }

    public void setPermissions(Collection<ConfigAttribute> permissions) {
        this.permissions = permissions;
    }

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public MenuItem getParent() {
        return parent;
    }

    public void setParent(MenuItem parent) {
        this.parent = parent;
    }

    public void addMenuItem(MenuItem menuItem){
        menuItemList.add(menuItem);
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
    public Boolean getActived() {
        return actived;
    }

    public void setActived(Boolean actived) {
        this.actived = actived;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    @Override
    protected MenuItem clone() throws CloneNotSupportedException {
        return (MenuItem) super.clone();    //To change body of overridden methods use File | Settings | File Templates.
    }
}
