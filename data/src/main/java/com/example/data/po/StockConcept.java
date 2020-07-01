package com.example.data.po;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity
@Table(name = "stock_concept")
@Data
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class StockConcept implements Serializable {

  @Id
  @Column(name = "stock_code")
  private Long stockCode;

  @Id
  @Column(name = "concept_id")
  private Long conceptId;
}
