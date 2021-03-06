from question_classifier import *
from question_parser import *
from answer_search import *

'''问答类'''
class ChatBotGraph:
    def __init__(self):
        self.classifier = QuestionClassifier()
        self.parser = QuestionPaser()
        self.searcher = AnswerSearcher()

    def chat_main(self, sent):
        answer = '您好，我是小宋金融智能助理，希望可以帮到您。如果没答上来，可联系1025149919@qq.com。祝您赚到大钱！'
        res_classify = self.classifier.classify(sent)
        print(res_classify)

        if not res_classify:
            return answer
        res_sql = self.parser.parser_main(res_classify)
        print(res_sql)

        final_answers = self.searcher.search_main(res_sql)
        if not final_answers:
            return answer
        else:
            return '\n'.join(final_answers)


if __name__ == '__main__':
    handler = ChatBotGraph()
    while 1:
        question = input('用户:')
        answer = handler.chat_main(question)
        print('小宋:', answer)
