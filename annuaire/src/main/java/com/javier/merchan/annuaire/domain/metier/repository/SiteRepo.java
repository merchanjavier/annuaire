package com.javier.merchan.annuaire.domain.metier.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.javier.merchan.annuaire.domain.metier.entity.Site;

@Repository
public interface SiteRepo extends CrudRepository<Site, Long> {

	List<Site> findAll();
}
