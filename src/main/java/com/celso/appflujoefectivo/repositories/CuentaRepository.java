package com.celso.appflujoefectivo.repositories;

import com.celso.appflujoefectivo.models.Cuenta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends CrudRepository<Cuenta, Long> {

    public boolean existsByNumeroCuenta(String numeroCuenta);

}
