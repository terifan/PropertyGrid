package org.terifan.propertygrid;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;
import org.terifan.ui.Anchor;
import org.terifan.ui.TextBox;
import org.terifan.ui.Utilities;


public class PropertyGridColumnHeader extends JPanel
{
	protected PropertyGrid mPropertyGrid;


	protected PropertyGridColumnHeader(PropertyGrid aTree)
	{
		mPropertyGrid = aTree;
	}


	@Override
	protected void paintComponent(Graphics aGraphics)
	{
		Styles st = mPropertyGrid.getStyles();

		ArrayList<PropertyGridColumn> columns = mPropertyGrid.getColumns();
		int y = 0;
		int w = getWidth();
		int h = getHeight();

		Utilities.enableTextAntialiasing(aGraphics);

		aGraphics.setColor(st.mBackground);
		aGraphics.fillRect(0, 0, w, h);
		aGraphics.setColor(st.mHorizontalLineColor);
		aGraphics.drawLine(0, h - 1, w, h - 1);

		int[] columnWidths = computeColumnWidths(columns, w);

		for (int columnIndex = 0, x = 0; columnIndex < columns.size(); columnIndex++)
		{
			PropertyGridColumn column = columns.get(columnIndex);
			int cw = columnWidths[columnIndex];

			aGraphics.setColor(st.mHorizontalLineColor);
			aGraphics.drawLine(x + cw, 0, x + cw, h - 1);

			new TextBox(column.getName()).setForeground(st.mForeground).setBounds(x + 5, y, cw, h).setAnchor(Anchor.WEST).render(aGraphics);

			x += cw;
		}
	}


	@Override
	public Dimension getPreferredSize()
	{
//		int width = 0;
//		ArrayList<Column> columns = mTree.getColumns();
//		for (int columnIndex = 0; columnIndex < columns.size(); columnIndex++)
//		{
//			width += Math.abs(columns.get(columnIndex).getWidth());
//		}

		return new Dimension(1, mPropertyGrid.getStyles().getColumnHeaderHeight());
	}


	static int[] computeColumnWidths(ArrayList<PropertyGridColumn> aColumns, int aWidth)
	{
		int[] widths = new int[aColumns.size()];

		int weight = 0;
		int remainingWidth = aWidth;
		for (int i = 0; i < aColumns.size(); i++)
		{
			PropertyGridColumn column = aColumns.get(i);
			int cw = column.getWidth();
			if (cw < 0)
			{
				weight -= cw;
			}
			else
			{
				remainingWidth -= cw;
			}
		}

		for (int i = 0; i < aColumns.size(); i++)
		{
			PropertyGridColumn column = aColumns.get(i);
			int cw = column.getWidth();

			if (cw < 0)
			{
				cw = remainingWidth * -cw / weight;
			}

			widths[i] = Math.max(cw, column.getMinimumWidth());
		}

		return widths;
	}
}
