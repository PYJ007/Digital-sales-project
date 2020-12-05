


//选择的动态联动

//定义省市的信息
let provList = ['银川市','石嘴山市','吴忠市','固原市','中卫市'];
let cityList = [];
cityList[0] = ['西夏区','金凤区','兴庆区','永宁县','贺兰县','武灵市'];//银川
cityList[1] = ['大武口', '惠农区', '平罗县'];//石嘴山
cityList[2] = ['利通区', '红寺堡区', '盐池县', '同心县','青铜峡市'];//吴忠
cityList[3] = ['原州区', '西吉县', '隆德县', '泾源县','彭阳县'];//固原
cityList[4] = ['沙坡头区','中宁县','海原县'];//中卫

let countryList = [[],[],[],[],[]];
//使用三维数组来存储县信息
//银川
countryList[0][0] = ['西花园路街道', '北京西路街道', '文昌路街道', '朔方路街道', '宁华路街道', '贺兰山西路街道', '怀远路街道','镇北堡镇', '兴泾镇'];//西夏区
countryList[0][1] = ['丰登镇', '良田镇', '上海西路街道', '北京中路街道', '长城中路街道', '黄河东路街道', '满城北街街道'];//金凤区
countryList[0][2] = ['凤凰北街街道', '解放西街街道', '文化街街道', '富宁街街道', '新华街街道', '玉皇阁北街街道', '前进街街道', '中山南街街道', '银古路街道', '胜利街街道', '丽景街街道', '掌政镇', '大新镇', '通贵乡', '月牙湖乡'];//兴庆区
countryList[0][3] = ['杨和街道','杨和镇','李俊镇','望远镇','望洪镇','闽宁镇','胜利乡'];//永宁县
countryList[0][4] = ['金贵镇', '立岗镇', '洪广镇', '习岗镇', '常信乡'];//贺兰山县
countryList[0][5] = ['东塔镇','郝家桥镇','崇兴镇','宁东镇','马家滩镇','临河镇','桐树乡','白土岗乡'];//武灵
//石嘴山
countryList[1][0] = ['长胜街道', '朝阳街道', '人民路街道', '长城街道', '青山街道', '石炭井街道', '白芨沟街道', '沟口街道', '长兴街道','锦林街道', '星海镇'];//大武口
countryList[1][1] = ['北街街道', '南街街道', '中街街道', '育才路街道', '河滨街街道', '火车站街道', '红果子镇', '尾闸镇', '园艺镇', '庙台乡', '礼和乡', '燕子墩乡'];//惠农区
countryList[1][2] = ['城关镇', '黄渠桥镇', '宝丰镇', '头闸镇', '姚伏镇', '崇岗镇', '陶乐镇', '高庄乡', '灵沙乡', '渠口乡', '通伏乡', '高仁乡', '红崖子乡'];//平罗县
//吴忠
countryList[2][0] = ['古城镇', '上桥镇', '胜利镇', '金星镇', '金积镇', '金银滩镇', '高闸镇', '扁担沟镇', '东塔寺乡', '板桥乡', '马莲渠乡', '郭家桥乡'];//利通区
countryList[2][1] = ['红寺堡镇', '太阳山镇', '南川乡', '大河乡', '沙泉乡', '买河乡'];//红寺堡
countryList[2][2] = ['花马池镇', '大水坑镇', '惠安堡镇', '高沙窝镇', '王乐井乡', '冯记沟乡', '青山乡', '麻黄山乡'];//盐池县
countryList[2][3] = ['豫海镇河西镇', '丁塘镇', '韦州镇', '下马关镇', '预旺镇', '王团镇', '田老庄乡', '马高庄乡', '张家塬乡', '兴隆乡', '石狮开发区管委会', '窑山管委会'];//同心县
countryList[2][4] = ['小坝镇','大坝镇','青铜峡镇','叶盛镇','瞿靖镇','峡口镇','邵刚镇','陈袁滩镇'];//青铜峡
//固原
countryList[3][0] = ['三营镇', '头营镇', '官厅镇', '张易镇', '开城镇', '彭堡镇', '黄铎堡镇', '寨科乡', '炭山乡', '中河乡', '河川乡', '南关街道', '古雁街道', '北塬街道'];//原州区
countryList[3][1] = ['吉强镇', '兴隆镇', '平峰镇', '将台堡镇', '新营乡', '红耀乡', '田坪乡', '马建乡', '震湖乡', '兴平乡', '西滩乡', '王民乡', '什字乡', '马莲乡', '硝河乡', '偏城乡', '沙沟乡', '白崖乡', '火石寨乡'];//西吉县
countryList[3][2] = ['城关镇', ' 沙塘镇', '联财镇', '陈靳乡', '好水乡', '观庄乡', '杨河乡', '神林乡', '张程乡', '凤岭乡', '山河乡 ', '温堡乡', '奠安乡'];//隆德县
countryList[3][3] = ['香水镇', '泾河源镇', '六盘山镇', '黄花乡', '大湾乡', '兴盛乡', '新民乡'];//泾源县
countryList[3][4] = ['白阳镇', '王洼镇', '古城镇', '红河镇', '新集乡', ' 城阳乡', ' 冯庄乡', ' 小岔乡', ' 孟塬乡', ' 罗洼乡 ', '交岔乡', '草庙乡'];//彭阳县
//中卫
countryList[4][0] = ['文昌镇', '滨河镇', '迎水桥镇', '东园镇', '柔远镇', '镇罗镇', '宣和镇', '永康镇', '常乐镇', '兴仁镇', '香山乡', '蒿川乡'];//沙坡头区
countryList[4][1] = ['宁安镇', '鸣沙镇', '石空镇', '新堡镇', '恩和镇', '舟塔乡', '白马乡', '余丁乡', '大战场乡', '喊叫水乡', '徐套乡'];//中宁县
countryList[4][2] = ['三河镇', '西安镇', '李旺镇', '贾塘乡', '曹洼乡', '郑旗乡', '高崖乡', '关桥乡', '李俊乡', '树台乡', '九彩乡', '关庄乡', '红羊乡', '史店乡', '罗山乡', '高台乡', '杨明乡'];//海原县

//作物信息
let cropList = ['玉米','水稻','枸杞'];
let breedList = [];
breedList[0] = ['保玉1号','苏玉31','中糯1号'];
breedList[1] = ['旱优73','宁粳4号','宁粳1号'];
breedList[2] = ['中华枸杞','北方枸杞','宁夏枸杞'];

//获取select元素
let provSelects = document.getElementsByClassName('prov');
let citySelects = document.getElementsByClassName('city');
let countrySelects = document.getElementsByClassName('country');
let cropsSelects = document.getElementsByClassName('crops');
let breedsSelects  = document.getElementsByClassName('breeds');

//作物信息添加
for(let i=0;i<cropsSelects.length;i++) {
    cropList.forEach(function(val, index){
        cropsSelects[i].add(new Option(val, index));

    });
    cropsSelects[i].onchange = function () {
        let index = this.value;
        breedsSelects[i].length = 0;
        breedList[index].forEach(function (val, index) {
            breedsSelects[i].add(new Option(val, index));
        })
    };
}


for(let i=0;i<citySelects.length;i++) {
    //把省的信息 添加到第一个select元素中
    provList.forEach(function(val, index){
        //DOM操作  了解
        provSelects[i].add(new Option(val, index));

    });

    let index1;
    //给第一个select绑定change事件
    provSelects[i].onchange = function () {
        //获取 当前的选项
        index1 = this.value;

        //清空第二个select原先内容
        citySelects[i].length = 0;

        //选择对应的城市列表，添加到第二个select
        cityList[index1].forEach(function (val, index) {
            citySelects[i].add(new Option(val, index));
        })
    };


    citySelects[i].onchange = function () {
        let index = this.value;
        countrySelects[i].length = 0;
        countryList[index1][index].forEach(function (val, index) {
            countrySelects[i].add(new Option(val, val));
        })
    };

    //手工触发一次 change事件
    provSelects[i].onchange();
    citySelects[i].onchange();
}
for(let i=0;i<cropsSelects.length;i++) {
    cropsSelects[i].onchange();
}

function add() {
    let tag1 = document.getElementsByClassName('crops-detail')[0];
    let tag2 = document.getElementById('add-button');
    let tag3 = document.getElementById('farmerProfile');
    let node = tag1.cloneNode(true);
    tag3.insertBefore(node,tag2);
}
