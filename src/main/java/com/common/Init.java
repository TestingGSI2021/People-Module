package com.common;

import com.webdriverutilities.Click;
import com.webdriverutilities.Drag_And_Drop;
import com.webdriverutilities.Navigate;
import com.webdriverutilities.Select_Dropdown;
import com.webdriverutilities.Send_Keys;
import com.webdriverutilities.Set_Webdriver;
import com.webdriverutilities.Text;
import com.webdriverutilities.Visible;
import com.webdriverutilities.Webelement;




public interface Init {

	 Click click = new Click();
	 Drag_And_Drop drag_drop= new Drag_And_Drop();
	 Navigate navigate = new Navigate();
	 Send_Keys sendkeys = new Send_Keys();
	 Text text = new Text();
	 Visible visible= new Visible();
	 Select_Dropdown select = new Select_Dropdown();
     Drag_And_Drop drop = new Drag_And_Drop();
     Webelement webelement= new Webelement();
	 Set_Webdriver set_Webdriver = new Set_Webdriver();


	
	
}
