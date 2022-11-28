package com.EMS.api.dao.impl;

import java.util.List;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.stereotype.Repository;

import com.EMS.api.dao.EmployeeDao;

import com.EMS.api.exception.EmployeeNotFoundException;
import com.EMS.api.module.Employee;

import net.bytebuddy.asm.Advice.Return;

@Repository
public class EmployeeDaoImpl  implements EmployeeDao
{
	/* Unable to acquire JDBC Connection the size of excel to database is only 10 ids 
	 * now it is possible when we add one property
	 * spring.datasource.hikari.maximum-pool-size=10*/

	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	List<Employee> list=null;
	
	
	@Override
	public boolean saveEmployee(Employee employee) 
	{
		Session session = sessionFactory.openSession();
		
		boolean isAdded=false;
		
		try 
		{
			Transaction transaction = session.beginTransaction();
		
			Employee employee2 = session.get(Employee.class, employee.getEmployeeId());
			
			if (employee2==null) {
		
				session.save(employee);
				
				transaction.commit();
				
				isAdded=true;
				
			}else 
			{
				return false;
			}
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			if (session!=null) 
			{
				session.close();
			}
		}
		return isAdded;
	}

	
	@Override
	public int excelToDatabase(List<Employee> list) 
	{
		int count=0;
		
		for (Employee employee : list) {
			
			boolean isAdded = saveEmployee(employee);
		
			count=count+1;
		}
		
		return count;
	}

	
	@Override
	public boolean deleteEmployeeById(int employeeId) 
	{
		boolean isDeleted=false;
		
		Session session = sessionFactory.openSession();
		
		try 
		{
			Transaction transaction = session.beginTransaction();
			
			Employee employee = session.get(Employee.class, employeeId);
			
			if(employee!=null) 
			{
				session.delete(employee);
				
				transaction.commit();
		
				isDeleted=true;
			}
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			if (session!=null) 
			{
				session.close();
			}
		}
		return isDeleted;
	}

	
	@Override
	public List<Employee> getAllEmployees() 
	{
		List list=null;
		
		Session session = sessionFactory.openSession();
		
		try 
		{
			/*createCriteria method is not shown criteria interface */
		
			Criteria criteria = session.createCriteria(Employee.class);
			
			list = criteria.list();
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			if (session!=null) 
			{
				session.close();
			}
		}
	return list;
	}

	
	@Override
	public Employee getEmployeeById(int employeeId) 
	{
		Employee employee=null;
		
		Session session = sessionFactory.openSession();
		
		try 
		{
			Criteria criteria = session.createCriteria(Employee.class);
			
			employee = session.get(Employee.class, employeeId);
			
			if (employee!=null) 
			{
				
			}
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			if (session!=null) 
			{
				session.close();
			}
		}
		return employee ;
	}

	
	@Override
	public boolean updateEmployee(Employee employee) 
	{
		boolean isUpadated=false;
		
		Session session =sessionFactory.openSession();
		
		try 
		{
			Transaction transaction = session.beginTransaction();
			
			Employee employee2 = session.get(Employee.class, employee.getEmployeeId());
			
			if(employee2!=null)
			{
				session.evict(employee2);
				
				session.update(employee);
				
				transaction.commit();
				
				isUpadated=true;
			}
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			if (session!=null) 
			{
				session.close();
			}
		}
		return isUpadated;
	}

	
	@Override
	public List<Employee> getEmployeeByDepartment(String employeeDepartment) 
	{
		List list=null;
		
		Session session = sessionFactory.openSession();
		
		try 
		{
			Criteria criteria = session.createCriteria(Employee.class);
//			Employee employee = session.get(Employee.class, employeeDepartment);
//			if (employee!=null) 
			{
				criteria.add(Restrictions.eq("employeeDepartment", employeeDepartment));
				
				list = criteria.list();
			}
			
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			if (session!=null) 
			{
				session.close();
			}
		}
		return list;
	}

	
	@Override
	public List<Employee> getEmployeeByName(String employeeName) 
	{
		/*here i am facing a problem ..> if i am giving wrong name then it show me []*/
		List list= null;
		
		Employee employee= null;
		
		Session session = sessionFactory.openSession();
		
		try 
		{
			Criteria criteria = session.createCriteria(Employee.class);
//		
//			employee = session.get(Employee.class, employee.getEmployeeName());
			
//			if (employee!=null) 
			{
				criteria.add(Restrictions.eq("employeeName", employeeName));
				
				list = criteria.list();
			}
			
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			if (session!=null) 
			{
				session.close();
			}
		}
		return list;
	}

	
	@Override
	public List<Employee> getAllDepartmentsList() 
	{
		List<Employee> list=null;
		
		Session session = sessionFactory.openSession();
		
		try 
		{
			Criteria criteria = session.createCriteria(Employee.class);
			
			criteria.setProjection(Projections.property("employeeDepartment"));
			
			list=criteria.list();
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			if (session!=null) 
			{
				session.close();
			}
		}
		return list;
	}

	
	@Override
	public List<Employee> getAllEmployeesNameList() 
	{
		List<Employee> list=null;
		
		Session session = sessionFactory.openSession();
		
		try 
		{
			Criteria criteria = session.createCriteria(Employee.class);
			
			criteria.setProjection(Projections.property("employeeName"));
			
			list= criteria.list();
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			if (session!=null) 
			{
				session.close();
			}
		}
		return list;
	}

	
	@Override
	public List<Employee> getEmployeeByFirstLetter(String ltr) 
	{
		List<Employee> list=null;
		
		Session session = sessionFactory.openSession();
		
		try 
		{
			Criteria criteria = session.createCriteria(Employee.class);
		
			criteria.add(Restrictions.ilike("employeeName",ltr ,MatchMode.START));
			
			list= criteria.list();
		} 
		catch (Exception e) 
		{
			
		}
		finally 
		{
			if (session!=null) 
			{
				session.close();
			}
		}
		return list;
	}

	
 	@Override
	public List<Employee> getEmployeeByAscId() 
 	{
 		Session session= sessionFactory.openSession();
 		
 		try 
 		{
 			Criteria criteria = session.createCriteria(Employee.class);
 			
 			criteria.addOrder(Order.asc("employeeId"));
 			
 			list=criteria.list();
 			
		} 
 		catch (Exception e)
 		{
			e.printStackTrace();
		}
 		finally 
		{
			if (session!=null) 
			{
				session.close();
			}
		}
		return list;
	}

 	
 	@Override
	public List<Employee> getEmployeeByDescId() 
 	{
 		Session session= sessionFactory.openSession();
 		
 		try 
 		{
 			Criteria criteria = session.createCriteria(Employee.class);
 			
 			criteria.addOrder(Order.desc("employeeId"));
 			
 			list=criteria.list();
 			
		} 
 		catch (Exception e)
 		{
			e.printStackTrace();
		}
 		finally 
		{
			if (session!=null) 
			{
				session.close();
			}
		}
		return list;
		
	}

 	
 	@Override
	public List<Employee> getEmployeeByAscName() 
 	{
 		Session session= sessionFactory.openSession();
 		
 		try 
 		{
 			Criteria criteria = session.createCriteria(Employee.class);
 			
 			criteria.addOrder(Order.asc("employeeName"));
 			
 			list=criteria.list();
 			
		} 
 		catch (Exception e)
 		{
			e.printStackTrace();
		}
 		finally 
		{
			if (session!=null) 
			{
				session.close();
			}
		}
		return list;
	}


 	@Override
	public List<Employee> getEmployeeByDescName() {
		
 		Session session= sessionFactory.openSession();
 		
 		try 
 		{
 			Criteria criteria = session.createCriteria(Employee.class);
 			
 			criteria.addOrder(Order.desc("employeeName"));
 			
 			list=criteria.list();
 			
		} 
 		catch (Exception e)
 		{
			e.printStackTrace();
		}
 		finally 
		{
			if (session!=null) 
			{
				session.close();
			}
		}
		return list;
	}

 	
 	@Override
	public List<Employee> getMaxSalaryEmployee() 
 	{
		Session session = sessionFactory.openSession();
		
		try 
		{
			Criteria criteria =session.createCriteria(Employee.class);

			criteria.setProjection(Projections.max("employeeSalary"));
			
			List<Double>list2 = criteria.list();
			
			Double double1 = list2.get(0);
			
			Criteria criteria2 = session.createCriteria(Employee.class);
			
			criteria2.add(Restrictions.eq("employeeSalary", double1));
			
			list= criteria2.list();
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			if (session!=null) 
			{
				session.close();
			}
		}
		return list;
	}


	@Override
	public List<Employee> getMinSalaryEmployee() 
	{
		Session session = sessionFactory.openSession();
		
		try 
		{
			Criteria criteria =session.createCriteria(Employee.class);
			
			criteria.setProjection(Projections.min("employeeSalary"));
			
			List<Double>list2 = criteria.list();
			
			Double double1 = list2.get(0);
			
			Criteria criteria2 = session.createCriteria(Employee.class);
			
			criteria2.add(Restrictions.eq("employeeSalary", double1));
			
			list= criteria2.list();
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			if (session!=null) 
			{
				session.close();
			}
		}
		return list;
	}


	
	
	@Override
	public double getTotalSumOfAllEmployeeSalary() 
	{
		Session session = sessionFactory.openSession();
		
		double sum=0;
		
		try
		{
			Criteria criteria = session.createCriteria(Employee.class);
			
			criteria.setProjection(Projections.sum("employeeSalary"));
			
			List<Double> list = criteria.list();
			
			sum= list.get(0);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			if (session!=null) 
			{
				session.close();
			}
		}
		return sum;
	}



	
	@Override
	public long getTotalCountOfEmployees() 
	{
		long count=0;
		
		Session session = sessionFactory.openSession();
		
		try 
		{
			Criteria criteria = session.createCriteria(Employee.class);
			
			criteria.setProjection(Projections.count("employeeId"));
		
			List<Long> list = criteria.list();
			
			count= list.get(0);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			if (session!=null) 
			{
				session.close();
			}
		}
		return count;
	}


	@Override
	public long getTotalEmployeeCountInDepartment(String employeeDepartment) 
	{
		long count=0;
		
		Session session = sessionFactory.openSession();
		
		try 
		{
			Criteria criteria = session.createCriteria(Employee.class);
			
			Criteria criteria2= session.createCriteria(Employee.class);
			
			criteria.add(Restrictions.eq("employeeDepartment", employeeDepartment));
			
			List<Employee> list2 = criteria.list();
			
			
			for (Employee  employee : list2) 
			{
				
				if (employee!=null) 
				{
					count=count+1;
				}
				
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			if (session!=null) 
			{
				session.close();
			}
		}
		return count;
	}


	@Override
	public List<Employee> getEmployeeByState(String employeeState) 
	{
		Session session = sessionFactory.openSession();
		
		try 
		{
			Criteria criteria= session.createCriteria(Employee.class);
			
			criteria.add(Restrictions.eq("employeeState", employeeState));
			
			list= criteria.list();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
				
				
		return list;
	}


	@Override
	public long getEmployeeCountByState(String employeeState) 
	{
		Session session = sessionFactory.openSession();
		
		int count=0;
		
		try 
		{
			Criteria criteria = session.createCriteria(Employee.class);
			
			criteria.add(Restrictions.eq("employeeState", employeeState));
			
			list= criteria.list();
			
			for (Employee employee : list) 
			{
				
				if (employee!=null)
				{
					count=count+1;
				}
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return count;
	}


	


	
	
}
