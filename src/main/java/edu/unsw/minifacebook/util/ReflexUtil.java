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
	 * ��ѯSQLƴ��
	 * 
	 * @param obj
	 * @return
	 */
	public static String getSelects(Object obj) {
		String sql = "select * from ";
		// if (null!=obj){
		// //��ȡʵ������Ʋ�Сдʵ����
		// sql=sql+obj.getClass().getName().substring(obj.getClass().getName().lastIndexOf(".")+1).toLowerCase();
		// }
		sql += "EntityStore ";
		String classname = obj.getClass().getName();
		Field[] fe = obj.getClass().getDeclaredFields();// ͨ�������ȡ�����������е�����
		try {
			if (fe.length >= 0) {
				sql = sql + " where 1=1 ";
				for (int j = 0; j < fe.length; j++) {
					String name = fe[j].getName();
					//name = name.substring(0, 1).toUpperCase() + name.substring(1);// �����Ե�����ĸ��д�����õ�get������������д

					
					if(name.equals("id")) {
						Method m = obj.getClass().getMethod("get" + name);
						Integer id = (Integer) m.invoke(obj);
						sql+= " and subject = " + classname.substring(0, 1).toUpperCase() +id;
						continue;
					}
					
					String type = fe[j].getGenericType().toString();// ��ȡ����ֵ������
					// �ж�����ֵ����ʲô��Ȼ��������ͻ�ȡ���е�ֵ ������Ŀ�����������������ж�
					// String ����
					if (type.equals("class java.lang.String")) {
						Method m = obj.getClass().getMethod("get" + name);
						String value = (String) m.invoke(obj);// ��ȡ����ֵ
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
	 * �޸�SQLƴ��
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
				Field[] fields = obj.getClass().getDeclaredFields();// ��ȡʵ�����������
				String trems = " where 1=1 ";
				for (int j = 0; j < fields.length; j++) {
					String name = fields[j].getName();// ��ȡ������
					name = name.substring(0, 1).toUpperCase() + name.substring(1);// ����ĸ��д ͨ��get��ȡֵ��������д
					String type = fields[j].getGenericType().toString();// ��ȡ����ֵ������
					// �޸����������������˴�Ĭ��Ϊ����IDֵ һ��ID��Ψһ��

					// �ж�����ֵ��Ȼ���������ֵƴ��sql
					if (type.equals("class java.lang.String")) {
						Method m = obj.getClass().getMethod("get" + name);
						String value = (String) m.invoke(obj);
						if (null != value && !"".equals(value)) {
							// �жϲ����Ƿ�Ϊ�����ĵ�һ��
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
							// �������ΪID �˴�Ӧ��ƴ���������
							trems = trems + " and id='" + value + "'";
							continue;
						} else {
							if (null != value && !"".equals(value)) {
								// �жϲ����Ƿ�Ϊ�����ĵ�һ��
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
							// �жϲ����Ƿ�Ϊ�����ĵ�һ��
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
							// �жϲ����Ƿ�Ϊ�����ĵ�һ��
							if (" set ".equals(parameter) || " set " == parameter) {
								parameter = parameter + fields[j].getName() + "='" + value;
							} else {
								parameter = parameter + "," + fields[j].getName() + "='" + value;
							}
						}
					}
				} // ѭ��������ƴ��SQL
				if (" set " == parameter || " set ".equals(parameter)) { // ����Ϊ�գ���ִ��
					return null;
				} else if (" where 1=1 " == trems || " where 1=1 ".equals(trems)) { // �������Ϊ��,��ִ�У������Ҫ�޸����������(��Ŀ��Ӧ�ܾ������޸����е����)
					return null;
				} else {
					sql = sql + parameter + trems;
				}
			} else {
				System.out.println("---------------������Ϣ----------------");
				System.out.println("����ʵ��" + obj.getClass().getName() + "Ϊ��");
				System.out.println("---------------������Ϣ����----------------");
				return null;
			}
		} catch (Exception e) {
			System.out.println("----------�쳣��Ϣ--------------");
			e.printStackTrace();
			System.out.println("----------�쳣��Ϣ����--------------");
			return null;
		}
		return sql;
	}

	/**
	 * ɾ��SQLƴ��
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
				Field[] fields = obj.getClass().getDeclaredFields();// ��ȡ���е�����
				sql += " where 1=1 and subject = '"; // �������
				for (int j = 0; j < fields.length; j++) {
					String name = fields[j].getName();
					name = name.substring(0, 1).toUpperCase() + name.substring(1);// ����ĸ��д������get������ȡ����ֵ
					String type = fields[j].getGenericType().toString();// ��ȡ���ԵĲ���������
					if(name.endsWith("id") || name.endsWith("Id")) {
						Method m = obj.getClass().getDeclaredMethod("get" + name);
						Integer value = (Integer) m.invoke(obj);
						sql += classname + value.toString();
					}

				}
				sql += "'";
			} else {
				System.out.println("-----------------ʵ��Ϊ��-----------------");
				return null;
			}
		} catch (Exception e) {
			System.out.println("-----------------�쳣��Ϣ-----------------");
			e.printStackTrace();
			System.out.println("-----------------�쳣��Ϣ����-----------------");
			return null;
		}
		return sql;
	}

	/**
	 * ����SQLƴ��
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

				Field[] fields = obj.getClass().getDeclaredFields();// ��ȡ���е�����

				for (int j = 0; j < fields.length; j++) {
					String sql = "insert into entitystore (subject,predicate,object) values ('";
					sql += subject + "', '";
					String name = fields[j].getName();
					name = name.substring(0, 1).toUpperCase() + name.substring(1); // ��ȡget������ȡֵ
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
				System.out.println("-----------------ʵ��Ϊ��-----------------");
				return null;
			}

		} catch (Exception e) {
			System.out.println("-----------------�쳣��Ϣ-----------------");
			e.printStackTrace();
			System.out.println("-----------------�쳣��Ϣ����-----------------");
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
