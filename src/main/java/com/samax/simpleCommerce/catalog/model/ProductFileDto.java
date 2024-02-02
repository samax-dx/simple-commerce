package com.samax.simpleCommerce.catalog.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductFileDto {

    String name;

    String description;

    MultipartFile image;

}
