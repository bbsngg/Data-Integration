//全局变量
var stockCodeList = [] //股票代码list
var conceptList = [] //概念股list
var industryList = [] //产业list

window.onload = function () {
    //请求数据
    getBaseData()
    //设置数据
    setData()
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

function setData() {
    var stockArea = document.getElementById("stockArea")
    var conceptArea = document.getElementById("conceptArea")
    var industryArea = document.getElementById("industryArea")

    var tmpText1 = ""
    var tmpText2 = ""
    var tmpText3 = ""
    for(var index in stockCodeList){
        tmpText1 += (stockCodeList[index] + " ")
    }
    for(var index in conceptList){
        tmpText2 += (conceptList[index] + " ")
    }
    for(var index in industryList){
        tmpText3 += (industryList[index] + " ")
    }

    stockArea.innerText = tmpText1
    stockArea.setAttribute("disabled", "true")

    conceptArea.innerText = tmpText2
    conceptArea.setAttribute("disabled", "true")

    industryArea.innerText = tmpText3
    industryArea.setAttribute("disabled", "true")
}