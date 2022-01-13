package org.terifan.propertygrid;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class Test extends JPanel
{
	public static void main(String ... args)
	{
		try
		{
			PropertyGrid test1 = test1();
			PropertyGrid test2 = test2();
			PropertyGrid test3 = test3();
			PropertyGrid test4 = test4();

			JPanel panel = new JPanel(new GridLayout(2, 3, 20, 20));
			panel.add(new JScrollPane(test1));
			panel.add(new JScrollPane(test2));
			panel.add(new JScrollPane(test3));
			panel.add(new JScrollPane(test4));
			panel.add(new JLabel(""));
			panel.add(new JLabel(""));

			JFrame frame = new JFrame();
			frame.addWindowFocusListener(new WindowFocusListener()
			{
				@Override
				public void windowGainedFocus(WindowEvent aEvent)
				{
					test1.mWindowFocused = true;
					test2.mWindowFocused = true;
					test3.mWindowFocused = true;
					test4.mWindowFocused = true;
					frame.repaint();
				}


				@Override
				public void windowLostFocus(WindowEvent aEvent)
				{
					test1.mWindowFocused = false;
					test2.mWindowFocused = false;
					test3.mWindowFocused = false;
					test4.mWindowFocused = false;
					frame.repaint();
				}
			});
			frame.add(panel);
			frame.setSize(1600, 1200);
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		}
		catch (Throwable e)
		{
			e.printStackTrace(System.out);
		}
	}


	public static PropertyGrid test1() throws IOException
	{
		BufferedImage icons = ImageIO.read(Test.class.getResource("icons.png"));

		PropertyNode n0 = new PropertyNode(new Entity("Aaaaaa", icons));
		PropertyNode n1 = new PropertyNode(new Entity("Bbbbbbbb", icons));
		PropertyNode n2 = new PropertyNode(new Entity("Cccc", icons));
		PropertyNode n3 = new PropertyNode(new Entity("Ddddd", icons));
		PropertyNode n4 = new PropertyNode(new Entity("Eeee", icons));
		PropertyNode n5 = new PropertyNode(new Entity("Fffffff", icons));
		PropertyNode n6 = new PropertyNode(new Entity("Ggggggg", icons));
		PropertyNode n7 = new PropertyNode(new Entity("Hhhhhhh", icons));
		PropertyNode n8 = new PropertyNode(new Entity("Iiiiiii", icons));
		PropertyNode n9 = new PropertyNode(new Entity("Jjjjj", icons));
		PropertyNode n10 = new PropertyNode(new Entity("Kkkkkkkk", icons));
		PropertyNode n11 = new PropertyNode(new Entity("Llllllll", icons));
		PropertyNode n12 = new PropertyNode(new Entity("Mmmmmmm", icons));
		PropertyNode n13 = new PropertyNode(new Entity("Nnnnnnnn", icons));
		PropertyNode n14 = new PropertyNode(new Entity("Ooooooo", icons));
		PropertyNode n15 = new PropertyNode(new Entity("Ppppppp", icons));
		PropertyNode n16 = new PropertyNode(new Entity("Qqqqqqq", icons));
		PropertyNode n17 = new PropertyNode(new Entity("Rrrrrrr", icons));
		PropertyNode n18 = new PropertyNode(new Entity("Sss", icons));
		PropertyNode n19 = new PropertyNode(new Entity("Tttttttttt", icons));
		n0.add(n1);
		n0.add(n2);
		n0.add(n15);
		n11.add(n16);
		n15.add(n17);
		n15.add(n18);
		n15.add(n19);
		n1.add(n3);
		n1.add(n4);
		n1.add(n10);
		n1.add(n11);
		n1.add(n5);
		n2.add(n6);
		n2.add(n12);
		n2.add(n7);
		n6.add(n8);
		n6.add(n13);
		n0.add(n9);
		n0.add(n14);

		PropertyGrid tree = new PropertyGrid();
		tree.setIconStyle(1);
		tree.addColumn(new PropertyGridColumn("Key").setFieldName("key").setWidth(-200));
		tree.addColumn(new PropertyGridColumn("Value").setFieldName("value").setWidth(-150));
		tree.addColumn(new PropertyGridColumn("Type").setFieldName("type").setWidth(-100));
		tree.setRoot(n0);
//		tree.setPaintHorizontalLines(true);
//		tree.setPaintVerticalLines(true);
//		tree.setPaintIndentLines(true);
//		tree.setPaintRootNode(false);
//		tree.setPaintHeaderRow(false);
		tree.setIndentWidth(20);
		tree.setIconWidth(20);
		tree.setIconTextSpacing(4);
		tree.setColumnHeaderHeight(28);

		return tree;
	}


	public static PropertyGrid test2() throws IOException
	{
		BufferedImage icons = ImageIO.read(Test.class.getResource("icons.png"));

		PropertyNode n0 = new PropertyNode(new Entity("Aaaaaa", icons));
		PropertyNode n1 = new PropertyNode(new Entity("Bbbbbbbb", icons)).setSelectable(false).setFont(new Font("arial", Font.BOLD, 12));
		PropertyNode n2 = new PropertyNode(new Entity("Cccc", icons)).setSelectable(false).setFont(new Font("arial", Font.BOLD, 12));
		PropertyNode n3 = new PropertyNode(new Entity("Ddddd", icons));
		PropertyNode n4 = new PropertyNode(new Entity("Eeee", icons));
		PropertyNode n5 = new PropertyNode(new Entity("Fffffff", icons));
		PropertyNode n6 = new PropertyNode(new Entity("Ggggggg", icons));
		PropertyNode n7 = new PropertyNode(new Entity("Hhhhhhh", icons));
		PropertyNode n8 = new PropertyNode(new Entity("Iiiiiii", icons));
		PropertyNode n9 = new PropertyNode(new Entity("Jjjjj", icons));
		PropertyNode n10 = new PropertyNode(new Entity("Kkkkkkkk", icons));
		PropertyNode n11 = new PropertyNode(new Entity("Llllllll", icons));
		PropertyNode n12 = new PropertyNode(new Entity("Mmmmmmm", icons));
		PropertyNode n13 = new PropertyNode(new Entity("Nnnnnnnn", icons));
		PropertyNode n14 = new PropertyNode(new Entity("Ooooooo", icons));
		PropertyNode n15 = new PropertyNode(new Entity("Ppppppp", icons)).setSelectable(false).setFont(new Font("arial", Font.BOLD, 12));
		PropertyNode n16 = new PropertyNode(new Entity("Qqqqqqq", icons));
		PropertyNode n17 = new PropertyNode(new Entity("Rrrrrrr", icons));
		PropertyNode n18 = new PropertyNode(new Entity("Sss", icons));
		PropertyNode n19 = new PropertyNode(new Entity("Tttttttttt", icons));
		n0.add(n1);
		n0.add(n2);
		n0.add(n15);
		n11.add(n16);
		n15.add(n17);
		n15.add(n18);
		n15.add(n19);
		n1.add(n3);
		n1.add(n4);
		n1.add(n10);
		n1.add(n11);
		n1.add(n5);
		n2.add(n6);
		n2.add(n12);
		n2.add(n7);
		n6.add(n8);
		n6.add(n13);
		n0.add(n9);
		n0.add(n14);

		PropertyGrid tree = new PropertyGrid();
		tree.addColumn(new PropertyGridColumn("Key").setFieldName("key").setWidth(200));
		tree.addColumn(new PropertyGridColumn("Value").setFieldName("value").setWidth(-1).setMinimumWidth(50));
		tree.addColumn(new PropertyGridColumn("Type").setFieldName("type").setWidth(50).setMinimumWidth(50));
		tree.setRoot(n0);
		tree.setPaintIndentLines(true);
		tree.setHighlightFullRow(true);
		tree.setIndentWidth(19);
		tree.setIconWidth(20);
		tree.setIconTextSpacing(4);
		tree.setIndentBackgroundColor(0, new Color(0xFFAA44));
		tree.setIndentBackgroundColor(1, new Color(0xFF9933));
		tree.setIndentBackgroundColor(2, new Color(0xFF8822));
		tree.setIndentLineColor(0, new Color(0xFF7722));
		tree.setIndentLineColor(1, new Color(0xFF6611));
		tree.setIndentLineColor(2, new Color(0xFF5500));

		return tree;
	}


	public static PropertyGrid test3() throws IOException
	{
		PropertyNode n0 = new PropertyNode(new Entity("Aaaaaa"));
		PropertyNode n1 = new PropertyNode(new Entity("Bbbbbbbb")).setRowBackground(new Color(0xF4F7FC)).setSelectable(false).setFont(new Font("arial", Font.BOLD, 12));
		PropertyNode n2 = new PropertyNode(new Entity("Cccc")).setRowBackground(new Color(0xF4F7FC)).setSelectable(false).setFont(new Font("arial", Font.BOLD, 12));
		PropertyNode n3 = new PropertyNode(new Entity("Ddddd"));
		PropertyNode n4 = new PropertyNode(new Entity("Eeee"));
		PropertyNode n5 = new PropertyNode(new Entity("Fffffff"));
		PropertyNode n6 = new PropertyNode(new Entity("Ggggggg"));
		PropertyNode n7 = new PropertyNode(new Entity("Hhhhhhh"));
		PropertyNode n8 = new PropertyNode(new Entity("Iiiiiii"));
		PropertyNode n9 = new PropertyNode(new Entity("Jjjjj"));
		PropertyNode n10 = new PropertyNode(new Entity("Kkkkkkkk"));
		PropertyNode n11 = new PropertyNode(new Entity("Llllllll"));
		PropertyNode n12 = new PropertyNode(new Entity("Mmmmmmm"));
		PropertyNode n13 = new PropertyNode(new Entity("Nnnnnnnn"));
		PropertyNode n14 = new PropertyNode(new Entity("Ooooooo"));
		PropertyNode n15 = new PropertyNode(new Entity("Ppppppp")).setRowBackground(new Color(0xF4F7FC)).setSelectable(false).setFont(new Font("arial", Font.BOLD, 12));
		PropertyNode n16 = new PropertyNode(new Entity("Qqqqqqq"));
		PropertyNode n17 = new PropertyNode(new Entity("Rrrrrrr"));
		PropertyNode n18 = new PropertyNode(new Entity("Sss"));
		PropertyNode n19 = new PropertyNode(new Entity("Tttttttttt"));
		n0.add(n1);
		n0.add(n2);
		n0.add(n15);
		n11.add(n16);
		n15.add(n17);
		n15.add(n18);
		n15.add(n19);
		n1.add(n3);
		n1.add(n4);
		n1.add(n10);
		n1.add(n11);
		n1.add(n5);
		n2.add(n6);
		n2.add(n12);
		n2.add(n7);
		n6.add(n8);
		n6.add(n13);
		n0.add(n9);
		n0.add(n14);

		PropertyGrid tree = new PropertyGrid();
		tree.addColumn(new PropertyGridColumn("Key").setFieldName("key").setWidth(200));
		tree.addColumn(new PropertyGridColumn("Value").setFieldName("value").setWidth(150));
		tree.setRoot(n0);
		tree.setPaintHorizontalLines(true);
		tree.setPaintVerticalLines(true);
//		tree.setPaintIndentLines(true);
		tree.setPaintRootNode(false);
		tree.setPaintHeaderRow(false);
		tree.setIndentWidth(20);
		tree.setIndentBackgroundColor(0, new Color(0xF4F7FC));
		tree.setIconWidth(20);
		tree.setIconTextSpacing(4);

		return tree;
	}


	public static PropertyGrid test4() throws IOException
	{
		BufferedImage icons = ImageIO.read(PropertyNode.class.getResource("icons.png"));

		PropertyNode n0 = new PropertyNode(new Entity("Aaaaaa", icons, 5,6,9));
		PropertyNode n1 = new PropertyNode(new Entity("Bbbbbbbb", icons, 5,6,9));
		PropertyNode n2 = new PropertyNode(new Entity("Cccc", icons, 5,6,9));
		PropertyNode n3 = new PropertyNode(new Entity("Ddddd", icons));
		PropertyNode n4 = new PropertyNode(new Entity("Eeee", icons));
		PropertyNode n5 = new PropertyNode(new Entity("Fffffff", icons));
		PropertyNode n6 = new PropertyNode(new Entity("Ggggggg", icons));
		PropertyNode n7 = new PropertyNode(new Entity("Hhhhhhh", icons));
		PropertyNode n8 = new PropertyNode(new Entity("Iiiiiii", icons));
		PropertyNode n9 = new PropertyNode(new Entity("Jjjjj", icons));
		PropertyNode n10 = new PropertyNode(new Entity("Kkkkkkkk", icons));
		PropertyNode n11 = new PropertyNode(new Entity("Llllllll", icons));
		PropertyNode n12 = new PropertyNode(new Entity("Mmmmmmm", icons));
		PropertyNode n13 = new PropertyNode(new Entity("Nnnnnnnn", icons));
		PropertyNode n14 = new PropertyNode(new Entity("Ooooooo", icons));
		PropertyNode n15 = new PropertyNode(new Entity("Ppppppp", icons));
		PropertyNode n16 = new PropertyNode(new Entity("Qqqqqqq", icons));
		PropertyNode n17 = new PropertyNode(new Entity("Rrrrrrr", icons));
		PropertyNode n18 = new PropertyNode(new Entity("Sss", icons));
		PropertyNode n19 = new PropertyNode(new Entity("Tttttttttt", icons));
		n0.add(n1);
		n0.add(n2);
		n0.add(n15);
		n11.add(n16);
		n15.add(n17);
		n15.add(n18);
		n15.add(n19);
		n1.add(n3);
		n1.add(n4);
		n1.add(n10);
		n1.add(n11);
		n1.add(n5);
		n2.add(n6);
		n2.add(n12);
		n2.add(n7);
		n6.add(n8);
		n6.add(n13);
		n0.add(n9);
		n0.add(n14);

		PropertyGrid tree = new PropertyGrid();
		tree.addColumn(new PropertyGridColumn("Key").setFieldName("key").setWidth(-100));
		tree.addColumn(new PropertyGridColumn("A").setFieldName("a").setWidth(20));
		tree.addColumn(new PropertyGridColumn("B").setFieldName("b").setWidth(20));
		tree.addColumn(new PropertyGridColumn("C").setFieldName("c").setWidth(20));
		tree.setRoot(n0);
//		tree.setPaintHorizontalLines(true);
//		tree.setPaintVerticalLines(true);
//		tree.setPaintIndentLines(true);
		tree.setPaintRootNode(false);
		tree.setPaintHeaderRow(false);
		tree.setIndentWidth(20);
		tree.setHighlightFullRow(true);
//		tree.setIndentBackgroundColor(0, new Color(0xF4F7FC));
		tree.setIconWidth(20);
		tree.setIconTextSpacing(4);

		return tree;
	}


	private static class Entity
	{
		private static String[] TYPES = new String[]{"Unknown","Int32","String","Double","Binary","Boolean","ID","null","DateTime","Array","Object"};
		String key;
		String value;
		String type;
		Icon icon;
		Icon a;
		Icon b;
		Icon c;


		public Entity(String aKey)
		{
			this(aKey, (Icon)null);
		}


		public Entity(String aKey, Icon aIcon)
		{
			key = aKey;
			value = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			type = TYPES[new Random().nextInt(11)];
			icon = aIcon;
		}


		public Entity(String aKey, BufferedImage aImage, int... aExtra)
		{
			this(aKey, aImage);
			a = new ImageIcon(aImage.getSubimage(aExtra[0] * 16, 0, 16, 16));
			b = new ImageIcon(aImage.getSubimage(aExtra[1] * 16, 0, 16, 16));
			c = new ImageIcon(aImage.getSubimage(aExtra[2] * 16, 0, 16, 16));
		}


		public Entity(String aKey, BufferedImage aImage)
		{
			int i = new Random().nextInt(11);
			icon = new ImageIcon(aImage.getSubimage(i * 16, 16, 16, 16));

			key = aKey;
			value = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			type = TYPES[i];
		}
	}
}
