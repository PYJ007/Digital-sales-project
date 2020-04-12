
//这是横版图片滚动和图片前方按钮
onload=function(){
    const arr = document.getElementsByClassName("li-banner");
    const banner_tag = document.getElementById("button3");
    for(let i=0; i<arr.length; i++){
        arr[i].style.left = i*100+"%";
    }
    banner_tag.style.background = "red";
};
function LeftMove(){
    const arr = document.getElementsByClassName("li-banner");//获取三个子div
    const banner_tag = document.getElementsByClassName("li-button");
    for(let i=0; i<arr.length; i++){
        let left = parseFloat(arr[i].style.left);
        left-=10;
        const width = 100;
        banner_tag[i].style.background = "rgba(255,255,255,0.5)";
        if(left<=-width){
            left=(arr.length-1)*width;//当图片完全走出显示框，拼接到末尾
            banner_tag[i].style.background = "red";
            clearInterval(moveId);
        }
        arr[i].style.left = left+"%";

    }
}
function divInterval(){
    moveId = setInterval(LeftMove, 60);//设置一个10毫秒定时器
}


timeId=setInterval(divInterval,10000);//设置一个3秒的定时器。

function stop(){
    clearInterval(timeId);
}
function start(){
    clearInterval(timeId);
    timeId = setInterval(divInterval, 4000);
    //    这里才是设置时间的
}

//页面失去焦点停止
onblur = function(){
    stop();
};
//页面获取焦点时开始
onfocus = function(){
    start();
};

//这是推送页面鼠标移动翻页
function trans(x) {
    const tags = document.getElementsByClassName("tab-tag");
    const lis = document.getElementsByClassName("dd01");
    for(let i=0;i<tags.length;i++) {
        tags[i].classList.remove("active");
        lis[i].style.display = "none";
    }
    tags[x].classList.add("active");
    lis[x].style.display = "block";
}




