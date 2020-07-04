import pymysql
from py2neo import Graph, Node, Relationship, NodeMatcher

def write_to_neo4j_edge(com2conlist, com2indlist, exe2comlist):
    graph = Graph('http://ismzl.com:7474', username = 'neo4j', password = 'neo4j')
    matcher = NodeMatcher(graph)

    for i in range(len(com2conlist)):
        stock_code = com2conlist[i][0]
        company = matcher.match('company', stock_code = stock_code).first()
        concept_id = com2conlist[i][1]
        concept = matcher.match('concept', id = concept_id).first()
        name = 'belong'


        # print(company)
        # print(concept)
        if company != None and concept != None:
            rel = Relationship(company, name, concept)
            graph.create(rel)


    for i in range(len(com2indlist)):
        stock_code = com2indlist[i][0]
        company = matcher.match('company', stock_code = stock_code).first()
        industry_id = com2indlist[i][1]
        industry = matcher.match('industry', id = industry_id).first()
        name = 'include'

        # print(company)
        # print(industry)

        if company != None and industry != None:

            rel = Relationship(company, name, industry)
            graph.create(rel)

    for i in range(len(exe2comlist)):
        executive_id = exe2comlist[i][0]
        executive = matcher.match('executive', id = executive_id).first()
        stock_code = exe2comlist[i][1]
        company = matcher.match('company', stock_code = stock_code).first()
        name = 'work-in'

        # print(executive)
        # print(company)

        if executive != None and company != None:

          rel = Relationship(executive, name, company)
          graph.create(rel)

def read_from_mysql_edge():
    # 创建数据库连接
    db = pymysql.connect(host='ismzl.com',
                         port=3306,
                         user='shkb',
                         passwd='shkb',
                         database='data',
                         )
    cursors = db.cursor()
    # 执行SQL
    sql = "select * from stock_concept"
    cursors.execute(sql)
    com2conlist = cursors.fetchall()

    sql = "select * from company"
    com2indlist = []
    cursors.execute(sql)
    info = cursors.fetchall()
    for i in range(len(info)):
        com2indlist.append( (info[i][1], info[i][5]) )

    sql = "select * from executive"
    cursors.execute(sql)
    info = cursors.fetchall()
    exe2comlist = []
    for i in range(len(info)):
        exe2comlist.append( (info[i][5], info[i][3]) )

    write_to_neo4j_edge(com2conlist, com2indlist, exe2comlist)
    # 关闭连接
    cursors.close()
    db.close()

if __name__ == "__main__":
    read_from_mysql_edge()