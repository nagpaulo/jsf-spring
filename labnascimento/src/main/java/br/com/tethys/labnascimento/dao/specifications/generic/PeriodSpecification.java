package br.com.tethys.labnascimento.dao.specifications.generic;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class PeriodSpecification<Entity> implements Specification<Entity> {
	
	private String field;
	private Date value1;
	private Date value2;
	
	public PeriodSpecification(String field, Date value1, Date value2) {
		super();
		this.field = field;
		this.value1 = value1;
		this.value2 = value2;
	}

	@Override
	public Predicate toPredicate(Root<Entity> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		if(value1 != null && value2 != null){
			return cb.between(  root.<Date>get(field) , value1 , value2 );
			//return cb.and( cb.ge( root.<Number>get(field) , value1 ) , cb.le( root.<Number>get(field) , value2 ) );
		}

		return cb.conjunction();
	}

}
