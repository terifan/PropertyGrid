package org.terifan.propertygrid;


public class PropertyGridColumn
{
	protected String mName;
	protected String mFieldName;
	protected int mWidth;
	protected int mMinimumWidth;


	public PropertyGridColumn(String aName)
	{
		mName = aName;
		mWidth = 100;
	}


	public String getName()
	{
		return mName;
	}


	public PropertyGridColumn setName(String aName)
	{
		mName = aName;
		return this;
	}


	public String getFieldName()
	{
		return mFieldName;
	}


	public PropertyGridColumn setFieldName(String aFieldName)
	{
		mFieldName = aFieldName;
		return this;
	}


	public int getWidth()
	{
		return mWidth;
	}


	public PropertyGridColumn setWidth(int aWidth)
	{
		mWidth = aWidth;
		return this;
	}


	public int getMinimumWidth()
	{
		return mMinimumWidth;
	}


	public PropertyGridColumn setMinimumWidth(int aMinimumWidth)
	{
		mMinimumWidth = aMinimumWidth;
		return this;
	}


	@Override
	public String toString()
	{
		return "Column{" + "mName=" + mName + ", mFieldName=" + mFieldName + '}';
	}
}
