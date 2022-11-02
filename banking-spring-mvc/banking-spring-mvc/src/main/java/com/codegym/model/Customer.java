package com.codegym.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "full_name", nullable = false)
    @NotEmpty(message = "Vui lòng nhập họ tên đầy đủ")
    @Size(min = 5, max = 100, message = "Họ tên có độ dài nằm trong khoảng 5-100 ký tự")
    private String fullName;

    @Column(unique = true, nullable = false)
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Email không đúng định dạng (VD: codegym@gmail.com)")
    private String email;

    @Column(unique = true, nullable = false)
    @Pattern(regexp = "^0[0-9]{9}$", message = "Vui lòng nhập đúng định dạng số điện thoại")
    private String phone;

    private String address;

    @Column(precision = 12, scale = 0, nullable = false)
    private BigDecimal balance;

    @OneToMany
    private List<Deposit> deposits;

    @OneToMany
    private List<Withdraw> withdraws;
}
