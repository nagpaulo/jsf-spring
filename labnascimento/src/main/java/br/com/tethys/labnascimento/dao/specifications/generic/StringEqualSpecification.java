package br.com.tethys.labnascimento.dao.specifications.generic;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;


public class StringEqualSpecification<Entity> implements Specification<Entity>{

	private String field;
	private String value;
	private SpecMode specMode;
	
	public StringEqualSpecification(String field, String value) {
		this( field , value , SpecMode.TUDO_SE_NULO_OU_VAZIO);
	}
	
	public StringEqualSpecification(String field, String value, SpecMode specMode) {
		super();
		this.field = field;
		this.value = value;
		this.specMode = specMode;
	}

	@Override
	public Predicate toPredicate(Root<Entity> root, CriteriaQuery<?> query,
			CriteriaBuilder cb) {
		if(StringUtils.hasText(value)){
			Path<Object> path;
			String[] split = field.split(".");
			if(split.length > 1){
				path = root.get(split[0]);
				for (int i = 1 ; i < split.length ; i++) {
					path = path.get(split[i]);
				}
			}else{
				path = root.get(field);
			}
			return cb.equal( path , value );
		}
		if(SpecMode.TUDO_SE_NULO_OU_VAZIO.equals(specMode)){
			return cb.conjunction();
		}else{
			return cb.disjunction();	
		}
	}

}
