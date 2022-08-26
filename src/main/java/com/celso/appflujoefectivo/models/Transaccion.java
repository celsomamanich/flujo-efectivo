package com.celso.appflujoefectivo.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "transacciones")
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(notes = "Identificador", position = 1)
    private Long id;

    @Column(name = "operacion")
    @ApiModelProperty(notes = "Operación DEPOSITO-RETIRO", position = 2)
    private String operacion;

    @Column(name = "monto")
    @ApiModelProperty(notes = "Monto de la transacción", position = 3)
    private Double monto;

    @Column(name = "moneda")
    @ApiModelProperty(notes = "Moneda BOB-USD", position = 4)
    private String moneda;

    @Column(name = "fecha")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(notes = "Fecha de la transacción", position = 5)
    private Date fecha;

    @Column(name = "idcuenta")
    @ApiModelProperty(notes = "Identificador de la cuenta", position = 6)
    private Long idCuenta;

}
