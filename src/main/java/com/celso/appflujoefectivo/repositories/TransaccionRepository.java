package com.celso.appflujoefectivo.repositories;

import com.celso.appflujoefectivo.models.Transaccion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransaccionRepository extends CrudRepository<Transaccion, Long> {

}
