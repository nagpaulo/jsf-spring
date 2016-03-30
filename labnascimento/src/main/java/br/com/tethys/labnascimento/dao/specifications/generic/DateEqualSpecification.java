package br.com.tethys.labnascimento.dao.specifications.generic;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class DateEqualSpecification<Entity> implements Specification<Entity> {
	
	private String field;
	private Date value;
	private SpecMode specMode;
	
	public DateEqualSpecification(String field, Date value) {
		this( field , value , SpecMode.TUDO_SE_NULO_OU_VAZIO);
	}
	
	public DateEqualSpecification(String field, Date value, SpecMode specMode) {
		super();
		this.field = field;
		this.value = value;
		this.specMode = specMode;
	}

	@Override
	public Predicate toPredicate(Root<Entity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if(value != null){
			return cb.equal( root.get(field) , value );
		}
		if(SpecMode.TUDO_SE_NULO_OU_VAZIO.equals(specMode)){
			return cb.conjunction();
		}else{
			return cb.disjunction();	
		}
	}

}
