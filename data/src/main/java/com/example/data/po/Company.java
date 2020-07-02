package com.example.data.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Entity
@Table(name = "company")
@Data
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Company {

  /**
   * 主键id
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * 股票代码
   */
  private Long stockCode;

  /**
   * 公司名称
   */
  private String companyName;

  /**
   * 董事长
   */
  private String executive;

  /**
   * 公司地址
   */
  private String location;

  /**
   * 行业id
   */
  private Long industryId;

  /**
   * 所在行业
   */
  private String industry;

  /**
   * 主营业务
   */
  private String business;

  /**
   * 股东
   */
  private String shareholder;

  /**
   * 注册资本
   */
  private String capital;

  /**
   * 办公地点
   */
  private String businessAddress;

  /**
   * 发行数量
   */
  private String circulation;

  /**
   * 发行价格
   */
  private String price;

  /**
   * 市盈率
   */
  private String pe;

  /**
   * 预计募资
   */
  private String fundraising;

  /**
   * 开盘价
   */
  private String openingPrice;

  /**
   * 中签率
   */
  private String winingRate;

  /**
   * 实际募资
   */
  private String actualFundraising;

  /**
   * 主承销商
   */
  private String underwriter;

  /**
   * 股票名称
   */
  private String stockName;


}
