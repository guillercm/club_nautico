package com.joyfe.daw.des.util;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;


public class Utilities {
	
	private static EntityManager em;
	
	public static EntityManager getEntityManager() {
		return em;
	}

	public static void setEntityManager(EntityManager em) {
		Utilities.em = em;
	}

	public static <T> T find(Class<T> classType, boolean allConditionsTrue, String ...columns) {
		try {
			Entity entity = classType.getAnnotation(Entity.class);
			if (entity == null) {
				return null;
			}
			String nameTable = entity.name();
			String nameTableFirstLeter = nameTable.substring(0, 1).toLowerCase();
			String sql = "SELECT " + nameTableFirstLeter + " FROM " + nameTable + " " + nameTableFirstLeter;
			if (columns.length > 0) {
				sql += " WHERE ";
				for (int i = 0; i < columns.length; i+=2) {
					String nameCol = columns[i];
					String valueCol = columns[i + 1];
					String negativeComparation = "";
					boolean valueIsString = valueCol instanceof String;
					if (nameCol.startsWith("!")) {
						columns[i] = nameCol.substring(1);
						nameCol = columns[i];
						negativeComparation = valueIsString ? "NOT" : "!";
					}
					String comparation = negativeComparation + (valueIsString ? " LIKE " : "= ");
					sql +=  nameTableFirstLeter + "." + nameCol + " " + comparation + ":" + nameCol;
					if ((i + 2) != columns.length) {
						sql += allConditionsTrue ? " AND " : " OR ";
					}
				}
				//System.out.println("\n\n"+sql+"\n\n");
				TypedQuery<TypedQuery> query = em.createQuery(sql, TypedQuery.class);
				for (int i = 0; i < columns.length; i += 2) {
					String nameCol = columns[i];
					String valueCol = columns[i+1];
					query.setParameter(nameCol, valueCol);
				}
				System.out.println("\n\n"+query+"\n\n");
				return (T) query.getResultList().get(0);
			}
		} catch (Exception e) {e.printStackTrace();}
		return null;
	}
	
}
