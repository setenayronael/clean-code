package com.c.refactoring.menuexamples;

import java.util.Arrays;
import java.util.List;

public class MenuAccess {

    public void setAuthorizationsInEachMenus(
            List<MenuItem> menuItemsList, Role[] roles) {
        if(roles == null)
            return;

        menuItemsList.forEach(menuItem ->  {
            if(doesUserHaveTheRole(roles, menuItem.getReadAccessRole())) {
                setMenuItemAccess(menuItem, Constants.READ);
            }

            if(doesUserHaveTheRole(roles, menuItem.getWriteAccessRole())){
                setMenuItemAccess(menuItem, Constants.WRITE);
            }
        });
    }

    private boolean doesUserHaveTheRole(Role[] roles, String menuItem) {
        return Arrays.stream(roles).anyMatch(role -> role.getName().equals(menuItem));
    }

    private void setMenuItemAccess(MenuItem menuItem, String access) {
        menuItem.setAccess(access);
        menuItem.setVisible(true);
    }

}
