package net.thrymr.organisation.management.dto;


import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddressDto {


    private Long addressId;
    private Integer floorNo;


    private String  landmark;

    private String fullAddress;

}

