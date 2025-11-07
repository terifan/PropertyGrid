package org.terifan.propertygrid;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.KeyStroke;
import javax.swing.Scrollable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;


public class PropertyGrid<T> extends JPanel implements Scrollable
{
	protected ArrayList<PropertyGridColumn> mColumns;
	protected FieldValueProvider<T> mFieldValueProvider;
	protected PropertyNode mTreeRoot;
	protected PropertyNode mRolloverNode;
	protected PropertyNode mSelectedNode;
	protected boolean mPaintRootNode;
	protected boolean mPaintIndentLines;
	protected boolean mPaintHeaderRow;
	protected boolean mPaintHorizontalLines;
	protected boolean mPaintVerticalLines;
	protected boolean mCompactFirstLevel;
	protected boolean mWindowFocused;
	protected boolean mHighlightFullRow;
	protected Styles mStyles;


	public PropertyGrid()
	{
		mFieldValueProvider = new FieldValueProvider<>();
		mColumns = new ArrayList<>();
		mStyles = new Styles();
		mPaintRootNode = true;
		mPaintHeaderRow = true;
		mPaintHorizontalLines = true;
		mPaintVerticalLines = true;

		super.setOpaque(true);
		super.setFocusable(true);

		// override JScrollPane actions...
		AbstractAction action = new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent aEvent)
			{
			}
		};
		super.registerKeyboardAction(action, KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), JComponent.WHEN_FOCUSED);
		super.registerKeyboardAction(action, KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), JComponent.WHEN_FOCUSED);
		super.registerKeyboardAction(action, KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), JComponent.WHEN_FOCUSED);
		super.registerKeyboardAction(action, KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), JComponent.WHEN_FOCUSED);
		super.registerKeyboardAction(action, KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_UP, 0), JComponent.WHEN_FOCUSED);
		super.registerKeyboardAction(action, KeyStroke.getKeyStroke(KeyEvent.VK_PAGE_DOWN, 0), JComponent.WHEN_FOCUSED);
		super.registerKeyboardAction(action, KeyStroke.getKeyStroke(KeyEvent.VK_HOME, 0), JComponent.WHEN_FOCUSED);
		super.registerKeyboardAction(action, KeyStroke.getKeyStroke(KeyEvent.VK_END, 0), JComponent.WHEN_FOCUSED);
		super.registerKeyboardAction(action, KeyStroke.getKeyStroke(KeyEvent.VK_HOME, InputEvent.CTRL_MASK), JComponent.WHEN_FOCUSED);
		super.registerKeyboardAction(action, KeyStroke.getKeyStroke(KeyEvent.VK_END, InputEvent.CTRL_MASK), JComponent.WHEN_FOCUSED);

		PropertyGridMouseListener mouseAdapter = new PropertyGridMouseListener(this);
		addMouseListener(mouseAdapter);
		addMouseMotionListener(mouseAdapter);
	}


	public PropertyGrid<T> setRoot(PropertyNode aTreeNode)
	{
		mTreeRoot = aTreeNode;
		return this;
	}


	public PropertyNode getRoot()
	{
		return mTreeRoot;
	}


	public PropertyGrid<T> addColumn(PropertyGridColumn aName)
	{
		mColumns.add(aName);
		return this;
	}


	public ArrayList<PropertyGridColumn> getColumns()
	{
		return mColumns;
	}


	public boolean isPaintRootNode()
	{
		return mPaintRootNode;
	}


	public PropertyGrid<T> setPaintRootNode(boolean aPaintRootNode)
	{
		mPaintRootNode = aPaintRootNode;
		return this;
	}


	public boolean isPaintIndentLines()
	{
		return mPaintIndentLines;
	}


	public PropertyGrid<T> setPaintIndentLines(boolean aPaintIndentLines)
	{
		mPaintIndentLines = aPaintIndentLines;
		return this;
	}


	public boolean isPaintHeaderRow()
	{
		return mPaintHeaderRow;
	}


	public PropertyGrid<T> setPaintHeaderRow(boolean aPaintHeaderRow)
	{
		mPaintHeaderRow = aPaintHeaderRow;
		configureEnclosingScrollPane();
		return this;
	}


	public boolean isPaintHorizontalLines()
	{
		return mPaintHorizontalLines;
	}


	public PropertyGrid<T> setPaintHorizontalLines(boolean aPaintHorizontalLines)
	{
		mPaintHorizontalLines = aPaintHorizontalLines;
		return this;
	}


	public boolean isPaintVerticalLines()
	{
		return mPaintVerticalLines;
	}


	public PropertyGrid<T> setPaintVerticalLines(boolean aPaintVerticalLines)
	{
		mPaintVerticalLines = aPaintVerticalLines;
		return this;
	}


	public boolean isCompactFirstLevel()
	{
		return mCompactFirstLevel;
	}


	public PropertyGrid<T> setCompactFirstLevel(boolean aCompactFirstLevel)
	{
		mCompactFirstLevel = aCompactFirstLevel;
		return this;
	}


	public FieldValueProvider<T> getFieldValueProvider()
	{
		return mFieldValueProvider;
	}


	public PropertyGrid<T> setFieldValueProvider(FieldValueProvider aFieldValueProvider)
	{
		mFieldValueProvider = aFieldValueProvider;
		return this;
	}


	public boolean isHighlightFullRow()
	{
		return mHighlightFullRow;
	}


	public PropertyGrid<T> setHighlightFullRow(boolean aHighlightFullRow)
	{
		mHighlightFullRow = aHighlightFullRow;
		return this;
	}


	@Override
	protected void paintComponent(Graphics aGraphics)
	{
		int y = 0;
		int w = getWidth();
		int h = getHeight();

		aGraphics.setColor(mStyles.getBackground());
		aGraphics.fillRect(0, y, w, h - y);

		if (mTreeRoot != null)
		{
			mTreeRoot.paintComponent(this, aGraphics, w, y, 0);
		}
	}


	@Override
	public Dimension getPreferredSize()
	{
		Dimension result;
		if (mTreeRoot == null)
		{
			result = new Dimension();
		}
		else
		{
			result = mTreeRoot.getPreferredSize(this, 0);
		}
		return result;
	}


	protected void setRollover(PropertyNode aNode)
	{
		if (mRolloverNode != null)
		{
			mRolloverNode.mRollover = false;
		}

		mRolloverNode = aNode;

		if (mRolloverNode != null)
		{
			mRolloverNode.mRollover = true;
		}

		repaint();
	}


	@Override
	public Dimension getPreferredScrollableViewportSize()
	{
		return getPreferredSize();
	}


	@Override
	public int getScrollableUnitIncrement(Rectangle aVisibleRect, int aOrientation, int aDirection)
	{
		int v = aOrientation == SwingConstants.VERTICAL ? mStyles.mRowHeight : mStyles.mRowHeight;
		return v;
	}


	@Override
	public int getScrollableBlockIncrement(Rectangle aVisibleRect, int aOrientation, int aDirection)
	{
		if (getParent() instanceof JViewport)
		{
			JViewport vp = (JViewport)getParent();
			return aOrientation == SwingConstants.VERTICAL ? vp.getHeight() : vp.getWidth();
		}

		int v = aOrientation == SwingConstants.VERTICAL ? mStyles.mRowHeight : mStyles.mRowHeight;
		return v;
	}


	@Override
	public boolean getScrollableTracksViewportWidth()
	{
		return getParent() instanceof JViewport && (((JViewport)getParent()).getWidth() > getPreferredSize().width);
	}


	@Override
	public boolean getScrollableTracksViewportHeight()
	{
		return getParent() instanceof JViewport && (((JViewport)getParent()).getHeight() > getPreferredSize().height);
	}


	protected JScrollPane getEnclosingScrollPane()
	{
		return (JScrollPane)SwingUtilities.getAncestorOfClass(JScrollPane.class, this);
	}


	@Override
	public void addNotify()
	{
		super.addNotify();
		configureEnclosingScrollPane();
	}


	public void configureEnclosingScrollPane()
	{
		JScrollPane scrollPane = getEnclosingScrollPane();

		if (scrollPane != null)
		{
			JViewport viewport = scrollPane.getViewport();
			viewport.setScrollMode(JViewport.BACKINGSTORE_SCROLL_MODE);

			if (mPaintHeaderRow)
			{
				JPanel columnHeaderView = new JPanel(new BorderLayout());
				columnHeaderView.add(new PropertyGridColumnHeader(this), BorderLayout.NORTH);

				scrollPane.setColumnHeaderView(columnHeaderView);
				scrollPane.setBorder(null);
			}
			else
			{
				scrollPane.setColumnHeaderView(null);
				scrollPane.setBorder(null);
			}

			JScrollBar vsb = scrollPane.getVerticalScrollBar();
			vsb.setUnitIncrement(mStyles.mRowHeight);
		}
	}


	protected void setSelectedNode(PropertyNode aNode)
	{
		if (mSelectedNode != null)
		{
			mSelectedNode.mSelected = false;
		}

		if (aNode == null || aNode.isSelectable())
		{
			mSelectedNode = aNode;

			if (mSelectedNode != null)
			{
				mSelectedNode.mSelected = true;
			}
		}
	}


	public Styles getStyles()
	{
		return mStyles;
	}


	public PropertyGrid<T> setStyles(Styles aStyles)
	{
		mStyles = aStyles;
		return this;
	}
}
