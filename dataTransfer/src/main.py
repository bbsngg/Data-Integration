import pymysql
from py2neo import Graph, Node, Relationship

companylist = []
conceptlist = []
industrylist = []
executivelist = []


def write_to_neo4j_node(companylist, conceptlist, industrylist, executivelist):
    graph = Graph('http://ismzl.com:7474', username = 'neo4j', password = 'neo4j')

    #创建节点
     #company
    for i in range(len(companylist)):
        company = companylist[i]
        node = Node("company",id = company[0], stock_code = company[1], company_name = company[2], executive = company[3], location = company[4], industry_id = company[5],
                    industry = company[6], business = company[7], shareholder = company[8], capital = company[9], business_address = company[10], circulation = company[11], price = company[12],
                    pe = company[13], fundraising = company[14], opening_price = company[15], wining_rate = company[16], actual_fundraising = company[17], underwriter = company[18],
                    stock_name = company[19])
        graph.create(node)
     #concept
    for i in range(len(conceptlist)):
        concept = conceptlist[i]
        node = Node("concept", name = concept[0], id = concept[1])
        graph.create(node)
     #executive
    for i in range(len(executivelist)):
        executive = executivelist[i]
        node = Node("executive", name = executive[0], sex = executive[1], age = executive[2], stock_code = executive[3], position = executive[4], id = executive[5])
        graph.create(node)

     #industry
    for i in range(len(industrylist)):
        industry = industrylist[i]
        node = Node("industry", name = industry[0], id = industry[1])
        graph.create(node)

def read_from_mysql_node():
    # 创建数据库连接
    db = pymysql.connect(host='ismzl.com',
                         port=3306,
                         user='shkb',
                         passwd='shkb',
                         database='data',
                         )
    cursors = db.cursor()
    # 执行SQL
    sql = "select * from company"
    cursors.execute(sql)
    companylist = cursors.fetchall()

    sql = "select * from concept"
    cursors.execute(sql)
    conceptlist = cursors.fetchall()

    sql = "select * from industry"
    cursors.execute(sql)
    industrylist = cursors.fetchall()

    sql = "select * from executive"
    cursors.execute(sql)
    executivelist = cursors.fetchall()

    write_to_neo4j_node(companylist, conceptlist, industrylist, executivelist)


    # 关闭连接
    cursors.close()
    db.close()




if __name__ == "__main__":
    read_from_mysql_node()
