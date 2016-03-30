package br.com.tethys.labnascimento.dao.specifications.generic;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class LikeSpecification<T> implements Specification<T> {

	
	private String fieldValue;
	private String fieldName;
	private SpecMode specMode;
	
	public LikeSpecification(String fieldName, String fieldValue) {
		this( fieldName , fieldValue , SpecMode.TUDO_SE_NULO_OU_VAZIO );
	}
	
	public LikeSpecification(String fieldName, String fieldValue , SpecMode specMode) {
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
		this.specMode = specMode;
	}

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery,
			CriteriaBuilder cb) {
		if (StringUtils.hasText(fieldValue)) {
			return cb.like(cb.upper(root.<String> get(fieldName)), "%" + fieldValue.toUpperCase() + "%");
		}
		if(SpecMode.TUDO_SE_NULO_OU_VAZIO.equals(specMode)){
			return cb.conjunction();
		}else{
			return cb.disjunction();	
		}
	}
}