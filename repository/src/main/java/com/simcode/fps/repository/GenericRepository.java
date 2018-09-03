package com.simcode.fps.repository;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.simcode.fps.repository.model.GenericEntity;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public interface GenericRepository<T extends GenericEntity> extends CrudRepository<T, Long>{
}
