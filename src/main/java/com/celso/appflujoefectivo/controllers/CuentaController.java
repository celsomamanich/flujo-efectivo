package com.celso.appflujoefectivo.controllers;

import com.celso.appflujoefectivo.dto.CuentaDto;
import com.celso.appflujoefectivo.dto.CuentaSaldoDto;
import com.celso.appflujoefectivo.models.Cuenta;
import com.celso.appflujoefectivo.services.CuentaService;
import com.celso.appflujoefectivo.utils.OperationException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Operaciones para la Cuenta")
@RestController
@RequestMapping("/api/v1/cuentas")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @ApiOperation(value = "Listar cuentas")
    @GetMapping
    public ResponseEntity<List<Cuenta>> index(){
        List<Cuenta> cuentas = this.cuentaService.listarCuenta();
        return new ResponseEntity<>(cuentas, HttpStatus.OK);
    }

    @ApiOperation(value = "Ver cuenta por id")
    @GetMapping("/{id}")
    public ResponseEntity<Cuenta> show(@PathVariable(name = "id") Long id){
        Cuenta cuenta = this.cuentaService.buscarCuenta(id);
        return new ResponseEntity<>(cuenta, HttpStatus.OK);
    }

    @ApiOperation(value = "Crear cuenta")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody CuentaDto cuentaDto){
        try{
            Cuenta cuenta = this.cuentaService.guardarCuenta(cuentaDto);
            return new ResponseEntity<>(cuenta, HttpStatus.CREATED);
        }catch(OperationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ApiOperation(value = "Consultar saldo por id")
    @GetMapping("/{id}/consultar-saldo")
    public ResponseEntity<CuentaSaldoDto> consultarSaldo(@PathVariable(name = "id") Long id){
        CuentaSaldoDto saldo = this.cuentaService.consultarSaldo(id);
        return new ResponseEntity<>(saldo, HttpStatus.OK);
    }

}
