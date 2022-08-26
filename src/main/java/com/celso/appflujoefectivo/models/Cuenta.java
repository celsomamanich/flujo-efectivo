package com.celso.appflujoefectivo.models;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cuentas")
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(notes = "Identificador", position = 1)
    private Long id;

    @Column(name = "numero_cuenta")
    @ApiModelProperty(notes = "Número de cuenta", position = 2)
    private String numeroCuenta;

    @Column(name = "saldo")
    @ApiModelProperty(notes = "Saldo total", position = 3)
    private Double saldo;

    @Column(name = "estado")
    @ApiModelProperty(notes = "Estado ACTIVE-HOLD", position = 4)
    private String estado;

    @Column(name = "moneda")
    @ApiModelProperty(notes = "Moneda BOB-USD", position = 5)
    private String moneda;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idcuenta")
    @ApiModelProperty(notes = "Transacciones de la cuenta", position = 6)
    private List<Transaccion> transacciones;

}
