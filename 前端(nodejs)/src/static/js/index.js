//全局变量
var stockCodeList = [] //股票代码list
var conceptList = [] //概念股list
var industryList = [] //产业list

window.onload = function() {
    //发送请求获取三种维度所有的标识
    getBaseData()
    //设置说明card的内容
    setCardBodyContent()
    //设置下拉菜单的监听
    setDropdownItemListener()
    //设置查询按钮监听
    setQueryBtnListener()
}

function getBaseData() {
    $.ajax({
        type: "GET",
        url: 'http://ismzl.com:8300/company/getAllStockCode',
        async: false,
        success: function (res) {
            if (!res.success) {
                alert("查询所有股票代码出错: " + res.message)
            } else {
                stockCodeList = res.content
            }
        }
    });



    $.ajax({
        type: "GET",
        url: 'http://ismzl.com:8300/concept/getAllName',
        async: false,
        success: function (res) {
            if (!res.success) {
                alert("查询所有概念股出错: " + res.message)
            } else {
                conceptList = res.content
            }
        }
    });

    $.ajax({
        type: "GET",
        url: 'http://ismzl.com:8300/industry/getAllName',
        async: false,
        success: function (res) {
            if (!res.success) {
                alert("查询所有产业出错: " + res.message)
            } else {
                industryList = res.content
            }
        }
    });

}

function setCardBodyContent() {
    var demoNum = 10
    var tmpText1 = ""
    for(var i = 0 ; i < demoNum + 5 && i < stockCodeList.length ; ++i){
        tmpText1 += (stockCodeList[Math.floor(Math.random()*stockCodeList.length)]+" ")
    }
    var tmpText2 = ""
    for(var i = 0 ; i < demoNum && i < conceptList.length ; ++i){
        tmpText2 += (conceptList[i]+" ")
    }
    var tmpText3 = ""
    for(var i = 0 ; i < demoNum && i < industryList.length ; ++i){
        tmpText3 += (industryList[i]+" ")
    }


    var cardBody = document.getElementById("cardBody")
    var content = "使用步骤: 1.在下拉菜单中选择查看维度；2.输入对应的查询参数（空格隔开）；3.点击query。<br><br>"
    content += "股票代码有：" + tmpText1 + " ...<br>"
    content += "概念股有：" + tmpText2 + " ...<br>"
    content += "产业有：" + tmpText3 + " ...<br>"
    content += "查看维度的所有值请点击链接 <a id = 'detailA' href='#'>详情</a><br>"
    content += "(输入参数建议复制粘贴，输入参数的个数避免过多，否则图过大难以准确显示，建议输入1、2个先尝试)<br>"
    content += "(可将鼠标置于图中并滑动(滚轴或者两根手指上下滑动)来缩放图；可按钮鼠标左键拖动图)"
    cardBody.innerHTML = content

    document.getElementById("detailA").addEventListener('click', function () {
        window.open("detail.html")
    })
}

function setDropdownItemListener() {
    var items = document.getElementsByClassName("dropdown-item")
    for(var i = 0 ; i < items.length; ++i){
        items[i].addEventListener('click', function (event) {
            document.getElementById("dropDownBtn").innerText = event.path[0].innerText
        })
    }
}

function setQueryBtnListener() {
    document.getElementById("queryBtn").addEventListener('click', function () {
        //清除原图数据
        document.getElementById("dataShowWrapperDiv").innerHTML = "<div id = \"dataShowDiv\" style=\"height: 120vh ; width: 90vw ; margin-left: 5vw ; margin-top: 2vh\"></div>"
        //获取选择纬度
        var choose = document.getElementById("dropDownBtn").innerText
        if(choose == undefined || choose == '选择查看维度'){
            alert("请选择查看维度");
        }
        else{
            //获取输入框中的输入
            var input = document.getElementById("inputArea").value.toString()
            input = input.replace(/(\r\n|\n|\r)/gm, " ");
            input = input.split(' ')
            input = checkInput(choose, input)
            var res = sendRequest(choose, input)
            if(res != undefined) {
                drawGraph(choose, res)
            }
        }
    })
}

function checkInput(choose, input) {
    var checkList = []
    if(choose == '股票代码') checkList = stockCodeList
    else if(choose == '概念股') checkList = conceptList
    else if(choose == '产业') checkList = industryList

    var inValidItem = []
    var validItem = []
    for(var index in input){
        if(input[index] == '') continue
        if(!checkList.some(item => item == input[index])) inValidItem.push(input[index])
        else validItem.push(input[index])
    }

    if(inValidItem.length > 0){
        alert("以下输入在数据库中不存在，自动忽略(请确保输入之间是空格隔开): " + inValidItem.join(' '))
    }
    return validItem;
}

function sendRequest(choose, data) {
    var res
    var url = ''
    if(choose == '股票代码') {
        url = "http://ismzl.com:8300/company/findCompanyByStockCode"
        for(var index in data){
            data[index] = parseInt(data[index])
        }
    }
    else if(choose == '概念股') url = "http://ismzl.com:8300/concept/findConceptByName"
    else if(choose == '产业') url = "http://ismzl.com:8300/industry/findIndustryByName"

    $.ajax({
             type : "POST",
             url : url, 
             async: false,
             data: JSON.stringify(data),
             contentType : "application/json",
             success : function(resp){
                if(!resp.success){
                    alert("查询失败: " + resp.message)
                    return undefined;
                }
                else{
                    res = resp.content
                }
             }
    });
    return res
}

function drawGraph(choose, content) {
    //从data构造类别、数据、link三个列表
    var categories = [], data = [], links = []
    if(choose == '股票代码') {
        categories = [{name: '股票'}, {name: '概念股'}, {name: '管理人'}, {name: '产业'}]
        for(var i = 0 ; i < content.length ; ++i){
            //股票
            var company = content[i].company
            company.id = company.stockCode + ""
            company.name = company.stockCode + ""
            company.des = ''
            company.category = 0
            data.push(company)

            //概念股
            var conceptList = content[i].concepts
            for(var index in conceptList){
                var concept = conceptList[index]
                concept.id = concept.name + ""
                concept.category = 1
                data.push(concept)
                links.push({source: company.id, target: concept.id, name: '属于'})
            }

            //董事
            var executives = content[i].executives
            for(var index in executives){
                var executive = executives[index]
                executive.id = executive.name + ""
                executive.category = 2
                data.push(executive)
                links.push({source: executive.id, target: company.id, name: 'work-in'})
            }

            //产业
            var industry = content[i].industry
            industry.id = industry.name + ""
            industry.category = 3
            data.push(industry)
            links.push({source: company.id, target: industry.id, name: '属于'})

            //去除重复data
            var validData = []
            for(var index in data){
                if(!validData.some(item => item.id == data[index].id)) validData.push(data[index])
            }
            data = validData
        }

    }
    else if(choose == '概念股'){
        categories = [{name: '股票'}, {name: '概念股'}]
        for(var i = 0 ; i < content.length ; ++i){
            //概念股
            var concept = content[i].concept
            concept.id = concept.id + ""
            concept.category = 1
            data.push(concept)
            //股票
            var companyList = content[i].companies
            for(var index in companyList){
                var company = companyList[index]
                company.id = company.id + ""
                company.category = 0
                company.name = company.stockCode + ""
                data.push(company)
                links.push({source: concept.id, target: company.id, name: '包含'})
            }
        }
        //去除重复data
        var validData = []
        for(var index in data){
            if(!validData.some(item => item.id == data[index].id)) validData.push(data[index])
        }
        data = validData

    }
    else if(choose == '产业') {
        categories = [{name: '股票'}, {name: '产业'}]
        for(var i = 0 ; i < content.length ; ++i){
            //产业
            var industry = content[i].industry
            industry.id = industry.id + ""
            industry.category = 1
            data.push(industry)
            //股票
            var companyList = content[i].companies
            for(var index in companyList){
                var company = companyList[index]
                company.id = company.id + ""
                company.category = 0
                company.name = company.stockCode + ""
                data.push(company)
                links.push({source: industry.id, target: company.id, name: '包含'})
            }
        }
        //去除重复data
        var validData = []
        for(var index in data){
            if(!validData.some(item => item.id == data[index].id)) validData.push(data[index])
        }
        data = validData
    }
    echartsDrawGraph(categories, data, links)
}

function echartsDrawGraph(categories, data, links) {
    var myChart = echarts.init(document.getElementById('dataShowDiv'));
    var categories = categories;

    let option = {
        // 图的标题
        title: {
            text: '关系图'
        },
        // 提示框的配置
        tooltip: {
                formatter: function(params, ticket, callback) {
                    var res = ""
                    var data = params.data
                    for(var key in data){
                        res += (key + ": " + data[key] + "<br />")
                    }
                    return res
                }
            },
        // 工具箱
        toolbox: {
            // 显示工具箱
            show: true,
            feature: {
                mark: {
                    show: true
                },
                // 还原
                restore: {
                    show: true
                },
                // 保存为图片
                saveAsImage: {
                    show: true
                }
            }
        },
        legend: [{
            // selectedMode: 'single',
            data: categories.map(function (a) {
                return a.name;
            })
        }],
        series: [{
            type: 'graph', // 类型:关系图
            layout: 'force', //图的布局，类型为力导图
            symbolSize: 40, // 调整节点的大小
            roam: true, // 是否开启鼠标缩放和平移漫游。默认不开启。如果只想要开启缩放或者平移,可以设置成 'scale' 或者 'move'。设置成 true 为都开启
            edgeSymbol: ['circle', 'arrow'],
            edgeSymbolSize: [2, 10],
            edgeLabel: {
                normal: {
                    textStyle: {
                        fontSize: 20
                    }
                }
            },
            force: {
                repulsion: 2500,
                edgeLength: [10, 50]
            },
            draggable: true,
            lineStyle: {
                normal: {
                    width: 2,
                    color: '#4b565b',
                }
            },
            edgeLabel: {
                normal: {
                    show: true,
                    formatter: function (x) {
                        return x.data.name;
                    }
                }
            },
            label: {
                normal: {
                    show: true,
                    textStyle: {}
                }
            },
            categories: categories,
            // 数据
            data: data,
            links: links
        }]
    };
    myChart.setOption(option);
}