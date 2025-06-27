package com.ritesh.demo.htmlfilewoker.model;

import java.sql.Timestamp;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class URL {
    
    String id;

    String url;


    String ContentType;


    Integer TimesProcessed;


    Timestamp LastProcessed;


    Timestamp CreatedDate;
    
}
