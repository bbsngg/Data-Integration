from py2neo import Graph


class AnswerSearcher:
    def __init__(self):
        self.g = Graph(
            host="ismzl.com",
            http_port=7474,
            user="neo4j",
            password="neo4j")

        self.num_limit = 200  # todo?

    '''执行cypher查询，并返回相应结果'''

    def search_main(self, sqls):
        final_answers = []
        for sql_ in sqls:
            question_type = sql_['question_type']
            queries = sql_['sql']
            answers = []
            for query in queries:
                ress = self.g.run(query).data()
                answers += ress
            print(answers)
            final_answer = self.answer_prettify(question_type, answers)
            if final_answer:
                final_answers.append(final_answer)
        return final_answers

    '''根据对应的qustion_type，调用相应的回复模板'''

    def answer_prettify(self, question_type, answers):
        final_answer = []

        if not answers:
            return ''

        if question_type == 'company_concept':
            desc = [i['n.name'] for i in answers]
            subject = answers[0]['m.stock_name']
            final_answer = '{0}所属的概念包括：{1}'.format(subject, '；'.join(list(set(desc))[:self.num_limit]))

        elif question_type == 'concept_company':
            desc = [i['m.stock_name'] for i in answers]
            subject = answers[0]['n.name']
            final_answer = '概念{0}包含的股票有：{1}'.format(subject, '；'.join(list(set(desc))[:self.num_limit]))

        elif question_type == 'company_industry':
            desc = [i['n.name'] for i in answers]
            subject = answers[0]['m.stock_name']
            final_answer = '{0}所属的产业为：{1}'.format(subject, '；'.join(list(set(desc))[:self.num_limit]))

        elif question_type == 'industry_company':
            desc = [i['m.stock_name'] for i in answers]
            subject = answers[0]['n.name']
            final_answer = '产业{0}包含的股票有：{1}'.format(subject, '；'.join(list(set(desc))[:self.num_limit]))

        elif question_type == 'company_executive':
            desc = [i['m.name'] for i in answers]
            subject = answers[0]['n.stock_name']
            final_answer = '股票{0}的董事成员包括：{1}'.format(subject, '；'.join(list(set(desc))[:self.num_limit]))

        elif question_type == 'executive_company':
            desc = [i['n.stock_name'] for i in answers]
            subject = answers[0]['m.name']
            final_answer = '{0}作为董事成员的股票有：{1}'.format(subject, '；'.join(list(set(desc))[:self.num_limit]))

        elif question_type == 'company_corp':
            desc = [i['m.company_name'] for i in answers]
            subject = answers[0]['m.stock_name']
            final_answer = '股票{0}的公司为：{1}'.format(subject, '；'.join(list(set(desc))[:self.num_limit]))

        elif question_type == 'company_province':
            desc = [i['m.location'] for i in answers]
            subject = answers[0]['m.stock_name']
            final_answer = '股票{0}所在的省份为：{1}'.format(subject, '；'.join(list(set(desc))[:self.num_limit]))

        elif question_type == 'company_code':
            desc = [str(i['m.stock_code']) for i in answers]
            subject = answers[0]['m.stock_name']
            final_answer = '股票{0}的股票代码为：{1}'.format(subject, '；'.join(list(set(desc))[:self.num_limit]))

        elif question_type == 'company_business':
            desc = [i['m.business'] for i in answers]
            subject = answers[0]['m.stock_name']
            final_answer = '股票{0}的主要业务内容为：{1}'.format(subject, '；'.join(list(set(desc))[:self.num_limit]))

        elif question_type == 'company_shareholder':
            desc = [i['m.shareholder'] for i in answers]
            subject = answers[0]['m.stock_name']
            final_answer = '股票{0}的主要股东为：{1}'.format(subject, '；'.join(list(set(desc))[:self.num_limit]))

        elif question_type == 'company_capital':
            desc = [i['m.capital'] for i in answers]
            subject = answers[0]['m.stock_name']
            final_answer = '股票{0}的资产有：{1}'.format(subject, '；'.join(list(set(desc))[:self.num_limit]))

        elif question_type == 'company_price':
            desc = [i['m.price'] for i in answers]
            subject = answers[0]['m.stock_name']
            final_answer = '股票{0}的每股价格为：{1}'.format(subject, '；'.join(list(set(desc))[:self.num_limit]))

        elif question_type == 'company_openprice':
            desc = [i['m.opening_price'] for i in answers]
            subject = answers[0]['m.stock_name']
            final_answer = '股票{0}的开盘价为：{1}'.format(subject, '；'.join(list(set(desc))[:self.num_limit]))

        elif question_type == 'company_underwriter':
            desc = [i['m.underwriter'] for i in answers]
            subject = answers[0]['m.stock_name']
            final_answer = '股票{0}的经销商为：{1}'.format(subject, '；'.join(list(set(desc))[:self.num_limit]))

        return final_answer


if __name__ == '__main__':
    searcher = AnswerSearcher()
