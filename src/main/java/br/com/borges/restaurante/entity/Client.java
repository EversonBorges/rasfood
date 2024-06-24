package br.com.borges.restaurante.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "clients")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    private String cpf;
    private String name;
    private Integer phone;

    @Override
    public String toString() {
        return "Client{" +
                "cpf='" + cpf + '\'' +
                ", name='" + name + '\'' +
                ", phone=" + phone +
                '}';
    }
}
