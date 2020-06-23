package com.gradeup.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * 
 * @author dgupta54
 *
 */
public class HomePage extends BasePage{

	By list_MenuItems = By.xpath(".//header//div[contains(@class,'header-menu')]//span[contains(@class,'anchor-label')]");
	By list_SubmenuItems = By.xpath(".//ancestor::a//following-sibling::div//li");

	public boolean selectMenu(String menu, String subMenu) throws InterruptedException {
		waitForPageToLoad();
		waitTimer(5);
		
		List<WebElement> menuList = driver.findElements(list_MenuItems);
		for(WebElement menuElement: menuList) {
			if(menuElement.getText().toUpperCase().equals(menu.toUpperCase())){
				menuElement.click();
				waitForPageToLoad();
				List<WebElement> subMenuList = menuElement.findElements(list_SubmenuItems);
				for(WebElement subMenuElement: subMenuList) {
					if(subMenuElement.getText().toUpperCase().equals(subMenu.toUpperCase())){
						subMenuElement.click();
						return true;
					}
				}
			}

		}
		return false;
	}
	
	
}