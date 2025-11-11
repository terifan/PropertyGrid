package org.terifan.propertygrid;

import java.awt.Color;
import java.awt.Font;


public class StylesLight extends Styles
{
	public StylesLight()
	{
		mForeground = Color.BLACK;
		mBackground = Color.WHITE;
		mFont = new Font("arial", Font.PLAIN, 12);
		mTitleFont = new Font("arial", Font.BOLD, 12);
		mHorizontalLineColor = new Color(0xE0EAF9);
		mVerticalLineColor = new Color(0xE0EAF9);
		mRowHeight = 24;
		mIndentWidth = 19;
		mColumnHeaderHeight = 20;
		mIconWidth = 20;
		mExpandWidth = 20;
		mIconTextSpacing = 4;
		mCellLeftMargin = 5;
		mCellRightMargin = 5;

		mNodeRolloverSelectedBackground = new Color(0xF0E5F3FB, true);
		mNodeRolloverSelectedBorder = new Color(0xF070C0E7, true);
		mNodeRolloverBackground = new Color(0x60E5F3FB, true);
		mNodeRolloverBorder = new Color(0x6070C0E7, true);
		mNodeSelectedBackground = new Color(0xA0E5F3FB, true);
		mNodeSelectedBorder = new Color(0xA070C0E7, true);
		c2 = new Color(0x80CBE8F6, true);
		c3 = new Color(0xFFFFFF);
		t2 = new Color(0x8026A0DA, true);

		setIndentBackgroundColor(0, new Color(0xF4F7FC));
	}
}
