
class QuestionPaser:

    '''构建实体节点'''
    def build_entitydict(self, args):
        entity_dict = {}
        for arg, types in args.items():
            for type in types:
                if type not in entity_dict:
                    entity_dict[type] = [arg]
                else:
                    entity_dict[type].append(arg)

        return entity_dict

    '''解析主函数'''
    def parser_main(self, res_classify):
        args = res_classify['args']     # {'黄山Ｂ股': ['company']}...
        entity_dict = self.build_entitydict(args)

        question_types = res_classify['question_types']     # ['company_business']...
        sqls = []

        for question_type in question_types:
            sql_ = {}
            sql_['question_type'] = question_type

            sql = []
            if question_type == 'company_concept':
                sql = self.sql_transfer(question_type, entity_dict.get('company'))

            elif question_type == 'concept_company':
                sql = self.sql_transfer(question_type, entity_dict.get('concept'))

            elif question_type == 'company_industry':
                sql = self.sql_transfer(question_type, entity_dict.get('company'))

            elif question_type == 'industry_company':
                sql = self.sql_transfer(question_type, entity_dict.get('industry'))

            elif question_type == 'company_executive':
                sql = self.sql_transfer(question_type, entity_dict.get('company'))

            elif question_type == 'executive_company':
                sql = self.sql_transfer(question_type, entity_dict.get('executive'))

            elif question_type == 'company_corp':
                sql = self.sql_transfer(question_type, entity_dict.get('company'))

            elif question_type == 'company_province':
                sql = self.sql_transfer(question_type, entity_dict.get('company'))

            elif question_type == 'company_code':
                sql = self.sql_transfer(question_type, entity_dict.get('company'))

            elif question_type == 'company_business':
                sql = self.sql_transfer(question_type, entity_dict.get('company'))

            elif question_type == 'company_shareholder':
                sql = self.sql_transfer(question_type, entity_dict.get('company'))

            elif question_type == 'company_capital':
                sql = self.sql_transfer(question_type, entity_dict.get('company'))

            elif question_type == 'company_price':
                sql = self.sql_transfer(question_type, entity_dict.get('company'))

            elif question_type == 'company_openprice':
                sql = self.sql_transfer(question_type, entity_dict.get('company'))

            elif question_type == 'company_underwriter':
                sql = self.sql_transfer(question_type, entity_dict.get('company'))

            elif question_type == 'company_risk':
                sql = self.sql_transfer(question_type, entity_dict.get('company'))

            elif question_type == 'executive_risk':
                sql = self.sql_transfer(question_type, entity_dict.get('executive'))

            if sql:
                sql_['sql'] = sql

                sqls.append(sql_)

        return sqls

    '''针对不同的问题，分开进行处理'''
    def sql_transfer(self, question_type, entities):
        if not entities:
            return []

        # 查询语句
        sql = []
        # 股票公司
        if question_type == 'company_corp':
            sql = ["MATCH (m:company) where m.stock_name = '{0}' return m.stock_name, m.company_name".format(i) for i in entities]

        # 股票省份
        elif question_type == 'company_province':
            sql = ["MATCH (m:company) where m.stock_name = '{0}' return m.stock_name, m.location".format(i) for i in entities]

        # 股票代码
        elif question_type == 'company_code':
            sql = ["MATCH (m:company) where m.stock_name = '{0}' return m.stock_name, m.stock_code".format(i) for i in entities]

        # 股票公司服务
        elif question_type == 'company_business':
            sql = ["MATCH (m:company) where m.stock_name = '{0}' return m.stock_name, m.business".format(i) for i in entities]

        # 股票股东
        elif question_type == 'company_shareholder':
            sql = ["MATCH (m:company) where m.stock_name = '{0}' return m.stock_name, m.shareholder".format(i) for i in entities]

        # 股票资产
        elif question_type == 'company_capital':
            sql = ["MATCH (m:company) where m.stock_name = '{0}' return m.stock_name, m.capital".format(i) for i in entities]

        # 股票单价
        elif question_type == 'company_price':
            sql = ["MATCH (m:company) where m.stock_name = '{0}' return m.stock_name, m.price".format(i) for i in entities]

        # 股票开盘价
        elif question_type == 'company_openprice':
            sql = ["MATCH (m:company) where m.stock_name = '{0}' return m.stock_name, m.opening_price".format(i) for i in entities]

        # 股票承销商
        elif question_type == 'company_underwriter':
            sql = ["MATCH (m:company) where m.stock_name = '{0}' return m.stock_name, m.underwriter".format(i) for i in entities]


        # 股票概念
        elif question_type == 'company_concept':
            sql = ["MATCH (m:company)-[r:belong]->(n:concept) where m.stock_name = '{0}' return m.stock_name, n.name".format(i) for i in entities]

        # 已知概念找股票
        elif question_type == 'concept_company':
            sql = ["MATCH (m:company)-[r:belong]->(n:concept) where n.name = '{0}' return m.stock_name, n.name".format(i) for i in entities]

        # 股票产业
        elif question_type == 'company_industry':
            sql = ["MATCH (m:company)-[r:include]->(n:industry) where m.stock_name = '{0}' return m.stock_name, n.name".format(i) for i in entities]

        # 已知产业找股票
        elif question_type == 'industry_company':
            sql = ["MATCH (m:company)-[r:include]->(n:industry) where n.name = '{0}' return m.stock_name, n.name".format(i) for i in entities]

        # 股票董事
        elif question_type == 'company_executive':
            sql = ["MATCH (m:executive)-[r:`work-in`]->(n:company) where n.stock_name = '{0}' return n.stock_name, m.name".format(i) for i in entities]

        # 已知董事找股票
        elif question_type == 'executive_company':
            sql = ["MATCH (m:executive)-[r:`work-in`]->(n:company) where m.name = '{0}' return m.name, n.stock_name".format(i) for i in entities]


        # 股票风险评估
        elif question_type == 'company_risk':
            sql = ["match (coa:concept)<-[rco:belong]-(ca:company)<-[r0:`work-in`]-(ea:executive), (ina:industry)<-[rin:include]-(ca:company)  where ca.stock_name='{0}' with ca,r0,ea,coa,rco,ina,rin " \
                   "match (cb:company)<-[r1:`work-in`]-(eb:executive) where eb.name=ea.name and eb.age=ea.age and eb.sex=ea.sex " \
                   "return cb.stock_name,cb.capital,cb.business,ca.underwriter,ca.price,ca.opening_price,ca.stock_name,ca.stock_code,ca.company_name,ca.business,ca.capital,ea.name,coa.name,ina.name".format(i) for i in entities]

        # 董事风险评估
        elif question_type == 'executive_risk':
            sql = ["match (ea0:executive) where ea0.name='{0}' with ea0 " \
                   "match (coa:concept)<-[rco:belong]-(ca:company)<-[r0:`work-in`]-(ea:executive), (ina:industry)<-[rin:include]-(ca:company) where ea.name=ea0.name and ea.age=ea0.age and ea.sex=ea0.sex with ca,ea,coa,ina,rco,r0,rin " \
                   "match (ca:company)<-[r1:`work-in`]-(eb:executive) with ca,ea,coa,ina,rco,r0,rin,r1,eb " \
                   "match (ec:executive)-[r2:`work-in`]->(cb:company) where ec.name=eb.name and ec.age=eb.age and ec.sex=eb.sex " \
                   "return cb.stock_name,cb.capital,cb.business,ca.underwriter,ca.price,ca.opening_price,ca.stock_name,ca.stock_code,ca.company_name,ca.business,ca.capital,ea.name,ea.age,ea.sex,coa.name,ina.name".format(i) for i in entities]

        return sql

if __name__ == '__main__':
    handler = QuestionPaser()
    print(handler.parser_main(eval("{'args': {'黄山Ｂ股': ['company']}, 'question_types': ['company_corp']}")))

