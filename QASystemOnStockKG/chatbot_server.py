import socketserver
from typing import Tuple
from chatbot_graph import ChatBotGraph

from http.server import BaseHTTPRequestHandler, HTTPServer
import cgi


handler = ChatBotGraph()

class MyHandler(BaseHTTPRequestHandler):

    def __init__(self, request: bytes, client_address: Tuple[str, int], server: socketserver.BaseServer):
        super().__init__(request, client_address, server)
        # self.handler = ChatBotGraph()

    def do_GET(self):
        if(len(self.path.split('=')) == 2):
            question = self.path.split('=')[1]
            self.send_response(200)
            self.send_header('Access-Control-Allow-Origin', '*')
            self.end_headers()

            answer = handler.chat_main(question)
            self.wfile.write(answer.encode('utf-8'))
    def do_POST(self):
        self.send_header('Access-Control-Allow-Origin', '*')
        form = cgi.FieldStorage(
            fp=self.rfile,
            headers=self.headers,
            environ={'REQUEST_METHOD': 'POST',
                     'CONTENT_TYPE': self.headers['Content-Type'],
                     }
        )
        self.send_response(200)
        self.end_headers()

        question = form['question'].value  # 获取问题数据

        # question = input('用户:')
        answer = self.handler.chat_main(question)
        # print('小宋:', answer)
        self.wfile.write(answer)


def main():
    try:
        server = HTTPServer(('', 8100), MyHandler)  # 启动服务
        print('welcome to  the  server.......')
        server.serve_forever()  # 一直运行
    except KeyboardInterrupt:
        print('shuting  done server')
        server.socket.close()


if __name__ == '__main__':
    main()
