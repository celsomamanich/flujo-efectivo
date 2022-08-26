package com.celso.appflujoefectivo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CuentaDto {

    private String numeroCuenta;
    private Double saldo;
    private String moneda;

}
