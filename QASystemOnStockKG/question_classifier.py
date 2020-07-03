import os
import ahocorasick

class QuestionClassifier:
    def __init__(self):
        cur_dir = '/'.join(os.path.abspath(__file__).split('/')[:-1])

        # 特征词路径
        self.deny_path = os.path.join(cur_dir, 'dict/deny.txt')

        self.company_path = os.path.join(cur_dir, 'dict/company.txt')
        # self.province_path = os.path.join(cur_dir, 'dict/province.txt')
        self.concept_path = os.path.join(cur_dir, 'dict/concept.txt')
        self.industry_path = os.path.join(cur_dir, 'dict/industry.txt')
        self.executive_path = os.path.join(cur_dir, 'dict/executive.txt')

        # 加载特征词
        self.company_wds= [i.strip() for i in open(self.company_path) if i.strip()]
        # self.province_wds= [i.strip() for i in open(self.province_path) if i.strip()]
        self.concept_wds= [i.strip() for i in open(self.concept_path) if i.strip()]
        self.industry_wds= [i.strip() for i in open(self.industry_path) if i.strip()]
        self.executive_wds= [i.strip() for i in open(self.executive_path) if i.strip()]
        self.region_words = set(self.company_wds + self.concept_wds + self.industry_wds + self.executive_wds)     # 所有词
        self.deny_words = [i.strip() for i in open(self.deny_path) if i.strip()]

        # 构造领域actree
        self.region_tree = self.build_actree(list(self.region_words))

        # 构建词典
        self.wdtype_dict = self.build_wdtype_dict()

        # 问句疑问词
        self.company_qwds = ['股票', '只股', '股份', 'stock']
        self.concept_qwds = ['概念', '特色', '热点']
        self.industry_qwds = ['产业', '行业', '板块', '领域']
        self.executive_qwds = ['董事', '执行人', '参股人', '执行董事', '股东', '持有', '有哪些股']

        self.corp_qwds = ['公司', '投资商']
        self.province_qwds = ['所在地', '地址', '所处', '省份', '地区']
        self.code_qwds = ['代码', '代号', '股票码']
        self.business_qwds = ['业务', '服务', '商务内容']
        self.shareholder_qwds = ['股东']
        self.capital_qwds = ['资产', '资本', '多少钱']
        self.price_qwds = ['每股', '单价', '价格']
        self.openprice_qwds = ['开盘价']
        self.underwriter_qwds = ['承销商', '经销商', '包销商', '承保']

        print('model init finished ......')

        return

    '''分类主函数: 前者是要求解的，后者是先验'''
    def classify(self, question):
        data = {}
        medical_dict = self.check_medical(question)
        if not medical_dict:
            return {}
        data['args'] = medical_dict
        #收集问句当中所涉及到的实体类型
        types = []
        for type_ in medical_dict.values():
            types += type_
        question_type = 'others'

        question_types = []

        # 概念
        if self.check_words(self.concept_qwds, question) and ('company' in types):
            question_type = 'company_concept'
            question_types.append(question_type)

        if self.check_words(self.concept_qwds, question) and ('concept' in types):
            question_type = 'concept_company'
            question_types.append(question_type)

        # 产业
        if self.check_words(self.industry_qwds, question) and ('company' in types):
            question_type = 'company_industry'
            question_types.append(question_type)

        if self.check_words(self.industry_qwds, question) and ('industry' in types):
            question_type = 'industry_company'
            question_types.append(question_type)

        # 董事
        if self.check_words(self.executive_qwds, question) and ('company' in types):
            question_type = 'company_executive'
            question_types.append(question_type)

        if self.check_words(self.executive_qwds, question) and ('executive' in types):
            question_type = 'executive_company'
            question_types.append(question_type)

        # 公司
        if self.check_words(self.corp_qwds, question) and ('company' in types):
            question_type = 'company_corp'
            question_types.append(question_type)

        # 省份
        if self.check_words(self.province_qwds, question) and 'company' in types:
            question_type = 'company_province'
            question_types.append(question_type)

        # 代码
        if self.check_words(self.code_qwds, question) and 'company' in types:
            question_type = 'company_code'
            question_types.append(question_type)

        # 业务
        if self.check_words(self.business_qwds, question) and 'company' in types:
            question_type = 'company_business'
            question_types.append(question_type)

        # 股东
        if self.check_words(self.shareholder_qwds, question) and 'company' in types:
            question_type = 'company_shareholder'
            question_types.append(question_type)

        # 资产
        if self.check_words(self.capital_qwds, question) and 'company' in types :
            question_type = 'company_capital'
            question_types.append(question_type)

        # 单价
        if self.check_words(self.price_qwds, question) and 'company' in types :
            question_type = 'company_price'
            question_types.append(question_type)

        # 开盘价
        if self.check_words(self.openprice_qwds, question) and 'company' in types :
            question_type = 'company_openprice'
            question_types.append(question_type)

        # 经销商
        if self.check_words(self.underwriter_qwds, question) and 'company' in types :
            question_type = 'company_underwriter'
            question_types.append(question_type)

        # 若没有查到相关的外部查询信息，那么则将该股票的名字信息返回
        if question_types == [] and 'company' in types:
            question_types = ['company_name']

        # 若没有查到相关的外部查询信息，那么则将该概念的名字信息返回
        if question_types == [] and 'concept' in types:
            question_types = ['concept_name']

        # 将多个分类结果进行合并处理，组装成一个字典
        data['question_types'] = question_types

        return data

    '''构造词对应的类型'''
    def build_wdtype_dict(self):
        wd_dict = dict()
        for wd in self.region_words:
            wd_dict[wd] = []
            if wd in self.company_wds:
                wd_dict[wd].append('company')
            if wd in self.concept_wds:
                wd_dict[wd].append('concept')
            if wd in self.industry_wds:
                wd_dict[wd].append('industry')
            if wd in self.executive_wds:
                wd_dict[wd].append('executive')
        return wd_dict

    '''构造actree，加速过滤'''
    def build_actree(self, wordlist):
        actree = ahocorasick.Automaton()
        for index, word in enumerate(wordlist):
            actree.add_word(word, (index, word))
        actree.make_automaton()
        return actree

    '''问句过滤'''
    def check_medical(self, question):
        region_wds = []
        for i in self.region_tree.iter(question):
            wd = i[1][1]
            region_wds.append(wd)
        stop_wds = []
        for wd1 in region_wds:
            for wd2 in region_wds:
                if wd1 in wd2 and wd1 != wd2:
                    stop_wds.append(wd1)
        final_wds = [i for i in region_wds if i not in stop_wds]
        final_dict = {i:self.wdtype_dict.get(i) for i in final_wds}

        return final_dict

    '''基于特征词进行分类'''
    def check_words(self, wds, sent):
        for wd in wds:
            if wd in sent:
                return True
        return False


if __name__ == '__main__':
    handler = QuestionClassifier()
    while 1:
        question = input('input an question:')
        data = handler.classify(question)
        print(data)