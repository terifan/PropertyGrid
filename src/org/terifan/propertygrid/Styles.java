package org.terifan.propertygrid;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;


public class Styles
{
	protected HashMap<Integer, Color> mIndentBackgroundColor;
	protected HashMap<Integer, Color> mIndentLineColor;
	protected Color mVerticalLineColor;
	protected Color mHorizontalLineColor;
	protected BufferedImage mExpandIcon;
	protected BufferedImage mCollapseIcon;
	protected int mIconStyle;
	protected Color mTitleForeground;
	protected Color mTitleBackground;
	protected Color mForeground;
	protected Color mBackground;
	protected Font mTitleFont;
	protected Font mFont;
	protected int mIndentWidth;
	protected int mRowHeight;
	protected int mColumnHeaderHeight;
	protected int mIconWidth;
	protected int mIconTextSpacing;
	protected int mExpandWidth;
	protected int mCellRightMargin;
	protected int mCellLeftMargin;

	protected Color mNodeRolloverSelectedBackground;
	protected Color mNodeRolloverBackground;
	protected Color c2;
	protected Color c3;
	protected Color mNodeSelectedBackground;
	protected Color mNodeRolloverSelectedBorder;
	protected Color mNodeRolloverBorder;
	protected Color t2;
	protected Color mNodeSelectedBorder;


	public Styles()
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


	public Color getIndentBackgroundColor(int aIndex)
	{
		return mIndentBackgroundColor == null ? null : mIndentBackgroundColor.get(aIndex);
	}


	public Styles setIndentBackgroundColor(int aIndex, Color aColor)
	{
		if (mIndentBackgroundColor == null)
		{
			mIndentBackgroundColor = new HashMap<>();
		}
		mIndentBackgroundColor.put(aIndex, aColor);
		return this;
	}


	public Color getIndentLineColor(int aIndex)
	{
		return mIndentLineColor == null ? null : mIndentLineColor.get(aIndex);
	}


	public Styles setIndentLineColor(int aIndex, Color aColor)
	{
		if (mIndentLineColor == null)
		{
			mIndentLineColor = new HashMap<>();
		}
		mIndentLineColor.put(aIndex, aColor);
		return this;
	}


	public Color getVerticalLineColor()
	{
		return mVerticalLineColor;
	}


	public Styles setVerticalLineColor(Color aVerticalLineColor)
	{
		mVerticalLineColor = aVerticalLineColor;
		return this;
	}


	public Color getHorizontalLineColor()
	{
		return mHorizontalLineColor;
	}


	public Styles setIconStyle(int aIconStyle)
	{
		mIconStyle = aIconStyle;
		mExpandIcon = null;
		return this;
	}


	protected BufferedImage getIcon(boolean aExpand)
	{
		if (mExpandIcon == null)
		{
			try
			{
				BufferedImage icons = ImageIO.read(PropertyNode.class.getResource("icons.png"));
				if (mIconStyle == 0)
				{
					mExpandIcon = icons.getSubimage(11 * 16, 0, 16, 16);
					mCollapseIcon = icons.getSubimage(10 * 16, 0, 16, 16);
				}
				else
				{
					mExpandIcon = icons.getSubimage(3 * 16, 0, 16, 16);
					mCollapseIcon = icons.getSubimage(4 * 16, 0, 16, 16);
				}
			}
			catch (IOException e)
			{
				throw new IllegalStateException(e);
			}
		}

		return aExpand ? mExpandIcon : mCollapseIcon;
	}


	public void setBackground(Color aBackground)
	{
		this.mBackground = aBackground;
	}


	public Color getBackground()
	{
		return mBackground;
	}


	public void setForeground(Color aForeground)
	{
		this.mForeground = aForeground;
	}


	public Color getForeground()
	{
		return mForeground;
	}


	public Font getFont()
	{
		return mFont;
	}


	public Styles setFont(Font aFont)
	{
		mFont = aFont;
		return this;
	}


	public int getIconWidth()
	{
		return mIconWidth;
	}


	public Styles setIconWidth(int aIconWidth)
	{
		mIconWidth = aIconWidth;
		return this;
	}


	public int getIconTextSpacing()
	{
		return mIconTextSpacing;
	}


	public Styles setIconTextSpacing(int aIconTextSpacing)
	{
		mIconTextSpacing = aIconTextSpacing;
		return this;
	}


	public int getIndentWidth()
	{
		return mIndentWidth;
	}


	public Styles setIndentWidth(int aIndentWidth)
	{
		mIndentWidth = aIndentWidth;
		return this;
	}


	public int getColumnHeaderHeight()
	{
		return mColumnHeaderHeight;
	}


	public Styles setColumnHeaderHeight(int aColumnHeaderHeight)
	{
		mColumnHeaderHeight = aColumnHeaderHeight;
		return this;
	}
}
