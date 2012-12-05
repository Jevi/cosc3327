package test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Set;

import tigers.CatCage;
import tigers.GridPacking;

public class CarolinaTigerRescue_StudentWrapper
{
	private Class<?> studentUtilsClass;
	private Class<?>[] PARAMETER_ARRAY = new Class<?>[] { Collection.class, Integer.TYPE, Integer.TYPE };

	public CarolinaTigerRescue_StudentWrapper(Class<?> studentUtilsClass)
	{
		assert studentUtilsClass != null : "studentUtilsClass is null!";
		this.studentUtilsClass = studentUtilsClass;
	}

	private Object invokeStaticMethod(Method method, Object[] args)
	{
		assert method != null : "method is null!";
		Object rv = null;
		try
		{
			rv = method.invoke(null, args);
		}
		catch (IllegalArgumentException e)
		{
			e.printStackTrace();
			throw new RuntimeException(e.getClass() + " " + e.getMessage());
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
			throw new RuntimeException(e.getClass() + " " + e.getMessage());
		}
		catch (InvocationTargetException e)
		{
			e.printStackTrace();
			System.out.println("ite.getTargetException() = " + e.getTargetException());
			throw new RuntimeException(e.getClass() + " " + e.getMessage());
		}
		return rv;
	}

	public Set<GridPacking<CatCage>> solve(Collection<CatCage> cageConfigurations, int rowCount, int columnCount)
	{
		Method method;
		try
		{
			method = studentUtilsClass.getMethod("solve", PARAMETER_ARRAY);
		}
		catch (SecurityException e)
		{
			e.printStackTrace();
			throw new RuntimeException(e.getClass() + " " + e.getMessage());
		}
		catch (NoSuchMethodException e)
		{
			e.printStackTrace();
			throw new RuntimeException(e.getClass() + " " + e.getMessage());
		}
		Object[] parameters = new Object[] { cageConfigurations, rowCount, columnCount };
		return (Set<GridPacking<CatCage>>) invokeStaticMethod(method, parameters);
	}
}
