package com.celso.appflujoefectivo.controllers;

import com.celso.appflujoefectivo.models.Transaccion;
import com.celso.appflujoefectivo.services.TransaccionService;
import com.celso.appflujoefectivo.utils.OperationException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Operaciones para la Transacción")
@RestController
@RequestMapping("/api/v1/transacciones")
public class TransaccionController {

    @Autowired
    private TransaccionService transaccionService;

    @ApiOperation(value = "Listar transacciones")
    @GetMapping
    public ResponseEntity<List<Transaccion>> index(){
        List<Transaccion> transacciones = this.transaccionService.listarTransaccion();
        return new ResponseEntity<>(transacciones, HttpStatus.OK);
    }

    @ApiOperation(value = "Ver transacción por id")
    @GetMapping("/{id}")
    public ResponseEntity<Transaccion> show(@PathVariable(name = "id") Long id){
        Transaccion transaccion = this.transaccionService.buscarTransaccion(id);
        return new ResponseEntity<>(transaccion, HttpStatus.OK);
    }

    @ApiOperation(value = "Realizar transacción")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Transaccion transaccion){
        try{
            Transaccion newTransaccion = this.transaccionService.guardarTransaccion(transaccion);
            return new ResponseEntity<>(newTransaccion, HttpStatus.CREATED);
        }catch(OperationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
