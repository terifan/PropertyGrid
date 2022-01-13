package org.terifan.propertygrid;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.atomic.AtomicInteger;


public class PropertyGridMouseListener extends MouseAdapter
{
	protected PropertyGrid mTree;


	public PropertyGridMouseListener(PropertyGrid aTree)
	{
		mTree = aTree;
	}


	@Override
	public void mousePressed(MouseEvent aEvent)
	{
		if (mTree.getRoot() != null)
		{
			AtomicInteger level = new AtomicInteger();

			PropertyNode node = mTree.getRoot().intersect(mTree, aEvent, new AtomicInteger(), level);

			if (node != null)
			{
				if (!node.getChildren().isEmpty() && aEvent.getX() > (level.get() - 1) * mTree.mIndentWidth && aEvent.getX() < level.get() * mTree.mIndentWidth)
				{
					node.mExpanded = !node.mExpanded;
				}
				else
				{
					mTree.setSelectedNode(node);
				}

				mTree.invalidate();
				mTree.repaint();
			}
		}
	}


	@Override
	public void mouseExited(MouseEvent aEvent)
	{
		mTree.setRollover(null);
		mTree.invalidate();
		mTree.repaint();
	}


	@Override
	public void mouseDragged(MouseEvent aEvent)
	{
		mouseMoved(aEvent);
	}


	@Override
	public void mouseMoved(MouseEvent aEvent)
	{
		if (mTree.getRoot() != null)
		{
			PropertyNode node = mTree.getRoot().intersect(mTree, aEvent, new AtomicInteger(), new AtomicInteger());

			if (mTree.mRolloverNode != node)
			{
				mTree.setRollover(node);
				mTree.invalidate();
				mTree.repaint();
			}
		}
	}
}
