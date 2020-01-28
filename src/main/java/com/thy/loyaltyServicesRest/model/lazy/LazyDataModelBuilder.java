package com.thy.loyaltyServicesRest.model.lazy;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;


@Data
@NoArgsConstructor
public class LazyDataModelBuilder<TEntity>
{
	private int start;
	private int end;
	private String sortField;
	private Long totalResultCount;
	private QuerySortOrder order;
	private Class<TEntity> clazz;
	private Map<String, Object> filters;
	private List<Object[]> customList;
	private List<TEntity> entityList;
	
	public LazyDataModelBuilder(Class<TEntity> clazz, int start, int end, String field, QuerySortOrder order, Map<String, Object> filters)
	{
		this.clazz = clazz;
		this.start = start;
		this.end = end;
		this.sortField = field;
		this.order = order;
		this.filters = filters;
	}
}
