package org.terifan.propertygrid;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;


public abstract class Styles
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
