package com.celso.appflujoefectivo.services;

import com.celso.appflujoefectivo.dto.CuentaDto;
import com.celso.appflujoefectivo.dto.CuentaSaldoDto;
import com.celso.appflujoefectivo.models.Cuenta;
import com.celso.appflujoefectivo.repositories.CuentaRepository;
import com.celso.appflujoefectivo.utils.OperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    public List<Cuenta> listarCuenta(){
        List<Cuenta> cuentas = new ArrayList<>();
        cuentas = (List<Cuenta>) this.cuentaRepository.findAll();
        return cuentas;
    }

    public Cuenta buscarCuenta(Long id){
        Cuenta cuenta = new Cuenta();
        cuenta = this.cuentaRepository.findById(id).orElse(null);
        return cuenta;
    }

    public Cuenta guardarCuenta(CuentaDto cuentaDto){

        if(this.cuentaRepository.existsByNumeroCuenta(cuentaDto.getNumeroCuenta())){
            throw new OperationException("El número de cuenta ya existe en la base de datos");
        }

        Cuenta nuevaCuenta = new Cuenta();
        nuevaCuenta.setNumeroCuenta(cuentaDto.getNumeroCuenta());
        nuevaCuenta.setSaldo(cuentaDto.getSaldo());
        nuevaCuenta.setEstado("ACTIVE");
        nuevaCuenta.setMoneda(cuentaDto.getMoneda());
        this.cuentaRepository.save(nuevaCuenta);
        return nuevaCuenta;
    }

    public CuentaSaldoDto consultarSaldo(Long id){
        Cuenta cuenta = new Cuenta();
        cuenta = this.cuentaRepository.findById(id).orElse(null);

        CuentaSaldoDto cuentaSaldoDto = new CuentaSaldoDto();
        cuentaSaldoDto.setSaldo(cuenta.getSaldo());

        return cuentaSaldoDto;
    }

}
