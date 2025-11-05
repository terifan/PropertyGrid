package org.terifan.propertygrid;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.Icon;
import org.terifan.ui.Anchor;
import org.terifan.ui.TextBox;


public class PropertyNode<T> implements Serializable
{
	private final static long serialVersionUID = 1L;

	protected T mValue;
	protected ArrayList<PropertyNode> mChildren;
	protected boolean mExpanded;
	protected boolean mRollover;
	protected boolean mSelected;
	protected boolean mSelectable;
	protected Integer mRowHeight;
	protected Color mForeground;
	protected Color mBackground;
	protected Color mRowBackground;
	protected Font mFont;
	protected Icon mIcon;


	public PropertyNode(T aValue)
	{
		mChildren = new ArrayList<>();
		mExpanded = true;
		mSelectable = true;
		mValue = aValue;
	}


	public boolean isSelectable()
	{
		return mSelectable;
	}


	public PropertyNode setSelectable(boolean aSelectable)
	{
		mSelectable = aSelectable;
		return this;
	}


	public Color getForeground()
	{
		return mForeground;
	}


	public PropertyNode setForeground(Color aColor)
	{
		mForeground = aColor;
		return this;
	}


	public Color getBackground()
	{
		return mBackground;
	}


	public PropertyNode setBackground(Color aColor)
	{
		mBackground = aColor;
		return this;
	}


	public Color getRowBackground()
	{
		return mRowBackground;
	}


	public PropertyNode setRowBackground(Color aColor)
	{
		mRowBackground = aColor;
		return this;
	}


	public Integer getRowHeight()
	{
		return mRowHeight;
	}


	public PropertyNode setRowHeight(Integer aRowHeight)
	{
		mRowHeight = aRowHeight;
		return this;
	}


	public Font getFont()
	{
		return mFont;
	}


	public PropertyNode setFont(Font aFont)
	{
		mFont = aFont;
		return this;
	}


	public PropertyNode add(PropertyNode aNode)
	{
		mChildren.add(aNode);
		return this;
	}


	public ArrayList<PropertyNode> getChildren()
	{
		return mChildren;
	}


	protected int paintComponent(PropertyGrid<T> aTree, Graphics aGraphics, int aWidth, int aY, int aLevel)
	{
		Styles styles = aTree.getStyles();

		if (aLevel > 0 || aTree.isPaintRootNode())
		{
			int level = aTree.isCompactFirstLevel() ? Math.max(aLevel - 1, 1) : aLevel;

			int indent = styles.mIndentWidth;
			int rowHeight = getRowHeight(aTree);

			if (mRowBackground != null)
			{
				aGraphics.setColor(mRowBackground);
				aGraphics.fillRect(0, aY, aWidth, rowHeight);
			}
			if (getActualBackground(aTree) != null)
			{
				int x = indent * level;
				aGraphics.setColor(getActualBackground(aTree));
				aGraphics.fillRect(x, aY, aWidth - x, rowHeight);
			}

			if (aTree.isPaintHorizontalLines())
			{
				aGraphics.setColor(styles.getHorizontalLineColor());
				aGraphics.drawLine(0, aY + rowHeight - 1, aWidth, aY + rowHeight - 1);
			}

			for (int i = 0, j = 0; i < level; i++, j++)
			{
				Color color = styles.getIndentBackgroundColor(i);

				if (color != null)
				{
					aGraphics.setColor(color);
					aGraphics.fillRect(indent * j, aY, indent, rowHeight);
				}
			}

			if (mSelectable && (mRollover || mSelected))
			{
				int x = aTree.isHighlightFullRow() ? 0 : indent * level;
				aGraphics.setColor(aTree.mWindowFocused ? mRollover ? mSelected ? styles.mNodeRolloverSelectedBackground : styles.mNodeRolloverBackground : mSelected ? styles.c2 : styles.c3 : mRollover ? mSelected ? styles.mNodeRolloverSelectedBackground : styles.mNodeRolloverBackground : mSelected ? styles.mNodeSelectedBackground : styles.c3);
				aGraphics.fillRect(x, aY, aWidth - x, rowHeight);
				aGraphics.setColor(aTree.mWindowFocused ? mRollover ? mSelected ? styles.mNodeRolloverSelectedBorder : styles.mNodeRolloverBorder : mSelected ? styles.t2 : styles.c3 : mRollover ? mSelected ? styles.mNodeRolloverSelectedBorder : styles.mNodeRolloverBorder : mSelected ? styles.mNodeSelectedBorder : styles.c3);
				aGraphics.drawRect(x, aY, aWidth - x - 1, rowHeight - 1);
			}

			if (aTree.isPaintIndentLines())
			{
				for (int i = 0, j = 0; i < level; i++, j++)
				{
					Color color = styles.getIndentLineColor(j);

					if (color != null)
					{
						int x0 = indent * j + indent / 2;

						aGraphics.setColor(color);
						aGraphics.drawLine(x0, aY, x0, aY + rowHeight - 1);
					}
				}
			}

			int[] columnWidths = PropertyGridColumnHeader.computeColumnWidths(aTree.getColumns(), aWidth);

			for (int columnIndex = 0, x0 = 0; columnIndex < aTree.getColumns().size(); columnIndex++)
			{
				boolean lastColumn = columnIndex == aTree.getColumns().size() - 1;
				int cw = columnWidths[columnIndex];
				int cx = columnIndex == 0 ? indent * level : x0;

				if (columnIndex == 0)
				{
					if (!mChildren.isEmpty())
					{
						BufferedImage icon = styles.getIcon(mExpanded);
						aGraphics.drawImage(icon, cx - indent + (indent - icon.getWidth()) / 2, aY + rowHeight / 2 - icon.getHeight() / 2, null);
					}

					Icon icon = mIcon;
					if (icon == null)
					{
						icon = aTree.getFieldValueProvider().getIcon(aTree, mValue);
					}

					if (icon != null)
					{
						cx += styles.mIconWidth;

						icon.paintIcon(aTree, aGraphics, cx - indent + (indent - icon.getIconWidth()) / 2, aY + (rowHeight - icon.getIconHeight()) / 2);
					}

					cx += styles.mIconTextSpacing;
				}
				else if (aTree.isPaintVerticalLines())
				{
					aGraphics.setColor(styles.getVerticalLineColor());
					aGraphics.drawLine(cx, aY, cx, aY + rowHeight - 1);

					cx += styles.mCellLeftMargin;
				}

				Icon icon = aTree.getFieldValueProvider().getIcon(aTree, columnIndex, mValue);

				if (icon != null)
				{
					cx += styles.mIconWidth;

					icon.paintIcon(aTree, aGraphics, cx - indent + (indent - icon.getIconWidth()) / 2, aY + (rowHeight - icon.getIconHeight()) / 2);
				}

				String text = aTree.getFieldValueProvider().getText(aTree, columnIndex, mValue);

				new TextBox(text)
					.setForeground(getActualForeground(aTree))
					.setFont(getActualFont(aTree))
					.setBounds(cx, aY, (lastColumn ? aWidth - cx : cw - cx + x0) - styles.mCellRightMargin, rowHeight)
					.setMaxLineCount(1)
					.setBreakChars(null)
					.setAnchor(Anchor.WEST)
					.render(aGraphics);

				x0 += cw;
			}

			aY += rowHeight;
		}

		if (mExpanded)
		{
			for (PropertyNode node : mChildren)
			{
				aY = node.paintComponent(aTree, aGraphics, aWidth, aY, aLevel + 1);
			}
		}

		return aY;
	}


	protected int getRowHeight(PropertyGrid aTree)
	{
		return mRowHeight != null ? mRowHeight : aTree.getStyles().mRowHeight;
	}


	protected Dimension getPreferredSize(PropertyGrid<T> aTree, int aLevel)
	{
		Dimension result = new Dimension(0, 0);

		if (aLevel > 0 || aTree.isPaintRootNode())
		{
			result.height += getRowHeight(aTree);
		}

		for (int columnIndex = 0, x = 0; columnIndex < aTree.getColumns().size(); columnIndex++)
		{
			PropertyGridColumn column = aTree.getColumns().get(columnIndex);
			int w = Math.max(column.getWidth(), column.getMinimumWidth());

			result.width += w;
		}

		if (mExpanded)
		{
			for (PropertyNode node : mChildren)
			{
				Dimension d = node.getPreferredSize(aTree, aLevel + 1);
				result.width = Math.max(result.width, d.width);
				result.height += d.height;
			}
		}

		return result;
	}


	public PropertyNode intersect(PropertyGrid aTree, MouseEvent aEvent, AtomicInteger aOffsetY, AtomicInteger aLevel)
	{
		if (aTree.isPaintRootNode() || aTree.getRoot() != this)
		{
			if (aEvent.getY() >= aOffsetY.get() && aEvent.getY() < aOffsetY.get() + getRowHeight(aTree))
			{
				return this;
			}

			aOffsetY.addAndGet(getRowHeight(aTree));
		}

		if (mExpanded)
		{
			aLevel.incrementAndGet();

			for (PropertyNode node : mChildren)
			{
				PropertyNode tmp = node.intersect(aTree, aEvent, aOffsetY, aLevel);

				if (tmp != null)
				{
					return tmp;
				}
			}

			aLevel.decrementAndGet();
		}

		return null;
	}


	private Color getActualBackground(PropertyGrid aGrid)
	{
		if (mBackground != null)
		{
			return mBackground;
		}
		Styles styles = aGrid.getStyles();
		if (this instanceof PropertyTitleNode && styles.mTitleBackground != null)
		{
			return styles.mTitleBackground;
		}
		if (styles.mBackground != null)
		{
			return styles.mBackground;
		}
		return aGrid.getBackground();
	}


	private Color getActualForeground(PropertyGrid aGrid)
	{
		if (mForeground != null)
		{
			return mForeground;
		}
		Styles styles = aGrid.getStyles();
		if (this instanceof PropertyTitleNode && styles.mTitleForeground != null)
		{
			return styles.mTitleForeground;
		}
		if (styles.mForeground != null)
		{
			return styles.mForeground;
		}
		return aGrid.getForeground();
	}


	private Font getActualFont(PropertyGrid aGrid)
	{
		if (mFont != null)
		{
			return mFont;
		}
		Styles styles = aGrid.getStyles();
		if (this instanceof PropertyTitleNode && styles.mTitleFont != null)
		{
			return styles.mTitleFont;
		}
		return styles.mFont;
	}
}
