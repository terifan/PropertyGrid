package org.terifan.propertygrid;

import java.lang.reflect.Field;
import javax.swing.Icon;


public class FieldValueProvider<T>
{
	public String getText(PropertyGrid<T> aTree, int aColumnIndex, T aValue)
	{
		Field field = getField(aTree, aColumnIndex, aValue);

		try
		{
			Object o = field.get(aValue);
			return o.toString();
		}
		catch (Error | Exception e)
		{
			return "";
		}
	}


	public Icon getIcon(PropertyGrid<T> aTree, T aValue)
	{
		Class<? extends Object> cls = aValue.getClass();

		try
		{
			for (Field field : cls.getDeclaredFields())
			{
				if (Icon.class.isAssignableFrom(field.getType()))
				{
					return (Icon)field.get(aValue);
				}
			}
		}
		catch (Exception e)
		{
		}

		return null;
	}


	public Icon getIcon(PropertyGrid<T> aTree, int aColumnIndex, T aValue)
	{
		Field field = getField(aTree, aColumnIndex, aValue);

		try
		{
			if (Icon.class.isAssignableFrom(field.getType()))
			{
				return (Icon)field.get(aValue);
			}
		}
		catch (Exception e)
		{
		}

		return null;
	}


	private Field getField(PropertyGrid<T> aTree, int aColumnIndex, T aValue)
	{
		PropertyGridColumn column = aTree.getColumns().get(aColumnIndex);

		try
		{
			String name = column.getFieldName();
			if (name == null)
			{
				name = column.getName();
			}

			Class<? extends Object> cls = aValue.getClass();

			Field field = null;
			try
			{
				field = cls.getField(name);
			}
			catch (Exception e)
			{
				try
				{
					field = cls.getDeclaredField(name);
				}
				catch (Exception ee)
				{
				}
			}

			field.setAccessible(true);

			return field;
		}
		catch (Exception e)
		{
			System.out.println("No field for column: " + column);
		}

		return null;
	}
}
