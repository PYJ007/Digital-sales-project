//这是专家推送页面鼠标移动翻页
function turned(x) {
    const tags = document.getElementsByClassName("ad-hot-li");
    const lis = document.getElementsByClassName("ad-hot-01");
    for(let i=0;i<5;i++) {
        tags[i].classList.remove("active");
        lis[i].style.display = "none";
    }
    tags[x].classList.add("active");
    lis[x].style.display = "block";
}

//这是商品推送页的翻动
function turn_on(x) {
    const tags = document.getElementsByClassName("pro-nav");
    const lis = document.getElementsByClassName("dd02");
    for(let i=0;i<tags.length;i++) {
        tags[i].classList.remove("active");
        lis[i].style.display = "none";
    }
    tags[x].classList.add("active");
    lis[x].style.display = "block";
}
