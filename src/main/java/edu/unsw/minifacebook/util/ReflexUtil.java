package edu.unsw.minifacebook.util;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.lang.reflect.Field;

public class ReflexUtil {
	/**
	 * 查询SQL拼接
	 * 
	 * @param obj
	 * @return
	 */
	public static String getSelects(Object obj) {
		String sql = "select * from ";
		// if (null!=obj){
		// //获取实体的名称并小写实体名
		// sql=sql+obj.getClass().getName().substring(obj.getClass().getName().lastIndexOf(".")+1).toLowerCase();
		// }
		sql += "EntityStore ";
		String classname = obj.getClass().getName();
		Field[] fe = obj.getClass().getDeclaredFields();// 通过数组获取该类型下所有的属性
		try {
			if (fe.length >= 0) {
				sql = sql + " where 1=1 ";
				for (int j = 0; j < fe.length; j++) {
					String name = fe[j].getName();
					//name = name.substring(0, 1).toUpperCase() + name.substring(1);// 将属性的首字母大写，调用的get方法属性名大写

					
					if(name.equals("id")) {
						Method m = obj.getClass().getMethod("get" + name);
						Integer id = (Integer) m.invoke(obj);
						sql+= " and subject = " + classname.substring(0, 1).toUpperCase() +id;
						continue;
					}
					
					String type = fe[j].getGenericType().toString();// 获取属性值的类型
					// 判断属性值属于什么，然后根据类型获取其中的值 根据项目需求自行增加类型判断
					// String 类型
					if (type.equals("class java.lang.String")) {
						Method m = obj.getClass().getMethod("get" + name);
						String value = (String) m.invoke(obj);// 获取属性值
						if (null != value && !"".equals(value)) {
							sql = sql + " and "
									+ "predicate = " + name + " and object = '" + value + "'";
						}

					}
					if (type.equals("class java.util.Date")) {
						Method m = obj.getClass().getMethod("get" + name);
						Date value = (Date) m.invoke(obj);
						if (null != value) {
							sql = sql + " and "
									+ "predicate = " + name + " and object = '" + value + "'";
						}
					}
					if (type.equals("class java.lang.Integer")) {
						Method m = obj.getClass().getMethod("get" + name);
						Integer value = (Integer) m.invoke(obj);
						if (null != value && !"".equals(value)) {
							sql = sql + " and "
									+ "predicate = " + name + " and object = " + value;
						}
					}


				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sql;
	}

	/**
	 * 修改SQL拼接
	 * 
	 * @param obj
	 * @return
	 */
	public static String getUpdates(Object obj) {
		String sql = "update ";
		try {
			if (null != obj) {
				sql = sql + obj.getClass().getName().substring(obj.getClass().getName().lastIndexOf(".") + 1)
						.toLowerCase();
				String parameter = " set ";
				Field[] fields = obj.getClass().getDeclaredFields();// 获取实体的所有属性
				String trems = " where 1=1 ";
				for (int j = 0; j < fields.length; j++) {
					String name = fields[j].getName();// 获取属性名
					name = name.substring(0, 1).toUpperCase() + name.substring(1);// 首字母大写 通过get获取值方法名大写
					String type = fields[j].getGenericType().toString();// 获取属性值的类型
					// 修改语句必须有条件，此处默认为主键ID值 一般ID是唯一的

					// 判断属性值，然后根据属性值拼接sql
					if (type.equals("class java.lang.String")) {
						Method m = obj.getClass().getMethod("get" + name);
						String value = (String) m.invoke(obj);
						if (null != value && !"".equals(value)) {
							// 判断参数是否为遍历的第一个
							if (" set ".equals(parameter) || " set " == parameter) {
								parameter = parameter + fields[j].getName() + "='" + value + "'";
							} else {
								parameter = parameter + "," + fields[j].getName() + "='" + value + "'";
							}
						}
					}
					if (type.equals("class java.lang.Integer")) {
						Method m = obj.getClass().getMethod("get" + name);
						Integer value = (Integer) m.invoke(obj);
						if (fields[j].getName().toUpperCase().equals("ID")
								|| "ID" == fields[j].getName().toUpperCase()) {
							// 如果参数为ID 此处应该拼接条件语句
							trems = trems + " and id='" + value + "'";
							continue;
						} else {
							if (null != value && !"".equals(value)) {
								// 判断参数是否为遍历的第一个
								if (" set ".equals(parameter) || " set " == parameter) {
									parameter = parameter + fields[j].getName() + "='" + value + "'";
								} else {
									parameter = parameter + "," + fields[j].getName() + "='" + value + "'";
								}
							}
						}
					}
					if (type.equals("class java.util.Date")) {
						Method m = obj.getClass().getMethod("get" + name);
						Date value = (Date) m.invoke(obj);
						if (null != value && !"".equals(value)) {
							// 判断参数是否为遍历的第一个
							if (" set ".equals(parameter) || " set " == parameter) {
								parameter = parameter + fields[j].getName() + "='" + value + "'";
							} else {
								parameter = parameter + "," + fields[j].getName() + "='" + value + "'";
							}
						}
					}
					if (type.equals("class java.lang.Boolean")) {
						Method m = obj.getClass().getMethod("get" + name);
						Boolean value = (Boolean) m.invoke(obj);
						if (null != value && !"".equals(value)) {
							// 判断参数是否为遍历的第一个
							if (" set ".equals(parameter) || " set " == parameter) {
								parameter = parameter + fields[j].getName() + "='" + value;
							} else {
								parameter = parameter + "," + fields[j].getName() + "='" + value;
							}
						}
					}
				} // 循环结束，拼接SQL
				if (" set " == parameter || " set ".equals(parameter)) { // 参数为空，不执行
					return null;
				} else if (" where 1=1 " == trems || " where 1=1 ".equals(trems)) { // 如果条件为空,不执行，如果需要修改所有请调整(项目中应拒绝这种修改所有的情况)
					return null;
				} else {
					sql = sql + parameter + trems;
				}
			} else {
				System.out.println("---------------错误信息----------------");
				System.out.println("传的实体" + obj.getClass().getName() + "为空");
				System.out.println("---------------错误信息结束----------------");
				return null;
			}
		} catch (Exception e) {
			System.out.println("----------异常信息--------------");
			e.printStackTrace();
			System.out.println("----------异常信息结束--------------");
			return null;
		}
		return sql;
	}

	/**
	 * 删除SQL拼接
	 * 
	 * @param obj
	 * @return
	 */
	public static String getDeletes(Object obj) {
		String sql = "delete from entitystore ";
		try {
			if (null != obj) {
				String classname = obj.getClass().getName().substring(obj.getClass().getName().lastIndexOf(".") + 1);
				//String subject = classname+ seq;
				Field[] fields = obj.getClass().getDeclaredFields();// 获取所有的属性
				sql += " where 1=1 and subject = '"; // 条件语句
				for (int j = 0; j < fields.length; j++) {
					String name = fields[j].getName();
					name = name.substring(0, 1).toUpperCase() + name.substring(1);// 首字母大写，调用get方法获取属性值
					String type = fields[j].getGenericType().toString();// 获取属性的参数的类型
					if(name.endsWith("id") || name.endsWith("Id")) {
						Method m = obj.getClass().getDeclaredMethod("get" + name);
						Integer value = (Integer) m.invoke(obj);
						sql += classname + value.toString();
					}

				}
				sql += "'";
			} else {
				System.out.println("-----------------实体为空-----------------");
				return null;
			}
		} catch (Exception e) {
			System.out.println("-----------------异常信息-----------------");
			e.printStackTrace();
			System.out.println("-----------------异常信息结束-----------------");
			return null;
		}
		return sql;
	}

	/**
	 * 新增SQL拼接
	 * 
	 * @param obj
	 * @return
	 */
	public static List<String> getInserts(Object obj, int seq) {
		List<String> sqls = new ArrayList<String>();
		try {
			if (null != obj) {
				//INSERT INTO `mini_facebook`.`sequence` (`name`, `current_value`, `increment`) VALUES ('a', '1', '2');

	
				String classname = obj.getClass().getName().substring(obj.getClass().getName().lastIndexOf(".") + 1);
				String subject = classname+ seq;

				Field[] fields = obj.getClass().getDeclaredFields();// 获取所有的属性

				for (int j = 0; j < fields.length; j++) {
					String sql = "insert into entitystore (subject,predicate,object) values ('";
					sql += subject + "', '";
					String name = fields[j].getName();
					name = name.substring(0, 1).toUpperCase() + name.substring(1); // 获取get方法，取值
					String type = fields[j].getGenericType().toString();
					if(name.endsWith("id") || name.endsWith("Id")) {
						if(!name.equals("Creatorid"))
							continue;
					}
					if (type.equals("class java.lang.String")) {
						Method m = obj.getClass().getDeclaredMethod("get" + name);
						String value = (String) m.invoke(obj);
						if (null != value && !"".equals(value)) {
							sql += name + "','";
							sql += value + "')";
							sqls.add(sql);
						}

						continue;
					}
					if (type.equals("class java.lang.Integer")) {
						Method m = obj.getClass().getDeclaredMethod("get" + name);
						Integer value = (Integer) m.invoke(obj);
						if (null != value && !"".equals(value)) {
							if (null != value && !"".equals(value)) {
								sql += name + "',";
								sql += value + ")";
								sqls.add(sql);
							}
							continue;
						}

					}
					if (type.equals("class java.lang.Boolean")) {
						Method m = obj.getClass().getDeclaredMethod("get" + name);
						Boolean value = (Boolean) m.invoke(obj);
						if (null != value && !"".equals(value)) {
							if (null != value && !"".equals(value)) {
								sql += name + "',";
								sql += value + ")";
								sqls.add(sql);
							}

							continue;
						}

					}
					if (type.equals("class java.util.Date")) {
						Method m = obj.getClass().getDeclaredMethod("get" + name);
						Date value = (Date) m.invoke(obj);
						if (null != value && !"".equals(value)) {
							if (null != value && !"".equals(value)) {
								sql += name + "','";
								sql += value + "')";
								sqls.add(sql);
							}

							continue;
						}

					}

				} 

			} else {
				System.out.println("-----------------实体为空-----------------");
				return null;
			}

		} catch (Exception e) {
			System.out.println("-----------------异常信息-----------------");
			e.printStackTrace();
			System.out.println("-----------------异常信息结束-----------------");
			return null;
		}
		return sqls;
	}
	
	public static void setAttribute(Object obj, String attributename, Object value) {
		
		try {
			Field field = obj.getClass().getDeclaredField(attributename.substring(0, 1).toLowerCase() + attributename.substring(1));
			Method m = obj.getClass().getDeclaredMethod("set" + attributename, field.getType());
			if(field.getType().getName().equals("java.lang.Integer")) {
				value = Integer.parseInt((String)value);
			}else if(attributename.contains("time") || attributename.contains("Time")) {
					String dateString = (String)value;
					Date parsed;
					try {
					    SimpleDateFormat format =
					        new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
					    parsed = format.parse(dateString);
					}
					catch(ParseException pe) {
					    throw new IllegalArgumentException(pe);
					}
					value = parsed;
			}
			m.invoke(obj,value);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
}
