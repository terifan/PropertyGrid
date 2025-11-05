package org.terifan.propertygrid;

import java.awt.Color;


public class StylesDark extends Styles
{
	public StylesDark()
	{
		mBackground = Color.BLACK;
		mForeground = new Color(211, 211, 211);
		mTitleBackground = Color.BLACK;
		mTitleForeground = new Color(255, 255, 255);
		mHorizontalLineColor = new Color(0x202417);
		mVerticalLineColor = new Color(0x202417);

		mNodeRolloverSelectedBackground = new Color(0x40D1E8FF, true);
		mNodeRolloverBackground = new Color(0x4025F3FB, true);
		c2 = new Color(0x80CBE8F6, true);
		c3 = new Color(0xFFFFFF);
		mNodeSelectedBackground = new Color(0x4067F7F7, true);
		mNodeRolloverSelectedBorder = new Color(0x4036A7E8, true);
		mNodeRolloverBorder = new Color(0x4030C0E7, true);
		t2 = new Color(0x4016A0DA, true);
		mNodeSelectedBorder = new Color(0x408EDEDE, true);

		setIndentBackgroundColor(0, new Color(0x0C0803));
	}
}
