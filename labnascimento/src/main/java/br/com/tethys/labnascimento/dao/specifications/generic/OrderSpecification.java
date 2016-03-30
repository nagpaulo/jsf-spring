package br.com.tethys.labnascimento.dao.specifications.generic;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class OrderSpecification<Entity> implements Specification<Entity> {
	
	private String field;
	private Order order;
	
	public OrderSpecification(String field, Order order) {
		super();
		this.field = field;
		this.order = order;
	}

	@Override
	public Predicate toPredicate(Root<Entity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if( Order.DESC.equals( order ) ){
			query.orderBy( cb.desc( root.get( field) ) );
		} else {
			query.orderBy( cb.asc( root.get( field) ) );
		}
		return cb.conjunction();
	}
}
