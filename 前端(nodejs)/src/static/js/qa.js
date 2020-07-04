window.onload = function() {
    //设置查询按钮监听
    setQueryBtnListener()
}

function setQueryBtnListener() {
    document.getElementById("queryBtn").addEventListener('click', function () {
            //获取输入框中的输入
            var input = document.getElementById("questionArea").value.toString()
            input = input.replace(/(\r\n|\n|\r)/gm, "");
            var res = sendRequest(input)
            if(res != undefined) {
                var answerArea = document.getElementById("answerArea");
                answerArea.setAttribute('disabled', false)
                if(answerArea.innerHTML.length == 0){
                    answerArea.innerHTML = res;
                }
                else{
                    answerArea.innerHTML = answerArea.innerHTML + "\n" + res;
                }
                answerArea.setAttribute('disabled', true)
                answerArea.scrollTop = answerArea.scrollHeight;
            }
    })
    document.getElementById("clearBtn").addEventListener('click', function() {
        var answerArea = document.getElementById("answerArea");
        answerArea.innerHTML = ''
    })
    document.getElementById("qaDemo").addEventListener('click', function() {
        window.open('qademo.html')
    })
}

function sendRequest(question) {
    var url = "http://ismzl.com:8100/query?question=" + question;//ismzl.com
    var res
    $.ajax({
        type : "get",
        url : url, 
        async: false,
        success : function(resp){
           res = resp
        }
});
    return formatDate(new Date()) + ": " + res;
}

function formatDate(date) {
    let year=date.getFullYear();// getFullYear() 返回年
    let month=date.getMonth()+1;// getMonth() 返回月份 (0 ~ 11)
    let day = date.getDate();// getDate() 返回日 (1 ~ 31)
    let hours=date.getHours(); // getHours() 返回小时 (0 ~ 23)
    let minutes=date.getMinutes();// getMinutes() 返回分(0 ~ 59)
    let seconds=date.getSeconds();// getSeconds() 返回秒(0 ~ 59)


    if(month < 10) month = "0"+month
    if(day < 10) day = "0"+day
    if(hours < 10) hours = "0"+hours
    if(minutes < 10) minutes = "0"+minutes
    if(seconds < 10) seconds = "0"+seconds

    return year + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + seconds;
}