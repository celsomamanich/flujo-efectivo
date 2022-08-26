package com.celso.appflujoefectivo.services;

import com.celso.appflujoefectivo.dto.CuentaDto;
import com.celso.appflujoefectivo.dto.CuentaSaldoDto;
import com.celso.appflujoefectivo.models.Cuenta;
import com.celso.appflujoefectivo.models.Transaccion;
import com.celso.appflujoefectivo.repositories.CuentaRepository;
import com.celso.appflujoefectivo.repositories.TransaccionRepository;
import com.celso.appflujoefectivo.utils.OperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TransaccionService {

    @Autowired
    private TransaccionRepository transaccionRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    public List<Transaccion> listarTransaccion(){
        List<Transaccion> transacciones = new ArrayList<>();
        transacciones = (List<Transaccion>) this.transaccionRepository.findAll();
        return transacciones;
    }

    public Transaccion buscarTransaccion(Long id){
        Transaccion transaccion = new Transaccion();
        transaccion = this.transaccionRepository.findById(id).orElse(null);
        return transaccion;
    }

    public Transaccion guardarTransaccion(Transaccion transaccion){
        Cuenta cuentaEncontrada = this.cuentaRepository.findById(transaccion.getIdCuenta()).orElse(null);
        Double saldo = 0d;

        if(!cuentaEncontrada.getMoneda().equals(transaccion.getMoneda())){
            throw new OperationException("No se puede realizar la transacción porque las monedas son diferentes");
        }

        if(transaccion.getOperacion().equals("DEPOSITO")){
            if(cuentaEncontrada.getEstado().equals("HOLD")){
                if(transaccion.getMonto() >= (-1)*cuentaEncontrada.getSaldo()){
                    cuentaEncontrada.setEstado("ACTIVE");
                }
            }
            saldo = cuentaEncontrada.getSaldo() + transaccion.getMonto();
            cuentaEncontrada.setSaldo(saldo);
        }

        if(transaccion.getOperacion().equals("RETIRO")){
            if(cuentaEncontrada.getEstado().equals("ACTIVE")){
                if(transaccion.getMonto() > cuentaEncontrada.getSaldo()){
                    cuentaEncontrada.setEstado("HOLD");
                }
                saldo = cuentaEncontrada.getSaldo() - transaccion.getMonto();
                cuentaEncontrada.setSaldo(saldo);
            }
            else{
                throw new OperationException("No se puede realizar la transacción porque su cuenta está en estado HOLD");
            }
        }

        Transaccion nuevaTransaccion = new Transaccion();
        nuevaTransaccion.setOperacion(transaccion.getOperacion());
        nuevaTransaccion.setMonto(transaccion.getMonto());
        nuevaTransaccion.setMoneda(transaccion.getMoneda());
        nuevaTransaccion.setFecha(new Date());
        nuevaTransaccion.setIdCuenta(transaccion.getIdCuenta());
        this.transaccionRepository.save(nuevaTransaccion);

        this.cuentaRepository.save(cuentaEncontrada);

        return nuevaTransaccion;
    }

}
