package com.project.shopapp.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.shopapp.models.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse extends  BaseResponse {
    private Long id;
    @ManyToOne
    @JsonProperty( "user_id")
    private User user;
    @JsonProperty( "full_name")
    private String fullName;
    @JsonProperty( "email")
    private String email;
    @JsonProperty( "phone_number")
    private String phoneNumber;
    @JsonProperty( "address")
    private String address;

    private String note;
    @JsonProperty( "order_date")
    private LocalDateTime orderDate;

    private String status;
    @JsonProperty( "total_money")
    private Float totalMoney;
    @JsonProperty( "shipping_method")
    private String shippingMethod;
    @JsonProperty( "shipping_address")
    private String shippingAddress;
    @JsonProperty( "shipping_date")
    private Date shippingDate;
    @JsonProperty( "tracking_number")
    private String trackingNumber;
    @JsonProperty( "payment_method")
    private String paymentMethod;
//    @JsonProperty( "payment_status")
//    private String paymentStatus;
//    @JsonProperty( "payment_date")
//    private Date paymentDate;// Khi thanh toán mới cập nhật
    private Boolean active;// thuộc về admin

}
